import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {GameWithRulesResponse} from "../../../../model/response/game-response";
import {CompetitionResponse} from "../../../../model/response/competition-response";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {catchError, Subject, takeUntil} from "rxjs";
import {SnackbarService} from "../../../../core/services/snackbar.service";
import {Router} from "@angular/router";
import {environmentPaths} from "../../../../environments/environment";
import {AddEventRequest} from "../../../../model/request/add-event-request";
import {AddOddRequest} from "../../../../model/request/add-odd-request";
import {EventService} from "../../../../core/services/event.service";

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrl: './add-event.component.css'
})
export class AddEventComponent implements OnInit, OnDestroy {

  @Output() loadEvents = new EventEmitter<void>();

  selectedGame: GameWithRulesResponse | null = null;
  selectedCompetition: CompetitionResponse | null = null;

  addEventForm: FormGroup = this.fb.group({
    name: ['', [Validators.required, Validators.maxLength(30)]],
    date: ['', [Validators.required]],
    rules: this.fb.array([])
  });
  isAddingEvent: boolean = false;

  private _destroy$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private snackBarService: SnackbarService,
    private eventService: EventService,
    private router: Router
  ) {

  }

  ngOnInit(): void {
    this.selectedGame = JSON.parse(sessionStorage.getItem('selectedGame')!);
    this.selectedCompetition = JSON.parse(sessionStorage.getItem('selectedCompetition')!);

    if (this.selectedGame == null) {
      sessionStorage.removeItem("selectedCompetition")
    } else {
      this.initializeFormWithRules()
    }
  }

  initializeFormWithRules(): void {
    const rulesArray = this.fb.array(
      this.selectedGame!.rules.map(rule =>
        this.fb.group({
          ruleName: [rule.name],
          value: [1, [Validators.required, Validators.min(1)]]
        })
      )
    );

    this.addEventForm.setControl('rules', rulesArray);
  }

  toggleAddEvent(): void {
    this.isAddingEvent = !this.isAddingEvent;
  }

  get rules(): FormArray {
    return this.addEventForm.get('rules') as FormArray;
  }

  onSubmit() {
    if (this.addEventForm.valid) {

      const addOddRequests: AddOddRequest[] = [];

      for (let i = 0; i < this.rules.length; i++) {
        if (this.rules.at(i).get('value')?.value) {
          addOddRequests.push({
            name: this.rules.at(i).get('ruleName')?.value,
            value: this.rules.at(i).get('value')?.value,
            position: i
          })
        }
      }

      const eventRequest: AddEventRequest = {
        competitionId: this.selectedCompetition!.id,
        name: this.addEventForm.get('name')?.value,
        date: this.addEventForm.get('date')?.value,
        odds: addOddRequests
      }

      this.eventService.addEvent(eventRequest)
        .pipe(
          takeUntil(this._destroy$),
          catchError((error) => {
            this.snackBarService.errorHandler('Failed to add event', error);
            return [];
          })
        ).subscribe(() => {
          this.snackBarService.showSnackbarMessage(
            'Event added successfully', 'success-snackbar'
          )

        this.loadEvents.emit();
        this.addEventForm.reset();
        this.isAddingEvent = false;
        this.initializeFormWithRules()
      })
    }
  }

  toAdminEsportsPage() {
    this.router.navigate([environmentPaths.e_sports_admin_page]);
  }

  toAdminCompetitionsPage() {
    this.router.navigate([environmentPaths.competitions_admin_page]);
  }

  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }

}
