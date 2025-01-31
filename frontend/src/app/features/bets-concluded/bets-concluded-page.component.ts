import {Component, OnInit} from '@angular/core';
import {Location} from "@angular/common";
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {BetsService} from "../../core/services/bets.service";
import {SnackbarService} from "../../core/services/snackbar.service";
import {ShowUserBetsRequest} from "../../model/request/show-user-bets-request";
import {BetPlacedResponse} from "../../model/response/bet-placed-response";
import {OddStaticResponse} from "../../model/response/odd-static-response";

@Component({
  selector: 'app-bets-concluded-page',
  templateUrl: './bets-concluded-page.component.html',
  styleUrl: './bets-concluded-page.component.css'
})
export class BetsConcludedPageComponent implements OnInit{

  allBets$ = new Observable<BetPlacedResponse[]>();

  private _destroy$ = new Subject<void>();

  constructor(
    private location: Location,
    private betsService: BetsService,
    private snackBarService: SnackbarService,
  ) {}

  goBack(): void {
    this.location.back();
  }
  ngOnInit(): void {
    this.showAllBets()
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

  showAllBets() {

    let showUserBetsRequest: ShowUserBetsRequest = {
      gamblerEmail: this.getCurrentUserEmail() || '',
      pending: false
    };

    this.allBets$ =this.betsService.showBets(showUserBetsRequest)
      .pipe(
        takeUntil(this._destroy$),
        catchError((error) => {

          let errorMessage: string = 'Failed to load Bets Concluded'
          this.snackBarService.errorHandler(errorMessage, error)

          return [];
        })
      );

  }
}
