

import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {MatButton, MatButtonModule, MatIconButton} from "@angular/material/button";
import {MatCard, MatCardModule} from "@angular/material/card";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatError, MatFormField, MatFormFieldModule, MatLabel, MatPrefix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatList, MatListItem} from "@angular/material/list";
import {
  MatDatepicker,
  MatDatepickerActions, MatDatepickerApply, MatDatepickerCancel,
  MatDatepickerInput,
  MatDatepickerToggle
} from "@angular/material/datepicker";
import {MatOption, MatSelect} from "@angular/material/select";
import {MatChip, MatChipsModule} from "@angular/material/chips";
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatRippleLoader} from "@angular/material/core";




const modules = [
  MatPaginatorModule,
  MatTableModule,
  MatButtonModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatCardModule,
  MatButton,
  MatFormField,
  MatIcon,
  MatIconButton,
  MatInput,
  MatLabel,
  NgOptimizedImage,
  MatList,
  MatListItem,
  MatError,
  MatPrefix,
  MatDatepicker,
  MatDatepickerInput,
  MatDatepickerToggle,
  MatDatepickerActions,
  MatDatepickerCancel,
  MatDatepickerApply,
  MatFormFieldModule,
  MatSelect,
  MatOption,
  MatCard,
  MatChipsModule,
  MatProgressSpinnerModule
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ...modules
  ],
  exports: [
    ...modules
  ],

})
export class AngularMaterialModule { }
