---
tags:
  - Spring_Security
  - Spring
---
# Spring Security

스프링 프레임워크에서 애플리케이션의 보안(인증과 인가, 보안조치)을 담당하는 스프링 하위 프레임워크이다.

인증 : 사이트에 대해서 유효한 사용자인지 확인하는 것 인가 : 인증된 사용자가 사용할 수 있는 기능인지 확인하고 권한이 부여된 리소스에만 접근을 허용하는 과정

Spring Security는 기본적으로 인증 절차를 거친 후에 인가 절차를 진행하게 되며, 인가 과정에서 해당 리소스에 대한 접근 권한이 있는지 확인하고 접근을 허용하게 된다. 인증, 인가를 위해 Principal을 아이디로 Credential을 비밀번호로 사용하는 Credential 기반의 인증 방식을 사용한다.

Principal(접근 주체) : 보호 받는 리소스에 접근하는 대상 → 회원 Credential(비밀 번호) : 리소스에 접근하는 대상의 비밀번호

세션&쿠키 방식으로 인증을 처리한다.

## DelegatingFilterProxy

서블릿 필터와 스프링 빈의 연결 매개체

Spring Security는 모든 요청에 대해서 필터를 기반으로 인증/인가를 처리한다. @EnableWebSecurity 가 선언된 시큐리티 관련 configuration 클래스에는 이런 저런 시큐리티 관련 빈 객체들이 생성되는데 이 빈 객체들이 서블릿 필터에 주입되도록 역할을 하는 것이 바로 DelegatingFilterProxy 객체이다.

