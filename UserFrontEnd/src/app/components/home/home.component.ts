import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {HomeConfig} from "../../shared/entity/home-config";
import {Device} from "../../shared/entity/device";
import {WebSocketAPI} from "../../service/websocket";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

    @Input() homeConfig: HomeConfig;

    socket = null;

    constructor() {
    }

    ngOnInit(): void {
        console.log(this.homeConfig);
        this.socket = new WebSocketAPI();
        this.socket.connect(this.homeConfig.id);
    }

    onAddNewDevice($event: Device) {
        this.homeConfig.devices.push($event);
    }

    sendControlToServer($event) {
        let deviceControlRequest = {cmd:"control",homeId:this.homeConfig.id, deviceId: $event.id, state: $event.state};
        this.socket.send(deviceControlRequest);
    }

    ngOnDestroy(): void {
        console.log("Disconnecting websocket....", this.homeConfig.id);
        this.socket._disconnect();
    }
}
