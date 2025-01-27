import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BankAccountPageComponent} from "./bank-account-page.component";
import {SharedModule} from "../../shared/shared.module";
import {BankAccountPageRoutingModule} from "./bank-account-page-routing.module";



@NgModule({
  declarations: [
    BankAccountPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    BankAccountPageRoutingModule,
  ]
})
export class BankAccountPageModule { }
