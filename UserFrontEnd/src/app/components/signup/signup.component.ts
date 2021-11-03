import {Component, OnInit} from '@angular/core';
import {SignupService} from "./signup.service";

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
    email: string = '';
    name: string = '';
    password: string = '';
    password_repeat: string  = '';
    errorMsg: string[] = [];
    constructor(private signupService: SignupService) { }

    ngOnInit() {}

    onSubmit = () => {
        if(this.valid()){
            this.signupService.register(this.name, this.email, this.password).subscribe(
                resp => alert(resp.name + 'successfully registered!')
            );
        }
    }

    private valid() {
        this.errorMsg.length = 0;
        const empty = this.email.length === 0 || this.name.length === 0 || this.password.length === 0 ;
        const passwordMatch = this.password === this.password_repeat;
        if(empty){
            this.errorMsg.push('All fields are required');
        }
        if (!passwordMatch) {
            this.errorMsg.push('Password is not matching');
        }
        return !empty && passwordMatch;
    }
}
