import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ModifyCustomerComponent} from './modify-customer/modify-customer.component';
import {CustomerViewModule} from '../customer-view.module';
import {RouterModule} from '@angular/router';
import {modifyCustomerRouting} from './modify-customer.routing';
import {TreoMessageModule} from '../../../../../@treo/components/message';


@NgModule({
  declarations: [ModifyCustomerComponent],
    imports: [
        CommonModule,
        RouterModule.forChild(modifyCustomerRouting),
        CustomerViewModule,
        TreoMessageModule
    ]
})
export class ModifyCustomerModule { }
