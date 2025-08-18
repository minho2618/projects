# FastAPI Cheat Sheet

## 설치 및 시작 (Installation & Setup)

```bash
# 설치
pip install fastapi
pip install "uvicorn[standard]"  # ASGI 서버

# 추가 패키지
pip install python-multipart  # 파일 업로드
pip install jinja2            # 템플릿 엔진
pip install python-jose[cryptography]  # JWT
pip install passlib[bcrypt]   # 패스워드 해싱
pip install sqlalchemy        # ORM
```

## 기본 앱 구조 (Basic App Structure)

```python
from fastapi import FastAPI

# 앱 인스턴스 생성
app = FastAPI(
    title="My API",
    description="API 설명",
    version="1.0.0",
    docs_url="/docs",        # Swagger UI 경로
    redoc_url="/redoc",      # ReDoc 경로
    openapi_url="/openapi.json"
)

# 기본 라우트
@app.get("/")
async def root():
    return {"message": "Hello World"}

# 서버 실행
# uvicorn main:app --reload --host 0.0.0.0 --port 8000
```

## HTTP 메서드 (HTTP Methods)

```python
from fastapi import FastAPI, status

app = FastAPI()

# GET - 데이터 조회
@app.get("/items/{item_id}")
async def read_item(item_id: int):
    return {"item_id": item_id}

# POST - 데이터 생성
@app.post("/items/", status_code=status.HTTP_201_CREATED)
async def create_item(item: dict):
    return item

# PUT - 전체 업데이트
@app.put("/items/{item_id}")
async def update_item(item_id: int, item: dict):
    return {"item_id": item_id, **item}

# PATCH - 부분 업데이트
@app.patch("/items/{item_id}")
async def partial_update(item_id: int, item: dict):
    return {"item_id": item_id, **item}

# DELETE - 삭제
@app.delete("/items/{item_id}", status_code=status.HTTP_204_NO_CONTENT)
async def delete_item(item_id: int):
    return None

# HEAD, OPTIONS 등도 지원
@app.head("/items/")
@app.options("/items/")
async def handle_head_options():
    return {"message": "OK"}
```

## 요청 파라미터 (Request Parameters)

### 경로 매개변수 (Path Parameters)

```python
from fastapi import Path

# 기본 경로 매개변수
@app.get("/users/{user_id}")
async def read_user(user_id: int):
    return {"user_id": user_id}

# 경로 매개변수 + 검증
@app.get("/items/{item_id}")
async def read_item(
    item_id: int = Path(
        ..., 
        title="Item ID",
        description="ID of the item",
        ge=1,  # 1 이상
        le=1000  # 1000 이하
    )
):
    return {"item_id": item_id}

# Enum 사용
from enum import Enum

class ModelName(str, Enum):
    alexnet = "alexnet"
    resnet = "resnet"
    lenet = "lenet"

@app.get("/models/{model_name}")
async def get_model(model_name: ModelName):
    return {"model": model_name}
```

### 쿼리 매개변수 (Query Parameters)

```python
from fastapi import Query
from typing import Optional, List

# 기본 쿼리 매개변수
@app.get("/items/")
async def read_items(skip: int = 0, limit: int = 10):
    return {"skip": skip, "limit": limit}

# Optional 쿼리 매개변수
@app.get("/items/")
async def read_items(
    q: Optional[str] = None,
    skip: int = 0,
    limit: int = Query(default=10, le=100)
):
    return {"q": q, "skip": skip, "limit": limit}

# 필수 쿼리 매개변수
@app.get("/items/")
async def read_items(
    q: str = Query(..., min_length=3, max_length=50)
):
    return {"q": q}

# 리스트 쿼리 매개변수
@app.get("/items/")
async def read_items(
    q: List[str] = Query(default=[])
):
    # /items/?q=foo&q=bar
    return {"q": q}

# 별칭 사용
@app.get("/items/")
async def read_items(
    item_query: str = Query(None, alias="item-query")
):
    return {"item_query": item_query}
```

## 요청 바디 (Request Body)

### Pydantic 모델

```python
from pydantic import BaseModel, Field, validator
from typing import Optional, List
from datetime import datetime

# 기본 모델
class Item(BaseModel):
    name: str
    price: float
    is_offer: Optional[bool] = None

# Field 검증
class User(BaseModel):
    username: str = Field(..., min_length=3, max_length=50)
    email: str = Field(..., regex="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$")
    age: int = Field(..., ge=0, le=120)
    full_name: Optional[str] = Field(None, max_length=100)

    # 커스텀 검증
    @validator('username')
    def username_alphanumeric(cls, v):
        assert v.isalnum(), 'must be alphanumeric'
        return v

    # Config 클래스
    class Config:
        schema_extra = {
            "example": {
                "username": "johndoe",
                "email": "john@example.com",
                "age": 30,
                "full_name": "John Doe"
            }
        }

# 중첩 모델
class Image(BaseModel):
    url: str
    name: str

class Product(BaseModel):
    name: str
    price: float
    tags: List[str] = []
    images: List[Image] = []

# 요청 바디 사용
@app.post("/items/")
async def create_item(item: Item):
    return item

# 여러 바디 매개변수
@app.put("/items/{item_id}")
async def update_item(
    item_id: int,
    item: Item,
    user: User,
    extra: dict  # 추가 JSON 데이터
):
    return {"item_id": item_id, "item": item, "user": user, "extra": extra}

# Body 파라미터
from fastapi import Body

@app.post("/items/")
async def create_item(
    item: Item = Body(
        ...,
        example={
            "name": "Foo",
            "price": 35.4,
            "is_offer": True
        }
    )
):
    return item
```

## 응답 모델 (Response Model)

```python
from typing import List, Optional

class UserIn(BaseModel):
    username: str
    password: str
    email: str

class UserOut(BaseModel):
    username: str
    email: str

# response_model 사용
@app.post("/users/", response_model=UserOut)
async def create_user(user: UserIn):
    return user  # password는 자동으로 제외됨

# response_model 옵션들
@app.get(
    "/items/",
    response_model=List[Item],
    response_model_exclude_unset=True,  # 설정되지 않은 값 제외
    response_model_exclude_defaults=True,  # 기본값 제외
    response_model_exclude_none=True,  # None 값 제외
    response_model_exclude={"password"},  # 특정 필드 제외
    response_model_include={"username", "email"}  # 특정 필드만 포함
)
async def read_items():
    return items

# Union 응답
from typing import Union

@app.get("/items/{item_id}", response_model=Union[Item, None])
async def read_item(item_id: str):
    if item_id in items:
        return items[item_id]
    return None
```

## 폼과 파일 (Forms and Files)

```python
from fastapi import Form, File, UploadFile
from typing import List

# 폼 데이터
@app.post("/login/")
async def login(
    username: str = Form(...),
    password: str = Form(...)
):
    return {"username": username}

# 파일 업로드
@app.post("/uploadfile/")
async def upload_file(
    file: bytes = File(...),  # 작은 파일
    fileb: UploadFile = File(...)  # 큰 파일
):
    return {
        "file_size": len(file),
        "fileb_content_type": fileb.content_type,
        "fileb_filename": fileb.filename
    }

# 여러 파일 업로드
@app.post("/uploadfiles/")
async def upload_files(
    files: List[UploadFile] = File(...)
):
    return {"filenames": [file.filename for file in files]}

# 폼과 파일 함께
@app.post("/create/")
async def create_file(
    file: UploadFile = File(...),
    notes: str = Form(...)
):
    return {
        "filename": file.filename,
        "notes": notes
    }

# 파일 저장
@app.post("/save/")
async def save_file(file: UploadFile = File(...)):
    contents = await file.read()
    with open(f"./uploads/{file.filename}", "wb") as f:
        f.write(contents)
    return {"filename": file.filename}
```

## 의존성 주입 (Dependency Injection)

```python
from fastapi import Depends, HTTPException, status
from typing import Optional

# 간단한 의존성
async def common_parameters(
    q: Optional[str] = None,
    skip: int = 0,
    limit: int = 100
):
    return {"q": q, "skip": skip, "limit": limit}

@app.get("/items/")
async def read_items(commons: dict = Depends(common_parameters)):
    return commons

# 클래스 의존성
class CommonQueryParams:
    def __init__(
        self,
        q: Optional[str] = None,
        skip: int = 0,
        limit: int = 100
    ):
        self.q = q
        self.skip = skip
        self.limit = limit

@app.get("/items/")
async def read_items(commons: CommonQueryParams = Depends()):
    return commons

# 중첩 의존성
def query_extractor(q: Optional[str] = None):
    return q

def query_or_body_extractor(
    q: str = Depends(query_extractor),
    body: Optional[dict] = None
):
    if q:
        return q
    return body

@app.post("/items/")
async def create_item(
    query_or_body: str = Depends(query_or_body_extractor)
):
    return {"query_or_body": query_or_body}

# 경로 수준 의존성
async def verify_token(x_token: str = Header(...)):
    if x_token != "secret-token":
        raise HTTPException(status_code=400, detail="Invalid token")

@app.get("/items/", dependencies=[Depends(verify_token)])
async def read_items():
    return [{"item": "Foo"}]

# 전역 의존성
app = FastAPI(dependencies=[Depends(verify_token)])
```

## 보안과 인증 (Security & Authentication)

### OAuth2 with JWT

```python
from fastapi.security import OAuth2PasswordBearer, OAuth2PasswordRequestForm
from jose import JWTError, jwt
from passlib.context import CryptContext
from datetime import datetime, timedelta

# 설정
SECRET_KEY = "your-secret-key"
ALGORITHM = "HS256"
ACCESS_TOKEN_EXPIRE_MINUTES = 30

# 패스워드 해싱
pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")

# OAuth2 스키마
oauth2_scheme = OAuth2PasswordBearer(tokenUrl="token")

# 유틸리티 함수
def verify_password(plain_password, hashed_password):
    return pwd_context.verify(plain_password, hashed_password)

def get_password_hash(password):
    return pwd_context.hash(password)

def create_access_token(data: dict):
    to_encode = data.copy()
    expire = datetime.utcnow() + timedelta(minutes=ACCESS_TOKEN_EXPIRE_MINUTES)
    to_encode.update({"exp": expire})
    encoded_jwt = jwt.encode(to_encode, SECRET_KEY, algorithm=ALGORITHM)
    return encoded_jwt

# 현재 사용자 가져오기
async def get_current_user(token: str = Depends(oauth2_scheme)):
    credentials_exception = HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED,
        detail="Could not validate credentials",
        headers={"WWW-Authenticate": "Bearer"},
    )
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms=[ALGORITHM])
        username: str = payload.get("sub")
        if username is None:
            raise credentials_exception
    except JWTError:
        raise credentials_exception
    return username

# 로그인 엔드포인트
@app.post("/token")
async def login(form_data: OAuth2PasswordRequestForm = Depends()):
    # 사용자 확인 로직
    if not verify_password(form_data.password, hashed_password):
        raise HTTPException(status_code=400, detail="Incorrect password")
    
    access_token = create_access_token(data={"sub": form_data.username})
    return {"access_token": access_token, "token_type": "bearer"}

# 보호된 라우트
@app.get("/users/me")
async def read_users_me(current_user: str = Depends(get_current_user)):
    return {"username": current_user}

# API 키 인증
from fastapi.security import APIKeyHeader

api_key_header = APIKeyHeader(name="X-API-Key")

@app.get("/items/")
async def read_items(api_key: str = Depends(api_key_header)):
    if api_key != "secret-api-key":
        raise HTTPException(status_code=403, detail="Invalid API Key")
    return {"items": []}
```

## 데이터베이스 연동 (Database Integration)

### SQLAlchemy ORM

```python
from sqlalchemy import create_engine, Column, Integer, String, Float, Boolean
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, Session

# 데이터베이스 설정
SQLALCHEMY_DATABASE_URL = "sqlite:///./sql_app.db"
# PostgreSQL: "postgresql://user:password@localhost/dbname"
# MySQL: "mysql://user:password@localhost/dbname"

engine = create_engine(
    SQLALCHEMY_DATABASE_URL,
    connect_args={"check_same_thread": False}  # SQLite only
)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()

# 모델 정의
class ItemDB(Base):
    __tablename__ = "items"
    
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True)
    price = Column(Float)
    is_offer = Column(Boolean, default=False)

# 테이블 생성
Base.metadata.create_all(bind=engine)

# 의존성
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# CRUD 작업
@app.post("/items/")
async def create_item(item: Item, db: Session = Depends(get_db)):
    db_item = ItemDB(**item.dict())
    db.add(db_item)
    db.commit()
    db.refresh(db_item)
    return db_item

@app.get("/items/{item_id}")
async def read_item(item_id: int, db: Session = Depends(get_db)):
    item = db.query(ItemDB).filter(ItemDB.id == item_id).first()
    if not item:
        raise HTTPException(status_code=404, detail="Item not found")
    return item

@app.get("/items/")
async def read_items(
    skip: int = 0,
    limit: int = 10,
    db: Session = Depends(get_db)
):
    items = db.query(ItemDB).offset(skip).limit(limit).all()
    return items

@app.put("/items/{item_id}")
async def update_item(
    item_id: int,
    item: Item,
    db: Session = Depends(get_db)
):
    db_item = db.query(ItemDB).filter(ItemDB.id == item_id).first()
    if not db_item:
        raise HTTPException(status_code=404, detail="Item not found")
    
    for key, value in item.dict().items():
        setattr(db_item, key, value)
    
    db.commit()
    db.refresh(db_item)
    return db_item

@app.delete("/items/{item_id}")
async def delete_item(item_id: int, db: Session = Depends(get_db)):
    db_item = db.query(ItemDB).filter(ItemDB.id == item_id).first()
    if not db_item:
        raise HTTPException(status_code=404, detail="Item not found")
    
    db.delete(db_item)
    db.commit()
    return {"message": "Item deleted"}
```

### 비동기 데이터베이스 (Async Database)

```python
import databases
import sqlalchemy

DATABASE_URL = "postgresql://user:password@localhost/dbname"

database = databases.Database(DATABASE_URL)
metadata = sqlalchemy.MetaData()

# 테이블 정의
items = sqlalchemy.Table(
    "items",
    metadata,
    sqlalchemy.Column("id", sqlalchemy.Integer, primary_key=True),
    sqlalchemy.Column("name", sqlalchemy.String),
    sqlalchemy.Column("price", sqlalchemy.Float),
)

# 앱 시작/종료 이벤트
@app.on_event("startup")
async def startup():
    await database.connect()

@app.on_event("shutdown")
async def shutdown():
    await database.disconnect()

# 비동기 CRUD
@app.post("/items/")
async def create_item(item: Item):
    query = items.insert().values(**item.dict())
    last_record_id = await database.execute(query)
    return {"id": last_record_id, **item.dict()}

@app.get("/items/")
async def read_items():
    query = items.select()
    return await database.fetch_all(query)
```

## 미들웨어 (Middleware)

```python
from fastapi.middleware.cors import CORSMiddleware
from fastapi.middleware.trustedhost import TrustedHostMiddleware
from fastapi.middleware.gzip import GZipMiddleware
import time

# CORS 미들웨어
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # 프로덕션에서는 특정 도메인 지정
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Trusted Host 미들웨어
app.add_middleware(
    TrustedHostMiddleware,
    allowed_hosts=["example.com", "*.example.com"]
)

# GZip 압축
app.add_middleware(GZipMiddleware, minimum_size=1000)

# 커스텀 미들웨어
@app.middleware("http")
async def add_process_time_header(request, call_next):
    start_time = time.time()
    response = await call_next(request)
    process_time = time.time() - start_time
    response.headers["X-Process-Time"] = str(process_time)
    return response

# 요청 로깅 미들웨어
@app.middleware("http")
async def log_requests(request, call_next):
    print(f"Request: {request.method} {request.url}")
    response = await call_next(request)
    print(f"Response: {response.status_code}")
    return response
```

## 백그라운드 태스크 (Background Tasks)

```python
from fastapi import BackgroundTasks
import time

# 백그라운드 함수
def write_log(message: str):
    time.sleep(5)  # 시간이 걸리는 작업
    with open("log.txt", "a") as f:
        f.write(f"{message}\n")

def send_email(email: str, message: str):
    time.sleep(10)  # 이메일 전송 시뮬레이션
    print(f"Email sent to {email}: {message}")

# 백그라운드 태스크 사용
@app.post("/send-notification/")
async def send_notification(
    email: str,
    background_tasks: BackgroundTasks
):
    background_tasks.add_task(write_log, f"Notification sent to {email}")
    background_tasks.add_task(send_email, email, "Notification message")
    return {"message": "Notification sent"}

# 의존성에서 백그라운드 태스크
def write_notification(
    email: str,
    message: str = "",
    background_tasks: BackgroundTasks = None
):
    if background_tasks:
        background_tasks.add_task(write_log, f"Notification: {email}")
    return {"email": email, "message": message}

@app.post("/send/")
async def send(
    notification: dict = Depends(write_notification)
):
    return notification
```

## WebSocket

```python
from fastapi import WebSocket, WebSocketDisconnect
from typing import List

# 연결 매니저
class ConnectionManager:
    def __init__(self):
        self.active_connections: List[WebSocket] = []

    async def connect(self, websocket: WebSocket):
        await websocket.accept()
        self.active_connections.append(websocket)

    def disconnect(self, websocket: WebSocket):
        self.active_connections.remove(websocket)

    async def send_personal_message(self, message: str, websocket: WebSocket):
        await websocket.send_text(message)

    async def broadcast(self, message: str):
        for connection in self.active_connections:
            await connection.send_text(message)

manager = ConnectionManager()

# WebSocket 엔드포인트
@app.websocket("/ws/{client_id}")
async def websocket_endpoint(websocket: WebSocket, client_id: int):
    await manager.connect(websocket)
    try:
        while True:
            data = await websocket.receive_text()
            await manager.send_personal_message(f"You: {data}", websocket)
            await manager.broadcast(f"Client {client_id}: {data}")
    except WebSocketDisconnect:
        manager.disconnect(websocket)
        await manager.broadcast(f"Client {client_id} left")
```

## 테스팅 (Testing)

```python
from fastapi.testclient import TestClient
import pytest

client = TestClient(app)

# 기본 테스트
def test_read_main():
    response = client.get("/")
    assert response.status_code == 200
    assert response.json() == {"message": "Hello World"}

# 파라미터 테스트
def test_read_item():
    response = client.get("/items/1")
    assert response.status_code == 200
    assert response.json() == {"item_id": 1}

# POST 테스트
def test_create_item():
    response = client.post(
        "/items/",
        json={"name": "Test", "price": 10.5}
    )
    assert response.status_code == 200
    assert response.json()["name"] == "Test"

# 인증 테스트
def test_auth():
    response = client.post(
        "/token",
        data={"username": "test", "password": "test"}
    )
    token = response.json()["access_token"]
    
    response = client.get(
        "/users/me",
        headers={"Authorization": f"Bearer {token}"}
    )
    assert response.status_code == 200

# 비동기 테스트
@pytest.mark.asyncio
async def test_async_endpoint():
    async with AsyncClient(app=app, base_url="http://test") as ac:
        response = await ac.get("/")
    assert response.status_code == 200

# WebSocket 테스트
def test_websocket():
    with client.websocket_connect("/ws/123") as websocket:
        websocket.send_text("Hello")
        data = websocket.receive_text()
        assert data == "You: Hello"
```

## 고급 기능 (Advanced Features)

### 라우터 (Routers)

```python
from fastapi import APIRouter

# 라우터 생성
router = APIRouter(
    prefix="/items",
    tags=["items"],
    dependencies=[Depends(verify_token)],
    responses={404: {"description": "Not found"}},
)

@router.get("/")
async def read_items():
    return [{"item": "Foo"}]

@router.get("/{item_id}")
async def read_item(item_id: int):
    return {"item_id": item_id}

# 메인 앱에 포함
app.include_router(router)
app.include_router(
    admin_router,
    prefix="/admin",
    tags=["admin"],
    dependencies=[Depends(verify_admin)],
)
```

### 이벤트 핸들러

```python
# 시작 이벤트
@app.on_event("startup")
async def startup_event():
    print("Application starting...")
    # 데이터베이스 연결 등

# 종료 이벤트
@app.on_event("shutdown")
async def shutdown_event():
    print("Application shutting down...")
    # 데이터베이스 연결 종료 등

# Lifespan 컨텍스트 매니저 (권장)
from contextlib import asynccontextmanager

@asynccontextmanager
async def lifespan(app: FastAPI):
    # Startup
    print("Starting up...")
    yield
    # Shutdown
    print("Shutting down...")

app = FastAPI(lifespan=lifespan)
```

### 에러 핸들러

```python
from fastapi import Request
from fastapi.responses import JSONResponse

# HTTP 예외 핸들러
@app.exception_handler(HTTPException)
async def http_exception_handler(request: Request, exc: HTTPException):
    return JSONResponse(
        status_code=exc.status_code,
        content={"detail": exc.detail, "custom": "error"},
    )

# 커스텀 예외
class CustomException(Exception):
    def __init__(self, name: str):
        self.name = name

@app.exception_handler(CustomException)
async def custom_exception_handler(request: Request, exc: CustomException):
    return JSONResponse(
        status_code=418,
        content={"message": f"Error: {exc.name}"},
    )

# 전역 예외 핸들러
@app.exception_handler(Exception)
async def global_exception_handler(request: Request, exc: Exception):
    return JSONResponse(
        status_code=500,
        content={"message": "Internal server error"},
    )
```

### 정적 파일과 템플릿

```python
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates
from fastapi.responses import HTMLResponse

# 정적 파일
app.mount("/static", StaticFiles(directory="static"), name="static")

# 템플릿
templates = Jinja2Templates(directory="templates")

@app.get("/", response_class=HTMLResponse)
async def read_root(request: Request):
    return templates.TemplateResponse(
        "index.html",
        {"request": request, "title": "FastAPI"}
    )
```

## 성능 최적화 팁

```python
# 1. 비동기 함수 사용
@app.get("/async")
async def async_endpoint():
    # I/O 작업에 유리
    return {"message": "async"}

# 2. 응답 캐싱
from fastapi.responses import Response

@app.get("/cached")
async def get_cached(response: Response):
    response.headers["Cache-Control"] = "public, max-age=3600"
    return {"data": "cached"}

# 3. 데이터베이스 연결 풀
engine = create_engine(
    SQLALCHEMY_DATABASE_URL,
    pool_size=20,
    max_overflow=40,
    pool_pre_ping=True,
)

# 4. Pydantic 모델 최적화
class OptimizedModel(BaseModel):
    class Config:
        # Python 3.10+ 최적화
        arbitrary_types_allowed = True
        # 검증 최소화
        validate_assignment = False

# 5. 응답 압축 (GZip)
app.add_middleware(GZipMiddleware, minimum_size=1000)

# 6. 비동기 작업 사용
import asyncio

@app.get("/concurrent")
async def concurrent_tasks():
    tasks = [async_task1(), async_task2(), async_task3()]
    results = await asyncio.gather(*tasks)
    return results
```

## 배포 (Deployment)

```bash
# Production 서버 실행
uvicorn main:app --host 0.0.0.0 --port 80 --workers 4

# Gunicorn with Uvicorn
gunicorn main:app -k uvicorn.workers.UvicornWorker --workers 4

# Docker 배포
# Dockerfile
FROM python:3.9
WORKDIR /code
COPY ./requirements.txt /code/requirements.txt
RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt
COPY ./app /code/app
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "80"]

# docker-compose.yml
version: "3.9"
services:
  web:
    build: .
    ports:
      - "80:80"
    environment:
      - DATABASE_URL=postgresql://user:password@db/dbname
  db:
    image: postgres:13
    environment:
      - POSTGRES_PASSWORD=password
      - POSTGRES_DB=dbname

# 환경 변수 관리
from pydantic import BaseSettings

class Settings(BaseSettings):
    app_name: str = "FastAPI App"
    admin_email: str
    database_url: str
    secret_key: str
    
    class Config:
        env_file = ".env"

settings = Settings()
```

## API 문서화 (API Documentation)

```python
from fastapi import FastAPI
from pydantic import BaseModel, Field

# 앱 수준 문서화
app = FastAPI(
    title="My API",
    description="""
    ## Features
    * **Users**: User registration and authentication
    * **Items**: CRUD operations for items
    """,
    version="1.0.0",
    terms_of_service="http://example.com/terms/",
    contact={
        "name": "API Support",
        "email": "api@example.com",
    },
    license_info={
        "name": "Apache 2.0",
        "url": "https://www.apache.org/licenses/LICENSE-2.0.html",
    },
)

# 태그 메타데이터
tags_metadata = [
    {
        "name": "users",
        "description": "Operations with users",
        "externalDocs": {
            "description": "External docs",
            "url": "https://example.com/",
        },
    },
    {
        "name": "items",
        "description": "Manage items",
    },
]

app = FastAPI(openapi_tags=tags_metadata)

# 엔드포인트 문서화
@app.post(
    "/items/",
    response_model=Item,
    summary="Create an item",
    description="Create an item with all the information",
    response_description="The created item",
    tags=["items"],
    deprecated=False,
)
async def create_item(item: Item):
    """
    Create an item with all the information:
    
    - **name**: each item must have a name
    - **price**: price in USD
    - **tax**: if the item has tax, include it
    """
    return item

# 응답 예시
class Item(BaseModel):
    name: str = Field(..., example="Laptop")
    price: float = Field(..., example=999.99)
    tax: Optional[float] = Field(None, example=99.99)
    
    class Config:
        schema_extra = {
            "example": {
                "name": "MacBook Pro",
                "price": 2399.99,
                "tax": 239.99
            }
        }

# 다중 응답 문서화
@app.get(
    "/items/{item_id}",
    responses={
        200: {
            "description": "Success",
            "content": {
                "application/json": {
                    "example": {"name": "Item", "price": 10.5}
                }
            },
        },
        404: {"description": "Item not found"},
        403: {"description": "Not authorized"},
    },
)
async def read_item(item_id: int):
    return {"item_id": item_id}
```

## 유용한 유틸리티 (Useful Utilities)

### 페이지네이션 (Pagination)

```python
from typing import Generic, TypeVar, List
from pydantic.generics import GenericModel

T = TypeVar('T')

class PaginationParams(BaseModel):
    skip: int = Query(0, ge=0)
    limit: int = Query(10, ge=1, le=100)

class PagedResponse(GenericModel, Generic[T]):
    total: int
    items: List[T]
    skip: int
    limit: int

@app.get("/items/", response_model=PagedResponse[Item])
async def read_items(
    pagination: PaginationParams = Depends(),
    db: Session = Depends(get_db)
):
    total = db.query(ItemDB).count()
    items = db.query(ItemDB).offset(pagination.skip).limit(pagination.limit).all()
    
    return PagedResponse(
        total=total,
        items=items,
        skip=pagination.skip,
        limit=pagination.limit
    )
```

### 레이트 리미팅 (Rate Limiting)

```python
from fastapi import Request
from fastapi.responses import JSONResponse
import time
from collections import defaultdict

# 간단한 메모리 기반 레이트 리미터
class RateLimiter:
    def __init__(self, calls: int = 10, period: int = 60):
        self.calls = calls
        self.period = period
        self.clients = defaultdict(list)
    
    def __call__(self, request: Request):
        client_id = request.client.host
        now = time.time()
        
        # 오래된 요청 제거
        self.clients[client_id] = [
            req_time for req_time in self.clients[client_id]
            if req_time > now - self.period
        ]
        
        if len(self.clients[client_id]) >= self.calls:
            raise HTTPException(
                status_code=429,
                detail="Too many requests"
            )
        
        self.clients[client_id].append(now)

# 사용
rate_limiter = RateLimiter(calls=10, period=60)

@app.get("/limited/")
async def limited_endpoint(rate_limit: None = Depends(rate_limiter)):
    return {"message": "Success"}
```

### 헬스 체크 (Health Check)

```python
from enum import Enum

class HealthStatus(str, Enum):
    healthy = "healthy"
    unhealthy = "unhealthy"
    degraded = "degraded"

@app.get("/health")
async def health_check(db: Session = Depends(get_db)):
    checks = {
        "database": HealthStatus.healthy,
        "cache": HealthStatus.healthy,
        "external_api": HealthStatus.healthy
    }
    
    # 데이터베이스 체크
    try:
        db.execute("SELECT 1")
        checks["database"] = HealthStatus.healthy
    except:
        checks["database"] = HealthStatus.unhealthy
    
    # 전체 상태 결정
    if any(status == HealthStatus.unhealthy for status in checks.values()):
        overall_status = HealthStatus.unhealthy
        status_code = 503
    elif any(status == HealthStatus.degraded for status in checks.values()):
        overall_status = HealthStatus.degraded
        status_code = 200
    else:
        overall_status = HealthStatus.healthy
        status_code = 200
    
    return JSONResponse(
        status_code=status_code,
        content={
            "status": overall_status,
            "checks": checks,
            "timestamp": datetime.utcnow().isoformat()
        }
    )
```

### 로깅 (Logging)

```python
import logging
from fastapi import Request
import uuid
import time

# 로거 설정
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)

# 요청 ID 미들웨어
@app.middleware("http")
async def add_request_id(request: Request, call_next):
    request_id = str(uuid.uuid4())
    request.state.request_id = request_id
    
    start_time = time.time()
    response = await call_next(request)
    process_time = time.time() - start_time
    
    logger.info(
        f"Request ID: {request_id} | "
        f"Path: {request.url.path} | "
        f"Method: {request.method} | "
        f"Status: {response.status_code} | "
        f"Duration: {process_time:.3f}s"
    )
    
    response.headers["X-Request-ID"] = request_id
    return response

# 구조화된 로깅
@app.post("/items/")
async def create_item(item: Item, request: Request):
    logger.info(
        "Creating item",
        extra={
            "request_id": request.state.request_id,
            "item_name": item.name,
            "item_price": item.price
        }
    )
    return item
```

### 비동기 작업 큐 (Celery Integration)

```python
from celery import Celery

# Celery 설정
celery_app = Celery(
    "tasks",
    broker="redis://localhost:6379",
    backend="redis://localhost:6379"
)

# Celery 태스크
@celery_app.task
def process_item(item_id: int):
    # 시간이 오래 걸리는 작업
    time.sleep(10)
    return f"Processed item {item_id}"

# FastAPI 엔드포인트
@app.post("/process/{item_id}")
async def process(item_id: int):
    task = process_item.delay(item_id)
    return {"task_id": task.id}

@app.get("/task/{task_id}")
async def get_task_result(task_id: str):
    task = process_item.AsyncResult(task_id)
    if task.ready():
        return {"status": "completed", "result": task.result}
    return {"status": "pending"}
```

## 모범 사례 (Best Practices)

### 프로젝트 구조

```
project/
├── app/
│   ├── __init__.py
│   ├── main.py           # FastAPI 앱
│   ├── dependencies.py   # 의존성
│   ├── config.py        # 설정
│   ├── models/          # Pydantic 모델
│   │   ├── __init__.py
│   │   ├── user.py
│   │   └── item.py
│   ├── schemas/         # 데이터베이스 스키마
│   │   ├── __init__.py
│   │   └── database.py
│   ├── routers/         # API 라우터
│   │   ├── __init__.py
│   │   ├── users.py
│   │   └── items.py
│   ├── services/        # 비즈니스 로직
│   │   ├── __init__.py
│   │   └── auth.py
│   ├── utils/           # 유틸리티
│   │   ├── __init__.py
│   │   └── security.py
│   └── tests/           # 테스트
│       ├── __init__.py
│       ├── test_users.py
│       └── test_items.py
├── requirements.txt
├── .env
├── .gitignore
└── README.md
```

### 보안 체크리스트

```python
# 1. HTTPS 사용 (프로덕션)
# 2. CORS 설정 제한
app.add_middleware(
    CORSMiddleware,
    allow_origins=["https://example.com"],  # 특정 도메인만
    allow_credentials=True,
    allow_methods=["GET", "POST"],
    allow_headers=["*"],
)

# 3. SQL Injection 방지 (ORM 사용)
# 4. 입력 검증 (Pydantic)
# 5. 인증/인가 구현
# 6. Rate Limiting
# 7. 환경 변수로 민감 정보 관리
# 8. 에러 메시지에 민감 정보 노출 방지
# 9. 의존성 정기 업데이트
# 10. 로깅 및 모니터링
```

### 성능 체크리스트

- ✅ 비동기 함수 사용 (I/O 작업)
- ✅ 데이터베이스 연결 풀링
- ✅ 캐싱 구현 (Redis)
- ✅ 페이지네이션
- ✅ N+1 쿼리 문제 해결
- ✅ 인덱스 최적화
- ✅ CDN 사용 (정적 파일)
- ✅ 응답 압축 (GZip)
- ✅ 백그라운드 작업 사용
- ✅ 로드 밸런싱

## 자주 사용하는 명령어

```bash
# FastAPI 프로젝트 시작
pip install fastapi uvicorn[standard]

# 개발 서버 실행
uvicorn main:app --reload

# 프로덕션 서버 실행
uvicorn main:app --host 0.0.0.0 --port 80 --workers 4

# 요구사항 파일 생성
pip freeze > requirements.txt

# 테스트 실행
pytest

# 코드 포맷팅
black .
isort .

# 타입 체크
mypy app/

# API 문서 접속
# Swagger UI: http://localhost:8000/docs
# ReDoc: http://localhost:8000/redoc
# OpenAPI JSON: http://localhost:8000/openapi.json
```