import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WithdrawalsPageComponent } from './withdrawals-page.component';

describe('WithdrawalsPageComponent', () => {
  let component: WithdrawalsPageComponent;
  let fixture: ComponentFixture<WithdrawalsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WithdrawalsPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WithdrawalsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
