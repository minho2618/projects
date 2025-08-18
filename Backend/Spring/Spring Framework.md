#Spring #Java #Backend 
# Spring Framework Cheat Sheet (with code comments)

## 1. Core Concepts

### Dependency Injection (DI)

- **Constructor Injection** (권장 방식)
    

```java
@Component // 스프링이 관리하는 컴포넌트로 등록
public class UserService {
    private final UserRepository userRepository;
    
    // 생성자 주입: final 필드와 함께 사용, 테스트 용이
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

- **Field Injection** (테스트가 어려워 권장되지 않음)
    

```java
@Component
public class UserService {
    @Autowired // 필드 주입: 직접 주입하지만 final 불가, 테스트 어려움
    private UserRepository userRepository;
}
```

### Inversion of Control (IoC)

- 스프링 컨테이너(ApplicationContext)가 객체의 생성과 의존성 주입을 관리함
    

## 2. 핵심 어노테이션

### Bean 생성 및 관리 어노테이션

```java
@Component       // 일반 컴포넌트
@Service         // 비즈니스 로직을 담당하는 서비스 컴포넌트
@Repository      // 데이터 액세스 계층, 예외 변환 처리
@Controller      // MVC 패턴의 컨트롤러
@RestController  // @Controller + @ResponseBody: REST API 응답
@Configuration   // Java 설정 클래스
```

### 의존성 주입 관련 어노테이션

```java
@Autowired       // 자동 의존성 주입
@Qualifier("name") // 이름으로 특정 Bean 지정
@Primary         // 동일 타입 Bean 중 우선 적용될 Bean 지정
@Value("${config.value}") // 프로퍼티 값을 주입받음
```

### Bean 설정 관련 어노테이션

```java
@Bean            // @Configuration 내부에서 Bean 등록 메서드
@Scope("singleton") // Bean 스코프: prototype, request, session 등 지정 가능
@Lazy            // 지연 초기화
@PostConstruct   // 빈 초기화 직후 실행
@PreDestroy      // 빈 소멸 직전 실행
```

## 3. Spring Boot

### 기본 설정

```java
@SpringBootApplication // @Configuration + @EnableAutoConfiguration + @ComponentScan 조합
@ComponentScan         // 컴포넌트 스캔 범위 지정
@EnableAutoConfiguration // 설정 자동화
```

### 프로퍼티 설정

```properties
# application.properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

```yaml
# application.yml
server:
  port: 8080
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: password
  jpa:
    hibernate:
      ddl-auto: update
```

## 4. Spring MVC

### REST 컨트롤러 기본

```java
@RestController // RESTful API 응답용 컨트롤러
@RequestMapping("/api/users") // 공통 경로 설정
public class UserController {

    @GetMapping // 전체 사용자 목록 조회
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}") // 개별 사용자 조회
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping // 사용자 생성
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}") // 사용자 정보 수정
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}") // 사용자 삭제
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
```

### 요청 매핑 단축 어노테이션

```java
@GetMapping("/users")           // GET 요청
@PostMapping("/users")          // POST 요청
@PutMapping("/users/{id}")      // PUT 요청
@DeleteMapping("/users/{id}")   // DELETE 요청
@PatchMapping("/users/{id}")    // PATCH 요청
```

### 파라미터 처리 예시

```java
@GetMapping("/users")
public List<User> getUsers(
    @RequestParam String name,         // 쿼리 파라미터: ?name=홍길동
    @RequestParam(defaultValue = "0") int page,
    @PathVariable Long id,             // 경로 변수: /users/1
    @RequestBody User user,            // JSON 요청 본문 매핑
    @RequestHeader String authorization // 요청 헤더 값
) {
    // 처리 로직
}
```

## 5. Spring Data JPA

### 엔티티 정의 예시

```java
@Entity
@Table(name = "users") // DB 테이블명 지정
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 ID
    private Long id;

    @Column(nullable = false, unique = true) // null 금지 및 중복 금지
    private String email;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // 관계 설정
    private List<Order> orders = new ArrayList<>();
}
```

### Repository 인터페이스 예시

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 메서드 이름으로 쿼리 생성
    List<User> findByName(String name);
    List<User> findByEmailContaining(String email);
    List<User> findByAgeGreaterThan(int age);

    // JPQL
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    // 네이티브 쿼리
    @Query(value = "SELECT * FROM users WHERE name = ?1", nativeQuery = true)
    List<User> findByNameNative(String name);

    // 수정 쿼리 (update/delete는 @Modifying 필요)
    @Modifying
    @Query("UPDATE User u SET u.name = ?1 WHERE u.id = ?2")
    int updateUserName(String name, Long id);
}
```

## 6. 트랜잭션 관리

```java
@Service
@Transactional // 클래스 레벨 트랜잭션 적용
public class UserService {

    @Transactional(readOnly = true) // 읽기 전용
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional(rollbackFor = Exception.class) // 롤백 조건 설정
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED) // 트랜잭션 전파 설정
    public void complexOperation() {
        // 복잡한 로직
    }
}
```

### 트랜잭션 속성 예시

```java
@Transactional(
    isolation = Isolation.READ_COMMITTED, // 격리 수준
    propagation = Propagation.REQUIRED,   // 전파 방식
    timeout = 30,                          // 타임아웃 (초)
    rollbackFor = {Exception.class},       // 롤백 조건
    noRollbackFor = {RuntimeException.class} // 롤백 제외 조건
)
```

## 7. 예외 처리

```java
@ControllerAdvice // 글로벌 예외 핸들러
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(500).body("Internal Server Error");
    }
}

// 커스텀 예외 정의
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```

## 8. 검증 (Validation)

```java
@Entity
public class User {
    @NotNull(message = "이름은 필수입니다")
    @Size(min = 2, max = 50, message = "이름은 2-50자 사이여야 합니다")
    private String name;

    @Email(message = "유효한 이메일 주소를 입력하세요")
    @NotBlank(message = "이메일은 필수입니다")
    private String email;

    @Min(value = 0, message = "나이는 0 이상이어야 합니다")
    @Max(value = 120, message = "나이는 120 이하여야 합니다")
    private int age;
}

// 컨트롤러에서 @Valid로 유효성 검사
@PostMapping("/users")
public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    User savedUser = userService.save(user);
    return ResponseEntity.ok(savedUser);
}
```

## 9. Java 설정 클래스

```java
@Configuration // 설정 클래스
@EnableWebMvc  // Spring MVC 활성화
@ComponentScan(basePackages = "com.example") // 컴포넌트 탐색 경로 설정
public class AppConfig {

    @Bean // DataSource Bean 등록
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    @Primary // 동일 타입 Bean 중 우선 적용
    public UserService userService() {
        return new UserServiceImpl();
    }
}
```

## 10. 프로파일 설정

```java
@Configuration
@Profile("dev") // 개발 환경용 설정
public class DevConfig {
    @Bean
    public DataSource dataSource() {
        return new H2DataSource(); // H2 임베디드 DB
    }
}

@Configuration
@Profile("prod") // 운영 환경용 설정
public class ProdConfig {
    @Bean
    public DataSource dataSource() {
        return new MySQLDataSource();
    }
}

# application.properties
spring.profiles.active=dev

# 또는 JVM 옵션
-Dspring.profiles.active=dev
```

## 11. 테스트

```java
// 단위 테스트
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock // 가짜 객체 생성
    private UserRepository userRepository;

    @InjectMocks // 테스트 대상 객체에 주입
    private UserService userService;

    @Test
    void testFindById() {
        User user = new User("John", "john@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("John");
    }
}

// 통합 테스트
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateUser() {
        User user = new User("John", "john@example.com");
        ResponseEntity<User> response = restTemplate.postForEntity("/api/users", user, User.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("John");
    }
}
```

## 12. 유틸리티 클래스

```java
// StringUtils 활용 예시
StringUtils.hasText(str);    // null 또는 빈 문자열 아닌지 체크
StringUtils.isEmpty(str);    // null 또는 빈 문자열인지 체크
StringUtils.capitalize(str); // 첫 글자 대문자 변환

// CollectionUtils 활용 예시
CollectionUtils.isEmpty(list);     // 컬렉션이 비어있는지
CollectionUtils.isNotEmpty(list);  // 컬렉션이 비어있지 않은지
```

## 13. 로깅 설정

```java
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User findById(Long id) {
        logger.info("Finding user with id: {}", id);
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            logger.warn("User not found with id: {}", id);
        }
        return user;
    }
}
```

```xml
<!-- logback-spring.xml 설정 -->
<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
    </root>
</configuration>
```

## 14. 보안 설정 (Spring Security)

```java
@Configuration
@EnableWebSecurity // Spring Security 활성화
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll() // 공개 경로
                .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자 권한
                .anyRequest().authenticated() // 그 외 요청은 인증 필요
            )
            .formLogin(Customizer.withDefaults()) // 폼 로그인 사용
            .httpBasic(Customizer.withDefaults()); // 기본 HTTP 인증 사용

        return http.build();
    }
}
```