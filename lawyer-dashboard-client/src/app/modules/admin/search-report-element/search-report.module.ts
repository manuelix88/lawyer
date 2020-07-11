import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SearchReportElementComponent} from './search-report-element.component';
import {RouterModule} from '@angular/router';
import {searchReportRouting} from './search-report.routing';
import {MatIconModule} from '@angular/material/icon';
import {TreoCardModule} from '../../../../@treo/components/card';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDividerModule} from '@angular/material/divider';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {FormsModule} from '@angular/forms';


@NgModule({
    declarations: [SearchReportElementComponent],
    imports: [
        CommonModule,
        RouterModule.forChild(searchReportRouting),
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
export class SearchReportModule { }
