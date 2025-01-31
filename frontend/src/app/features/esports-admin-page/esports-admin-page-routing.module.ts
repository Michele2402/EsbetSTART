
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {EsportsAdminPageComponent} from "./esports-admin-page.component";


const routes: Routes = [
  {
    path: '',
    component: EsportsAdminPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EsportsAdminPageRoutingModule { }
