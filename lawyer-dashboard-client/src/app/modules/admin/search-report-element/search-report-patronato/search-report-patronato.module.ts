import {NgModule} from '@angular/core';
import {SearchReportPatronatoComponent} from './search-report-patronato.component';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {TreoCardModule} from '../../../../../@treo/components/card';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {FormsModule} from '@angular/forms';
import {searchReportPatronatoRouting} from './search-report-patronato.routing';

@NgModule({
    declarations: [SearchReportPatronatoComponent],
    imports: [
        CommonModule,
        RouterModule.forChild(searchReportPatronatoRouting),
        MatIconModule,
        TreoCardModule,
        MatButtonModule,
        MatDividerModule,
        MatSelectModule,
        MatInputModule,
        MatFormFieldModule,
        MatTableModule,
        MatPaginatorModule,
        FormsModule
    ]
})
export class SearchReportPatronatoModule {

}
