import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EsportsPageComponent } from './esports-page.component';

describe('EsportsPageComponent', () => {
  let component: EsportsPageComponent;
  let fixture: ComponentFixture<EsportsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EsportsPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EsportsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
