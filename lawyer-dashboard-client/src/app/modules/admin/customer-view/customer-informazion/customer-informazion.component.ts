import {Component, EventEmitter, Host, Input, OnInit, Optional, Output} from '@angular/core';
import {AnagraficaCliente} from '../model/anagrafica-cliente';
import {ReportAmministrative} from '../model/report-amministrative';
import {Codice, DataUdienze, ReportPatronato, Status, Tribunale} from '../model/report-patronato';
import {ApplicationStoreService} from '../../../../core/store/application-store.service';
import {NgForm} from '@angular/forms';
import {SatPopover} from '@ncstate/sat-popover';
import {filter} from 'rxjs/operators';
import {TreoAnimations} from '../../../../../@treo/animations';
import {Avvocati} from '../model/avvocati';
import {PatronatoProvenienza} from '../model/patronatoProvenienza';
import {TipoPratiche} from '../model/tipo-pratiche';

@Component({
    selector: 'customer-information',
    templateUrl: './customer-informazion.component.html',
    styleUrls: ['./customer-informazion.component.scss'],
    animations   : TreoAnimations
})
export class CustomerInformazionComponent implements OnInit {


    message: any;
    @Input() showReportPatronato;
    @Input() showReportInfo;
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

    constructor(@Optional() @Host() public popover: SatPopover,public store: ApplicationStoreService) {
        this.showReportPatronato = true;
        this.showReportInfo = true;
    }

    ngOnInit(): void {
        this.message = null;
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

    compareAvvocati(o1: Avvocati, o2: Avvocati): boolean {
        return o1?.avvocatoDelegato === o2?.avvocatoDelegato && o1?.id === o2?.id;
    }
    comparePatronaTiProvenienza(o1: PatronatoProvenienza, o2: PatronatoProvenienza): boolean {
        return o1?.patronato === o2?.patronato && o1?.id === o2?.id;
    }

    compareTipoPratiche(o1: TipoPratiche, o2: TipoPratiche): boolean {
        return o1?.tipoPratica === o2?.tipoPratica && o1?.id === o2?.id;
    }
    compareCodice(o1: Codice, o2: Codice): boolean {
        return o1?.code === o2?.code && o1?.id === o2?.id;
    }

    sendCustomer(anagrafica: AnagraficaCliente, form: NgForm): void {
        if(form.invalid) {
            this.message = {
                appearance: 'outline',
                content   : 'I dati inseriti non sono validi',
                shake     : true,
                showIcon  : false,
                type      : 'error'
            };
            return;
        }

        this.sendForm.emit(anagrafica);

        if(anagrafica.uuid === undefined) {
            this.reset()
        }
    }

    reset(): void {
        this.customer = new AnagraficaCliente();
        this.customer.reportAmministrative = new ReportAmministrative();
        this.customer.reportPatronato = new ReportPatronato();

    }
    saveDate(dataUdienza: DataUdienze) : void {
        if(!dataUdienza.id) {
            dataUdienza.enable = false;

            this.customer.reportPatronato.dateUdienze.push(dataUdienza);
        } else {
            if (!this.customer.reportPatronato) {
                this.customer.reportPatronato = new ReportPatronato();
            }
            this.customer.reportPatronato.dateUdienze = this.customer.reportPatronato.dateUdienze.filter(value1 => value1.id !== dataUdienza.id);
            this.customer.reportPatronato.dateUdienze.push(dataUdienza);


        }
    }

    // dataChange(form: NgForm): void {
    //     console.log(moment(form.get()));
    // }
}
