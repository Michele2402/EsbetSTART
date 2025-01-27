import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {EventsAdminPageComponent} from "./events-admin-page.component";


const routes: Routes = [
  {
    path: '',
    component: EventsAdminPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventsAdminPageRoutingModule { }
