import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";
import {AcceptOfferRequest} from "../../model/request/accept-offer-request";


@Injectable({
  providedIn: 'root'
})
export class PromotionService {

  private basePath = environmentPaths.base_path;

  constructor(private http: HttpClient) { }

  acceptOffer(body : AcceptOfferRequest){
    return this.http.post(this.basePath + environmentPaths.accept_offer, body)
}




}
