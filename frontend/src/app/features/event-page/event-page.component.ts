import {Component, OnDestroy, OnInit} from '@angular/core';
import {CompetitionResponse} from "../../model/response/competition-response";
import {RuleResponse} from "../../model/response/rule-response";
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {EventResponse} from "../../model/response/event-response";
import {EventService} from "../../core/services/event.service";
import {SnackbarService} from "../../core/services/snackbar.service";
import {Router} from "@angular/router";
import {GameWithRulesResponse} from "../../model/response/game-response";
import {SlipService} from "../../core/services/slip.service";
import {OddResponse} from "../../model/response/odd-response";
import {SlipOddResponse} from "../../model/response/slip-odd-response";

@Component({
  selector: 'app-event-page',
  templateUrl: './event-page.component.html',
  styleUrl: './event-page.component.css'
})
export class EventPageComponent implements OnInit, OnDestroy {

  selectedCompetition: CompetitionResponse | null = null
  selectedGameRules: RuleResponse[] | undefined;

  events$: Observable<EventResponse[]> = new Observable()

  private destroy$ = new Subject<void>();

  constructor(
    private eventService: EventService,
    private snackBarService: SnackbarService,
    private router: Router,
    private slipService: SlipService
  ) {
  }

  ngOnInit(): void {
    this.selectedCompetition = JSON.parse(sessionStorage.getItem('selectedCompetition')!);

    const selectedGame: GameWithRulesResponse | null = JSON.parse(sessionStorage.getItem('selectedGame')!);
    this.selectedGameRules = selectedGame?.rules;

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

  //TODO non inserire due odd uguali
  addOddToSlip(event: EventResponse, rule: RuleResponse) {

    const odd: OddResponse = {
      id: event.odds[rule.position ]?.id,
      name: rule.name,
      value: event.odds[rule.position]?.value,
      position: rule.position
    }

    const slipOddToAdd: SlipOddResponse = {
      oddId: odd.id!,
      eventName: event.name,
      oddName: odd.name,
      oddValue: odd.value
    }

    this.slipService.addOddToSlip(slipOddToAdd);
  }



  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

}
