import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";
import {BetPlacedResponse} from "../../model/response/bet-placed-response";
import {ShowUserBetsRequest} from "../../model/request/show-user-bets-request";

@Injectable({
  providedIn: 'root'
})
export class BetsService {

  private basePath = environmentPaths.base_path;

  constructor(private http: HttpClient) { }

  showBets(body: ShowUserBetsRequest) {
    const token = sessionStorage.getItem('token');

    return this.http.post<BetPlacedResponse[]>(
      this.basePath + environmentPaths.show_bets,
      body,
      {headers: {'Authorization': 'Bearer ' + token}}
    );
  }

  placeBet(slipId: string) {
    const token = sessionStorage.getItem('token');

    return this.http.post<any>(
      this.basePath + environmentPaths.place_bet,
      slipId,
      {headers: {'Authorization': 'Bearer ' + token}}
    )
  }
}
