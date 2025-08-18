# Electron Cheat Sheet

## 📋 개요

Electron은 JavaScript, HTML, CSS를 사용하여 크로스 플랫폼 데스크톱 애플리케이션을 구축할 수 있는 오픈소스 프레임워크입니다.

## 🚀 설치 및 초기 설정

### 설치

```bash
npm init -y
npm install electron --save-dev
```

### 기본 프로젝트 구조

```
my-electron-app/
├── package.json
├── main.js          # 메인 프로세스
├── preload.js       # 프리로드 스크립트
└── index.html       # 렌더러 프로세스
```

### package.json 설정

```json
{
  "main": "main.js",
  "scripts": {
    "start": "electron .",
    "dev": "electron . --inspect=9229"
  }
}
```

## 🏗️ 메인 프로세스 (main.js)

### 기본 템플릿

```javascript
const { app, BrowserWindow } = require('electron');
const path = require('path');

const createWindow = () => {
  const mainWindow = new BrowserWindow({
    width: 1200,
    height: 800,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      contextIsolation: true,
      nodeIntegration: false
    }
  });

  mainWindow.loadFile('index.html');
};

app.whenReady().then(() => {
  createWindow();

  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) {
      createWindow();
    }
  });
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit();
  }
});
```

### BrowserWindow 옵션

```javascript
new BrowserWindow({
  width: 800,
  height: 600,
  minWidth: 400,
  minHeight: 300,
  resizable: true,
  fullscreen: false,
  show: false,              // 처음에 숨김
  frame: false,             // 프레임 없는 창
  transparent: true,        // 투명 배경
  alwaysOnTop: true,        // 항상 위에
  skipTaskbar: true,        // 작업표시줄에서 숨김
  icon: 'icon.png',         // 아이콘 설정
  webPreferences: {
    preload: path.join(__dirname, 'preload.js'),
    contextIsolation: true,
    nodeIntegration: false,
    sandbox: true
  }
});
```

## 🔧 프리로드 스크립트 (preload.js)

### 기본 API 노출

```javascript
const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('electronAPI', {
  // IPC 통신
  sendMessage: (message) => ipcRenderer.invoke('send-message', message),
  onMenuClick: (callback) => ipcRenderer.on('menu-click', callback),
  
  // 파일 시스템
  readFile: (filepath) => ipcRenderer.invoke('read-file', filepath),
  writeFile: (filepath, data) => ipcRenderer.invoke('write-file', filepath, data),
  
  // 시스템 정보
  getPlatform: () => process.platform,
  getVersion: () => process.versions.electron,
  
  // 창 제어
  minimizeWindow: () => ipcRenderer.send('minimize-window'),
  closeWindow: () => ipcRenderer.send('close-window')
});
```

## 🔄 IPC (Inter-Process Communication)

### 메인 → 렌더러

```javascript
// main.js
const { ipcMain } = require('electron');

ipcMain.handle('get-app-version', () => {
  return app.getVersion();
});

ipcMain.on('minimize-window', () => {
  BrowserWindow.getFocusedWindow()?.minimize();
});
```

### 렌더러 → 메인

```javascript
// renderer.js (웹페이지)
const version = await window.electronAPI.getVersion();
window.electronAPI.minimizeWindow();
```

### 양방향 통신

```javascript
// main.js - 이벤트 보내기
mainWindow.webContents.send('update-data', newData);

// preload.js - 이벤트 받기
contextBridge.exposeInMainWorld('electronAPI', {
  onUpdateData: (callback) => {
    ipcRenderer.on('update-data', (_event, data) => callback(data));
  }
});

// renderer.js - 콜백 등록
window.electronAPI.onUpdateData((data) => {
  console.log('Data updated:', data);
});
```

## 📁 파일 시스템 접근

### 파일 읽기/쓰기

```javascript
// main.js
const fs = require('fs').promises;
const { dialog } = require('electron');

ipcMain.handle('read-file', async () => {
  const result = await dialog.showOpenDialog({
    properties: ['openFile'],
    filters: [{ name: 'Text Files', extensions: ['txt'] }]
  });
  
  if (!result.canceled) {
    return await fs.readFile(result.filePaths[0], 'utf-8');
  }
});

ipcMain.handle('save-file', async (event, data) => {
  const result = await dialog.showSaveDialog({
    filters: [{ name: 'Text Files', extensions: ['txt'] }]
  });
  
  if (!result.canceled) {
    await fs.writeFile(result.filePath, data);
  }
});
```

## 🍽️ 메뉴 관리

### 애플리케이션 메뉴

```javascript
const { Menu } = require('electron');

const template = [
  {
    label: 'File',
    submenu: [
      {
        label: 'New',
        accelerator: 'CmdOrCtrl+N',
        click: () => console.log('New file')
      },
      {
        label: 'Open',
        accelerator: 'CmdOrCtrl+O',
        click: async () => {
          // 파일 열기 로직
        }
      },
      { type: 'separator' },
      {
        label: 'Exit',
        accelerator: process.platform === 'darwin' ? 'Cmd+Q' : 'Ctrl+Q',
        click: () => app.quit()
      }
    ]
  }
];

const menu = Menu.buildFromTemplate(template);
Menu.setApplicationMenu(menu);
```

### 컨텍스트 메뉴

```javascript
// main.js
const { Menu } = require('electron');

const contextMenu = Menu.buildFromTemplate([
  { label: 'Cut', role: 'cut' },
  { label: 'Copy', role: 'copy' },
  { label: 'Paste', role: 'paste' }
]);

// 우클릭 시 표시
mainWindow.webContents.on('context-menu', (event, params) => {
  contextMenu.popup();
});
```

## 🔔 알림 (Notifications)

### 시스템 알림

```javascript
// main.js
const { Notification } = require('electron');

function showNotification(title, body) {
  new Notification({
    title: title,
    body: body,
    icon: 'icon.png'
  }).show();
}

ipcMain.on('show-notification', (event, title, body) => {
  showNotification(title, body);
});
```

## 🏪 스토어 관리

### electron-store 사용

```bash
npm install electron-store
```

```javascript
// main.js
const Store = require('electron-store');
const store = new Store();

ipcMain.handle('store-get', (event, key) => {
  return store.get(key);
});

ipcMain.handle('store-set', (event, key, value) => {
  store.set(key, value);
});

ipcMain.handle('store-delete', (event, key) => {
  store.delete(key);
});
```

## 🚀 빌드 및 배포

### Electron Builder 설정

```bash
npm install electron-builder --save-dev
```

```json
// package.json
{
  "build": {
    "appId": "com.example.myapp",
    "productName": "My App",
    "directories": {
      "output": "dist"
    },
    "files": [
      "main.js",
      "preload.js",
      "index.html",
      "package.json"
    ],
    "win": {
      "target": "nsis",
      "icon": "icon.ico"
    },
    "mac": {
      "target": "dmg",
      "icon": "icon.icns"
    },
    "linux": {
      "target": "AppImage",
      "icon": "icon.png"
    }
  },
  "scripts": {
    "build": "electron-builder",
    "build-win": "electron-builder --win",
    "build-mac": "electron-builder --mac",
    "build-linux": "electron-builder --linux"
  }
}
```

## 🛡️ 보안 체크리스트

### 필수 보안 설정

```javascript
// webPreferences 보안 설정
webPreferences: {
  nodeIntegration: false,        // ✅ 비활성화
  contextIsolation: true,        // ✅ 활성화
  sandbox: true,                 // ✅ 활성화 (권장)
  preload: path.join(__dirname, 'preload.js'),
  webSecurity: true              // ✅ 활성화 (기본값)
}
```

### Content Security Policy

```html
<!-- index.html -->
<meta http-equiv="Content-Security-Policy" content="
  default-src 'self';
  script-src 'self' 'unsafe-inline';
  style-src 'self' 'unsafe-inline';
  img-src 'self' data:;
">
```

## 🐛 디버깅

### 개발자 도구

```javascript
// 개발 모드에서 DevTools 자동 열기
if (process.env.NODE_ENV === 'development') {
  mainWindow.webContents.openDevTools();
}
```

### 로깅

```javascript
// main.js에서 로그
const log = require('electron-log');
log.info('Application started');

// renderer에서 로그 (preload를 통해)
contextBridge.exposeInMainWorld('electronAPI', {
  log: (message) => ipcRenderer.send('log', message)
});
```

## 📚 유용한 라이브러리

- **electron-store**: 데이터 저장
- **electron-builder**: 앱 빌드 및 배포
- **electron-updater**: 자동 업데이트
- **electron-log**: 로깅 시스템
- **spectron**: E2E 테스팅
- **electron-reload**: 개발 시 핫 리로드

## 🎯 모범 사례

1. **보안 우선**: contextIsolation 활성화, nodeIntegration 비활성화
2. **프로세스 분리**: 메인과 렌더러 프로세스 역할 명확히 구분
3. **메모리 관리**: 창 닫기 시 적절한 정리
4. **에러 핸들링**: 모든 비동기 작업에 에러 처리
5. **성능 최적화**: 필요한 모듈만 로드
6. **사용자 경험**: 로딩 상태, 진행률 표시

## 📱 플랫폼별 고려사항

### Windows

- NSIS 인스톨러 사용
- 시작 메뉴 등록
- 레지스트리 설정

### macOS

- DMG 배포
- 코드 서명 필요
- 알림 권한 요청

### Linux

- AppImage, Snap, deb 패키지
- 데스크톱 엔트리 파일
- 의존성 관리