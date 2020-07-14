import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AnagraficaCliente} from '../customer-view/model/anagrafica-cliente';
import {MatTableDataSource} from '@angular/material/table';
import {CustomerService} from '../customer-service/customer.service';
import {NgxSpinnerService} from 'ngx-spinner';
export class SearchFilter {
    nome: string;
    cognome: number;
    codiceFiscale: string;
    qualifica: string;
    faldone: number;
    documentazione: string;
}


@Component({
    selector: 'search-report-element',
    templateUrl: './search-report-element.component.html',
    styleUrls: ['./search-report-element.component.scss'],
    encapsulation  : ViewEncapsulation.None,
})
export class SearchReportElementComponent implements OnInit {
    searchFiler = new SearchFilter();
    displayedColumns: string[] = ['name', 'surname', 'cf','qualifica', 'documentazione', 'details'];
    dataSource: MatTableDataSource<AnagraficaCliente>;
    customers: Array<AnagraficaCliente> = [];
    length = 0;
    pageSize = 10;
    pageIndex = 0;
    pageSizeOption: number[] = [5, 10, 25, 100];
    data: any;
    constructor(private customerService: CustomerService, private spinner: NgxSpinnerService) { }

    ngOnInit(): void {
    }

    searchData(input: SearchFilter): void {
     this.getPageAnagrafica(input);
    }

    pageChanged(event, searchFiler: SearchFilter): void {
        this.pageIndex = event.pageIndex;
        this.pageSize = event.pageSize;
        this.getPageAnagrafica(searchFiler);
    }

    getPageAnagrafica(input?: SearchFilter): void {
        this.spinner.show();
        const req = {
            page: this.pageIndex,
            limit: this.pageSize,
            nome: input === undefined ? null : input.nome,
            cognome:  input === undefined ? null : input.cognome,
            codiceFiscale: input === undefined ? null : input.codiceFiscale,
            qualifica: input === undefined ? null : input.qualifica,
            faldone: input === undefined ? null : input.faldone,
            documentazione: input === undefined ? null : input.documentazione,
        };
        this.customerService.getAnagraficaAll(req)
            .then( value => {
                this.data = value;

                this.customers = this.data.content;
                this.dataSource = new MatTableDataSource(this.customers);
                this.length = this.data.totalElements;
                this.spinner.hide();
            })
            .catch(error => {
                // this.notification.error(
                //     error.message
                // );
                this.spinner.hide();
            });
    }
}
