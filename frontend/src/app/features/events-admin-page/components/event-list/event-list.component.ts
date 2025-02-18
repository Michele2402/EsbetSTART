import {Component, OnDestroy, OnInit} from '@angular/core';
import {CompetitionResponse} from "../../../../model/response/competition-response";
import {catchError, map, Observable, Subject, takeUntil} from "rxjs";
import {EventResponse} from "../../../../model/response/event-response";
import {EventService} from "../../../../core/services/event.service";
import {SnackbarService} from "../../../../core/services/snackbar.service";
import {Router} from "@angular/router";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {RuleResponse} from "../../../../model/response/rule-response";
import {GameWithRulesResponse} from "../../../../model/response/game-response";
import {FormBuilder, Validators} from "@angular/forms";
import {UpdateEventRequest} from "../../../../model/request/update-event-request";
import {OddResponse} from "../../../../model/response/odd-response";
import {UpdateOddRequest} from "../../../../model/request/update-odd-request";
import {EndEventRequest} from "../../../../model/request/end-event-request";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrl: './event-list.component.css',
  animations: [
    trigger('detailExpand', [
      state('collapsed,void', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class EventListComponent implements OnDestroy, OnInit {

  selectedCompetition: CompetitionResponse | null = null
  selectedGameRules: RuleResponse[] | undefined;

  events$: Observable<EventResponse[]> = new Observable()

  private destroy$ = new Subject<void>();

  editEventForm = this.fb.group({
    name: ['', [Validators.required, Validators.maxLength(50)]],
    date: ['', Validators.required],
  });

  columnsToDisplay: string[] = [];
  columnsToDisplayWithExpand: string[] = []

  expandedElement: EventResponse | null = null;

  editingOdd: OddResponse | null = null;

  isUpdating: boolean = false;
  isEnding: boolean = false;

  selectedOdds: { [key: string]: boolean } = {};

  constructor(
    private eventService: EventService,
    private snackBarService: SnackbarService,
    private router: Router,
    private fb: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.selectedCompetition = JSON.parse(sessionStorage.getItem('selectedCompetition')!);

    const selectedGame: GameWithRulesResponse | null = JSON.parse(sessionStorage.getItem('selectedGame')!);
    this.selectedGameRules = selectedGame?.rules;

    this.columnsToDisplay = ['name', ...(this.selectedGameRules?.map((rule) => rule.name) || [])];
    this.columnsToDisplayWithExpand = [...this.columnsToDisplay, 'expand'];

    this.loadAllEvents();
  }

  onRowClick(element: EventResponse, event: MouseEvent, mode: string): void {
    event.stopPropagation();

    this.expandedElement = this.expandedElement === element ? null : element;
    this.selectedOdds = {};

    if (mode === 'update') {
      this.isUpdating = true;
      this.isEnding = false;

      if (this.expandedElement) {
        this.editEventForm.patchValue({
          name: this.expandedElement.name,
          date: this.expandedElement.date,
        });
      }
    } else if (mode === 'end') {
      this.isUpdating = false;
      this.isEnding = true;
    }
  }


  onSubmit(currentEvent: EventResponse | null): void {

    const name = this.editEventForm.get('name')?.value;
    const date = this.editEventForm.get('date')?.value;

    if (this.editEventForm.valid && name && date && currentEvent) {

      const updateEventRequest: UpdateEventRequest = {
        eventId: currentEvent.id,
        name: name,
        date: date
      };

      this.eventService.updateEvent(updateEventRequest)
        .pipe(
          takeUntil(this.destroy$),
          catchError((error) => {
            this.snackBarService.errorHandler('Failed to update event', error);
            return [];
          })
        )
        .subscribe(() => {

          this.snackBarService.showSnackbarMessage(
            'Event updated', 'success-snackbar'
          )

          this.expandedElement = null;
          this.loadAllEvents();
        });
    }
  }

  submitOdds(): void {
    const selectedOddIds = Object.keys(this.selectedOdds).filter(id => this.selectedOdds[id]);

    const endEventRequest: EndEventRequest = {
      eventId: this.expandedElement?.id!,
      winningOdds: selectedOddIds
    };

    this.eventService.endEvent(endEventRequest)
      .pipe(
        takeUntil(this.destroy$),
        catchError((error) => {
          this.snackBarService.errorHandler('Failed to end event', error);
          return [];
        })
      )
      .subscribe(() => {
        this.snackBarService.showSnackbarMessage(
          'Event ended', 'success-snackbar'
        )
        this.loadAllEvents();
      });
  }

  loadAllEvents() {
    if (this.selectedCompetition) {
      this.events$ = this.eventService.getAllByCompetitionId(this.selectedCompetition?.id!)
        .pipe(
          takeUntil(this.destroy$),
          map((events: EventResponse[]) => {
            return events.map(event => {
              event.odds = event.odds.map(odd => {
                odd.value = parseFloat(odd.value.toFixed(2));
                return odd;
              });
              return event;
            });
          }),
          catchError((error) => {
            let defaultErrorMessage: string = 'Failed to load events';
            this.snackBarService.errorHandler(defaultErrorMessage, error);
            return [];
          })
      );
    }
  }

  getRuleValue(event: EventResponse, ruleName: string): number | undefined {
    return event.odds.find(odd => odd.name === ruleName)?.value;
  }

  startEditing(odd: OddResponse): void {
    this.editingOdd = odd;
  }

  stopEditing(odd: OddResponse): void {

    if (this.editingOdd) {

      const updateOddRequest: UpdateOddRequest = {
        oddId: odd.id,
        oddValue: odd.value
      };

      this.eventService.updateOdd(updateOddRequest)
        .pipe(
          takeUntil(this.destroy$),
          catchError((error) => {
            this.snackBarService.errorHandler('Failed to update odd', error);
            return [];
          })
        )
        .subscribe(() => {
          this.snackBarService.showSnackbarMessage(
            'Odd updated', 'success-snackbar'
          )
          this.loadAllEvents();
          this.editingOdd = null;
        });
    }
  }

  toFixed(value: number): number {
    return Number(value.toFixed(2));
  }


  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
