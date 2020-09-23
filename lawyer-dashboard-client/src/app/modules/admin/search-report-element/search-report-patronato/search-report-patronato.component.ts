import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {AnagraficaCliente} from '../../customer-view/model/anagrafica-cliente';
import {CustomerService} from '../../customer-service/customer.service';
import {NgxSpinnerService} from 'ngx-spinner';
import {ApplicationStoreService} from '../../../../core/store/application-store.service';
import {Avvocati} from '../../customer-view/model/avvocati';
import {PatronatoProvenienza} from '../../customer-view/model/patronatoProvenienza';
import {SearchFilter} from '../search-report-element.component';

@Component({
  selector: 'app-search-report-patronato',
  templateUrl: './search-report-patronato.component.html',
  styleUrls: ['./search-report-patronato.component.scss']
})
export class SearchReportPatronatoComponent implements OnInit {
    searchFiler = new SearchFilter();
    displayedColumns: string[] = ['name', 'surname', 'cf','ruoloGenerale', 'patronato', 'avvocato'];
    dataSource: MatTableDataSource<AnagraficaCliente>;
    customers: Array<AnagraficaCliente> = [];
    length = 0;
    pageSize = 10;
    pageIndex = 0;
    pageSizeOption: number[] = [5, 10, 25, 100];
    data: any;
    constructor(private customerService: CustomerService, private spinner: NgxSpinnerService, public store: ApplicationStoreService) { }

    ngOnInit(): void {
    }

    searchData(input: SearchFilter): void {
        this.pageSize = 10;
        this.pageIndex = 0;
        this.getPageAnagrafica(input);
    }
    compareAvvocati(o1: Avvocati, o2: Avvocati): boolean {
        return o1?.avvocatoDelegato === o2?.avvocatoDelegato && o1?.id === o2?.id;
    }
    comparePatronaTiProvenienza(o1: PatronatoProvenienza, o2: PatronatoProvenienza): boolean {
        return o1?.patronato === o2?.patronato && o1?.id === o2?.id;
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
            // qualifica: input === undefined ? null : input.qualifica,
            // faldone: input === undefined ? null : input.faldone,
            // documentazione: input === undefined ? null : input.documentazione,
            // ruoloGenerale: input === undefined ? null : input.ruoloGenerale,
            // avvocatoDelegato: input === undefined ? null : input.avvocatoDelegato,
            // patronatoProvenienza: input === undefined ? null : input.patronatoProvenienza,
        };
        this.customerService.getPatronatoReportAll(req)
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
