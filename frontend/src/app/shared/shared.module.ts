import { NgModule } from '@angular/core';
import {CommonModule} from '@angular/common';
import {AngularMaterialModule} from "./modules/angular-material/angular-material.module";
import {FooterComponent} from "./components/footer/footer.component";
import {NavbarComponent} from "./components/navbar/navbar.component";
import {FormsModule} from "@angular/forms";
import {LeftbarComponent} from "./components/leftbar/leftbar.component";
import {RouterLink} from "@angular/router";



@NgModule({
  declarations: [
    NavbarComponent,
    FooterComponent,
    LeftbarComponent
  ],
    imports: [
        CommonModule,
        AngularMaterialModule,
        FormsModule,
        RouterLink,
    ],
  exports: [
    AngularMaterialModule,
    FooterComponent,
    NavbarComponent,
    LeftbarComponent,
  ]
})
export class SharedModule { }
