import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Device} from '../../shared/entity/device';
import {nextState} from "../../shared/utility/devide-util";

@Component({
  selector: 'app-device',
  templateUrl: './device.component.html',
  styleUrls: ['./device.component.css']
})
export class DeviceComponent implements OnInit {

  @Input() device:Device;

  @Output() controlEvent: EventEmitter<Device> = new EventEmitter<Device>();
  constructor() { }

  ngOnInit(): void {
  }

  control() {
    this.controlEvent.emit(this.device);
  }

  nextState(status: string) {
    return nextState(status,"switch");
  }
}
