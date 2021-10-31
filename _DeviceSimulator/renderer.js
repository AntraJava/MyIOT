// preload.js

// All of the Node.js APIs are available in the preload process.
// It has the same sandbox as a Chrome extension.

const http = require('https')
const sound = require("sound-play");
const { ipcRenderer } = require('electron')


async function getCountry1() {
    return new Promise((resolve, reject)=>{
        http.get("https://api.nationalize.io?name=daniel", (res) => {
            res.on('data', d => {
                resolve(d.toString())
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

    replaceText(`home_id`, homeId)

    const notifyServer = (id, status)=>{
        console.log("send message to server", id, status === ''?'off':status);
    }
    const home_config= {
        id:123,
        name: "sweet home",
        devices:[
            {id:"d1", type:"switch", name:"Living Room Light", state:"on"},
            {id:"d2", type:"switch", name:"Christmas Tree Light", state:"off"},
            {id:"d3", type:"switch", name:"Bathroom Light", state:"on"},
            {id:"d4", type:"switch", name:"Office Light", state:"off"},
        ]
    }
    const addDevice = (device) => {
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
            this.classList.toggle("on");
            //          sound.play("click2.mp3");
            notifyServer(device.id, this.className);
            e.preventDefault();
        });
        if(device.state === 'on') newDevice.setAttribute("class","on");
        const newSpan = document.createElement("span");
        newSection.appendChild(newP);
        newSection.appendChild(newDevice);
        newSection.appendChild(newSpan);
        element.appendChild(newSection);
    }
    for (const device of home_config.devices) {
        addDevice(device);
    }

}