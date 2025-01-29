import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {EventPageComponent} from "./event-page.component";
import {SharedModule} from "../../shared/shared.module";
import {EventRoutingModule} from "./event-routing.module";



@NgModule({
  declarations: [
    EventPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    EventRoutingModule
  ]
})
export class EventPageModule { }
