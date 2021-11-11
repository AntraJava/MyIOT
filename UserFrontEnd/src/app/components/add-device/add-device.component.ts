import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {DeviceService} from "../device/device.service";
import {Device} from "../../shared/entity/device";

@Component({
  selector: 'app-add-device',
  templateUrl: './add-device.component.html',
  styleUrls: ['./add-device.component.css']
})
export class AddDeviceComponent implements OnInit {

  @Input() homeId:string;

  @Output() newDevice: EventEmitter<Device> = new EventEmitter<Device>();

  constructor(private modalService: NgbModal, private deviceService:DeviceService) { }

  ngOnInit(): void {
  }

  addNew(){
    this.modalService.open(AddDeviceModalContent).result.then(result => {
      if (result) {
        result.homeId = this.homeId;
        this.deviceService.addDevice(result).subscribe(
            device => this.newDevice.emit(device)
        );
      }
    }).catch(console.error);
  }
}
@Component({
  selector: 'app-modal-add-device',
  template: `
    <div class="modal-header">
        <h5 class="modal-title text-center">New Device</h5>
        <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss()">
<!--        <span aria-hidden="true">&times;</span>-->
        </button>
    </div>
    <div class="modal-body text-center"> 
          <label>Device Serial Number</label>
            <input name="serialNum" type="text" class="form-control" placeholder="Device Serial Number" [(ngModel)]="serialNum">
    </div> 
    <div class="modal-body text-center"> 
          <label>Device Name</label>
            <input name="deviceName" type="text" class="form-control" placeholder="Device Name" [(ngModel)]="deviceName">
    </div>
    <div class="modal-footer">
        <div class="left-side">
            <button type="button" class="btn btn-default btn-link" (click)="activeModal.close()">Cancel</button>
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
  deviceName: string = '';
  constructor(public activeModal: NgbActiveModal) {}

  addDevice() {
    if (this.serialNum.length > 0 && this.deviceName.length > 0) {
      let device = {serialNum: this.serialNum, name:this.deviceName};
      this.activeModal.close(device)
    }
  }
}