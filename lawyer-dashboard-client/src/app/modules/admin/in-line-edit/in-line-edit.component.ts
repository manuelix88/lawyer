import { Component, Input, Optional, Host } from '@angular/core';
import { SatPopover } from '@ncstate/sat-popover';
import {filter} from 'rxjs/operators';

@Component({
    selector: 'inline-edit',
    styleUrls: ['in-line-edit.component.scss'],
    template: `
    <form (ngSubmit)="onSubmit()">
      <div class="mat-subheading-2">Add a comment</div>

      <mat-form-field>
        <input matInput maxLength="140" name="comment" [(ngModel)]="comment">
        <mat-hint align="end">/140</mat-hint>
      </mat-form-field>

      <div class="actions">
        <button mat-button type="button" color="primary" (click)="onCancel()">Esci</button>
        <button mat-button type="submit" color="primary">Salva</button>
      </div>
    </form>
  `
})
export class InlineEditComponent {

    /** Overrides the comment and provides a reset value when changes are cancelled. */
    @Input()
    get value(): string { return this._value; }
    set value(x: string) {
        this.comment = this._value = x;
    }
    private _value = '';

    /** Form model for the input. */
    comment = '';

    constructor(@Optional() @Host() public popover: SatPopover) { }

    ngOnInit(): void {
        // subscribe to cancellations and reset form value
        if (this.popover) {
            this.popover.closed.pipe(filter(val => val == null))
                .subscribe(() => this.comment = this.value || '');
        }
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
