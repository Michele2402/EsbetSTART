import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerServiceDialogComponent } from './customer-service-dialog.component';

describe('CustomerServiceDialogComponent', () => {
  let component: CustomerServiceDialogComponent;
  let fixture: ComponentFixture<CustomerServiceDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerServiceDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerServiceDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
