import {ChangeDetectorRef, Component, EventEmitter, OnDestroy, Output, Type} from '@angular/core';
import {AbstractControl, FormArray, FormBuilder, ValidationErrors, Validators} from "@angular/forms";
import {AddGameRequest} from "../../../../model/request/add-game-request";
import {catchError, Subject, takeUntil} from "rxjs";
import {GameService} from "../../../../core/services/game.service";
import {SnackbarService} from "../../../../core/services/snackbar.service";
import {AddRuleRequest} from "../../../../model/request/add-rule-request";

export function minMaxArrayLengthValidator(min: number, max: number) {
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
  selector: 'app-add-game-button',
  templateUrl: './add-game-button.component.html',
  styleUrl: './add-game-button.component.css'
})
export class AddGameButtonComponent implements OnDestroy {

  @Output() loadGames = new EventEmitter<any>();

  private _destroy$ = new Subject<void>();

  addGameForm = this._fb.group({
    name: ['', [Validators.required, Validators.maxLength(30)]],
    rules: this._fb.array([], minMaxArrayLengthValidator(1, 100))
  });

  isAddingGame: boolean = false;

  constructor(
    private gameService: GameService,
    private _fb: FormBuilder,
    private _snackBarService: SnackbarService,
    private _cdr: ChangeDetectorRef
  ) {

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
    let addGameRequest: AddGameRequest;

    const name = this.addGameForm.get('name')?.value;
    const rules = [] as AddRuleRequest[];

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

            let errorMessage: string = 'Failed to add game'
            this._snackBarService.errorHandler(errorMessage, error)

            return [];
          })
        )
        .subscribe(() => {
          this.isAddingGame = false;
          this.loadGames.emit();
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
