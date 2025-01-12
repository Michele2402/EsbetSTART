import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HomePageComponent} from "./home-page.component";
import {HomePageRoutingModule} from "./home-page-routing.module";
import {SharedModule} from "../../shared/shared.module";
import {LeftbarComponent} from "./components/leftbar/leftbar.component";
import {BannerComponent} from "./components/banner/banner.component";

@NgModule({
  declarations: [
    HomePageComponent,
    LeftbarComponent,
    BannerComponent
  ],
  imports: [
    CommonModule,
    HomePageRoutingModule,
    SharedModule
  ]
})
export class HomePageModule { }
