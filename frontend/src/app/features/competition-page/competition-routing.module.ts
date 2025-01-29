import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {CompetitionPageComponent} from "./competition-page.component";


const routes: Routes = [
  {
    path: '',
    component: CompetitionPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompetitionRoutingModule { }
