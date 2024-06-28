import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {ManagerService} from "../../services/manager.service";

@Component({
  selector: 'app-post-category',
  templateUrl: './post-category.component.html',
  styleUrl: './post-category.component.scss'
})
export class PostCategoryComponent implements OnInit{
  categoryForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private managerService: ManagerService
  ) {}

  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null],
    })
  }

  addCategory(): void {

    if (this.categoryForm.valid) {
      this.managerService.addCategory(this.categoryForm.value).subscribe(
        (response) => {
          if (response.id != null) {
            this.snackBar.open('Category added successfully', 'Close', {
              duration: 5000
            });
            this.router.navigateByUrl('/manager/dashboard');
          } else {
            this.snackBar.open(response.message, 'Close', {
              duration: 5000,
              panelClass: 'error-snackbar'
            });
          }
        })
    } else {
      this.categoryForm.markAllAsTouched();
    }
  }
}
