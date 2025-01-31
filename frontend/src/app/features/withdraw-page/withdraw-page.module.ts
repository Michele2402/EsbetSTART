import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {WithdrawPageComponent} from "./withdraw-page.component";
import {SharedModule} from "../../shared/shared.module";
import {WithdrawPageRoutingModule} from "./withdraw-page-routing.module";



@NgModule({
  declarations: [
    WithdrawPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    WithdrawPageRoutingModule,
  ]
})
export class WithdrawPageModule { }
