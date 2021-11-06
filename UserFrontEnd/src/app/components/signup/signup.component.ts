import {Component, OnInit} from '@angular/core';
import {SignupService} from "./signup.service";
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";

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
    constructor(private signupService: SignupService, private modalService: NgbModal) { }

    ngOnInit() {

    }

    onSubmit = () => {
        if(this.valid()){
            this.signupService.register(this.name, this.email, this.password).subscribe(
                resp => this.modalService.open(SignUpModalContent),
                error => {this.errorMsg.length = 0; this.errorMsg.push(error.error.message);}
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
@Component({
    selector: 'app-modal-signup',
    template: `
    <div class="modal-header">
        <h5 class="modal-title text-center">You did it!</h5>
        <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
<!--        <span aria-hidden="true">&times;</span>-->
        </button>
    </div>
    <div class="modal-body text-center"> <h3 class="text-success font-weight-bold">Congratulations!</h3>
    </div>
    <div class="modal-footer">
        <div class="left-side">
            <button type="button" class="btn btn-default btn-link" (click)="activeModal.close('Close click')">Cancel</button>
        </div>
        <div class="divider"></div>
        <div class="right-side">
            <button type="button" class="btn btn-danger btn-link" [routerLink]="'/login'" (click)="activeModal.close('Close click')">LOGIN NOW</button>
        </div>
    </div>
    `
})
export class SignUpModalContent {
    constructor(public activeModal: NgbActiveModal) {}
}