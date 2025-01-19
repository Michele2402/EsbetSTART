import { Injectable } from '@angular/core';
import {environmentPaths} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {GameWithRulesResponse} from "../../model/response/game-response";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private basePath = environmentPaths.base_path;

  constructor(private http: HttpClient) { }

  getAllGames(): Observable<GameWithRulesResponse[]> {
    return this.http.get<GameWithRulesResponse[]>(this.basePath + environmentPaths.get_all_games);
  }
}
