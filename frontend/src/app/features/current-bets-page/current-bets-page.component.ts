import { Component } from '@angular/core';
import {Location} from "@angular/common";


@Component({
  selector: 'app-current-bets-page',
  templateUrl: './current-bets-page.component.html',
  styleUrl: './current-bets-page.component.css'
})
export class CurrentBetsPageComponent {

  constructor(private location: Location) {}

  goBack(): void {
    this.location.back();
  }
}
