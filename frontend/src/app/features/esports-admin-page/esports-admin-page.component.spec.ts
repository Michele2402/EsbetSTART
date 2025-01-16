import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EsportsAdminPageComponent } from './esports-admin-page.component';

describe('EsportsAdminPageComponent', () => {
  let component: EsportsAdminPageComponent;
  let fixture: ComponentFixture<EsportsAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EsportsAdminPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EsportsAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
