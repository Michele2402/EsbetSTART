import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";
import {AcceptOfferRequest} from "../../model/request/accept-offer-request";
import {Observable} from "rxjs";
import {OfferResponse} from "../../model/response/offer-response";
import {ActivatedOfferResponse} from "../../model/response/activated-offer-response";


@Injectable({
  providedIn: 'root'
})
export class PromotionService {

  private basePath = environmentPaths.base_path;

  constructor(private http: HttpClient) {
  }

  acceptOffer(body: AcceptOfferRequest) {
    const token = sessionStorage.getItem('token');

    return this.http.post(this.basePath + environmentPaths.accept_offer, body,
      {headers: {'Authorization': 'Bearer ' + token}});
  }

  getAllOffer(): Observable<OfferResponse[]> {
    const token = sessionStorage.getItem('token');

    return this.http.get<OfferResponse[]>(
      this.basePath + environmentPaths.get_all_offer,
      {headers: {'Authorization': 'Bearer ' + token}}
    );

  }

  getActivatedOffer(gamblerEmail: string): Observable<ActivatedOfferResponse[]> {
    const token = sessionStorage.getItem('token');

    return this.http.get<ActivatedOfferResponse[]>(this.basePath + environmentPaths.get_activated_offer + '/' + gamblerEmail,
      {headers: {'Authorization': 'Bearer ' + token}});

  }


}
