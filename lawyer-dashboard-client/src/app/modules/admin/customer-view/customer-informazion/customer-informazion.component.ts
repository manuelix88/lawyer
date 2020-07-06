import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AnagraficaCliente} from '../model/anagrafica-cliente';
import {ReportAmministrative} from '../model/report-amministrative';
import {ReportPatronato} from '../model/report-patronato';

@Component({
    selector: 'customer-information',
    templateUrl: './customer-informazion.component.html',
    styleUrls: ['./customer-informazion.component.scss']
})
export class CustomerInformazionComponent implements OnInit {

    @Input() customer = new AnagraficaCliente();
    @Output() sendForm = new EventEmitter<AnagraficaCliente>();
    constructor() { }

    ngOnInit(): void {
        if(this.customer.reportAmministrative === undefined) {
            this.customer.reportAmministrative = new ReportAmministrative();
        }
        if(this.customer.reportPatronato === undefined) {
            this.customer.reportPatronato = new ReportPatronato();
        }
    }

    sendCustomer(form: AnagraficaCliente): void {
        this.sendForm.emit(form);
    }
}
