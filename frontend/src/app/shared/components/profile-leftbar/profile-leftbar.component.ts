import {Component, OnDestroy} from '@angular/core';
import {SnackbarService} from "../../../core/services/snackbar.service";
import {BalanceService} from "../../../core/services/balance.service";
import {TransactionRequest} from "../../../model/request/transaction-request";
import {JwtService} from "../../../core/services/jwt.service";
import {ProfileService} from "../../../core/services/profile.service";
import {catchError, Subject, takeUntil} from "rxjs";


@Component({
  selector: 'app-profile-leftbar',
  templateUrl: './profile-leftbar.component.html',
  styleUrl: './profile-leftbar.component.css'
})
export class ProfileLeftbarComponent implements OnDestroy {

  isDepositVisible = false;
  isWithdrawVisible = false;
  depositAmount: number = 0;
  withdrawAmount: number = 0;

  private destroy$ = new Subject<void>();

  constructor(
    private snackBarService: SnackbarService,
    private balanceService: BalanceService,
    private jwtService: JwtService,
    private profileService: ProfileService
  ) {
  }

  toggleDepositInput(): void {
    this.isDepositVisible = !this.isDepositVisible;
    if (this.isDepositVisible) {
      this.isWithdrawVisible = false; // Hide withdraw input
    }
  }

  toggleWithdrawInput(): void {
    this.isWithdrawVisible = !this.isWithdrawVisible;
    if (this.isWithdrawVisible) {
      this.isDepositVisible = false; // Hide deposit input
    }
  }

  onInputChange(): void {
    if (this.depositAmount < 0) {
      this.depositAmount = 0;
    }
    if (this.withdrawAmount < 0) {
      this.withdrawAmount = 0;
    }
  }

  deposit(): void {

    const email = this.jwtService.getCurrentUserEmail();

    if (!email) {
      this.snackBarService.showSnackbarMessage('Please login first!', 'error-snackbar');
      return;
    }

    const transactionRequest: TransactionRequest = {
      gamblerEmail: email,
      transactionType: 'DEPOSIT',
      transactionValue: this.depositAmount
    };

    if (this.depositAmount >= 0) {
      this.profileService.transaction(transactionRequest)
        .pipe(
          takeUntil(this.destroy$),
          catchError((error) => {
            this.snackBarService.errorHandler('Transaction failed!', error);
            return [];
          })
        ).subscribe(() => {
          this.snackBarService.showSnackbarMessage('Transaction successful!', 'success-snackbar');
          this.depositAmount = 0;
          this.balanceService.updateBalance(email);
          this.toggleDepositInput();
        })
    }
  }

  withdraw(): void {
    if (this.withdrawAmount >= 0) {
      const email = this.jwtService.getCurrentUserEmail();

      if (!email) {
        this.snackBarService.showSnackbarMessage('Please login first!', 'error-snackbar');
        return;
      }

      const transactionRequest: TransactionRequest = {
        gamblerEmail: email,
        transactionType: 'WITHDRAWAL',
        transactionValue: this.withdrawAmount
      };

      if (this.withdrawAmount >= 0) {
        this.profileService.transaction(transactionRequest)
          .pipe(
            takeUntil(this.destroy$),
            catchError((error) => {
              this.snackBarService.errorHandler('Transaction failed!', error);
              return [];
            })
          ).subscribe(() => {
          this.snackBarService.showSnackbarMessage('Transaction successful!', 'success-snackbar');
          this.withdrawAmount = 0;
          this.balanceService.updateBalance(email);
          this.toggleWithdrawInput();
        })
      }
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
