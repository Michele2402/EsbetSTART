
import {Component, OnDestroy, OnInit} from '@angular/core';
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {TransactionService} from "../../core/services/transaction.service";
import {SnackbarService} from "../../core/services/snackbar.service";
import {TransactionRequest} from "../../model/request/transaction-request";
import {TransactionType} from "../../model/enum/transaction-type";
import {TransactionResponse} from "../../model/response/transaction-response";

@Component({
  selector: 'app-withdrawals-page',
  templateUrl: './withdrawals-page.component.html',
  styleUrl: './withdrawals-page.component.css'
})
export class WithdrawalsPageComponent implements OnInit, OnDestroy{

  withdrawals$ = new Observable<TransactionResponse[]>();


  private _destroy$ = new Subject<void>();

  constructor(
    private transactionService: TransactionService,
    private snackBarService: SnackbarService,

  ) {  }

  ngOnInit(): void {
    this.loadAllTransaction()
  }

  getEmailFromToken(token: string): string | null {
    if (token) {
      try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.sub || null;
      } catch (error) {
        return null;
      }
    }
    return null
  }

  getCurrentUserEmail(): string | null {
    const token = sessionStorage.getItem('token');
    if(token) {
      return this.getEmailFromToken(token)
    }
    return null;
  }

  loadAllTransaction(): void {

    let transactionRequest: TransactionRequest = {
      gamblerEmail: this.getCurrentUserEmail() || '',
      type: TransactionType.WITHDRAWAL,
    };

   this.withdrawals$ = this.transactionService.showTransaction(transactionRequest)
      .pipe(
        takeUntil(this._destroy$),
        catchError((error) => {

          let errorMessage: string = 'Failed to load Transactions'
          this.snackBarService.errorHandler(errorMessage, error)

          return [];
        })
      );
  }


  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }

}
