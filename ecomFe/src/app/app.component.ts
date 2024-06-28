import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";
import {UserStorageService} from "./services/storage/user-storage.service";
import {filter} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'Diona Diamond Shop';
  showNavbar: boolean = true;
  isCustomerLoggedIn: boolean = UserStorageService.isCustomerLoggedIn();
  isManagerLoggedIn: boolean = UserStorageService.isManagerLoggedIn();

  constructor(private router: Router){}

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      this.isCustomerLoggedIn = UserStorageService.isCustomerLoggedIn();
      this.isManagerLoggedIn = UserStorageService.isManagerLoggedIn();
    })

    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.showNavbar = !['/login', '/signup'].includes(event.urlAfterRedirects);
    });
  }


  logout() {
    UserStorageService.signOut();
    this.router.navigate(['/login']);
  }
}
