import {Component, signal} from '@angular/core';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {


  hide = signal(true);

  clickEvent(event?: MouseEvent) {
    this.hide.set(!this.hide());
    event?.stopPropagation();
  }

}
