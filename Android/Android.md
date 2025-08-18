#Android 
# Android 개발 치트 시트

## 📱 프로젝트 구조

### 기본 디렉터리 구조

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/package/
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── values/
│   │   │   ├── drawable/
│   │   │   └── mipmap/
│   │   └── AndroidManifest.xml
│   ├── test/
│   └── androidTest/
├── build.gradle
└── proguard-rules.pro
```

## 🎨 레이아웃 & UI

### 주요 레이아웃

```xml
<!-- LinearLayout -->
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">
</LinearLayout>

<!-- RelativeLayout -->
<RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">
</RelativeLayout>

<!-- ConstraintLayout -->
<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">
</androidx.constraintlayout.widget.ConstraintLayout>
```

### 주요 뷰 컴포넌트

```xml
<!-- TextView -->
<TextView
    android:id="@+id/textView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello World"
    android:textSize="16sp"
    android:textColor="@android:color/black" />

<!-- Button -->
<Button
    android:id="@+id/button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Click Me"
    android:onClick="onButtonClick" />

<!-- EditText -->
<EditText
    android:id="@+id/editText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Enter text here"
    android:inputType="text" />

<!-- ImageView -->
<ImageView
    android:id="@+id/imageView"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:src="@drawable/ic_launcher"
    android:scaleType="centerCrop" />

<!-- RecyclerView -->
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

## 🏗️ Activity 생명주기

### Activity 라이프사이클 메서드

```java
public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 초기화 작업
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        // Activity가 보이기 시작할 때
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        // Activity가 foreground로 올 때
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        // Activity가 일시정지될 때
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        // Activity가 보이지 않을 때
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Activity가 종료될 때
    }
}
```

## 📝 Intent & Activity 전환

### 명시적 Intent

```java
// 다른 Activity로 전환
Intent intent = new Intent(this, SecondActivity.class);
intent.putExtra("key", "value");
startActivity(intent);

// 결과를 받는 Activity 시작
Intent intent = new Intent(this, SecondActivity.class);
startActivityForResult(intent, REQUEST_CODE);
```

### 암시적 Intent

```java
// 전화 걸기
Intent callIntent = new Intent(Intent.ACTION_CALL);
callIntent.setData(Uri.parse("tel:01012345678"));
startActivity(callIntent);

// 웹 브라우저 열기
Intent webIntent = new Intent(Intent.ACTION_VIEW);
webIntent.setData(Uri.parse("https://www.google.com"));
startActivity(webIntent);

// 이메일 보내기
Intent emailIntent = new Intent(Intent.ACTION_SEND);
emailIntent.setType("text/plain");
emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@example.com"});
emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
startActivity(Intent.createChooser(emailIntent, "Send Email"));
```

## 🗂️ Fragment

### 기본 Fragment

```java
public class MyFragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // View 초기화
    }
}
```

### Fragment 트랜잭션

```java
// Fragment 추가/교체
FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
transaction.replace(R.id.fragment_container, new MyFragment());
transaction.addToBackStack(null);
transaction.commit();
```

## 📊 데이터 저장

### SharedPreferences

```java
// 저장
SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
SharedPreferences.Editor editor = prefs.edit();
editor.putString("key", "value");
editor.putInt("number", 123);
editor.apply();

// 읽기
String value = prefs.getString("key", "default");
int number = prefs.getInt("number", 0);
```

### Room Database

```java
// Entity
@Entity
public class User {
    @PrimaryKey
    public int uid;
    
    @ColumnInfo(name = "first_name")
    public String firstName;
    
    @ColumnInfo(name = "last_name")
    public String lastName;
}

// DAO
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();
    
    @Insert
    void insertAll(User... users);
    
    @Delete
    void delete(User user);
}

// Database
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
```

## 🌐 네트워킹

### Retrofit 사용법

```java
// API 인터페이스
public interface ApiService {
    @GET("users/{id}")
    Call<User> getUser(@Path("id") int userId);
    
    @POST("users")
    Call<User> createUser(@Body User user);
}

// Retrofit 클라이언트
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build();

ApiService service = retrofit.create(ApiService.class);

// API 호출
Call<User> call = service.getUser(123);
call.enqueue(new Callback<User>() {
    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        // 성공 처리
    }
    
    @Override
    public void onFailure(Call<User> call, Throwable t) {
        // 실패 처리
    }
});
```

## 📋 RecyclerView

### 어댑터 구현

```java
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> mData;
    
    public MyAdapter(List<String> data) {
        this.mData = data;
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mData.get(position));
    }
    
    @Override
    public int getItemCount() {
        return mData.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
```

### RecyclerView 설정

```java
RecyclerView recyclerView = findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(new MyAdapter(dataList));
```

## 🔒 권한 처리

### Manifest 권한

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

### 런타임 권한 요청

```java
// 권한 체크
if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED) {
    // 권한 요청
    ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.CAMERA},
            REQUEST_CAMERA_PERMISSION);
}

@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                     int[] grantResults) {
    switch (requestCode) {
        case REQUEST_CAMERA_PERMISSION:
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한 허용됨
            } else {
                // 권한 거부됨
            }
            break;
    }
}
```

## 🎯 이벤트 처리

### 클릭 리스너

```java
// XML에서 onClick 속성 사용
public void onButtonClick(View view) {
    // 버튼 클릭 처리
}

// 코드에서 리스너 설정
Button button = findViewById(R.id.button);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // 클릭 처리
    }
});

// 람다식 사용 (Java 8+)
button.setOnClickListener(v -> {
    // 클릭 처리
});
```

## 📱 디바이스 기능

### 토스트 메시지

```java
Toast.makeText(this, "메시지", Toast.LENGTH_SHORT).show();
Toast.makeText(this, "긴 메시지", Toast.LENGTH_LONG).show();
```

### 알림 대화상자

```java
new AlertDialog.Builder(this)
    .setTitle("제목")
    .setMessage("메시지")
    .setPositiveButton("확인", (dialog, which) -> {
        // 확인 버튼 클릭
    })
    .setNegativeButton("취소", null)
    .show();
```

### 스낵바

```java
Snackbar.make(view, "메시지", Snackbar.LENGTH_LONG)
    .setAction("실행취소", v -> {
        // 액션 처리
    })
    .show();
```

## 🔄 비동기 처리

### AsyncTask (Deprecated - 참고용)

```java
private class MyAsyncTask extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... voids) {
        // 백그라운드 작업
        return "결과";
    }
    
    @Override
    protected void onPostExecute(String result) {
        // UI 업데이트
    }
}
```

### Handler 사용

```java
Handler handler = new Handler(Looper.getMainLooper());
new Thread(() -> {
    // 백그라운드 작업
    String result = "결과";
    
    handler.post(() -> {
        // UI 업데이트
        textView.setText(result);
    });
}).start();
```

## 🛠️ 유용한 명령어

### ADB 명령어

```bash
# 디바이스 연결 확인
adb devices

# 앱 설치
adb install app.apk

# 앱 제거
adb uninstall com.package.name

# 로그 확인
adb logcat

# 화면 캡처
adb shell screencap /sdcard/screenshot.png
adb pull /sdcard/screenshot.png
```

### Gradle 명령어

```bash
# 빌드
./gradlew build

# 디버그 APK 빌드
./gradlew assembleDebug

# 릴리즈 APK 빌드
./gradlew assembleRelease

# 테스트 실행
./gradlew test

# 클린 빌드
./gradlew clean build
```

## 📦 주요 의존성

### build.gradle (Module: app)

```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    
    // RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.3.0'
    
    // Room
    implementation 'androidx.room:room-runtime:2.5.0'
    annotationProcessor 'androidx.room:room-compiler:2.5.0'
    
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // Glide
    implementation 'com.github.bumptech.glide:glide:4.15.1'
    
    // ViewPager2
    implementation 'androidx.viewpager2:viewpager2:1.0.0'
    
    // Fragment
    implementation 'androidx.fragment:fragment:1.5.7'
}
```

## 💡 유용한 팁

### 디버깅

- `Log.d(TAG, "message")` - 로그 출력
- Breakpoint 사용으로 단계별 디버깅
- Layout Inspector로 UI 구조 확인

### 성능 최적화

- ViewHolder 패턴 사용 (RecyclerView)
- 이미지 로딩 시 Glide, Picasso 등 라이브러리 사용
- 메모리 누수 방지 (강한 참조 주의)
- 네트워크 호출은 메인 스레드가 아닌 백그라운드에서 실행

### 보안

- API 키는 BuildConfig나 별도 파일로 관리
- ProGuard/R8으로 코드 난독화
- HTTPS 사용 권장