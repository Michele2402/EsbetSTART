import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CurrentBetsPageComponent} from "./current-bets-page.component";
import {SharedModule} from "../../shared/shared.module";
import {CurrentBetsPageRoutingModule} from "./current-bets-page-routing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {MatDateRangeInput} from "@angular/material/datepicker";



@NgModule({
  declarations: [
    CurrentBetsPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    CurrentBetsPageRoutingModule,
    ReactiveFormsModule,
    MatDateRangeInput
  ]
})
export class CurrentBetsPageModule { }
