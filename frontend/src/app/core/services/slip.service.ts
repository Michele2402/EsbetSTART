import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {SlipResponse} from "../../model/response/slip-response";
import {SlipOddResponse} from "../../model/response/slip-odd-response";

@Injectable({
    providedIn: 'root'
})
export class SlipService {

    private slipSubject = new BehaviorSubject<SlipResponse>(this.fetchSlip());
    private slip$ = this.slipSubject.asObservable();

    constructor(private http: HttpClient) {
    }

    private fetchSlip(): SlipResponse {
        return JSON.parse(sessionStorage.getItem('slip')!);

        //se non c'è l'oggetto, chiama dal db e lo mette nella sessione
    }

    private updateSlipOdds(odds: SlipOddResponse[]) {
        const slip: SlipResponse = JSON.parse(sessionStorage.getItem('slip')!);

        sessionStorage.setItem('slip', JSON.stringify({amount: slip.amount, odds: odds}));

        this.slipSubject.next(this.fetchSlip());
    }

    addOddToSlip(odd: SlipOddResponse) {
        const slipOdds: SlipOddResponse[] = this.fetchSlip().odds

        this.updateSlipOdds([...slipOdds, odd])
    }

    removeOddFromSlip(oddId: string) {
        const slipOdds: SlipOddResponse[] = this.fetchSlip().odds

        this.updateSlipOdds(slipOdds.filter(odd => odd.oddId !== oddId))
    }


     updateSlipAmount(amount: number) {
        const slip: SlipResponse = JSON.parse(sessionStorage.getItem('slip')!);

        sessionStorage.setItem('slip', JSON.stringify({amount: amount, odds: slip.odds}));

        this.slipSubject.next(this.fetchSlip());
    }

    getSlip(): Observable<SlipResponse>{
        return this.slip$
    }
}
