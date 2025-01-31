

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
  MatDatepickerToggle, MatDateRangeInput, MatDateRangePicker
} from "@angular/material/datepicker";
import {MatOption, MatSelect, MatSelectTrigger} from "@angular/material/select";
import {MatChip, MatChipsModule} from "@angular/material/chips";
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatRippleLoader} from "@angular/material/core";
import {MatDivider} from "@angular/material/divider";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogModule,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatMenu, MatMenuTrigger} from "@angular/material/menu";
import {MatProgressBarModule} from "@angular/material/progress-bar";




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
  MatProgressSpinnerModule,
  MatDivider,
  MatTabGroup,
  MatTab,
  MatChip,
  MatDialogClose,
  MatDialogContent,
  MatDialogActions,
  MatDialogTitle,
  MatSelectTrigger,
  MatDateRangePicker,
  MatDateRangeInput,
  MatMenu,
  MatMenuTrigger,
  MatDialogModule,
  MatProgressBarModule
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
