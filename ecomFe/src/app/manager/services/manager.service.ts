import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserStorageService} from "../../services/storage/user-storage.service";

const BASIC_URL = 'http://localhost:8080/'; // Define the base URL for the API

@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  constructor(private http: HttpClient) { }

  addCategory(dto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/manager/category', dto,{
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllCategories(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/manager',{
      headers: this.createAuthorizationHeader(),
    });
  }

  addProduct(dto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/manager/product', dto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllProducts(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/manager/products',{
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllProductsByName(name:any): Observable<any> {
    return this.http.get(BASIC_URL + `api/manager/search/${name}`,{
      headers: this.createAuthorizationHeader(),
    });
  }

  deleteProduct(productId: any): Observable<any> {
    return this.http.delete(BASIC_URL + `api/manager/products/${productId}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set('Authorization', 'Bearer ' + UserStorageService.getToken());
  }
}
