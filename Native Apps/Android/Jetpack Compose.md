---

## 🎨 Jetpack Compose Cheat Sheet (Kotlin)

### 📦 기본 설정 (Gradle)

groovy

복사편집

`android {     buildFeatures {         compose true     }     composeOptions {         kotlinCompilerExtensionVersion = "1.5.3" // 버전은 최신으로     } }  dependencies {     implementation "androidx.compose.ui:ui:1.5.3"     implementation "androidx.compose.material:material:1.5.3"     implementation "androidx.activity:activity-compose:1.8.2" }`

---

### 🛠️ 기본 구조

kotlin

복사편집

`@Composable fun Greeting(name: String) {     Text(text = "Hello, $name!") }  @Preview(showBackground = true) @Composable fun DefaultPreview() {     Greeting("Android") }`

---

### 🧱 주요 컴포저블 요소

|컴포저블 함수|설명|
|---|---|
|`Text()`|텍스트 출력|
|`Button()`|버튼|
|`Column {}`|수직 정렬 레이아웃|
|`Row {}`|수평 정렬 레이아웃|
|`Box {}`|겹쳐서 배치할 때 사용|
|`Image()`|이미지 출력|
|`Spacer()`|공간 띄우기|
|`LazyColumn {}`|리스트 스크롤 뷰 (RecyclerView 대체)|

---

### 💡 상태 관리

kotlin

복사편집

`@Composable fun Counter() {     var count by remember { mutableStateOf(0) }          Column {         Text("Count: $count")         Button(onClick = { count++ }) {             Text("Increment")         }     } }`

---

### 🎛️ Modifier (스타일 & 배치)

kotlin

복사편집

`Text(     text = "Styled Text",     modifier = Modifier         .padding(16.dp)         .background(Color.LightGray)         .fillMaxWidth()         .clickable { /* ... */ },     fontSize = 20.sp,     color = Color.Black )`

|Modifier 함수|설명|
|---|---|
|`padding()`|내부 여백|
|`fillMaxWidth()`|너비를 최대로 설정|
|`height(50.dp)`|높이 지정|
|`clickable {}`|클릭 이벤트 처리|
|`background()`|배경 색|

---

### 🧾 리스트 (LazyColumn)

kotlin

복사편집

`val items = listOf("Apple", "Banana", "Cherry")  LazyColumn {     items(items) { item ->         Text(text = item, modifier = Modifier.padding(8.dp))     } }`

---

### 🧭 네비게이션 (Navigation Compose)

kotlin

복사편집

`// build.gradle 추가 implementation "androidx.navigation:navigation-compose:2.7.5"  // 사용 예시 NavHost(navController, startDestination = "home") {     composable("home") { HomeScreen(navController) }     composable("detail") { DetailScreen() } }  Button(onClick = { navController.navigate("detail") }) {     Text("Go to Detail") }`

---

### 💤 Dialog & Snackbar

kotlin

복사편집

`// AlertDialog if (showDialog) {     AlertDialog(         onDismissRequest = { showDialog = false },         title = { Text("Title") },         text = { Text("Dialog content") },         confirmButton = {             TextButton(onClick = { showDialog = false }) {                 Text("OK")             }         }     ) }`

---

### 🔄 애니메이션 (기본 예시)

kotlin

복사편집

`val expanded = remember { mutableStateOf(false) } val size by animateDpAsState(if (expanded.value) 200.dp else 100.dp)  Box(     Modifier         .size(size)         .background(Color.Cyan)         .clickable { expanded.value = !expanded.value } )`

---

### 🧪 Preview Tips

kotlin

복사편집

`@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES) @Composable fun DarkModePreview() {     MyComposable() }`

---

### 📌 기타 유용 단축키 (Android Studio)

|단축키 (Windows)|설명|
|---|---|
|`Ctrl + B`|정의로 이동|
|`Alt + Enter`|자동 수정 제안|
|`Ctrl + Shift + ↑/↓`|함수 간 빠른 이동|
|`@Preview` 클릭 후 `Split`|코드 + 화면 미리보기|

---

### 🛠️ 실전 팁

- `remember`는 컴포저블이 재구성되어도 상태를 유지하게 함
    
- `rememberSaveable`은 화면 회전에도 값 유지
    
- Modifier는 순서 중요!
    
- `LazyColumn`은 RecyclerView보다 선언적이며 간단
    

---

필요하시면 Room, Coroutine, Hilt(DI), Paging, Theme 관련 Compose Cheat Sheet도 이어서 제공해드릴 수 있어요. 원하시나요?