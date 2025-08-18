#JPA

# JPA (Java Persistence API) Cheat Sheet

## 1. 기본 설정

### persistence.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence" version="2.0">
    <persistence-unit name="myPU">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <properties>
            <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/mydb"/>
            <property name="javax.persistence.jdbc.user" value="username"/>
            <property name="javax.persistence.jdbc.password" value="password"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```

### Spring Boot application.yml

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: username
    password: password
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```

## 2. 엔티티 매핑

### 기본 엔티티

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_name", nullable = false, length = 50)
    private String username;
    
    @Column(unique = true)
    private String email;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    
    // 생성자, getter, setter
}
```

### 주요 어노테이션

- `@Entity`: JPA 엔티티로 지정
- `@Table`: 테이블 이름 지정
- `@Id`: 기본 키 지정
- `@GeneratedValue`: 기본 키 생성 전략
- `@Column`: 컬럼 속성 지정
- `@Temporal`: 날짜/시간 타입 지정
- `@Enumerated`: Enum 타입 매핑
- `@Lob`: CLOB, BLOB 매핑
- `@Transient`: JPA가 관리하지 않는 필드

### 기본 키 생성 전략

```java
// AUTO: DB에 따라 자동 선택
@GeneratedValue(strategy = GenerationType.AUTO)

// IDENTITY: DB의 auto_increment 사용
@GeneratedValue(strategy = GenerationType.IDENTITY)

// SEQUENCE: DB 시퀀스 사용
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence")

// TABLE: 키 생성 전용 테이블 사용
@GeneratedValue(strategy = GenerationType.TABLE)
```

## 3. 연관관계 매핑

### 일대일 (@OneToOne)

```java
@Entity
public class User {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}

@Entity
public class Profile {
    @OneToOne(mappedBy = "profile")
    private User user;
}
```

### 일대다 (@OneToMany)

```java
@Entity
public class Team {
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();
}

@Entity
public class Member {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
```

### 다대다 (@ManyToMany)

```java
@Entity
public class Student {
    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();
}

@Entity
public class Course {
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();
}
```

### Cascade 타입

- `CascadeType.ALL`: 모든 연산 전파
- `CascadeType.PERSIST`: 저장 시 전파
- `CascadeType.REMOVE`: 삭제 시 전파
- `CascadeType.MERGE`: 병합 시 전파
- `CascadeType.REFRESH`: 새로고침 시 전파
- `CascadeType.DETACH`: 분리 시 전파

### Fetch 타입

- `FetchType.EAGER`: 즉시 로딩 (기본값: @OneToOne, @ManyToOne)
- `FetchType.LAZY`: 지연 로딩 (기본값: @OneToMany, @ManyToMany)

## 4. EntityManager 사용법

### 기본 CRUD 작업

```java
// EntityManager 생성
EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
EntityManager em = emf.createEntityManager();
EntityTransaction tx = em.getTransaction();

try {
    tx.begin();
    
    // 저장 (CREATE)
    User user = new User();
    user.setUsername("john");
    em.persist(user);
    
    // 조회 (READ)
    User foundUser = em.find(User.class, 1L);
    
    // 수정 (UPDATE)
    foundUser.setUsername("johnny");
    // em.merge()는 준영속 상태의 엔티티를 영속 상태로 변경
    
    // 삭제 (DELETE)
    em.remove(foundUser);
    
    tx.commit();
} catch (Exception e) {
    tx.rollback();
} finally {
    em.close();
}
```

### 영속성 컨텍스트 관리

```java
// 영속 상태로 만들기
em.persist(entity);

// 준영속 상태로 만들기
em.detach(entity);

// 영속성 컨텍스트 비우기
em.clear();

// 즉시 DB에 반영
em.flush();
```

## 5. JPQL (Java Persistence Query Language)

### 기본 쿼리

```java
// 전체 조회
String jpql = "SELECT u FROM User u";
List<User> users = em.createQuery(jpql, User.class).getResultList();

// 조건부 조회
String jpql = "SELECT u FROM User u WHERE u.username = :username";
User user = em.createQuery(jpql, User.class)
              .setParameter("username", "john")
              .getSingleResult();

// 페이징
List<User> users = em.createQuery("SELECT u FROM User u", User.class)
                     .setFirstResult(10)
                     .setMaxResults(20)
                     .getResultList();
```

### JOIN 쿼리

```java
// INNER JOIN
String jpql = "SELECT u FROM User u JOIN u.orders o WHERE o.status = :status";

// LEFT JOIN
String jpql = "SELECT u FROM User u LEFT JOIN u.orders o";

// FETCH JOIN (N+1 문제 해결)
String jpql = "SELECT u FROM User u JOIN FETCH u.orders";
```

### 집계 함수

```java
// COUNT
String jpql = "SELECT COUNT(u) FROM User u";
Long count = em.createQuery(jpql, Long.class).getSingleResult();

// SUM, AVG, MAX, MIN
String jpql = "SELECT AVG(o.amount) FROM Order o";
Double avgAmount = em.createQuery(jpql, Double.class).getSingleResult();
```

### Named Query

```java
@Entity
@NamedQuery(
    name = "User.findByUsername",
    query = "SELECT u FROM User u WHERE u.username = :username"
)
public class User {
    // ...
}

// 사용
User user = em.createNamedQuery("User.findByUsername", User.class)
              .setParameter("username", "john")
              .getSingleResult();
```

## 6. Criteria API

### 기본 사용법

```java
CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery<User> cq = cb.createQuery(User.class);
Root<User> user = cq.from(User.class);

// 조건 추가
Predicate condition = cb.equal(user.get("username"), "john");
cq.select(user).where(condition);

List<User> users = em.createQuery(cq).getResultList();
```

### 복합 조건

```java
// AND 조건
Predicate p1 = cb.equal(user.get("status"), UserStatus.ACTIVE);
Predicate p2 = cb.like(user.get("username"), "%john%");
cq.where(cb.and(p1, p2));

// OR 조건
cq.where(cb.or(p1, p2));

// 정렬
cq.orderBy(cb.asc(user.get("username")));
```

## 7. Native Query

### 기본 사용법

```java
// Native SQL 실행
String sql = "SELECT * FROM users WHERE username = ?";
Query query = em.createNativeQuery(sql, User.class);
query.setParameter(1, "john");
List<User> users = query.getResultList();

// Named Native Query
@Entity
@NamedNativeQuery(
    name = "User.findByEmail",
    query = "SELECT * FROM users WHERE email = :email",
    resultClass = User.class
)
public class User {
    // ...
}
```

## 8. Spring Data JPA

### Repository 인터페이스

```java
public interface UserRepository extends JpaRepository<User, Long> {
    // 메서드 이름으로 쿼리 생성
    List<User> findByUsername(String username);
    List<User> findByUsernameAndStatus(String username, UserStatus status);
    List<User> findByUsernameContaining(String username);
    
    // @Query 어노테이션
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
    
    // Native Query
    @Query(value = "SELECT * FROM users WHERE created_at > :date", nativeQuery = true)
    List<User> findUsersCreatedAfter(@Param("date") Date date);
    
    // 수정 쿼리
    @Modifying
    @Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
    int updateUserStatus(@Param("id") Long id, @Param("status") UserStatus status);
}
```

### 페이징과 정렬

```java
// Pageable 사용
Page<User> users = userRepository.findAll(PageRequest.of(0, 10));

// 정렬 추가
Page<User> users = userRepository.findAll(
    PageRequest.of(0, 10, Sort.by("username").ascending())
);

// 메서드에 Pageable 파라미터 추가
Page<User> findByStatus(UserStatus status, Pageable pageable);
```

## 9. 성능 최적화

### N+1 문제 해결

```java
// Fetch Join 사용
@Query("SELECT u FROM User u JOIN FETCH u.orders")
List<User> findAllWithOrders();

// @EntityGraph 사용
@EntityGraph(attributePaths = {"orders"})
@Query("SELECT u FROM User u")
List<User> findAllWithOrdersEntityGraph();
```

### 배치 처리

```java
// 배치 삽입
@Modifying
@Query("INSERT INTO User(username, email) VALUES (:username, :email)")
void batchInsert(@Param("username") String username, @Param("email") String email);

// 배치 크기 설정 (application.yml)
spring:
  jpa:
    properties:
      hibernate:
        jdbc:
          batch_size: 20
```

### 캐싱

```java
// 2차 캐시
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
    // ...
}

// 쿼리 캐시
@Query("SELECT u FROM User u WHERE u.status = :status")
@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
List<User> findByStatusCacheable(@Param("status") UserStatus status);
```

## 10. 트랜잭션

### @Transactional 사용

```java
@Service
public class UserService {
    
    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }
    
    @Transactional(readOnly = true)
    public User findUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
```

### 전파 속성

- `REQUIRED`: 기존 트랜잭션이 있으면 참여, 없으면 새로 생성 (기본값)
- `REQUIRES_NEW`: 항상 새로운 트랜잭션 생성
- `SUPPORTS`: 기존 트랜잭션이 있으면 참여, 없어도 실행
- `NOT_SUPPORTED`: 트랜잭션 없이 실행
- `NEVER`: 트랜잭션이 있으면 예외 발생
- `MANDATORY`: 기존 트랜잭션이 반드시 있어야 함

## 11. 자주 사용하는 어노테이션 정리

### 엔티티 관련

- `@Entity`: JPA 엔티티로 지정
- `@Table`: 테이블 이름 및 속성 지정
- `@Id`: 기본 키 지정
- `@GeneratedValue`: 기본 키 생성 전략
- `@Column`: 컬럼 속성 지정
- `@JoinColumn`: 외래 키 컬럼 지정

### 연관관계 관련

- `@OneToOne`: 일대일 관계
- `@OneToMany`: 일대다 관계
- `@ManyToOne`: 다대일 관계
- `@ManyToMany`: 다대다 관계
- `@JoinTable`: 연결 테이블 지정

### Spring Data JPA 관련

- `@Repository`: 리포지토리 계층 지정
- `@Query`: 커스텀 쿼리 작성
- `@Modifying`: 데이터 수정 쿼리
- `@Param`: 쿼리 파라미터 바인딩
- `@Transactional`: 트랜잭션 처리

## 12. 실무 팁

### 엔티티 설계 시 주의사항

1. 양방향 연관관계보다는 단방향 연관관계를 우선적으로 고려
2. 지연 로딩(LAZY)을 기본으로 사용
3. @ToString에서 연관관계 필드 제외 (순환참조 방지)
4. 연관관계 편의 메서드 작성

### 성능 개선

1. N+1 문제 해결: fetch join, @EntityGraph 활용
2. 적절한 배치 크기 설정
3. 읽기 전용 쿼리에는 @Transactional(readOnly = true) 사용
4. 불필요한 컬럼 조회 방지: Projection 활용

### 예외 처리

```java
// 엔티티가 없을 때
Optional<User> optionalUser = userRepository.findById(id);
User user = optionalUser.orElseThrow(() -> new EntityNotFoundException("User not found"));

// 유니크 제약조건 위반 시
try {
    userRepository.save(user);
} catch (DataIntegrityViolationException e) {
    throw new DuplicateEmailException("Email already exists");
}
```