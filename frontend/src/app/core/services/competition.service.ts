import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {CompetitionResponse} from "../../model/response/competition-response";
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";
import {AddCompetitionRequest} from "../../model/request/add-competition-request";
import {UpdateCompetitionRequest} from "../../model/request/update-competition-request";

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  private basePath = environmentPaths.base_path;

  private selectedCompetitionSubject$ = new BehaviorSubject<CompetitionResponse | null>(null);

  constructor(private http: HttpClient) { }

  getAllByGameId(gameId: string): Observable<CompetitionResponse[]> {

    return this.http.get<CompetitionResponse[]>(
      this.basePath + environmentPaths.get_all_competitions + '/' + gameId,
    );
  }

  removeCompetition(competitionId: string): Observable<any> {
    const token = sessionStorage.getItem('token');

    return this.http.delete(
      this.basePath + environmentPaths.remove_competition + '/' + competitionId,
      {headers: {'Authorization': 'Bearer ' + token}}
    )
  }

  addCompetition(request: AddCompetitionRequest): Observable<any> {
    const token = sessionStorage.getItem('token');

    return this.http.post(
      this.basePath + environmentPaths.add_competition, request,
      {headers: {'Authorization': 'Bearer ' + token}}
    );
  }

  updateCompetition(request: UpdateCompetitionRequest): Observable<any> {
    const token = sessionStorage.getItem('token');

    return this.http.post(
      this.basePath + environmentPaths.update_competition,
      request,
      {headers: {'Authorization': 'Bearer ' + token}}
    )
  }
}
