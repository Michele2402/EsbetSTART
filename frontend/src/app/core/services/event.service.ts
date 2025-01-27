import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";
import {Observable} from "rxjs";
import {EventResponse} from "../../model/response/event-response";
import {AddEventRequest} from "../../model/request/add-event-request";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) {
  }

  getAllByCompetitionId(competitionId: string): Observable<EventResponse[]> {

    const token = sessionStorage.getItem('token');

    return this.http.get<EventResponse[]>(
      environmentPaths.base_path + environmentPaths.get_all_events + '/' + competitionId,
      {headers: {'Authorization': 'Bearer ' + token}}
    );
  }

  addEvent(addEventRequest: AddEventRequest) {

    const token = sessionStorage.getItem('token');

    return this.http.post<EventResponse>(
      environmentPaths.base_path + environmentPaths.add_event,
      addEventRequest,
      {headers: {'Authorization': 'Bearer ' + token}}
    );
  }

}
