import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {environmentPaths} from "../../../environments/environment";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {

  constructor(
    private router: Router
  ) {
  }

  toTerms() {
    this.router.navigate([environmentPaths.terms_page]);
  }
}
