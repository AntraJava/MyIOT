import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {HomeConfigService} from "./home-config.service";
import {HomeConfig} from "../../shared/entity/home-config";
import {NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Subscription} from 'rxjs/Subscription';
import {BusService} from '../../service/bus.service';

@Component({
  selector: 'app-control-board',
  templateUrl: './control-board.component.html',
  styleUrls: ['./control-board.component.css']
})
export class ControlBoardComponent implements OnInit {

  homeList: HomeConfig[] = [];
  subscription: Subscription;

  constructor(private authService: AuthService, private homeService: HomeConfigService,
              private modalService: NgbModal, private busService: BusService) { }

  ngOnInit(): void {
    // this.homeService.loadHome(this.authService.getCurrentUser()).subscribe(
    //     homeList =>{
    //       console.log(homeList);}
    // );
    this.subscription = this.busService.getMessage().subscribe(message => { console.log(message);
    if(message.text === 'new home') { this.addHome()}});
  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  addHome() {
    this.modalService.open(AddHomeModalContent);
  }
}

@Component({
  selector: 'app-modal-add-home',
  template: `
    <div class="modal-header">
        <h5 class="modal-title text-center">New Home</h5>
        <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
<!--        <span aria-hidden="true">&times;</span>-->
        </button>
    </div>
    <div class="modal-body text-center"> 
          <label>Home Name</label>
          <input name="homeName" type="text" class="form-control" placeholder="Display Name" [(ngModel)]="name">
    </div>
    <div class="modal-body text-center">
      <label>Address</label>
      <input name="address" type="text" class="form-control" placeholder="Location Info" [(ngModel)]="locationInfo" autocomplete="off">
    </div>
    <div class="modal-footer">
        <div class="left-side">
            <button type="button" class="btn btn-default btn-link" (click)="activeModal.close('Close click')">Cancel</button>
        </div>
        <div class="divider"></div>
        <div class="right-side">
            <button type="button" class="btn btn-danger btn-link" (click)="addDevice()">Add</button>
        </div>
    </div>
    `
})
export class AddHomeModalContent {
  name:string;
  locationInfo:string;
  //ownerId;
  constructor(public activeModal: NgbActiveModal) {
  }

  addDevice() {
    this.activeModal.dismiss();
  }
}
