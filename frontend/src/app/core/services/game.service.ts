import {Injectable} from '@angular/core';
import {environmentPaths} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {GameWithRulesResponse} from "../../model/response/game-response";
import {AddGameRequest} from "../../model/request/add-game-request";
import {UpdateGameRequest} from "../../model/request/update-game-request";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private basePath = environmentPaths.base_path;

  constructor(private http: HttpClient) {
  }

  //----------------------------------------------

  getAllGames(): Observable<GameWithRulesResponse[]> {
    return this.http.get<GameWithRulesResponse[]>(this.basePath + environmentPaths.get_all_games);
  }

  addGame(request: AddGameRequest): Observable<any> {
    return this.http.post<any>(this.basePath + environmentPaths.add_game, request);
  }

  removeGame(id: string): Observable<any> {
    return this.http.delete<any>(this.basePath + environmentPaths.remove_game + '/' + id);
  }

  updateGame(request: UpdateGameRequest): Observable<any> {
    return this.http.post<any>(this.basePath + environmentPaths.update_game, request);
  }
}
