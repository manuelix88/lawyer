import { Component, Input, Optional, Host } from '@angular/core';
import { SatPopover } from '@ncstate/sat-popover';
import {filter} from 'rxjs/operators';
import {DataUdienze} from '../customer-view/model/report-patronato';

@Component({
    selector: 'inline-edit',
    styleUrls: ['in-line-edit.component.scss'],
    template: `
    <form (ngSubmit)="onSubmit()">
      <div class="mat-subheading-2">Aggiungi Una Data Udienza</div>

        <mat-selection-list #shoes>
            <mat-list-option *ngFor="let shoe of date" [selected]="shoe.enable" (click)="shoe.enable = !shoe.enable">
                <div class="flex justify-between flex-auto">
                    <div> {{shoe.dataUdienza | date: 'dd/MM/yyyy'}}</div>
                    <button mat-icon-button >
                        <mat-icon>edit</mat-icon>
                    </button>
                </div>


            </mat-list-option>

        </mat-selection-list>

      <div class="actions">
        <button mat-button type="button" color="primary" (click)="onCancel()">Esci</button>
        <button mat-button type="submit" color="primary">Salva</button>
      </div>
    </form>
  `
})
export class InlineEditComponent {

    /** Overrides the comment and provides a reset value when changes are cancelled. */
    @Input('dataUltimaUdienza') date: DataUdienze[];
    private _value = '';

    /** Form model for the input. */
    comment = '';

    constructor(@Optional() @Host() public popover: SatPopover) { }

    ngOnInit(): void {
        console.log(JSON.stringify(this.date));
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
            this.popover.close();
        }
    }
}
