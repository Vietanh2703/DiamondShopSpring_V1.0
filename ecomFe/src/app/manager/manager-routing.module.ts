import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManagerComponent } from './manager.component';
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {PostCategoryComponent} from "./components/post-category/post-category.component";
import {PostProductComponent} from "./components/post-product/post-product.component";

const routes: Routes = [
  { path: '', component: ManagerComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'category', component: PostCategoryComponent },
  { path: 'product', component: PostProductComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManagerRoutingModule { }
