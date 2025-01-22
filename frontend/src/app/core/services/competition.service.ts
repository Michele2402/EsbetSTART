import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {CompetitionResponse} from "../../model/response/competition-response";
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  private basePath = environmentPaths.base_path;

  private selectedCompetitionSubject$ = new BehaviorSubject<CompetitionResponse | null>(null);

  constructor(private http: HttpClient) { }

  getAllByGameId(gameId: string): Observable<CompetitionResponse[]> {
    return this.http.get<CompetitionResponse[]>(this.basePath + environmentPaths.get_all_competitions + '/' + gameId);
  }

  removeCompetition(competitionId: string): Observable<any> {
    return this.http.delete(this.basePath + environmentPaths.remove_competition + '/' + competitionId);
  }
}
