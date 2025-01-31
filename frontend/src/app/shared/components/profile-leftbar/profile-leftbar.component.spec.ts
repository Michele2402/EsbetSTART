import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileLeftbarComponent } from './profile-leftbar.component';

describe('ProfileLeftbarComponent', () => {
  let component: ProfileLeftbarComponent;
  let fixture: ComponentFixture<ProfileLeftbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileLeftbarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileLeftbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
