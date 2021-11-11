import {Component, OnInit} from '@angular/core';
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {DeviceService} from "../device/device.service";

@Component({
  selector: 'app-add-device',
  templateUrl: './add-device.component.html',
  styleUrls: ['./add-device.component.css']
})
export class AddDeviceComponent implements OnInit {

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  addNew(){
    this.modalService.open(AddDeviceModalContent);
  }
}
@Component({
  selector: 'app-modal-add-device',
  template: `
    <div class="modal-header">
        <h5 class="modal-title text-center">New Device</h5>
        <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
<!--        <span aria-hidden="true">&times;</span>-->
        </button>
    </div>
    <div class="modal-body text-center"> 
          <label>Device Serial Number</label>
            <input name="serialNum" type="text" class="form-control" placeholder="Device Serial Number" [(ngModel)]="serialNum">
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
export class AddDeviceModalContent {
  serialNum: string = '';
  constructor(public activeModal: NgbActiveModal, private deviceService:DeviceService) {}

  addDevice() {
    if (this.serialNum.length > 0) {
      this.deviceService.addDevice(this.serialNum).subscribe(
          device => this.activeModal.close(device)
      );
    }
  }
}