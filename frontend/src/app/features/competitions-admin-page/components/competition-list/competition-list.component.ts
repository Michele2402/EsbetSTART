import {Component, OnDestroy, OnInit} from '@angular/core';
import {GameWithRulesResponse} from "../../../../model/response/game-response";
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {CompetitionResponse} from "../../../../model/response/competition-response";
import {CompetitionService} from "../../../../core/services/competition.service";
import {SnackbarService} from "../../../../core/services/snackbar.service";

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrl: './competition-list.component.css'
})
export class CompetitionListComponent implements OnInit, OnDestroy {

  selectedGame: GameWithRulesResponse | null = null;

  competitions$: Observable<CompetitionResponse[]> = new Observable()

  found: boolean = false

  private _destroy$ = new Subject<void>();

  constructor(
    private competitionService: CompetitionService,
    private snackBarService: SnackbarService
  ) {
  }

  ngOnInit(): void {

    this.selectedGame = JSON.parse(sessionStorage.getItem('selectedGame')!);
    if (this.selectedGame) {
      this.found = true;
    }

    if (this.selectedGame) {
      this.competitions$ = this.competitionService.getAllByGameId(this.selectedGame.id).pipe(
        takeUntil(this._destroy$),
        catchError((error) => {
          this.snackBarService.showSnackbarMessage(
            error.error.errors, 'error-snackbar'
          )
          return [];
        })
      )
    }
  }

  updateCompetition(competition: CompetitionResponse) {

  }

  removeCompetition(competitionId: string) {
    this.competitionService.removeCompetition(competitionId)
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

  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }
}
