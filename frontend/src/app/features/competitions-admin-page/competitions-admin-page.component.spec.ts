import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionsAdminPageComponent } from './competitions-admin-page.component';

describe('CompetitionsAdminPageComponent', () => {
  let component: CompetitionsAdminPageComponent;
  let fixture: ComponentFixture<CompetitionsAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompetitionsAdminPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompetitionsAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
