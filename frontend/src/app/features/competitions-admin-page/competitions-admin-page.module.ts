import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedModule} from "../../shared/shared.module";
import {CompetitionsAdminPageRoutingModule} from "./competitions-admin-page-routing-module";
import {CompetitionsAdminPageComponent} from "./competitions-admin-page.component";
import {CompetitionListComponent} from "./components/competition-list/competition-list.component";



@NgModule({
  declarations: [
    CompetitionsAdminPageComponent,
    CompetitionListComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    CompetitionsAdminPageRoutingModule
  ]
})
export class CompetitionsAdminPageModule { }
