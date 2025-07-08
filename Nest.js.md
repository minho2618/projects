[[Node.js]] 
[[Typescript]] 
# 의존성

```shell
npm install @nestjs/common@7.6.17 @nestjs/core@7.6.17 @nestjs/platform-express@7.6.17 reflect-metadata@0.1.13 typescript@4.3.2
```

# 설치

```shell
# nest-cli 설치
npm install -g @nestjs/cli

# 프로젝트 생성
nest new name
```

# 네이밍 규칙
1. 파일명은 .으로 연결합니다. 모듈이 둘 이상의 단어로 구성되어 있으면 대시로 연결합니다.

```TypeScript
// <모듈명>.<컴포넌트명>.ts
hello.controller.ts
my-first.controller.ts
```

2. 

# 3. **기본 개념 및 아키텍처**

Nest.js는 **모듈(Module) 기반 아키텍처**를 사용하여 애플리케이션을 구성합니다. 이 구조는 **대규모 애플리케이션에서도 유지보수와 확장성을 높이는 역할**을 합니다.

이번 단계에서는 Nest.js의 핵심 개념을 설명하고, **컨트롤러(Controller), 서비스(Service), 프로바이더(Provider), 의존성 주입(DI)** 등의 개념을 살펴보겠습니다.

---

## **1. Nest.js의 핵심 개념**

Nest.js의 주요 개념은 다음과 같습니다.



---

# Nest.js의 기본 구조

```
│── README.md
|── .gitignore                  # git 버전 관리에서 제외할 목록 지정
|── .prettierrc                 # 코드 포매팅 관련 설정 파일
|── nest-cli.json               # nest-cli 설정
|── package-lock.json
|── package.json
|── src/
|   │── app.controller.ts       # 컨트롤러
|   │── app.controller.spec.ts  # 컨트롤러 테스트 코드
|   │── app.module.ts           # 모듈
|   │── app.service.ts          # 서비스
|   │── main.ts                 # 서비스 메인 파일
|── test/
|   │── app.e2e-spec.ts
|   │── jset-e2e.json
│── tsconfig.build.json     
│── tsconfig.json               # 타입스크립트 설정
```

| 개념                                   | 설명                                              |
| ------------------------------------ | ----------------------------------------------- |
| **모듈(Module)**                       | Nest.js 애플리케이션을 구성하는 기본 단위. 관련된 기능을 하나의 모듈로 그룹화 |
| **컨트롤러(Controller)**                 | 클라이언트 요청을 처리하고 응답을 반환                           |
| **서비스(Service)**                     | 비즈니스 로직을 처리하는 역할                                |
| **프로바이더(Provider)**                  | 서비스를 주입하여 재사용성을 높이는 역할                          |
| **의존성 주입(DI, Dependency Injection)** | 객체 간의 의존성을 관리하여 코드의 유지보수성을 향상                   |

이제 각 개념을 코드와 함께 자세히 살펴보겠습니다.

---

## 🚀 **3. 컨트롤러(Controller)**

컨트롤러는 HTTP 요청을 받아서 처리하는 역할을 합니다.

### 📌 **컨트롤러 생성**

Nest.js에서 컨트롤러를 만들려면 `@Controller()` 데코레이터를 사용합니다.

### **예제: `src/app.controller.ts`**

```tsx
typescript

import { Controller, Get } from '@nestjs/common';

@Controller('users') // '/users' 경로로 요청을 받음
export class AppController {
  @Get()
  getAllUsers(): string {
    return '모든 사용자 정보를 반환합니다.';
  }

  @Get(':id')
  getUserById(): string {
    return '특정 사용자 정보를 반환합니다.';
  }
}

```

> 📝 설명
> 
> - `@Controller('users')`: 이 컨트롤러는 `/users` 경로의 요청을 처리합니다.
> - `@Get()`: HTTP GET 요청을 처리하는 메서드입니다.
> - `@Get(':id')`: URL 파라미터 `id`를 받아 특정 유저 정보를 가져옵니다.

---

## ⚡ **4. 서비스(Service)**

서비스는 **비즈니스 로직을 담당하는 클래스**로, 컨트롤러에서 호출하여 데이터를 처리합니다.

### 📌 **서비스 생성**

서비스를 만들려면 `@Injectable()` 데코레이터를 사용합니다.

### **예제: `src/app.service.ts`**

```tsx
typescript

import { Injectable } from '@nestjs/common';

@Injectable() // 이 클래스를 서비스로 사용 가능하게 만듦
export class AppService {
  getAllUsers(): string {
    return '모든 사용자 목록을 반환합니다.';
  }

  getUserById(id: number): string {
    return `ID가 ${id}인 사용자의 정보를 반환합니다.`;
  }
}

```

> 📝 설명
> 
> - `@Injectable()`: 이 클래스가 다른 곳에서 **의존성 주입(DI)**으로 사용될 수 있도록 설정합니다.
> - `getAllUsers()`: 모든 사용자 목록을 반환하는 메서드.
> - `getUserById(id: number)`: 특정 사용자 정보를 반환하는 메서드.

---

## 🔗 **5. 컨트롤러와 서비스 연결**

컨트롤러에서 서비스를 사용하려면 **의존성 주입(DI)**을 활용해야 합니다.

### **예제: `src/app.controller.ts`**

```tsx
typescript

import { Controller, Get, Param } from '@nestjs/common';
import { AppService } from './app.service';

@Controller('users')
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getAllUsers(): string {
    return this.appService.getAllUsers();
  }

  @Get(':id')
  getUserById(@Param('id') id: string): string {
    return this.appService.getUserById(Number(id));
  }
}

```

> 📝 설명
> 
> - `constructor(private readonly appService: AppService) {}`
>     - Nest.js는 자동으로 `appService` 인스턴스를 주입합니다.
>     - 이렇게 하면 `AppController`에서 `AppService`의 메서드를 사용할 수 있습니다.
> - `@Param('id') id: string`
>     - `@Param('id')` 데코레이터는 URL에서 `id` 값을 추출합니다.
>     - `Number(id)`: `id` 값이 문자열이므로 숫자로 변환합니다.

---

## 🏗 **6. 모듈(Module)에서 컨트롤러와 서비스 등록**

Nest.js에서는 모든 컨트롤러와 서비스를 **모듈(Module)**에 등록해야 합니다.

### **예제: `src/app.module.ts`**

```tsx
typescript

import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';

@Module({
  controllers: [AppController], // 컨트롤러 등록
  providers: [AppService], // 서비스 등록
})
export class AppModule {}

```

> 📝 설명
> 
> - `controllers: [AppController]`: `AppController`를 이 모듈에서 사용할 수 있도록 등록.
> - `providers: [AppService]`: `AppService`를 이 모듈에서 사용할 수 있도록 등록.

---

## ✅ **7. 서버 실행 및 테스트**

이제 애플리케이션을 실행하고 API를 테스트해 봅시다.

```
sh

npm run start

```

### **테스트 방법**

1. **모든 사용자 가져오기**
    - 요청: `GET <http://localhost:3000/users`>
    - 응답: `"모든 사용자 목록을 반환합니다."`
2. **특정 사용자 가져오기 (예: ID=1)**
    - 요청: `GET <http://localhost:3000/users/1`>
    - 응답: `"ID가 1인 사용자의 정보를 반환합니다."`

---

## 🎯 **정리**

- **컨트롤러(Controller)**: HTTP 요청을 처리하는 역할
- **서비스(Service)**: 비즈니스 로직을 담당하며 컨트롤러에서 호출
- **의존성 주입(DI)**: Nest.js가 서비스 인스턴스를 자동으로 컨트롤러에 주입
- **모듈(Module)**: Nest.js에서 모든 기능을 그룹화하는 기본 단위

이제 Nest.js의 기본적인 아키텍처를 이해했어!

**'다음'**을 입력하면 **"기본 API 개발"**을 진행할게! 🚀

# 4. **기본 API 개발**

이번 단계에서는 **Nest.js에서 RESTful API를 개발하는 방법**을 배웁니다.

**CRUD(Create, Read, Update, Delete) API를 구현**하면서, Nest.js에서 **DTO(Data Transfer Object)와 유효성 검사**를 어떻게 활용하는지도 설명할게요.

---

## 🚀 **1. 프로젝트 설정 및 기본 API 엔드포인트**

우리는 **사용자(User) 관리 API**를 만들 것입니다.

이 API는 사용자를 추가(Create), 조회(Read), 수정(Update), 삭제(Delete)하는 기능을 제공합니다.

### **📂 프로젝트 폴더 구조**

```
plaintext

src/
│── users/
│   ├── users.controller.ts   # 사용자 요청을 처리하는 컨트롤러
│   ├── users.service.ts      # 비즈니스 로직을 처리하는 서비스
│   ├── users.module.ts       # 사용자 모듈
│   ├── dto/                  # DTO(Data Transfer Object) 폴더
│   │   ├── create-user.dto.ts  # 유저 생성 DTO
│   │   ├── update-user.dto.ts  # 유저 업데이트 DTO
│
│── app.module.ts             # 애플리케이션의 루트 모듈
│── main.ts                   # 서버 실행 파일

```

---

## 🏗 **2. 사용자(User) 모듈 생성**

먼저 `users` 모듈을 생성합니다.

```
sh

nest generate module users

```

또는

```
sh

nest g mo users

```

> 📝 설명
> 
> - `nest g mo users`: `users` 모듈을 생성합니다.

---

## 🎯 **3. 사용자(User) 서비스 생성**

서비스는 사용자 데이터를 관리하는 로직을 포함합니다.

```
sh

nest generate service users

```

또는

```
sh

nest g s users

```

> 📝 설명
> 
> - `nest g s users`: `users.service.ts` 파일을 생성합니다.

📌 **`src/users/users.service.ts` (서비스 파일)**

```tsx
typescript

import { Injectable } from '@nestjs/common';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';

@Injectable()
export class UsersService {
  private users = [];

  // 모든 사용자 조회
  findAll() {
    return this.users;
  }

  // 특정 사용자 조회
  findOne(id: number) {
    return this.users.find(user => user.id === id);
  }

  // 새로운 사용자 생성
  create(createUserDto: CreateUserDto) {
    const newUser = { id: Date.now(), ...createUserDto };
    this.users.push(newUser);
    return newUser;
  }

  // 사용자 정보 수정
  update(id: number, updateUserDto: UpdateUserDto) {
    const userIndex = this.users.findIndex(user => user.id === id);
    if (userIndex === -1) return null;

    this.users[userIndex] = { ...this.users[userIndex], ...updateUserDto };
    return this.users[userIndex];
  }

  // 사용자 삭제
  remove(id: number) {
    this.users = this.users.filter(user => user.id !== id);
    return { message: 'User deleted successfully' };
  }
}

```

> 📝 설명
> 
> - `findAll()`: 모든 사용자 데이터를 배열에서 가져옴.
> - `findOne(id)`: 특정 ID의 사용자 찾기.
> - `create(createUserDto)`: 새로운 사용자 생성.
> - `update(id, updateUserDto)`: 기존 사용자 정보 업데이트.
> - `remove(id)`: 특정 사용자 삭제.

---

## 🌍 **4. 사용자(User) 컨트롤러 생성**

이제 컨트롤러를 생성합니다.

```
sh

nest generate controller users

```

또는

```
sh

nest g co users

```

> 📝 설명
> 
> - `nest g co users`: `users.controller.ts` 파일을 생성합니다.

📌 **`src/users/users.controller.ts` (컨트롤러 파일)**

```tsx
typescript

import { Controller, Get, Post, Body, Param, Put, Delete } from '@nestjs/common';
import { UsersService } from './users.service';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';

@Controller('users') // '/users' 경로에서 요청을 처리
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get()
  findAll() {
    return this.usersService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.usersService.findOne(Number(id));
  }

  @Post()
  create(@Body() createUserDto: CreateUserDto) {
    return this.usersService.create(createUserDto);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() updateUserDto: UpdateUserDto) {
    return this.usersService.update(Number(id), updateUserDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.usersService.remove(Number(id));
  }
}

```

> 📝 설명
> 
> - `@Get()` → 모든 사용자 데이터 반환.
> - `@Get(':id')` → 특정 사용자 조회.
> - `@Post()` → 새로운 사용자 추가.
> - `@Put(':id')` → 특정 사용자 정보 업데이트.
> - `@Delete(':id')` → 특정 사용자 삭제.

---

## ✅ **5. DTO(Data Transfer Object) 생성**

Nest.js에서는 **DTO(Data Transfer Object)**를 사용하여 **입력 데이터를 검증**합니다.

```
sh

mkdir src/users/dto
touch src/users/dto/create-user.dto.ts
touch src/users/dto/update-user.dto.ts

```

📌 **`src/users/dto/create-user.dto.ts` (새로운 사용자 생성 DTO)**

```tsx
typescript

import { IsString, IsInt, MinLength } from 'class-validator';

export class CreateUserDto {
  @IsString()
  @MinLength(2)
  name: string;

  @IsInt()
  age: number;
}

```

📌 **`src/users/dto/update-user.dto.ts` (사용자 정보 수정 DTO)**

```tsx
typescript

import { IsOptional, IsString, IsInt, MinLength } from 'class-validator';

export class UpdateUserDto {
  @IsOptional()
  @IsString()
  @MinLength(2)
  name?: string;

  @IsOptional()
  @IsInt()
  age?: number;
}

```

> 📝 설명
> 
> - `@IsString()`, `@IsInt()` 등 유효성 검사 라이브러리를 사용하여 입력 데이터를 검증.
> - `@IsOptional()`을 사용하여 선택적으로 수정 가능.

유효성 검사를 사용하려면 **`class-validator` 및 `class-transformer` 패키지**를 설치해야 합니다.

```
sh

npm install class-validator class-transformer

```

---

## 🚀 **6. 모듈(Module) 설정**

마지막으로 `users` 모듈에 컨트롤러와 서비스를 등록합니다.

📌 **`src/users/users.module.ts`**

```tsx
typescript

import { Module } from '@nestjs/common';
import { UsersController } from './users.controller';
import { UsersService } from './users.service';

@Module({
  controllers: [UsersController],
  providers: [UsersService],
})
export class UsersModule {}

```

📌 **`src/app.module.ts`** (메인 모듈에서 `UsersModule` 추가)

```tsx
typescript

import { Module } from '@nestjs/common';
import { UsersModule } from './users/users.module';

@Module({
  imports: [UsersModule],
})
export class AppModule {}

```

---

## 🎯 **7. API 테스트**

이제 애플리케이션을 실행하고 API를 테스트해 봅시다.

```
sh

npm run start

```

테스트 요청:

- **GET** `http://localhost:3000/users`
- **POST** `http://localhost:3000/users` (Body: `{ "name": "John", "age": 30 }`)
- **PUT** `http://localhost:3000/users/1` (Body: `{ "name": "Doe" }`)
- **DELETE** `http://localhost:3000/users/1`

---

이제 기본적인 CRUD API 개발이 완료되었습니다!

**'다음'**을 입력하면 **"데이터베이스 연동"**을 설명할게요! 🚀



# 5. **데이터베이스 연동**

이번 단계에서는 **Nest.js와 데이터베이스를 연동하는 방법**을 배웁니다.

Nest.js에서 가장 많이 사용하는 **TypeORM**을 활용하여 **MySQL 또는 PostgreSQL**에 연결하고, CRUD를 데이터베이스에서 수행할 수 있도록 설정하겠습니다.

---

## 📌 **1. TypeORM 및 데이터베이스 설치**

우리는 **TypeORM + MySQL**을 사용할 것입니다.

(MySQL 대신 PostgreSQL을 사용하려면 `mysql2` 대신 `pg` 패키지를 설치하면 됩니다.)

```
sh

npm install @nestjs/typeorm typeorm mysql2

```

> 📝 설명
> 
> - `@nestjs/typeorm`: Nest.js에서 TypeORM을 쉽게 사용할 수 있도록 하는 모듈.
> - `typeorm`: TypeORM 라이브러리.
> - `mysql2`: MySQL 드라이버. (PostgreSQL을 사용할 경우 `pg` 설치)

---

## ⚙ **2. 데이터베이스 설정**

`app.module.ts`에서 **데이터베이스 연결을 설정**합니다.

📌 **`src/app.module.ts`**

```tsx
typescript

import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UsersModule } from './users/users.module';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'mysql', // PostgreSQL 사용 시 'postgres'
      host: 'localhost',
      port: 3306,
      username: 'root',  // 데이터베이스 사용자명
      password: 'password', // 비밀번호
      database: 'nest_db', // 사용할 데이터베이스 이름
      entities: [__dirname + '/**/*.entity.{ts,js}'], // 엔터티 자동 로드
      synchronize: true, // 개발 환경에서만 true (자동으로 테이블 생성)
    }),
    UsersModule,
  ],
})
export class AppModule {}

```

> 📝 설명
> 
> - `TypeOrmModule.forRoot()`를 사용하여 데이터베이스 연결을 설정.
> - `entities` 옵션을 통해 자동으로 엔터티를 스캔.
> - `synchronize: true`는 **개발 환경에서만 사용** (자동으로 테이블을 생성 및 업데이트).

---

## 🏗 **3. User 엔터티(Entity) 생성**

이제 **User 엔터티(데이터베이스 테이블과 매핑될 클래스)**를 생성합니다.

```
sh

mkdir src/users/entities
touch src/users/entities/user.entity.ts

```

📌 **`src/users/entities/user.entity.ts`**

```tsx
typescript

import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity()
export class User {
  @PrimaryGeneratedColumn()
  id: number; // 자동 증가 ID

  @Column()
  name: string;

  @Column()
  age: number;
}

```

> 📝 설명
> 
> - `@Entity()`: 이 클래스가 데이터베이스 테이블과 매핑됨을 의미.
> - `@PrimaryGeneratedColumn()`: 기본 키(primary key)이며 자동 증가하는 `id` 필드.
> - `@Column()`: 일반적인 컬럼으로 사용될 필드.

---

## 🔗 **4. Repository(리포지토리) 생성**

Nest.js에서는 **TypeORM의 Repository 패턴을 사용하여 데이터베이스와 상호작용**합니다.

📌 **`src/users/users.module.ts`**

리포지토리를 `UsersModule`에 등록해야 합니다.

```tsx
typescript

import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UsersController } from './users.controller';
import { UsersService } from './users.service';
import { User } from './entities/user.entity';

@Module({
  imports: [TypeOrmModule.forFeature([User])], // User 엔터티를 등록
  controllers: [UsersController],
  providers: [UsersService],
})
export class UsersModule {}

```

> 📝 설명
> 
> - `TypeOrmModule.forFeature([User])`: **이 모듈에서 User 엔터티를 사용할 수 있도록 설정**.

---

## 📂 **5. Service(서비스)에서 Repository 사용**

이제 **`users.service.ts`**에서 TypeORM의 Repository를 사용하여 **데이터베이스에서 CRUD를 수행**합니다.

📌 **`src/users/users.service.ts`**

```tsx
typescript

import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { User } from './entities/user.entity';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';

@Injectable()
export class UsersService {
  constructor(
    @InjectRepository(User)
    private readonly userRepository: Repository<User>,
  ) {}

  async findAll(): Promise<User[]> {
    return this.userRepository.find();
  }

  async findOne(id: number): Promise<User> {
    return this.userRepository.findOne({ where: { id } });
  }

  async create(createUserDto: CreateUserDto): Promise<User> {
    const newUser = this.userRepository.create(createUserDto);
    return this.userRepository.save(newUser);
  }

  async update(id: number, updateUserDto: UpdateUserDto): Promise<User> {
    await this.userRepository.update(id, updateUserDto);
    return this.findOne(id);
  }

  async remove(id: number): Promise<void> {
    await this.userRepository.delete(id);
  }
}

```

> 📝 설명
> 
> - `@InjectRepository(User) private readonly userRepository: Repository<User>`:
>     - `User` 엔터티에 대한 Repository를 주입하여 사용.
> - `this.userRepository.find()`: 모든 사용자 조회.
> - `this.userRepository.findOne({ where: { id } })`: 특정 사용자 조회.
> - `this.userRepository.create()`: 새 사용자 엔터티 생성.
> - `this.userRepository.save()`: 사용자 데이터 저장.
> - `this.userRepository.update()`: 사용자 정보 업데이트.
> - `this.userRepository.delete()`: 사용자 삭제.

---

## 🌍 **6. Controller에서 Service 호출**

이제 컨트롤러에서 **서비스를 호출하여 API를 완성**합니다.

📌 **`src/users/users.controller.ts`**

```tsx
typescript

import { Controller, Get, Post, Body, Param, Put, Delete } from '@nestjs/common';
import { UsersService } from './users.service';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';

@Controller('users')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get()
  findAll() {
    return this.usersService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.usersService.findOne(Number(id));
  }

  @Post()
  create(@Body() createUserDto: CreateUserDto) {
    return this.usersService.create(createUserDto);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() updateUserDto: UpdateUserDto) {
    return this.usersService.update(Number(id), updateUserDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.usersService.remove(Number(id));
  }
}

```

> 📝 설명
> 
> - 서비스에서 데이터를 받아서 API 응답을 처리.

---

## ✅ **7. API 테스트**

이제 애플리케이션을 실행하고 **데이터베이스가 연동된 CRUD API를 테스트**합니다.

```
sh

npm run start

```

테스트 요청:

1. **GET** `http://localhost:3000/users`
    
2. **POST** `http://localhost:3000/users`
    
    ```json
    json
    
    {
      "name": "Alice",
      "age": 25
    }
    
    ```
    
3. **PUT** `http://localhost:3000/users/1`
    
    ```json
    json
    
    {
      "name": "Bob"
    }
    
    ```
    
4. **DELETE** `http://localhost:3000/users/1`
    

---

이제 Nest.js와 데이터베이스를 연동하여 CRUD API를 완성했습니다!

**'다음'**을 입력하면 **"미들웨어 및 인터셉터"**를 설명할게요! 🚀



# 6. 미들웨어 및 인터셉터
이번 단계에서는 **미들웨어(Middleware)와 인터셉터(Interceptor)**의 개념과 사용법을 배우고, Nest.js에서 이를 활용하는 방법을 실습해보겠습니다.

---

## 🔹 **1. 미들웨어(Middleware)란?**

미들웨어는 **요청(Request)과 응답(Response) 사이에서 실행되는 함수**입니다.

주로 **로그 기록, 인증, 요청 변환, 에러 핸들링** 등의 작업을 수행할 때 사용됩니다.

Nest.js에서 미들웨어는 **Express 미들웨어 방식과 유사**하지만, `@Middleware()` 데코레이터가 필요하지 않고 **클래스 또는 함수로 구현**됩니다.

---

## 📌 **2. 미들웨어 생성**

Nest.js에서는 **미들웨어를 직접 생성하여 특정 모듈에서 사용할 수 있습니다.**

### 1️⃣ **미들웨어 파일 생성**

```
sh

mkdir src/common/middleware
touch src/common/middleware/logger.middleware.ts

```

📌 **`src/common/middleware/logger.middleware.ts`**

```tsx
typescript

import { Injectable, NestMiddleware } from '@nestjs/common';
import { Request, Response, NextFunction } from 'express';

@Injectable()
export class LoggerMiddleware implements NestMiddleware {
  use(req: Request, res: Response, next: NextFunction) {
    console.log(`[${new Date().toISOString()}] ${req.method} ${req.url}`);
    next(); // 다음 미들웨어 또는 컨트롤러 실행
  }
}

```

> 📝 설명
> 
> - `NestMiddleware` 인터페이스를 구현하여 `use()` 메서드를 정의합니다.
> - `req.method`, `req.url`을 사용하여 요청 정보를 로깅합니다.
> - `next()`를 호출해야 요청이 다음 단계(컨트롤러)로 넘어갑니다.

---

## 🔗 **3. 미들웨어 적용**

미들웨어는 특정 **모듈의 `configure()` 메서드에서 설정**해야 합니다.

📌 **`src/users/users.module.ts`**

```tsx
typescript

import { MiddlewareConsumer, Module, NestModule } from '@nestjs/common';
import { UsersController } from './users.controller';
import { UsersService } from './users.service';
import { LoggerMiddleware } from '../common/middleware/logger.middleware';

@Module({
  controllers: [UsersController],
  providers: [UsersService],
})
export class UsersModule implements NestModule {
  configure(consumer: MiddlewareConsumer) {
    consumer.apply(LoggerMiddleware).forRoutes(UsersController);
  }
}

```

> 📝 설명
> 
> - `configure(consumer: MiddlewareConsumer)`: 미들웨어를 특정 라우트에 적용하는 설정 메서드.
> - `consumer.apply(LoggerMiddleware)`: `LoggerMiddleware` 적용.
> - `.forRoutes(UsersController)`: `UsersController`의 모든 경로에 미들웨어 적용.

---

## 🚀 **4. 미들웨어 테스트**

서버를 실행하고 API를 호출하면 요청이 로그에 남는 것을 확인할 수 있습니다.

```
sh

npm run start

```

### **테스트 요청**

```
sh

curl -X GET <http://localhost:3000/users>

```

### **출력 예시 (콘솔 로그)**

```
bash

[2025-03-07T12:34:56.789Z] GET /users

```

---

## 🔹 **5. 인터셉터(Interceptor)란?**

인터셉터는 **미들웨어와 비슷하지만 더 강력한 기능을 제공**하는 Nest.js의 중요한 개념입니다.

주로 **응답을 변환하거나, 성능을 측정하거나, 요청을 가로채 추가 로직을 실행**하는 용도로 사용됩니다.

|개념|실행 시점|주요 역할|
|---|---|---|
|**미들웨어**|요청 → 컨트롤러 실행 전|요청 데이터 변환, 로깅, 인증|
|**인터셉터**|요청 → 컨트롤러 실행 후 → 응답 전|응답 데이터 변환, 성능 측정, 캐싱|

---

## 📌 **6. 인터셉터 생성**

Nest.js의 인터셉터는 **클래스를 생성한 후 `@Injectable()` 데코레이터를 사용하여 구현**합니다.

### 1️⃣ **인터셉터 파일 생성**

```
sh

mkdir src/common/interceptors
touch src/common/interceptors/transform.interceptor.ts

```

📌 **`src/common/interceptors/transform.interceptor.ts`**

```tsx
typescript

import {
  CallHandler,
  ExecutionContext,
  Injectable,
  NestInterceptor,
} from '@nestjs/common';
import { Observable, map } from 'rxjs';

@Injectable()
export class TransformInterceptor implements NestInterceptor {
  intercept(context: ExecutionContext, next: CallHandler): Observable<any> {
    console.log('📌 인터셉터가 실행되었습니다.');
    return next.handle().pipe(
      map((data) => ({
        success: true,
        data, // 원래 응답 데이터
      })),
    );
  }
}

```

> 📝 설명
> 
> - `intercept(context, next)`: 요청을 가로채서 응답을 변환하는 메서드.
> - `next.handle().pipe(map())`: 컨트롤러의 응답을 가공하여 변환.
> - 모든 응답을 `{ success: true, data: ... }` 형식으로 변환.

---

## 🔗 **7. 인터셉터 적용**

인터셉터는 **전역, 특정 컨트롤러, 특정 메서드**에 적용할 수 있습니다.

### ✅ **1. 전역 인터셉터 적용**

전역 인터셉터를 사용하려면 `main.ts`에서 설정합니다.

📌 **`src/main.ts`**

```tsx
typescript

import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { TransformInterceptor } from './common/interceptors/transform.interceptor';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // 전역 인터셉터 적용
  app.useGlobalInterceptors(new TransformInterceptor());

  await app.listen(3000);
  console.log(`🚀 Server is running on http://localhost:3000`);
}
bootstrap();

```

---

### ✅ **2. 특정 컨트롤러에 인터셉터 적용**

컨트롤러에 적용할 수도 있습니다.

📌 **`src/users/users.controller.ts`**

```tsx
typescript

import { Controller, Get, UseInterceptors } from '@nestjs/common';
import { UsersService } from './users.service';
import { TransformInterceptor } from '../common/interceptors/transform.interceptor';

@Controller('users')
@UseInterceptors(TransformInterceptor) // 컨트롤러에 인터셉터 적용
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get()
  findAll() {
    return this.usersService.findAll();
  }
}

```

---

### ✅ **3. 특정 메서드에 인터셉터 적용**

메서드 단위로 적용할 수도 있습니다.

📌 **`src/users/users.controller.ts`**

```tsx
typescript

import { Get, UseInterceptors } from '@nestjs/common';

@Get()
@UseInterceptors(TransformInterceptor)
findAll() {
  return this.usersService.findAll();
}

```

---

## 🚀 **8. 인터셉터 테스트**

서버를 실행하고 API를 호출하면 **응답이 변경**되는 것을 확인할 수 있습니다.

```
sh

npm run start

```

### **테스트 요청**

```
sh

curl -X GET <http://localhost:3000/users>

```

### **응답 예시 (변환된 JSON)**

```json
json

{
  "success": true,
  "data": []
}

```

> 📝 설명
> 
> - 원래 단순한 배열(`[]`)이었지만, 인터셉터가 `{ success: true, data: [] }` 형태로 변환함.

---

## ✅ **정리**

|기능|미들웨어|인터셉터|
|---|---|---|
|실행 시점|요청을 받으면 실행됨|요청이 컨트롤러에 도착 후, 응답이 반환되기 전 실행됨|
|주 사용 목적|로깅, 인증, 요청 변환|응답 변환, 성능 측정, 캐싱|
|적용 방법|`consumer.apply()`로 적용|`@UseInterceptors()` 또는 전역 설정|

이제 Nest.js에서 미들웨어와 인터셉터를 활용하는 방법을 배웠습니다!

**'다음'**을 입력하면 **"인증 및 보안"**을 설명할게요! 🚀
# 7. **인증 및 보안**

이번 단계에서는 **Nest.js에서 인증(Authentication)과 보안(Security)**을 어떻게 구현하는지 배웁니다.

우리는 **JWT(Json Web Token)를 활용한 로그인 인증**을 구현할 것이며, **Passport.js를 이용한 전략 기반 인증**, **가드(Guard)**, 그리고 기본적인 보안 설정을 다룰 거예요.

---

## 🔐 **1. 인증 흐름 이해**

Nest.js에서 일반적인 인증 흐름은 다음과 같습니다:

1. 사용자가 **로그인 요청**을 보냄 (`POST /auth/login`)
2. 서버는 사용자를 확인한 뒤 **JWT 토큰을 발급**
3. 사용자는 이후 모든 요청에 **JWT를 포함**하여 보냄
4. 서버는 JWT를 검증해 요청을 허용 또는 거부

---

## 📦 **2. 패키지 설치**

JWT 인증을 위해 필요한 패키지를 설치합니다.

```bash
bash

npm install @nestjs/jwt @nestjs/passport passport passport-jwt bcrypt
npm install --save-dev @types/passport-jwt @types/bcrypt

```

> 📝 설명
> 
> - `@nestjs/jwt`: JWT 토큰 발급 및 검증
> - `passport`: 인증 미들웨어
> - `passport-jwt`: JWT 전략
> - `bcrypt`: 비밀번호 해시 처리

---

## ⚙️ **3. Auth 모듈 및 서비스 생성**

```bash
bash

nest g module auth
nest g service auth
nest g controller auth

```

---

## 🛠 **4. 사용자 엔터티에 비밀번호 추가**

📌 `src/users/entities/user.entity.ts`

```
ts

import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class User {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  name: string;

  @Column()
  age: number;

  @Column()
  password: string; // 비밀번호 컬럼 추가
}

```

---

## ✏️ **5. 비밀번호 암호화 적용**

📌 `src/users/users.service.ts`

```
ts

import * as bcrypt from 'bcrypt';

async create(createUserDto: CreateUserDto): Promise<User> {
  const hashed = await bcrypt.hash(createUserDto.password, 10);
  const newUser = this.userRepository.create({ ...createUserDto, password: hashed });
  return this.userRepository.save(newUser);
}

```

> 비밀번호는 반드시 저장 전에 해시 처리해야 합니다.

---

## ✨ **6. JWT 전략 구현**

### 📌 `src/auth/jwt.strategy.ts`

```
ts

import { Injectable } from '@nestjs/common';
import { PassportStrategy } from '@nestjs/passport';
import { ExtractJwt, Strategy } from 'passport-jwt';

@Injectable()
export class JwtStrategy extends PassportStrategy(Strategy) {
  constructor() {
    super({
      jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
      secretOrKey: 'jwt-secret-key', // 실제 환경에선 환경변수 사용
    });
  }

  async validate(payload: any) {
    return { userId: payload.sub, username: payload.username };
  }
}

```

---

## 🔐 **7. JWT 모듈 구성**

📌 `src/auth/auth.module.ts`

```
ts

import { Module } from '@nestjs/common';
import { JwtModule } from '@nestjs/jwt';
import { PassportModule } from '@nestjs/passport';
import { AuthService } from './auth.service';
import { AuthController } from './auth.controller';
import { UsersModule } from '../users/users.module';
import { JwtStrategy } from './jwt.strategy';

@Module({
  imports: [
    UsersModule,
    PassportModule,
    JwtModule.register({
      secret: 'jwt-secret-key',
      signOptions: { expiresIn: '1h' },
    }),
  ],
  controllers: [AuthController],
  providers: [AuthService, JwtStrategy],
})
export class AuthModule {}

```

---

## ✉️ **8. 로그인 구현**

### 📌 `src/auth/auth.service.ts`

```
ts

import { Injectable, UnauthorizedException } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { UsersService } from '../users/users.service';
import * as bcrypt from 'bcrypt';

@Injectable()
export class AuthService {
  constructor(
    private usersService: UsersService,
    private jwtService: JwtService,
  ) {}

  async validateUser(username: string, password: string): Promise<any> {
    const users = await this.usersService.findAll();
    const user = users.find(u => u.name === username);
    if (user && await bcrypt.compare(password, user.password)) {
      const { password, ...result } = user;
      return result;
    }
    return null;
  }

  async login(user: any) {
    const payload = { username: user.name, sub: user.id };
    return {
      access_token: this.jwtService.sign(payload),
    };
  }
}

```

---

## 📌 `src/auth/auth.controller.ts`

```
ts

import { Controller, Post, Body, UnauthorizedException } from '@nestjs/common';
import { AuthService } from './auth.service';

@Controller('auth')
export class AuthController {
  constructor(private authService: AuthService) {}

  @Post('login')
  async login(@Body() body: { username: string; password: string }) {
    const user = await this.authService.validateUser(body.username, body.password);
    if (!user) {
      throw new UnauthorizedException();
    }
    return this.authService.login(user);
  }
}

```

---

## 🛡 **9. JWT 보호 라우트 만들기**

Nest에서는 `@UseGuards()`와 `AuthGuard('jwt')`를 통해 **JWT가 필요한 API를 보호**할 수 있습니다.

📌 `src/users/users.controller.ts`

```
ts

import { UseGuards } from '@nestjs/common';
import { AuthGuard } from '@nestjs/passport';

@UseGuards(AuthGuard('jwt'))
@Get()
findAll() {
  return this.usersService.findAll();
}

```

---

## ✅ **10. 테스트 흐름**

1. **회원 가입**
    
    ```
    http
    
    POST /users
    {
      "name": "user1",
      "age": 30,
      "password": "pass123"
    }
    
    ```
    
2. **로그인 후 JWT 발급**
    
    ```
    http
    
    POST /auth/login
    {
      "username": "user1",
      "password": "pass123"
    }
    
    ```
    
    응답:
    
    ```json
    json
    
    { "access_token": "eyJhbGci..." }
    
    ```
    
3. **JWT 포함 요청**
    
    ```
    sql
    
    GET /users
    Authorization: Bearer eyJhbGci...
    
    ```
    

---

## ✅ 보안 팁

- `.env` 파일로 **JWT 시크릿 키 분리**: `ConfigModule` 활용
- **CORS 설정**: `app.enableCors()` 활용
- **HTTPS 적용**
- **Rate Limiting** (요청 제한): `nestjs/throttler`

---

이제 Nest.js에서 JWT 기반 인증 시스템을 구축했어요!

**'다음'**을 입력하면 **GraphQL API 개발**을 다룰게요!
# 8. **GraphQL API 개발**

이번 단계에서는 **Nest.js에서 GraphQL을 사용하는 방법**을 배우고,

**Query / Mutation / Resolver 구조**를 이해하면서 GraphQL 기반의 사용자(User) API를 만들어 보겠습니다.

Nest.js는 **Code First**와 **Schema First** 두 가지 방식을 모두 지원합니다.

여기서는 **TypeScript 기반 Code First 방식**으로 진행할게요.

---

## 📦 **1. 패키지 설치 (Code First 방식)**

```bash
bash

npm install @nestjs/graphql @nestjs/apollo graphql apollo-server-express

```

> 📝 설명
> 
> - `@nestjs/graphql`: Nest에서 GraphQL 기능 제공
> - `@nestjs/apollo`: Apollo 서버 어댑터
> - `graphql`: 핵심 GraphQL 라이브러리
> - `apollo-server-express`: Express 기반 Apollo 서버

---

## ⚙ **2. GraphQL 모듈 설정**

📌 **`src/app.module.ts`**

```
ts

import { Module } from '@nestjs/common';
import { GraphQLModule } from '@nestjs/graphql';
import { ApolloDriver, ApolloDriverConfig } from '@nestjs/apollo';
import { UsersModule } from './users/users.module';
import { join } from 'path';

@Module({
  imports: [
    GraphQLModule.forRoot<ApolloDriverConfig>({
      driver: ApolloDriver,
      autoSchemaFile: join(process.cwd(), 'src/schema.gql'), // Code-first
    }),
    UsersModule,
  ],
})
export class AppModule {}

```

> autoSchemaFile은 컴파일 시 GraphQL 스키마를 자동 생성해줍니다.

---

## 🧱 **3. GraphQL용 User 모델 작성**

### 1️⃣ **User Entity + GraphQL Object Type**

📌 **`src/users/entities/user.entity.ts`**

```
ts

import { ObjectType, Field, Int } from '@nestjs/graphql';
import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@ObjectType() // GraphQL 타입으로 노출
@Entity()
export class User {
  @Field(() => Int)
  @PrimaryGeneratedColumn()
  id: number;

  @Field()
  @Column()
  name: string;

  @Field(() => Int)
  @Column()
  age: number;

  @Column()
  password: string; // GraphQL에는 노출하지 않음
}

```

> @ObjectType()은 GraphQL에 이 클래스를 타입으로 노출합니다.
> 
> `@Field()`는 GraphQL에서 필드를 의미하고, `password`는 생략하여 숨김 처리할 수 있습니다.

---

## ✏️ **4. DTOs → GraphQL Input Type으로 변경**

### 📌 `src/users/dto/create-user.input.ts`

```
ts

import { InputType, Field, Int } from '@nestjs/graphql';

@InputType()
export class CreateUserInput {
  @Field()
  name: string;

  @Field(() => Int)
  age: number;

  @Field()
  password: string;
}

```

### 📌 `src/users/dto/update-user.input.ts`

```
ts

import { InputType, Field, Int, PartialType } from '@nestjs/graphql';
import { CreateUserInput } from './create-user.input';

@InputType()
export class UpdateUserInput extends PartialType(CreateUserInput) {
  @Field(() => Int)
  id: number;
}

```

---

## 🧠 **5. Resolver 생성**

```bash
bash

nest g resolver users

```

📌 **`src/users/users.resolver.ts`**

```
ts

import { Resolver, Query, Mutation, Args, Int } from '@nestjs/graphql';
import { UsersService } from './users.service';
import { User } from './entities/user.entity';
import { CreateUserInput } from './dto/create-user.input';
import { UpdateUserInput } from './dto/update-user.input';

@Resolver(() => User)
export class UsersResolver {
  constructor(private readonly usersService: UsersService) {}

  @Mutation(() => User)
  createUser(@Args('createUserInput') createUserInput: CreateUserInput) {
    return this.usersService.create(createUserInput);
  }

  @Query(() => [User], { name: 'users' })
  findAll() {
    return this.usersService.findAll();
  }

  @Query(() => User, { name: 'user' })
  findOne(@Args('id', { type: () => Int }) id: number) {
    return this.usersService.findOne(id);
  }

  @Mutation(() => User)
  updateUser(@Args('updateUserInput') updateUserInput: UpdateUserInput) {
    const { id, ...rest } = updateUserInput;
    return this.usersService.update(id, rest);
  }

  @Mutation(() => Boolean)
  removeUser(@Args('id', { type: () => Int }) id: number) {
    return this.usersService.remove(id).then(() => true);
  }
}

```

---

## ✅ **6. GraphQL 서버 실행 및 테스트**

```bash
bash

npm run start

```

### 접속 주소:

```
bash

<http://localhost:3000/graphql>

```

### 예시 요청 (mutation):

```graphql
graphql

mutation {
  createUser(createUserInput: { name: "Tom", age: 29, password: "1234" }) {
    id
    name
    age
  }
}

```

### 예시 요청 (query):

```graphql
graphql

query {
  users {
    id
    name
    age
  }
}

```

---

## ✅ 정리

- `@ObjectType`, `@Field` → GraphQL에서 반환되는 객체 정의
- `@InputType`, `@Args` → 요청 받을 때 사용하는 입력값
- Resolver에서 Query/Mutation으로 사용자 정의 API 구성
- GraphQL 스키마가 자동 생성됨 (`src/schema.gql`)

---

이제 Nest.js에서 GraphQL 기반 API를 구축할 수 있어요!

**'다음'**을 입력하면 **"마이크로서비스 아키텍처"**를 소개할게요.
# 9. **마이크로서비스 아키텍처**

이번 단계에서는 **Nest.js로 마이크로서비스(Microservice) 아키텍처를 구성하는 방법**을 배웁니다.

Nest.js는 다양한 **Transport Layer**(TCP, Redis, NATS, Kafka, RabbitMQ, gRPC 등)를 지원하며, **확장성과 유연성**이 뛰어납니다.

이번 예제에서는 가장 기본이 되는 **TCP 기반 마이크로서비스**를 구성해볼게요.

---

## 🔷 **1. 마이크로서비스란?**

마이크로서비스는 시스템을 **작은 독립적인 서비스들로 나누어 구성하는 아키텍처**입니다.

### 특징

- 각각의 서비스는 **독립적인 실행/배포/확장 가능**
- 서비스 간 **통신은 메시지 기반**
- 장애 전파를 최소화할 수 있음

---

## ⚙ **2. 기본 구조**

Nest.js에서는 마이크로서비스를 다음과 같이 구성할 수 있습니다:

```
bash

apps/
├── main-app/       # 클라이언트 역할 (API 게이트웨이)
└── math-service/   # 서비스 역할 (비즈니스 처리 담당)

```

---

## 📦 **3. 프로젝트 초기화**

```bash
bash

nest new microservice-app
cd microservice-app

```

### 디렉토리 분리

```bash
bash

mkdir apps
mv src apps/main-app

```

---

## 🛠 **4. 수학 마이크로서비스 (math-service) 만들기**

```bash
bash

mkdir apps/math-service
touch apps/math-service/main.ts

```

📌 **`apps/math-service/main.ts`**

```
ts

import { NestFactory } from '@nestjs/core';
import { MicroserviceOptions, Transport } from '@nestjs/microservices';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.createMicroservice<MicroserviceOptions>(
    AppModule,
    {
      transport: Transport.TCP,
      options: {
        host: '127.0.0.1',
        port: 3001,
      },
    },
  );
  await app.listen();
  console.log('✅ Math microservice is running on port 3001');
}
bootstrap();

```

---

📌 **`apps/math-service/app.module.ts`**

```
ts

import { Module } from '@nestjs/common';
import { MathService } from './math.service';

@Module({
  providers: [MathService],
})
export class AppModule {}

```

📌 **`apps/math-service/math.service.ts`**

```
ts

import { Controller } from '@nestjs/common';
import { MessagePattern } from '@nestjs/microservices';

@Controller()
export class MathService {
  @MessagePattern({ cmd: 'sum' }) // 메시지의 패턴이 'sum'일 때 실행
  accumulate(data: number[]): number {
    return data.reduce((a, b) => a + b, 0);
  }
}

```

---

## 📡 **5. 메인 앱에서 마이크로서비스 호출**

📌 **`apps/main-app/main.ts`**

```
ts

import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { Transport, ClientProxyFactory } from '@nestjs/microservices';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // TCP 클라이언트 생성
  const client = ClientProxyFactory.create({
    transport: Transport.TCP,
    options: {
      host: '127.0.0.1',
      port: 3001,
    },
  });

  // 마이크로서비스 호출 예시
  const result = await client.send({ cmd: 'sum' }, [1, 2, 3, 4]).toPromise();
  console.log('Sum result:', result);

  await app.listen(3000);
}
bootstrap();

```

---

📌 **`apps/main-app/app.module.ts`**

```
ts

import { Module } from '@nestjs/common';

@Module({})
export class AppModule {}

```

---

## 🚀 **6. 실행**

### 터미널 1 (math-service)

```bash
bash

ts-node apps/math-service/main.ts

```

### 터미널 2 (main-app)

```bash
bash

ts-node apps/main-app/main.ts

```

### 출력 결과

```
sql

Sum result: 10

```

---

## ✅ 정리

|구성 요소|역할|
|---|---|
|`@MessagePattern()`|특정 명령을 수신하는 핸들러|
|`ClientProxy.send()`|마이크로서비스에게 요청을 보냄|
|TCP, Redis, NATS 등|다양한 메시지 전송 방식 지원|

---

Nest.js를 통해 **마이크로서비스 간 메시지 기반 통신을 쉽게 구현**할 수 있습니다.

**RabbitMQ, Kafka, Redis** 같은 브로커도 유사한 방식으로 설정할 수 있어요.

---

- *'다음'**을 입력하면 **"실시간 기능 추가 (WebSocket)"**을 배워볼게요!
# 10. **실시간 기능 추가 (WebSocket)**

이번 단계에서는 Nest.js에서 **WebSocket을 활용한 실시간 기능**을 구현하는 방법을 배웁니다.

실시간 채팅, 알림, 센서 데이터 스트리밍 등 이벤트 기반 양방향 통신에 적합합니다.

---

## 🔌 **1. WebSocket 이란?**

- **HTTP**: 요청이 있어야만 응답이 가능 (단방향)
- **WebSocket**: 서버와 클라이언트가 **항상 연결 상태를 유지하며 실시간 통신이 가능** (양방향)

Nest.js는 내부적으로 **[Socket.IO](http://Socket.IO)** 또는 `ws` 라이브러리를 사용할 수 있습니다.

이번에는 **[Socket.IO](http://Socket.IO) 기반** 예제를 사용하겠습니다.

---

## 📦 **2. 설치**

```bash
bash

npm install @nestjs/websockets @nestjs/platform-socket.io socket.io
npm install --save-dev @types/socket.io

```

---

## 🧱 **3. 게이트웨이(Gateway) 생성**

```bash
bash

nest g gateway chat

```

📌 **`src/chat/chat.gateway.ts`**

```
ts

import {
  SubscribeMessage,
  WebSocketGateway,
  OnGatewayInit,
  OnGatewayConnection,
  OnGatewayDisconnect,
  MessageBody,
  ConnectedSocket,
} from '@nestjs/websockets';
import { Server, Socket } from 'socket.io';

@WebSocketGateway({ cors: true }) // WebSocket 서버 생성
export class ChatGateway
  implements OnGatewayInit, OnGatewayConnection, OnGatewayDisconnect
{
  private server: Server;

  afterInit(server: Server) {
    this.server = server;
    console.log('✅ WebSocket 서버 초기화 완료');
  }

  handleConnection(client: Socket) {
    console.log(`🔌 클라이언트 연결됨: ${client.id}`);
  }

  handleDisconnect(client: Socket) {
    console.log(`❌ 클라이언트 연결 해제: ${client.id}`);
  }

  @SubscribeMessage('message')
  handleMessage(
    @MessageBody() message: string,
    @ConnectedSocket() client: Socket,
  ) {
    console.log(`📩 메시지 수신: ${message}`);

    // 모든 클라이언트에게 메시지 브로드캐스트
    this.server.emit('message', {
      sender: client.id,
      text: message,
    });
  }
}

```

> @SubscribeMessage('message'): 클라이언트가 message 이벤트를 보낼 때 호출
> 
> `this.server.emit('message', data)`: 모든 클라이언트에게 메시지 전송

---

## 🧩 **4. 모듈 등록**

📌 **`src/chat/chat.module.ts`**

```
ts

import { Module } from '@nestjs/common';
import { ChatGateway } from './chat.gateway';

@Module({
  providers: [ChatGateway],
})
export class ChatModule {}

```

📌 **`src/app.module.ts`**

```
ts

import { Module } from '@nestjs/common';
import { ChatModule } from './chat/chat.module';

@Module({
  imports: [ChatModule],
})
export class AppModule {}

```

---

## 🧪 **5. 클라이언트 테스트 (HTML)**

`index.html` 파일 생성:

```html
html

<!DOCTYPE html>
<html>
  <head>
    <title>Chat</title>
    <script src="<https://cdn.socket.io/4.0.1/socket.io.min.js>"></script>
  </head>
  <body>
    <input id="msg" placeholder="메시지 입력" />
    <button onclick="send()">전송</button>
    <ul id="chat"></ul>

    <script>
      const socket = io('<http://localhost:3000>');

      socket.on('connect', () => {
        console.log('서버에 연결됨!');
      });

      socket.on('message', (data) => {
        const item = document.createElement('li');
        item.textContent = `${data.sender}: ${data.text}`;
        document.getElementById('chat').appendChild(item);
      });

      function send() {
        const msg = document.getElementById('msg').value;
        socket.emit('message', msg);
        document.getElementById('msg').value = '';
      }
    </script>
  </body>
</html>

```

---

## ▶️ **6. 실행 후 테스트**

```bash
bash

npm run start

```

- 브라우저에서 `index.html` 열기
- 입력 후 “전송”을 누르면 메시지가 **모든 브라우저 탭에 실시간으로 나타납니다**

---

## ✅ 정리

|구성 요소|역할|
|---|---|
|`@WebSocketGateway()`|WebSocket 서버 생성|
|`@SubscribeMessage()`|클라이언트 이벤트 수신 핸들러|
|`this.server.emit()`|서버에서 클라이언트로 메시지 전송|
|`Socket.IO`|WebSocket을 추상화한 라이브러리로 브라우저 호환성 좋음|

---

실시간 기능까지 마쳤습니다!

**'다음'**을 입력하면 **테스팅 및 디버깅**을 다룰게요.
# 11. 테스팅 및 디버깅

이번 단계에서는 Nest.js 애플리케이션의 **테스트 코드 작성과 디버깅 방법**을 배웁니다.

Nest.js는 기본적으로 **[Jest](https://jestjs.io/)**를 테스트 러너로 사용하며,

**단위(Unit) 테스트**, **통합(Integration) 테스트**, **E2E 테스트**를 쉽게 작성할 수 있도록 구성되어 있습니다.

---

## ✅ **1. 테스트 종류**

|테스트 종류|설명|
|---|---|
|단위 테스트 (Unit Test)|하나의 컴포넌트(서비스, 함수 등)를 고립시켜 테스트|
|통합 테스트 (Integration Test)|여러 컴포넌트가 함께 작동하는지 테스트|
|E2E 테스트 (End-to-End)|전체 애플리케이션 흐름 테스트 (예: HTTP 요청 테스트)|

---

## 📦 **2. Nest.js 프로젝트 기본 테스트 구조**

Nest.js로 프로젝트를 생성하면 자동으로 테스트 파일이 포함됩니다.

```
css

src/
├── app.controller.ts
├── app.controller.spec.ts  ← 컨트롤러 테스트
├── app.service.ts
├── app.service.spec.ts     ← 서비스 테스트

```

---

## ⚙️ **3. 서비스 단위 테스트 예제**

### 📌 `src/users/users.service.spec.ts`

```
ts

import { Test, TestingModule } from '@nestjs/testing';
import { UsersService } from './users.service';

describe('UsersService', () => {
  let service: UsersService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [UsersService],
    }).compile();

    service = module.get<UsersService>(UsersService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  it('create() should return a user object', async () => {
    const mockUserDto = { name: 'Alice', age: 30, password: '1234' };
    const user = await service.create(mockUserDto);
    expect(user).toHaveProperty('id');
    expect(user.name).toBe('Alice');
  });
});

```

> 📝 설명
> 
> - `Test.createTestingModule()` → 테스트용 DI 컨테이너 생성
> - `expect(...)` → Jest의 단언(assertion) 메서드

---

## 🧪 **4. HTTP E2E 테스트 (e2e 테스트)**

### 📁 `test/app.e2e-spec.ts`

```
ts

import * as request from 'supertest';
import { Test } from '@nestjs/testing';
import { INestApplication } from '@nestjs/common';
import { AppModule } from './../src/app.module';

describe('AppController (e2e)', () => {
  let app: INestApplication;

  beforeAll(async () => {
    const moduleFixture = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    app = moduleFixture.createNestApplication();
    await app.init();
  });

  it('/ (GET)', () => {
    return request(app.getHttpServer())
      .get('/')
      .expect(200)
      .expect('Hello, Nest.js!');
  });
});

```

> 📝 설명
> 
> - `supertest`: HTTP 요청을 시뮬레이션하여 E2E 테스트 수행
> - `app.getHttpServer()` → Nest 서버 인스턴스에 직접 접근

---

## ▶️ **5. 테스트 실행**

```bash
bash

npm run test         # 전체 테스트 실행
npm run test:watch   # 파일 변경 시 자동 실행
npm run test:cov     # 테스트 커버리지 리포트 출력

```

---

## 🐞 **6. 디버깅 방법**

### ✅ 1. **console.log() 또는 `Logger` 사용**

```
ts

import { Logger } from '@nestjs/common';

const logger = new Logger('UserService');
logger.debug('디버깅 로그 출력');

```

### ✅ 2. **VSCode 디버거 설정 (`launch.json`)**

```json
json

{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "node",
      "request": "launch",
      "name": "Nest Debug",
      "args": ["${workspaceFolder}/src/main.ts"],
      "runtimeArgs": ["--nolazy", "-r", "ts-node/register"],
      "sourceMaps": true,
      "cwd": "${workspaceFolder}",
      "protocol": "inspector",
      "env": {
        "TS_NODE_PROJECT": "tsconfig.json"
      }
    }
  ]
}

```

> 실행 후 브레이크포인트를 설정하고 디버깅 가능!

---

## ✅ 정리

|도구|역할|
|---|---|
|Jest|테스트 러너|
|supertest|HTTP 테스트용 라이브러리|
|Logger|Nest 내장 로깅 유틸|
|VSCode 디버거|소스 코드 기반 디버깅|

---

# 12. 배포 및 운영

마지막 단계에서는 **Nest.js 애플리케이션을 배포하고 운영하는 방법**을 다룹니다.

빌드 → 환경 변수 설정 → Docker → 클라우드 배포까지의 기본 흐름을 익히고,

운영 단계에서 필요한 **로깅, 환경 구성, CI/CD**까지 간단히 정리할게요.

---

## **1. 빌드 및 실행 준비**

Nest.js는 TypeScript 기반이므로 **빌드 후 JavaScript 코드**로 실행해야 합니다.

### 빌드 명령

```bash
npm run build
```

- 출력 결과는 `dist/` 폴더에 생성됨

### 빌드된 앱 실행

```bash
node dist/main.js
```

---

## **2. 환경 변수 설정**

운영 환경에선 환경변수를 통해 설정을 관리합니다.

### `.env` 파일

```perl
PORT=3000
DB_HOST=localhost
DB_USER=root
DB_PASS=secret
JWT_SECRET=your-secret-key
```

### 설치 및 적용

```bash
npm install @nestjs/config
```

**`src/app.module.ts`**

```tsx
import { ConfigModule } from '@nestjs/config';

@Module({
  imports: [
    ConfigModule.forRoot({
      isGlobal: true, // 전역 모듈로 사용
    }),
  ],
})
export class AppModule {}
```

**사용 예시**

```tsx
import { ConfigService } from '@nestjs/config';

constructor(private configService: ConfigService) {
  const dbUser = this.configService.get('DB_USER');
}
```

---

## **3. Docker로 배포하기**

### `Dockerfile`

```docker
# 1. Node 베이스 이미지 선택
FROM node:18

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. 종속성 복사 및 설치
COPY package*.json ./
RUN npm install

# 4. 소스 코드 복사
COPY . .

# 5. Nest 앱 빌드
RUN npm run build

# 6. 앱 실행
CMD ["node", "dist/main"]
```

---

### `.dockerignore`

```docker
node_modules
dist
test
```

---

### Docker 빌드 및 실행

```bash
docker build -t my-nest-app .
docker run -p 3000:3000 my-nest-app
```

---

## **4. 클라우드에 배포하기 (예: Render, Heroku, EC2)**

- **Render**
    
    - GitHub 연동 → 자동 빌드 및 배포
    - Dockerfile 사용 가능
- **Heroku**
    
    ```bash
    heroku create
    git push heroku main
    heroku logs --tail
    ```
    
- **EC2**
    
    - SSH 접속 후 `git clone`, `npm install`, `npm run build`, `pm2`로 실행

---

## **5. PM2로 프로세스 관리**

```bash
npm install -g pm2

# 앱 실행
pm2 start dist/main.js --name nest-app

# 앱 상태 확인
pm2 list

# 앱 로그 확인
pm2 logs

# 서버 재부팅 시 자동 실행 설정
pm2 startup
pm2 save
```

---

## **6. CI/CD 예시 (GitHub Actions)**

📄 `.github/workflows/deploy.yml`

```yaml
name: Deploy Nest App

on:
  push:
    branches:
      - main

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout
        uses: actions/checkout@v3

      - name: Set up Node
        uses: actions/setup-node@v3
        with:
          node-version: 18

      - run: npm ci
      - run: npm run build
```

> 이후 Docker 배포나 SSH 자동 배포 설정도 연결 가능

---

## ✅ 정리

|항목|도구/방법|
|---|---|
|빌드|`npm run build`|
|환경 변수|`.env` + `@nestjs/config`|
|배포|Docker, Render, Heroku, AWS|
|운영|`pm2`, `nginx`, HTTPS, 로깅|
|자동화|GitHub Actions, Jenkins, GitLab CI|

---