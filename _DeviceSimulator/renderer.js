const http = require('http')
const sound = require("sound-play");
const { ipcRenderer } = require('electron')

const notifyServer = (id, status)=>{
    console.log("send message to server", id, status === ''?'off':status);
}

async function getHomeConfig(homeId) {
    return new Promise((resolve, reject)=>{
        http.get("http://localhost:9090/home/config/123", (res) => {
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
            addDevice(device);
        }
    }).catch(error => alert(error));
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

