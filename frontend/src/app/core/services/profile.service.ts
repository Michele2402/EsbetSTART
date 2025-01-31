import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";
import {UserResponse} from "../../model/response/user-response";
import {UpdateUserRequest} from "../../model/request/update-user-request";
import {Observable} from "rxjs";
import {TransactionRequest} from "../../model/request/transaction-request";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private baseUrl = environmentPaths.base_path;

  constructor(private http: HttpClient) { }

  getProfile(email: string): Observable<UserResponse> {
    const token = sessionStorage.getItem('token');

    return this.http.get<UserResponse>(
      this.baseUrl + environmentPaths.get + '/' + email,
      {headers: {'Authorization': 'Bearer ' + token}}
    );
  }

  updateUser(updateUserRequest: UpdateUserRequest) {
    const token = sessionStorage.getItem('token');

      return this.http.post(
        this.baseUrl +  environmentPaths.update,
        updateUserRequest,
        {headers: {'Authorization': 'Bearer ' + token}}
      );
  }

  transaction(request: TransactionRequest) {
    const token = sessionStorage.getItem('token');

    return this.http.post(
      this.baseUrl +  environmentPaths.transactions,
      request,
      {headers: {'Authorization': 'Bearer ' + token}}
    );
  }
}
