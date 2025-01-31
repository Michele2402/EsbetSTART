import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";
import {Observable} from "rxjs";
import {EventResponse} from "../../model/response/event-response";
import {AddEventRequest} from "../../model/request/add-event-request";
import {UpdateEventRequest} from "../../model/request/update-event-request";
import {UpdateOddRequest} from "../../model/request/update-odd-request";
import {EndEventRequest} from "../../model/request/end-event-request";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) {
  }

  getAllByCompetitionId(competitionId: string): Observable<EventResponse[]> {

    return this.http.get<EventResponse[]>(
      environmentPaths.base_path + environmentPaths.get_all_events + '/' + competitionId,
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

  updateEvent(updateEventRequest: UpdateEventRequest) {

    const token = sessionStorage.getItem('token');

    return this.http.post<EventResponse>(
      environmentPaths.base_path + environmentPaths.update_event,
      updateEventRequest,
      {headers: {'Authorization': 'Bearer ' + token}}
    );
  }

  updateOdd(updateOddRequest: UpdateOddRequest) {

    const token = sessionStorage.getItem('token');

    return this.http.post<EventResponse>(
      environmentPaths.base_path + environmentPaths.update_odd,
      updateOddRequest,
      {headers: {'Authorization': 'Bearer ' + token}}
    );
  }

  endEvent(endEventRequest: EndEventRequest) {

    const token = sessionStorage.getItem('token');

    return this.http.post<EventResponse>(
      environmentPaths.base_path + environmentPaths.end_event,
      endEventRequest,
      {headers: {'Authorization': 'Bearer ' + token}}
    );
  }

}
