#HTML
# HTML Cheat Sheet

## 기본 구조

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>페이지 제목</title>
</head>
<body>
    <!-- 컨텐츠 -->
</body>
</html>
```

## 텍스트 관련 태그

### 제목
```html
<h1>제목 1 (가장 큼)</h1>
<h2>제목 2</h2>
<h3>제목 3</h3>
<h4>제목 4</h4>
<h5>제목 5</h5>
<h6>제목 6 (가장 작음)</h6>
```

### 단락과 텍스트
```html
<p>단락</p>
<br>                <!-- 줄바꿈 -->
<hr>                <!-- 수평선 -->
<strong>굵은 텍스트 (중요)</strong>
<b>굵은 텍스트 (시각적)</b>
<em>기울임 (강조)</em>
<i>기울임 (시각적)</i>
<u>밑줄</u>
<mark>형광펜 효과</mark>
<small>작은 텍스트</small>
<del>취소선</del>
<ins>추가된 텍스트</ins>
<sub>아래 첨자</sub>
<sup>위 첨자</sup>
<code>인라인 코드</code>
<pre>미리 서식이 지정된 텍스트</pre>
<blockquote>인용문</blockquote>
<abbr title="풀네임">약어</abbr>
```

## 링크와 미디어

### 링크
```html
<a href="URL">링크 텍스트</a>
<a href="URL" target="_blank">새 탭에서 열기</a>
<a href="#section">같은 페이지 내 이동</a>
<a href="mailto:email@example.com">이메일 링크</a>
<a href="tel:+821012345678">전화 링크</a>
```

### 이미지
```html
<img src="이미지경로" alt="대체 텍스트" width="너비" height="높이">
<img src="image.jpg" loading="lazy">  <!-- 지연 로딩 -->
```

### 비디오와 오디오
```html
<video controls width="320" height="240">
    <source src="video.mp4" type="video/mp4">
    <source src="video.webm" type="video/webm">
    브라우저가 비디오를 지원하지 않습니다.
</video>

<audio controls>
    <source src="audio.mp3" type="audio/mpeg">
    <source src="audio.ogg" type="audio/ogg">
    브라우저가 오디오를 지원하지 않습니다.
</audio>
```

### 기타 미디어
```html
<iframe src="URL" width="600" height="400"></iframe>
<embed src="file.pdf" type="application/pdf" width="500" height="375">
```

## 목록

### 순서 없는 목록
```html
<ul>
    <li>항목 1</li>
    <li>항목 2</li>
    <li>항목 3</li>
</ul>
```

### 순서 있는 목록
```html
<ol>
    <li>첫 번째</li>
    <li>두 번째</li>
    <li>세 번째</li>
</ol>

<ol type="A">     <!-- A, B, C... -->
<ol type="a">     <!-- a, b, c... -->
<ol type="I">     <!-- I, II, III... -->
<ol type="i">     <!-- i, ii, iii... -->
<ol start="5">    <!-- 5부터 시작 -->
```

### 설명 목록
```html
<dl>
    <dt>용어</dt>
    <dd>용어 설명</dd>
    <dt>다른 용어</dt>
    <dd>다른 용어 설명</dd>
</dl>
```

## 테이블

```html
<table>
    <caption>테이블 제목</caption>
    <thead>
        <tr>
            <th>헤더 1</th>
            <th>헤더 2</th>
            <th>헤더 3</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>데이터 1</td>
            <td>데이터 2</td>
            <td>데이터 3</td>
        </tr>
        <tr>
            <td colspan="2">병합된 셀</td>
            <td rowspan="2">세로 병합</td>
        </tr>
    </tbody>
    <tfoot>
        <tr>
            <td>바닥글</td>
        </tr>
    </tfoot>
</table>
```

## 폼 (Forms)

### 기본 폼 구조
```html
<form action="/submit" method="POST">
    <fieldset>
        <legend>폼 섹션 제목</legend>
        <!-- 폼 요소들 -->
    </fieldset>
</form>
```

### 입력 필드
```html
<!-- 텍스트 입력 -->
<input type="text" name="username" placeholder="사용자명" required>
<input type="password" name="pwd" minlength="8">
<input type="email" name="email" placeholder="email@example.com">
<input type="tel" name="phone" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}">
<input type="url" name="website">
<input type="search" name="q">

<!-- 숫자와 날짜 -->
<input type="number" min="1" max="100" step="1">
<input type="range" min="0" max="100" value="50">
<input type="date" name="birthday">
<input type="time" name="appt">
<input type="datetime-local" name="meeting">
<input type="month" name="bdaymonth">
<input type="week" name="week">

<!-- 선택 요소 -->
<input type="checkbox" name="agree" value="yes" checked>
<input type="radio" name="gender" value="male">
<input type="radio" name="gender" value="female">

<!-- 파일과 색상 -->
<input type="file" name="upload" accept="image/*" multiple>
<input type="color" name="favcolor" value="#ff0000">

<!-- 버튼 -->
<input type="submit" value="전송">
<input type="reset" value="초기화">
<input type="button" value="클릭" onclick="alert('클릭!')">
<button type="submit">전송 버튼</button>

<!-- 숨김 필드 -->
<input type="hidden" name="userid" value="12345">
```

### 기타 폼 요소
```html
<!-- 텍스트 영역 -->
<textarea name="message" rows="10" cols="30" maxlength="500">
기본 텍스트
</textarea>

<!-- 드롭다운 -->
<select name="cars">
    <optgroup label="독일차">
        <option value="mercedes">Mercedes</option>
        <option value="audi" selected>Audi</option>
    </optgroup>
    <optgroup label="미국차">
        <option value="tesla">Tesla</option>
        <option value="ford">Ford</option>
    </optgroup>
</select>

<!-- 데이터리스트 -->
<input list="browsers" name="browser">
<datalist id="browsers">
    <option value="Chrome">
    <option value="Firefox">
    <option value="Safari">
    <option value="Edge">
</datalist>

<!-- 라벨 -->
<label for="username">사용자명:</label>
<input type="text" id="username" name="username">

<!-- 출력 -->
<output name="result" for="a b"></output>

<!-- 진행 표시 -->
<progress value="32" max="100"></progress>
<meter value="6" min="0" max="10">6 out of 10</meter>
```

## 시맨틱 HTML5 태그

### 구조적 요소
```html
<header>헤더 영역</header>
<nav>네비게이션 메뉴</nav>
<main>주요 컨텐츠</main>
<section>섹션</section>
<article>독립적인 컨텐츠</article>
<aside>사이드바, 부가 정보</aside>
<footer>푸터 영역</footer>
<figure>
    <img src="image.jpg" alt="설명">
    <figcaption>이미지 캡션</figcaption>
</figure>
```

### 기타 시맨틱 태그
```html
<details>
    <summary>클릭하여 보기</summary>
    <p>숨겨진 내용</p>
</details>

<dialog open>대화 상자 내용</dialog>

<time datetime="2024-12-25">크리스마스</time>

<address>
    연락처 정보<br>
    email@example.com
</address>

<cite>참조 작품 제목</cite>

<data value="12345">제품 번호</data>

<kbd>Ctrl+C</kbd>  <!-- 키보드 입력 -->
<samp>출력 예시</samp>  <!-- 프로그램 출력 -->
<var>변수</var>  <!-- 변수명 -->

<wbr>  <!-- 줄바꿈 가능 위치 -->
```

## 메타 태그

```html
<head>
    <!-- 문자 인코딩 -->
    <meta charset="UTF-8">
    
    <!-- 뷰포트 설정 (반응형) -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- 페이지 설명 -->
    <meta name="description" content="페이지 설명">
    <meta name="keywords" content="키워드1, 키워드2">
    <meta name="author" content="작성자">
    
    <!-- 새로고침 -->
    <meta http-equiv="refresh" content="30">
    
    <!-- Open Graph (소셜 미디어) -->
    <meta property="og:title" content="제목">
    <meta property="og:description" content="설명">
    <meta property="og:image" content="이미지URL">
    <meta property="og:url" content="페이지URL">
    
    <!-- 파비콘 -->
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    
    <!-- CSS 연결 -->
    <link rel="stylesheet" href="styles.css">
    
    <!-- JavaScript 연결 -->
    <script src="script.js" defer></script>
</head>
```

## 전역 속성 (모든 태그에 사용 가능)

```html
id="unique-id"              <!-- 고유 식별자 -->
class="class1 class2"       <!-- CSS 클래스 -->
style="color: red;"         <!-- 인라인 스타일 -->
title="툴팁 텍스트"           <!-- 툴팁 -->
lang="ko"                   <!-- 언어 -->
dir="ltr"                   <!-- 텍스트 방향 (ltr/rtl) -->
hidden                      <!-- 숨김 -->
tabindex="1"                <!-- 탭 순서 -->
contenteditable="true"      <!-- 편집 가능 -->
spellcheck="true"           <!-- 맞춤법 검사 -->
draggable="true"            <!-- 드래그 가능 -->
data-*="custom"             <!-- 사용자 정의 데이터 속성 -->
```

## 특수 문자 (HTML Entities)

```html
&nbsp;      <!-- 공백 -->
&lt;        <!-- < -->
&gt;        <!-- > -->
&amp;       <!-- & -->
&quot;      <!-- " -->
&apos;      <!-- ' -->
&copy;      <!-- © -->
&reg;       <!-- ® -->
&trade;     <!-- ™ -->
&euro;      <!-- € -->
&pound;     <!-- £ -->
&yen;       <!-- ¥ -->
&cent;      <!-- ¢ -->
&sect;      <!-- § -->
&deg;       <!-- ° -->
&plusmn;    <!-- ± -->
&frac12;    <!-- ½ -->
&frac14;    <!-- ¼ -->
&times;     <!-- × -->
&divide;    <!-- ÷ -->
&larr;      <!-- ← -->
&rarr;      <!-- → -->
&uarr;      <!-- ↑ -->
&darr;      <!-- ↓ -->
&hearts;    <!-- ♥ -->
&spades;    <!-- ♠ -->
&clubs;     <!-- ♣ -->
&diams;     <!-- ♦ -->
```

## 주석

```html
<!-- 한 줄 주석 -->

<!--
여러 줄
주석
-->
```

## 유용한 팁

1. **시맨틱 태그 사용**: div와 span 대신 의미있는 HTML5 태그 사용
2. **접근성**: alt 속성, ARIA 레이블, 적절한 제목 계층 구조 유지
3. **SEO 최적화**: 메타 태그, 제목 태그, 시맨틱 마크업 활용
4. **성능**: 이미지 lazy loading, 적절한 이미지 포맷 사용
5. **유효성 검사**: W3C Validator로 HTML 검증