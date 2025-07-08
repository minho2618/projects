물론! Express는 Node.js에서 가장 널리 쓰이는 웹 프레임워크 중 하나야. 아래는 **Express.js 전체 개요를 빠르게 정리한 Cheat Sheet**야. 실무와 학습에 유용하도록 구조, API, 미들웨어, 라우팅 등을 요약했어.

---

# 🚀 Express.js Cheat Sheet

## 📦 1. 프로젝트 초기화

bash

복사편집

`npm init -y npm install express`

js

복사편집

``// app.js const express = require('express'); const app = express(); const PORT = 3000;  app.listen(PORT, () => {   console.log(`Server is running on port ${PORT}`); });``

---

## 🛣️ 2. 기본 라우팅

js

복사편집

`app.get('/', (req, res) => {   res.send('Hello Express!'); });  app.post('/user', (req, res) => {   res.send('User created'); });`

|메서드|설명|
|---|---|
|`app.get()`|데이터 조회 (READ)|
|`app.post()`|데이터 생성 (CREATE)|
|`app.put()`|데이터 수정 (UPDATE)|
|`app.delete()`|데이터 삭제 (DELETE)|

---

## 🧩 3. 미들웨어

js

복사편집

``app.use(express.json()); // JSON body 파싱 app.use(express.urlencoded({ extended: true })); // form 파싱  // 커스텀 미들웨어 app.use((req, res, next) => {   console.log(`${req.method} ${req.path}`);   next(); });``

- `app.use()` → 글로벌 미들웨어 등록
    
- `next()` → 다음 미들웨어로 전달
    

---

## 🗃️ 4. 정적 파일 제공

js

복사편집

`app.use(express.static('public'));`

→ `/public/index.html` → `/index.html`로 접근 가능

---

## 📁 5. 라우터 분리

js

복사편집

`// routes/user.js const express = require('express'); const router = express.Router();  router.get('/', (req, res) => res.send('User list')); router.post('/', (req, res) => res.send('Create user'));  module.exports = router;  // app.js const userRouter = require('./routes/user'); app.use('/users', userRouter);`

---

## 📄 6. 요청 & 응답 객체

js

복사편집

`app.get('/info', (req, res) => {   console.log(req.query);    // 쿼리 스트링   console.log(req.params);   // 라우트 파라미터   console.log(req.body);     // 요청 본문    res.status(200).json({ msg: 'OK' }); // 응답 });`

---

## ⚠️ 7. 에러 처리

js

복사편집

`// 라우터 후에 위치해야 함 app.use((err, req, res, next) => {   console.error(err.stack);   res.status(500).send('Something broke!'); });`

---

## 🔐 8. 환경변수 (.env)

bash

복사편집

`npm install dotenv`

js

복사편집

`// app.js require('dotenv').config(); const PORT = process.env.PORT || 3000;`

env

복사편집

`PORT=4000`

---

## 🧪 9. 테스트용 서버 실행 (nodemon)

bash

복사편집

`npm install -D nodemon`

json

복사편집

`// package.json "scripts": {   "dev": "nodemon app.js" }`

---

## 🔄 10. RESTful API 예시

js

복사편집

`// GET /posts app.get('/posts', (req, res) => {});  // POST /posts app.post('/posts', (req, res) => {});  // GET /posts/:id app.get('/posts/:id', (req, res) => {});  // PUT /posts/:id app.put('/posts/:id', (req, res) => {});  // DELETE /posts/:id app.delete('/posts/:id', (req, res) => {});`

---

## 💡 11. 자주 쓰는 유틸

|기능|코드|
|---|---|
|리다이렉트|`res.redirect('/new-path')`|
|응답 코드 설정|`res.status(404).send('Not Found')`|
|JSON 응답|`res.json({ message: 'OK' })`|
|쿠키 읽기/쓰기|`req.cookies`, `res.cookie('name', 'value')` _(cookie-parser 필요)_|

---

## 📚 12. 추천 패키지

|패키지|용도|
|---|---|
|`morgan`|HTTP 로그|
|`cors`|CORS 허용|
|`helmet`|보안 설정|
|`cookie-parser`|쿠키 파싱|
|`dotenv`|환경변수|
|`express-validator`|요청값 검증|

---

## 🧩 13. Express Generator (프로젝트 템플릿)

bash

복사편집

`npm install -g express-generator express myapp --view=pug cd myapp && npm install npm start`

---

## 📘 공식 문서

- [https://expressjs.com/](https://expressjs.com/)
    

---

필요하다면 PDF, Notion 포맷, 특정 기능만 깊이 파서도 줄 수 있어!  
어떤 기능(예: 인증, MongoDB 연결 등)이든 더 자세히 알고 싶으면 말해줘!

##### 나의 말:

인증과 DB 연결도 추가해줘

###### ChatGPT의 말:

좋아, 그럼 기존 Express Cheat Sheet에 **인증(Auth)**과 **데이터베이스 연결(DB)** 항목을 **추가 확장**해서 제공할게.  
💡 **JWT 기반 인증** + **MongoDB (Mongoose)** 기준으로 설명할게. (필요 시 MySQL 예시도 줄 수 있음)

---

# ✅ Express 인증 + DB 연동 확장 Cheat Sheet

---

## 🔐 14. 인증 - JWT 기반

### 1) 설치

bash

복사편집

`npm install jsonwebtoken bcryptjs`

---

### 2) 유저 모델 (MongoDB 기준)

js

복사편집

`// models/User.js const mongoose = require('mongoose'); const bcrypt = require('bcryptjs');  const userSchema = new mongoose.Schema({   email: { type: String, unique: true },   password: String, });  userSchema.pre('save', async function () {   if (this.isModified('password')) {     this.password = await bcrypt.hash(this.password, 10);   } });  userSchema.methods.comparePassword = function (plainPwd) {   return bcrypt.compare(plainPwd, this.password); };  module.exports = mongoose.model('User', userSchema);`

---

### 3) 로그인/회원가입 라우팅

js

복사편집

`// routes/auth.js const express = require('express'); const jwt = require('jsonwebtoken'); const User = require('../models/User'); const router = express.Router();  // POST /auth/register router.post('/register', async (req, res) => {   const user = new User(req.body);   await user.save();   res.status(201).json({ message: 'User registered' }); });  // POST /auth/login router.post('/login', async (req, res) => {   const { email, password } = req.body;   const user = await User.findOne({ email });   if (!user || !(await user.comparePassword(password))) {     return res.status(401).json({ error: 'Invalid credentials' });   }    const token = jwt.sign({ userId: user._id }, process.env.JWT_SECRET, {     expiresIn: '1h',   });   res.json({ token }); });  module.exports = router;`

---

### 4) 인증 미들웨어

js

복사편집

`// middleware/auth.js const jwt = require('jsonwebtoken');  module.exports = (req, res, next) => {   const authHeader = req.headers.authorization;   if (!authHeader?.startsWith('Bearer ')) {     return res.status(401).json({ error: 'Unauthorized' });   }    try {     const token = authHeader.split(' ')[1];     const decoded = jwt.verify(token, process.env.JWT_SECRET);     req.user = decoded; // { userId }     next();   } catch (err) {     res.status(401).json({ error: 'Invalid token' });   } };`

---

### 5) 보호된 라우트 예시

js

복사편집

`const auth = require('./middleware/auth');  app.get('/profile', auth, async (req, res) => {   const user = await User.findById(req.user.userId).select('-password');   res.json(user); });`

---

## 🛢️ 15. 데이터베이스 연결 - MongoDB (Mongoose)

### 1) 설치

bash

복사편집

`npm install mongoose`

---

### 2) 연결 코드

js

복사편집

`// db.js const mongoose = require('mongoose');  const connectDB = async () => {   try {     await mongoose.connect(process.env.MONGO_URI, {       useNewUrlParser: true,       useUnifiedTopology: true,     });     console.log('✅ MongoDB connected');   } catch (err) {     console.error('❌ MongoDB connection error:', err);     process.exit(1);   } };  module.exports = connectDB;`

js

복사편집

`// app.js require('dotenv').config(); const express = require('express'); const connectDB = require('./db'); const authRoutes = require('./routes/auth');  const app = express(); connectDB();  app.use(express.json()); app.use('/auth', authRoutes);  app.listen(3000, () => console.log('Server running'));`

---

### 3) .env 예시

ini

복사편집

`PORT=3000 MONGO_URI=mongodb://localhost:27017/myapp JWT_SECRET=my_super_secret_key`

---

✅ 참고: MongoDB 대신 **MySQL + Sequelize** 쓰고 싶다면 따로 예시 만들어줄게.  
📦 인증 방식도 **세션 기반 로그인, OAuth, 쿠키 기반 인증** 등 다양하게 확장 가능해.

필요한 방식 알려주면 바로 만들어줄게!