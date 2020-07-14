import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../../customer-service/customer.service';
import {AnagraficaCliente} from '../model/anagrafica-cliente';
import {TreoAnimations} from '../../../../../@treo/animations';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
    selector: 'add-customer',
    templateUrl: './add-customer.component.html',
    styleUrls: ['./add-customer.component.scss'],
    animations   : TreoAnimations
})
export class AddCustomerComponent implements OnInit {
    message: any;
    anagrafica = new AnagraficaCliente();
    constructor(private customer: CustomerService, private spinner: NgxSpinnerService) { }

    ngOnInit(): void {
        this.message = null;
    }

    // tslint:disable-next-line:typedef
    async sendForm($event: AnagraficaCliente) {
        await this.spinner.show();

        // $event.reportPatronato = null;
        // $event.reportAmministrative = null;
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

               this.spinner.hide();
            })
            .catch(error => {
                this.message = {
                    appearance: 'outline',
                    content   : error.error.message,
                    shake     : true,
                    showIcon  : false,
                    type      : 'error'
                };

                this.anagrafica = $event;
                this.spinner.hide();
            })
    }
}
