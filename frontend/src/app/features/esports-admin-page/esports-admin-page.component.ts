import {Component, OnDestroy, OnInit} from '@angular/core';
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {GameWithRulesResponse} from "../../model/response/game-response";
import {GameServiceService} from "../../core/services/game-service.service";

@Component({
  selector: 'app-esports-admin-page',
  templateUrl: './esports-admin-page.component.html',
  styleUrl: './esports-admin-page.component.css'
})
export class EsportsAdminPageComponent implements OnInit, OnDestroy{

  allGames$ = new Observable<GameWithRulesResponse[]>();

  private _destroy$ = new Subject<void>();

  constructor(
    private gameService: GameServiceService
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

  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }

}
