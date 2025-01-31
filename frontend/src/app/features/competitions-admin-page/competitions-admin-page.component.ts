import {Component, ViewChild} from '@angular/core';
import {CompetitionListComponent} from "./components/competition-list/competition-list.component";

@Component({
  selector: 'app-competitions-admin-page',
  templateUrl: './competitions-admin-page.component.html',
  styleUrl: './competitions-admin-page.component.css'
})
export class CompetitionsAdminPageComponent {

  @ViewChild(CompetitionListComponent) competitionList!: CompetitionListComponent;

  loadCompetitions(): void {
    this.competitionList.loadAllCompetitions();
  }
}
