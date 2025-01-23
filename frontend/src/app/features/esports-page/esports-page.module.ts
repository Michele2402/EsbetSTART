import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {EsportsPageComponent} from "./esports-page.component";
import {SharedModule} from "../../shared/shared.module";
import {EsportsRoutingModule} from "./esports-page-routing.module";



@NgModule({
  declarations: [
    EsportsPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    EsportsRoutingModule
  ]
})
export class EsportsPageModule { }
