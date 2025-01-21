import {Component, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, catchError, debounceTime, Subject, switchMap, takeUntil} from "rxjs";
import {SearchBarFilterData} from "../../model/html/search-bar-filter-data";

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];




@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrl: './search-page.component.css'
})
export class SearchPageComponent implements OnInit, OnDestroy {
  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource = ELEMENT_DATA;


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


  //UNSUBSCRIPTIONS
  private _destroy$ = new Subject<void>()

  ngOnInit() {
    this._filterSubject$.pipe(
      debounceTime(this._filterWaitTime),
/*      switchMap((filters: SearchBarFilterData) => {
        return //TODO: chiamare API passandogli i filtri come parametri
      }),*/
      takeUntil(this._destroy$),
      catchError(() => []) //todo: implementare con snackbar
    ).subscribe();
  }



  ngOnDestroy() {
    this._destroy$.next();
    this._destroy$.complete();
  }

}
