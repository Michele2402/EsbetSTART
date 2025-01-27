import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {CurrentBetsPageComponent} from "./current-bets-page.component";





const routes: Routes = [
  {
    path: '',
    component: CurrentBetsPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CurrentBetsPageRoutingModule { }
