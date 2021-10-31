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

async function getCountry2() {
    return new Promise((resolve, reject)=>{
        http.get("https://api.nationalize.io?name=dawei", (res) => {
            res.on('data', d => {
                resolve(d.toString())
            })
        }).on('error', d=>{
            reject(d.message);
        });
    });
}

function getCountryByName(name) {
    return Promise.all([getCountry1(), getCountry2()]);

}

async function caller() {
    let result = "$";
    await getCountryByName("mayue").then((a) => result = a).catch(d => console.log(d));
    console.log('done')
    return result;
}

let apple = caller();
apple.then(console.log);
