import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CurrentBetsPageComponent} from "./current-bets-page.component";
import {SharedModule} from "../../shared/shared.module";
import {CurrentBetsPageRoutingModule} from "./current-bets-page-routing.module";



@NgModule({
  declarations: [
    CurrentBetsPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    CurrentBetsPageRoutingModule
  ]
})
export class CurrentBetsPageModule { }
