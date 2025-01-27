import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ProfilePageComponent} from "./profile-page.component";
import {SharedModule} from "../../shared/shared.module";
import {ProfilePageRoutingModule} from "./profile-page-routing.module";



@NgModule({
  declarations: [
    ProfilePageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ProfilePageRoutingModule
  ]
})
export class ProfilePageModule { }
