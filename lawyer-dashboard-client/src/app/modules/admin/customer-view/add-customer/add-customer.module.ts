import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AddCustomerComponent} from './add-customer.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {RouterModule} from '@angular/router';
import {addCustomerRouting} from './add-customer.routing';
import {MatButtonModule} from '@angular/material/button';
import {CustomerViewModule} from '../customer-view.module';


@NgModule({
  declarations: [AddCustomerComponent],
    imports: [
        CommonModule,
        RouterModule.forChild(addCustomerRouting),
        MatFormFieldModule,
        MatIconModule,
        MatDividerModule,
        MatInputModule,
        MatSelectModule,
        MatButtonModule,
        CustomerViewModule
    ]
})
export class AddCustomerModule { }
