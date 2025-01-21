import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {environmentPaths} from "../../../environments/environment";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private router: Router) { }

  toHomePage() {
    this.router.navigate([environmentPaths.home_page]);
  }
}
