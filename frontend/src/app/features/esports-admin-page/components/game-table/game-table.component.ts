import {Component, OnDestroy, OnInit} from '@angular/core';
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {GameService} from "../../../../core/services/game.service";
import {GameWithRulesResponse} from "../../../../model/response/game-response";
import {SnackbarService} from "../../../../core/services/snackbar/snackbar.service";

@Component({
  selector: 'app-game-table',
  templateUrl: './game-table.component.html',
  styleUrl: './game-table.component.css'
})
export class GameTableComponent implements OnInit, OnDestroy {

  allGames$ = new Observable<GameWithRulesResponse[]>();

  private _destroy$ = new Subject<void>();

  constructor(
    private gameService: GameService,
    private snackBarService: SnackbarService
  ) {

  }

  ngOnInit(): void {
    this.loadAllGames();
  }

  loadAllGames(): void {
    this.allGames$ = this.gameService.getAllGames()
      .pipe(
        takeUntil(this._destroy$),
        catchError((error) => {
          this.snackBarService.showSnackbarMessage(
            error.error.errors, 'error-snackbar'
          )
          return [];
        })
      )
  }

  removeGame(id: string): void {
    this.gameService.removeGame(id)
      .pipe(
        takeUntil(this._destroy$),
        catchError((error) => {
          this.snackBarService.showSnackbarMessage(
            error.error.errors, 'error-snackbar'
          )
          return [];
        })
      ).subscribe(() => this.loadAllGames())
  }

  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }

}
