import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environmentPaths} from "../../environments/environment";
import {TransactionRequest} from "../../model/request/transaction-request";
import {TransactionResponse} from "../../model/response/transaction-response";


@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private basePath = environmentPaths.base_path;

  constructor(private http: HttpClient) { }


  showTransaction(body: TransactionRequest) {
    const token = sessionStorage.getItem('token');

    return this.http.post<TransactionResponse[]>(this.basePath + environmentPaths.show_transaction,body,
      {headers: {'Authorization': 'Bearer ' + token}});
  }

  showAllTransaction() {
    const token = sessionStorage.getItem('token');

    return this.http.post<TransactionResponse[]>(this.basePath + environmentPaths.show_all_transaction,
      {headers: {'Authorization': 'Bearer ' + token}});
  }


}
