import { Component } from '@angular/core';
import {Location} from "@angular/common";

interface Problems {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-customer-service-ticket',
  templateUrl: './customer-service-ticket.component.html',
  styleUrl: './customer-service-ticket.component.css'
})
export class CustomerServiceTicketComponent {

  problem: Problems[] = [
    {value: '0', viewValue: 'Forgot password'},
    {value: '1', viewValue: 'Blocked account'},
    {value: '2', viewValue: 'Withdrawal rejected or pending'},
    {value: '3', viewValue: 'Refund'},
    {value: '4', viewValue: 'Winnings not credited'},
    {value: '5', viewValue: 'Promotions not working'},
  ];

  onSubmit(): void {
  }

  constructor(private location: Location) {}

  goBack(): void {
    this.location.back();
  }
}
