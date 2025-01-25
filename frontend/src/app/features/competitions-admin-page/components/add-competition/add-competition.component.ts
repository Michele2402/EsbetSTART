import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {GameWithRulesResponse} from '../../../../model/response/game-response';
import {AddCompetitionRequest} from "../../../../model/request/add-competition-request";
import {CompetitionService} from "../../../../core/services/competition.service";
import {SnackbarService} from "../../../../core/services/snackbar.service";
import {catchError, Subject, takeUntil} from 'rxjs';
import {Router} from "@angular/router";
import {environmentPaths} from "../../../../environments/environment";

@Component({
    selector: 'app-add-competition',
    templateUrl: './add-competition.component.html',
    styleUrl: './add-competition.component.css'
})
export class AddCompetitionComponent implements OnInit, OnDestroy {

    @Output() loadCompetitions = new EventEmitter<void>();

    selectedGame: GameWithRulesResponse | null = null;

    competitionForm: FormGroup = this.fb.group({
        name: ['', [Validators.required, Validators.maxLength(30)]],
        originCountry: ['', [Validators.required, Validators.maxLength(30)]]
    });
    formVisible = false;

    private _destroy$ = new Subject<void>();

    constructor(
        private fb: FormBuilder,
        private competitionService: CompetitionService,
        private snackBarService: SnackbarService,
        private router: Router
    ) {

    }

    ngOnInit(): void {
        this.selectedGame = JSON.parse(sessionStorage.getItem('selectedGame')!);
        console.log(this.selectedGame)
    }

    toAdminEsportsPage() {
        this.router.navigate([environmentPaths.e_sports_admin_page]);
    }

    toggleForm(): void {
        this.formVisible = !this.formVisible;
    }

    onSubmit(): void {
        if (this.competitionForm.valid) {

            const addCompetitionRequest: AddCompetitionRequest = {
                gameId: this.selectedGame!.id,
                name: this.competitionForm.get('name')?.value,
                originCountry: this.competitionForm.get('originCountry')?.value
            };

            this.competitionService.addCompetition(addCompetitionRequest)
                .pipe(
                    takeUntil(this._destroy$),
                    catchError((error) => {
                        this.snackBarService.showSnackbarMessage(
                            error.error.errors, 'error-snackbar'
                        )
                        return []
                    })
                )
                .subscribe(() => {
                        this.snackBarService.showSnackbarMessage(
                            'Competition added', 'success-snackbar'
                        )

                        this.loadCompetitions.emit();
                        this.competitionForm.reset();
                        this.formVisible = false;
                    }
                )
        }
    }

    ngOnDestroy(): void {
        this._destroy$.next();
        this._destroy$.complete();
    }

}


