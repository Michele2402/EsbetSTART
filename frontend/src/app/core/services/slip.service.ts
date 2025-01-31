import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SlipOdd} from "../../model/internal/slip-odd";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SlipService {

  private slipOddsSubject = new BehaviorSubject<SlipOdd[]>(this.getSlipOddsFromStorage());
  slipOdds$ = this.slipOddsSubject.asObservable();

  constructor(private http: HttpClient) {
  }

  private getSlipOddsFromStorage(): SlipOdd[] {
    return JSON.parse(sessionStorage.getItem('slipOdds')!) || [];
  }

  private updateSlipOdds(odds: SlipOdd[]) {
    sessionStorage.setItem('slipOdds', JSON.stringify(odds));
    this.slipOddsSubject.next(odds);
  }

  addOddToSlip(odd: SlipOdd) {
    const slipOdds = this.getSlipOddsFromStorage();
    slipOdds.push(odd);
    this.updateSlipOdds(slipOdds);
  }

  removeOddFromSlip(oddId: string) {
    let slipOdds = this.getSlipOddsFromStorage();
    slipOdds = slipOdds.filter(odd => odd.oddId !== oddId);
    this.updateSlipOdds(slipOdds);
  }

  getSlipOdds(): SlipOdd[] {
    return this.slipOddsSubject.getValue();
  }
}
