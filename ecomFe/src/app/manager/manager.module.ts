import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ManagerRoutingModule } from './manager-routing.module';
import { ManagerComponent } from './manager.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {DemoMaterialModule} from "../DemoMaterialModule";
import { PostCategoryComponent } from './components/post-category/post-category.component';
import { PostProductComponent } from './components/post-product/post-product.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatToolbar} from "@angular/material/toolbar";
import {MatButton} from "@angular/material/button";
import {MatMenuModule} from "@angular/material/menu";


@NgModule({
  declarations: [
    ManagerComponent,
    DashboardComponent,
    PostCategoryComponent,
    PostProductComponent
  ],
  imports: [
    CommonModule,
    ManagerRoutingModule,
    HttpClientModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatToolbar,
    MatButton,
    MatMenuModule,
    ReactiveFormsModule,
    DemoMaterialModule
  ]
})
export class ManagerModule { }
