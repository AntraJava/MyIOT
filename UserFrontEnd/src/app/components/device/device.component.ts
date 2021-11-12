import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Device} from '../../shared/entity/device';

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
}
