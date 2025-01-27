import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {CustomerServiceTicketComponent} from "./customer-service-ticket.component";


const routes: Routes = [
  {
    path: '',
    component: CustomerServiceTicketComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerServiceTicketRoutingModule { }
