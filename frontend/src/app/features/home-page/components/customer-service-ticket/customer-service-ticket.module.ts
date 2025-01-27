import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CustomerServiceTicketComponent} from "./customer-service-ticket.component";
import {SharedModule} from "../../../../shared/shared.module";
import {CustomerServiceTicketRoutingModule} from "./customer-service-ticket-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    CustomerServiceTicketComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    CustomerServiceTicketRoutingModule,
    ReactiveFormsModule,
    FormsModule,
  ]
})
export class CustomerServiceTicketModule { }
