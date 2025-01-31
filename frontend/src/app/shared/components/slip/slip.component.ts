import {Component, OnInit} from '@angular/core';
import {SlipService} from "../../../core/services/slip.service";
import {Observable} from "rxjs";
import {JwtService} from "../../../core/services/jwt.service";
import {SnackbarService} from "../../../core/services/snackbar.service";
import {SlipResponse} from "../../../model/response/slip-response";

@Component({
  selector: 'app-slip',
  templateUrl: './slip.component.html',
  styleUrl: './slip.component.css'
})
export class SlipComponent implements OnInit {

  isOpen = false;

  slip$: Observable<SlipResponse> = new Observable();

  constructor(
    private slipService: SlipService,
    private jwtService: JwtService,
    private snackBarService: SnackbarService
  ) {
  }

  ngOnInit(): void {
    this.slip$ = this.slipService.getSlip();
  }

  toggleBetSlip() {
    this.isOpen = !this.isOpen;
  }

  updateSlipOdd() {

/*    const slipOdds = this.slipService.getSlipOdds();
    const oddIds = slipOdds.map(odd => odd.oddId);

    const gamblerEmail = this.jwtService.getCurrentUserEmail();

    if(!gamblerEmail) {
      this.snackBarService.showSnackbarMessage(
        'Failed to update slip, please log in',
        'error-snackbar'
      );
      return;
    }

    const updateSlipRequest: UpdateSlipRequest = {
      oddIds: oddIds,
      gamblerEmail: gamblerEmail,
      amount : 0
    }*/

/*    this.slipService.updateSlipOdd();*/
  }

}
