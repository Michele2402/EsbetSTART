import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginPageComponent} from "./login-page.component";
import {SharedModule} from "../../shared/shared.module";
import {LoginPageRoutingModule} from "./login-page-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    LoginPageComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    LoginPageRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class LoginPageModule { }
