#MyBatis

## 전체 구조도

![[Pasted image 20250709180059.png]]

---

# MyBatis Cheat Sheet

## 기본 설정

### Maven 의존성

```xml
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.13</version>
</dependency>

<!-- Spring Boot -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.3.1</version>
</dependency>
```

### mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
        <setting name="cacheEnabled" value="true"/>
        <setting name="lazyLoadingEnabled" value="true"/>
    </settings>
    
    <typeAliases>
        <package name="com.example.model"/>
    </typeAliases>
    
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
                <property name="username" value="user"/>
                <property name="password" value="password"/>
            </dataSource>
        </environment>
    </environments>
    
    <mappers>
        <package name="com.example.mapper"/>
    </mappers>
</configuration>
```

### application.yml (Spring Boot)

```yaml
mybatis:
  config-location: classpath:mybatis-config.xml
  mapper-locations: 
    - classpath:mapper/**/*.xml
  type-aliases-package: com.example.model
  configuration:
    map-underscore-to-camel-case: true
    cache-enabled: true
    lazy-loading-enabled: true
```

## 매퍼 인터페이스

### 기본 매퍼 인터페이스

```java
@Mapper
public interface UserMapper {
    // 단일 조회
    User findById(Long id);
    
    // 목록 조회
    List<User> findAll();
    
    // 조건부 조회
    List<User> findByName(@Param("name") String name);
    
    // 삽입
    int insert(User user);
    
    // 업데이트
    int update(User user);
    
    // 삭제
    int deleteById(Long id);
    
    // 개수 조회
    int count();
    
    // 복잡한 조건 조회
    List<User> findByCondition(@Param("condition") UserSearchCondition condition);
}
```

## XML 매퍼

### 기본 XML 매퍼 구조

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.mapper.UserMapper">
    
    <!-- ResultMap 정의 -->
    <resultMap id="userResultMap" type="User">
        <id property="id" column="user_id"/>
        <result property="name" column="user_name"/>
        <result property="email" column="email"/>
        <result property="createDate" column="create_date"/>
    </resultMap>
    
    <!-- SQL 조각 재사용 -->
    <sql id="userColumns">
        user_id, user_name, email, create_date
    </sql>
    
    <!-- 기본 CRUD -->
    <select id="findById" parameterType="long" resultMap="userResultMap">
        SELECT <include refid="userColumns"/>
        FROM users 
        WHERE user_id = #{id}
    </select>
    
    <select id="findAll" resultMap="userResultMap">
        SELECT <include refid="userColumns"/>
        FROM users 
        ORDER BY create_date DESC
    </select>
    
    <insert id="insert" parameterType="User" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO users (user_name, email, create_date)
        VALUES (#{name}, #{email}, #{createDate})
    </insert>
    
    <update id="update" parameterType="User">
        UPDATE users 
        SET user_name = #{name}, 
            email = #{email}
        WHERE user_id = #{id}
    </update>
    
    <delete id="deleteById" parameterType="long">
        DELETE FROM users 
        WHERE user_id = #{id}
    </delete>
    
</mapper>
```

## SQL 문법

### 매개변수 바인딩

```xml
<!-- 단일 매개변수 -->
<select id="findById" parameterType="long" resultType="User">
    SELECT * FROM users WHERE id = #{id}
</select>

<!-- 다중 매개변수 -->
<select id="findByNameAndAge" resultType="User">
    SELECT * FROM users 
    WHERE name = #{name} AND age = #{age}
</select>

<!-- Map 매개변수 -->
<select id="findByMap" parameterType="map" resultType="User">
    SELECT * FROM users 
    WHERE name = #{name} AND status = #{status}
</select>

<!-- 객체 매개변수 -->
<select id="findByUser" parameterType="User" resultType="User">
    SELECT * FROM users 
    WHERE name = #{name} AND email = #{email}
</select>
```

### LIKE 검색

```xml
<!-- 방법 1: concat 함수 사용 -->
<select id="findByNameLike" resultType="User">
    SELECT * FROM users 
    WHERE name LIKE CONCAT('%', #{name}, '%')
</select>

<!-- 방법 2: $ 문자 사용 (주의: SQL 인젝션 위험) -->
<select id="findByNameLike2" resultType="User">
    SELECT * FROM users 
    WHERE name LIKE '%${name}%'
</select>
```

## 동적 SQL

### if 조건문

```xml
<select id="findUsers" resultType="User">
    SELECT * FROM users
    <where>
        <if test="name != null and name != ''">
            AND name = #{name}
        </if>
        <if test="email != null and email != ''">
            AND email = #{email}
        </if>
        <if test="age != null">
            AND age = #{age}
        </if>
    </where>
</select>
```

### choose, when, otherwise

```xml
<select id="findUsers" resultType="User">
    SELECT * FROM users
    <where>
        <choose>
            <when test="name != null">
                name = #{name}
            </when>
            <when test="email != null">
                email = #{email}
            </when>
            <otherwise>
                status = 'ACTIVE'
            </otherwise>
        </choose>
    </where>
</select>
```

### foreach 반복문

```xml
<!-- IN 절 -->
<select id="findByIds" resultType="User">
    SELECT * FROM users
    WHERE id IN
    <foreach collection="ids" item="id" open="(" separator="," close=")">
        #{id}
    </foreach>
</select>

<!-- 배치 INSERT -->
<insert id="insertBatch" parameterType="list">
    INSERT INTO users (name, email) VALUES
    <foreach collection="list" item="user" separator=",">
        (#{user.name}, #{user.email})
    </foreach>
</insert>

<!-- 배치 UPDATE -->
<update id="updateBatch" parameterType="list">
    <foreach collection="list" item="user" separator=";">
        UPDATE users 
        SET name = #{user.name}, email = #{user.email}
        WHERE id = #{user.id}
    </foreach>
</update>
```

### set 동적 업데이트

```xml
<update id="updateUser" parameterType="User">
    UPDATE users
    <set>
        <if test="name != null">name = #{name},</if>
        <if test="email != null">email = #{email},</if>
        <if test="age != null">age = #{age},</if>
    </set>
    WHERE id = #{id}
</update>
```

## 결과 매핑

### 기본 ResultMap

```xml
<resultMap id="userResultMap" type="User">
    <id property="id" column="user_id"/>
    <result property="name" column="user_name"/>  
    <result property="email" column="user_email"/>
    <result property="createDate" column="create_date"/>
</resultMap>
```

### 연관관계 매핑

```xml
<!-- 1:1 관계 -->
<resultMap id="userWithProfileMap" type="User">
    <id property="id" column="user_id"/>
    <result property="name" column="user_name"/>
    <association property="profile" javaType="Profile">
        <id property="id" column="profile_id"/>
        <result property="bio" column="bio"/>
    </association>
</resultMap>

<!-- 1:N 관계 -->
<resultMap id="userWithOrdersMap" type="User">
    <id property="id" column="user_id"/>
    <result property="name" column="user_name"/>
    <collection property="orders" ofType="Order">
        <id property="id" column="order_id"/>
        <result property="amount" column="amount"/>
        <result property="orderDate" column="order_date"/>
    </collection>
</resultMap>
```

### 중첩 Select (N+1 문제 주의)

```xml
<resultMap id="userWithOrdersMap" type="User">
    <id property="id" column="user_id"/>
    <result property="name" column="user_name"/>
    <collection property="orders" column="user_id" select="findOrdersByUserId"/>
</resultMap>

<select id="findOrdersByUserId" resultType="Order">
    SELECT * FROM orders WHERE user_id = #{userId}
</select>
```

## 캐시

### 1차 캐시 (SqlSession 레벨)

- 기본적으로 활성화
- 같은 SqlSession 내에서 동일한 쿼리 결과 캐시

### 2차 캐시 (Mapper 레벨)

```xml
<!-- 매퍼 레벨 캐시 활성화 -->
<cache eviction="LRU" 
       flushInterval="60000" 
       size="512" 
       readOnly="true"/>

<!-- 또는 커스텀 캐시 -->
<cache type="com.example.MyCustomCache">
    <property name="cacheFile" value="/tmp/my-custom-cache.tmp"/>
</cache>

<!-- 캐시 사용하지 않는 쿼리 -->
<select id="findById" resultType="User" useCache="false">
    SELECT * FROM users WHERE id = #{id}
</select>
```

## 어노테이션

### 기본 CRUD 어노테이션

```java
@Mapper
public interface UserMapper {
    
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(Long id);
    
    @Select("SELECT * FROM users ORDER BY name")
    List<User> findAll();
    
    @Insert("INSERT INTO users(name, email) VALUES(#{name}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);
    
    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    int update(User user);
    
    @Delete("DELETE FROM users WHERE id = #{id}")
    int deleteById(Long id);
}
```

### 동적 SQL 어노테이션

```java
@SelectProvider(type = UserSqlProvider.class, method = "findByCondition")
List<User> findByCondition(UserSearchCondition condition);

public class UserSqlProvider {
    public String findByCondition(UserSearchCondition condition) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("users");
        
        if (condition.getName() != null) {
            sql.WHERE("name = #{name}");
        }
        if (condition.getEmail() != null) {
            sql.WHERE("email = #{email}");
        }
        
        return sql.toString();
    }
}
```

### ResultMap 어노테이션

```java
@Results(id = "userMap", value = {
    @Result(property = "id", column = "user_id", id = true),
    @Result(property = "name", column = "user_name"),
    @Result(property = "email", column = "user_email")
})
@Select("SELECT user_id, user_name, user_email FROM users WHERE id = #{id}")
User findById(Long id);

@ResultMap("userMap")
@Select("SELECT user_id, user_name, user_email FROM users")
List<User> findAll();
```

## Spring Boot 통합

### Configuration 클래스

```java
@Configuration
@MapperScan("com.example.mapper")
public class MyBatisConfig {
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(
            new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        return sessionFactory.getObject();
    }
}
```

### Service 클래스 예제

```java
@Service
@Transactional
public class UserService {
    
    private final UserMapper userMapper;
    
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userMapper.findById(id);
    }
    
    public User createUser(User user) {
        userMapper.insert(user);
        return user;
    }
    
    public User updateUser(User user) {
        userMapper.update(user);
        return user;
    }
    
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }
}
```

## 유용한 팁

### 페이징 처리

```xml
<!-- MySQL -->
<select id="findUsersWithPaging" resultType="User">
    SELECT * FROM users 
    ORDER BY id 
    LIMIT #{offset}, #{limit}
</select>

<!-- Oracle -->
<select id="findUsersWithPaging" resultType="User">
    SELECT * FROM (
        SELECT ROWNUM rn, u.* FROM users u 
        WHERE ROWNUM &lt;= #{offset} + #{limit}
    ) WHERE rn > #{offset}
</select>
```

### 트랜잭션 처리

```java
@Transactional(rollbackFor = Exception.class)
public void transferMoney(Long fromId, Long toId, BigDecimal amount) {
    accountMapper.withdraw(fromId, amount);
    accountMapper.deposit(toId, amount);
}
```

### 배치 처리

```java
@Transactional
public void insertUsers(List<User> users) {
    // 배치 크기로 나누어 처리
    int batchSize = 1000;
    for (int i = 0; i < users.size(); i += batchSize) {
        int end = Math.min(i + batchSize, users.size());
        userMapper.insertBatch(users.subList(i, end));
    }
}
```

### 자주 사용하는 설정

```yaml
mybatis:
  configuration:
    # 카멜케이스 자동 변환
    map-underscore-to-camel-case: true
    # 지연 로딩 활성화
    lazy-loading-enabled: true
    # 프록시 팩토리 설정
    proxy-factory: CGLIB
    # NULL 값 처리
    call-setters-on-nulls: true
    # 로그 설정
    log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl

logging:
  level:
    com.example.mapper: DEBUG
```

이 Cheat Sheet는 MyBatis의 핵심 기능들을 빠르게 참조할 수 있도록 정리한 것입니다. 실제 프로젝트에서 필요에 따라 상세한 설정을 추가하거나 수정하여 사용하시면 됩니다.