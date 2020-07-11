import {Component, EventEmitter, Host, Input, OnInit, Optional, Output} from '@angular/core';
import {AnagraficaCliente} from '../model/anagrafica-cliente';
import {ReportAmministrative} from '../model/report-amministrative';
import {Codice, ReportPatronato, Status, Tribunale} from '../model/report-patronato';
import {ApplicationStoreService} from '../../../../core/store/application-store.service';
import {NgForm} from '@angular/forms';
import moment from 'moment';
import {SatPopover} from '@ncstate/sat-popover';
import {filter} from 'rxjs/operators';

@Component({
    selector: 'customer-information',
    templateUrl: './customer-informazion.component.html',
    styleUrls: ['./customer-informazion.component.scss']
})
export class CustomerInformazionComponent implements OnInit {

    @Input() customer = new AnagraficaCliente();
    @Output() sendForm = new EventEmitter<AnagraficaCliente>();

    @Input()
    get value(): string { return this._value; }
    set value(x: string) {
        this.comment = this._value = x;
    }
    private _value = '';

    /** Form model for the input. */
    comment = '';

    constructor(@Optional() @Host() public popover: SatPopover,public store: ApplicationStoreService) { }

    ngOnInit(): void {
        if(this.customer.reportAmministrative === undefined) {
            this.customer.reportAmministrative = new ReportAmministrative();
        }
        if(this.customer.reportPatronato === undefined) {
            this.customer.reportPatronato = new ReportPatronato();
        }
        if (this.popover) {
            this.popover.closed.pipe(filter(val => val == null))
                .subscribe(() => this.comment = this.value || '');
        }
    }
    compareTribunale(o1: Tribunale, o2: Tribunale): boolean {
        return o1?.tribunali === o2?.tribunali && o1?.id === o2?.id;
    }

    compareStatus(o1: Status, o2: Status): boolean {
        return o1?.status === o2?.status && o1?.id === o2?.id;
    }

    compareCodice(o1: Codice, o2: Codice): boolean {
        return o1?.code === o2?.code && o1?.id === o2?.id;
    }

    sendCustomer(anagrafica: AnagraficaCliente, form: NgForm): void {
        if(form.invalid) {
            alert('InvalidForm');
            return;
        }
        this.sendForm.emit(anagrafica);
    }

    reset(): void {
        this.customer = new AnagraficaCliente();
        this.customer.reportAmministrative = new ReportAmministrative();
        this.customer.reportPatronato = new ReportPatronato();

    }

    // dataChange(form: NgForm): void {
    //     console.log(moment(form.get()));
    // }
}
