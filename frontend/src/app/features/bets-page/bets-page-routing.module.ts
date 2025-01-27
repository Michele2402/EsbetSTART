import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {BetsPageComponent} from "./bets-page.component";



const routes: Routes = [
  {
    path: '',
    component: BetsPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BetsPageRoutingModule { }
