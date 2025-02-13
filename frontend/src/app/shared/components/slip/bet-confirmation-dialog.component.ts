import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SlipResponse } from "../../../model/response/slip-response";

@Component({
  selector: 'app-bet-confirmation-dialog',
  template: `
    <div class="dialog-container">
      <h2>You are betting on the following odds:</h2>

      <div class="bet-list">
        <div class="bet-item header">
          <span>Event</span>
          <span>Odd</span>
          <span>Value</span>
        </div>
        <div class="bet-item" *ngFor="let odd of data.slip.odds">
          <span class="bet-name">{{ odd.eventName }}</span>
          <span class="bet-odd">{{ odd.oddName }}</span>
          <span class="bet-value">{{ odd.oddValue }}</span>
        </div>
      </div>

      <div class="bet-summary">
        <p><strong>Amount:</strong> €{{ data.slip.amount }}</p>
        <p><strong>Potential Winnings:</strong> €{{ calculatePotentialWinnings() | number:'1.2-2' }}</p>
      </div>

      <div class="dialog-actions">
        <button mat-raised-button color="warn" (click)="close(false)">No</button>
        <button mat-raised-button color="primary" (click)="close(true)">Yes</button>
      </div>
    </div>
  `,
  styles: [`
    .dialog-container {
      text-align: center;
      padding: 20px;
    }

    .bet-list {
      margin: 20px 0;
      display: flex;
      flex-direction: column;
      gap: 10px;
      height: 400px;
      overflow-y: scroll;
      -ms-overflow-style: none;
      scrollbar-width: none;
    }

    .bet-item {
      display: grid;
      grid-template-columns: 2fr 1fr 1fr;
      padding: 10px;
      border-bottom: 1px solid #ddd;
      text-align: center;
    }

    .bet-item.header {
      font-weight: bold;
      background: #f5f5f5;
      border-top: 1px solid #ddd;
    }

    .bet-summary {
      margin: 20px 0;
      font-size: 18px;
    }

    .dialog-actions {
      display: flex;
      justify-content: space-around;
      margin-top: 20px;
    }
  `]
})
export class BetConfirmationDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<BetConfirmationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { slip: SlipResponse }
  ) {}

  close(confirm: boolean) {
    this.dialogRef.close(confirm);
  }

  calculatePotentialWinnings(): number {
    const oddsProduct = this.data.slip.odds.reduce((total, odd) => total * odd.oddValue, 1);
    return this.data.slip.amount * oddsProduct;
  }
}
