#Nodejs #JavaScript #Cheat-Sheet 
# Node.js Backend API Cheat Sheet

---

## 1. 프로젝트 초기화

```bash
npm init -y              # package.json 기본 생성
npm install express      # Express 프레임워크 설치
npm install dotenv       # .env 환경 변수 사용을 위한 패키지
```

```env
# .env 파일
PORT=3000
```

```js
// src/index.js
require('dotenv').config();         // .env 파일의 환경 변수 로드
const express = require('express');
const app = express();

app.use(express.json());           // JSON 요청 본문 파싱 미들웨어

app.get('/', (req, res) => res.send('Hello, API!'));  // 기본 루트 라우트

app.listen(process.env.PORT);      // 지정된 포트에서 서버 실행
```

---

## 2. 라우팅 + 미들웨어

```js
const morgan = require('morgan');
app.use(morgan('dev'));             // HTTP 요청 로그 출력 미들웨어

app.get('/users/:id', (req, res) => {
  const id = req.params.id;        // URL 파라미터 추출
  res.json({ id });                // JSON 응답 반환
});
```

---

## 3. 입력 검증

```bash
npm install express-validator
```

```js
const { body, validationResult } = require('express-validator');

app.post('/users',
  body('email').isEmail(),                           // email 필드가 유효한 이메일인지 확인
  (req, res) => {
    const errors = validationResult(req);            // 유효성 검사 결과 가져오기
    if (!errors.isEmpty()) return res.status(400).json(errors.array());
    res.send('ok');                                   // 성공 시 응답
  });
```

---

## 4. JWT 인증

```bash
npm install jsonwebtoken bcrypt
```

```js
const jwt = require('jsonwebtoken');
const SECRET = 'my-secret'; // 실제 운영에선 .env로 관리

app.post('/login', async (req, res) => {
  // 사용자 검증 후 토큰 발급
  const token = jwt.sign({ userId: 1 }, SECRET, { expiresIn: '1h' });
  res.json({ token });
});

function auth(req, res, next) {
  const token = req.headers.authorization?.split(' ')[1]; // Bearer 토큰 추출
  try {
    req.user = jwt.verify(token, SECRET);                 // 토큰 검증
    next();                                                // 다음 미들웨어로
  } catch {
    res.status(401).send('Unauthorized');                 // 실패 시 인증 오류
  }
}
```

---

## 5. MongoDB 연동 (Mongoose)

```bash
npm install mongoose
```

```js
const mongoose = require('mongoose');
mongoose.connect(process.env.MONGO_URI); // 환경 변수에서 URI 가져옴

// 사용자 모델 정의
const User = mongoose.model('User', new mongoose.Schema({
  email: String, name: String
}));
```

---

## 6. MySQL 연동 (Sequelize)

```bash
npm install sequelize mysql2
```

```js
const { Sequelize, DataTypes } = require('sequelize');
// 데이터베이스 연결 인스턴스 생성
const sequelize = new Sequelize('db', 'root', 'pw', { dialect: 'mysql' });

// 모델 정의
const User = sequelize.define('User', {
  email: DataTypes.STRING
});
```

---

## 7. 에러 처리 미들웨어

```js
// 마지막에 등록되는 에러 핸들링 미들웨어
app.use((err, req, res, next) => {
  res.status(err.statusCode || 500).json({ message: err.message });
});
```

---

## 8. 보안

```bash
npm install helmet cors express-rate-limit
```

```js
const helmet = require('helmet');
const cors = require('cors');
const rateLimit = require('express-rate-limit');

app.use(helmet());                    // 보안 HTTP 헤더 설정
app.use(cors());                      // 모든 Origin 허용 (개발 시)
app.use(rateLimit({                  // IP별 요청 제한
  windowMs: 10000,                   // 10초
  max: 5
}));
```

---

## 9. 성능 최적화

```bash
npm install compression
```

```js
const compression = require('compression');
app.use(compression());              // Gzip 압축을 통한 응답 크기 감소
```

**Redis 캐싱 예시:**

```bash
npm install redis
```

```js
const redis = require('redis');
const client = redis.createClient();

// 캐시 설정 (60초 동안 유지)
client.setex('key', 60, JSON.stringify({ data: 'cached' }));
```

---

## 10. 테스트

```bash
npm install --save-dev jest supertest
```

```js
// test/app.test.js
const request = require('supertest');
const app = require('../src/index');

// '/' 라우트에 대한 통합 테스트
test('GET /', async () => {
  const res = await request(app).get('/');
  expect(res.statusCode).toBe(200);  // 응답 상태코드 확인
});
```

---

## 11. 배포

**Dockerfile:**

```Dockerfile
FROM node:18
WORKDIR /app
COPY . .
RUN npm install
EXPOSE 3000
CMD ["node", "src/index.js"]        # 서버 시작 명령
```

**PM2로 무중단 실행:**

```bash
npm install pm2 -g
pm2 start src/index.js -i max       # 모든 CPU 코어 활용
```

---

# 📦 부록: Prisma, TypeORM, 클린 아키텍처

---

## Prisma ORM

```bash
npm install prisma @prisma/client
npx prisma init
```

```prisma
// schema.prisma
model User {
  id    Int     @id @default(autoincrement())
  email String  @unique
}
```

```js
const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();

// 사용자 생성
await prisma.user.create({
  data: { email: 'test@example.com' }
});
```

---

## TypeORM

```bash
npm install typeorm reflect-metadata sqlite3
```

```ts
// entity/User.ts
@Entity()
export class User {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  email: string;
}
```

```ts
// DB 연결
const AppDataSource = new DataSource({
  type: 'sqlite',
  database: 'db.sqlite',
  entities: [User],
  synchronize: true
});
await AppDataSource.initialize();
```

---

## 클린 아키텍처 구조

```txt
src/
├─ controllers/    # Express 라우터
├─ usecases/       # 핵심 비즈니스 로직
├─ repositories/   # 추상화 인터페이스
├─ infra/          # DB 구현체
├─ entities/       # 도메인 모델
```

```ts
// usecases/CreateUserUseCase.ts
export class CreateUserUseCase {
  constructor(private userRepo: UserRepository) {}
  async execute(data: { email: string }) {
    const user = new User(data.email);
    return await this.userRepo.save(user);
  }
}
```

```ts
// controllers/UserController.ts
app.post('/users', async (req, res) => {
  const usecase = new CreateUserUseCase(new UserRepositoryImpl());
  const result = await usecase.execute(req.body);
  res.status(201).json(result);
});
```

---
