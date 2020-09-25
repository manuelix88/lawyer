import {Component, OnInit} from '@angular/core';
import {CustomerService} from '../../customer-service/customer.service';
import {AnagraficaCliente} from '../model/anagrafica-cliente';
import {TreoAnimations} from '../../../../../@treo/animations';
import {NgxSpinnerService} from 'ngx-spinner';
import {ToastrService} from 'ngx-toastr';
import {ReportPatronato} from '../model/report-patronato';
import {ReportAmministrative} from '../model/report-amministrative';

@Component({
    selector: 'add-customer',
    templateUrl: './add-customer.component.html',
    styleUrls: ['./add-customer.component.scss'],
    animations   : TreoAnimations
})
export class AddCustomerComponent implements OnInit {
    message: any;
    anagrafica = new AnagraficaCliente();
    constructor(private customer: CustomerService,
                private notificationService: ToastrService ,
                private spinner: NgxSpinnerService) { }

    ngOnInit(): void {
        this.message = null;
    }

    // tslint:disable-next-line:typedef
    async sendForm($event: AnagraficaCliente) {
        await this.spinner.show();

        $event.reportAmministrative = this.checkReportAmministrativo($event.reportAmministrative);
        $event.reportPatronato = this.checkReportPatronato($event.reportPatronato);
        await this.customer.addAnagrafica($event)
            .then(value => {
                // Show the error message
                this.message = {
                    appearance: 'outline',
                    content   : 'Operazione effettuata con successo',
                    shake     : true,
                    showIcon  : false,
                    type      : 'success'
                };
                // $event = new AnagraficaCliente();
                // this.anagrafica = new AnagraficaCliente();
                this.spinner.hide();
            })
            .catch(error => {
                this.notificationService.error(  error.error.errors,'ERRORE');
                this.anagrafica = $event;
                this.spinner.hide();
            });
    }

    checkReportPatronato(rep: ReportPatronato): ReportPatronato {
        if (rep) {
            if (
                (rep.spese === undefined || rep.spese === '') && (rep.avvocatoDelegato === undefined ) &&
                (rep.status === undefined ) && (rep.patronatoProvenienza === undefined ) && (rep.giudice === undefined || rep.giudice === '') &&
                (rep.giudice === undefined || rep.giudice === '') && (rep.dateUdienze === undefined || rep.dateUdienze.length === 0 ) && (rep.tipoPratica === undefined ) &&
                (rep.tribunale === undefined) && (rep.ruoloGenerale === undefined || rep.ruoloGenerale === '') && (rep.note === undefined || rep.note === '') ) {
                rep = null;
                return rep;
            }else {
                return rep;
            }
        }
    }

    checkReportAmministrativo(rep: ReportAmministrative): ReportAmministrative {
        if (rep) {
            if((rep.documentazione === undefined || rep.documentazione === '') &&
                (rep.note === undefined || rep.note === '') && (rep.dataPagamento === undefined || rep.dataPagamento === '') &&
                (rep.ricordoCedu === undefined || rep.ricordoCedu === '') && (rep.qualifica === undefined || rep.qualifica === '') &&
                (rep.numeroFaldone === undefined)
            ) {
                rep = null;
                return rep;
            }
        }
        return rep;
    }
}
