import {Component, OnInit} from '@angular/core';
import {SlipOdd} from "../../../model/internal/slip-odd";
import {SlipService} from "../../../core/services/slip.service";
import {Observable} from "rxjs";
import {UpdateSlipRequest} from "../../../model/request/update-slip-request";
import {JwtService} from "../../../core/services/jwt.service";
import {SnackbarService} from "../../../core/services/snackbar.service";

@Component({
  selector: 'app-slip',
  templateUrl: './slip.component.html',
  styleUrl: './slip.component.css'
})
export class SlipComponent implements OnInit {

  isOpen = false;

  slipOdds$: Observable<SlipOdd[]> | undefined;

  constructor(
    private slipService: SlipService,
    private jwtService: JwtService,
    private snackBarService: SnackbarService
  ) {
  }

  toggleBetSlip() {
    this.isOpen = !this.isOpen;
  }

  ngOnInit(): void {
    this.slipOdds$ = this.slipService.slipOdds$;
  }

  updateSlipOdd() {

    const slipOdds = this.slipService.getSlipOdds();
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
    }

/*    this.slipService.updateSlipOdd();*/
  }

}
