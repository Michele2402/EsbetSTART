
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {EventPageComponent} from "./event-page.component";

const routes: Routes = [
  {
    path: '',
    component: EventPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventRoutingModule { }
