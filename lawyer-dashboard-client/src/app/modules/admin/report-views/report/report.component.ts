import {Component, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss'],
    encapsulation  : ViewEncapsulation.None,
})
export class ReportComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
