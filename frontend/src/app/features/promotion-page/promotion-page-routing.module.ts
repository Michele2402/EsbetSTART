import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {PromotionPageComponent} from "./promotion-page.component";


const routes: Routes = [
  {
    path: '',
    component: PromotionPageComponent,
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PromotionPageRoutingModule { }
