import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerInformazionComponent } from './customer-informazion.component';

describe('CustomerInformazionComponent', () => {
  let component: CustomerInformazionComponent;
  let fixture: ComponentFixture<CustomerInformazionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerInformazionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerInformazionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
