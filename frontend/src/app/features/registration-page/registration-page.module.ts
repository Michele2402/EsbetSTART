import { NgModule } from '@angular/core';
import {CommonModule} from '@angular/common';
import {RegistrationPageComponent} from "./registration-page.component";
import {SharedModule} from "../../shared/shared.module";
import {ReactiveFormsModule} from "@angular/forms";
import {RegistrationPageRoutingModule} from "./registration-page-routing,module";



@NgModule({
  declarations: [
    RegistrationPageComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    ReactiveFormsModule,
    RegistrationPageRoutingModule,
  ]
})
export class RegistrationPageModule { }
