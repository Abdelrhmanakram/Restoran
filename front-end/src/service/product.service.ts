import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseUrl = 'http://localhost:8085/product';
  constructor(private http: HttpClient) { }

  getAllProducts(pageNo , pageSize): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + '/pageNo/' +pageNo +'/pageSize/' +pageSize).pipe(
      map( response => response)
    )
  }

  getProductsByCategoryId(categoryId , pageNo , pageSize): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + '/categoryId/' + categoryId + '/pageNo/' +pageNo +'/pageSize/' +pageSize).pipe(
      map( response => response)
    )
  }

  searchProducts(key , pageNo , pageSize): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + '/search/' + key + '/pageNo/' +pageNo +'/pageSize/' +pageSize).pipe(
      map( response => response)
    )
  }


  getProductById(productId: number): Observable<Product> {
      return this.http.get<Product>(`${this.baseUrl}/${productId}`).pipe(
        map( response => response)
      )
  }

  updateProduct(productId: number, formData: any): Observable<Product> {
    return this.http.put<Product>(`${this.baseUrl}/update/${productId}`, formData).pipe(
      map(response => response)
    );
  }

  createProduct(formData: any): Observable<Product> {
    return this.http.post<Product>(`${this.baseUrl}/create`, formData).pipe(
      map(response => response)
    );
  }


  deleteProduct(productId: number): Observable<void> {
      return this.http.delete<void>(`${this.baseUrl}/delete/${productId}`);
  }
}
