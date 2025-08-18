다음은 **Go 언어 면접 대비용 Cheat Sheet**입니다. 기초 문법부터 고급 개념, 면접에서 자주 나오는 주제들까지 요약한 자료입니다.

---

## 📌 기본 문법 (Syntax)

|항목|예시|
|---|---|
|변수 선언|`var x int = 10`, `x := 10`|
|상수|`const Pi = 3.14`|
|함수 선언|`func add(a int, b int) int { return a + b }`|
|다중 반환|`func swap(x, y string) (string, string)`|
|defer|`defer fmt.Println("끝")` (스코프 종료 시 실행)|
|포인터|`var p *int = &x`|
|배열|`var arr [3]int = [3]int{1,2,3}`|
|슬라이스|`s := []int{1, 2, 3}`|
|맵|`m := map[string]int{"a": 1}`|
|for 루프|`for i := 0; i < 10; i++ {}` 또는 `for _, val := range s {}`|
|if 문|`if x > 10 {}`|
|switch 문|`switch x { case 1: ... }`|

---

## 📌 구조체와 인터페이스

go

복사편집

`type Person struct {     Name string     Age  int }  func (p Person) Greet() string {     return "Hi, I'm " + p.Name }  type Greeter interface {     Greet() string }`

- **값/포인터 리시버**: 값 복사 vs 원본 수정
    
- **인터페이스 만족 조건**: 메서드 자동 구현 (명시적 선언 불필요)
    

---

## 📌 주요 내장 함수 및 패키지

|함수 / 패키지|설명|
|---|---|
|`len()`|길이 반환|
|`make()`|슬라이스/채널/맵 생성|
|`new()`|메모리 할당, 포인터 반환|
|`append()`|슬라이스 추가|
|`delete()`|맵 항목 삭제|
|`panic()` / `recover()`|예외 처리|
|`strings`, `strconv`, `fmt`|문자열, 변환, 출력 처리|

---

## 📌 고루틴과 채널

go

복사편집

`go say("Hello") // 고루틴  ch := make(chan int) go func() { ch <- 3 }() x := <-ch`

- `chan T`: T 타입 데이터를 주고받는 채널
    
- `<-ch`: 수신, `ch <- val`: 송신
    
- `select`: 여러 채널 대기
    

---

## 📌 동시성 패턴

|패턴|설명|
|---|---|
|고루틴 + 채널|CSP(Communicating Sequential Process) 모델|
|`sync.Mutex`|임계영역 보호|
|`sync.WaitGroup`|고루틴 종료 대기|
|`sync.Once`|한 번만 실행|
|`context`|타임아웃, 취소, 요청 전파|

---

## 📌 에러 처리

go

복사편집

`func divide(a, b int) (int, error) {     if b == 0 {         return 0, errors.New("division by zero")     }     return a / b, nil }`

- Go는 예외 대신 에러 값을 반환
    
- `errors.New()`, `fmt.Errorf()`
    

---

## 📌 모듈과 패키지

sh

복사편집

`go mod init mymodule go mod tidy go build / go run / go test`

- `import "fmt"`: 외부/표준 패키지 불러오기
    
- 모듈 시스템: Go 1.11+
    

---

## 📌 테스트

go

복사편집

`import "testing"  func TestAdd(t *testing.T) {     got := Add(2, 3)     want := 5     if got != want {         t.Errorf("got %d, want %d", got, want)     } }`

- `go test`, `go test -v`
    
- `t.Fatal()`, `t.Error()`
    

---

## 📌 자주 나오는 면접 질문

|질문 유형|예시|
|---|---|
|Go의 인터페이스는 어떻게 작동하나요?||
|슬라이스와 배열의 차이점은?||
|채널을 이용한 동시성 예시를 설명해주세요||
|고루틴의 스케줄링은 어떻게 되나요?||
|panic과 recover의 사용법?||
|context의 용도는?||
|zero value란 무엇인가요?||
|메모리 누수를 어떻게 방지하나요?||
|value vs pointer receiver 차이?||
|map 순서가 고정되지 않는 이유는?||

---

## 📌 추천 패키지

- `net/http`: 웹서버
    
- `encoding/json`: JSON 처리
    
- `os`, `io`, `bufio`: 파일 입출력
    
- `log`: 로깅
    
- `testing`: 유닛 테스트
    

---

필요하다면 PDF 혹은 인쇄용 요약본도 만들어 드릴 수 있습니다.  
또한 특정 주제 (예: 고루틴, 인터페이스 등)에 대해 면접용 예제 중심으로 더 깊이 다뤄볼 수도 있어요. 요청해 주세요.