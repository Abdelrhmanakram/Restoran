import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Cheef} from "../model/cheef";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class CheefService {

  baseUrl = 'http://localhost:8085/cheef';
  constructor(private http: HttpClient) { }

  getAllCheefs(): Observable<Cheef[]> {
    return this.http.get<Cheef[]>(this.baseUrl).pipe(
      map(
        response => response
      )
    )
  }
}
