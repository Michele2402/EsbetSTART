import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RechargePageComponent } from './recharge-page.component';

describe('RechargePageComponent', () => {
  let component: RechargePageComponent;
  let fixture: ComponentFixture<RechargePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RechargePageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RechargePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
