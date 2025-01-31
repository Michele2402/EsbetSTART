import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {GameWithRulesResponse} from "../../../../model/response/game-response";
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {CompetitionResponse} from "../../../../model/response/competition-response";
import {CompetitionService} from "../../../../core/services/competition.service";
import {SnackbarService} from "../../../../core/services/snackbar.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AddCompetitionRequest} from "../../../../model/request/add-competition-request";
import {UpdateCompetitionRequest} from "../../../../model/request/update-competition-request";
import {environmentPaths} from "../../../../environments/environment";
import {Router} from "@angular/router";

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrl: './competition-list.component.css'
})
export class CompetitionListComponent implements OnInit, OnDestroy {

  selectedGame: GameWithRulesResponse | null = null;

  competitions$: Observable<CompetitionResponse[]> = new Observable()

  competitionToUpdate: CompetitionResponse | null = null;

  private _destroy$ = new Subject<void>();
  updateCompetitionForm: FormGroup = this.fb.group({
    name: [this.competitionToUpdate?.name, [Validators.required, Validators.maxLength(30)]],
    originCountry: [this.competitionToUpdate?.originCountry, [Validators.required, Validators.maxLength(30)]]
  });

  formVisible = false

  constructor(
    private competitionService: CompetitionService,
    private snackBarService: SnackbarService,
    private fb: FormBuilder,
    private router: Router
  ) {
  }

  ngOnInit(): void {

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
    this.router.navigate([environmentPaths.events_admin_page]);
  }

  updateCompetition(competition: CompetitionResponse) {

    this.updateCompetitionForm.patchValue({
      name: competition.name,
      originCountry: competition.originCountry
    })

    if (this.competitionToUpdate === competition) {
      this.formVisible = false
      this.competitionToUpdate = null
    } else {
      this.competitionToUpdate = competition
      this.formVisible = true
    }
  }

  onSubmit() {
    if (this.updateCompetitionForm.valid) {

      const updateCompetitionRequest: UpdateCompetitionRequest = {
        competitionId: this.competitionToUpdate!.id,
        name: this.updateCompetitionForm.get('name')?.value,
        originCountry: this.updateCompetitionForm.get('originCountry')?.value
      };

      this.competitionService.updateCompetition(updateCompetitionRequest)
        .pipe(
          takeUntil(this._destroy$),
          catchError((error) => {

            let defaultErrorMessage: string = 'Failed to update competition'
            this.snackBarService.errorHandler(defaultErrorMessage, error)

            return [];
          })
        )
        .subscribe(() => {
            this.snackBarService.showSnackbarMessage(
              'Competition updated', 'success-snackbar'
            )

            this.loadAllCompetitions()
            this.updateCompetitionForm.reset();
            this.formVisible = false;
          }
        )
    }
  }

  removeCompetition(competitionId: string) {
    this.competitionService.removeCompetition(competitionId)
      .pipe(
        takeUntil(this._destroy$),
        catchError((error) => {

          let errorMessage: string = 'Failed to remove competition'
          this.snackBarService.errorHandler(errorMessage, error)

          return [];
        })
      ).subscribe(
      () => {
        this.loadAllCompetitions();
        this.snackBarService.showSnackbarMessage(
          'Competition removed', 'success-snackbar'
        )
      }
    )

  }

  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }
}
