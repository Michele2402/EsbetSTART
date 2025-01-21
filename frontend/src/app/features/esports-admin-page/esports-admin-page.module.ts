import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {EsportsAdminPageComponent} from "./esports-admin-page.component";
import {EsportsAdminPageRoutingModule} from "./esports-admin-page-routing.module";
import {SharedModule} from "../../shared/shared.module";
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    EsportsAdminPageComponent
  ],
  imports: [
    CommonModule,
    EsportsAdminPageRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class EsportsAdminPageModule { }
