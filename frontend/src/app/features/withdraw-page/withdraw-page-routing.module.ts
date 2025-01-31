import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {WithdrawPageComponent} from "./withdraw-page.component";



const routes: Routes = [
  {
    path: '',
    component: WithdrawPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WithdrawPageRoutingModule { }
