import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DepositsPageComponent} from "./deposits-page.component";
import {SharedModule} from "../../shared/shared.module";
import {DepositsPageRoutingModule} from "./deposits-page-routing.module";



@NgModule({
  declarations: [
    DepositsPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    DepositsPageRoutingModule,
  ]
})
export class DepositsPageModule { }
