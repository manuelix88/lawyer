import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CustomerInformazionComponent} from './customer-informazion/customer-informazion.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatTabsModule} from '@angular/material/tabs';



@NgModule({
    declarations: [CustomerInformazionComponent],
    exports: [
        CustomerInformazionComponent
    ],
    imports: [
        CommonModule,
        MatFormFieldModule,
        MatSelectModule,
        MatIconModule,
        MatDividerModule,
        MatInputModule,
        MatButtonModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatTabsModule
    ],
    providers: [
        MatDatepickerModule,
    ],
})
export class CustomerViewModule { }
