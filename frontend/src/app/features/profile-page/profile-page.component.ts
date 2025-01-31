import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {ProfileService} from "../../core/services/profile.service";
import {UserResponse} from "../../model/response/user-response";
import {JwtService} from "../../core/services/jwt.service";
import {BehaviorSubject, catchError, Observable, Subject, takeUntil} from "rxjs";
import {SnackbarService} from "../../core/services/snackbar.service";
import {UpdateUserRequest} from "../../model/request/update-user-request";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrl: './profile-page.component.css'
})
export class ProfilePageComponent implements OnInit, OnDestroy {

  editProfileForm = this._fb.group({
    name: ['', [Validators.required, Validators.maxLength(30)]],
    surname: ['', [Validators.required, Validators.maxLength(30)]],
    username: ['', [Validators.required, Validators.maxLength(30)]],
  });

  private loggedUserSubject$ = new BehaviorSubject<UserResponse | null>(null);
  loggedUser: Observable<UserResponse | null> = this.loggedUserSubject$.asObservable();

  private _destroy$ = new Subject<void>();

  constructor(
    private _fb: FormBuilder,
    private profileService: ProfileService,
    private jwtService: JwtService,
    private snackBarService: SnackbarService
  ) {
  }

  ngOnInit(): void {
    this.loadUser();
  }

  loadUser() {

    const token = sessionStorage.getItem('token');
    const email = this.jwtService.getCurrentUserEmail();

    if (token != null && email != null) {
      this.profileService.getProfile(email)
        .pipe(
          takeUntil(this._destroy$),
          catchError((error) => {
            this.snackBarService.errorHandler('Failed to load profile', error);
            return []
          })
        ).subscribe(
        (response) => {
          this.loggedUserSubject$.next(response);

          this.editProfileForm.patchValue({
            name: response.name,
            surname: response.surname,
            username: response.username
          })
        }
      )
    }
  }


  onSubmit(): void {

    const email = this.jwtService.getCurrentUserEmail();
    const name = this.editProfileForm.get('name')?.value;
    const surname = this.editProfileForm.get('surname')?.value;
    const username = this.editProfileForm.get('username')?.value;

    if (this.editProfileForm.valid && email != null && name != null && surname != null && username != null) {

      const formData: UpdateUserRequest = {
        email: email,
        name: name,
        surname: surname,
        username: username
      }

      this.profileService.updateUser(formData)
        .pipe(
          takeUntil(this._destroy$),
          catchError((error) => {
            this.snackBarService.errorHandler('Failed to update profile', error)
            return [];
          })
        )
        .subscribe(() => {
          this.snackBarService.showSnackbarMessage(
            'Credentials updated', 'success-snackbar'
          )

          this.loadUser()
        })
    }
  }

  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }

}
