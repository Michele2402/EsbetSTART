import {Injectable, OnDestroy} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, catchError, Observable, Subject, takeUntil} from "rxjs";
import {SlipResponse} from "../../model/response/slip-response";
import {SlipOddResponse} from "../../model/response/slip-odd-response";
import {environmentPaths} from "../../environments/environment";
import {JwtService} from "./jwt.service";
import {SnackbarService} from "./snackbar.service";
import {Router} from "@angular/router";
import {UpdateSlipRequest} from "../../model/request/update-slip-request";

@Injectable({
  providedIn: 'root'
})
export class SlipService implements OnDestroy {

  private basePath = environmentPaths.base_path

  private destroy$ = new Subject<void>();

  private slipSubject = new BehaviorSubject<SlipResponse>({id: '', amount: 0, odds: []}); // Valore di default
  private slip$ = this.slipSubject.asObservable();

  private isOpen = false

  constructor(
    private http: HttpClient,
    private jwtService: JwtService,
    private snackBarService: SnackbarService,
    private router: Router
  ) {

    window.addEventListener('beforeunload', () => this.saveSlipInStorage());
    this.fetchSlip();
  }

  toggleBetSlip() {
    this.isOpen = !this.isOpen;
  }

  isSlipOpen(): boolean {
    return this.isOpen;
  }

  fetchSlip() {

    let slip: SlipResponse | null = JSON.parse(sessionStorage.getItem('slip')!);

    if (slip) {
      this.slipSubject.next(slip);
    } else {
      const email = this.jwtService.getCurrentUserEmail();
      if (!email) {
        console.log("not logged")
        return;
      }

      const token = sessionStorage.getItem('token');

      this.http.get<SlipResponse>(
        this.basePath + environmentPaths.get_slip + '/' + email,
        {headers: {'Authorization': 'Bearer ' + token}}
      )
        .pipe(
          takeUntil(this.destroy$),
          catchError((error) => {
            //this.snackBarService.errorHandler('Failed to load slip', error);
            return [];
          })
        )
        .subscribe((slipResponse: SlipResponse) => {
          console.log(slipResponse);
          sessionStorage.setItem('slip', JSON.stringify(slipResponse));
          this.slipSubject.next(slipResponse);
        });
    }
  }


  private updateSlipOdds(odds: SlipOddResponse[]) {

    const slip: SlipResponse = JSON.parse(sessionStorage.getItem('slip')!);

    sessionStorage.setItem('slip', JSON.stringify({id: slip.id, amount: slip.amount, odds: odds}));

    this.fetchSlip()
  }

  addOddToSlip(odd: SlipOddResponse) {
    const slipOdds: SlipOddResponse[] = this.slipSubject.value.odds

    if (slipOdds.some(slipOdd => slipOdd.oddId === odd.oddId)) {
      this.snackBarService.showSnackbarMessage('Odd already selected', 'error-snackbar');
      return;
    }

    this.updateSlipOdds([...slipOdds, odd])
  }

  removeOddFromSlip(oddId: string) {
    const slipOdds: SlipOddResponse[] = this.slipSubject.value.odds

    this.updateSlipOdds(slipOdds.filter(odd => odd.oddId !== oddId))
  }


  updateSlipAmount(amount: number) {

    const slip: SlipResponse = JSON.parse(sessionStorage.getItem('slip')!);

    sessionStorage.setItem('slip', JSON.stringify({id: slip.id, amount: amount, odds: slip.odds}));

    this.fetchSlip()
  }

  getSlipObservable(): Observable<SlipResponse> {
    return this.slip$
  }

  getSlipValue(): SlipResponse {
    return this.slipSubject.value
  }

  saveSlipInStorage(): Observable<any> {

    const email = this.jwtService.getCurrentUserEmail();
    if (!email) {
      return new Observable();
    }

    const token = sessionStorage.getItem('token');
    const slip: SlipResponse = JSON.parse(sessionStorage.getItem('slip')!);

    const updateSlipRequest: UpdateSlipRequest = {
      amount: slip.amount,
      oddsIds: slip.odds.map(odd => odd.oddId),
      gamblerEmail: email
    }

    return this.http.post<any>(
      this.basePath + environmentPaths.save_slip,
      updateSlipRequest,
      {headers: {'Authorization': 'Bearer ' + token}}
    )
  }

  resetSlip() {
    sessionStorage.removeItem('slip');
    this.fetchSlip();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
