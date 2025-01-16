import {Component} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {signal} from "@angular/core";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrl: './registration-page.component.css'
})
export class RegistrationPageComponent {

  precisionForm = this._fb.group({
    name: ['', Validators.required],
    surname: ['', Validators.required],
    telephone: ['', Validators.required],
    date: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
    nationality: ['', Validators.required],
  });

  constructor(
    private _fb: FormBuilder
  ) {
  }

  onSubmit(): void {
    if (this.precisionForm.valid) {
      console.log('Form submitted:', this.precisionForm.value);
    } else {
      console.log('Form is invalid');
    }
  }


  hide = signal(true);
  clickEvent(event?: MouseEvent) {
    this.hide.set(!this.hide());
    event?.stopPropagation();
  }

}
