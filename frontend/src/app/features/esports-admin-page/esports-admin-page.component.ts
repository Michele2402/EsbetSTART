import {Component, ViewChild} from '@angular/core';
import {GameTableComponent} from "./components/game-table/game-table.component";


@Component({
  selector: 'app-esports-admin-page',
  templateUrl: './esports-admin-page.component.html',
  styleUrl: './esports-admin-page.component.css'
})
export class EsportsAdminPageComponent  {

  @ViewChild(GameTableComponent) gameTable!: GameTableComponent;

  loadAllGames(): void {
    this.gameTable.loadAllGames();
  }

}
