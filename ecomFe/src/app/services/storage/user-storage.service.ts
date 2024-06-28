import { Injectable } from '@angular/core';


const TOKEN = 'ecom-token';
const USER = 'ecom-user';
@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() {
  }

  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  public saveUser(user): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): string {
    if (typeof window !== 'undefined' && window.localStorage) {
      return localStorage.getItem(TOKEN);
    }
    return null;
  }
  // get exists TOKEN from local Storage

  static getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }

  static getUserId(): string {
    const user = this.getUser();
    if(user == null) {
      return '';
    }
    return user.userId;
  }

  static getUserRole(): string {
    const user = this.getUser();
    if(user == null) {
      return '';
    }
    return user.role;
  }

  //Check if the manager is logged in
  static isManagerLoggedIn(): boolean {
    if(this.getToken() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role === 'MANAGER';
  }

  //Check if the customer is logged in
  static isCustomerLoggedIn(): boolean {
    if(this.getToken() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role === 'CUSTOMER';
  }

  static signOut(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
