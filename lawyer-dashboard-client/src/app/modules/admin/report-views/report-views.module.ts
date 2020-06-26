import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReportComponent} from './report/report.component';
import {RouterModule} from '@angular/router';
import {importReportRouting} from './report-views.routing';
import {MatIconModule} from '@angular/material/icon';
import {TreoCardModule} from '../../../../@treo/components/card';
import {MatButtonModule} from '@angular/material/button';


@NgModule({
    declarations: [ReportComponent],
    imports: [
        CommonModule,
        RouterModule.forChild(importReportRouting),
        MatIconModule,
        TreoCardModule,
        MatButtonModule
    ]
})
export class ReportViewsModule { }
