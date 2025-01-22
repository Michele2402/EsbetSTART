
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {CompetitionsAdminPageComponent} from "./competitions-admin-page.component";


const routes: Routes = [
  {
    path: '',
    component: CompetitionsAdminPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompetitionsAdminPageRoutingModule { }
