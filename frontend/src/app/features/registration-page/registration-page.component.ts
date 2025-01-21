import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {signal} from "@angular/core";
import {RegistrationService} from "../../core/services/registration.service";
import {RegisterRequest} from "../../model/request/register-request";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrl: './registration-page.component.css'
})
export class RegistrationPageComponent implements OnInit, OnDestroy {

    precisionForm = this._fb.group({
    name: ['', [Validators.required, Validators.maxLength(30)]], // Solo validatori sincroni nel secondo parametro
    surname: ['', [Validators.required, Validators.maxLength(30)]],
    email: ['', [Validators.required, Validators.email, Validators.maxLength(30)]],
    username: ['', [Validators.required, Validators.maxLength(30)]],
    password: [
      '',
      [
        Validators.required,
        Validators.maxLength(30),
        Validators.minLength(8),
        Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/),
      ],
    ],
  });

  constructor(
    private _fb: FormBuilder,
    private registrationService : RegistrationService,
  ) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.precisionForm.valid) {

      const formData: RegisterRequest = this.precisionForm.value as RegisterRequest;

      this.registrationService.signUp(formData).subscribe(data =>  {
        console.log(data);
      })
      this.precisionForm.reset()

    } else {
      console.log('Form is invalid');
    }
  }

  hide = signal(true);

  clickEvent(event?: MouseEvent) {
    this.hide.set(!this.hide());
    event?.stopPropagation();
  }

  ngOnDestroy(): void {
  }
}
