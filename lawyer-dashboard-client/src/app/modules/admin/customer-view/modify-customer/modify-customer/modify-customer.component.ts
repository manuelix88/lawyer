import {Component, Host, Input, OnInit, Optional} from '@angular/core';
import {CustomerService} from '../../../customer-service/customer.service';
import {AnagraficaCliente} from '../../model/anagrafica-cliente';
import {TreoAnimations} from '../../../../../../@treo/animations';
import * as _ from 'lodash';
import {ActivatedRoute} from '@angular/router';
import {ReportPatronato} from '../../model/report-patronato';
import {ReportAmministrative} from '../../model/report-amministrative';
import moment from 'moment';
import {SatPopover} from '@ncstate/sat-popover';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
    selector: 'modify-customer',
    templateUrl: './modify-customer.component.html',
    styleUrls: ['./modify-customer.component.scss'],
    animations   : TreoAnimations
})
export class ModifyCustomerComponent implements OnInit {


    /** Overrides the comment and provides a reset value when changes are cancelled. */

    anagrafica = new AnagraficaCliente();
    message: any;
    constructor(private customerService: CustomerService,private activatedRouter: ActivatedRoute,
                private spinner: NgxSpinnerService) { }

    // tslint:disable-next-line:typedef
    async ngOnInit() {
        this.message = null;
        if (this.activatedRouter.snapshot.params.id) {
            await this.spinner.show();
            const req = {
                anagraficaId: this.activatedRouter.snapshot.params.id
            }
            await this.customerService.getAnagraficaById(req)
                .then( value => {
                    const data = moment(value.dataNascita).toISOString();
                    console.log(data);
                    this.anagrafica = value;
                    this.anagrafica.dataNascita = data;
                    this.spinner.hide();
                })
                .catch(error => {
                    this.spinner.hide();
                });
        }
    }

    async sendForm($event: AnagraficaCliente) {

        $event.reportAmministrative = this.checkReportAmministrativo($event.reportAmministrative);
        $event.reportPatronato = this.checkReportPatronato($event.reportPatronato);
        const clonesValue = _.cloneDeep($event);
        await this.spinner.show();
        await this.customerService.updateAnagrafica($event)
            .then(value => {
                this.message = {
                    appearance: 'outline',
                    content   : 'Operazione effettuata con successo',
                    shake     : true,
                    showIcon  : false,
                    type      : 'success'
                };
                // this.anagrafica = new AnagraficaCliente();
                // this.anagrafica = value;
                // this.anagrafica.
                // this.spinner.hide();
                // window.location.reload();
            })
            .catch(error=> {
                this.message = {
                    appearance: 'outline',
                    content   : error.message,
                    shake     : true,
                    showIcon  : false,
                    type      : 'error'
                };
                this.anagrafica = clonesValue;
                this.spinner.hide();
            });
        // await this.spinner.show();
        const req = {
            anagraficaId: $event.idAnagrafica
        }
        await this.customerService.getAnagraficaById(req)
            .then( value => {
                const data = moment(value.dataNascita).toISOString();
                console.log(data);
                this.anagrafica = value;
                this.anagrafica.dataNascita = data;
                this.spinner.hide();
            })
            .catch(error => {
                this.spinner.hide();
            });
    }

    checkReportPatronato(rep: ReportPatronato): ReportPatronato {
        if (rep) {
            if (rep.idRepPatronato >= 1) {
                return rep;
            } else if (rep.spese && rep.note && rep.avvocatoDelegato && rep.status && rep.patronatoProvenienza
                && rep.giudice && rep.dateUdienze && rep.tipoPratica && rep.convenzione
                && rep.codice && rep.tribunale && rep.ruoloGenerale) {
                rep = null;
                return rep;
            }else {
                return rep;
            }
        }
    }

    checkReportAmministrativo(rep: ReportAmministrative): ReportAmministrative {
        if (rep) {
            if (rep.idRepAmministrative >= 1) {
                return rep;
            } else if (rep.documentazione && rep.note && rep.altro &&
                rep.dataPagamento && rep.ricordoCedu
                && rep.qualifica && rep.numeroFaldone) {
                rep = null;
                return rep;

            } else {
                return rep;
            }
        }
    }

    async getAnag(req) {

    }
}
