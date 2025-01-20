import { Injectable } from '@angular/core';
import {environmentPaths} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {GameWithRulesResponse} from "../../model/response/game-response";
import {AddGameRequest} from "../../model/request/add-game-request";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private basePath = environmentPaths.base_path;

  constructor(private http: HttpClient) { }

  getAllGames(): Observable<GameWithRulesResponse[]> {
    return this.http.get<GameWithRulesResponse[]>(this.basePath + environmentPaths.get_all_games);
  }

  addGame(request: AddGameRequest): Observable<any> {
    return this.http.post<any>(this.basePath + environmentPaths.add_game, request);
  }
}
