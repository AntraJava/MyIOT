// main.js

// Modules to control application life and create native browser window
const { app, BrowserWindow } = require('electron')
const path = require('path')
const prompt = require('electron-prompt');

function createWindow (homeId) {
    // Create the browser window.
    const mainWindow = new BrowserWindow({
        width: 600,
        height: 800,
        webPreferences: {
            preload: path.join(__dirname, 'preload.js'),
            devTools: true,
            nodeIntegration: true,
            contextIsolation: false,
            enableRemoteModule: true,
        }
    })
    // and load the index.html of the app.
    mainWindow.loadFile('index.html')
   // mainWindow.removeMenu();
    mainWindow.webContents.on('did-finish-load', () => {
        mainWindow.webContents.send('homeId', homeId);
    })
    // Open the DevTools.
    // mainWindow.webContents.openDevTools()
    return mainWindow;
}

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.whenReady().then(() => {
    prompt({
        title: 'Home Id is required',
        label: 'Please type your home id:',
        type: 'input'
    }).then((r) => {
        if(r === null) {
            console.log('user cancelled');
        } else {
            const mainWindow = createWindow(r);
        }
    }).catch(console.error);

    app.on('activate', function () {
        // On macOS it's common to re-create a window in the app when the
        // dock icon is clicked and there are no other windows open.
        if (BrowserWindow.getAllWindows().length === 0) createWindow()
    })
})

// Quit when all windows are closed, except on macOS. There, it's common
// for applications and their menu bar to stay active until the user quits
// explicitly with Cmd + Q.
app.on('window-all-closed', function () {
    if (process.platform !== 'darwin') app.quit()
})

// In this file you can include the rest of your app's specific main process
// code. You can also put them in separate files and require them here.
