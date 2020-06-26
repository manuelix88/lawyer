import {Component, OnInit, ViewEncapsulation} from '@angular/core';
export interface TableResultData {
    name: string;
    position: number;
    surname: string;
    cf: string;
    qualifica: string;
}

const ELEMENT_DATA: TableResultData[] = [
    {position: 1, name: 'Fabrizio', surname: 'Vita', cf: 'H', qualifica: 'Pompiere'},
    {position: 2, name: 'Emanuele', surname: 'Aprea', cf: 'He', qualifica: 'Sviluppatore'},
    {position: 3, name: 'Salvatore', surname: 'Arca', cf: 'Li', qualifica: 'Pensionato'},
    {position: 4, name: 'Gianluca', surname: 'Tello', cf: 'Be', qualifica: 'Disoccupato'},
    {position: 5, name: 'Alex', surname: 'Baroni', cf: 'B', qualifica: 'Cantante'}
];
@Component({
    selector: 'search-report-element',
    templateUrl: './search-report-element.component.html',
    styleUrls: ['./search-report-element.component.scss'],
    encapsulation  : ViewEncapsulation.None,
})
export class SearchReportElementComponent implements OnInit {
    displayedColumns: string[] = ['position', 'name', 'surname', 'cf','qualifica', 'details'];
    dataSource = ELEMENT_DATA;
    constructor() { }

    ngOnInit(): void {
    }

    test(): void {
        alert('asd')
    }
}
