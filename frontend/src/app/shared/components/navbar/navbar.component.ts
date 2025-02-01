import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {environmentPaths} from "../../../environments/environment";
import {Role} from "../../../model/enum/role";
import {Observable} from "rxjs";
import {BalanceResponse} from "../../../model/response/balance-response";
import {JwtService} from "../../../core/services/jwt.service";
import {BalanceService} from "../../../core/services/balance.service";
import {SlipService} from "../../../core/services/slip.service";
import {SnackbarService} from "../../../core/services/snackbar.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{

  role: string | null = null;

  isBalanceVisible: boolean = false;

  balance = new Observable<BalanceResponse>()

  constructor(
    private router: Router,
    private jwtService: JwtService,
    private balanceService: BalanceService,
    private slipService: SlipService,
    private snackBarService: SnackbarService,
  ) {

  }

  ngOnInit(): void {
    this.role = this.jwtService.getCurrentUserRole();

    if (this.role == Role.GAMBLER) {
      this.slipService.fetchSlip();

      const email = this.jwtService.getCurrentUserEmail();

      if(email) {
        this.balanceService.updateBalance(email);
      }

      this.balance = this.balanceService.getBalance();
    }
  }

  toHomePage() {
    this.router.navigate([environmentPaths.home_page]);
  }

  toggleBalance(): void {
    this.isBalanceVisible = !this.isBalanceVisible;
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
