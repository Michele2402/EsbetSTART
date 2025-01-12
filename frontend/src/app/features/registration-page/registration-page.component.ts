import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrl: './registration-page.component.css'
})
export class RegistrationPageComponent {

  precisionForm = this._fb.group({
    precision: [0, [Validators.required, Validators.min(30), Validators.max(500)]],
    name: ['', Validators.required]
  });

  constructor(
    private _fb: FormBuilder
   /* private _authService: AuthService */
  ) {

  }

  onSubmit() {
    console.log(this.precisionForm);

    if (this.precisionForm.valid) {
    }
  }


}
