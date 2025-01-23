import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {EsportsPageComponent} from "./esports-page.component";


const routes: Routes = [
  {
    path: '',
    component: EsportsPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EsportsRoutingModule { }
