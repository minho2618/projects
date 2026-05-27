✦ 제공해주신 Spring Security 및 JWT 관련 코드를 분석한 결과, 발생할 수 있는 주요 예상 문제점들을 다음과 같이 정리해 드립니다.

  1. JWT 필터 내 예외 처리 미흡 (가장 중요한 문제)
  JwtAuthenticationFilter에서 jwtProvider.validateToken(token)을 호출할 때, 만료된 토큰이나 변조된 토큰일 경우 JwtProvider는 JwtException을 던집니다.
   * 문제점: 필터(Filter)는 서블릿(Servlet) 외부 단계에서 동작하므로, 여기서 발생한 예외는 @ExceptionHandler나 @ControllerAdvice에서 잡히지 않습니다.
   * 결과: 토큰이 만료되었을 때 클라이언트에게 깔끔한 JSON 에러 응답(401 Unauthorized)을 보내는 대신, 서버 내부 오류(500)가 발생하거나 스프링 부트의 기본 에러 페이지가 노출될 수
     있습니다.
   * 해결책: JwtAuthenticationFilter 내부에 try-catch 블록을 추가하여 예외를 잡고, response 객체에 직접 에러 메시지를 작성하거나 별도의 AuthenticationEntryPoint를 구현해야 합니다.

  2. CORS(Cross-Origin Resource Sharing) 설정 부재
  SecurityConfig에서 csrf.disable()은 설정되어 있지만, cors() 설정이 보이지 않습니다.
   * 문제점: 프론트엔드(React, Vue 등)와 백엔드가 서로 다른 도메인이나 포트에서 구동될 경우, 브라우저의 보안 정책으로 인해 API 호출이 차단됩니다.
   * 결과: 프론트엔드에서 API 요청 시 CORS policy 오류가 발생하며 연결이 불가능해집니다.

  3. 인증 실패 및 권한 거부 핸들러 부재
  SecurityConfig에 exceptionHandling 설정이 없습니다.
   * 문제점: 인증되지 않은 사용자가 보호된 리소스에 접근하거나, 권한이 없는 사용자가 접근할 때 스프링 시큐리티의 기본 동작(주로 HTML 응답 혹은 빈 응답)이 수행됩니다.
   * 결과: REST API 서비스임에도 불구하고 에러 발생 시 일관성 없는 응답 형식이 반환될 수 있습니다.

  4. JWT Secret Key 보안 및 유효성
  JwtProvider에서 secretKey.getBytes(StandardCharsets.UTF_8)를 사용하여 키를 생성합니다.
   * 문제점: application.properties에 설정된 secretKey 문자열이 너무 짧을 경우(HS256 알고리즘 기준 최소 256비트 미만), 애플리케이션 시작 시점에 보안 강도가 낮다는 예외가 발생하며
     구동이 안 될 수 있습니다.
   * 참고: Jwts.builder().signWith(getSigningKey()) 호출 시 최신 버전의 jjwt 라이브러리는 엄격한 키 길이를 요구합니다.

  5. 매 요청마다 DB 조회로 인한 성능 저하 가능성
  JwtAuthenticationFilter의 로직을 보면:

   1 Integer userId = jwtProvider.getUserId(token);
   2 UserAuth userAuth = userAuthService.findByUserId(userId);
   * 문제점: 모든 API 요청마다 토큰에서 ID를 꺼내 DB의 UserAuth 테이블을 조회합니다.
   * 결과: 트래픽이 많아질 경우 DB에 상당한 부하를 줄 수 있습니다. 토큰 내에 필요한 정보(Role 등)를 Claim으로 포함시키고, DB 조회 빈도를 줄이는 전략(예: 캐싱)을 고려해볼 수
     있습니다.

  6. 리프레시 토큰(Refresh Token) 로직 미비
  JwtProvider에 refreshTokenExpiration 변수는 선언되어 있으나, 이를 생성하거나 검증하고 재발급하는 로직이 완성되어 있지 않습니다.
   * 결과: 액세스 토큰만 사용할 경우, 보안을 위해 만료 시간을 짧게 잡으면 사용자가 자주 로그아웃되는 불편함이 생기고, 길게 잡으면 탈취 시 위험이 커지는 딜레마가 발생합니다.

  7. Principal 객체의 일관성 문제
  JwtAuthenticationFilter에서 SecurityContext에 저장하는 Principal이 UserAuth 엔티티 자체입니다.

   1 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
   2     userAuth, null, userAuth.getAuthorities()
   3 );
   * 주의점: 다른 서비스 로직에서 @AuthenticationPrincipal로 정보를 가져올 때, 보통 UserDetails 인터페이스 구현체를 기대하는 경우가 많습니다. 만약 UserAuth가 UserDetails를
     구현하고 있지 않다면 캐스팅 오류나 예상치 못한 동작이 발생할 수 있으니 UserAuth 엔티티의 구현 상태를 확인해야 합니다.

  다음 단계 제안:
  가장 시급한 1번(필터 내 예외 처리) 및 2번(CORS 설정) 문제를 해결하기 위한 코드 수정을 원하신다면 말씀해 주세요. 관련 코드를 제안해 드릴 수 있습니다.