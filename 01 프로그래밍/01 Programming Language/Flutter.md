
## 1. Flutter 핵심 개념 요약

|개념|설명|
|---|---|
|**Flutter**|구글의 크로스 플랫폼 UI 프레임워크 (iOS, Android, Web, Desktop)|
|**Dart**|Flutter의 프로그래밍 언어|
|**Widget**|모든 UI 요소의 기본 단위 (레이아웃, 버튼, 텍스트 등)|
|**Hot Reload**|상태를 유지한 채 UI 코드 수정 반영|
|**Stateful vs Stateless**|상태가 있는지에 따라 컴포넌트 유형 구분|

---

## 2. StatelessWidget / StatefulWidget

dart

복사편집

`// StatelessWidget class Hello extends StatelessWidget {   @override   Widget build(BuildContext context) {     return Text('Hello');   } }`

dart

복사편집

`// StatefulWidget class Counter extends StatefulWidget {   @override   _CounterState createState() => _CounterState(); }  class _CounterState extends State<Counter> {   int count = 0;    @override   Widget build(BuildContext context) {     return Column(       children: [         Text('$count'),         ElevatedButton(           onPressed: () => setState(() => count++),           child: Text('Increase'),         ),       ],     );   } }`

---

## 🧩 3. 주요 위젯 정리

|위젯|설명|
|---|---|
|`Text`|텍스트 출력|
|`Container`|패딩, 마진, 배경, 크기 설정 가능|
|`Row`, `Column`|수평 / 수직 정렬|
|`Expanded`|남은 공간을 차지하도록 설정|
|`ListView`|스크롤 가능한 리스트|
|`Stack`|위젯을 겹쳐서 배치|
|`Scaffold`|화면 기본 구조 제공 (AppBar, body 등 포함)|

---

## 🎨 4. 레이아웃 예시

dart

복사편집

`Scaffold(   appBar: AppBar(title: Text('Title')),   body: Center(     child: Column(       mainAxisAlignment: MainAxisAlignment.center,       children: [         Text('Hello'),         SizedBox(height: 10),         ElevatedButton(onPressed: () {}, child: Text('Click')),       ],     ),   ), );`

---

## ⚙️ 5. 상태 관리 옵션

|방식|설명|
|---|---|
|`setState()`|로컬 상태, 가장 기본적인 방식|
|`Provider`|전역 상태 관리용, 공식 권장|
|`Riverpod`|Provider의 개선판|
|`Bloc / Cubit`|이벤트 기반 상태 관리|
|`GetX`, `MobX`|간결한 방식의 상태 관리 도구들|

dart

복사편집

`// Provider 예시 ChangeNotifierProvider(   create: (_) => CounterModel(),   child: MyApp(), );`

---

## 🔁 6. Navigator (라우팅)

dart

복사편집

`Navigator.push(   context,   MaterialPageRoute(builder: (context) => SecondPage()), );  Navigator.pop(context);`

dart

복사편집

`// Named routes 방식 routes: {   '/': (context) => HomePage(),   '/detail': (context) => DetailPage(), };`

---

## 🌐 7. 비동기 처리 (Future / async/await)

dart

복사편집

`Future<String> fetchData() async {   final response = await http.get(Uri.parse('https://example.com'));   return response.body; }`

dart

복사편집

`FutureBuilder(   future: fetchData(),   builder: (context, snapshot) {     if (snapshot.connectionState == ConnectionState.waiting)       return CircularProgressIndicator();     return Text(snapshot.data ?? '');   }, );`

---

## 📁 8. 프로젝트 구조 예시

css

복사편집

`lib/  ├── main.dart  ├── screens/  ├── widgets/  ├── models/  ├── services/  └── providers/`

---

## 🧪 9. 테스트 기본

dart

복사편집

`test('adds one to input values', () {   final calc = Calculator();   expect(calc.addOne(2), 3); });`

bash

복사편집

`flutter test`

---

## 📚 10. 자주 묻는 면접 질문 요약

|질문|핵심 요약|
|---|---|
|Stateless vs Stateful 차이?|상태 보존 여부, 리렌더링 방식 차이|
|Hot Reload vs Hot Restart?|상태 유지 여부 다름 (Reload 유지, Restart 초기화)|
|setState의 역할?|상태 변경 후 UI 갱신 트리거|
|Flutter에서 비동기 처리 방식?|Future, async/await, FutureBuilder|
|Navigator 작동 원리?|Stack 구조 기반 화면 전환|
|Provider와 Bloc 차이?|Provider는 간단한 의존성 주입 + 상태, Bloc은 명확한 구조화|
|Widget이 build 되는 조건?|State 변경, 부모 위젯 리빌드 등|
|Flutter의 장점?|크로스 플랫폼, 빠른 개발, 일관된 UI|

---

## ✨ 11. 기타 핵심 정리

|항목|설명|
|---|---|
|`pubspec.yaml`|의존성 관리 파일|
|`MaterialApp`|Material 디자인 기반 앱의 루트|
|`ThemeData`|앱 전체 테마 설정|
|`MediaQuery`|반응형 UI 구성에 사용|
|`CustomPaint`|캔버스 기반 UI 커스터마이징|

---

✅ 이 Cheat Sheet는 **Flutter 면접 전 10분 복습용**입니다.  
원하시면 `Firebase 연동`, `Platform 채널`, `테마 다크모드`, `앱 배포 전략`, `Riverpod vs Bloc 비교` 등도 이어서 정리해드릴게요!

##### 나의 말:

Firebase 연동, Platform 채널, 테마 다크모드, 앱 배포 전략, Riverpod vs Bloc 비교도 포함해줘

###### ChatGPT의 말:

좋습니다! 아래는 **Flutter 면접 대비용 확장 Cheat Sheet**입니다.  
기존 핵심 내용을 기반으로 다음 항목들을 추가했습니다:

- ✅ Firebase 연동
    
- ✅ Platform 채널
    
- ✅ 테마 & 다크모드
    
- ✅ 앱 배포 전략
    
- ✅ Riverpod vs Bloc 비교
    

---

# 📱 Flutter Cheat Sheet (면접 대비 확장판)

---

## 🔥 1. Firebase 연동

bash

복사편집

`flutterfire configure  // Firebase CLI로 프로젝트 연결 flutter pub add firebase_core firebase_auth cloud_firestore`

### 초기화 코드 (`main.dart`)

dart

복사편집

`void main() async {   WidgetsFlutterBinding.ensureInitialized();   await Firebase.initializeApp();   runApp(MyApp()); }`

### 예시: Firebase Auth

dart

복사편집

`final userCredential = await FirebaseAuth.instance     .signInWithEmailAndPassword(email: 'test@mail.com', password: '1234');`

---

## 🔗 2. Platform 채널 (Flutter ↔ Android/iOS 통신)

### Flutter (Dart)

dart

복사편집

`static const platform = MethodChannel('samples.flutter.dev/battery');  Future<void> getBatteryLevel() async {   final level = await platform.invokeMethod('getBatteryLevel');   print('Battery level: $level'); }`

### Android (Kotlin)

kotlin

복사편집

`MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "samples.flutter.dev/battery")   .setMethodCallHandler { call, result ->     if (call.method == "getBatteryLevel") {         val level = getBatteryLevel()         result.success(level)     } }`

> 실무에서는 native 기능(예: 블루투스, 센서, 보안 키 등) 접근 시 사용

---

## 🌙 3. 테마 및 다크모드 설정

dart

복사편집

`MaterialApp(   theme: ThemeData.light(),   darkTheme: ThemeData.dark(),   themeMode: ThemeMode.system, // light / dark / system );`

dart

복사편집

`final isDark = MediaQuery.of(context).platformBrightness == Brightness.dark;`

> 다크모드 대응은 사용자 경험 및 접근성 면에서 중요

---

## 📦 4. 앱 배포 전략

### ✅ Android

bash

복사편집

`flutter build apk --release flutter build appbundle --release  // Play Store 배포용`

### ✅ iOS

bash

복사편집

`flutter build ios --release`

> iOS는 Xcode와 Apple Developer 계정 필요  
> Android는 Google Play Console 필요

### ✅ Web & Desktop

bash

복사편집

`flutter build web flutter build macos / windows / linux`

---

## ⚖️ 5. Riverpod vs Bloc 비교

|항목|Riverpod|Bloc|
|---|---|---|
|설계 방식|선언형, 함수형 중심|이벤트 기반 (Flux 유사)|
|러닝커브|쉬움|비교적 복잡|
|보일러플레이트|적음|많음 (Event, State 클래스 필수)|
|테스트 용이성|매우 높음|높음|
|커뮤니티|공식 권장, 성장 중|성숙하고 대형 프로젝트에 적합|
|추천 용도|중소 프로젝트, 단순 상태 공유|복잡한 UI/도메인 분리 필요할 때|

---

## 🎯 면접 대비 키워드 정리

|질문|핵심 요약|
|---|---|
|Firebase의 장점은?|백엔드 없이 인증/DB/푸시/호스팅 지원|
|Platform Channel 언제 쓰나?|네이티브 기능 사용할 때|
|ThemeMode.system이란?|시스템 설정에 따라 테마 자동 적용|
|Bloc의 단점은?|러닝커브 있음, 코드 양 많음|
|Riverpod 왜 쓰나?|전역 상태 관리, 의존성 주입 통합 가능|
|앱 배포 전 필수 작업은?|키 서명, manifest 설정, iOS 설정|
|Firebase Firestore의 구조?|컬렉션 → 문서 → 필드 (NoSQL 트리형)|

---

✅ 이 Cheat Sheet는 **Flutter 면접 직전 10~15분 복습용 요약 자료**입니다.  
원하시면 이어서 `애니메이션`, `커스텀 위젯`, `멀티 모듈 구조`, `Flutter Clean Architecture`, `비동기 테스트`, `Integration Test`까지 확장 가능합니다. 필요하신 항목 알려주세요!