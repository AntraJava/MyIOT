const http = require('https')

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
getCountry1().then((d)=>console.log("Load state from server............" + d));
