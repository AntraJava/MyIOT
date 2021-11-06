import {Component, OnInit} from '@angular/core';
import {SigninService} from "./signin.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  email:string = '';
  password:string = '';
  constructor(private signinService: SigninService, private router: Router) { }

  ngOnInit() {}

  doLogIn() {
    if (this.email.trim().length > 3 && this.password.trim().length > 3) {
      this.signinService.signIn(this.email, this.password).subscribe(
          resp =>  this.router.navigate(['control']),
          error => alert("System cannot log you in.")
      );
    }

  }
}
