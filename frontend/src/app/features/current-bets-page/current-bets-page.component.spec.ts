import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentBetsPageComponent } from './current-bets-page.component';

describe('CurrentBetsPageComponent', () => {
  let component: CurrentBetsPageComponent;
  let fixture: ComponentFixture<CurrentBetsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CurrentBetsPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CurrentBetsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
