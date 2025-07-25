import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Product } from "../../../model/product";
import { ProductService } from "../../../service/product.service";
import { ActivatedRoute, Router } from "@angular/router";
import { CategoryService } from "../../../service/category.service";
import { Category } from "../../../model/category";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  productForm: FormGroup;
  isEditMode = false;
  productId: number | null = null;
  categories: Category[] = [];

  @Input() product: Product | null = null;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService
  ) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', [Validators.required, Validators.maxLength(500)]],
      price: ['', [Validators.required, Validators.min(1)]],
      categoryId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadCategories();

    // Check if the component is in edit mode (for updating a product)
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.productId = +id;
        this.isEditMode = true;
        this.loadProductData();
      }
    });
  }

  // Load the list of categories to populate the category dropdown
  loadCategories() {
    this.categoryService.getAllCategory().subscribe(
      (response) => {
        this.categories = response;
      },
      (error) => {
        console.error('Error loading categories:', error);
      }
    );
  }

  // Load the product data for editing
  loadProductData() {
    if (this.productId) {
      this.productService.getProductById(this.productId).subscribe(
        (product) => {
          this.productForm.patchValue(product); // Prefill the form with product data
        },
        (error) => {
          console.error('Error loading product:', error);
        }
      );
    }
  }

  // Handle form submission (either create or update product)
  onSubmit() {
    if (this.productForm.invalid) {
      return;
    }

    const formData = this.productForm.value;

    if (this.isEditMode && this.productId) {
      // Update the existing product
      this.productService.updateProduct(this.productId, formData).subscribe(
        (updatedProduct) => {
          console.log('Product updated successfully', updatedProduct);
          this.router.navigate(['/products']);
        }
      );
    } else {
      // Create a new product
      this.productService.createProduct(formData).subscribe(
        (newProduct) => {
          console.log('Product created successfully', newProduct);
          this.router.navigate(['/products']);
        }
      );
    }
  }

  onDelete() {
    if (this.productId) {
      this.productService.deleteProduct(this.productId).subscribe(
        () => {
          console.log('Product deleted successfully');
          this.router.navigate(['/products']);
        },
        (error) => {
          console.error('Error deleting product:', error);
        }
      );
    }
  }


}
