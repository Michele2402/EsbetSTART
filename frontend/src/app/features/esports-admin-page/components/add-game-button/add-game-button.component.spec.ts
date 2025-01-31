import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGameButtonComponent } from './add-game-button.component';

describe('AddGameButtonComponent', () => {
  let component: AddGameButtonComponent;
  let fixture: ComponentFixture<AddGameButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddGameButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddGameButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
