#Android
## 📱 Android Cheat Sheet (Kotlin 기준)

### 🗂️ 1. 주요 구성 요소

|구성 요소|설명|
|---|---|
|`Activity`|화면을 구성하는 UI 단위. 앱의 진입점.|
|`Fragment`|Activity 내 재사용 가능한 UI 조각.|
|`ViewModel`|UI 상태 관리. 생명주기 고려.|
|`LiveData`|관찰 가능한 데이터 홀더.|
|`Intent`|컴포넌트 간 데이터 전달.|
|`RecyclerView`|리스트 표시용 View.|

---

### 🧩 2. 생명주기 (Activity 기준)

kotlin

복사편집

`onCreate()   // 초기화 onStart()    // UI 보이기 시작 onResume()   // 사용자와 상호작용 onPause()    // 다른 액티비티로 전환 중 onStop()     // UI 숨겨짐 onDestroy()  // 종료 전 정리`

---

### 🚀 3. Activity 전환

kotlin

복사편집

`val intent = Intent(this, TargetActivity::class.java) intent.putExtra("key", "value") startActivity(intent)`

### ⬅️ 데이터 받기

kotlin

복사편집

`val data = intent.getStringExtra("key")`

---

### 📤 Fragment 사용

kotlin

복사편집

`supportFragmentManager.beginTransaction()     .replace(R.id.container, MyFragment())     .commit()`

---

### 📑 ViewModel + LiveData

kotlin

복사편집

`class MyViewModel : ViewModel() {     val text = MutableLiveData<String>() }  val viewModel: MyViewModel by viewModels()  viewModel.text.observe(this) { value ->     textView.text = value }`

---

### 🧮 RecyclerView 구성

kotlin

복사편집

`// Adapter 정의 class MyAdapter(private val items: List<String>) :     RecyclerView.Adapter<MyAdapter.ViewHolder>() {      inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {         val textView: TextView = view.findViewById(R.id.item_text)     }      override fun onCreateViewHolder(...) = ...     override fun onBindViewHolder(...) = ...     override fun getItemCount() = items.size }  // 연결 recyclerView.adapter = MyAdapter(list) recyclerView.layoutManager = LinearLayoutManager(this)`

---

### 🌐 인터넷 권한

xml

복사편집

`<!-- AndroidManifest.xml --> <uses-permission android:name="android.permission.INTERNET" />`

---

### 📦 의존성 추가 (Gradle)

groovy

복사편집

`// ViewModel + LiveData implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0" implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.8.0"  // RecyclerView implementation "androidx.recyclerview:recyclerview:1.3.2"  // Retrofit (REST API 통신) implementation "com.squareup.retrofit2:retrofit:2.9.0" implementation "com.squareup.retrofit2:converter-gson:2.9.0"`

---

### 💾 SharedPreferences

kotlin

복사편집

`val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE) prefs.edit().putString("key", "value").apply()  val value = prefs.getString("key", "")`

---

### 🌈 Jetpack Compose 시작 코드

kotlin

복사편집

`@Composable fun Greeting(name: String) {     Text(text = "Hello $name!") }  @Preview @Composable fun PreviewGreeting() {     Greeting("Android") }`

---

### 🧪 디버깅 팁

- `Log.d("TAG", "message")`
    
- 앱 강제 종료 시 로그: **Logcat → filter by 'E'**
    
- 앱 새로 설치: `Build → Clean Project` → `Rebuild`
    

---

### 📌 기타 유용한 단축키 (Android Studio)

|단축키 (Win/Linux)|기능|
|---|---|
|`Ctrl + Shift + A`|액션 검색|
|`Ctrl + O`|오버라이드 메소드 검색|
|`Alt + Enter`|빠른 수정 제안|
|`Ctrl + /`|한 줄 주석 토글|
|`Ctrl + Alt + L`|코드 포맷팅|

---

필요하다면 PDF나 인쇄 가능한 포맷으로도 정리해드릴 수 있어요. Jetpack Compose, Room, Retrofit, Coroutine 등 세부 주제별 Cheat Sheet도 원하시면 이어서 제공해드릴게요.