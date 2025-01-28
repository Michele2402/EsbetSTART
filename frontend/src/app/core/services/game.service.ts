import {Injectable} from '@angular/core';
import {environmentPaths} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, map, Observable} from "rxjs";
import {GameWithRulesResponse} from "../../model/response/game-response";
import {AddGameRequest} from "../../model/request/add-game-request";
import {UpdateGameRequest} from "../../model/request/update-game-request";
import {SearchBarFilterData} from "../../model/html/search-bar-filter-data";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private basePath = environmentPaths.base_path;

  constructor(private http: HttpClient) {
  }

  getFilteredGames(filters: SearchBarFilterData): Observable<GameWithRulesResponse[]> {
    return this.http.get<GameWithRulesResponse[]>("http://localhost:3000/games").pipe(
      //map((response: any) => response['content']) //todo: decommentare quando avrai le api con la paginazione
    );
  }

  getAllGames(): Observable<GameWithRulesResponse[]> {
    const token = sessionStorage.getItem('token');

    return this.http.get<GameWithRulesResponse[]>(
      this.basePath + environmentPaths.get_all_games,
      {headers: {'Authorization': 'Bearer ' + token}}
    );
  }

  addGame(request: AddGameRequest): Observable<any> {
    const token = sessionStorage.getItem('token')

    return this.http.post<any>(
      this.basePath + environmentPaths.add_game, request,
      {headers: {'Authorization': 'Bearer ' + token}}
    );
  }

  removeGame(id: string): Observable<any> {
    const token = sessionStorage.getItem('token')

    return this.http.delete<any>(
      this.basePath + environmentPaths.remove_game + '/' + id,
      {headers: {'Authorization': 'Bearer ' + token}}
    )
  }

  updateGame(request: UpdateGameRequest): Observable<any> {

    const token = sessionStorage.getItem('token')

    return this.http.post<any>(
      this.basePath + environmentPaths.update_game,
      request,
      {headers: {'Authorization': 'Bearer ' + token}}
    )
  }
}
