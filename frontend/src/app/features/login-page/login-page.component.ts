import {Component, OnDestroy, signal} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {LoginRequest} from "../../model/request/login-request";
import {LoginService} from "../../core/services/login.service";
import {catchError, Subject, takeUntil, tap} from "rxjs";
import {SnackbarService} from "../../core/services/snackbar.service";
import {LoginResponse} from "../../model/response/login-response";
import {RequestStatus} from "../../model/enum/request-status";
import {Router} from "@angular/router";
import {environmentPaths} from "../../environments/environment";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent implements OnDestroy {

  loginForm = this._fb.group({
    email: ['', [Validators.required, Validators.email, Validators.maxLength(30)]],
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

  requestStatus: RequestStatus = RequestStatus.NOT_SENT;

  private destroy$ = new Subject<void>();

  constructor(
    private _fb: FormBuilder,
    private loginService: LoginService,
    private snackBarService: SnackbarService,
    private router: Router
  ) {
  }

  hide = signal(true);

  clickEvent(event?: MouseEvent) {
    event?.preventDefault()
    this.hide.set(!this.hide());
    event?.stopPropagation();
  }

  onSubmit(): void {
    if (this.loginForm.valid) {

      const loginRequest: LoginRequest = this.loginForm.value as LoginRequest;

      this.requestStatus = RequestStatus.LOADING

      this.loginService.login(loginRequest)
        .pipe(
          takeUntil(this.destroy$),
          catchError((error) => {

            let errorMessage: string = 'Authentication failed'
            this.snackBarService.showSnackbarMessage(
              errorMessage, 'error-snackbar'
            )

            this.requestStatus = RequestStatus.ERROR
            return []
          })
        )
        .subscribe((loginResponse: LoginResponse) => {
          this.snackBarService.showSnackbarMessage(
            'Login successful', 'success-snackbar'
          )

          sessionStorage.setItem('token', loginResponse.token);

          this.requestStatus = RequestStatus.COMPLETED

          this.router.navigate([environmentPaths.home_page]);
        })

      this.loginForm.reset();
      Object.keys(this.loginForm.controls).forEach(key => {
        const control = this.loginForm.get(key);
        control?.setErrors(null);
        control?.markAsPristine();
        control?.markAsUntouched();
      });
    }
  }


  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  protected readonly RequestStatus = RequestStatus;
}
