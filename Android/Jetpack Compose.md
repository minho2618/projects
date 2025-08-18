#Android #Jetpack
# Jetpack Compose Cheat Sheet

## 기본 설정

### 의존성 추가 (build.gradle)

```kotlin
android {
    compileSdk 34
    
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose true
    }
    composeOptions {
        kotlinCompilerExtensionVersion '1.5.8'
    }
}

dependencies {
    implementation 'androidx.compose.bom:2024.02.00'
    implementation 'androidx.compose.ui:ui'
    implementation 'androidx.compose.ui:ui-tooling-preview'
    implementation 'androidx.compose.material3:material3'
    implementation 'androidx.activity:activity-compose:1.8.2'
}
```

## 기본 구조

### MainActivity 설정

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}
```

### Composable 함수 기본 형태

```kotlin
@Composable
fun MyComposable() {
    // UI 코드
}

@Preview
@Composable
fun MyComposablePreview() {
    MyAppTheme {
        MyComposable()
    }
}
```

## 기본 UI 컴포넌트

### Text

```kotlin
Text(
    text = "Hello World",
    fontSize = 16.sp,
    fontWeight = FontWeight.Bold,
    color = Color.Blue,
    modifier = Modifier.padding(16.dp)
)
```

### Button

```kotlin
Button(
    onClick = { /* 클릭 동작 */ },
    modifier = Modifier.padding(8.dp)
) {
    Text("Click Me")
}

// 다른 버튼 타입들
OutlinedButton(onClick = { }) { Text("Outlined") }
TextButton(onClick = { }) { Text("Text Button") }
```

### TextField

```kotlin
var text by remember { mutableStateOf("") }

TextField(
    value = text,
    onValueChange = { text = it },
    label = { Text("Enter text") },
    modifier = Modifier.fillMaxWidth()
)

// OutlinedTextField
OutlinedTextField(
    value = text,
    onValueChange = { text = it },
    label = { Text("Outlined") }
)
```

### Image

```kotlin
Image(
    painter = painterResource(id = R.drawable.image),
    contentDescription = "설명",
    modifier = Modifier.size(100.dp)
)

// 네트워크 이미지 (Coil 라이브러리)
AsyncImage(
    model = "https://example.com/image.jpg",
    contentDescription = null,
    modifier = Modifier.size(100.dp)
)
```

## 레이아웃

### Column (세로 배치)

```kotlin
Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text("First")
    Text("Second")
    Text("Third")
}
```

### Row (가로 배치)

```kotlin
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Text("Left")
    Text("Center")
    Text("Right")
}
```

### Box (겹치기 배치)

```kotlin
Box(
    modifier = Modifier.size(200.dp),
    contentAlignment = Alignment.Center
) {
    Text("Background", fontSize = 24.sp)
    Text("Foreground", fontSize = 16.sp, color = Color.White)
}
```

### LazyColumn (스크롤 가능한 리스트)

```kotlin
LazyColumn {
    items(itemList) { item ->
        Text(
            text = item,
            modifier = Modifier.padding(16.dp)
        )
    }
}

// 인덱스와 함께
LazyColumn {
    itemsIndexed(itemList) { index, item ->
        Text("$index: $item")
    }
}
```

### LazyRow (가로 스크롤 리스트)

```kotlin
LazyRow {
    items(itemList) { item ->
        Card(
            modifier = Modifier
                .width(100.dp)
                .padding(4.dp)
        ) {
            Text(item)
        }
    }
}
```

## 상태 관리

### remember와 mutableStateOf

```kotlin
@Composable
fun CounterScreen() {
    var count by remember { mutableStateOf(0) }
    
    Column {
        Text("Count: $count")
        Button(onClick = { count++ }) {
            Text("Increment")
        }
    }
}
```

### rememberSaveable (구성 변경 시 상태 유지)

```kotlin
@Composable
fun SaveableCounter() {
    var count by rememberSaveable { mutableStateOf(0) }
    
    Button(onClick = { count++ }) {
        Text("Count: $count")
    }
}
```

### ViewModel과 함께 사용

```kotlin
@Composable
fun ViewModelScreen(viewModel: MyViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    
    when (uiState) {
        is Loading -> CircularProgressIndicator()
        is Success -> Text(uiState.data)
        is Error -> Text("Error: ${uiState.message}")
    }
}
```

## Modifier

### 기본 Modifier들

```kotlin
Modifier
    .fillMaxSize()              // 최대 크기로 채우기
    .fillMaxWidth()             // 가로 최대로
    .fillMaxHeight()            // 세로 최대로
    .size(100.dp)               // 고정 크기
    .width(200.dp)              // 가로 크기
    .height(100.dp)             // 세로 크기
    .padding(16.dp)             // 패딩
    .padding(horizontal = 16.dp, vertical = 8.dp)
    .background(Color.Blue)     // 배경색
    .clip(RoundedCornerShape(8.dp))  // 모서리 둥글게
    .clickable { /* 클릭 동작 */ }   // 클릭 가능하게
    .weight(1f)                 // Row/Column에서 가중치
```

### 조건부 Modifier

```kotlin
Modifier
    .then(
        if (condition) {
            Modifier.background(Color.Red)
        } else {
            Modifier
        }
    )
```

## 네비게이션

### Navigation Compose 설정

```kotlin
// build.gradle
implementation "androidx.navigation:navigation-compose:2.7.6"

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController) }
        composable("detail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            DetailScreen(itemId, navController)
        }
    }
}

// 네비게이션 사용
navController.navigate("detail/123")
navController.popBackStack()
```

## 테마와 스타일링

### Material Theme

```kotlin
@Composable
fun MyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
    
    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}
```

### 커스텀 컬러와 타이포그래피

```kotlin
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)
```

## 애니메이션

### 기본 애니메이션

```kotlin
@Composable
fun AnimatedVisibilityExample() {
    var visible by remember { mutableStateOf(true) }
    
    Column {
        Button(onClick = { visible = !visible }) {
            Text("Toggle")
        }
        
        AnimatedVisibility(visible = visible) {
            Text("Hello World!")
        }
    }
}
```

### 값 애니메이션

```kotlin
@Composable
fun AnimatedValueExample() {
    var targetValue by remember { mutableStateOf(100.dp) }
    val animatedSize by animateDpAsState(
        targetValue = targetValue,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    Box(
        modifier = Modifier
            .size(animatedSize)
            .background(Color.Blue)
            .clickable { 
                targetValue = if (targetValue == 100.dp) 200.dp else 100.dp 
            }
    )
}
```

## 자주 사용하는 패턴

### 로딩 상태 처리

```kotlin
@Composable
fun LoadingContent(
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()
        
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
```

### 에러 처리

```kotlin
@Composable
fun ErrorMessage(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}
```

이 치트 시트는 Jetpack Compose의 핵심 개념과 자주 사용되는 패턴들을 정리한 것입니다. 실제 개발할 때 빠른 참고용으로 활용하시면 됩니다.