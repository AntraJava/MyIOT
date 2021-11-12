import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {environment} from "../../environments/environment";

export class WebSocketAPI {

    webSocketEndPoint: string = environment.baseUrl+'/ws';
    topic: string = "/queue/home";
    stompClient: any;
    private hId:string;
    constructor() {}

    connect(homeId:string) {
        console.log(`Initialize WebSocket Connection to ${homeId}`);
        this.hId = homeId;
        let ws = new SockJS(this.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
        const _this = this;
        _this.stompClient.connect({token:localStorage.getItem('iotToken')}, function (frame) {
            _this.stompClient.subscribe(_this.topic+"/"+homeId, function (sdkEvent) {
                _this.onMessageReceived(sdkEvent);
            });
            //_this.stompClient.reconnect_delay = 2000;
        }, this.errorCallBack);
    };

    _disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    // on error, schedule a reconnection attempt
    errorCallBack(error) {
        console.log("errorCallBack -> " + error)
        setTimeout(() => {
            this.connect(this.hId);
        }, 5000);
    }

    /**
     * Send message to sever via web socket
     * @param {*} message
     */
    send(message) {
        console.log("calling logout api via web socket");
        this.stompClient.send("/app/control", {token:localStorage.getItem('iotToken')}, JSON.stringify(message));
    }

    onMessageReceived(message) {
        console.log("Message Recieved from Server :: " + message);
        // this.appComponent.handleMessage(JSON.stringify(message.body));
    }
}