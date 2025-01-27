import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HomePageComponent} from "./home-page.component";
import {HomePageRoutingModule} from "./home-page-routing.module";
import {SharedModule} from "../../shared/shared.module";
import {BannerComponent} from "./components/banner/banner.component";
import {CustomerServiceComponent} from "./components/customer-service/customer-service.component";
import {CustomerServiceDialogComponent} from "./components/customer-service-dialog/customer-service-dialog.component";

@NgModule({
  declarations: [
    HomePageComponent,
    BannerComponent,
    CustomerServiceComponent,
    CustomerServiceDialogComponent,
      ],
  imports: [
    CommonModule,
    HomePageRoutingModule,
    SharedModule
  ]
})
export class HomePageModule { }
