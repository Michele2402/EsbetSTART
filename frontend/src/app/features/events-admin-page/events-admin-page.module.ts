import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {EventsAdminPageComponent} from "./events-admin-page.component";
import {SharedModule} from "../../shared/shared.module";
import {EventsAdminPageRoutingModule} from "./events-admin-page-routing.module";
import {AddEventComponent} from "./components/add-event/add-event.component";
import {EventListComponent} from "./components/event-list/event-list.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    EventsAdminPageComponent,
    AddEventComponent,
    EventListComponent
  ],
    imports: [
        CommonModule,
        SharedModule,
        EventsAdminPageRoutingModule,
        ReactiveFormsModule,
        FormsModule
    ]
})
export class EventsAdminPageModule { }
