require('dotenv').config();
const http = require('http')
const sound = require("sound-play");
const { ipcRenderer } = require('electron')
const kafka = require('./kafka/kafka-client')

const notifyServer = (id, status, homeId)=>{
    status = status === 'on'?'off':'on';
    console.log("send message to server", id, status);
    const message = {deviceId:id, deviceState:status, homeId:homeId};
    const payload = {key: homeId, value:JSON.stringify(message)};
    return kafka.sendMessage(process.env.TOPIC_STATE_CHANGE, payload);
}

const updateDevice = (data) =>{
    console.log('Got message from server: ',data);
    const deviceDom = document.getElementById(data.deviceId);
    if(deviceDom) {
        if (data.state === 'on') {
            deviceDom.setAttribute("class", "on");
        } else if (data.state === 'off') {
            deviceDom.removeAttribute("class");
        }
        // console.log(deviceDom.classList);
    } else {
        console.error('Device not found');
    }
}

async function getHomeConfig(homeId) {
    return new Promise((resolve, reject)=>{
        const url = `${process.env.URL_API_GATEWAY}/home/config/${homeId}`;
        http.get(url, (res) => {
            res.on('data', d => {
                resolve(JSON.parse(d.toString()))
            })
        }).on('error', d=>{
            reject(d.message);
        });
    });
}
window.addEventListener('DOMContentLoaded', () => {
    ipcRenderer.on('homeId', (event, message) => {
        createScreen(message);
    })
})
const replaceText = (selector, text) => {
    const element = document.getElementById(selector)
    if (element) element.innerText = text
}
const createScreen = (homeId)=>{
    console.log("Loading....",homeId);
    getHomeConfig(homeId).then((home_config)=>{
        console.log('Got home config', home_config);
        replaceText(`home_id`, home_config.name + "(" + home_config.id + ")");
        for (const device of home_config.devices) {
            addDevice(device, homeId);
        }
    }).catch(error => alert(error));
    kafka.listenToMessage(`${process.env.TOPIC_CONTROL}.${homeId}`, homeId, updateDevice);

    // const home_config= {
    //     id:123,
    //     name: "sweet home",
    //     devices:[
    //         {id:"d1", type:"switch", name:"Living Room Light", state:"on"},
    //         {id:"d2", type:"switch", name:"Christmas Tree Light", state:"off"},
    //         {id:"d3", type:"switch", name:"Bathroom Light", state:"on"},
    //         {id:"d4", type:"switch", name:"Office Light", state:"off"},
    //     ]
    // }

}
const addDevice = (device, homeId) => {
    const element = document.getElementById("devices_area")
    const newSection = document.createElement("section");
    const newP = document.createElement("p")
    newP.innerText = device.name;
    newSection.setAttribute("class", "switch");
    const newDevice = document.createElement("a");
    newDevice.setAttribute("id", device.id);
    newDevice.setAttribute("href", "#");
    newDevice.text="ON";
    newDevice.addEventListener("click", function (e) {
        //          sound.play("click2.mp3");
        notifyServer(device.id, this.className, homeId).then(()=>this.classList.toggle("on"));

        e.preventDefault();
    });
    if(device.state === 'on') newDevice.setAttribute("class","on");
    const newSpan = document.createElement("span");
    newSection.appendChild(newP);
    newSection.appendChild(newDevice);
    newSection.appendChild(newSpan);
    element.appendChild(newSection);
}

