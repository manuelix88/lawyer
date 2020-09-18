import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { forkJoin, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {ApiService} from './core/services/api';
import {GenericsService} from './core/services/generics.service';
import {ApplicationStoreService} from './core/store/application-store.service';
import {Codice, Status, Tribunale} from './modules/admin/customer-view/model/report-patronato';
import {
    AvvocatiDelegatiArray,
    CodiceArray, PatronatiArray,
    StatusArray,
    TipoPraticheArray,
    TribunaleArray
} from './core/store/generics.store';
import {Avvocati} from './modules/admin/customer-view/model/avvocati';
import {TipoPratiche} from './modules/admin/customer-view/model/tipo-pratiche';
import {PatronatoProvenienza} from './modules/admin/customer-view/model/patronatoProvenienza';

@Injectable({
    providedIn: 'root'
})
export class InitialDataResolver implements Resolve<any>
{
    /**
     * Constructor
     *
     * @param {HttpClient} _httpClient
     * @param _genericService
     * @param _store
     */
    constructor(
        private _httpClient: HttpClient, private _genericService: GenericsService, private _store: ApplicationStoreService
    )
    {
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Private methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Load messages
     *
     * @private
     */
    private _loadMessages(): Observable<any>
    {
        return this._httpClient.get('api/common/messages');
    }

    /**
     * Load navigation data
     *
     * @private
     */
    private _loadNavigation(): Observable<any>
    {
        return this._httpClient.get('api/common/navigation');
    }

    /**
     * Load notifications
     *
     * @private
     */
    private _loadNotifications(): Observable<any>
    {
        return this._httpClient.get('api/common/notifications');
    }

    /**
     * Load shortcuts
     *
     * @private
     */
    private _loadShortcuts(): Observable<any>
    {
        return this._httpClient.get('api/common/shortcuts');
    }

    /**
     * Load user
     *
     * @private
     */
    private _loadUser(): Observable<any>
    {
        return this._httpClient.get('api/common/user');
    }

    private _loadTribunali(): void {
        this._genericService.getAllTribunali().then((values:Tribunale[]) => {
            this._store.updateStore(new TribunaleArray(values));
        })
    }

    private _loadCodici(): void {
        this._genericService.getAllCodice().then((values:Codice[]) => {
            this._store.updateStore(new CodiceArray(values));
        })
    }

    private _loadStatus(): void {
        this._genericService.getAllStatus().then((values: Status[]) => {
            this._store.updateStore(new StatusArray(values));
        })
    }

    private _loadAvvocati(): void {
        this._genericService.retrieveAllAvvocati().then((values: Avvocati[]) => {
            this._store.updateStore(new AvvocatiDelegatiArray(values));
        })
    }

    private _loadTipoPratiche(): void {
        this._genericService.retrieveAllTipoPratiche().then((values: TipoPratiche[]) => {
            this._store.updateStore(new TipoPraticheArray(values));
        })
    }

    private _loadPatronati(): void {
        this._genericService.retrieveAllPatronati().then((values: PatronatoProvenienza[]) => {
            this._store.updateStore(new PatronatiArray(values));
        })
    }
    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Resolver
     *
     * @param route
     * @param state
     */
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any>
    {
        // this._loadCodici();
        this._loadStatus();
        this._loadTribunali()
        this._loadTipoPratiche();
        this._loadAvvocati();
        this._loadPatronati();
        return forkJoin([

            // Messages
            this._loadMessages(),

            // Navigation data
            this._loadNavigation(),

            // Notifications
            // this._loadNotifications(),

            // Shortcuts
            // this._loadShortcuts(),

            // User
            this._loadUser(),



        ]).pipe(
            map((data) => {

                return {
                    messages     : data[0].messages,
                    navigation   : {
                        compact   : data[1].compact,
                        default   : data[1].default,
                        futuristic: data[1].futuristic,
                        horizontal: data[1].horizontal
                    },
                    // notifications: data[2].notifications,
                    // shortcuts    : data[3].shortcuts,
                    // user         : data[4].user
                };
            })
        );
    }
}
