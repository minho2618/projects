# MongoDB Cheat Sheet

## 📋 목차
- [기본 개념](#기본-개념)
- [연결 및 데이터베이스 관리](#연결-및-데이터베이스-관리)
- [컬렉션 관리](#컬렉션-관리)
- [문서 CRUD 작업](#문서-crud-작업)
- [쿼리 및 검색](#쿼리-및-검색)
- [인덱스](#인덱스)
- [집계 (Aggregation)](#집계-aggregation)
- [데이터 타입](#데이터-타입)
- [유용한 명령어](#유용한-명령어)

---

## 🔧 기본 개념

| SQL 용어 | MongoDB 용어 | 설명 |
|----------|--------------|------|
| Database | Database | 데이터베이스 |
| Table | Collection | 테이블 = 컬렉션 |
| Row | Document | 행 = 문서 |
| Column | Field | 열 = 필드 |
| Index | Index | 인덱스 |
| Join | Lookup/Populate | 조인 |

---

## 🔌 연결 및 데이터베이스 관리

### MongoDB 연결
```bash
# 로컬 MongoDB 연결
mongosh

# 원격 MongoDB 연결
mongosh "mongodb://username:password@host:port/database"

# MongoDB Atlas 연결
mongosh "mongodb+srv://username:password@cluster.mongodb.net/database"
```

### 데이터베이스 명령어
```javascript
// 현재 데이터베이스 확인
db

// 데이터베이스 목록 보기
show dbs

// 데이터베이스 선택/생성
use myDatabase

// 데이터베이스 삭제
db.dropDatabase()

// 데이터베이스 통계
db.stats()
```

---

## 📁 컬렉션 관리

```javascript
// 컬렉션 목록 보기
show collections

// 컬렉션 생성
db.createCollection("myCollection")

// 컬렉션 삭제
db.myCollection.drop()

// 컬렉션 이름 변경
db.myCollection.renameCollection("newName")

// 컬렉션 통계
db.myCollection.stats()
```

---

## 📝 문서 CRUD 작업

### Create (생성)
```javascript
// 단일 문서 삽입
db.users.insertOne({
  name: "John Doe",
  age: 30,
  email: "john@example.com"
})

// 여러 문서 삽입
db.users.insertMany([
  { name: "Alice", age: 25, email: "alice@example.com" },
  { name: "Bob", age: 35, email: "bob@example.com" }
])

// save() - upsert 동작
db.users.save({ _id: ObjectId("..."), name: "Updated Name" })
```

### Read (조회)
```javascript
// 모든 문서 조회
db.users.find()

// 조건부 조회
db.users.find({ age: 30 })

// 단일 문서 조회
db.users.findOne({ name: "John Doe" })

// 특정 필드만 조회 (projection)
db.users.find({}, { name: 1, email: 1, _id: 0 })

// 정렬, 제한, 건너뛰기
db.users.find().sort({ age: -1 }).limit(10).skip(5)

// 문서 개수 세기
db.users.countDocuments({ age: { $gte: 25 } })
```

### Update (수정)
```javascript
// 단일 문서 수정
db.users.updateOne(
  { name: "John Doe" },
  { $set: { age: 31, city: "Seoul" } }
)

// 여러 문서 수정
db.users.updateMany(
  { age: { $lt: 30 } },
  { $set: { status: "young" } }
)

// 문서 교체
db.users.replaceOne(
  { name: "John Doe" },
  { name: "John Smith", age: 32, email: "johnsmith@example.com" }
)

// upsert (없으면 생성, 있으면 수정)
db.users.updateOne(
  { email: "new@example.com" },
  { $set: { name: "New User", age: 25 } },
  { upsert: true }
)
```

### Delete (삭제)
```javascript
// 단일 문서 삭제
db.users.deleteOne({ name: "John Doe" })

// 여러 문서 삭제
db.users.deleteMany({ age: { $lt: 18 } })

// 모든 문서 삭제
db.users.deleteMany({})
```

---

## 🔍 쿼리 및 검색

### 비교 연산자
```javascript
// 같음
db.users.find({ age: 30 })

// 같지 않음
db.users.find({ age: { $ne: 30 } })

// 크기 비교
db.users.find({ age: { $gt: 25 } })      // 초과
db.users.find({ age: { $gte: 25 } })     // 이상
db.users.find({ age: { $lt: 35 } })      // 미만
db.users.find({ age: { $lte: 35 } })     // 이하

// 범위
db.users.find({ age: { $gte: 25, $lte: 35 } })

// 포함
db.users.find({ age: { $in: [25, 30, 35] } })
db.users.find({ age: { $nin: [25, 30, 35] } })
```

### 논리 연산자
```javascript
// AND (기본)
db.users.find({ age: 30, city: "Seoul" })

// OR
db.users.find({
  $or: [
    { age: 30 },
    { city: "Seoul" }
  ]
})

// NOT
db.users.find({ age: { $not: { $gte: 30 } } })

// NOR
db.users.find({
  $nor: [
    { age: 30 },
    { city: "Seoul" }
  ]
})
```

### 배열 쿼리
```javascript
// 배열 요소 포함
db.users.find({ hobbies: "reading" })

// 모든 요소 포함
db.users.find({ hobbies: { $all: ["reading", "swimming"] } })

// 배열 크기
db.users.find({ hobbies: { $size: 3 } })

// 배열 요소 조건
db.users.find({ "scores.0": { $gt: 85 } })
```

### 정규식
```javascript
// 패턴 매칭
db.users.find({ name: /^J/ })              // J로 시작
db.users.find({ name: /doe$/i })           // doe로 끝남 (대소문자 무시)
db.users.find({ email: /@gmail\.com$/ })   // Gmail 주소
```

### 존재 및 타입 확인
```javascript
// 필드 존재 확인
db.users.find({ email: { $exists: true } })

// 타입 확인
db.users.find({ age: { $type: "number" } })
db.users.find({ name: { $type: "string" } })
```

---

## 📊 인덱스

```javascript
// 인덱스 목록 보기
db.users.getIndexes()

// 단일 필드 인덱스 생성
db.users.createIndex({ email: 1 })        // 오름차순
db.users.createIndex({ age: -1 })          // 내림차순

// 복합 인덱스
db.users.createIndex({ name: 1, age: -1 })

// 텍스트 인덱스
db.users.createIndex({ name: "text", description: "text" })

// 고유 인덱스
db.users.createIndex({ email: 1 }, { unique: true })

// 부분 인덱스
db.users.createIndex(
  { age: 1 },
  { partialFilterExpression: { age: { $gte: 18 } } }
)

// 인덱스 삭제
db.users.dropIndex("email_1")
db.users.dropIndex({ email: 1 })

// 모든 인덱스 삭제 (_id 제외)
db.users.dropIndexes()

// 쿼리 실행 계획 확인
db.users.find({ email: "john@example.com" }).explain("executionStats")
```

---

## 📈 집계 (Aggregation)

### 기본 집계 파이프라인
```javascript
db.orders.aggregate([
  // 매치 단계
  { $match: { status: "completed" } },

  // 그룹화 단계
  {
    $group: {
      _id: "$customerId",
      totalAmount: { $sum: "$amount" },
      orderCount: { $sum: 1 },
      avgAmount: { $avg: "$amount" }
    }
  },

  // 정렬 단계
  { $sort: { totalAmount: -1 } },

  // 제한 단계
  { $limit: 10 },

  // 프로젝션 단계
  {
    $project: {
      customerId: "$_id",
      totalAmount: 1,
      orderCount: 1,
      avgAmount: { $round: ["$avgAmount", 2] },
      _id: 0
    }
  }
])
```

### 주요 집계 연산자
```javascript
// 수학 연산자
{ $sum: "$amount" }              // 합계
{ $avg: "$amount" }              // 평균
{ $min: "$amount" }              // 최솟값
{ $max: "$amount" }              // 최댓값
{ $first: "$date" }              // 첫 번째 값
{ $last: "$date" }               // 마지막 값

// 배열 연산자
{ $push: "$item" }               // 배열에 추가
{ $addToSet: "$category" }       // 중복 제거하여 배열에 추가
{ $size: "$items" }              // 배열 크기

// 날짜 연산자
{ $year: "$createdAt" }          // 연도 추출
{ $month: "$createdAt" }         // 월 추출
{ $dayOfWeek: "$createdAt" }     // 요일 추출

// 문자열 연산자
{ $concat: ["$firstName", " ", "$lastName"] }  // 문자열 결합
{ $substr: ["$name", 0, 3] }     // 부분 문자열
{ $toUpper: "$name" }            // 대문자 변환
{ $toLower: "$email" }           // 소문자 변환
```

### 고급 집계 예제
```javascript
// 조인 (lookup)
db.orders.aggregate([
  {
    $lookup: {
      from: "customers",
      localField: "customerId",
      foreignField: "_id",
      as: "customer"
    }
  },
  { $unwind: "$customer" }
])

// 조건부 그룹화
db.sales.aggregate([
  {
    $group: {
      _id: {
        $cond: {
          if: { $gte: ["$amount", 1000] },
          then: "high",
          else: "low"
        }
      },
      count: { $sum: 1 },
      totalAmount: { $sum: "$amount" }
    }
  }
])
```

---

## 🔢 데이터 타입

```javascript
// ObjectId
ObjectId("507f1f77bcf86cd799439011")

// 문자열
"Hello World"

// 숫자
42                    // 정수
3.14159              // 실수
NumberLong("9223372036854775807")  // 64비트 정수
NumberDecimal("99.99")             // 고정소수점

// 불린
true
false

// 배열
["apple", "banana", "cherry"]

// 객체/문서
{
  name: "John",
  age: 30,
  address: {
    city: "Seoul",
    country: "Korea"
  }
}

// 날짜
new Date()
ISODate("2024-01-15T10:30:00Z")

// null
null

// 정규식
/pattern/flags
```

---

## 🛠️ 유용한 명령어

### 데이터베이스 정보
```javascript
// 서버 상태
db.serverStatus()

// 현재 작업 확인
db.currentOp()

// 프로파일링 활성화
db.setProfilingLevel(2)

// 슬로우 쿼리 확인
db.system.profile.find().sort({ ts: -1 }).limit(5)
```

### 데이터 가져오기/내보내기
```bash
# 데이터 내보내기
mongodump --db myDatabase --out /backup/

# 데이터 가져오기
mongorestore --db myDatabase /backup/myDatabase/

# JSON 내보내기
mongoexport --db myDatabase --collection users --out users.json

# JSON 가져오기
mongoimport --db myDatabase --collection users --file users.json
```

### 복제 및 샤딩
```javascript
// 복제 세트 상태
rs.status()

// 복제 세트 설정
rs.initiate()

// 샤드 상태
sh.status()

// 컬렉션 샤딩 활성화
sh.shardCollection("myDatabase.users", { "userId": 1 })
```

### 성능 모니터링
```javascript
// 실행 통계
db.users.find({ age: 30 }).explain("executionStats")

// 인덱스 사용량 통계
db.users.aggregate([{ $indexStats: {} }])

// 컬렉션 통계
db.users.stats()

// 데이터베이스 통계
db.stats()
```

---

## 💡 유용한 팁

1. **인덱스 최적화**: 자주 쿼리하는 필드에 인덱스를 생성하세요.
2. **프로젝션 사용**: 필요한 필드만 가져와 네트워크 트래픽을 줄이세요.
3. **집계 파이프라인**: 복잡한 데이터 처리에는 집계를 활용하세요.
4. **스키마 설계**: MongoDB의 비정규화된 접근 방식을 고려하세요.
5. **연결 풀링**: 프로덕션 환경에서는 연결 풀을 사용하세요.

---

이 cheat sheet는 MongoDB의 핵심 기능들을 빠르게 참조할 수 있도록 구성되었습니다. 더 자세한 정보는 [MongoDB 공식 문서](https://docs.mongodb.com/)를 참조하세요.
