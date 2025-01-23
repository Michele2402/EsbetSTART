import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {GameWithRulesResponse} from "../../../../model/response/game-response";

@Component({
  selector: 'app-add-competition',
  templateUrl: './add-competition.component.html',
  styleUrl: './add-competition.component.css'
})
export class AddCompetitionComponent implements  OnInit{

  selectedGame: GameWithRulesResponse | null = null

  ngOnInit(): void {
    this.selectedGame = JSON.parse(sessionStorage.getItem('selectedGame')!);
  }

}
