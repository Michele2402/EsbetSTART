import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {BankAccountPageComponent} from "./bank-account-page.component";




const routes: Routes = [
  {
    path: '',
    component: BankAccountPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BankAccountPageRoutingModule { }
