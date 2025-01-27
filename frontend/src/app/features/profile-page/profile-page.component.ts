import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrl: './profile-page.component.css'
})
export class ProfilePageComponent implements OnInit{

  profileForm = this._fb.group({
    email: ['', [Validators.required, Validators.email, Validators.maxLength(30)]],
    username: ['', [Validators.required, Validators.maxLength(30)]],
    password: [
      '',
      [
        Validators.required,
        Validators.maxLength(30),
        Validators.minLength(8),
        Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/),
      ],
    ],
  });


  constructor(
    private _fb: FormBuilder,
    ) { }


  ngOnInit(): void {
    this.onSubmit();
  }

  onSubmit(): void {
  }

}
