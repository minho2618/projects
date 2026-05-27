#PHP #Cheat-Sheet
# PHP 치트 시트

## 기본 구문

### PHP 태그

```php
<?php
// PHP 코드
?>

// 짧은 태그 (권장하지 않음)
<? ... ?>

// Echo 태그
<?= $variable ?>
```

### 변수

```php
$variable = "값";
$number = 123;
$array = array(1, 2, 3);
$bool = true;

// 변수 확인
isset($variable);    // 변수가 설정되었는지
empty($variable);    // 변수가 비어있는지
unset($variable);    // 변수 삭제
```

### 상수

```php
define("CONSTANT_NAME", "값");
const CONSTANT_NAME = "값";

// 사용
echo CONSTANT_NAME;
```

## 데이터 타입

### 문자열

```php
$str = "문자열";
$str = '문자열';
$str = "Hello $name";        // 변수 포함
$str = 'Hello ' . $name;     // 문자열 연결

// 문자열 함수
strlen($str);               // 길이
substr($str, 0, 5);        // 부분 문자열
str_replace("old", "new", $str);  // 치환
strtolower($str);          // 소문자
strtoupper($str);          // 대문자
trim($str);                // 공백 제거
explode(",", $str);        // 배열로 분할
implode(",", $array);      // 배열을 문자열로
```

### 배열

```php
// 배열 생성
$arr = array(1, 2, 3);
$arr = [1, 2, 3];           // PHP 5.4+

// 연관 배열
$assoc = array("key" => "value");
$assoc = ["key" => "value"];

// 배열 조작
$arr[] = 4;                 // 끝에 추가
array_push($arr, 4);        // 끝에 추가
array_pop($arr);            // 마지막 제거
array_shift($arr);          // 첫번째 제거
array_unshift($arr, 0);     // 첫번째에 추가

// 배열 함수
count($arr);                // 길이
in_array("value", $arr);    // 값 존재 확인
array_keys($arr);           // 키 배열
array_values($arr);         // 값 배열
array_merge($arr1, $arr2);  // 배열 합치기
sort($arr);                 // 정렬
```

## 제어 구조

### 조건문

```php
if ($condition) {
    // 코드
} elseif ($condition2) {
    // 코드
} else {
    // 코드
}

// 삼항 연산자
$result = $condition ? "true" : "false";

// Null 병합 연산자 (PHP 7+)
$result = $variable ?? "기본값";

// Switch
switch ($variable) {
    case 'value1':
        // 코드
        break;
    case 'value2':
        // 코드
        break;
    default:
        // 기본 코드
}
```

### 반복문

```php
// For 루프
for ($i = 0; $i < 10; $i++) {
    echo $i;
}

// While 루프
while ($condition) {
    // 코드
}

// Do-while 루프
do {
    // 코드
} while ($condition);

// Foreach 루프
foreach ($array as $value) {
    echo $value;
}

foreach ($array as $key => $value) {
    echo "$key: $value";
}
```

## 함수

### 함수 정의

```php
function functionName($param1, $param2 = "기본값") {
    return $result;
}

// 호출
$result = functionName("arg1", "arg2");

// 가변 인수 (PHP 5.6+)
function varArgs(...$args) {
    foreach ($args as $arg) {
        echo $arg;
    }
}

// 익명 함수
$closure = function($param) {
    return $param * 2;
};
```

### 내장 함수

```php
// 수학 함수
abs(-5);                    // 절댓값
ceil(4.3);                  // 올림
floor(4.7);                 // 내림
round(4.6);                 // 반올림
rand(1, 10);                // 랜덤 수
max(1, 2, 3);               // 최댓값
min(1, 2, 3);               // 최솟값

// 날짜/시간
date("Y-m-d H:i:s");        // 현재 날짜/시간
time();                     // Unix 타임스탬프
mktime(0, 0, 0, 12, 20, 2023);  // 특정 날짜의 타임스탬프
```

## 객체 지향 프로그래밍

### 클래스와 객체

```php
class MyClass {
    public $public = 'Public';
    protected $protected = 'Protected';
    private $private = 'Private';
    
    public function __construct($param) {
        $this->public = $param;
    }
    
    public function publicMethod() {
        return $this->private;
    }
    
    protected function protectedMethod() {
        // 코드
    }
    
    private function privateMethod() {
        // 코드
    }
    
    public static function staticMethod() {
        return "Static method";
    }
}

// 객체 생성
$obj = new MyClass("값");
echo $obj->public;
echo $obj->publicMethod();
echo MyClass::staticMethod();
```

### 상속

```php
class ChildClass extends MyClass {
    public function __construct($param) {
        parent::__construct($param);
    }
    
    public function childMethod() {
        return parent::publicMethod();
    }
}
```

### 인터페이스와 추상 클래스

```php
interface MyInterface {
    public function interfaceMethod();
}

abstract class AbstractClass {
    abstract public function abstractMethod();
    
    public function concreteMethod() {
        return "Concrete";
    }
}

class ConcreteClass extends AbstractClass implements MyInterface {
    public function abstractMethod() {
        return "Implementation";
    }
    
    public function interfaceMethod() {
        return "Interface implementation";
    }
}
```

## 파일 처리

### 파일 읽기/쓰기

```php
// 파일 읽기
$content = file_get_contents("file.txt");
$lines = file("file.txt");              // 배열로 읽기

// 파일 쓰기
file_put_contents("file.txt", "내용");
file_put_contents("file.txt", "추가", FILE_APPEND);

// 파일 핸들링
$handle = fopen("file.txt", "r");
while (($line = fgets($handle)) !== false) {
    echo $line;
}
fclose($handle);

// 파일 정보
file_exists("file.txt");
is_file("file.txt");
is_dir("directory");
filesize("file.txt");
```

## 폼 처리

### GET/POST 데이터

```php
// GET 데이터
$value = $_GET['key'];
$value = filter_input(INPUT_GET, 'key', FILTER_SANITIZE_STRING);

// POST 데이터
$value = $_POST['key'];
$value = filter_input(INPUT_POST, 'key', FILTER_SANITIZE_STRING);

// 파일 업로드
if (isset($_FILES['upload'])) {
    $file = $_FILES['upload'];
    move_uploaded_file($file['tmp_name'], "uploads/" . $file['name']);
}
```

### 세션과 쿠키

```php
// 세션
session_start();
$_SESSION['key'] = 'value';
$value = $_SESSION['key'];
session_destroy();

// 쿠키
setcookie("name", "value", time() + 3600);  // 1시간
$value = $_COOKIE['name'];
```

## 데이터베이스 (MySQLi)

### 연결

```php
$mysqli = new mysqli("localhost", "username", "password", "database");

if ($mysqli->connect_error) {
    die("Connection failed: " . $mysqli->connect_error);
}
```

### 쿼리 실행

```php
// SELECT
$result = $mysqli->query("SELECT * FROM users");
while ($row = $result->fetch_assoc()) {
    echo $row['name'];
}

// Prepared Statement
$stmt = $mysqli->prepare("SELECT * FROM users WHERE id = ?");
$stmt->bind_param("i", $id);
$stmt->execute();
$result = $stmt->get_result();

// INSERT/UPDATE/DELETE
$mysqli->query("INSERT INTO users (name, email) VALUES ('John', 'john@email.com')");
```

## PDO (PHP Data Objects)

```php
// 연결
$pdo = new PDO("mysql:host=localhost;dbname=database", "username", "password");
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

// 쿼리
$stmt = $pdo->prepare("SELECT * FROM users WHERE id = :id");
$stmt->bindParam(':id', $id);
$stmt->execute();
$users = $stmt->fetchAll(PDO::FETCH_ASSOC);

// INSERT
$stmt = $pdo->prepare("INSERT INTO users (name, email) VALUES (:name, :email)");
$stmt->execute([':name' => $name, ':email' => $email]);
```

## 오류 처리

### Try-Catch

```php
try {
    // 위험한 코드
    throw new Exception("에러 메시지");
} catch (Exception $e) {
    echo "에러: " . $e->getMessage();
} finally {
    // 항상 실행되는 코드
}
```

### 에러 리포팅

```php
error_reporting(E_ALL);
ini_set('display_errors', 1);

// 커스텀 에러 핸들러
function customError($errno, $errstr) {
    echo "Error: [$errno] $errstr";
}
set_error_handler("customError");
```

## 유용한 함수들

### 타입 확인

```php
is_string($var);
is_int($var);
is_array($var);
is_object($var);
is_null($var);
is_numeric($var);
gettype($var);
var_dump($var);         // 상세 정보
print_r($var);          // 읽기 쉬운 형태
```

### JSON 처리

```php
$json = json_encode($array);        // 배열을 JSON으로
$array = json_decode($json, true);  // JSON을 배열로
```

### URL 처리

```php
$encoded = urlencode($string);
$decoded = urldecode($string);
$parsed = parse_url($url);
```

### 정규표현식

```php
preg_match("/pattern/", $string, $matches);
preg_match_all("/pattern/", $string, $matches);
preg_replace("/pattern/", "replacement", $string);
```

## 보안

### 입력 검증

```php
// 필터링
$email = filter_var($email, FILTER_VALIDATE_EMAIL);
$int = filter_var($int, FILTER_VALIDATE_INT);

// HTML 이스케이프
$safe = htmlspecialchars($input, ENT_QUOTES, 'UTF-8');

// SQL 인젝션 방지 (Prepared Statements 사용)
$stmt = $pdo->prepare("SELECT * FROM users WHERE email = ?");
$stmt->execute([$email]);
```

### 패스워드 해싱

```php
// 해싱
$hash = password_hash($password, PASSWORD_DEFAULT);

// 검증
if (password_verify($password, $hash)) {
    // 로그인 성공
}
```

## 네임스페이스와 Autoloading

### 네임스페이스

```php
namespace MyNamespace;

class MyClass {
    // 클래스 정의
}

// 사용
use MyNamespace\MyClass;
$obj = new MyClass();
```

### Composer Autoloading

```php
require_once 'vendor/autoload.php';

use Vendor\Package\ClassName;
$obj = new ClassName();
```

이 치트 시트는 PHP의 핵심 기능들을 빠르게 참조할 수 있도록 정리한 것입니다. 실제 개발 시 필요에 따라 PHP 공식 문서를 참조하여 더 자세한 정보를 확인하시기 바랍니다.