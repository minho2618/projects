#CSS
# CSS Cheat Sheet

## CSS 적용 방법

### 인라인 스타일
```html
<p style="color: red; font-size: 16px;">텍스트</p>
```

### 내부 스타일시트
```html
<head>
    <style>
        p { color: red; }
    </style>
</head>
```

### 외부 스타일시트
```html
<link rel="stylesheet" href="styles.css">
```

### @import
```css
@import url('styles.css');
@import url('https://fonts.googleapis.com/css2?family=Roboto');
```

## 선택자 (Selectors)

### 기본 선택자
```css
* { }                      /* 전체 선택자 */
p { }                      /* 요소 선택자 */
.class { }                 /* 클래스 선택자 */
#id { }                    /* ID 선택자 */
[attribute] { }            /* 속성 선택자 */
[attribute="value"] { }    /* 속성값 선택자 */
[attribute~="value"] { }   /* 속성값 포함 (공백 구분) */
[attribute|="value"] { }   /* 속성값 시작 (하이픈 포함) */
[attribute^="value"] { }   /* 속성값 시작 */
[attribute$="value"] { }   /* 속성값 끝 */
[attribute*="value"] { }   /* 속성값 포함 */
```

### 결합 선택자
```css
div p { }                  /* 자손 선택자 */
div > p { }                /* 자식 선택자 */
div + p { }                /* 인접 형제 선택자 */
div ~ p { }                /* 일반 형제 선택자 */
div, p { }                 /* 그룹 선택자 */
```

### 의사 클래스 (Pseudo-classes)
```css
/* 링크 관련 */
:link { }                  /* 방문하지 않은 링크 */
:visited { }               /* 방문한 링크 */
:hover { }                 /* 마우스 오버 */
:active { }                /* 클릭 중 */
:focus { }                 /* 포커스 상태 */

/* 구조적 의사 클래스 */
:first-child { }           /* 첫 번째 자식 */
:last-child { }            /* 마지막 자식 */
:nth-child(n) { }          /* n번째 자식 */
:nth-child(odd) { }        /* 홀수 번째 */
:nth-child(even) { }       /* 짝수 번째 */
:nth-child(3n) { }         /* 3의 배수 */
:nth-child(3n+1) { }       /* 3n+1 번째 */
:nth-last-child(n) { }     /* 뒤에서 n번째 */
:first-of-type { }         /* 해당 타입의 첫 번째 */
:last-of-type { }          /* 해당 타입의 마지막 */
:nth-of-type(n) { }        /* 해당 타입의 n번째 */
:only-child { }            /* 유일한 자식 */
:only-of-type { }          /* 해당 타입의 유일한 요소 */
:empty { }                 /* 비어있는 요소 */
:root { }                  /* 루트 요소 */

/* 폼 관련 */
:enabled { }               /* 활성화된 입력 요소 */
:disabled { }              /* 비활성화된 입력 요소 */
:checked { }               /* 체크된 요소 */
:required { }              /* 필수 입력 요소 */
:optional { }              /* 선택적 입력 요소 */
:valid { }                 /* 유효한 입력 */
:invalid { }               /* 유효하지 않은 입력 */
:in-range { }              /* 범위 내 값 */
:out-of-range { }          /* 범위 외 값 */
:read-only { }             /* 읽기 전용 */
:read-write { }            /* 읽기/쓰기 가능 */

/* 기타 */
:not(selector) { }         /* 부정 선택자 */
:target { }                /* 현재 타겟 */
:lang(ko) { }              /* 언어 선택자 */
```

### 의사 요소 (Pseudo-elements)
```css
::before { content: ""; }  /* 요소 앞에 내용 삽입 */
::after { content: ""; }   /* 요소 뒤에 내용 삽입 */
::first-line { }           /* 첫 번째 줄 */
::first-letter { }         /* 첫 번째 글자 */
::selection { }            /* 선택된 텍스트 */
::marker { }               /* 목록 마커 */
::placeholder { }          /* 플레이스홀더 텍스트 */
```

## 색상 (Colors)

```css
/* 색상 표현 방법 */
color: red;                          /* 색상 이름 */
color: #ff0000;                      /* HEX 6자리 */
color: #f00;                         /* HEX 3자리 */
color: rgb(255, 0, 0);               /* RGB */
color: rgba(255, 0, 0, 0.5);         /* RGBA (투명도) */
color: hsl(0, 100%, 50%);            /* HSL */
color: hsla(0, 100%, 50%, 0.5);      /* HSLA */
color: transparent;                   /* 투명 */
color: currentColor;                  /* 현재 색상 상속 */
```

## 텍스트 (Text)

```css
/* 글꼴 */
font-family: 'Arial', sans-serif;    /* 글꼴 종류 */
font-size: 16px;                     /* 글꼴 크기 */
font-weight: normal | bold | 100-900; /* 글꼴 두께 */
font-style: normal | italic | oblique; /* 글꼴 스타일 */
font-variant: small-caps;            /* 작은 대문자 */
font: italic bold 16px/1.5 Arial;    /* 단축 속성 */

/* 텍스트 스타일 */
color: #333;                          /* 글자 색상 */
text-align: left | right | center | justify; /* 정렬 */
text-decoration: none | underline | overline | line-through; /* 장식 */
text-transform: uppercase | lowercase | capitalize; /* 대소문자 변환 */
text-indent: 20px;                    /* 들여쓰기 */
text-shadow: 2px 2px 4px rgba(0,0,0,0.5); /* 그림자 */
letter-spacing: 2px;                  /* 자간 */
word-spacing: 5px;                    /* 단어 간격 */
line-height: 1.5;                     /* 줄 높이 */
white-space: normal | nowrap | pre | pre-wrap; /* 공백 처리 */
word-break: normal | break-all | keep-all; /* 단어 줄바꿈 */
word-wrap: normal | break-word;       /* 단어 래핑 */
text-overflow: clip | ellipsis;       /* 텍스트 오버플로우 */
```

## 박스 모델 (Box Model)

```css
/* 크기 */
width: 100px | 50% | auto;           /* 너비 */
height: 100px | 50% | auto;          /* 높이 */
min-width: 100px;                    /* 최소 너비 */
max-width: 100px;                    /* 최대 너비 */
min-height: 100px;                   /* 최소 높이 */
max-height: 100px;                   /* 최대 높이 */

/* 여백 */
margin: 10px;                         /* 모든 방향 */
margin: 10px 20px;                    /* 상하 좌우 */
margin: 10px 20px 30px;               /* 상 좌우 하 */
margin: 10px 20px 30px 40px;          /* 상 우 하 좌 */
margin-top: 10px;
margin-right: 10px;
margin-bottom: 10px;
margin-left: 10px;
margin: 0 auto;                       /* 가운데 정렬 */

/* 패딩 */
padding: 10px;                        /* 모든 방향 */
padding: 10px 20px;                   /* 상하 좌우 */
padding: 10px 20px 30px;              /* 상 좌우 하 */
padding: 10px 20px 30px 40px;         /* 상 우 하 좌 */
padding-top: 10px;
padding-right: 10px;
padding-bottom: 10px;
padding-left: 10px;

/* 박스 사이징 */
box-sizing: content-box | border-box;
```

## 테두리 (Border)

```css
/* 기본 테두리 */
border: 1px solid black;              /* 단축 속성 */
border-width: 1px | thin | medium | thick;
border-style: none | solid | dashed | dotted | double | groove | ridge | inset | outset;
border-color: #000;

/* 개별 방향 */
border-top: 1px solid black;
border-right: 1px solid black;
border-bottom: 1px solid black;
border-left: 1px solid black;

/* 모서리 둥글게 */
border-radius: 5px;                   /* 모든 모서리 */
border-radius: 5px 10px;               /* 좌상우하 우상좌하 */
border-radius: 5px 10px 15px 20px;    /* 좌상 우상 우하 좌하 */
border-radius: 50%;                   /* 원형 */

/* 개별 모서리 */
border-top-left-radius: 5px;
border-top-right-radius: 5px;
border-bottom-right-radius: 5px;
border-bottom-left-radius: 5px;
```

## 배경 (Background)

```css
background-color: #f0f0f0;
background-image: url('image.jpg');
background-repeat: repeat | repeat-x | repeat-y | no-repeat;
background-position: top left | center | 50% 50% | 10px 20px;
background-size: auto | cover | contain | 100px 200px | 50%;
background-attachment: scroll | fixed | local;
background-origin: padding-box | border-box | content-box;
background-clip: padding-box | border-box | content-box;

/* 단축 속성 */
background: #f0f0f0 url('image.jpg') no-repeat center/cover;

/* 다중 배경 */
background-image: url('img1.jpg'), url('img2.jpg');

/* 그라디언트 */
background: linear-gradient(to right, red, blue);
background: linear-gradient(45deg, red, blue);
background: linear-gradient(red 0%, blue 50%, green 100%);
background: radial-gradient(circle, red, blue);
background: conic-gradient(from 45deg, red, blue, green);
```

## 레이아웃 (Layout)

### Display
```css
display: none;                        /* 숨김 */
display: block;                       /* 블록 */
display: inline;                      /* 인라인 */
display: inline-block;                /* 인라인 블록 */
display: flex;                        /* 플렉스박스 */
display: inline-flex;                 /* 인라인 플렉스 */
display: grid;                        /* 그리드 */
display: inline-grid;                 /* 인라인 그리드 */
display: table;                       /* 테이블 */
```

### Position
```css
position: static;                     /* 기본값 */
position: relative;                   /* 상대 위치 */
position: absolute;                   /* 절대 위치 */
position: fixed;                      /* 고정 위치 */
position: sticky;                     /* 스티키 */

/* 위치 속성 */
top: 10px;
right: 10px;
bottom: 10px;
left: 10px;
z-index: 100;                        /* 쌓임 순서 */
```

### Float & Clear
```css
float: left | right | none;
clear: left | right | both | none;
```

### Overflow
```css
overflow: visible | hidden | scroll | auto;
overflow-x: visible | hidden | scroll | auto;
overflow-y: visible | hidden | scroll | auto;
```

### Visibility
```css
visibility: visible | hidden | collapse;
opacity: 0.5;                         /* 0-1 투명도 */
```

## Flexbox

### 컨테이너 속성
```css
display: flex;
flex-direction: row | row-reverse | column | column-reverse;
flex-wrap: nowrap | wrap | wrap-reverse;
flex-flow: row wrap;                 /* direction + wrap 단축 */
justify-content: flex-start | flex-end | center | space-between | space-around | space-evenly;
align-items: stretch | flex-start | flex-end | center | baseline;
align-content: flex-start | flex-end | center | space-between | space-around | stretch;
gap: 10px;                           /* 아이템 간격 */
row-gap: 10px;
column-gap: 10px;
```

### 아이템 속성
```css
order: 1;                            /* 순서 */
flex-grow: 1;                        /* 늘어나는 비율 */
flex-shrink: 1;                      /* 줄어드는 비율 */
flex-basis: auto | 100px;            /* 기본 크기 */
flex: 1 1 auto;                      /* grow shrink basis 단축 */
align-self: auto | flex-start | flex-end | center | baseline | stretch;
```

## Grid

### 컨테이너 속성
```css
display: grid;
grid-template-columns: 100px 200px auto;
grid-template-columns: repeat(3, 1fr);
grid-template-columns: minmax(100px, 1fr);
grid-template-rows: 100px 200px;
grid-template-areas: 
    "header header"
    "sidebar main"
    "footer footer";
grid-template: rows / columns;       /* 단축 속성 */

grid-column-gap: 10px;
grid-row-gap: 10px;
grid-gap: 10px;                      /* 단축 속성 */
gap: 10px 20px;                      /* 행 열 간격 */

justify-items: start | end | center | stretch;
align-items: start | end | center | stretch;
place-items: center;                 /* align + justify 단축 */

justify-content: start | end | center | stretch | space-around | space-between | space-evenly;
align-content: start | end | center | stretch | space-around | space-between | space-evenly;
place-content: center;                /* align + justify 단축 */

grid-auto-columns: 100px;
grid-auto-rows: 100px;
grid-auto-flow: row | column | dense;
```

### 아이템 속성
```css
grid-column-start: 1;
grid-column-end: 3;
grid-column: 1 / 3;                  /* start / end 단축 */
grid-column: span 2;                 /* 2칸 차지 */

grid-row-start: 1;
grid-row-end: 3;
grid-row: 1 / 3;                     /* start / end 단축 */

grid-area: 1 / 1 / 3 / 3;            /* row-start / col-start / row-end / col-end */
grid-area: header;                   /* template-areas 이름 */

justify-self: start | end | center | stretch;
align-self: start | end | center | stretch;
place-self: center;                  /* align + justify 단축 */
```

## 변형 (Transform)

```css
/* 2D 변형 */
transform: translate(50px, 100px);   /* 이동 */
transform: translateX(50px);
transform: translateY(100px);
transform: scale(2, 0.5);            /* 크기 조절 */
transform: scaleX(2);
transform: scaleY(0.5);
transform: rotate(45deg);            /* 회전 */
transform: skew(10deg, 20deg);       /* 기울이기 */
transform: skewX(10deg);
transform: skewY(20deg);
transform: matrix(1, 0, 0, 1, 0, 0);

/* 3D 변형 */
transform: translate3d(50px, 100px, 20px);
transform: translateZ(20px);
transform: scale3d(2, 0.5, 1);
transform: scaleZ(1);
transform: rotate3d(1, 0, 0, 45deg);
transform: rotateX(45deg);
transform: rotateY(45deg);
transform: rotateZ(45deg);
transform: perspective(100px);

/* 변형 기준점 */
transform-origin: center;            /* 기본값 */
transform-origin: top left;
transform-origin: 50% 50%;
transform-origin: 10px 20px;

/* 3D 스타일 */
transform-style: flat | preserve-3d;
backface-visibility: visible | hidden;
perspective: 1000px;
perspective-origin: 50% 50%;
```

## 전환 (Transition)

```css
transition-property: all | width | color;
transition-duration: 0.3s | 300ms;
transition-timing-function: ease | linear | ease-in | ease-out | ease-in-out | cubic-bezier(0.1, 0.7, 1.0, 0.1);
transition-delay: 0.5s;

/* 단축 속성 */
transition: all 0.3s ease 0.5s;
transition: width 0.3s, height 0.5s;
```

## 애니메이션 (Animation)

```css
/* 키프레임 정의 */
@keyframes slidein {
    from { transform: translateX(0%); }
    to { transform: translateX(100%); }
}

@keyframes bounce {
    0% { transform: translateY(0); }
    50% { transform: translateY(-100px); }
    100% { transform: translateY(0); }
}

/* 애니메이션 적용 */
animation-name: slidein;
animation-duration: 3s;
animation-timing-function: ease;
animation-delay: 1s;
animation-iteration-count: infinite | 3;
animation-direction: normal | reverse | alternate | alternate-reverse;
animation-fill-mode: none | forwards | backwards | both;
animation-play-state: running | paused;

/* 단축 속성 */
animation: slidein 3s ease 1s infinite alternate;
```

## 필터 (Filter)

```css
filter: blur(5px);
filter: brightness(1.5);
filter: contrast(200%);
filter: grayscale(100%);
filter: hue-rotate(90deg);
filter: invert(100%);
filter: opacity(50%);
filter: saturate(200%);
filter: sepia(100%);
filter: drop-shadow(5px 5px 10px rgba(0,0,0,0.5));

/* 다중 필터 */
filter: blur(5px) brightness(1.5) contrast(200%);
```

## 기타 유용한 속성

### 커서
```css
cursor: auto | default | pointer | move | text | wait | help | crosshair | not-allowed | grab | grabbing;
```

### 리스트
```css
list-style-type: disc | circle | square | decimal | lower-alpha | upper-alpha | none;
list-style-position: inside | outside;
list-style-image: url('marker.png');
list-style: square inside url('marker.png');
```

### 테이블
```css
border-collapse: collapse | separate;
border-spacing: 5px;
caption-side: top | bottom;
empty-cells: show | hide;
table-layout: auto | fixed;
```

### 아웃라인
```css
outline: 2px solid red;
outline-width: 2px;
outline-style: solid;
outline-color: red;
outline-offset: 5px;
```

### 리사이즈
```css
resize: none | both | horizontal | vertical;
```

### 박스 그림자
```css
box-shadow: 5px 5px 10px rgba(0,0,0,0.5);
box-shadow: inset 5px 5px 10px rgba(0,0,0,0.5);
box-shadow: 5px 5px 10px red, -5px -5px 10px blue; /* 다중 그림자 */
```

### 스크롤 동작
```css
scroll-behavior: auto | smooth;
scroll-snap-type: x mandatory;
scroll-snap-align: start | center | end;
```

## 미디어 쿼리 (Media Queries)

```css
/* 기본 구조 */
@media screen and (max-width: 768px) {
    /* 스타일 */
}

/* 일반적인 브레이크포인트 */
/* 모바일 */
@media (max-width: 576px) { }

/* 태블릿 */
@media (min-width: 577px) and (max-width: 768px) { }

/* 데스크톱 */
@media (min-width: 769px) { }

/* 기타 미디어 쿼리 */
@media print { }                     /* 인쇄 */
@media (orientation: portrait) { }   /* 세로 방향 */
@media (orientation: landscape) { }  /* 가로 방향 */
@media (prefers-color-scheme: dark) { } /* 다크 모드 */
@media (prefers-reduced-motion: reduce) { } /* 애니메이션 감소 */
```

## CSS 변수 (Custom Properties)

```css
/* 변수 정의 */
:root {
    --primary-color: #007bff;
    --secondary-color: #6c757d;
    --spacing: 1rem;
    --border-radius: 4px;
}

/* 변수 사용 */
.element {
    color: var(--primary-color);
    padding: var(--spacing);
    border-radius: var(--border-radius);
    background: var(--bg-color, #fff); /* 기본값 설정 */
}

/* 변수 재정의 */
.dark-theme {
    --primary-color: #4dabf7;
}
```

## 단위 (Units)

```css
/* 절대 단위 */
px                    /* 픽셀 */
pt                    /* 포인트 (1pt = 1/72 inch) */
pc                    /* 파이카 (1pc = 12pt) */
cm                    /* 센티미터 */
mm                    /* 밀리미터 */
in                    /* 인치 */

/* 상대 단위 */
%                     /* 퍼센트 */
em                    /* 부모 요소의 font-size */
rem                   /* 루트 요소의 font-size */
vw                    /* 뷰포트 너비의 1% */
vh                    /* 뷰포트 높이의 1% */
vmin                  /* vw와 vh 중 작은 값 */
vmax                  /* vw와 vh 중 큰 값 */
ex                    /* 현재 폰트의 x-height */
ch                    /* 현재 폰트의 0의 너비 */
```

## 함수 (Functions)

```css
/* 색상 함수 */
rgb(255, 0, 0)
rgba(255, 0, 0, 0.5)
hsl(0, 100%, 50%)
hsla(0, 100%, 50%, 0.5)

/* 계산 함수 */
calc(100% - 20px)
min(100px, 50%)
max(100px, 50%)
clamp(100px, 50%, 300px)    /* min, preferred, max */

/* 변형 함수 */
translateX(100px)
scale(1.5)
rotate(45deg)

/* 필터 함수 */
blur(5px)
brightness(1.5)

/* 기타 */
url('image.jpg')
var(--custom-property)
attr(data-content)
counter(section-counter)
```

## 우선순위 (Specificity)

```css
/* 우선순위 계산 */
인라인 스타일:         1000점
ID 선택자 (#id):       100점
클래스 선택자 (.class): 10점
속성 선택자 ([attr]):  10점
의사 클래스 (:hover):   10점
요소 선택자 (div):      1점
전체 선택자 (*):        0점

/* !important는 모든 것을 무시 */
color: red !important;
```

## 유용한 팁

1. **CSS Reset/Normalize**: 브라우저 기본 스타일 초기화
2. **BEM 방법론**: Block__Element--Modifier 네이밍 규칙
3. **모바일 우선**: min-width 미디어 쿼리 사용
4. **CSS 변수 활용**: 테마 전환, 반복값 관리
5. **성능 최적화**: will-change, transform 사용으로 GPU 가속
6. **접근성**: focus 스타일, 충분한 색상 대비
7. **플렉스박스 vs 그리드**: 1차원은 Flex, 2차원은 Grid