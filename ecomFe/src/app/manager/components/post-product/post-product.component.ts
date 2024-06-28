import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {ManagerService} from "../../services/manager.service";


@Component({
  selector: 'app-post-product',
  templateUrl: './post-product.component.html',
  styleUrl: './post-product.component.scss'
})
export class PostProductComponent implements OnInit{

  productForm!: FormGroup;
  listOfCategories: any[] = [];
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private managerService: ManagerService
  ) {
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage() {
    if (this.selectedFile) {
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
      }
      reader.readAsDataURL(this.selectedFile);
    } else {
      console.error('No file selected');
    }
  }

  ngOnInit(): void {
    this.productForm= this.fb.group({
      categoryId: [null, [Validators.required]],
      name: [null, [Validators.required]],
      price: [null, [Validators.required, Validators.min(1)]],
      description: [null],
    });
    this.GetAllCategories();
  }

  GetAllCategories(): void {
    this.managerService.getAllCategories().subscribe(response => {
      this.listOfCategories = response;
    })
  }

  addProduct() {
    if (this.productForm.valid) {
      const formData = new FormData();

      formData.append('name', this.productForm.value.name);
      formData.append('description', this.productForm.value.description);
      formData.append('price', this.productForm.value.price);
      formData.append('categoryId', this.productForm.value.categoryId);
      formData.append('img', this.selectedFile);
      this.managerService.addProduct(formData).subscribe(
        (response) => {
          if (response.id != null) {
            this.snackBar.open('Product added successfully', 'Close', {
              duration: 5000
            });
            this.router.navigateByUrl('/manager/dashboard');
          } else {
            this.snackBar.open(response.message, 'ERROR', {
              duration: 5000,
            });
          }
        });
    } else {
      for(const i in this.productForm.controls) {
        this.productForm.controls[i].markAsDirty();
        this.productForm.controls[i].updateValueAndValidity();
      }
    }
  }
}
