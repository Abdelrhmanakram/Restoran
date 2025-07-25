import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../../service/product.service";
import {Product} from "../../../model/product";
import {ActivatedRoute, Router} from "@angular/router";
import {DEBUG} from "@angular/compiler-cli/src/ngtsc/logging/src/console_logger";
import {CartService} from "../../../service/cart.service";
import {CartOrder} from "../../../model/cart-order";
import {CategoryService} from "../../../service/category.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit{

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      value =>  this.finalProduct(this.pageNumber)
    )

  }

  constructor(private productService: ProductService , private activatedRoute: ActivatedRoute , private cartService: CartService , private router: Router) { }
  pageNumber : number = 1;
  pageSize: number = 10;
  collectionSize: number ;
  product: Product[] = [];
  messageAr: string = '';
  messageEn: string = '';

  isEditMode: boolean = false;
  selectedProduct: Product = new Product();

  finalProduct(pageNo){
    let categoryId = this.activatedRoute.snapshot.paramMap.has("id");
    let keyExists = this.activatedRoute.snapshot.paramMap.has("key");
    if(categoryId){
      let id = this.activatedRoute.snapshot.paramMap.get("id");
      this.loadProductByCategoryId(id , pageNo -1 )
    } else if(keyExists && this.activatedRoute.snapshot.paramMap.get("key") !== ''){
      let key = this.activatedRoute.snapshot.paramMap.get("key");
      this.searchProducts(key , pageNo -1)
    }else {
      this.loadAllProducts(pageNo -1)
    }
  }


  loadAllProducts(pageNo){
    this.productService.getAllProducts(pageNo, this.pageSize).subscribe(
      response => {
        //@ts-ignore
        this.product = response.products;
        //@ts-ignore
        this.collectionSize = response.totalProducts;
      }
    )
  }

  loadProductByCategoryId(categoryId , pageNo ){
    this.productService.getProductsByCategoryId(categoryId ,pageNo , this.pageSize).subscribe(
      response => {
        //@ts-ignore
        this.product = response.products;
        //@ts-ignore
        this.collectionSize = response.totalProducts;
        if (response.length === 0) {
          this.messageAr = '🌟 ترقبوا! شيء مذهل قادم قريبًا. 🚀';
          this.messageEn = '🌟 Stay tuned! Something amazing is coming soon. 🚀';
        }
      }
    )
  }

  searchProducts(key , pageNo ){
    this.productService.searchProducts(key ,pageNo , this.pageSize).subscribe(
      response => {
        //@ts-ignore
        this.product = response.products;

        //@ts-ignore
        this.collectionSize = response.totalProducts;
      }
    )
  }

  doing(){
    this.finalProduct(this.pageNumber)
  }

  addproduct(pro: Product) {
    let order = new CartOrder(pro);

    this.cartService.addProductToCart(order);
  }

  // Create Product Method
  createProduct(product: Product) {
    this.productService.createProduct(product).subscribe(
      (response) => {
        console.log('Product created successfully:', response);
        this.loadAllProducts(this.pageNumber);  // Reload product list after creation
      },
      (error) => {
        console.error('Error creating product:', error);
      }
    );
  }

  // Update Product Method
  updateProduct() {
    if (this.isEditMode && this.selectedProduct.id) {
      this.productService.updateProduct(this.selectedProduct.id, this.selectedProduct).subscribe(
        (response) => {
          console.log('Product updated successfully:', response);
          this.loadAllProducts(this.pageNumber);  // Reload product list after update
          this.resetForm();
         // Reset the form after update
        },
        (error) => {
          console.error('Error updating product:', error);
        }
      );
    }
  }

  openUpdateModal(product: Product) {
    this.isEditMode = true;
    this.selectedProduct = { ...product };
    this.router.navigateByUrl("/product/update/:id");
  }

  resetForm() {
    this.selectedProduct = new Product();
    this.isEditMode = false;
  }

  getUserRole(): string | null {
    const token = sessionStorage.getItem('token');
    if (token) {
      try {
        const decodedToken = this.decodeToken(token);

        if (decodedToken?.roles && decodedToken.roles.length > 0) {
          return decodedToken.roles[0]?.code || null;
        }

        return null;
      } catch (error) {
        console.error('Error decoding token:', error);
        return null;
      }
    }
    return null;
  }


  decodeToken(token: string): any {
    try {
      const base64Url = token.split('.')[1];
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      const jsonPayload = decodeURIComponent(atob(base64).split('').map((c) => {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
      }).join(''));

      return JSON.parse(jsonPayload);
    } catch (error) {
      throw new Error('Invalid token format');
    }
  }
}
