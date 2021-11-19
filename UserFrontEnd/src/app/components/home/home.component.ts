import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {HomeConfig} from "../../shared/entity/home-config";
import {Device} from "../../shared/entity/device";
import {WebSocketAPI} from "../../service/websocket";
import {nextState} from "../../shared/utility/device-util";

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
        this.socket.onMessageReceived = (msg) => {
            this.updateDevices(msg);
        };
        this.socket.connect(this.homeConfig.id);
    }

    ngOnDestroy(): void {
        this.socket.disconnect();
    }

    onAddNewDevice($event: Device) {
        if (this.homeConfig.devices == undefined) {
            this.homeConfig.devices = [];
        }
        this.homeConfig.devices.push($event);
    }

    sendControlToServer($event) {
        let deviceControlRequest = {cmd:"control",homeId:this.homeConfig.id, deviceId: $event.id, status: nextState($event.status)};
        this.socket.send(deviceControlRequest);
    }

    updateDevices(msg) {
        const body = JSON.parse(msg.body);
        body.forEach(ds => {
            const device = this.homeConfig.devices.find(device => device.id === ds.id);
            device.status = ds.status;
        });
    }
}
