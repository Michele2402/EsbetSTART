import {Component, OnDestroy, OnInit} from '@angular/core';
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {OfferResponse} from "../../model/response/offer-response";
import {ActivatedOfferResponse} from "../../model/response/activated-offer-response";
import {PromotionService} from "../../core/services/promotion.service";
import {SnackbarService} from "../../core/services/snackbar.service";
import {AcceptOfferRequest} from "../../model/request/accept-offer-request";
import {JwtService} from "../../core/services/jwt.service";


@Component({
  selector: 'app-promotion-page',
  templateUrl: './promotion-page.component.html',
  styleUrl: './promotion-page.component.css'
})
export class PromotionPageComponent implements OnInit, OnDestroy {

  allPromotions$ = new Observable<OfferResponse[]>();

  allActivedPromotion$ = new Observable<ActivatedOfferResponse[]>();

  private _destroy$ = new Subject<void>();

  constructor(
    private promotionService: PromotionService,
    private snackBarService: SnackbarService,
    private jwtService: JwtService
  ) {
  }

  ngOnInit(): void {
    this.loadAllPromotion();
    this.loadAllActivePromotion();
  }


  loadAllPromotion(): void {

    this.allPromotions$ = this.promotionService.getAllOffer()
      .pipe(
        takeUntil(this._destroy$),
        catchError((error) => {

          let errorMessage: string = 'Failed to load promotions'
          this.snackBarService.errorHandler(errorMessage, error)

          return [];
        })
      );
  }

  loadAllActivePromotion(): void {

    const email = this.jwtService.getCurrentUserEmail();

    if(email == null){
      this.snackBarService.showSnackbarMessage(
        'Please login first', 'error-snackbar');
      return;
    }

    this.allActivedPromotion$ = this.promotionService.getActivatedOffer(email)
      .pipe(
        takeUntil(this._destroy$),
        catchError((error) => {

          let errorMessage: string = 'Failed to load active promotions'
          this.snackBarService.errorHandler(errorMessage, error)

          return [];
        })
      );

  }

  acceptPromotion(id: string): void {

    const email = this.jwtService.getCurrentUserEmail();

    if(email == null){
      this.snackBarService.showSnackbarMessage(
        'Please login first', 'error-snackbar');
      return;
    }

    const acceptOfferRequest: AcceptOfferRequest = {
      gamblerEmail: email,
      offerId: id
    }

    this.promotionService.acceptOffer(acceptOfferRequest)
      .pipe(
        takeUntil(this._destroy$),
        catchError((error) => {

          let errorMessage: string = 'Failed to add promotion'
          this.snackBarService.errorHandler(errorMessage, error)

          return [];
        })
      )
      .subscribe(() => {
        this.snackBarService.showSnackbarMessage(
          'Promotion Added', 'success-snackbar');
        this.loadAllActivePromotion();
      });

  }


  ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }

}
