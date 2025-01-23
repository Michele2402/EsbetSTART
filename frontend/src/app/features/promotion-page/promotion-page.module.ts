import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PromotionPageComponent} from "./promotion-page.component";
import {SharedModule} from "../../shared/shared.module";
import {PromotionPageRoutingModule} from "./promotion-page-routing.module";



@NgModule({
  declarations: [
    PromotionPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    PromotionPageRoutingModule,
  ]
})
export class PromotionPageModule { }
