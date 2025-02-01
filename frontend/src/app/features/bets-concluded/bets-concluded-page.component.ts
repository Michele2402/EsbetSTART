import {Component, OnInit} from '@angular/core';
import {Location} from "@angular/common";
import {catchError, map, Observable, Subject, takeUntil} from "rxjs";
import {BetsService} from "../../core/services/bets.service";
import {SnackbarService} from "../../core/services/snackbar.service";
import {ShowUserBetsRequest} from "../../model/request/show-user-bets-request";
import {BetPlacedResponse} from "../../model/response/bet-placed-response";
import {OddStaticResponse} from "../../model/response/odd-static-response";
import {JwtService} from "../../core/services/jwt.service";

@Component({
  selector: 'app-bets-concluded-page',
  templateUrl: './bets-concluded-page.component.html',
  styleUrl: './bets-concluded-page.component.css'
})
export class BetsConcludedPageComponent {


}
