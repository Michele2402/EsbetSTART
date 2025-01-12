import { Component } from '@angular/core';
import {Router} from "@angular/router";
import{environmentPaths} from "../../environments/environment";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {
  constructor(
    private router: Router,
  ) {

  }
  toNetGraph() {
    this.router.navigate(['/' + environmentPaths.e_sports]);
  }
}
