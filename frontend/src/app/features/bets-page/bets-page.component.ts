import {Component, OnInit} from '@angular/core';
import {catchError, map, Observable, Subject, takeUntil} from "rxjs";
import {BetPlacedResponse} from "../../model/response/bet-placed-response";
import {Location} from "@angular/common";
import {BetsService} from "../../core/services/bets.service";
import {SnackbarService} from "../../core/services/snackbar.service";
import {JwtService} from "../../core/services/jwt.service";
import {ShowUserBetsRequest} from "../../model/request/show-user-bets-request";

@Component({
  selector: 'app-bets-page',
  templateUrl: './bets-page.component.html',
  styleUrl: './bets-page.component.css'
})
export class BetsPageComponent implements OnInit {

  allBets$ = new Observable<BetPlacedResponse[]>();

  private _destroy$ = new Subject<void>();

  constructor(
    private betsService: BetsService,
    private snackBarService: SnackbarService,
    private jwtService: JwtService
  ) {
  }

  ngOnInit(): void {
    this.showAllBets()
  }

  showAllBets() {

    const email = this.jwtService.getCurrentUserEmail() || '';

    let showUserBetsRequest: ShowUserBetsRequest = {
      gamblerEmail: email,
      pending: false
    };

    this.allBets$ = this.betsService.showBets(showUserBetsRequest)
      .pipe(
        map(bets => bets
          .sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime())
          .map(bet => ({
            ...bet,
            date: new Date(bet.date).toLocaleString(undefined, {
              year: 'numeric',
              month: '2-digit',
              day: '2-digit',
              hour: '2-digit',
              minute: '2-digit'
            }),
            oddStatics: bet.oddStatics.map(oddStatic => ({
              ...oddStatic,
              value: parseFloat(oddStatic.value.toFixed(2))
            }))
          }))
        ),
        takeUntil(this._destroy$),
        catchError((error) => {
          let errorMessage: string = 'Failed to load all Bets';
          this.snackBarService.errorHandler(errorMessage, error);
          return [];
        })
      );

  }

  calculatePotentialWinnings(bet: BetPlacedResponse): string {

    console.log(bet)
    let potentialWinnings = bet.amount;
    bet.oddStatics.forEach((odd: any) => {
      potentialWinnings *= odd.value;
    });
    return potentialWinnings.toFixed(2);
  }
}
