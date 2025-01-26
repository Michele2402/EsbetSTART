import {Component, ViewChild} from '@angular/core';
import {EventListComponent} from "./components/event-list/event-list.component";

@Component({
  selector: 'app-events-admin-page',
  templateUrl: './events-admin-page.component.html',
  styleUrl: './events-admin-page.component.css'
})
export class EventsAdminPageComponent {
  @ViewChild(EventListComponent) eventList!: EventListComponent;

  loadEvents() {
    this.eventList.loadAllEvents();
  }
}
