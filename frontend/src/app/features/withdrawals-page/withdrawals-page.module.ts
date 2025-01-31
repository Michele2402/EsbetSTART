import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {WithdrawalsPageComponent} from "./withdrawals-page.component";
import {SharedModule} from "../../shared/shared.module";
import {WithdrawalsPageRoutingModule} from "./withdrawals-page-routing.module";



@NgModule({
  declarations: [
    WithdrawalsPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    WithdrawalsPageRoutingModule,
  ]
})
export class WithdrawalsPageModule { }
