import {Component, OnDestroy, OnInit} from '@angular/core';
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {CompetitionService} from "../../core/services/competition.service";
import {SnackbarService} from "../../core/services/snackbar.service";
import {CompetitionResponse} from "../../model/response/competition-response";
import {GameWithRulesResponse} from "../../model/response/game-response";
import {environmentPaths} from "../../environments/environment";
import {Router} from "@angular/router";

@Component({
  selector: 'app-competition-page',
  templateUrl: './competition-page.component.html',
  styleUrl: './competition-page.component.css'
})
export class CompetitionPageComponent implements OnInit, OnDestroy {


  selectedGame: GameWithRulesResponse | null = null;

  competitions$: Observable<CompetitionResponse[]> = new Observable()

  private _destroy$ = new Subject<void>();

  constructor(
    private competitionService: CompetitionService,
    private snackBarService: SnackbarService,
    private router: Router,
  ) {
  }

  ngOnInit(): void{
    this.selectedGame = JSON.parse(sessionStorage.getItem('selectedGame')!);
    this.loadAllCompetitions()
  }

  loadAllCompetitions() {

    if (this.selectedGame) {
      this.competitions$ = this.competitionService.getAllByGameId(this.selectedGame.id).pipe(
        takeUntil(this._destroy$),
        catchError((error) => {

          let defaultErrorMessage: string = 'Failed to load competitions'
          this.snackBarService.errorHandler(defaultErrorMessage, error)

          return [];
        })
      )
    }
  }

  selectCompetition(competition: CompetitionResponse) {
    sessionStorage.setItem('selectedCompetition', JSON.stringify(competition));
    this.router.navigate([environmentPaths.event_page]);
  }

  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }
}
