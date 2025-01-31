import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DepositsPageComponent} from "./deposits-page.component";



const routes: Routes = [
  {
    path: '',
    component: DepositsPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DepositsPageRoutingModule { }
