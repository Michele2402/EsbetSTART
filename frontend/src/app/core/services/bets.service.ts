import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class BetsService {

  private basePath = environmentPaths.base_path

  constructor(private http: HttpClient) {
  }

  placeBet(slipId: string) {
    const token = sessionStorage.getItem('token');

    return this.http.post(
      this.basePath + environmentPaths.place_bet + '?slipId=' + slipId,
      {headers: {'Authorization': 'Bearer ' + token}}
    )
  }
}
