#Spring #Spring-Boot
### 1. 프로젝트 구조 기본

```Perl
src
 └── main
     ├── java
     │    └── com.example.demo
     │         ├── DemoApplication.java
     │         ├── controller/
     │         ├── service/
     │         ├── repository/
     │         └── domain/
     └── resources
          ├── application.properties
          ├── static/
          └── templates/
```

---

### 2. 주요 의존성 (Gradle)

```groovy
dependencies {     
	// 웹
	implementation 'org.springframework.boot:spring-boot-starter-web'   
	// JPA
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'  
	// 보안 
	implementation 'org.springframework.boot:spring-boot-starter-security' 
	// 유효성 검사 
	implementation 'org.springframework.boot:spring-boot-starter-validation'     
	// MySQL
	runtimeOnly 'com.mysql:mysql-connector-j'      
	// 테스트 
	testImplementation 'org.springframework.boot:spring-boot-starter-test' 
}
```
---

### 3. application.properties 기본 설정

```properties
# Server 
server.port=8080  

# Database 
spring.datasource.url=jdbc:mysql://localhost:3306/db_name 
spring.datasource.username=root 
spring.datasource.password=1234 
spring.jpa.hibernate.ddl-auto=update 
spring.jpa.show-sql=true
```

---

## 4. `application.yml` 예시

```yaml
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
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```

---

## 5. 요청-응답 흐름

```less
클라이언트 → @RestController → @Service → @Repository → DB
```
---

## 6. 테스트 기본

```java
@SpringBootTest 
@AutoConfigureMockMvc 
public class MyTest {     
	@Autowired     
	private MockMvc mockMvc;      
	
	@Test     
	void testHome() throws Exception {   
	      mockMvc.perform(get("/")).andExpect(status().isOk());     
	} 
}
```
---

## 🔐 7. Spring Security

- 사용자 인증 설정:
    

java

복사편집

`@Configuration @EnableWebSecurity public class SecurityConfig {     @Bean     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {         http           .csrf().disable()           .authorizeHttpRequests()             .requestMatchers("/public/**").permitAll()             .anyRequest().authenticated()           .and()             .formLogin();         return http.build();     } }`

---

## 🗂️ 8. JPA 기본

### 📌 Entity

java

복사편집

`@Entity public class User {     @Id @GeneratedValue     private Long id;      private String name; }`

### 📌 Repository

java

복사편집

`public interface UserRepository extends JpaRepository<User, Long> {     List<User> findByName(String name); }`

---

## 🔁 9. DTO, Validation

java

복사편집

`public class UserDto {     @NotBlank     private String name; }`

java

복사편집

`@PostMapping("/users") public ResponseEntity<?> createUser(@Valid @RequestBody UserDto dto) {     ... }`

---

## 🔧 10. 예외 처리 (Global)

java

복사편집

`@RestControllerAdvice public class GlobalExceptionHandler {      @ExceptionHandler(MethodArgumentNotValidException.class)     public ResponseEntity<?> handleValidationErrors(Exception ex) {         return ResponseEntity.badRequest().body("Validation Error");     } }`

---

## 🧵 11. 기타 유용 기능

- **Scheduling**
    

java

복사편집

`@EnableScheduling @Scheduled(fixedRate = 5000) // 5초마다 실행 public void runTask() { ... }`

- **CommandLineRunner (앱 시작 시 실행)**
    

java

복사편집

`@Bean CommandLineRunner runner() {     return args -> {         System.out.println("앱 시작됨");     }; }`

- **파일 업로드 처리**
    

java

복사편집

`@PostMapping("/upload") public String upload(@RequestParam MultipartFile file) { ... }`

---

## 12. 참고 문서

- 공식 문서: [https://docs.spring.io/spring-boot/](https://docs.spring.io/spring-boot/)   
- 스타터 목록: [https://start.spring.io](https://start.spring.io)

### 📂 4. Controller 예시

java

복사편집

`@RestController @RequestMapping("/api") public class HelloController {      @GetMapping("/hello")     public String hello() {         return "Hello, Spring Boot!";     } }`

---

### 💼 5. JPA Entity & Repository

java

복사편집

`@Entity public class User {     @Id @GeneratedValue     private Long id;     private String name; }  public interface UserRepository extends JpaRepository<User, Long> {     Optional<User> findByName(String name); }`

---

### 🧠 6. Service Layer

java

복사편집

`@Service @RequiredArgsConstructor public class UserService {     private final UserRepository userRepository;      public List<User> getAllUsers() {         return userRepository.findAll();     } }`

---

### 🔒 7. Spring Security 기본 설정

java

복사편집

`@Configuration @EnableWebSecurity public class SecurityConfig extends WebSecurityConfigurerAdapter {     @Override     protected void configure(HttpSecurity http) throws Exception {         http           .csrf().disable()           .authorizeRequests()             .antMatchers("/api/public/**").permitAll()             .anyRequest().authenticated()           .and()             .httpBasic();     } }`

---

### 📑 8. DTO 예시

java

복사편집

`public class UserDTO {     private String name; }`

---

### 🌐 9. REST API 응답 구조

java

복사편집

`@GetMapping("/user/{id}") public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {     User user = userService.findUser(id);     return ResponseEntity.ok(new UserDTO(user.getName())); }`

---

### 10. 핵심 어노테이션 모음

|애노테이션|설명|
|---|---|
|`@SpringBootApplication`|부트스트랩용 메인 클래스에 사용|
|`@RestController`|`@Controller + @ResponseBody` 조합|
|`@GetMapping`, etc.|요청 방식별 매핑|
|`@Entity`|JPA 테이블 매핑|
|`@Repository`|DAO 역할|
|`@Service`|비즈니스 로직 처리|
|`@Autowired` / `@RequiredArgsConstructor`|의존성 주입 방법|

|어노테이션|설명|
|---|---|
|`@SpringBootApplication`|앱 시작점, 여러 어노테이션 포함|
|`@RestController`|JSON 반환용 컨트롤러|
|`@Controller`|View 반환 컨트롤러 (Thymeleaf 등)|
|`@Service`|서비스 계층|
|`@Repository`|DAO 계층, 예외 처리 자동 전환|
|`@Autowired`|의존성 주입|
|`@Value("${key}")`|properties 값 주입|
|`@RequestMapping`, `@GetMapping` 등|라우팅 매핑|
|`@Entity`, `@Table`, `@Id`|JPA 엔티티|
|`@Transactional`|트랜잭션 적용|


### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#repository-)@Repository :

- Class Level Annotation
- It can reach the database and do all the operations.
- It make the connection between the database and the business logic.
- DAO is a repository.
- It is a marker interface.

```
@Repository
public class TestRepo{
   public void add(){
      System.out.println("Added");
   }
}
```

---

### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#service-)@Service :

- Class Level Annotation
- It is a marker interface.
- It is a business logic.
- It is a service layer.
- It used to create a service layer.

```
@Service
public class TestService{
   public void service1(){
      //business code (iş kodları)
   }
}
```

---

### @Autowired :

- Field Level Annotation
- It is used to inject the dependency.
- It is used to inject the object.
- It is used to inject the object reference.
- Dependency Injection is a design pattern.

```
public class Brand{
   private int id;
   private String name;

   @Autowired
   public Brand(int id, String name){
     this.id = id;
     this.name = name;
   }
}
```

---

### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#controller-)@Controller :

- Class Level Annotation
- It is a marker interface.
- It is a controller layer.
- It is used to create a controller layer.
- It use with @RequestMapping annotation.

```
@Controller
@RequestMapping("/api/brands")
public class BrandsController{
   @GetMapping("/getall")
   public Employee getAll(){
       return brandService.getAll();
   }
}
```

---

### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#requestmapping-)@RequestMapping :

- Method Level Annotation
- It is used to map the HTTP request with specific method.

```
@Controller
@RequestMapping("/api/brands")
public class BrandsController{
   @GetMapping("/getall")
   public Employee getAll(){
       return brandService.getAll();
   }
}
```

---

### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#getmapping-)@GetMapping :

- Method Level Annotation
- It is used to map the HTTP GET request with specific method.
- It is used to get the data.
- It is used to read the data.

```
  @GetMapping("/getall")
  public Employee getAll(){
      return brandService.getAll();
  }
```

---

### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#postmapping-)@PostMapping :

- Method Level Annotation
- It is used to map the HTTP POST request with specific method.
- It is used to add the data.
- It is used to create the data.

```
  @PostMapping("/add")
  public void add(@RequestBody Brand brand){
      brandService.add(brand);
  }
```

---

### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#putmapping-)@PutMapping :

- Method Level Annotation
- It is used to map the HTTP PUT request with specific method.
- It is used to update the data.

```
  @PutMapping("/update")
  public void update(@RequestBody Brand brand){
      brandService.update(brand);
  }
```

---

### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#deletemapping-)@DeleteMapping :

- Method Level Annotation
- It is used to map the HTTP DELETE request with specific method.
- It is used to delete the data.

```
  @DeleteMapping("/delete")
  public void delete(@RequestBody Brand brand){
      brandService.delete(brand);
  }
```

---

### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#pathvariable-)@PathVariable :

- Method Level Annotation
- It is used to get the data from the URL.
- It is the most suitable for RESTful web service that contains a path variable.

```
  @GetMapping("/getbyid/{id}")
  public Brand getById(@PathVariable int id){
      return brandService.getById(id);
  }
```

---

### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#requestbody)@RequestBody:

- It is used to get the data from the request body.
- It is used to get the data from the HTTP request.
- It is used to get the data from the HTTP request body.

```
  @PostMapping("/add")
  public void add(@RequestBody Brand brand){
      brandService.add(brand);
  }
```

---

### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#requestparam)@RequestParam:

- It is used to get the data from the URL.
- It is used to get the data from the URL query parameters.
- It is also known as query parameter.

```
@GetMapping("/getbyid")
public Brand getById(@RequestParam int id){
    return brandService.getById(id);
}
```

---

### [](https://dev.to/burakboduroglu/spring-boot-cheat-sheet-460c#restcontroller)@RestController:

- Class Level Annotation
- It is a marker interface.
- It is a controller layer.
- It is used to create a controller layer.
- It use with @RequestMapping annotation.
- It is a combination of @Controller and @ResponseBody annotations.
- @RestController annotation is explained with @ResponseBody annotation.
- @ResponseBody eliminates the need to add a comment to every method.

```
@RestController
@RequestMapping("/api/brands")
public class BrandsController{
   @GetMapping("/getall")
   public Employee getAll(){
       return brandService.getAll();
   }
}
```

---


---

### 🧪 11. 테스트 코드 예시

java

복사편집

`@SpringBootTest class UserServiceTest {      @Autowired     private UserService userService;      @Test     void testGetAllUsers() {         List<User> users = userService.getAllUsers();         assertNotNull(users);     } }`

---

필요하면 **JWT, 파일 업로드, 예외처리, 커스텀 인터셉터/필터** 항목도 추가해드릴 수 있어요. 더 구체적인 내용 원하면 말씀해주세요!




---


