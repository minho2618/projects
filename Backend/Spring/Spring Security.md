#Spring_Security #Spring #Java
# Spring Security Cheat Sheet

## 1. 의존성 추가 (Gradle 기준)

```groovy
dependencies {
  // Spring Security 기본 스타터
  implementation 'org.springframework.boot:spring-boot-starter-security'

  // JWT 등을 사용할 경우 추가
  implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
  runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
  runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
}
```

---

## 2. 기본 보안 설정

```java
@Configuration
@EnableWebSecurity // Spring Security 설정 클래스임을 명시
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf().disable() // CSRF 보호 비활성화 (API 서버에서 주로 비활성화)
      .authorizeHttpRequests((authz) -> authz
        .requestMatchers("/api/public/**").permitAll() // 누구나 접근 가능
        .requestMatchers("/api/admin/**").hasRole("ADMIN") // ADMIN 권한 필요
        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
      )
      .httpBasic(); // 기본 HTTP 인증 사용 (개발 시 테스트 용이)
    return http.build();
  }
}
```

---

## 3. 사용자 정보 제공: `UserDetails`와 `UserDetailsService`

```java
// 사용자 정보를 나타내는 클래스 (DB 사용자 정보와 매핑)
public class CustomUserDetails implements UserDetails {

  private final User user; // 실제 User 엔티티

  public CustomUserDetails(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // "ROLE_USER" 등 권한 정보 리턴
    return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
  }

  @Override
  public String getPassword() {
    return user.getPassword(); // 암호화된 비밀번호
  }

  @Override
  public String getUsername() {
    return user.getUsername(); // 사용자 식별 ID
  }

  // 계정 만료 여부 등 설정
  @Override public boolean isAccountNonExpired() { return true; }
  @Override public boolean isAccountNonLocked() { return true; }
  @Override public boolean isCredentialsNonExpired() { return true; }
  @Override public boolean isEnabled() { return true; }
}
```

```java
// 사용자 정보를 조회하는 서비스
@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // username으로 DB에서 사용자 찾기
    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    return new CustomUserDetails(user);
  }
}
```

---

## 4. 패스워드 암호화 설정

```java
@Configuration
public class PasswordEncoderConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    // BCrypt 해시 사용 (가장 일반적인 방식)
    return new BCryptPasswordEncoder();
  }
}
```

```java
// 회원 가입 시 비밀번호 암호화 예시
public User register(String username, String rawPassword) {
  String encrypted = passwordEncoder.encode(rawPassword); // 비밀번호 암호화
  return userRepository.save(new User(username, encrypted));
}
```

---

## 5. JWT 인증 필터 구성 예시

```java
// 요청 헤더의 JWT 토큰을 검증하는 필터
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;

  public JwtAuthenticationFilter(JwtTokenProvider provider) {
    this.jwtTokenProvider = provider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
      throws ServletException, IOException {

    String token = jwtTokenProvider.resolveToken(request); // Authorization 헤더에서 토큰 추출

    if (token != null && jwtTokenProvider.validateToken(token)) {
      Authentication auth = jwtTokenProvider.getAuthentication(token); // 토큰에서 사용자 정보 추출
      SecurityContextHolder.getContext().setAuthentication(auth); // Spring Security에 인증 정보 설정
    }

    filterChain.doFilter(request, response); // 다음 필터 실행
  }
}
```

---

## 6. JWT 토큰 제공 클래스 (간단 예시)

```java
@Component
public class JwtTokenProvider {

  private final String secretKey = "secret-example-key";

  // 토큰 생성
  public String createToken(String username, String role) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + 1000 * 60 * 60); // 1시간 유효

    return Jwts.builder()
      .setSubject(username)
      .claim("role", role)
      .setIssuedAt(now)
      .setExpiration(validity)
      .signWith(SignatureAlgorithm.HS256, secretKey)
      .compact();
  }

  // 토큰에서 인증 정보 추출
  public Authentication getAuthentication(String token) {
    String username = getUsername(token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  // 토큰에서 사용자 이름 추출
  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  // 토큰 유효성 검증
  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  // 요청에서 토큰 추출
  public String resolveToken(HttpServletRequest request) {
    String bearer = request.getHeader("Authorization");
    if (bearer != null && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }
    return null;
  }

  @Autowired
  private CustomUserDetailsService userDetailsService;
}
```

---

## 7. SecurityFilterChain에 JWT 필터 등록

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http, JwtTokenProvider jwtTokenProvider) throws Exception {
  http
    .csrf().disable()
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안함
    .and()
    .authorizeHttpRequests((authz) -> authz
      .requestMatchers("/api/auth/**").permitAll()
      .anyRequest().authenticated()
    )
    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

  return http.build();
}
```

---

## 8. 로그인 성공/실패 핸들러 설정 (옵션)

```java
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication)
      throws IOException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write("Login Success");
  }
}
```

```java
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(HttpServletRequest request,
                                      HttpServletResponse response,
                                      AuthenticationException exception)
      throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write("Login Failed: " + exception.getMessage());
  }
}
```

이 핸들러들을 `.formLogin().successHandler(...).failureHandler(...)`에 설정 가능.

---

## ✅ 기타 팁

|항목|설명|
|:--|:--|
|`@PreAuthorize("hasRole('ADMIN')")`|메서드 단위 권한 검사|
|`SecurityContextHolder.getContext().getAuthentication()`|현재 인증된 사용자 정보 조회|
|`@EnableGlobalMethodSecurity(prePostEnabled = true)`|`@PreAuthorize` 사용 활성화|
|`SessionCreationPolicy.STATELESS`|JWT 기반 인증 시 세션 사용하지 않도록 설정|

---

필요 시 OAuth2, RememberMe, CORS 설정 등도 추가 가능합니다. 필요한 항목 있으면 말씀해 주세요.