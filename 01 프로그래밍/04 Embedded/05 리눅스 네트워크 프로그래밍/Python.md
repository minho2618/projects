#Python
# Python Cheat Sheet

## 기본 문법 (Basic Syntax)

### 변수와 자료형 (Variables and Data Types)

```python
# 변수 선언
x = 5                  # int
y = 3.14              # float
name = "Python"       # str
is_valid = True       # bool
nothing = None        # NoneType

# 타입 확인
type(x)               # <class 'int'>

# 타입 변환
int("123")            # 123
float("3.14")         # 3.14
str(100)              # "100"
bool(1)               # True
```

### 문자열 (Strings)

```python
# 문자열 생성
s1 = 'single quotes'
s2 = "double quotes"
s3 = """multi
line"""

# 문자열 연산
"Hello" + " World"    # "Hello World"
"Ha" * 3              # "HaHaHa"

# 문자열 메서드
s = "Hello World"
s.upper()             # "HELLO WORLD"
s.lower()             # "hello world"
s.strip()             # 공백 제거
s.replace("World", "Python")  # "Hello Python"
s.split()             # ['Hello', 'World']
" ".join(['a','b'])   # "a b"

# 문자열 포매팅
name = "Alice"
age = 30
f"Name: {name}, Age: {age}"     # f-string
"Name: {}, Age: {}".format(name, age)
"Name: %s, Age: %d" % (name, age)

# 문자열 인덱싱/슬라이싱
s = "Python"
s[0]                  # 'P'
s[-1]                 # 'n'
s[1:4]                # 'yth'
s[:3]                 # 'Pyt'
s[3:]                 # 'hon'
s[::2]                # 'Pto'
s[::-1]               # 'nohtyP' (역순)
```

### 리스트 (Lists)

```python
# 리스트 생성
lst = [1, 2, 3, 4, 5]
mixed = [1, "two", 3.0, True]

# 리스트 메서드
lst.append(6)         # 끝에 추가
lst.insert(0, 0)      # 특정 위치에 삽입
lst.extend([7, 8])    # 여러 요소 추가
lst.remove(3)         # 값으로 제거
lst.pop()             # 마지막 요소 제거 및 반환
lst.pop(0)            # 인덱스로 제거
lst.clear()           # 모든 요소 제거
lst.index(2)          # 값의 인덱스 찾기
lst.count(1)          # 값의 개수
lst.sort()            # 정렬
lst.reverse()         # 역순

# 리스트 컴프리헨션
[x**2 for x in range(5)]           # [0, 1, 4, 9, 16]
[x for x in range(10) if x % 2 == 0]  # [0, 2, 4, 6, 8]
```

### 튜플 (Tuples)

```python
# 불변(immutable) 시퀀스
t = (1, 2, 3)
t = 1, 2, 3           # 괄호 생략 가능
single = (1,)         # 단일 요소 튜플

# 언패킹
a, b, c = (1, 2, 3)
a, *rest = (1, 2, 3, 4)  # a=1, rest=[2,3,4]
```

### 딕셔너리 (Dictionaries)

```python
# 딕셔너리 생성
d = {"name": "Alice", "age": 30}
d = dict(name="Alice", age=30)

# 딕셔너리 연산
d["name"]             # "Alice"
d.get("city", "N/A")  # 기본값 설정
d["city"] = "Seoul"   # 추가/수정
del d["age"]          # 삭제
"name" in d           # 키 존재 확인

# 딕셔너리 메서드
d.keys()              # 모든 키
d.values()            # 모든 값
d.items()             # 모든 키-값 쌍
d.update({"age": 31}) # 업데이트
d.pop("age")          # 제거 및 반환
d.clear()             # 모든 항목 제거

# 딕셔너리 컴프리헨션
{x: x**2 for x in range(5)}  # {0:0, 1:1, 2:4, 3:9, 4:16}
```

### 집합 (Sets)

```python
# 집합 생성
s = {1, 2, 3}
s = set([1, 2, 2, 3])  # {1, 2, 3} - 중복 제거

# 집합 연산
s.add(4)              # 요소 추가
s.remove(2)           # 요소 제거 (없으면 에러)
s.discard(5)          # 요소 제거 (없어도 OK)
s1 | s2               # 합집합
s1 & s2               # 교집합
s1 - s2               # 차집합
s1 ^ s2               # 대칭차집합
```

## 제어문 (Control Flow)

### 조건문 (Conditionals)

```python
# if-elif-else
if x > 0:
    print("positive")
elif x < 0:
    print("negative")
else:
    print("zero")

# 삼항 연산자
result = "even" if x % 2 == 0 else "odd"

# match-case (Python 3.10+)
match value:
    case 1:
        print("one")
    case 2 | 3:
        print("two or three")
    case _:
        print("other")
```

### 반복문 (Loops)

```python
# for 루프
for i in range(5):         # 0부터 4까지
    print(i)

for i in range(2, 10, 2):  # 2부터 8까지 2씩 증가
    print(i)

for index, value in enumerate(['a', 'b', 'c']):
    print(f"{index}: {value}")

for x, y in zip([1, 2], ['a', 'b']):
    print(f"{x}: {y}")

# while 루프
while x < 10:
    x += 1
    if x == 5:
        continue    # 다음 반복으로
    if x == 8:
        break       # 루프 종료
    print(x)

# else 절 (break 없이 종료시 실행)
for i in range(3):
    print(i)
else:
    print("completed")
```

## 함수 (Functions)

### 함수 정의

```python
# 기본 함수
def greet(name):
    return f"Hello, {name}"

# 기본 매개변수
def greet(name="World"):
    return f"Hello, {name}"

# 가변 인자
def sum_all(*args):
    return sum(args)

def print_info(**kwargs):
    for key, value in kwargs.items():
        print(f"{key}: {value}")

# 람다 함수
square = lambda x: x**2
add = lambda x, y: x + y

# 타입 힌트
def add(x: int, y: int) -> int:
    return x + y

# 독스트링
def complex_function(x):
    """
    이 함수의 설명
    
    Args:
        x: 매개변수 설명
    
    Returns:
        반환값 설명
    """
    return x * 2
```

### 내장 함수

```python
# 수학 함수
abs(-5)               # 5
round(3.7)            # 4
min([1, 2, 3])        # 1
max([1, 2, 3])        # 3
sum([1, 2, 3])        # 6

# 타입 변환
int(), float(), str(), bool()
list(), tuple(), set(), dict()

# 시퀀스 함수
len([1, 2, 3])        # 3
sorted([3, 1, 2])     # [1, 2, 3]
reversed([1, 2, 3])   # 역순 이터레이터
enumerate(['a', 'b']) # 인덱스와 값
zip([1, 2], ['a', 'b'])  # 병렬 순회

# 기타 유용한 함수
all([True, True])     # True (모두 참)
any([False, True])    # True (하나라도 참)
filter(lambda x: x > 0, [-1, 0, 1])  # [1]
map(lambda x: x**2, [1, 2, 3])       # [1, 4, 9]
```

## 클래스 (Classes)

```python
class Person:
    # 클래스 변수
    species = "Homo sapiens"
    
    # 생성자
    def __init__(self, name, age):
        self.name = name      # 인스턴스 변수
        self.age = age
        self._protected = 0   # 보호 변수 (관례)
        self.__private = 0    # 프라이빗 변수
    
    # 인스턴스 메서드
    def greet(self):
        return f"Hi, I'm {self.name}"
    
    # 클래스 메서드
    @classmethod
    def from_birth_year(cls, name, birth_year):
        age = 2024 - birth_year
        return cls(name, age)
    
    # 정적 메서드
    @staticmethod
    def is_adult(age):
        return age >= 18
    
    # 프로퍼티
    @property
    def description(self):
        return f"{self.name} is {self.age} years old"
    
    # 특수 메서드
    def __str__(self):
        return f"Person({self.name}, {self.age})"
    
    def __repr__(self):
        return f"Person('{self.name}', {self.age})"

# 상속
class Student(Person):
    def __init__(self, name, age, student_id):
        super().__init__(name, age)
        self.student_id = student_id
    
    def study(self):
        return f"{self.name} is studying"
```

## 예외 처리 (Exception Handling)

```python
# try-except
try:
    result = 10 / 0
except ZeroDivisionError:
    print("Cannot divide by zero")
except (ValueError, TypeError) as e:
    print(f"Error: {e}")
except Exception as e:
    print(f"Unexpected error: {e}")
else:
    print("No errors occurred")
finally:
    print("Always executed")

# 예외 발생
raise ValueError("Invalid value")

# 사용자 정의 예외
class CustomError(Exception):
    pass

# assert
assert x > 0, "x must be positive"
```

## 파일 처리 (File Handling)

```python
# 파일 읽기
with open('file.txt', 'r', encoding='utf-8') as f:
    content = f.read()        # 전체 읽기
    # 또는
    lines = f.readlines()     # 줄 단위 리스트
    # 또는
    for line in f:            # 줄 단위 순회
        print(line.strip())

# 파일 쓰기
with open('file.txt', 'w', encoding='utf-8') as f:
    f.write("Hello World\n")
    f.writelines(['line1\n', 'line2\n'])

# 파일 추가
with open('file.txt', 'a', encoding='utf-8') as f:
    f.write("Appended text\n")

# JSON 처리
import json

# 저장
data = {"name": "Alice", "age": 30}
with open('data.json', 'w') as f:
    json.dump(data, f)

# 읽기
with open('data.json', 'r') as f:
    data = json.load(f)
```

## 모듈과 패키지 (Modules and Packages)

```python
# 모듈 임포트
import math
from math import sqrt, pi
from math import sqrt as square_root
from math import *

# 자신의 모듈 임포트
import mymodule
from mypackage import mymodule
from mypackage.mymodule import myfunction

# 모듈 경로
import sys
sys.path.append('/path/to/module')

# __name__ 확인
if __name__ == "__main__":
    # 직접 실행될 때만 실행
    main()
```

## 유용한 내장 모듈

```python
# os - 운영체제 인터페이스
import os
os.getcwd()              # 현재 디렉토리
os.listdir('.')          # 파일 목록
os.path.join('dir', 'file.txt')  # 경로 결합
os.path.exists('file.txt')       # 파일 존재 확인

# datetime - 날짜와 시간
from datetime import datetime, timedelta
now = datetime.now()
today = datetime.today()
custom = datetime(2024, 1, 1)
future = now + timedelta(days=7)

# random - 난수 생성
import random
random.random()          # 0-1 사이 실수
random.randint(1, 10)    # 1-10 정수
random.choice([1, 2, 3]) # 랜덤 선택
random.shuffle(lst)      # 리스트 섞기

# re - 정규표현식
import re
pattern = r'\d+'         # 숫자 패턴
re.findall(pattern, text)  # 모든 매치 찾기
re.search(pattern, text)   # 첫 매치 찾기
re.sub(pattern, 'X', text) # 치환

# collections - 고급 자료구조
from collections import Counter, defaultdict, deque
Counter([1, 1, 2, 3])    # {1: 2, 2: 1, 3: 1}
dd = defaultdict(list)   # 기본값이 있는 딕셔너리
dq = deque([1, 2, 3])    # 양방향 큐

# itertools - 반복자 도구
import itertools
itertools.combinations([1,2,3], 2)  # 조합
itertools.permutations([1,2,3], 2)  # 순열
itertools.product([1,2], ['a','b']) # 곱집합
```

## 데코레이터 (Decorators)

```python
# 함수 데코레이터
def timer(func):
    import time
    def wrapper(*args, **kwargs):
        start = time.time()
        result = func(*args, **kwargs)
        print(f"Execution time: {time.time() - start}")
        return result
    return wrapper

@timer
def slow_function():
    time.sleep(1)

# 클래스 데코레이터
@dataclass
class Point:
    x: float
    y: float
```

## 컨텍스트 매니저 (Context Managers)

```python
# with 문 사용
with open('file.txt') as f:
    content = f.read()

# 커스텀 컨텍스트 매니저
from contextlib import contextmanager

@contextmanager
def my_context():
    print("Enter")
    try:
        yield "resource"
    finally:
        print("Exit")

with my_context() as resource:
    print(resource)
```

## 제너레이터 (Generators)

```python
# 제너레이터 함수
def fibonacci(n):
    a, b = 0, 1
    for _ in range(n):
        yield a
        a, b = b, a + b

# 제너레이터 표현식
gen = (x**2 for x in range(10))

# 사용
for num in fibonacci(5):
    print(num)
```

## 유용한 팁

### 파이썬스러운 코드 (Pythonic Code)

```python
# 스왑
a, b = b, a

# 여러 변수 할당
x = y = z = 0

# 체이닝 비교
if 0 < x < 10:
    pass

# in 연산자 활용
if x in [1, 2, 3]:
    pass

# enumerate 사용
for i, value in enumerate(lst):
    print(f"{i}: {value}")

# zip 사용
for x, y in zip(list1, list2):
    print(x, y)

# 딕셔너리 get 메서드
value = d.get('key', 'default')

# 리스트 컴프리헨션
squares = [x**2 for x in range(10)]

# 조건부 표현식
result = "yes" if condition else "no"
```

### 성능 최적화 팁

```python
# 리스트 대신 제너레이터 사용 (메모리 효율)
sum(x**2 for x in range(1000000))

# join 사용 (문자열 연결)
''.join(['a', 'b', 'c'])  # 'abc'

# set/dict 활용 (검색 O(1))
if item in my_set:  # 빠름
    pass

# collections.deque 사용 (양끝 연산)
from collections import deque
dq = deque()
dq.appendleft(1)  # O(1)
dq.popleft()      # O(1)
```

## 주요 명령어 요약

|카테고리|명령어|설명|
|---|---|---|
|변수|`x = 5`|변수 할당|
|출력|`print()`|콘솔 출력|
|입력|`input()`|사용자 입력|
|타입|`type()`|타입 확인|
|길이|`len()`|길이 반환|
|범위|`range()`|숫자 시퀀스|
|조건|`if/elif/else`|조건문|
|반복|`for/while`|반복문|
|함수|`def`|함수 정의|
|클래스|`class`|클래스 정의|
|임포트|`import`|모듈 가져오기|
|예외|`try/except`|예외 처리|
|With|`with`|컨텍스트 관리|
|람다|`lambda`|익명 함수|
|Return|`return`|값 반환|
|Yield|`yield`|제너레이터|
|Pass|`pass`|빈 구문|
|Break|`break`|루프 종료|
|Continue|`continue`|다음 반복|