import { Injectable } from '@angular/core';
import {CanActivate, Router} from "@angular/router";
import {SnackbarService} from "../services/snackbar.service";
import {environmentPaths} from "../../environments/environment";
import {JwtService} from "../services/jwt.service";
import {LoginResponse} from "../../model/response/login-response";
import {Role} from "../../model/enum/role";

@Injectable({
  providedIn: 'root'
})
export class EventManagerGuardService implements CanActivate {

  constructor(
    private router: Router,
    private snackBarService: SnackbarService,
    private jwtService: JwtService
  ) {}

  canActivate(): boolean {
    const token = sessionStorage.getItem('token');

    if (!token) {
      this.snackBarService.showSnackbarMessage(
        'You need to be logged in to access this page', 'error-snackbar'
      )
      this.router.navigate([environmentPaths.home_page]);
      return false;
    }

    if (this.jwtService.getRoleFromToken(token) === Role.EVENT_MANAGER) {
      return true;
    } else {
      this.snackBarService.showSnackbarMessage(
        'You need to be an event manager to access this page', 'error-snackbar'
      )
      this.router.navigate([environmentPaths.home_page]);
      return false;
    }
  }
}
