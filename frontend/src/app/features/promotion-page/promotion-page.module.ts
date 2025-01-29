import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PromotionPageComponent} from "./promotion-page.component";
import {SharedModule} from "../../shared/shared.module";
import {PromotionPageRoutingModule} from "./promotion-page-routing.module";
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    PromotionPageComponent
  ],
    imports: [
        CommonModule,
        SharedModule,
        PromotionPageRoutingModule,
        FormsModule,
    ]
})
export class PromotionPageModule { }
