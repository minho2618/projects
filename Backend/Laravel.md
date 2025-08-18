#PHP #Laravel #Cheat-Sheet 

# Laravel Cheat Sheet

---

## ✅ 1. Laravel 설치 및 인증 스캐폴딩

```bash
# Laravel 프로젝트 생성
composer create-project laravel/laravel blog

# Breeze (인증 기능) 설치 및 초기 설정
composer require laravel/breeze --dev
php artisan breeze:install
npm install && npm run dev
php artisan migrate
```

**Laravel 사용법**

```Shell
laravel new example-app

cd example-app
npm install && npm run build
composer run dev
```
---

## ✅ 2. 웹 라우팅 설정

```php
// routes/web.php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\PostController;

// 기본 페이지: 루트로 접근하면 게시글 목록으로 리다이렉트
Route::get('/', fn() => redirect()->route('posts.index'));

// 인증된 사용자만 접근 가능한 게시글 라우트 등록
Route::middleware(['auth'])->group(function () {
    Route::resource('posts', PostController::class);  // CRUD 자동 라우팅
});
```

---

## ✅ 3. 모델 및 마이그레이션

```bash
php artisan make:model Post -m
```

```php
// database/migrations/xxxx_create_posts_table.php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

Schema::create('posts', function (Blueprint $table) {
    $table->id();  // 기본 키
    $table->foreignId('user_id')->constrained()->onDelete('cascade'); // 작성자 ID (users.id 참조)
    $table->string('title');    // 게시글 제목
    $table->text('content');    // 게시글 본문
    $table->timestamps();       // created_at, updated_at
});
```

```bash
php artisan migrate  # 마이그레이션 실행
```

---

## ✅ 4. Eloquent 관계 정의

```php
// app/Models/User.php

public function posts() {
    return $this->hasMany(Post::class); // 사용자 → 게시글 다수
}

// app/Models/Post.php

public function user() {
    return $this->belongsTo(User::class); // 게시글 → 사용자
}
```

---

## ✅ 5. 컨트롤러 생성 및 로직

```bash
php artisan make:controller PostController --resource
```

```php
// app/Http/Controllers/PostController.php

use App\Models\Post;
use Illuminate\Http\Request;

class PostController extends Controller
{
    // 게시글 목록 조회
    public function index() {
        $posts = Post::latest()->paginate(10); // 최신순 정렬 + 페이지네이션
        return view('posts.index', compact('posts'));
    }

    // 게시글 작성 폼 출력
    public function create() {
        return view('posts.create');
    }

    // 게시글 저장 처리
    public function store(Request $request) {
        $validated = $request->validate([
            'title' => 'required|max:255',
            'content' => 'required|min:10',
        ]);

        $request->user()->posts()->create($validated);  // 로그인 사용자의 게시글 저장

        return redirect()->route('posts.index')->with('success', '작성 완료');
    }

    // 게시글 상세 보기
    public function show(Post $post) {
        return view('posts.show', compact('post'));
    }

    // 게시글 수정 폼 출력
    public function edit(Post $post) {
        $this->authorize('update', $post); // 작성자만 접근 가능
        return view('posts.edit', compact('post'));
    }

    // 게시글 수정 저장
    public function update(Request $request, Post $post) {
        $this->authorize('update', $post);

        $validated = $request->validate([
            'title' => 'required|max:255',
            'content' => 'required|min:10',
        ]);

        $post->update($validated);

        return redirect()->route('posts.show', $post);
    }

    // 게시글 삭제
    public function destroy(Post $post) {
        $this->authorize('delete', $post);
        $post->delete();

        return redirect()->route('posts.index')->with('success', '삭제 완료');
    }
}
```

---

## ✅ 6. Blade 템플릿 예시

```blade
<!-- resources/views/posts/index.blade.php -->

@extends('layouts.app')

@section('content')
    <h1>📚 게시글 목록</h1>
    <a href="{{ route('posts.create') }}">✏️ 새 글 작성</a>

    <ul>
        @foreach ($posts as $post)
            <li>
                <a href="{{ route('posts.show', $post) }}">{{ $post->title }}</a>
                <small>by {{ $post->user->name }}</small>
            </li>
        @endforeach
    </ul>

    {{ $posts->links() }}  {{-- 페이지네이션 링크 --}}
@endsection
```

---

## ✅ 7. FormRequest 유효성 검사

```bash
php artisan make:request StorePostRequest
```

```php
// app/Http/Requests/StorePostRequest.php

public function rules(): array {
    return [
        'title' => 'required|max:255',
        'content' => 'required|min:10',
    ];
}

public function messages(): array {
    return [
        'title.required' => '제목을 입력해주세요.',
        'content.required' => '내용을 입력해주세요.',
    ];
}
```

---

## ✅ 8. 파일 업로드

```blade
<!-- 파일 업로드 폼 -->
<form action="/upload" method="POST" enctype="multipart/form-data">
    @csrf
    <input type="file" name="photo">
    <button type="submit">업로드</button>
</form>
```

```php
// 파일 업로드 처리
public function upload(Request $request) {
    $path = $request->file('photo')->store('images', 'public'); // public 디스크 저장
    return '/storage/' . $path;  // 접근 가능한 URL 반환
}
```

```bash
php artisan storage:link  # /storage 링크 생성 (최초 1회)
```

---

## ✅ 9. 예외 처리

```php
use Illuminate\Database\Eloquent\ModelNotFoundException;

public function show($id) {
    try {
        $post = Post::findOrFail($id);
    } catch (ModelNotFoundException $e) {
        abort(404, '게시글을 찾을 수 없습니다.');
    }

    return view('posts.show', compact('post'));
}
```

```php
// 예외 로그 기록
use Illuminate\Support\Facades\Log;

Log::error('게시글 조회 실패', ['id' => $id]);
```

---

## ✅ 10. 테스트

```bash
php artisan make:test PostTest
```

```php
// tests/Feature/PostTest.php

public function test_user_can_create_post() {
    $user = User::factory()->create();

    $response = $this->actingAs($user)->post('/posts', [
        'title' => '테스트 제목',
        'content' => '테스트 내용입니다.',
    ]);

    $response->assertRedirect('/posts');
    $this->assertDatabaseHas('posts', ['title' => '테스트 제목']);
}
```

```bash
php artisan test
```

---

## ✅ 11. 배포 준비

```bash
php artisan config:cache     # 설정 캐싱
php artisan route:cache      # 라우트 캐싱
php artisan view:cache       # 뷰 캐싱
php artisan storage:link     # public/storage 링크 생성

# 운영용 .env 설정 예시
APP_ENV=production
APP_DEBUG=false
APP_URL=https://yourdomain.com
```

---
