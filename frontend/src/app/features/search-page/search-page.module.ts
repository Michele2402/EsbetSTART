import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedModule} from "../../shared/shared.module";
import {SearchPageRoutingModule} from "./search-page-routing.module";
import {SearchPageComponent} from "./search-page.component";



@NgModule({
  declarations: [
    SearchPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    SearchPageRoutingModule
  ]
})
export class SearchPageModule { }
