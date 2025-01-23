import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedModule} from "../../shared/shared.module";
import {CompetitionsAdminPageRoutingModule} from "./competitions-admin-page-routing-module";
import {CompetitionsAdminPageComponent} from "./competitions-admin-page.component";
import {CompetitionListComponent} from "./components/competition-list/competition-list.component";
import {AddCompetitionComponent} from "./components/add-competition/add-competition.component";



@NgModule({
  declarations: [
    CompetitionsAdminPageComponent,
    CompetitionListComponent,
    AddCompetitionComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    CompetitionsAdminPageRoutingModule
  ]
})
export class CompetitionsAdminPageModule { }
