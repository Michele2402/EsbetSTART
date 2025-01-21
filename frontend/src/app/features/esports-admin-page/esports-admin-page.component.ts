import {Component, OnDestroy, OnInit} from '@angular/core';
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {GameWithRulesResponse} from "../../model/response/game-response";
import {GameService} from "../../core/services/game.service";
import {AbstractControl, FormArray, FormBuilder, ValidationErrors, Validators} from "@angular/forms";
import {ChangeDetectorRef} from '@angular/core';
import {AddGameRequest} from "../../model/request/add-game-request";
import {SnackbarService} from "../../core/services/snackbar/snackbar.service";

function minMaxArrayLengthValidator(min: number, max: number) {
  return (control: AbstractControl): ValidationErrors | null => {
    if (!control || !control.value || !Array.isArray(control.value)) {
      return null;
    }
    const length = control.value.length;
    if (length < min) {
      return {minLength: {requiredLength: min, actualLength: length}};
    }
    if (length > max) {
      return {maxLength: {requiredLength: max, actualLength: length}};
    }
    return null;
  }
}

@Component({
  selector: 'app-esports-admin-page',
  templateUrl: './esports-admin-page.component.html',
  styleUrl: './esports-admin-page.component.css'
})
export class EsportsAdminPageComponent implements OnInit, OnDestroy {

  allGames$ = new Observable<GameWithRulesResponse[]>();

  private _destroy$ = new Subject<void>();

  addGameForm = this._fb.group({
    name: ['', [Validators.required, Validators.maxLength(30)]],
    rules: this._fb.array([], minMaxArrayLengthValidator(1, 5))
  });

  isAddingGame: boolean = false;

  constructor(
    private gameService: GameService,
    private _fb: FormBuilder,
    private _snackBarService: SnackbarService,
    private _cdr: ChangeDetectorRef
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
          console.log(error);
          return [];
        })
      )
  }

  get rules(): FormArray {
    return this.addGameForm.get('rules') as FormArray;
  }

  addRule(event: Event): void {
    event.preventDefault();
    const ruleGroup = this._fb.group({
      rule: ['', [Validators.required, Validators.maxLength(30)]]
    });
    this.rules.push(ruleGroup);
    this._cdr.detectChanges();
  }

  removeRule(index: number): void {
    this.rules.removeAt(index);
    this._cdr.detectChanges();
  }

  toggleAddGame(): void {
    this.isAddingGame = !this.isAddingGame;
  }

  addGame(): void {
    console.log(this.addGameForm);

    let addGameRequest: AddGameRequest;

    const name = this.addGameForm.get('name')?.value;
    const rules = [];

    for (let i = 0; i < this.rules.length; i++) {
      if (this.rules.at(i).get('rule')?.value) {
        rules.push({name: this.rules.at(i).get('rule')?.value, position: i});
      }
    }

    if (this.addGameForm.valid && name) {
      addGameRequest = {
        name: name,
        rules: rules
      }

      this.gameService.addGame(addGameRequest)
        .pipe(
          takeUntil(this._destroy$),
          catchError((error) => {
            this._snackBarService.showSnackbarMessage(
              error.error.errors, 'error-snackbar'
            )
            return [];
          })
        )
        .subscribe(() => {
          this.isAddingGame = false;
          this.loadAllGames();
          this._snackBarService.showSnackbarMessage(
            'Game Added', 'success-snackbar'
          )
        });
    }

  }

  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }

}
