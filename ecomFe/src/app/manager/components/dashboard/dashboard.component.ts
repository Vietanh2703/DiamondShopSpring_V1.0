import {Component, OnInit} from '@angular/core';
import {ManagerService} from "../../services/manager.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit{
      products: any[] = [];
      searchProductForm!: FormGroup;

      constructor(private managerService: ManagerService,
                  private fb: FormBuilder,
                  private snackBar: MatSnackBar
      ) {
      }

      ngOnInit(): void {
        this.getAllProducts();
        this.searchProductForm = this.fb.group({
          title: [null, [Validators.minLength(1)]]
        });
      }
      getAllProducts(): void {
        this.products = [];
        this.managerService.getAllProducts().subscribe(response => {
          response.forEach(element => {
            element.processImg = 'data:image/jpeg;base64,' + element.byteImg;
            this.products.push(element);
          });
        })
      }

      submitForm(): void {
        this.products = [];
        const title = this.searchProductForm.get('title')!.value;
        this.managerService.getAllProductsByName(title).subscribe(response => {
          response.forEach(element => {
            element.processImg = 'data:image/jpeg;base64,' + element.byteImg;
            this.products.push(element);
          });
        })
      }

      deleteProduct(productId:any) {
        this.managerService.deleteProduct(productId).subscribe(response => {
          if(response.status === 200) {
            this.snackBar.open('Product deleted successfully', 'Close', {
              duration: 5000,
            });
            this.getAllProducts();
            }
          else {
            this.snackBar.open(response.message, 'Close', {
              duration: 5000,
            });
            }
          })
        }
}
