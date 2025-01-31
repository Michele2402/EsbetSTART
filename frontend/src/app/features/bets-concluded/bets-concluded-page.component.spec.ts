import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BetsConcludedPageComponent } from './bets-concluded-page.component';

describe('BetsConcludedPageComponent', () => {
  let component: BetsConcludedPageComponent;
  let fixture: ComponentFixture<BetsConcludedPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BetsConcludedPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BetsConcludedPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
