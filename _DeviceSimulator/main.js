const { app, BrowserWindow } = require('electron')
const path = require('path')
const prompt = require('electron-prompt');

function createWindow (homeId) {
    // Create the browser window.
    const mainWindow = new BrowserWindow({
        width: 600,
        height: 850,
        webPreferences: {
            preload: path.join(__dirname, 'preload.js'),
            devTools: true,
            nodeIntegration: true,
            contextIsolation: false,
            enableRemoteModule: true,
        }
    })
    mainWindow.loadFile('index.html')
   // mainWindow.removeMenu();
    mainWindow.webContents.on('did-finish-load', () => {
        mainWindow.webContents.send('homeId', homeId);
    })
    // mainWindow.webContents.openDevTools()
    return mainWindow;
}

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
        if (BrowserWindow.getAllWindows().length === 0) createWindow()
    })
})

// Quit when all windows are closed, except on macOS. There, it's common
// for applications and their menu bar to stay active until the user quits
// explicitly with Cmd + Q.
app.on('window-all-closed', function () {
    if (process.platform !== 'darwin') app.quit()
})