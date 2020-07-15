import {Component, Input, Optional, Host, Output, EventEmitter} from '@angular/core';
import { SatPopover } from '@ncstate/sat-popover';
import {filter} from 'rxjs/operators';
import {DataUdienze} from '../customer-view/model/report-patronato';
import moment from 'moment';

@Component({
    selector: 'inline-edit',
    styleUrls: ['in-line-edit.component.scss'],
    template: `
        <div class="template">
            <form>
                <div class="mat-subheading-2">Aggiungi Una Data Udienza</div>
                <div class="flex-col "></div>
                <ng-container *ngFor="let d of date">
                    <div class="flex justify-between flex-auto">
                        <div> {{d.dataUdienza | date: 'dd/MM/yyyy'}}</div>
                        <div class="flex">
                            <mat-checkbox [checked]="d.enable" (change)="d.enable = !d.enable" class="flex-auto m-4"></mat-checkbox>
                            <button mat-icon-button type="button" class=" flex-auto ml-4" (click)="modificaData(d)">
                                <mat-icon>edit</mat-icon>
                            </button>

                        </div>
                    </div>
                </ng-container>
                <mat-form-field  class="flex-auto gt-xs:pr-3">
                    <mat-label>Data Udienza</mat-label>
                    <input matInput  type="text" [placeholder]="'Data Udienza'" [matDatepicker]="picker"  [(ngModel)]="dataUdienza.dataUdienza" name="dataUdienza">
                    <mat-datepicker-toggle matPrefix [for]="picker"></mat-datepicker-toggle>
                    <mat-datepicker #picker></mat-datepicker>
                </mat-form-field>
                <div class="actions">
                    <button mat-button type="button" color="primary" (click)="onCancel()">Esci</button>
                    <button mat-button type="submit" color="primary" (click)="saveData()">Aggiungi</button>
                </div>
            </form></div>
    `
})
export class InlineEditComponent {

    /** Overrides the comment and provides a reset value when changes are cancelled. */
        // tslint:disable-next-line:no-input-rename
    @Input('dataUltimaUdienza') date: DataUdienze[];
    @Output('savaData') dataEmitter = new EventEmitter<DataUdienze>();
    /** Form model for the input. */
    comment = '';
    dataUdienza: DataUdienze;

    constructor(@Optional() @Host() public popover: SatPopover) { }

    ngOnInit(): void {
        this.dataUdienza = new DataUdienze();
        // subscribe to cancellations and reset form value
        // if (this.popover) {
        //     this.popover.closed.pipe(filter(val => val == null))
        //         .subscribe(() => this.comment = this.value || '');
        // }
    }

    onSubmit(): void {
        if (this.popover) {
            this.popover.close(this.comment);
        }
    }

    onCancel(): void {
        if (this.popover) {
            this.dataUdienza = new DataUdienze();
            this.popover.close();
        }
    }

    modificaData(dataUd: DataUdienze): void {
        if (this.popover) {
            this.dataUdienza.enable = dataUd.enable;
            this.dataUdienza.dataUdienza = moment(dataUd.dataUdienza).toISOString();
            this.dataUdienza.id = dataUd.id;
        }
    }

    saveData(): void {
        if(this.dataUdienza.dataUdienza) {
            this.dataEmitter.emit(this.dataUdienza);
        }
        this.dataUdienza = new DataUdienze();
        this.popover.close();
    }
}
