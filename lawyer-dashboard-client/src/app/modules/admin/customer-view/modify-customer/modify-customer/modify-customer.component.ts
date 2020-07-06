import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../../../customer-service/customer.service';
import {AnagraficaCliente} from '../../model/anagrafica-cliente';
import {TreoAnimations} from '../../../../../../@treo/animations';
import * as _ from 'lodash';

@Component({
    selector: 'modify-customer',
    templateUrl: './modify-customer.component.html',
    styleUrls: ['./modify-customer.component.scss'],
    animations   : TreoAnimations
})
export class ModifyCustomerComponent implements OnInit {

    anagrafica = new AnagraficaCliente();
    message: any;
    constructor(private customerService: CustomerService) { }

    // tslint:disable-next-line:typedef
    async ngOnInit() {
        this.message = null;
        const req = {
            anagraficaId: 8
        }
        await this.customerService.getAnagraficaById(req)
            .then( value => {
                this.anagrafica = value;
            });
    }

    async sendForm($event: AnagraficaCliente) {
        const clonesValue = _.cloneDeep($event);
        await this.customerService.updateAnagrafica($event)
            .then(value => {
                this.message = {
                    appearance: 'outline',
                    content   : 'Operazione effettuata con successo',
                    shake     : true,
                    showIcon  : false,
                    type      : 'success'
                };
                this.anagrafica = clonesValue;
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
            })

    }
}
