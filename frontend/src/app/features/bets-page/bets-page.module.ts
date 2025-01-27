import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BetsPageComponent} from "./bets-page.component";
import {SharedModule} from "../../shared/shared.module";
import {BetsPageRoutingModule} from "./bets-page-routing.module";



@NgModule({
  declarations: [
    BetsPageComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    BetsPageRoutingModule
  ]
})
export class BetsPageModule { }
