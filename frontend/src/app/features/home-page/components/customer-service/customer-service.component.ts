import { Component } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {inject} from "@angular/core";
import {CustomerServiceDialogComponent} from "../customer-service-dialog/customer-service-dialog.component";

@Component({
  selector: 'app-customer-service',
  templateUrl: './customer-service.component.html',
  styleUrl: './customer-service.component.css'
})
export class CustomerServiceComponent {

  readonly dialog = inject(MatDialog);

  openDialog() {
    this.dialog.open(CustomerServiceDialogComponent, {
      position: { bottom: '100px', right: '20px' },
      width: '300px',
    });
  }

}
