import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  baseUrl = 'http://localhost:8085/user';
  constructor(private http: HttpClient) {}

  createAccount(name, phoneNumber, email, password): Observable<any> {
    return this.http.post(this.baseUrl + '/create-client', {name, email, phoneNumber, password}).pipe(
      map(
        response => response
      )
    )
  }

  login(email, password): Observable<any> {
    return this.http.post(this.baseUrl + '/login', {email, password}).pipe(
      map(
        response => response
      )
    )
  }

  isUserLogIn(){
    return sessionStorage.getItem('token') != null &&  sessionStorage.getItem('token') != undefined;
  }

  getUserRole(): string | null {
    const token = sessionStorage.getItem('token');
    if (token) {
      try {
        const decodedToken = this.decodeToken(token);
        return decodedToken?.role || null;
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
