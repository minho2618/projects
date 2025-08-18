## ✅ 1. EL (Expression Language) – 표현 언어

### 🔹 정의

- `${}` 문법으로 JSP에서 **자바 객체의 값에 접근**하는 언어
- 자바 코드를 `<% %>`로 작성하지 않고도, **간단하게 데이터 출력** 가능

### 🔹 사용 예시

```JSP
${user.name}           <!-- user 객체의 name 필드 --> 
${param.id}            <!-- request parameter 중 id --> 
${sessionScope.user}   <!-- 세션 영역에 있는 user --> 
${empty list}          <!-- 리스트가 비어있는지 확인 -->`
```

### 🔹 지원하는 기본 객체 (Implicit Objects)

|이름|설명|
|---|---|
|`param`|request 파라미터 (ex. `${param.name}`)|
|`sessionScope`|세션 영역의 속성|
|`applicationScope`|application 영역의 속성|
|`requestScope`|request 영역의 속성|
|`pageScope`|페이지 범위 속성|
|`cookie`|쿠키 값|
|`header`|HTTP 헤더 값|

---

## ✅ 2. JSTL (JSP Standard Tag Library)

### 🔹 정의

- JSP에서 자주 사용하는 로직을 **태그 형식으로 지원하는 라이브러리**
- 조건문, 반복문, 포맷팅, URL 처리 등을 **태그로 간결하게 표현** 가능

### 🔹 기본 선언 (JSP 상단에 추가)

```JSP
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```

---

### 🔹 주요 태그 예시

|태그|설명|예시|
|---|---|---|
|`<c:if>`|조건문|`<c:if test="${user != null}">로그인됨</c:if>`|
|`<c:choose>`|if-else|`<c:choose><c:when>...</c:when><c:otherwise>...</c:otherwise></c:choose>`|
|`<c:forEach>`|반복문|`<c:forEach var="item" items="${list}">...</c:forEach>`|
|`<c:set>`|변수 저장|`<c:set var="count" value="10" />`|
|`<c:out>`|값 출력 (`EL` 대신)|`<c:out value="${user.name}" />`|

---

## ✅ EL vs JSTL 요약 비교

|항목|EL (Expression Language)|JSTL (Tag Library)|
|---|---|---|
|역할|값 출력, 조건 확인 등 간단한 표현|흐름 제어, 반복, 변수 선언 등 로직 처리|
|문법|`${}`|`<c:태그>`|
|복잡한 로직|처리 어려움|처리 가능|

---

## ✅ 정리 요약

|목적|사용 방법|예시|
|---|---|---|
|값 출력|EL|`${user.name}`|
|조건 분기|JSTL `<c:if>`|`<c:if test="${user != null}">`|
|반복 출력|JSTL `<c:forEach>`|`<c:forEach var="item" items="${list}">`|
