import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HomePageComponent} from "./home-page.component";
import {HomePageRoutingModule} from "./home-page-routing.module";
import {SharedModule} from "../../shared/shared.module";
import {NavbarModule} from "../../navbar/navbar.module";



@NgModule({
  declarations: [
    HomePageComponent
  ],
  imports: [
    CommonModule,
    HomePageRoutingModule,
    SharedModule,
    NavbarModule
  ]
})
export class HomePageModule { }
