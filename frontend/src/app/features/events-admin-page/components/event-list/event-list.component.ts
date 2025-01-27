import {Component, OnDestroy, OnInit} from '@angular/core';
import {CompetitionResponse} from "../../../../model/response/competition-response";
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {EventResponse} from "../../../../model/response/event-response";
import {EventService} from "../../../../core/services/event.service";
import {SnackbarService} from "../../../../core/services/snackbar.service";
import {Router} from "@angular/router";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {RuleResponse} from "../../../../model/response/rule-response";
import {GameWithRulesResponse} from "../../../../model/response/game-response";

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

  columnsToDisplay: string[] = [];
  columnsToDisplayWithExpand: string[]  = []
  expandedElement: EventResponse | null = null;

  constructor(
    private eventService: EventService,
    private snackBarService: SnackbarService,
    private router: Router
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

  loadAllEvents() {
    if (this.selectedCompetition) {
      this.events$ = this.eventService.getAllByCompetitionId(this.selectedCompetition?.id!)
        .pipe(
          takeUntil(this.destroy$),
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

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
