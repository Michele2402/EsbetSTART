import { Component } from '@angular/core';
import {Location} from "@angular/common";

@Component({
  selector: 'app-bets-concluded-page',
  templateUrl: './bets-concluded-page.component.html',
  styleUrl: './bets-concluded-page.component.css'
})
export class BetsConcludedPageComponent {
  constructor(private location: Location) {}

  goBack(): void {
    this.location.back();
  }
}
