#JSP #EL, #JSTL

## 1. JSP (JavaServer Pages) 기본

### JSP 지시어 (Directives)

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ page session="true" %>
<%@ page errorPage="error.jsp" %>
<%@ page isErrorPage="true" %>
<%@ page buffer="16kb" autoFlush="true" %>

<%@ include file="header.jsp" %>  <!-- 정적 포함 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
```

### JSP 스크립트 요소

```jsp
<%-- JSP 주석 --%>

<%! 
    // 선언문 (Declaration) - 멤버 변수, 메소드 선언
    private int count = 0;
    public String getInfo() {
        return "Info";
    }
%>

<% 
    // 스크립틀릿 (Scriptlet) - Java 코드 실행
    String name = request.getParameter("name");
    out.println("Hello " + name);
%>

<%= expression %>  <!-- 표현식 (Expression) - 출력 -->
```

### JSP 액션 태그

```jsp
<jsp:include page="footer.jsp" />  <!-- 동적 포함 -->
<jsp:include page="content.jsp">
    <jsp:param name="title" value="Welcome" />
</jsp:include>

<jsp:forward page="result.jsp" />
<jsp:forward page="result.jsp">
    <jsp:param name="status" value="success" />
</jsp:forward>

<jsp:useBean id="user" class="com.example.User" scope="session" />
<jsp:setProperty name="user" property="name" value="John" />
<jsp:setProperty name="user" property="*" />  <!-- 모든 파라미터 자동 매핑 -->
<jsp:getProperty name="user" property="name" />
```

### JSP 내장 객체

```jsp
<%
    // request - HttpServletRequest
    String param = request.getParameter("id");
    String[] values = request.getParameterValues("hobby");
    request.setAttribute("user", userObject);
    
    // response - HttpServletResponse
    response.sendRedirect("login.jsp");
    response.setContentType("text/html; charset=UTF-8");
    
    // session - HttpSession
    session.setAttribute("userId", "john123");
    session.getAttribute("userId");
    session.invalidate();
    
    // application - ServletContext
    application.setAttribute("counter", 100);
    
    // out - JspWriter
    out.println("Hello World");
    
    // config - ServletConfig
    String servletName = config.getServletName();
    
    // pageContext - PageContext
    pageContext.setAttribute("name", "value");
    
    // page - Object (현재 JSP 페이지 인스턴스)
    
    // exception - Throwable (errorPage에서만 사용)
%>
```

## 2. EL (Expression Language)

### EL 기본 문법

```jsp
${expression}
${10 + 20}
${user.name}
${user["name"]}
${sessionScope.user.email}
${param.id}
${empty user}
```

### EL 내장 객체

```jsp
<!-- Scope 객체 -->
${pageScope.attribute}        <!-- page 범위 -->
${requestScope.attribute}     <!-- request 범위 -->
${sessionScope.attribute}     <!-- session 범위 -->
${applicationScope.attribute} <!-- application 범위 -->

<!-- 파라미터 -->
${param.name}           <!-- request.getParameter("name") -->
${paramValues.hobby[0]} <!-- request.getParameterValues("hobby")[0] -->

<!-- 헤더 -->
${header["User-Agent"]}     <!-- request.getHeader("User-Agent") -->
${headerValues["Accept"][0]} <!-- 여러 값 중 첫 번째 -->

<!-- 쿠키 -->
${cookie.JSESSIONID.value}  <!-- 쿠키 값 -->

<!-- 초기화 파라미터 -->
${initParam.adminEmail}  <!-- context-param 값 -->

<!-- PageContext -->
${pageContext.request.contextPath}
${pageContext.request.method}
${pageContext.session.id}
```

### EL 연산자

```jsp
<!-- 산술 연산자 -->
${10 + 20}  ${10 - 5}  ${10 * 2}  
${10 / 3} 또는 ${10 div 3}
${10 % 3} 또는 ${10 mod 3}

<!-- 비교 연산자 -->
${10 == 10} 또는 ${10 eq 10}
${10 != 5}  또는 ${10 ne 5}
${10 < 20}  또는 ${10 lt 20}
${10 > 5}   또는 ${10 gt 5}
${10 <= 20} 또는 ${10 le 20}
${10 >= 5}  또는 ${10 ge 5}

<!-- 논리 연산자 -->
${true && false} 또는 ${true and false}
${true || false} 또는 ${true or false}
${!true}         또는 ${not true}

<!-- Empty 연산자 -->
${empty str}     <!-- null, "", 빈 컬렉션/배열 체크 -->
${not empty list}

<!-- 삼항 연산자 -->
${user.age >= 18 ? "성인" : "미성년"}
```

## 3. JSTL (JSP Standard Tag Library)

### Core 태그 라이브러리

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 변수 설정 -->
<c:set var="name" value="John" />
<c:set var="user" value="${userObject}" scope="session" />
<c:set target="${user}" property="age" value="25" />

<!-- 변수 제거 -->
<c:remove var="name" scope="page" />

<!-- 출력 -->
<c:out value="${user.name}" default="Guest" />
<c:out value="${user.bio}" escapeXml="true" />

<!-- 조건문 -->
<c:if test="${user.age >= 18}">
    <p>성인입니다.</p>
</c:if>

<c:choose>
    <c:when test="${score >= 90}">A</c:when>
    <c:when test="${score >= 80}">B</c:when>
    <c:when test="${score >= 70}">C</c:when>
    <c:otherwise>F</c:otherwise>
</c:choose>

<!-- 반복문 -->
<c:forEach var="item" items="${list}">
    ${item}<br>
</c:forEach>

<c:forEach var="i" begin="1" end="10" step="2">
    ${i}<br>
</c:forEach>

<c:forEach var="item" items="${list}" varStatus="status">
    ${status.index} / ${status.count} : ${item}
    ${status.first} / ${status.last}
</c:forEach>

<!-- 문자열 분할 -->
<c:forTokens var="token" items="apple,banana,orange" delims=",">
    ${token}<br>
</c:forTokens>

<!-- URL 관리 -->
<c:url var="myUrl" value="/user/profile">
    <c:param name="id" value="${userId}" />
</c:url>
<a href="${myUrl}">Profile</a>

<!-- 리다이렉트 -->
<c:redirect url="/login.jsp">
    <c:param name="error" value="true" />
</c:redirect>

<!-- 외부 자원 포함 -->
<c:import url="http://example.com/api/data" var="data" />

<!-- 예외 처리 -->
<c:catch var="exception">
    <% int x = 10/0; %>
</c:catch>
<c:if test="${exception != null}">
    Error: ${exception.message}
</c:if>
```

### Formatting 태그 라이브러리

```jsp
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 숫자 포맷 -->
<fmt:formatNumber value="12345.678" pattern="#,##0.00" />
<fmt:formatNumber value="0.85" type="percent" />
<fmt:formatNumber value="12345" type="currency" />
<fmt:formatNumber value="12345.678" maxFractionDigits="2" />

<fmt:parseNumber value="1,234.56" var="num" />

<!-- 날짜 포맷 -->
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" />
<fmt:formatDate value="${now}" type="date" dateStyle="full" />
<fmt:formatDate value="${now}" type="time" timeStyle="short" />
<fmt:formatDate value="${now}" type="both" />

<fmt:parseDate value="2024-01-15" pattern="yyyy-MM-dd" var="date" />

<!-- 시간대 설정 -->
<fmt:setTimeZone value="GMT+9" />
<fmt:timeZone value="America/New_York">
    <fmt:formatDate value="${now}" type="both" />
</fmt:timeZone>

<!-- 국제화 (i18n) -->
<fmt:setLocale value="ko_KR" />
<fmt:setBundle basename="messages" var="msg" />
<fmt:message key="welcome.message" />
<fmt:message key="greeting">
    <fmt:param value="${username}" />
</fmt:message>

<fmt:bundle basename="messages">
    <fmt:message key="label.title" />
</fmt:bundle>
```

### Functions 태그 라이브러리

```jsp
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

${fn:length(list)}                          <!-- 길이 -->
${fn:toUpperCase(str)}                      <!-- 대문자 변환 -->
${fn:toLowerCase(str)}                      <!-- 소문자 변환 -->
${fn:substring(str, 0, 5)}                  <!-- 부분 문자열 -->
${fn:substringAfter(str, ":")}              <!-- 특정 문자 이후 -->
${fn:substringBefore(str, ":")}             <!-- 특정 문자 이전 -->
${fn:replace(str, "old", "new")}            <!-- 문자열 치환 -->
${fn:indexOf(str, "search")}                <!-- 인덱스 찾기 -->
${fn:startsWith(str, "prefix")}             <!-- 시작 문자열 체크 -->
${fn:endsWith(str, "suffix")}               <!-- 끝 문자열 체크 -->
${fn:contains(str, "substring")}            <!-- 포함 여부 -->
${fn:containsIgnoreCase(str, "substring")}  <!-- 대소문자 무시 포함 -->
${fn:split(str, ",")}                       <!-- 문자열 분할 -->
${fn:join(array, ",")}                      <!-- 배열 결합 -->
${fn:trim(str)}                             <!-- 공백 제거 -->
${fn:escapeXml(str)}                        <!-- XML 이스케이프 -->
```

### SQL 태그 라이브러리 (참고용)

```jsp
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!-- 데이터소스 설정 -->
<sql:setDataSource var="dataSource" 
    driver="com.mysql.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/db"
    user="username" password="password" />

<!-- 쿼리 실행 -->
<sql:query dataSource="${dataSource}" var="result">
    SELECT * FROM users WHERE age > ?
    <sql:param value="18" />
</sql:query>

<!-- 결과 출력 -->
<c:forEach var="row" items="${result.rows}">
    ${row.name} - ${row.email}<br>
</c:forEach>

<!-- 업데이트/삽입/삭제 -->
<sql:update dataSource="${dataSource}">
    INSERT INTO users (name, email) VALUES (?, ?)
    <sql:param value="${param.name}" />
    <sql:param value="${param.email}" />
</sql:update>

<!-- 트랜잭션 -->
<sql:transaction dataSource="${dataSource}">
    <sql:update>...</sql:update>
    <sql:update>...</sql:update>
</sql:transaction>
```

## 4. 실용적인 예제 패턴

### 로그인 체크

```jsp
<c:if test="${empty sessionScope.user}">
    <c:redirect url="/login.jsp" />
</c:if>
```

### 페이징 처리

```jsp
<c:forEach var="page" begin="1" end="${totalPages}">
    <c:choose>
        <c:when test="${page eq currentPage}">
            <span>${page}</span>
        </c:when>
        <c:otherwise>
            <a href="?page=${page}">${page}</a>
        </c:otherwise>
    </c:choose>
</c:forEach>
```

### 테이블 행 번호

```jsp
<table>
    <c:forEach var="item" items="${list}" varStatus="status">
        <tr class="${status.index % 2 == 0 ? 'even' : 'odd'}">
            <td>${status.count}</td>
            <td>${item.name}</td>
        </tr>
    </c:forEach>
</table>
```

### NULL 체크와 기본값

```jsp
<c:out value="${user.nickname}" default="Anonymous" />
${not empty user.email ? user.email : 'No email'}
```

### 다국어 지원

```jsp
<fmt:setLocale value="${param.lang}" />
<fmt:bundle basename="messages">
    <h1><fmt:message key="welcome.title" /></h1>
    <p><fmt:message key="welcome.text">
        <fmt:param value="${user.name}" />
    </fmt:message></p>
</fmt:bundle>
```

## 5. 주의사항 및 팁

- **EL에서 NULL 처리**: EL은 null을 빈 문자열로 처리하므로 NullPointerException이 발생하지 않음
- **JSTL과 스크립틀릿 혼용 지양**: 가능한 JSTL과 EL만 사용하여 코드 가독성 향상
- **scope 명시**: 변수 설정 시 scope를 명시하여 메모리 효율성 향상
- **XSS 방지**: `<c:out>` 태그나 `fn:escapeXml()` 사용하여 사용자 입력 이스케이프
- **JSTL 라이브러리 추가**: Maven/Gradle에 jstl 의존성 추가 필요