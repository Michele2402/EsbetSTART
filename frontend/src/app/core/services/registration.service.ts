import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";
import {RegisterRequest} from "../../model/request/register-request";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private basePath = environmentPaths.base_path;

  constructor(private http: HttpClient) { }

    signUp(body: RegisterRequest) {
    return this.http.post(this.basePath + environmentPaths.register, body);
  }

}
