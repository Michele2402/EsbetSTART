import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  constructor(private http: HttpClient) { }

  private basePath = environmentPaths.base_path;

    openTicket() {
      const token = sessionStorage.getItem('token');

      return this.http.post<>(this.basePath + environmentPaths.open_ticket,
        {headers: {'Authorization': 'Bearer ' + token}});

    }





}
