import {Component, HostListener, OnDestroy, OnInit} from '@angular/core';
import {SlipService} from "../../../core/services/slip.service";
import {catchError, Observable, Subject, switchMap, takeUntil} from "rxjs";
import {JwtService} from "../../../core/services/jwt.service";
import {SnackbarService} from "../../../core/services/snackbar.service";
import {SlipResponse} from "../../../model/response/slip-response";
import {BetsService} from "../../../core/services/bets.service";
import {MatDialog} from "@angular/material/dialog";
import {BetConfirmationDialogComponent} from "./bet-confirmation-dialog.component";
import {SlipSaverService} from "../../../core/services/slip-saver.service";

@Component({
  selector: 'app-slip',
  templateUrl: './slip.component.html',
  styleUrl: './slip.component.css'
})
export class SlipComponent implements OnInit, OnDestroy {

  isOpen = false;

  slip$: Observable<SlipResponse> = new Observable();

  private destroy$ = new Subject<void>();

  constructor(
    private slipService: SlipService,
    private slipSaver: SlipSaverService,
    private snackBarService: SnackbarService,
    private betsService: BetsService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.slip$ = this.slipService.getSlipObservable();
    this.isOpen = this.slipService.isSlipOpen();
  }

  toggleBetSlip() {
    this.slipService.toggleBetSlip();
    this.isOpen = this.slipService.isSlipOpen();
  }

  updateAmount(event: Event) {
    const newAmount = (event.target as HTMLInputElement).valueAsNumber;
    if (!isNaN(newAmount)) {
      this.slipService.updateSlipAmount(newAmount);
    }
  }


  removeOdd(oddId: string) {
    this.slipService.removeOddFromSlip(oddId);
  }

  placeBet() {
    const slip = this.slipService.getSlipValue();

    const dialogRef = this.dialog.open(BetConfirmationDialogComponent, {
      width: '400px',
      data: { slip }
    });

    this.slipService.saveSlipInStorage()
      .pipe(
        takeUntil(this.destroy$),
        catchError((error) => {
          this.snackBarService.errorHandler('Failed to save slip', error);
          return [];
        }),
        switchMap(() => dialogRef.afterClosed())
      )
      .subscribe(result => {
        if (result) {
          this.betsService.placeBet(slip.id)
            .pipe(
              takeUntil(this.destroy$),
              catchError((error) => {
                this.snackBarService.errorHandler('Failed to place bet', error);
                return [];
              })
            )
            .subscribe(() => {
              this.snackBarService.showSnackbarMessage('Bet placed', 'success-snackbar');
              this.slipService.resetSlip();
            });
        }
      });
  }


  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
