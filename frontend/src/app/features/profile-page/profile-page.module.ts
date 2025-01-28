import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ProfilePageComponent} from "./profile-page.component";
import {SharedModule} from "../../shared/shared.module";
import {ProfilePageRoutingModule} from "./profile-page-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    ProfilePageComponent
  ],
    imports: [
        CommonModule,
        SharedModule,
        ProfilePageRoutingModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class ProfilePageModule { }
