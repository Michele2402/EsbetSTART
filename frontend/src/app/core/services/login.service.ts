import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterRequest} from "../../model/request/register-request";
import {environmentPaths} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private basePath = environmentPaths.base_path;

  constructor(private http: HttpClient) { }

  signIn(body: RegisterRequest) {
    return this.http.post(this.basePath + environmentPaths.login, body);
  }


}
