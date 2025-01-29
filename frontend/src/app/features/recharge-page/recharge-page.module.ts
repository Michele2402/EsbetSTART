import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RechargePageComponent} from "./recharge-page.component";
import {SharedModule} from "../../shared/shared.module";
import {RechargePageRoutingModule} from "./recharge-page-routing.module";



@NgModule({
  declarations: [
    RechargePageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RechargePageRoutingModule
  ]
})
export class RechargePageModule { }
