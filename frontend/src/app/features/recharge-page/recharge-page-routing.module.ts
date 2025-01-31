import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {RechargePageComponent} from "./recharge-page.component";



const routes: Routes = [
  {
    path: '',
    component: RechargePageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RechargePageRoutingModule { }
