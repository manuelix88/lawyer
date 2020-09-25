import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AnagraficaCliente} from '../customer-view/model/anagrafica-cliente';
import {MatTableDataSource} from '@angular/material/table';
import {CustomerService} from '../customer-service/customer.service';
import {NgxSpinnerService} from 'ngx-spinner';
import {Avvocati} from '../customer-view/model/avvocati';
import {PatronatoProvenienza} from '../customer-view/model/patronatoProvenienza';
import {ApplicationStoreService} from '../../../core/store/application-store.service';
import {ToastrService} from 'ngx-toastr';
import {DeleteModalComponent} from '../../../shared/components/delete-modal/delete-modal.component';
import {MatDialog} from '@angular/material/dialog';

export class SearchFilter {
    nome: string;
    cognome: string;
    codiceFiscale: string;
    qualifica: string;
    faldone: number;
    documentazione: string;
    ruoloGenerale: string;
    avvocatoDelegato: number;
    patronatoProvenienza: number;
}


@Component({
    selector: 'search-report-element',
    templateUrl: './search-report-element.component.html',
    styleUrls: ['./search-report-element.component.scss'],
    encapsulation  : ViewEncapsulation.None,
})
export class SearchReportElementComponent implements OnInit {
    searchFiler: SearchFilter;
    displayedColumns: string[] = ['name', 'surname', 'cf','qualifica', 'documentazione', 'details'];
    dataSource: MatTableDataSource<AnagraficaCliente>;
    customers: Array<AnagraficaCliente> = [];
    length = 0;
    pageSize = 10;
    pageIndex = 0;
    pageSizeOption: number[] = [5, 10, 25, 100];
    data: any;
    constructor(private customerService: CustomerService,  public dialog: MatDialog,
                private notificationService: ToastrService , private spinner: NgxSpinnerService, public store: ApplicationStoreService) { }

    ngOnInit(): void {
        this.searchFiler = new SearchFilter();
        this.searchFiler.avvocatoDelegato = this.store.baseData.avvocati[0].id;
        this.searchFiler.patronatoProvenienza = this.store.baseData.patronati[0].id;
    }

    openDialog(uuid): void {
        const dialogRef = this.dialog.open(DeleteModalComponent, {
            width: '300px',
            data: {uuid:uuid}
        });

        dialogRef.afterClosed().subscribe(result => {
            this.spinner.show()
            this.customerService.deleteAnagraficaById(result).then(data => {
                this.dataSource = new MatTableDataSource();
                this.notificationService.success('Elemento eliminato con successo','SUCCESSO');
                this.spinner.hide();

            }).catch(error => {
                this.spinner.hide();
                this.notificationService.error('Elemento non eliminato con successo','ERRORE');
            });
        });
    }

    searchData(input: SearchFilter): void {
        this.pageSize = 10;
        this.pageIndex = 0;
        this.length = 0;
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
            qualifica: input === undefined ? null : input.qualifica,
            faldone: input === undefined ? null : input.faldone,
            documentazione: input === undefined ? null : input.documentazione,
            // ruoloGenerale: input === undefined ? null : input.ruoloGenerale,
            // avvocatoDelegato: input === undefined ? null : input.avvocatoDelegato,
            // patronatoProvenienza: input === undefined ? null : input.patronatoProvenienza,
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
                // );vv
                this.notificationService.error(  error.error.errors,'ERRORE');
                this.spinner.hide();
            });
    }
}
