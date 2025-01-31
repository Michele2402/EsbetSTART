import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CompetitionPageComponent} from "./competition-page.component";
import {SharedModule} from "../../shared/shared.module";
import {CompetitionRoutingModule} from "./competition-routing.module";



@NgModule({
  declarations: [
    CompetitionPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    CompetitionRoutingModule,
  ]
})
export class CompetitionPageModule { }
