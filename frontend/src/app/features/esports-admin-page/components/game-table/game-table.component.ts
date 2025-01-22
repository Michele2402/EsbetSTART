import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {GameService} from "../../../../core/services/game.service";
import {GameWithRulesResponse} from "../../../../model/response/game-response";
import {SnackbarService} from "../../../../core/services/snackbar/snackbar.service";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {minMaxArrayLengthValidator} from "../add-game-button/add-game-button.component";
import {UpdateGameRequest} from "../../../../model/request/update-game-request";
import {AddRuleRequest} from "../../../../model/request/add-rule-request";

@Component({
  selector: 'app-game-table',
  templateUrl: './game-table.component.html',
  styleUrl: './game-table.component.css'
})
export class GameTableComponent implements OnInit, OnDestroy {

  allGames$ = new Observable<GameWithRulesResponse[]>();
  editingGame: GameWithRulesResponse | null = null;

  editGameForm = this._fb.group({
    name: ['', [Validators.required, Validators.maxLength(30)]],
    rules: this._fb.array([] as string[], minMaxArrayLengthValidator(1, 100))
  });

  private _destroy$ = new Subject<void>();

  constructor(
    private gameService: GameService,
    private _fb: FormBuilder,
    private snackBarService: SnackbarService,
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
          this.snackBarService.showSnackbarMessage(
            error.error.errors, 'error-snackbar'
          );
          return [];
        })
      );
  }

  removeGame(id: string): void {
    this.gameService.removeGame(id)
      .pipe(
        takeUntil(this._destroy$),
        catchError((error) => {
          console.log(error)
          this.snackBarService.showSnackbarMessage(
            error.error.errors, 'error-snackbar'
          )
          return [];
        })
      ).subscribe(() => this.loadAllGames())
  }

  toggleEditing(game: GameWithRulesResponse): void {
    if (this.editingGame?.id !== game.id) {
      this.editingGame = game;

      this.editGameForm.reset({
        name: game.name
      });

      this.rules.clear();

      game.rules.forEach(rule => {
        const ruleGroup = this._fb.group({
          name: [rule.name, [Validators.required, Validators.maxLength(30)]]
        });
        this.rules.push(ruleGroup);
      });

      this._cdr.detectChanges();
    } else {
      this.editingGame = null;
    }
  }

  get rules(): FormArray {
    return this.editGameForm?.get('rules') as FormArray;
  }

  addRule(event: Event): void {
    event.preventDefault();
    const ruleGroup = this._fb.group({
      name: ['', [Validators.required, Validators.maxLength(30)]]
    });
    this.rules.push(ruleGroup);
    this._cdr.detectChanges();
  }

  removeRule(index: number, event: Event): void {
    event.preventDefault();
    this.rules.removeAt(index);
  }

  saveChanges(): void {

    let updateGameRequest: UpdateGameRequest;

    const name = this.editGameForm.get('name')?.value;
    const rulesToAdd = [] as AddRuleRequest[];

    for (let i = 0; i < this.rules.length; i++) {
      if (this.rules.at(i).get('name')?.value) {
        rulesToAdd.push({name: this.rules.at(i).get('name')?.value, position: i});
      }
    }

    if (this.editGameForm.valid && this.editingGame && name) {

      updateGameRequest = {
        gameId: this.editingGame.id,
        name: name,
        rules: rulesToAdd
      };

      this.gameService.updateGame(updateGameRequest)
        .pipe(
          takeUntil(this._destroy$),
          catchError((error) => {
            this.snackBarService.showSnackbarMessage(
              error.error.errors, 'error-snackbar'
            );
            return [];
          })
        ).subscribe(() => {
        this.loadAllGames();
      });
    }

    this.editingGame = null;
    this.editGameForm.markAsDirty();
  }

  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }
}
