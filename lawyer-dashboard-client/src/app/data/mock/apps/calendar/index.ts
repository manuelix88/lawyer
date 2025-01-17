import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import * as moment from 'moment';
import RRule, { RRuleSet, rrulestr } from 'rrule';
import { TreoMockApi } from '@treo/lib/mock-api/mock-api.interfaces';
import { TreoMockApiService } from '@treo/lib/mock-api/mock-api.service';
import { TreoMockApiUtils } from '@treo/lib/mock-api/mock-api.utils';
import { calendars as calendarsData, events as eventsData, exceptions as exceptionsData, settings as settingsData, weekdays as weekdaysData } from 'app/data/mock/apps/calendar/data';

@Injectable({
    providedIn: 'root'
})
export class CalendarMockApi implements TreoMockApi
{
    // Private
    private _calendars: any[];
    private _events: any[];
    private _exceptions: any[];
    private _settings: any;
    private _weekdays: any;

    /**
     * Constructor
     *
     * @param {TreoMockApiService} _treoMockApiService
     */
    constructor(
        private _treoMockApiService: TreoMockApiService
    )
    {
        // Set the data
        this._calendars = calendarsData;
        this._events = eventsData;
        this._exceptions = exceptionsData;
        this._settings = settingsData;
        this._weekdays = weekdaysData;

        // Register the API endpoints
        this.register();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Private methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Generates an RRuleSet from given event
     *
     * @param event
     * @param dtStart
     * @param until
     * @private
     */
    private _generateRuleset(event, dtStart, until): RRuleSet | RRule
    {
        // Parse the recurrence rules
        const parsedRules = {};
        event.recurrence.split(';').forEach((rule) => {

            // Split the rule
            const parsedRule = rule.split('=');

            // Omit UNTIL or COUNT from the parsed rules since we only
            // need them for calculating the event's end date. We will
            // add an UNTIL later based on the above calculations.
            if ( parsedRule[0] === 'UNTIL' || parsedRule[0] === 'COUNT' )
            {
                return;
            }

            // Add the rule to the parsed rules
            parsedRules[parsedRule[0]] = parsedRule[1];
        });

        // Generate the rule array from the parsed rules
        const rules = [];
        Object.keys(parsedRules).forEach((key) => {
            rules.push(key + '=' + parsedRules[key]);
        });

        // Prepare the ruleSet
        const ruleSet = [];

        // Add DTSTART
        ruleSet.push('DTSTART:' + dtStart.format('YYYYMMDD[T]HHmmss[Z]'));

        // Add RRULE
        ruleSet.push('RRULE:' + rules.join(';') + ';UNTIL=' + until.format('YYYYMMDD[T]HHmmss[Z]'));

        // Find and add any available exceptions to the rule
        this._exceptions.forEach((item) => {

            // If the item is an exception to this event...
            if ( item.eventId === event.id )
            {
                // Add it as an EXDATE to the rrule
                ruleSet.push('EXDATE:' + moment(item.exdate).format('YYYYMMDD[T]HHmmss[Z]'));
            }
        });

        // Create an RRuleSet from the ruleSet array
        return rrulestr(ruleSet.join('\n'), {forceset: true});
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Register
     */
    register(): void
    {
        // -----------------------------------------------------------------------------------------------------
        // @ Calendars - GET
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onGet('api/apps/calendar/calendars')
            .reply(() => {

                // Clone the calendars
                const calendars = _.cloneDeep(this._calendars);

                return [
                    200,
                    calendars
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Calendars - PUT
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onPut('api/apps/calendar/calendars')
            .reply((request) => {

                // Get the calendar as the new calendar
                const newCalendar = _.cloneDeep(request.body.calendar);

                // Add an id to the new calendar
                newCalendar.id = TreoMockApiUtils.guid();

                // Push the new calendar
                this._calendars.push(newCalendar);

                return [
                    200,
                    newCalendar
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Calendars - PATCH
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onPatch('api/apps/calendar/calendars')
            .reply((request) => {

                // Get the id and calendar
                const id = request.body.id;
                const calendar = _.cloneDeep(request.body.calendar);

                // Prepare the updated calendar
                let updatedCalendar = null;

                // Find the calendar and update it
                this._calendars.forEach((item, index, calendars) => {

                    if ( item.id === id )
                    {
                        // Update the calendar
                        calendars[index] = _.assign({}, calendars[index], calendar);

                        // Store the updated calendar
                        updatedCalendar = calendars[index];
                    }
                });

                return [
                    200,
                    updatedCalendar
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Calendars - DELETE
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onDelete('api/apps/calendar/calendars')
            .reply((request) => {

                // Get the id
                const id = request.params.get('id');

                // Find the calendar and delete it
                const index = this._calendars.findIndex((calendar) => calendar.id === id);
                this._calendars.splice(index, 1);

                // Find the events that belong to the calendar and remove them as well
                this._events = this._events.filter((event) => event.calendarId !== id);

                return [
                    200,
                    true
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Events - GET
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onGet('api/apps/calendar/events')
            .reply((request) => {

                // Get the start and end dates as moment
                const viewStart = moment(request.params.get('start')).startOf('day');
                const viewEnd = moment(request.params.get('end')).endOf('day');

                // Clone the events
                const events = _.cloneDeep(this._events);

                // Prepare the results
                const results = [];

                // Go through the events...
                events.forEach((event) => {

                    // Get the event's start and end dates as moment
                    const eventStart = moment(event.start);
                    const eventEnd = moment(event.end);

                    // If it's a normal event...
                    if ( !event.recurrence )
                    {
                        // Only grab the event if it's within the range
                        if ( eventStart.isSameOrAfter(viewStart, 'day') && eventEnd.isSameOrBefore(viewEnd, 'day') )
                        {
                            // Push the event into the results array
                            results.push(event);
                        }
                    }
                    // If it's a recurring event...
                    else
                    {
                        // Skip if the event does not recur within the view range
                        if ( eventStart.isAfter(viewEnd, 'day') || eventEnd.isBefore(viewStart, 'day') )
                        {
                            return;
                        }

                        // Set the DTSTART and UNTIL for RRule
                        const dtStart = eventStart.clone();
                        const until = viewEnd.isBefore(eventEnd) ? viewEnd.clone().utc() : eventEnd.clone().utc();

                        // Create an RRuleSet
                        const rruleset = this._generateRuleset(event, dtStart, until);

                        // Generate the recurring dates and loop through them
                        rruleset.all().forEach((date) => {

                            // Get the rule date as a moment
                            const ruleDate = moment(date);

                            // Subtract the UTC Offset from the rule date as we use local time for DTSTART.
                            // The reason for this is simple; if we use UTC dates for DTSTART, RRule generated
                            // dates can shift. Since we have to store the dates as UTC, we can figure out the
                            // UTC value by simply subtracting the UTC Offset (minutes) from the rule date rather
                            // than using UTC dates in the first place. This will ensure there will be no time/day
                            // shift on generated rules since they will be generated based on the local time.
                            ruleDate.subtract(ruleDate.utcOffset(), 'minutes');

                            // Skip the date if it's not in between the view start and view end
                            // to prevent generating unnecessary amount of instances and to
                            // prevent instance duplication
                            if ( !ruleDate.isBetween(viewStart, viewEnd, 'day', '[]') )
                            {
                                return;
                            }

                            // Prepare the event instance
                            const eventInstance = {

                                // Generate an instance id using the event id and rule date
                                id: event.id + '_' + ruleDate.clone().utc().format('YYYYMMDD[T]HHmmss[Z]'),

                                // Set the recurringEventId on the event so we don't lose the event's origin
                                recurringEventId: event.id,

                                // Whether this is the first instance of the recurring event or not
                                isFirstInstance: event.start === ruleDate.clone().toISOString(),

                                // Get the rest of the data
                                calendarId : event.calendarId,
                                title      : event.title,
                                description: event.description,
                                start      : ruleDate.toISOString(),
                                end        : ruleDate.add(event.duration, 'minutes').toISOString(),
                                duration   : event.duration,
                                allDay     : event.allDay,
                                recurrence : event.recurrence
                            };

                            // Push the event instance to the results array
                            results.push(eventInstance);
                        });
                    }
                });

                return [
                    200,
                    results
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Event - PUT
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onPut('api/apps/calendar/event')
            .reply((request) => {

                // Get the event as the new event
                const newEvent = _.cloneDeep(request.body.event);

                // Add an id to the new event
                newEvent.id = TreoMockApiUtils.guid();

                // Unshift the new event
                this._events.unshift(newEvent);

                return [
                    200,
                    newEvent
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Event - PATCH
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onPatch('api/apps/calendar/event')
            .reply((request) => {

                // Get the id and event
                const id = request.body.id;
                const event = _.cloneDeep(request.body.event);

                // Prepare the updated event
                let updatedEvent = null;

                // Find the event and update it
                this._events.forEach((item, index, events) => {

                    if ( item.id === id )
                    {
                        // Update the event
                        events[index] = _.assign({}, events[index], event);

                        // Store the updated event
                        updatedEvent = events[index];
                    }
                });

                return [
                    200,
                    updatedEvent
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Event - DELETE
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onDelete('api/apps/calendar/event')
            .reply((request) => {

                // Get the id and event
                const id = request.params.get('id');

                // Find the event and delete it
                const index = this._events.findIndex((item) => item.id === id);
                this._events.splice(index, 1);

                return [
                    200,
                    true
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Recurring Event - PATCH
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onPatch('api/apps/calendar/recurring-event')
            .reply((request) => {

                // Get the event, original event and mode
                const event = _.cloneDeep(request.body.event);
                const originalEvent = _.cloneDeep(request.body.originalEvent);
                const mode = request.body.mode;

                // Find the original recurring event from db
                const recurringEvent = this._events.find((item) => item.id === event.recurringEventId);

                // Single
                if ( mode === 'single' )
                {
                    // Create a new event from the event while ignoring the range and recurringEventId
                    const {range, recurringEventId, ...newEvent} = event;

                    // Generate a unique id for the event
                    newEvent.id = TreoMockApiUtils.guid();

                    // Calculate the end date using the start date and the duration
                    newEvent.end = moment(newEvent.start).add(newEvent.duration, 'minutes');

                    // Null-ify the recurrence and duration
                    newEvent.duration = null;
                    newEvent.recurrence = null;

                    // Push the new event to the events array
                    this._events.push(newEvent);

                    // If this is the first instance of the recurring event...
                    if ( originalEvent.start === recurringEvent.start )
                    {
                        // Generate the rruleset
                        const rruleset = this._generateRuleset(recurringEvent, moment(recurringEvent.start), moment(recurringEvent.end).utc());

                        // Generate the dates using rruleset and get the 2nd date from start
                        const ruleDate = moment(rruleset.all((date, i) => i < 2)[1]);

                        // Subtract the UTC Offset from the rule date as we use local time for DTSTART.
                        // The reason for this is simple; if we use UTC dates for DTSTART, RRule generated
                        // dates can shift. Since we have to store the dates as UTC, we can figure out the
                        // UTC value by simply subtracting the UTC Offset (minutes) from the rule date rather
                        // than using UTC dates in the first place. This will ensure there will be no time/day
                        // shift on generated rules since they will be generated based on the local time.
                        ruleDate.subtract(ruleDate.utcOffset(), 'minutes');

                        // Update the recurring event's start date
                        recurringEvent.start = ruleDate.toISOString();
                    }
                    // Otherwise...
                    else
                    {
                        // Add a new exception for the recurring event that ignores this single event's start date
                        this._exceptions.push({
                            id     : TreoMockApiUtils.guid(),
                            eventId: originalEvent.recurringEventId,
                            exdate : moment(originalEvent.start).toISOString()
                        });
                    }
                }

                // Future
                if ( mode === 'future' )
                {
                    // Update the end date
                    recurringEvent.end = moment(originalEvent.start).subtract(1, 'day').endOf('day').toISOString();

                    // Parse the recurrence rules from the original event
                    const parsedRules = {};
                    originalEvent.recurrence.split(';').forEach((rule) => {
                        const parsedRule = rule.split('=');
                        parsedRules[parsedRule[0]] = parsedRule[1];
                    });

                    // Add/Update the UNTIL rule
                    parsedRules['UNTIL'] = moment(recurringEvent.end).utc().format('YYYYMMDD[T]HHmmss[Z]');

                    // Generate the rule string from the parsed rules
                    const rules = [];
                    Object.keys(parsedRules).forEach((key) => {
                        rules.push(key + '=' + parsedRules[key]);
                    });
                    const rrule = rules.join(';');

                    // Update the recurrence on the original recurring event
                    recurringEvent.recurrence = rrule;

                    // Create a new event from the event while ignoring the recurringEventId
                    const {recurringEventId, ...newEvent} = event;

                    // Generate a unique id for the event
                    newEvent.id = TreoMockApiUtils.guid();

                    // Push the new event to the events array
                    this._events.push(newEvent);
                }

                // All
                if ( mode === 'all' )
                {
                    // Find the event index
                    const eventIndex = this._events.findIndex((item) => item.id === event.recurringEventId);

                    // Update the recurring event
                    this._events[eventIndex] = _.assign({}, this._events[eventIndex], _.omit(event, ['id', 'recurringEventId', 'range']));
                }

                return [
                    200,
                    true
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Recurring Event - DELETE
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onDelete('api/apps/calendar/recurring-event')
            .reply((request) => {

                // Get the event and mode
                const event = JSON.parse(request.params.get('event'));
                const mode = request.params.get('mode');

                // Find the recurring event
                const recurringEvent = this._events.find((item) => item.id === event.recurringEventId);

                // Single
                if ( mode === 'single' )
                {
                    // If this is the first instance of the recurring event...
                    if ( event.start === recurringEvent.start )
                    {
                        // Generate the rruleset
                        const rruleset = this._generateRuleset(recurringEvent, moment(recurringEvent.start), moment(recurringEvent.end).utc());

                        // Generate the dates using rruleset and get the 2nd date from start
                        const ruleDate = moment(rruleset.all((date, i) => i < 2)[1]);

                        // Subtract the UTC Offset from the rule date as we use local time for DTSTART.
                        // The reason for this is simple; if we use UTC dates for DTSTART, RRule generated
                        // dates can shift. Since we have to store the dates as UTC, we can figure out the
                        // UTC value by simply subtracting the UTC Offset (minutes) from the rule date rather
                        // than using UTC dates in the first place. This will ensure there will be no time/day
                        // shift on generated rules since they will be generated based on the local time.
                        ruleDate.subtract(ruleDate.utcOffset(), 'minutes');

                        // Update the recurring event's start date
                        recurringEvent.start = ruleDate.toISOString();
                    }
                    // Otherwise...
                    else
                    {
                        // Add a new exception for the recurring event that ignores this single event's start date
                        this._exceptions.push({
                            id     : TreoMockApiUtils.guid(),
                            eventId: event.recurringEventId,
                            exdate : moment(event.start).toISOString()
                        });
                    }
                }

                // Future
                if ( mode === 'future' )
                {
                    // Update the end date of the event
                    recurringEvent.end = moment(event.start).subtract(1, 'day').endOf('day').toISOString();

                    // Parse the recurrence rules of the event
                    const parsedRules = {};
                    recurringEvent.recurrence.split(';').forEach((rule) => {
                        const parsedRule = rule.split('=');
                        parsedRules[parsedRule[0]] = parsedRule[1];
                    });

                    // Add/Update the UNTIL rule
                    parsedRules['UNTIL'] = moment(event.end).utc().format('YYYYMMDD[T]HHmmss[Z]');

                    // Generate the rule string from the parsed rules
                    const rules = [];
                    Object.keys(parsedRules).forEach((key) => {
                        rules.push(key + '=' + parsedRules[key]);
                    });
                    const rrule = rules.join(';');

                    // Update the recurrence of the event
                    recurringEvent.recurrence = rrule;
                }

                // All
                if ( mode === 'all' )
                {
                    // Find the event and delete it
                    const index = this._events.findIndex((item) => item.id === event.recurringEventId);
                    this._events.splice(index, 1);
                }

                return [
                    200,
                    true
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Settings - GET
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onGet('api/apps/calendar/settings')
            .reply(() => {

                // Clone the settings
                const settings = _.cloneDeep(this._settings);

                return [
                    200,
                    settings
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Settings - PATCH
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onPatch('api/apps/calendar/settings')
            .reply((request) => {

                // Get the settings
                const settings = _.cloneDeep(request.body.settings);

                // Store the updated settings
                this._settings = settings;

                return [
                    200,
                    settings
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Weekdays - GET
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onGet('api/apps/calendar/weekdays')
            .reply(() => {

                // Clone the weekdays
                const weekdays = _.cloneDeep(this._weekdays);

                // If the startWeekOn setting is set to Sunday...
                if ( this._settings.startWeekOn === 0 )
                {
                    // Move the Sunday to the beginning
                    weekdays.unshift(weekdays.pop());
                }

                // If the startWeekOn is set to Saturday...
                if ( this._settings.startWeekOn === 6 )
                {
                    // Move the Sunday to the beginning
                    weekdays.unshift(weekdays.pop());

                    // Then move the Saturday to the beginning
                    weekdays.unshift(weekdays.pop());
                }

                return [
                    200,
                    weekdays
                ];
            });
    }
}
