import {Component, OnInit} from '@angular/core';
import {Category} from "../../../model/category";
import {CategoryService} from "../../../service/category.service";
import {ProductService} from "../../../service/product.service";
import {Product} from "../../../model/product";
import {Router} from "@angular/router";
import {AuthService} from "../../../service/auth/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  ngOnInit(): void {
    this.loadAllCategories()
  }

  category: Category[] = [];
  product: Product[] = [];
  constructor(private categoryService: CategoryService , private  router:Router,private authService: AuthService) { }

  loadAllCategories(){
    this.categoryService.getAllCategory().subscribe(
      response => {
        this.category = response
      }
    )
  }

  search(key){
  this.router.navigateByUrl(`/search/` + key)
  }

  logOut() {
    sessionStorage.removeItem('token');
    this.router.navigateByUrl("/login")
  }

  isUserLogin(){
    return this.authService.isUserLogIn();
  }

  login() {
    this.router.navigateByUrl("/login")
  }

  signup() {
    this.router.navigateByUrl("/signup")
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
