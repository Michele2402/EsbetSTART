import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventsAdminPageComponent } from './events-admin-page.component';

describe('EventsAdminPageComponent', () => {
  let component: EventsAdminPageComponent;
  let fixture: ComponentFixture<EventsAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EventsAdminPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventsAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
