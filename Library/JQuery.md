#JavaScript #JQuery
## 기본 구문

```javascript
$(document).ready(function() {
    // DOM이 준비되면 실행
});

// 축약형
$(function() {
    // DOM이 준비되면 실행
});
```

## 선택자 (Selectors)

### 기본 선택자

```javascript
$("*")              // 모든 요소
$("#id")            // ID 선택자
$(".class")         // 클래스 선택자
$("element")        // 요소 선택자
$("el1, el2")       // 다중 선택자
```

### 계층 선택자

```javascript
$("parent > child")     // 직접 자식
$("parent child")       // 모든 하위 요소
$("prev + next")        // 바로 다음 형제
$("prev ~ siblings")    // 모든 형제
```

### 속성 선택자

```javascript
$("[attribute]")           // 속성 보유
$("[attr='value']")        // 속성값 일치
$("[attr!='value']")       // 속성값 불일치
$("[attr^='value']")       // 속성값 시작
$("[attr$='value']")       // 속성값 끝
$("[attr*='value']")       // 속성값 포함
```

### 필터 선택자

```javascript
$(":first")         // 첫 번째 요소
$(":last")          // 마지막 요소
$(":even")          // 짝수 인덱스
$(":odd")           // 홀수 인덱스
$(":eq(n)")         // n번째 요소
$(":gt(n)")         // n보다 큰 인덱스
$(":lt(n)")         // n보다 작은 인덱스
$(":not(selector)") // 제외
```

## DOM 조작

### 내용 가져오기/설정하기

```javascript
// HTML 내용
.html()             // 가져오기
.html("content")    // 설정하기

// 텍스트 내용
.text()             // 가져오기
.text("content")    // 설정하기

// 값 (폼 요소)
.val()              // 가져오기
.val("value")       // 설정하기
```

### 속성 조작

```javascript
.attr("name")               // 속성 가져오기
.attr("name", "value")      // 속성 설정
.attr({                     // 여러 속성 설정
    name1: "value1",
    name2: "value2"
})
.removeAttr("name")         // 속성 제거

.prop("checked")            // 프로퍼티 가져오기
.prop("checked", true)      // 프로퍼티 설정
```

### 클래스 조작

```javascript
.addClass("class")          // 클래스 추가
.removeClass("class")       // 클래스 제거
.toggleClass("class")       // 클래스 토글
.hasClass("class")          // 클래스 확인 (true/false)
```

### CSS 조작

```javascript
.css("property")            // CSS 속성 가져오기
.css("property", "value")   // CSS 속성 설정
.css({                      // 여러 CSS 속성 설정
    property1: "value1",
    property2: "value2"
})
```

## DOM 탐색

### 부모/조상 탐색

```javascript
.parent()                   // 직접 부모
.parents()                  // 모든 조상
.parents("selector")        // 특정 조상
.parentsUntil("selector")   // 특정 조상까지
.closest("selector")        // 가장 가까운 조상
```

### 자식/하위 탐색

```javascript
.children()                 // 직접 자식
.children("selector")       // 특정 자식
.find("selector")           // 모든 하위 요소
```

### 형제 탐색

```javascript
.siblings()                 // 모든 형제
.siblings("selector")       // 특정 형제
.next()                     // 다음 형제
.nextAll()                  // 모든 다음 형제
.nextUntil("selector")      // 특정 형제까지
.prev()                     // 이전 형제
.prevAll()                  // 모든 이전 형제
.prevUntil("selector")      // 특정 형제까지
```

### 필터링

```javascript
.first()                    // 첫 번째 요소
.last()                     // 마지막 요소
.eq(n)                      // n번째 요소
.filter("selector")         // 필터링
.not("selector")            // 제외
.has("selector")            // 포함하는 요소
```

## DOM 조작 - 추가/제거

### 내부에 추가

```javascript
.append(content)            // 마지막에 추가
.prepend(content)           // 처음에 추가
.appendTo(target)           // 대상의 마지막에 추가
.prependTo(target)          // 대상의 처음에 추가
```

### 외부에 추가

```javascript
.after(content)             // 뒤에 추가
.before(content)            // 앞에 추가
.insertAfter(target)        // 대상 뒤에 추가
.insertBefore(target)       // 대상 앞에 추가
```

### 감싸기

```javascript
.wrap(element)              // 각각 감싸기
.wrapAll(element)           // 모두 감싸기
.wrapInner(element)         // 내부 감싸기
.unwrap()                   // 부모 제거
```

### 교체/제거

```javascript
.replaceWith(content)       // 교체
.replaceAll(target)         // 대상 교체
.remove()                   // 요소 제거
.empty()                    // 내용 비우기
.clone()                    // 복사
```

## 이벤트

### 기본 이벤트

```javascript
.click(function() {})       // 클릭
.dblclick(function() {})    // 더블클릭
.mouseenter(function() {})  // 마우스 진입
.mouseleave(function() {})  // 마우스 이탈
.hover(function() {},       // 마우스 호버
       function() {})
.focus(function() {})       // 포커스
.blur(function() {})        // 포커스 해제
```

### 이벤트 바인딩

```javascript
// on() 메서드
.on("click", function() {})
.on("click", selector, function() {})  // 위임
.on({                       // 여러 이벤트
    click: function() {},
    mouseenter: function() {}
})

// off() 메서드
.off("click")               // 이벤트 제거
.off()                      // 모든 이벤트 제거

// one() 메서드
.one("click", function() {}) // 한 번만 실행
```

### 이벤트 트리거

```javascript
.trigger("click")           // 이벤트 발생
.triggerHandler("click")    // 핸들러만 실행
```

## 효과

### 기본 효과

```javascript
.show()                     // 보이기
.show(duration)             // 애니메이션 보이기
.hide()                     // 숨기기
.hide(duration)             // 애니메이션 숨기기
.toggle()                   // 토글
.toggle(duration)           // 애니메이션 토글
```

### 페이드 효과

```javascript
.fadeIn()                   // 페이드 인
.fadeIn(duration)
.fadeOut()                  // 페이드 아웃
.fadeOut(duration)
.fadeToggle()               // 페이드 토글
.fadeToggle(duration)
.fadeTo(duration, opacity)  // 투명도 조절
```

### 슬라이드 효과

```javascript
.slideDown()                // 슬라이드 다운
.slideDown(duration)
.slideUp()                  // 슬라이드 업
.slideUp(duration)
.slideToggle()              // 슬라이드 토글
.slideToggle(duration)
```

### 커스텀 애니메이션

```javascript
.animate({                  // 애니메이션
    property: value
}, duration, easing, callback)

.stop()                     // 애니메이션 중지
.stop(clearQueue, jumpToEnd)
.delay(duration)            // 지연
.finish()                   // 애니메이션 완료
```

## AJAX

### 기본 AJAX

```javascript
$.ajax({
    url: "url",
    method: "GET/POST",
    data: { key: "value" },
    dataType: "json",
    success: function(data) {},
    error: function(xhr, status, error) {},
    complete: function() {}
});
```

### 간편 메서드

```javascript
$.get(url, data, success, dataType)
$.post(url, data, success, dataType)
$.getJSON(url, data, success)
$.getScript(url, success)
```

### AJAX 이벤트

```javascript
.ajaxStart(function() {})   // AJAX 시작
.ajaxStop(function() {})    // AJAX 종료
.ajaxComplete(function() {}) // AJAX 완료
.ajaxError(function() {})   // AJAX 에러
.ajaxSuccess(function() {}) // AJAX 성공
```

## 유틸리티

### 배열/객체

```javascript
$.each(array/object, function(index, value) {})
$.map(array, function(value, index) {})
$.grep(array, function(value, index) {})
$.merge(array1, array2)
$.unique(array)
$.inArray(value, array)
$.isArray(obj)
$.isPlainObject(obj)
```

### 타입 체크

```javascript
$.isFunction(obj)
$.isNumeric(obj)
$.isWindow(obj)
$.isEmptyObject(obj)
```

### 기타

```javascript
$.trim(string)              // 공백 제거
$.now()                     // 현재 시간
$.proxy(function, context)  // 컨텍스트 바인딩
$.extend(target, object)    // 객체 확장
```

## 폼 처리

### 폼 이벤트

```javascript
.submit(function() {})      // 제출
.change(function() {})      // 변경
.select(function() {})      // 선택
```

### 폼 필터

```javascript
$(":input")                 // 모든 입력 요소
$(":text")                  // 텍스트 입력
$(":password")              // 비밀번호 입력
$(":radio")                 // 라디오 버튼
$(":checkbox")              // 체크박스
$(":submit")                // 제출 버튼
$(":button")                // 버튼
$(":selected")              // 선택된 옵션
$(":checked")               // 체크된 요소
$(":disabled")              // 비활성화된 요소
$(":enabled")               // 활성화된 요소
```

### 폼 데이터

```javascript
.serialize()                // 폼 직렬화
.serializeArray()           // 폼 배열 직렬화
```

## 치수 및 위치

### 치수

```javascript
.width()                    // 너비
.height()                   // 높이
.innerWidth()               // 패딩 포함 너비
.innerHeight()              // 패딩 포함 높이
.outerWidth()               // 보더 포함 너비
.outerHeight()              // 보더 포함 높이
.outerWidth(true)           // 마진 포함 너비
.outerHeight(true)          // 마진 포함 높이
```

### 위치

```javascript
.offset()                   // 문서 기준 위치
.offset({ top: 10, left: 20 })
.position()                 // 부모 기준 위치
.scrollTop()                // 스크롤 위치
.scrollTop(value)
.scrollLeft()
.scrollLeft(value)
```

## 체이닝

```javascript
$("#element")
    .addClass("active")
    .css("color", "red")
    .fadeIn(300)
    .delay(1000)
    .fadeOut(300);
```

이 치트 시트는 jQuery의 가장 자주 사용되는 메서드들을 포함하고 있습니다. 각 메서드는 체이닝이 가능하며, 대부분의 메서드는 콜백 함수를 지원합니다.