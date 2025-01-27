import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BetsConcludedPageComponent} from "./bets-concluded-page.component";
import {SharedModule} from "../../shared/shared.module";
import {BetsConcludedPageRoutingModule} from "./bets-concluded-page-routing.module";



@NgModule({
  declarations: [
    BetsConcludedPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    BetsConcludedPageRoutingModule
  ]
})
export class BetsConcludedPageModule { }
