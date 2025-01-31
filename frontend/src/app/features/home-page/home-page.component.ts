import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {environmentPaths} from "../../environments/environment";
import {JwtService} from "../../core/services/jwt.service";
import {SnackbarService} from "../../core/services/snackbar.service";
import {Role} from "../../model/enum/role";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit {

  role: string | null = null;

  constructor(
    private router: Router,
    private jwtService: JwtService,
    private snackBarService: SnackbarService
  ) {

  }

  ngOnInit(): void {
    this.role = this.jwtService.getCurrentUserRole();
  }

  toLogin() {
    this.router.navigate([environmentPaths.login_page]);
  }

  toRegister() {
    this.router.navigate([environmentPaths.registration_page]);
  }

  toProfile() {
    this.router.navigate([environmentPaths.profile_page]);
  }

  logout() {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('slip');
    this.router.navigate([environmentPaths.login_page]);

    this.snackBarService.showSnackbarMessage(
      'Logged out', 'success-snackbar'
    )
  }

  protected readonly Role = Role;
}
