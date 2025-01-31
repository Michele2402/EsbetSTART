import {Component, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, catchError, debounceTime, Observable, Subject, switchMap, takeUntil} from "rxjs";
import {SearchBarFilterData} from "../../model/html/search-bar-filter-data";
import {SnackbarService} from "../../core/services/snackbar.service";
import {GameWithRulesResponse} from "../../model/response/game-response";
import {GameService} from "../../core/services/game.service";



@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrl: './search-page.component.css'
})
export class SearchPageComponent implements OnInit, OnDestroy {
  displayedColumns: string[] = ['id', 'name'];

  //FILTERS
  pageIndex = 0;
  pageSize = 10;
  private _filterWaitTime = 500;
  private _initialFilters: SearchBarFilterData = {
    nameFilter: '',
    pagination: {
      pageIndex: this.pageIndex,
      pageSize: this.pageSize
    }
  }
  private _filterSubject$ = new BehaviorSubject<SearchBarFilterData>(this._initialFilters);
  filters$ = this._filterSubject$.asObservable();


  //GAMES
  private _gamesSubject$ = new BehaviorSubject<GameWithRulesResponse[]>([]);
  games$ = this._gamesSubject$.asObservable();
  games: GameWithRulesResponse[] = [];


  //UNSUBSCRIPTIONS
  private _destroy$ = new Subject<void>();

  constructor(private _gamesService: GameService) {
  }

  ngOnInit() {
    this._filterSubject$.pipe(
      debounceTime(this._filterWaitTime),
      switchMap((filters: SearchBarFilterData) => {
        return this.getFilteredGames(filters);
      }),
      takeUntil(this._destroy$),
      catchError(() => []) //todo: implementare con snackbar
    ).subscribe((games) => {
        this.games = games;
    });
  }


  private getFilteredGames(filters: SearchBarFilterData): Observable<GameWithRulesResponse[]> {
    return this._gamesService.getFilteredGames(filters);
  }


  ngOnDestroy() {
    this._destroy$.next();
    this._destroy$.complete();
  }

}
