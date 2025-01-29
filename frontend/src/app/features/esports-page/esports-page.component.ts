import {Component, OnDestroy, OnInit} from '@angular/core';
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {GameService} from "../../core/services/game.service";
import {GameWithRulesResponse} from "../../model/response/game-response";
import {SnackbarService} from "../../core/services/snackbar.service";
import {environmentPaths} from "../../environments/environment";
import {Router} from "@angular/router";

@Component({
  selector: 'app-esports-page',
  templateUrl: './esports-page.component.html',
  styleUrl: './esports-page.component.css'
})
export class EsportsPageComponent implements OnInit, OnDestroy{

  allGames$ = new Observable<GameWithRulesResponse[]>();

  private _destroy$ = new Subject<void>();

  constructor(
    private gameService: GameService,
    private snackBarService: SnackbarService,
    private router: Router,
  ) {

  }
  ngOnInit(): void {
    this.loadAllGames();
  }

 selectGame(game: GameWithRulesResponse): void {
    sessionStorage.setItem('selectedGame', JSON.stringify(game));
    this.router.navigate([environmentPaths.competition_page]);
  }

  loadAllGames(): void {
    this.allGames$ = this.gameService.getAllGames()
      .pipe(
        takeUntil(this._destroy$),
        catchError((error) => {

          let errorMessage = 'Failed to load games'
          this.snackBarService.errorHandler(errorMessage, error)

          return [];
        })
      )
  }

  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }
}
