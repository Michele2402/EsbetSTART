import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepositsPageComponent } from './deposits-page.component';

describe('DepositsPageComponent', () => {
  let component: DepositsPageComponent;
  let fixture: ComponentFixture<DepositsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DepositsPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepositsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
