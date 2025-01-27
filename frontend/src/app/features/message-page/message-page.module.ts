import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MessagePageComponent} from "./message-page.component";
import {SharedModule} from "../../shared/shared.module";
import {MessagePageRoutingModule} from "./message-page-routing.module";



@NgModule({
  declarations: [
    MessagePageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    MessagePageRoutingModule,
  ]
})
export class MessagePageModule { }
