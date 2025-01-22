import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {EsportsAdminPageComponent} from "./esports-admin-page.component";
import {EsportsAdminPageRoutingModule} from "./esports-admin-page-routing.module";
import {SharedModule} from "../../shared/shared.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AddGameButtonComponent} from "./components/add-game-button/add-game-button.component";
import {GameTableComponent} from "./components/game-table/game-table.component";



@NgModule({
  declarations: [
    EsportsAdminPageComponent,
    AddGameButtonComponent,
    GameTableComponent
  ],
  imports: [
    CommonModule,
    EsportsAdminPageRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class EsportsAdminPageModule { }
