#Go
# Go 언어 치트 시트

## 기본 구조

```go
package main

import "fmt"

func main() {
    fmt.Println("Hello, World!")
}
```

## 변수 선언

```go
// 명시적 타입 선언
var name string = "Go"
var age int = 10

// 타입 추론
var name = "Go"
age := 10

// 여러 변수 동시 선언
var (
    name string = "Go"
    age  int    = 10
)

// 짧은 선언 (함수 내부에서만)
name, age := "Go", 10
```

## 기본 데이터 타입

### 숫자형

```go
// 정수
int, int8, int16, int32, int64
uint, uint8, uint16, uint32, uint64
byte (= uint8), rune (= int32)

// 부동소수점
float32, float64

// 복소수
complex64, complex128
```

### 기타

```go
bool        // true, false
string      // "문자열"
```

## 상수

```go
const Pi = 3.14
const (
    StatusOK = 200
    StatusNotFound = 404
)

// iota를 사용한 자동 증가
const (
    Sunday = iota    // 0
    Monday           // 1
    Tuesday          // 2
)
```

## 연산자

```go
// 산술 연산자
+, -, *, /, %

// 비교 연산자
==, !=, <, <=, >, >=

// 논리 연산자
&&, ||, !

// 비트 연산자
&, |, ^, &^, <<, >>

// 할당 연산자
=, +=, -=, *=, /=, %=, &=, |=, ^=, &^=, <<=, >>=

// 증감 연산자
++, --

// 포인터 연산자
&, *
```

## 제어문

### 조건문

```go
// if문
if x > 0 {
    fmt.Println("positive")
} else if x < 0 {
    fmt.Println("negative")
} else {
    fmt.Println("zero")
}

// if with initialization
if err := someFunction(); err != nil {
    return err
}

// switch문
switch day {
case "Monday":
    fmt.Println("월요일")
case "Tuesday", "Wednesday":
    fmt.Println("화요일 또는 수요일")
default:
    fmt.Println("기타")
}

// switch without expression
switch {
case x > 0:
    fmt.Println("positive")
case x < 0:
    fmt.Println("negative")
default:
    fmt.Println("zero")
}
```

### 반복문

```go
// for문 (3가지 형태)
for i := 0; i < 10; i++ {
    fmt.Println(i)
}

// while과 같은 형태
for condition {
    // code
}

// 무한루프
for {
    // code
    break
}

// range를 사용한 반복
for index, value := range slice {
    fmt.Println(index, value)
}

for key, value := range map {
    fmt.Println(key, value)
}

// 인덱스만 필요한 경우
for i := range slice {
    fmt.Println(i)
}

// 값만 필요한 경우
for _, value := range slice {
    fmt.Println(value)
}
```

## 함수

```go
// 기본 함수
func add(a, b int) int {
    return a + b
}

// 여러 반환값
func divide(a, b float64) (float64, error) {
    if b == 0 {
        return 0, errors.New("division by zero")
    }
    return a / b, nil
}

// 명명된 반환값
func calculate(a, b int) (sum, product int) {
    sum = a + b
    product = a * b
    return  // naked return
}

// 가변인자
func sum(nums ...int) int {
    total := 0
    for _, num := range nums {
        total += num
    }
    return total
}

// 익명 함수 (클로저)
add := func(a, b int) int {
    return a + b
}
```

## 배열과 슬라이스

### 배열

```go
// 배열 선언
var arr [5]int
arr[0] = 1

// 초기화
arr := [5]int{1, 2, 3, 4, 5}
arr := [...]int{1, 2, 3, 4, 5}  // 길이 자동 계산
```

### 슬라이스

```go
// 슬라이스 선언
var slice []int
slice = append(slice, 1, 2, 3)

// 초기화
slice := []int{1, 2, 3, 4, 5}
slice := make([]int, 5)      // 길이 5
slice := make([]int, 0, 10)  // 길이 0, 용량 10

// 슬라이싱
arr := [5]int{1, 2, 3, 4, 5}
slice := arr[1:4]  // [2, 3, 4]
slice := arr[:3]   // [1, 2, 3]
slice := arr[2:]   // [3, 4, 5]
slice := arr[:]    // [1, 2, 3, 4, 5]

// 슬라이스 조작
slice = append(slice, 6, 7)
copy(dest, src)
len(slice)  // 길이
cap(slice)  // 용량
```

## 맵

```go
// 맵 선언
var m map[string]int
m = make(map[string]int)

// 초기화
m := map[string]int{
    "apple":  5,
    "banana": 3,
}

m := make(map[string]int)

// 조작
m["orange"] = 7
delete(m, "apple")

// 존재 확인
value, ok := m["apple"]
if ok {
    fmt.Println("apple exists:", value)
}
```

## 구조체

```go
// 구조체 정의
type Person struct {
    Name string
    Age  int
}

// 생성
p1 := Person{Name: "Alice", Age: 30}
p2 := Person{"Bob", 25}
p3 := &Person{Name: "Charlie", Age: 35}

// 메서드 정의
func (p Person) Greet() string {
    return "Hello, " + p.Name
}

func (p *Person) HaveBirthday() {
    p.Age++
}

// 임베디드 구조체
type Employee struct {
    Person
    Company string
}
```

## 인터페이스

```go
// 인터페이스 정의
type Writer interface {
    Write([]byte) (int, error)
}

type Reader interface {
    Read([]byte) (int, error)
}

// 다중 인터페이스
type ReadWriter interface {
    Reader
    Writer
}

// 빈 인터페이스
var value interface{}
value = 42
value = "hello"

// 타입 어설션
str, ok := value.(string)
if ok {
    fmt.Println("String:", str)
}

// 타입 스위치
switch v := value.(type) {
case int:
    fmt.Println("Integer:", v)
case string:
    fmt.Println("String:", v)
default:
    fmt.Println("Unknown type")
}
```

## 포인터

```go
x := 42
p := &x      // x의 주소
fmt.Println(*p)  // x의 값 (42)
*p = 100     // x의 값을 100으로 변경

// 구조체 포인터
type Person struct {
    Name string
}

p := &Person{Name: "Alice"}
fmt.Println(p.Name)  // 자동 역참조
```

## 에러 처리

```go
import "errors"

// 에러 생성
err := errors.New("something went wrong")
err := fmt.Errorf("error: %v", value)

// 에러 처리 패턴
result, err := someFunction()
if err != nil {
    return err  // 에러 전파
}

// 사용자 정의 에러
type MyError struct {
    Code    int
    Message string
}

func (e MyError) Error() string {
    return fmt.Sprintf("Error %d: %s", e.Code, e.Message)
}
```

## 고루틴과 채널

### 고루틴

```go
// 고루틴 시작
go function()
go func() {
    fmt.Println("Hello from goroutine")
}()

// 메인 고루틴 대기
time.Sleep(time.Second)
```

### 채널

```go
// 채널 생성
ch := make(chan int)
ch := make(chan int, 10)  // 버퍼 채널

// 송수신
ch <- 42        // 송신
value := <-ch   // 수신
value, ok := <-ch  // 채널이 닫혔는지 확인

// 채널 닫기
close(ch)

// 채널 방향 지정
func send(ch chan<- int) {  // 송신 전용
    ch <- 42
}

func receive(ch <-chan int) int {  // 수신 전용
    return <-ch
}

// select문
select {
case msg1 := <-ch1:
    fmt.Println("Received from ch1:", msg1)
case msg2 := <-ch2:
    fmt.Println("Received from ch2:", msg2)
case <-time.After(time.Second):
    fmt.Println("Timeout")
default:
    fmt.Println("No channel ready")
}
```

## 패키지와 임포트

```go
// 기본 임포트
import "fmt"
import "net/http"

// 그룹 임포트
import (
    "fmt"
    "net/http"
    "time"
)

// 별칭 사용
import (
    f "fmt"
    h "net/http"
)

// 빈 임포트 (초기화만)
import _ "database/sql"

// 점 임포트 (권장하지 않음)
import . "fmt"
```

## defer, panic, recover

### defer

```go
func example() {
    defer fmt.Println("1")
    defer fmt.Println("2")
    fmt.Println("3")
    // 출력: 3, 2, 1
}

// 리소스 정리
func readFile() {
    file, err := os.Open("file.txt")
    if err != nil {
        return
    }
    defer file.Close()  // 함수 종료 시 자동 실행
    
    // 파일 읽기 작업
}
```

### panic과 recover

```go
func example() {
    defer func() {
        if r := recover(); r != nil {
            fmt.Println("Recovered:", r)
        }
    }()
    
    panic("something went wrong")
    fmt.Println("This won't be printed")
}
```

## 유용한 내장 함수

```go
// 길이와 용량
len(slice)    // 길이
cap(slice)    // 용량

// 메모리 할당
make([]int, 10)       // 슬라이스
make(map[string]int)  // 맵
make(chan int)        // 채널

new(int)  // 포인터 반환

// 슬라이스/맵 조작
append(slice, values...)
copy(dst, src)
delete(map, key)

// 타입 변환
int(3.14)
string(65)    // "A"
[]byte("hello")
```

## 문자열 조작

```go
import "strings"

// 문자열 함수
strings.Contains(s, substr)
strings.HasPrefix(s, prefix)
strings.HasSuffix(s, suffix)
strings.Index(s, substr)
strings.Join([]string{"a", "b"}, ",")
strings.Split("a,b,c", ",")
strings.ToLower(s)
strings.ToUpper(s)
strings.Trim(s, " ")
strings.Replace(s, old, new, n)

// 문자열 포맷팅
import "fmt"

fmt.Sprintf("%d %s %.2f", 42, "hello", 3.14)
fmt.Printf("%d %s %.2f\n", 42, "hello", 3.14)
```

## JSON 처리

```go
import "encoding/json"

type Person struct {
    Name string `json:"name"`
    Age  int    `json:"age"`
}

// 마샬링 (구조체 -> JSON)
p := Person{Name: "Alice", Age: 30}
data, err := json.Marshal(p)

// 언마샬링 (JSON -> 구조체)
var p Person
err := json.Unmarshal(data, &p)
```

## 파일 I/O

```go
import (
    "io/ioutil"
    "os"
)

// 파일 읽기
data, err := ioutil.ReadFile("file.txt")
if err != nil {
    log.Fatal(err)
}

// 파일 쓰기
err = ioutil.WriteFile("file.txt", []byte("content"), 0644)

// 파일 생성/열기
file, err := os.Create("file.txt")
file, err := os.Open("file.txt")
defer file.Close()
```

## HTTP 클라이언트/서버

```go
import "net/http"

// HTTP GET 요청
resp, err := http.Get("https://api.example.com")
if err != nil {
    log.Fatal(err)
}
defer resp.Body.Close()

body, err := ioutil.ReadAll(resp.Body)

// HTTP 서버
http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintf(w, "Hello, World!")
})

log.Fatal(http.ListenAndServe(":8080", nil))
```

## 테스트

```go
// main_test.go
import "testing"

func TestAdd(t *testing.T) {
    result := Add(2, 3)
    expected := 5
    
    if result != expected {
        t.Errorf("Add(2, 3) = %d; want %d", result, expected)
    }
}

// 벤치마크 테스트
func BenchmarkAdd(b *testing.B) {
    for i := 0; i < b.N; i++ {
        Add(2, 3)
    }
}

// 실행: go test
// 벤치마크: go test -bench=.
```

## 동시성 패턴

### Worker Pool

```go
func workerPool(jobs <-chan int, results chan<- int) {
    for j := range jobs {
        results <- j * 2
    }
}

jobs := make(chan int, 100)
results := make(chan int, 100)

// 워커 시작
for w := 1; w <= 3; w++ {
    go workerPool(jobs, results)
}

// 작업 전송
for j := 1; j <= 5; j++ {
    jobs <- j
}
close(jobs)

// 결과 수집
for a := 1; a <= 5; a++ {
    <-results
}
```

### 컨텍스트

```go
import "context"

ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
defer cancel()

select {
case <-ctx.Done():
    fmt.Println("Timeout or cancelled")
case result := <-someChannel:
    fmt.Println("Received:", result)
}
```

## Go Modules

```bash
# 모듈 초기화
go mod init module-name

# 의존성 추가
go get github.com/some/package

# 의존성 업데이트
go mod tidy

# 의존성 확인
go list -m all
```

이 치트 시트는 Go 언어의 핵심 기능들을 빠르게 참조할 수 있도록 정리한 것입니다. 실제 개발 시 공식 문서와 함께 참고하시면 더욱 도움이 될 것입니다.