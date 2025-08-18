#React #React-Native #Cross-Platform

## 1. 프로젝트 생성

```Bash
npx react-native init MyApp        # React Native CLI 
npx expo init MyApp                # Expo CLI (더 간편)
```

---

## 🧱 2. 기본 구성 요소 (Core Components)

|컴포넌트|설명|
|---|---|
|`View`|기본 컨테이너 (div와 유사)|
|`Text`|텍스트 표시|
|`TextInput`|입력 필드|
|`Button`|기본 버튼|
|`TouchableOpacity`, `Pressable`|커스텀 버튼|
|`ScrollView`, `FlatList`|스크롤 가능 리스트|

jsx

복사편집

`<View>   <Text>Hello</Text>   <TextInput placeholder="입력" />   <Button title="Click" onPress={handleClick} /> </View>`

---

## 🎨 3. 스타일링

- React Native는 CSS가 아닌 `StyleSheet` 사용
    

js

복사편집

`import { StyleSheet } from 'react-native';  const styles = StyleSheet.create({   container: {     flex: 1,     backgroundColor: '#fff',     padding: 20,   }, });`

---

## 🔄 4. 상태 관리

### ✅ useState

jsx

복사편집

`const [count, setCount] = useState(0);`

### ✅ useEffect

jsx

복사편집

`useEffect(() => {   fetchData(); }, []);`

---

## 🧭 5. 네비게이션

bash

복사편집

`npm install @react-navigation/native npx expo install react-native-screens react-native-safe-area-context react-native-gesture-handler react-native-reanimated npm install @react-navigation/native-stack`

### 기본 스택 네비게이터

jsx

복사편집

`import { NavigationContainer } from '@react-navigation/native'; import { createNativeStackNavigator } from '@react-navigation/native-stack';  const Stack = createNativeStackNavigator();  export default function App() {   return (     <NavigationContainer>       <Stack.Navigator>         <Stack.Screen name="Home" component={HomeScreen}/>         <Stack.Screen name="Detail" component={DetailScreen}/>       </Stack.Navigator>     </NavigationContainer>   ); }`

---

## 🌐 6. API 호출 (axios)

bash

복사편집

`npm install axios`

js

복사편집

`import axios from 'axios';  useEffect(() => {   axios.get('https://api.example.com/data')     .then(res => setData(res.data))     .catch(err => console.error(err)); }, []);`

---

## 🗂️ 7. 상태 관리 라이브러리

|라이브러리|설명|
|---|---|
|`Context API`|소규모 앱|
|`Redux Toolkit`|대규모 상태 관리|
|`Zustand`, `Recoil`, `Jotai`|간편한 대안|

bash

복사편집

`npm install @reduxjs/toolkit react-redux`

js

복사편집

`// store.js const store = configureStore({ reducer: rootReducer });`

---

## 🧪 8. 테스트 (기본)

bash

복사편집

`npm install --save-dev jest @testing-library/react-native`

jsx

복사편집

`import { render } from '@testing-library/react-native';  test('Text 출력 테스트', () => {   const { getByText } = render(<MyComponent />);   expect(getByText('Hello')).toBeTruthy(); });`

---

## 📱 9. 플랫폼별 차이

js

복사편집

`import { Platform } from 'react-native';  if (Platform.OS === 'android') {   // 안드로이드 전용 }`

- `SafeAreaView` → iOS notch 고려
    
- `StatusBar`, `KeyboardAvoidingView` → UI 충돌 해결용
    

---

## 📦 10. 유용한 라이브러리

|라이브러리|기능|
|---|---|
|`axios`|API 통신|
|`formik + yup`|폼 처리 + 유효성|
|`react-native-vector-icons`|아이콘|
|`react-native-paper`|UI 컴포넌트|
|`react-native-device-info`|디바이스 정보|
|`expo-camera`, `expo-image-picker`|카메라 및 갤러리|

---

## 🧳 11. 배포 요약

### 안드로이드 (CLI)

bash

복사편집

`cd android && ./gradlew assembleRelease`

### iOS (Xcode)

- Xcode에서 빌드 & App Store 업로드
    

### Expo 앱 배포

bash

복사편집

`npx expo build:android npx expo build:ios`

---

## 💡 자주 묻는 면접 질문

|질문|키워드|
|---|---|
|React Native와 React의 차이점은?|네이티브 브리지, UI 컴포넌트|
|성능 최적화 방법은?|`memo`, `useCallback`, FlatList 최적화|
|Expo vs React Native CLI|빠른 시작 vs 자유로운 네이티브 코드|
|상태관리 방식의 차이|Context, Redux, Recoil 비교|
|네비게이션 종류|Stack, Tab, Drawer|
|비동기 처리 방식|async/await, fetch/axios|

---

## 📚 참고 문서

- [React Native 공식](https://reactnative.dev)
    
- [React Navigation](https://reactnavigation.org/)
    
- [Awesome React Native](https://github.com/jondot/awesome-react-native)
    

---

필요하면 **PDF**, **요약 정리용 Notion 포맷**, 또는 **심화 질문별 답변**도 따로 정리해줄게!  
특정 주제(예: 애니메이션, 폼, 푸시 알림 등) 더 보고 싶으면 말해줘!

# Deprecated
---

### 프로젝트 생성

```bash
npx react-native init MyApp 
cd MyApp 
npx react-native run-android  # or run-ios
```

> `expo` 사용 시

```bash
npx create-expo-app MyApp 
cd MyApp 
npx expo start
```

---

### 기본 폴더 구조

```
MyApp/
  ├── App.js  
  ├── components/  
  ├── screens/  
  ├── assets/  
  └── navigation/
```

---

### 핵심 컴포넌트

|컴포넌트|설명|
|---|---|
|`View`|기본 레이아웃 컨테이너|
|`Text`|텍스트 출력|
|`TextInput`|입력 필드|
|`Button`|기본 버튼|
|`TouchableOpacity`|커스텀 클릭 영역 버튼|
|`ScrollView`|스크롤 가능한 뷰|
|`FlatList`|성능 최적화 리스트 컴포넌트|
|`Image`|이미지 출력|

---

### 스타일 작성

```JavaScript
import { StyleSheet } from 'react-native';  

const styles = StyleSheet.create({   
	container: {     
		flex: 1,     
		padding: 16,     
		backgroundColor: '#fff',   
	},   
	text: {     
		fontSize: 20,     
		color: 'blue',   
	} 
});
```

> 사용

```JSX
<View style={styles.container}>   
	<Text style={styles.text}>Hello</Text> 
</View>
```

---

### 상태 및 이벤트 처리

```JavaScript
import React, { useState } from 'react'; 
import { Text, Button } from 'react-native';  

const Counter = () => {   
	const [count, setCount] = useState(0);    
	return (     
		<>       
			<Text>{count}</Text>       
			<Button title="Increase" onPress={() => setCount(count + 1)} />  
		</>   
	); 
};
```

---

### 네비게이션 (React Navigation)

```Bash
npm install @react-navigation/native 
npm install react-native-screens react-native-safe-area-context 
npm install @react-navigation/native-stack`
```

js

복사편집

`import { NavigationContainer } from '@react-navigation/native'; import { createNativeStackNavigator } from '@react-navigation/native-stack';  const Stack = createNativeStackNavigator();  export default function App() {   return (     <NavigationContainer>       <Stack.Navigator initialRouteName="Home">         <Stack.Screen name="Home" component={HomeScreen} />         <Stack.Screen name="Detail" component={DetailScreen} />       </Stack.Navigator>     </NavigationContainer>   ); }`

---

### 플랫폼별 분기

js

복사편집

`import { Platform } from 'react-native';  if (Platform.OS === 'ios') {   // iOS 전용 로직 }`

---

### API 요청 (예: Axios)

bash

복사편집

`npm install axios`

js

복사편집

`import axios from 'axios';  const fetchData = async () => {   const response = await axios.get('https://api.example.com/data');   console.log(response.data); };`

---

### useEffect 사용 예시

js

복사편집

`import React, { useEffect } from 'react';  useEffect(() => {   console.log('컴포넌트 마운트 시 실행');   return () => {     console.log('언마운트 시 정리');   }; }, []);`

---

### 텍스트 입력 핸들링

js

복사편집

`<TextInput   value={text}   onChangeText={setText}   placeholder="Type something..."   style={{ borderWidth: 1, padding: 10 }} />`

---

### 🛠️ 유용한 패키지들

|패키지|용도|
|---|---|
|`axios`|API 통신|
|`react-navigation`|화면 간 이동|
|`react-native-vector-icons`|아이콘 사용|
|`expo-image-picker`|이미지 업로드|
|`@react-native-async-storage/async-storage`|로컬 저장소|
|`formik` + `yup`|폼/유효성 검증|
|`react-native-reanimated`|고급 애니메이션|

---

### 디버깅 팁

- `console.log()` → Metro에서 확인
- Android Studio / Xcode 로그 확인
- `React Native Debugger` 사용 추천
- `Expo` 사용 시: `?dev=true&debug=true`로 연결 가능

---

필요 시 다음 항목들도 이어서 만들어드릴 수 있어요:

- Form 입력 및 유효성 검사
    
- AsyncStorage 활용 예시
    
- Expo 기능 활용 (카메라, 위치, 푸시 알림 등)
    
- 상태 관리 (Redux, Zustand 등)
    

