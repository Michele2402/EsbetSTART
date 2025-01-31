import {Component, OnDestroy, OnInit} from '@angular/core';
import {catchError, Observable, Subject, takeUntil} from "rxjs";
import {OfferResponse} from "../../model/response/offer-response";
import {ActivatedOfferResponse} from "../../model/response/activated-offer-response";
import {PromotionService} from "../../core/services/promotion.service";
import {SnackbarService} from "../../core/services/snackbar.service";
import {AcceptOfferRequest} from "../../model/request/accept-offer-request";



@Component({
  selector: 'app-promotion-page',
  templateUrl: './promotion-page.component.html',
  styleUrl: './promotion-page.component.css'
})
export class PromotionPageComponent implements OnInit,OnDestroy {

  allPromotion$ = new Observable<OfferResponse[]>();

  allActivedPromotion$ = new Observable<ActivatedOfferResponse[]>();

  private _destroy$ = new Subject<void>();

  constructor(
    private promotionService: PromotionService,
    private snackBarService: SnackbarService,

    ) {  }

  ngOnInit(): void {
    this.loadAllPromotion();
    this.loadAllActivePromotion(this.getCurrentUserEmail() || '');
  }

  getEmailFromToken(token: string): string | null {
    if (token) {
      try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.sub || null;
      } catch (error) {
        return null;
      }
    }
    return null
  }

  getCurrentUserEmail(): string | null {
    const token = sessionStorage.getItem('token');
    if(token) {
      return this.getEmailFromToken(token)
    }
    return null;
  }

  loadAllPromotion(): void {

    this.allPromotion$ = this.promotionService.getAllOffer()
      .pipe(
        takeUntil(this._destroy$),
        catchError((error) => {

          let errorMessage: string = 'Failed to load promotions'
          this.snackBarService.errorHandler(errorMessage, error)

          return [];
        })
      );
  }

  loadAllActivePromotion(email: string): void {
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

  acceptPromotion(id: string): void{

    let acceptOfferRequest: AcceptOfferRequest = {
      offerId: id,
      gamblerEmail: this.getCurrentUserEmail() || ''
    };

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
        this.loadAllActivePromotion(this.getCurrentUserEmail() || '');
      });

}


ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }

}
