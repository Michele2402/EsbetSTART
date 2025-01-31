import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";
import {BalanceResponse} from "../../model/response/balance-response";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BalanceService {

  private basePath = environmentPaths.base_path

  private balanceSubject = new BehaviorSubject<BalanceResponse>({balance: 0, bonusBalance: 0, withdrawableBalance: 0});
  balance$ = this.balanceSubject.asObservable();

  constructor(private http: HttpClient) {
  }

  updateBalance(email: string) {
    const token = sessionStorage.getItem('token');

    this.http.get<BalanceResponse>(
      this.basePath + environmentPaths.balance + '/' + email,
      {headers: {'Authorization': `Bearer ${token}`}}
    ).subscribe(
      balanceResponse => this.balanceSubject.next(balanceResponse)
    )
  }

  getBalance(): Observable<BalanceResponse> {
    return this.balance$
  }
}
