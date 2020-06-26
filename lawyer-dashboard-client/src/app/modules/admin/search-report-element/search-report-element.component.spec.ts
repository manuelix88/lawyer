import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchReportElementComponent } from './search-report-element.component';

describe('SearchReportElementComponent', () => {
  let component: SearchReportElementComponent;
  let fixture: ComponentFixture<SearchReportElementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchReportElementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchReportElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
