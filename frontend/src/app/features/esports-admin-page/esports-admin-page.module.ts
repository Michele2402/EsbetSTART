import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {EsportsAdminPageComponent} from "./esports-admin-page.component";
import {EsportsAdminPageRoutingModule} from "./esports-admin-page-routing.module";



@NgModule({
  declarations: [
    EsportsAdminPageComponent
  ],
  imports: [
    CommonModule,
    EsportsAdminPageRoutingModule
  ]
})
export class EsportsAdminPageModule { }
