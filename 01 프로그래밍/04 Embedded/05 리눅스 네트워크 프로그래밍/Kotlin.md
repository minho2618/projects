#Kotlin

## 기본 문법

### 변수 선언

```kotlin
val immutable = "변경 불가능"     // 읽기 전용 (final)
var mutable = "변경 가능"         // 변경 가능
const val CONSTANT = "상수"       // 컴파일 타임 상수

// 타입 명시
val explicit: String = "명시적 타입"
val inferred = "타입 추론"        // String으로 추론됨
```

### 널 안정성 (Null Safety)

```kotlin
var nullable: String? = null      // 널 가능
var nonNull: String = "not null"  // 널 불가능

// 안전 호출 연산자
nullable?.length                  // null이면 null 반환

// 엘비스 연산자
val length = nullable?.length ?: 0  // null이면 0 반환

// 널 아님 단언
nullable!!.length                 // null이면 NPE 발생

// 안전한 캐스팅
val x: String? = y as? String     // 실패시 null 반환
```

## 함수

### 함수 선언

```kotlin
// 기본 함수
fun greet(name: String): String {
    return "Hello, $name"
}

// 단일 표현식 함수
fun add(a: Int, b: Int) = a + b

// 기본 매개변수
fun greet(name: String = "World") = "Hello, $name"

// 명명된 인자
greet(name = "Kotlin")

// 가변 인자
fun sum(vararg numbers: Int): Int {
    return numbers.sum()
}

// 확장 함수
fun String.lastChar(): Char = this[length - 1]
"Kotlin".lastChar()  // 'n'

// 중위 함수
infix fun Int.times(str: String) = str.repeat(this)
2 times "Bye "  // "Bye Bye "

// 지역 함수
fun outer() {
    fun inner() { }
    inner()
}
```

### 람다와 고차 함수

```kotlin
// 람다 표현식
val sum = { x: Int, y: Int -> x + y }
val sum2: (Int, Int) -> Int = { x, y -> x + y }

// 고차 함수
fun operate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}
operate(5, 3, { a, b -> a + b })
operate(5, 3) { a, b -> a * b }  // 후행 람다

// it: 단일 매개변수의 암시적 이름
listOf(1, 2, 3).filter { it > 1 }

// 함수 참조
listOf(1, 2, 3).map(Int::toString)
```

## 클래스와 객체

### 클래스 선언

```kotlin
// 기본 클래스
class Person(val name: String, var age: Int)

// 주 생성자와 초기화 블록
class Person(firstName: String) {
    val name = firstName.uppercase()
    
    init {
        println("Person created: $name")
    }
    
    // 보조 생성자
    constructor(firstName: String, age: Int) : this(firstName) {
        this.age = age
    }
}

// 프로퍼티
class Rectangle(val width: Int, val height: Int) {
    val area: Int
        get() = width * height
    
    var color: String = "red"
        set(value) {
            field = value.uppercase()
        }
}
```

### 데이터 클래스

```kotlin
data class User(val name: String, val age: Int)

val user = User("Alice", 29)
val (name, age) = user           // 구조 분해
val copy = user.copy(age = 30)   // 복사
```

### 봉인 클래스 (Sealed Class)

```kotlin
sealed class Result {
    data class Success(val data: String) : Result()
    data class Error(val message: String) : Result()
    object Loading : Result()
}

fun handleResult(result: Result) = when(result) {
    is Result.Success -> println(result.data)
    is Result.Error -> println(result.message)
    Result.Loading -> println("Loading...")
}
```

### 싱글톤 객체

```kotlin
object Singleton {
    fun doSomething() { }
}
Singleton.doSomething()

// 동반 객체
class MyClass {
    companion object {
        fun create(): MyClass = MyClass()
    }
}
val instance = MyClass.create()
```

## 상속과 인터페이스

### 상속

```kotlin
// open으로 상속 가능하게 만들기
open class Animal(val name: String) {
    open fun sound() = "Some sound"
}

class Dog(name: String) : Animal(name) {
    override fun sound() = "Woof!"
}

// 추상 클래스
abstract class Shape {
    abstract fun area(): Double
}

class Circle(val radius: Double) : Shape() {
    override fun area() = Math.PI * radius * radius
}
```

### 인터페이스

```kotlin
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")  // 기본 구현
}

interface Focusable {
    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {
    override fun click() = println("Clicked")
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}
```

## 컬렉션

### 리스트

```kotlin
val immutableList = listOf(1, 2, 3)
val mutableList = mutableListOf(1, 2, 3)
mutableList.add(4)

// 리스트 연산
val doubled = immutableList.map { it * 2 }
val filtered = immutableList.filter { it > 1 }
val sum = immutableList.reduce { acc, n -> acc + n }
```

### 맵

```kotlin
val map = mapOf("a" to 1, "b" to 2)
val mutableMap = mutableMapOf<String, Int>()
mutableMap["key"] = 42

// 맵 연산
for ((key, value) in map) {
    println("$key -> $value")
}
map.forEach { (k, v) -> println("$k -> $v") }
```

### 셋

```kotlin
val set = setOf(1, 2, 3, 3)  // 중복 제거됨
val mutableSet = mutableSetOf<Int>()
```

### 시퀀스 (지연 연산)

```kotlin
val sequence = sequenceOf(1, 2, 3)
val bigSequence = (1..1000000).asSequence()
    .filter { it % 2 == 0 }
    .map { it * 2 }
    .take(10)
    .toList()  // 최종 연산에서만 실행됨
```

## 제어 흐름

### when 표현식

```kotlin
val result = when (x) {
    1 -> "one"
    2 -> "two"
    3, 4 -> "three or four"
    in 5..10 -> "five to ten"
    is String -> "string"
    else -> "unknown"
}

// 인자 없는 when
when {
    x.isOdd() -> println("odd")
    x.isEven() -> println("even")
    else -> println("funny")
}
```

### for 루프

```kotlin
for (i in 1..10) { }           // 1부터 10까지
for (i in 1 until 10) { }      // 1부터 9까지
for (i in 10 downTo 1) { }     // 10부터 1까지
for (i in 1..10 step 2) { }    // 1, 3, 5, 7, 9

for ((index, value) in list.withIndex()) {
    println("$index: $value")
}
```

## 범위 (Ranges)

```kotlin
val range = 1..10               // IntRange
val charRange = 'a'..'z'        // CharRange
val downRange = 10 downTo 1     // 역순

if (x in 1..10) { }             // 범위 체크
if (x !in 1..10) { }            // 범위 밖 체크
```

## 스코프 함수

```kotlin
val result = with(object) {
    // this = object
    property + method()
}

val result = object.let {
    // it = object
    it.property + it.method()
}

val result = object.run {
    // this = object
    property + method()
}

val result = object.apply {
    // this = object
    property = value
    method()
}  // object 반환

val result = object.also {
    // it = object
    println(it)
}  // object 반환

// takeIf, takeUnless
val email = input.takeIf { it.contains("@") }
val name = input.takeUnless { it.isNullOrBlank() }
```

## 예외 처리

```kotlin
// try-catch 표현식
val result = try {
    riskyOperation()
} catch (e: Exception) {
    defaultValue
} finally {
    cleanup()
}

// throw는 표현식
val percentage = 
    if (value in 0..100) value
    else throw IllegalArgumentException("Invalid percentage")

// Nothing 타입
fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}
```

## 제네릭

```kotlin
// 제네릭 클래스
class Box<T>(val value: T)

// 제네릭 함수
fun <T> singletonList(item: T): List<T> {
    return listOf(item)
}

// 제약
fun <T : Comparable<T>> max(a: T, b: T): T {
    return if (a > b) a else b
}

// 변성
class Producer<out T>      // 공변성 (생산만)
class Consumer<in T>       // 반공변성 (소비만)
class Box<T>              // 무변성

// 타입 프로젝션
fun copy(from: Array<out Any>, to: Array<Any>) { }

// reified 타입 파라미터
inline fun <reified T> isInstance(value: Any) = value is T
```

## 위임 (Delegation)

```kotlin
// 클래스 위임
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base) : Base by b

// 프로퍼티 위임
class Example {
    var p: String by Delegate()
    
    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }
    
    var observed: String by Delegates.observable("initial") { 
        _, old, new -> println("$old -> $new")
    }
}
```

## 코루틴 (기본)

```kotlin
import kotlinx.coroutines.*

// 코루틴 시작
GlobalScope.launch {
    delay(1000L)
    println("World!")
}
println("Hello,")

// suspend 함수
suspend fun doSomething() {
    delay(1000L)
}

// runBlocking
runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

// async와 await
runBlocking {
    val deferred = async { computeValue() }
    val result = deferred.await()
}
```

## 유용한 팁

### 타입 별칭

```kotlin
typealias CustomerName = String
typealias CustomerId = Int
typealias CustomerMap = Map<CustomerId, CustomerName>
```

### 구조 분해 선언

```kotlin
val (name, age) = person
val (_, status) = getResult()  // 첫 번째 값 무시

for ((key, value) in map) { }
```

### 인라인 함수

```kotlin
inline fun measureTime(block: () -> Unit) {
    val start = System.currentTimeMillis()
    block()
    println("Time: ${System.currentTimeMillis() - start}ms")
}
```

### 연산자 오버로딩

```kotlin
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}
val sum = Point(1, 2) + Point(3, 4)  // Point(4, 6)
```

### String 템플릿

```kotlin
val name = "Kotlin"
println("Hello, $name")
println("Length: ${name.length}")
```

### 스마트 캐스트

```kotlin
fun demo(x: Any) {
    if (x is String) {
        println(x.length)  // x는 자동으로 String으로 캐스트됨
    }
}
```