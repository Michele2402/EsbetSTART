import {Component, OnDestroy, OnInit, signal} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {RegistrationService} from "../../core/services/registration.service";
import {RegisterRequest} from "../../model/request/register-request";
import {SnackbarService} from "../../core/services/snackbar.service";
import {catchError, Subject, takeUntil} from "rxjs";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrl: './registration-page.component.css'
})
export class RegistrationPageComponent implements OnDestroy {

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

  private _destroy$ = new Subject<void>();

  constructor(
    private _fb: FormBuilder,
    private registrationService : RegistrationService,
    private snackbarService: SnackbarService
  ) {
  }

  onSubmit(): void {

    const formData: RegisterRequest = this.precisionForm.value as RegisterRequest;

    if (this.precisionForm.valid) {
      this.registrationService.signUp(formData)
        .pipe(
          takeUntil(this._destroy$),
          catchError((error) => {
            this.snackbarService.showSnackbarMessage(
              error.error.errors, 'error-snackbar'
            )
            return [];
          })
        )
        .subscribe(() =>  {
        this.snackbarService.showSnackbarMessage(
          'Registration successful, you can now login', 'success-snackbar'
        )

        this.precisionForm.reset();
          Object.keys(this.precisionForm.controls).forEach(key => {
            const control = this.precisionForm.get(key);
            control?.setErrors(null);
            control?.markAsPristine();
            control?.markAsUntouched();
          });
      })
    }
  }

  hide = signal(true);

  clickEvent(event?: MouseEvent) {
    this.hide.set(!this.hide());
    event?.stopPropagation();
  }

  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }
}
