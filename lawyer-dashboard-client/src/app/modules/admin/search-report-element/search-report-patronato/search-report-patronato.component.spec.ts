import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchReportPatronatoComponent } from './search-report-patronato.component';

describe('SearchReportPatronatoComponent', () => {
  let component: SearchReportPatronatoComponent;
  let fixture: ComponentFixture<SearchReportPatronatoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchReportPatronatoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchReportPatronatoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
