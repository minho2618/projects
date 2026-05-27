#JavaScript 

## 변수 선언

```javascript
let x = 1;         // 재할당 가능, 블록 스코프
const y = 2;       // 재할당 불가, 블록 스코프
var z = 3;         // 재할당 가능, 함수 스코프 (레거시)
```

## 데이터 타입

### 원시 타입 (Primitive)

```javascript
let num = 123;                  // Number
let str = "Hello";              // String
let bool = true;                // Boolean
let undef = undefined;          // Undefined
let nul = null;                 // Null
let sym = Symbol('id');         // Symbol
let bigInt = 123n;              // BigInt
```

### 참조 타입 (Reference)

```javascript
let arr = [1, 2, 3];            // Array
let obj = {name: "John"};       // Object
let func = function() {};       // Function
let date = new Date();          // Date
let regex = /ab+c/;             // RegExp
```

## 연산자

### 산술 연산자

```javascript
+  // 더하기
-  // 빼기
*  // 곱하기
/  // 나누기
%  // 나머지
** // 거듭제곱
++ // 증가
-- // 감소
```

### 비교 연산자

```javascript
==   // 동등 (타입 변환 허용)
===  // 일치 (타입까지 같아야 함)
!=   // 부등
!==  // 불일치
>    // 초과
<    // 미만
>=   // 이상
<=   // 이하
```

### 논리 연산자

```javascript
&&   // AND
||   // OR
!    // NOT
??   // Nullish coalescing (null/undefined 체크)
```

## 조건문

```javascript
// if-else
if (condition) {
  // code
} else if (condition2) {
  // code
} else {
  // code
}

// 삼항 연산자
let result = condition ? value1 : value2;

// switch
switch (expression) {
  case value1:
    // code
    break;
  case value2:
    // code
    break;
  default:
    // code
}
```

## 반복문

```javascript
// for
for (let i = 0; i < 5; i++) {
  console.log(i);
}

// while
while (condition) {
  // code
}

// do-while
do {
  // code
} while (condition);

// for...of (배열 순회)
for (let item of array) {
  console.log(item);
}

// for...in (객체 속성 순회)
for (let key in object) {
  console.log(key, object[key]);
}
```

## 함수

```javascript
// 함수 선언문
function add(a, b) {
  return a + b;
}

// 함수 표현식
const multiply = function(a, b) {
  return a * b;
};

// 화살표 함수
const divide = (a, b) => a / b;
const greet = name => `Hello ${name}`;
const complex = (a, b) => {
  let result = a + b;
  return result * 2;
};

// 기본 매개변수
function power(base, exponent = 2) {
  return base ** exponent;
}

// Rest 매개변수
function sum(...numbers) {
  return numbers.reduce((a, b) => a + b, 0);
}
```

## 배열 메서드

```javascript
let arr = [1, 2, 3, 4, 5];

// 변형 메서드 (원본 배열 변경)
arr.push(6);           // 끝에 추가
arr.pop();             // 끝에서 제거
arr.unshift(0);        // 앞에 추가
arr.shift();           // 앞에서 제거
arr.splice(1, 2);      // 인덱스 1부터 2개 제거
arr.reverse();         // 순서 뒤집기
arr.sort();            // 정렬

// 비변형 메서드 (새 배열 반환)
arr.slice(1, 3);       // 부분 배열 추출
arr.concat([6, 7]);    // 배열 합치기
arr.join(', ');        // 문자열로 변환

// 반복 메서드
arr.forEach(item => console.log(item));
arr.map(x => x * 2);              // 변환된 새 배열
arr.filter(x => x > 2);           // 조건에 맞는 요소만
arr.find(x => x > 2);             // 조건에 맞는 첫 요소
arr.findIndex(x => x > 2);        // 조건에 맞는 첫 인덱스
arr.reduce((acc, cur) => acc + cur, 0);  // 누적 계산
arr.some(x => x > 3);             // 하나라도 조건 만족?
arr.every(x => x > 0);            // 모두 조건 만족?
arr.includes(3);                   // 포함 여부
```

## 객체

```javascript
// 객체 생성
let person = {
  name: "John",
  age: 30,
  greet() {
    return `Hello, I'm ${this.name}`;
  }
};

// 속성 접근
person.name;        // 점 표기법
person['age'];      // 대괄호 표기법

// 속성 추가/수정/삭제
person.email = "john@example.com";
person.age = 31;
delete person.email;

// 객체 메서드
Object.keys(person);       // 키 배열
Object.values(person);     // 값 배열
Object.entries(person);    // [키, 값] 배열
Object.assign({}, person); // 객체 복사
Object.freeze(person);     // 객체 동결
Object.seal(person);       // 객체 봉인
```

## 구조 분해 할당

```javascript
// 배열 구조 분해
let [a, b, ...rest] = [1, 2, 3, 4, 5];
// a = 1, b = 2, rest = [3, 4, 5]

// 객체 구조 분해
let {name, age, city = "Seoul"} = person;
let {name: userName, age: userAge} = person;  // 이름 변경

// 함수 매개변수 구조 분해
function display({name, age}) {
  console.log(`${name} is ${age} years old`);
}
```

## 스프레드 연산자

```javascript
// 배열 스프레드
let arr1 = [1, 2, 3];
let arr2 = [...arr1, 4, 5];        // [1, 2, 3, 4, 5]
let arr3 = [...arr1, ...arr2];     // 배열 합치기

// 객체 스프레드
let obj1 = {a: 1, b: 2};
let obj2 = {...obj1, c: 3};        // {a: 1, b: 2, c: 3}
let obj3 = {...obj1, b: 3};        // {a: 1, b: 3} (덮어쓰기)

// 함수 호출
Math.max(...[1, 2, 3]);             // Math.max(1, 2, 3)
```

## 클래스

```javascript
class Animal {
  constructor(name) {
    this.name = name;
  }
  
  speak() {
    return `${this.name} makes a sound`;
  }
  
  static info() {
    return "Animals are living beings";
  }
}

class Dog extends Animal {
  constructor(name, breed) {
    super(name);
    this.breed = breed;
  }
  
  speak() {
    return `${this.name} barks`;
  }
  
  get description() {
    return `${this.name} is a ${this.breed}`;
  }
  
  set nickname(value) {
    this.nick = value;
  }
}

let dog = new Dog("Max", "Golden Retriever");
```

## 비동기 처리

### Promise

```javascript
// Promise 생성
let promise = new Promise((resolve, reject) => {
  setTimeout(() => resolve("Done!"), 1000);
});

// Promise 사용
promise
  .then(result => console.log(result))
  .catch(error => console.error(error))
  .finally(() => console.log("Finished"));

// Promise 메서드
Promise.all([promise1, promise2]);     // 모두 완료 대기
Promise.race([promise1, promise2]);    // 가장 빠른 것
Promise.allSettled([p1, p2]);         // 모든 결과 대기
```

### Async/Await

```javascript
// async 함수
async function fetchData() {
  try {
    let response = await fetch('/api/data');
    let data = await response.json();
    return data;
  } catch (error) {
    console.error('Error:', error);
  }
}

// 사용
fetchData().then(data => console.log(data));

// 병렬 처리
async function parallel() {
  let [result1, result2] = await Promise.all([
    fetch('/api/1'),
    fetch('/api/2')
  ]);
}
```

## 모듈

```javascript
// 내보내기 (export.js)
export const PI = 3.14159;
export function add(a, b) { return a + b; }
export default class Calculator {}

// 가져오기 (import.js)
import Calculator from './export.js';        // 기본 내보내기
import { PI, add } from './export.js';       // 명명된 내보내기
import * as math from './export.js';         // 모든 것
import { add as sum } from './export.js';    // 별칭
```

## 에러 처리

```javascript
try {
  // 위험한 코드
  throw new Error("Something went wrong");
} catch (error) {
  console.error(error.message);
} finally {
  // 항상 실행
  console.log("Cleanup");
}

// 커스텀 에러
class CustomError extends Error {
  constructor(message) {
    super(message);
    this.name = "CustomError";
  }
}
```

## 정규 표현식

```javascript
// 생성
let regex1 = /pattern/flags;
let regex2 = new RegExp('pattern', 'flags');

// 플래그
// g - 전역 검색
// i - 대소문자 무시
// m - 여러 줄

// 메서드
regex.test(str);           // 매칭 여부
str.match(regex);          // 매칭 결과
str.replace(regex, '');    // 치환
str.search(regex);         // 위치 검색
str.split(regex);          // 분할

// 패턴 예시
/^hello/        // hello로 시작
/world$/        // world로 끝
/[a-z]/         // 소문자
/[0-9]+/        // 숫자 하나 이상
/\d{3}-\d{4}/   // 000-0000 형식
/\w+@\w+\.\w+/  // 간단한 이메일
```

## 유용한 내장 객체

### Math

```javascript
Math.PI;              // 3.14159...
Math.abs(-5);         // 5
Math.round(4.7);      // 5
Math.floor(4.7);      // 4
Math.ceil(4.3);       // 5
Math.max(1, 2, 3);    // 3
Math.min(1, 2, 3);    // 1
Math.random();        // 0 ~ 1 사이 랜덤
Math.pow(2, 3);       // 8
Math.sqrt(16);        // 4
```

### Date

```javascript
let now = new Date();
let date = new Date('2024-01-01');

date.getFullYear();     // 연도
date.getMonth();        // 월 (0-11)
date.getDate();         // 일
date.getHours();        // 시
date.getMinutes();      // 분
date.getSeconds();      // 초
date.getTime();         // 타임스탬프
date.toISOString();     // ISO 문자열
```

### JSON

```javascript
// 직렬화
JSON.stringify(object);
JSON.stringify(object, null, 2);  // 들여쓰기

// 역직렬화
JSON.parse(jsonString);
```

## 타입 체크

```javascript
typeof 123;              // "number"
typeof "Hello";          // "string"
typeof true;             // "boolean"
typeof undefined;        // "undefined"
typeof null;             // "object" (버그)
typeof {};               // "object"
typeof [];               // "object"
typeof function(){};     // "function"

// 더 정확한 타입 체크
Array.isArray([]);       // true
obj instanceof Date;     // true/false
Number.isNaN(NaN);       // true
Number.isFinite(123);    // true
```

## DOM (Document Object Model)

### 요소 선택

```javascript
// 단일 요소 선택
document.getElementById('id');                    // ID로 선택
document.querySelector('.class');                 // CSS 선택자 (첫 번째)
document.querySelector('[data-id="123"]');        // 속성 선택자

// 여러 요소 선택
document.getElementsByClassName('class');         // 클래스명 (HTMLCollection)
document.getElementsByTagName('div');             // 태그명 (HTMLCollection)
document.querySelectorAll('.class');             // CSS 선택자 (NodeList)
document.getElementsByName('name');               // name 속성

// 관계 선택
element.parentElement;           // 부모 요소
element.children;                // 자식 요소들 (HTMLCollection)
element.firstElementChild;       // 첫 번째 자식
element.lastElementChild;        // 마지막 자식
element.nextElementSibling;      // 다음 형제
element.previousElementSibling;  // 이전 형제
element.closest('.class');       // 가장 가까운 조상
```

### 요소 생성 및 추가

```javascript
// 요소 생성
let div = document.createElement('div');
let text = document.createTextNode('Hello');
let fragment = document.createDocumentFragment();

// 요소 추가
parent.appendChild(child);              // 마지막에 추가
parent.insertBefore(newChild, refChild); // 특정 위치에 삽입
parent.prepend(child);                  // 첫 번째로 추가
parent.append(child);                   // 마지막에 추가
element.after(newElement);              // 요소 뒤에 추가
element.before(newElement);             // 요소 앞에 추가
parent.insertAdjacentHTML('beforeend', '<div>HTML</div>');
// 위치: beforebegin, afterbegin, beforeend, afterend

// 요소 복제
let clone = element.cloneNode(true);    // deep copy
let shallowClone = element.cloneNode(false);

// 요소 제거
element.remove();                       // 자기 자신 제거
parent.removeChild(child);              // 자식 제거
element.replaceWith(newElement);        // 교체
parent.replaceChild(newChild, oldChild);
```

### 내용 조작

```javascript
// 텍스트 내용
element.textContent = 'Plain text';     // 텍스트만
element.innerText = 'Visible text';     // 보이는 텍스트만

// HTML 내용
element.innerHTML = '<strong>HTML</strong>';  // HTML 파싱
element.outerHTML = '<div>Replace</div>';     // 요소 자체를 교체

// 속성 값
element.value;                          // input, textarea 값
element.checked;                        // checkbox, radio 상태
element.selected;                       // option 선택 상태
```

### 속성 조작

```javascript
// 속성 읽기/쓰기
element.getAttribute('href');
element.setAttribute('href', 'https://example.com');
element.removeAttribute('href');
element.hasAttribute('href');

// 데이터 속성
element.dataset.userId;                 // data-user-id 읽기
element.dataset.userId = '123';         // data-user-id 설정
delete element.dataset.userId;          // data-user-id 제거

// 특수 속성 직접 접근
element.id = 'myId';
element.className = 'class1 class2';
element.href;                           // a 태그
element.src;                            // img, script
element.disabled = true;                // 폼 요소
```

### 클래스 조작

```javascript
// classList API
element.classList.add('new-class');
element.classList.remove('old-class');
element.classList.toggle('active');
element.classList.contains('active');   // true/false
element.classList.replace('old', 'new');

// 여러 클래스 한 번에
element.classList.add('class1', 'class2', 'class3');
element.classList.remove('class1', 'class2');
```

### 스타일 조작

```javascript
// 인라인 스타일
element.style.color = 'red';
element.style.backgroundColor = '#fff';
element.style.fontSize = '16px';
element.style.cssText = 'color: red; background: white;';

// 계산된 스타일 읽기
let styles = window.getComputedStyle(element);
let color = styles.getPropertyValue('color');
let fontSize = styles.fontSize;

// CSS 변수
element.style.setProperty('--main-color', 'blue');
element.style.getPropertyValue('--main-color');
element.style.removeProperty('--main-color');
```

### 이벤트 처리

```javascript
// 이벤트 리스너 추가
element.addEventListener('click', handler);
element.addEventListener('click', handler, { once: true });  // 한 번만
element.addEventListener('click', handler, { capture: true }); // 캡처링

// 이벤트 리스너 제거
element.removeEventListener('click', handler);

// 이벤트 객체
function handler(event) {
  event.target;               // 이벤트 발생 요소
  event.currentTarget;        // 리스너가 붙은 요소
  event.preventDefault();     // 기본 동작 방지
  event.stopPropagation();    // 버블링 중단
  event.stopImmediatePropagation(); // 다른 리스너도 중단
  
  // 마우스 이벤트
  event.clientX, event.clientY;     // 뷰포트 기준
  event.pageX, event.pageY;         // 페이지 기준
  event.offsetX, event.offsetY;     // 요소 기준
  
  // 키보드 이벤트
  event.key;                  // 'Enter', 'Escape', 'a'
  event.code;                 // 'Enter', 'Escape', 'KeyA'
  event.altKey, event.ctrlKey, event.shiftKey;
}

// 이벤트 위임
document.addEventListener('click', function(e) {
  if (e.target.matches('.button')) {
    // 동적으로 추가된 .button 요소도 처리
  }
});

// 커스텀 이벤트
let customEvent = new CustomEvent('myEvent', {
  detail: { data: 'custom data' },
  bubbles: true,
  cancelable: true
});
element.dispatchEvent(customEvent);
```

### 주요 이벤트 종류

```javascript
// 마우스 이벤트
'click'         // 클릭
'dblclick'      // 더블클릭
'mousedown'     // 마우스 버튼 누름
'mouseup'       // 마우스 버튼 뗌
'mousemove'     // 마우스 이동
'mouseenter'    // 마우스 진입 (버블링 X)
'mouseleave'    // 마우스 떠남 (버블링 X)
'mouseover'     // 마우스 진입 (버블링 O)
'mouseout'      // 마우스 떠남 (버블링 O)
'contextmenu'   // 우클릭 메뉴

// 키보드 이벤트
'keydown'       // 키 누름
'keyup'         // 키 뗌
'keypress'      // 키 누름 (deprecated)

// 폼 이벤트
'submit'        // 폼 제출
'reset'         // 폼 리셋
'input'         // 값 변경 (실시간)
'change'        // 값 변경 (포커스 이동 시)
'focus'         // 포커스 획득
'blur'          // 포커스 잃음
'select'        // 텍스트 선택

// 문서/윈도우 이벤트
'DOMContentLoaded'  // DOM 로드 완료
'load'          // 모든 리소스 로드 완료
'resize'        // 창 크기 변경
'scroll'        // 스크롤
'unload'        // 페이지 떠남
'beforeunload'  // 페이지 떠나기 전

// 기타 이벤트
'error'         // 에러 발생
'abort'         // 중단
'touchstart'    // 터치 시작
'touchend'      // 터치 끝
'touchmove'     // 터치 이동
'drag'          // 드래그
'drop'          // 드롭
```

### 폼 요소 다루기

```javascript
// 폼 접근
let form = document.forms['formName'];
let form = document.forms[0];

// 폼 요소 접근
let input = form.elements['inputName'];
let inputs = form.elements;

// 값 가져오기/설정
input.value = 'new value';
checkbox.checked = true;
radio.checked = false;
select.selectedIndex = 0;
select.value = 'optionValue';

// 폼 검증
input.checkValidity();      // 유효성 검사
form.checkValidity();        // 폼 전체 검사
input.setCustomValidity('Error message');
form.reportValidity();       // 검증 후 메시지 표시

// 폼 제출
form.submit();               // 프로그래밍적 제출
form.reset();                // 폼 리셋
```

### 위치 및 크기

```javascript
// 요소 크기
element.offsetWidth, element.offsetHeight;   // border 포함
element.clientWidth, element.clientHeight;   // padding 포함
element.scrollWidth, element.scrollHeight;   // 스크롤 영역 포함

// 요소 위치
element.offsetTop, element.offsetLeft;       // offsetParent 기준
element.getBoundingClientRect();             // 뷰포트 기준
// { top, right, bottom, left, width, height, x, y }

// 스크롤 위치
element.scrollTop, element.scrollLeft;       // 스크롤 위치
window.scrollX, window.scrollY;              // 페이지 스크롤
window.pageXOffset, window.pageYOffset;      // 별칭

// 스크롤 이동
element.scrollTo(x, y);
element.scrollTo({ top: 100, left: 0, behavior: 'smooth' });
element.scrollBy(x, y);
element.scrollIntoView();
element.scrollIntoView({ behavior: 'smooth', block: 'center' });
```

### DOM 탐색 및 검사

```javascript
// 노드 타입 확인
element.nodeType;       // 1: Element, 3: Text, 8: Comment
element.nodeName;       // 태그명 (대문자)
element.tagName;        // 태그명 (대문자)

// 콘텐츠 확인
element.hasChildNodes();
element.contains(otherElement);
element.matches('.selector');
element.compareDocumentPosition(other);

// 탐색 (노드 기준 - 텍스트 노드 포함)
element.parentNode;
element.childNodes;     // NodeList
element.firstChild;
element.lastChild;
element.nextSibling;
element.previousSibling;
```

### 성능 최적화 팁

```javascript
// DocumentFragment 사용 (리플로우 최소화)
let fragment = document.createDocumentFragment();
for (let i = 0; i < 100; i++) {
  let li = document.createElement('li');
  li.textContent = `Item ${i}`;
  fragment.appendChild(li);
}
list.appendChild(fragment);  // 한 번만 DOM 업데이트

// 이벤트 위임 사용
// 나쁜 예: 각 요소에 리스너
items.forEach(item => {
  item.addEventListener('click', handler);
});

// 좋은 예: 부모에 하나의 리스너
parent.addEventListener('click', e => {
  if (e.target.classList.contains('item')) {
    handler(e);
  }
});

// 스타일 변경 최소화
// 나쁜 예: 여러 번 변경
element.style.width = '100px';
element.style.height = '100px';
element.style.background = 'red';

// 좋은 예: 클래스 사용 또는 cssText
element.classList.add('styled');
// 또는
element.style.cssText = 'width: 100px; height: 100px; background: red;';
```

## 유용한 팁

```javascript
// Optional Chaining
user?.address?.street;   // undefined 대신 에러 방지

// Nullish Coalescing
let value = input ?? 'default';  // null/undefined일 때만 기본값

// 단축 평가
true && doSomething();    // 조건부 실행
false || setDefault();    // 기본값 설정

// 템플릿 리터럴
`Hello ${name}, you are ${age} years old`;

// 숫자 구분자
let million = 1_000_000;  // 가독성 향상

// 조건부 속성 추가
let obj = {
  name: "John",
  ...(age && { age })    // age가 truthy일 때만 추가
};
```
