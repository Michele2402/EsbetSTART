import { Injectable } from '@angular/core';
import {CanActivate, Router} from "@angular/router";
import {SnackbarService} from "../services/snackbar.service";
import {JwtService} from "../services/jwt.service";
import {environmentPaths} from "../../environments/environment";
import {Role} from "../../model/enum/role";

@Injectable({
  providedIn: 'root'
})
export class NotLoggedGuardService implements CanActivate{

  constructor(
    private router: Router,
    private snackBarService: SnackbarService,
    private jwtService: JwtService
  ) {}

  canActivate(): boolean {
    const token = sessionStorage.getItem('token');

    if (token) {
      this.snackBarService.showSnackbarMessage(
        'You need to log out to access this page', 'error-snackbar'
      )
      this.router.navigate([environmentPaths.home_page]);
      return false;
    }

    return true;
  }
}
