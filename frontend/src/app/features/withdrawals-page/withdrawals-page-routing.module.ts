import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {WithdrawalsPageComponent} from "./withdrawals-page.component";



const routes: Routes = [
  {
    path: '',
    component: WithdrawalsPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WithdrawalsPageRoutingModule { }
