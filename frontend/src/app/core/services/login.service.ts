import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterRequest} from "../../model/request/register-request";
import {environmentPaths} from "../../environments/environment";
import {Observable} from "rxjs";
import {LoginRequest} from "../../model/request/login-request";
import {LoginResponse} from "../../model/response/login-response";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private basePath = environmentPaths.base_path;

  constructor(private http: HttpClient) { }

  login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.basePath + environmentPaths.login, request);
  }
}
