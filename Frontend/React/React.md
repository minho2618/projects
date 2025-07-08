### 기본 구성 요소

#### Functional Component

```JavaScript
function Hello({ name }) {   
	return <h1>Hello, {name}!</h1>;
}
```

#### JSX 문법 요약

```jsx
const element = <div className="box">Hello</div>; // class -> className 
const isVisible = true; 
return <>{ isVisible && <p>보임</p> }</>; // 조건부 렌더링
```

---

### 🔁 Props & State

#### Props

jsx

복사편집

`function Welcome({ user }) {   return <h1>Welcome, {user}!</h1>; }`

#### State (useState)

jsx

복사편집

`import { useState } from 'react';  function Counter() {   const [count, setCount] = useState(0);   return <button onClick={() => setCount(count + 1)}>{count}</button>; }`

---

### 🎯 이벤트 핸들링

jsx

복사편집

`function Button() {   const handleClick = (e) => {     e.preventDefault();     alert('Clicked!');   };    return <button onClick={handleClick}>Click me</button>; }`

---

### 🔄 조건부 렌더링

jsx

복사편집

`{isLoggedIn ? <Dashboard /> : <Login />}`

---

### 🔃 리스트 렌더링 (map)

jsx

복사편집

`const items = ['a', 'b', 'c']; return (   <ul>     {items.map((item, index) => (       <li key={index}>{item}</li>     ))}   </ul> );`

---

### 📦 useEffect (라이프사이클)

jsx

복사편집

`import { useEffect } from 'react';  useEffect(() => {   console.log('Mounted!');   return () => console.log('Unmounted!'); }, []);`

- `[]` = 컴포넌트 처음 렌더될 때만 실행
    
- `[count]` = `count` 바뀔 때마다 실행
    

---

### 📚 폼 핸들링

jsx

복사편집

`const [text, setText] = useState('');  <form onSubmit={(e) => { e.preventDefault(); alert(text); }}>   <input value={text} onChange={(e) => setText(e.target.value)} /> </form>`

---

### 📁 컴포넌트 구조 예시

css

복사편집

`/src  ├── components/  │    ├── Header.jsx  │    ├── Footer.jsx  ├── pages/  │    ├── Home.jsx  │    ├── About.jsx  ├── App.jsx  └── index.js`

---

### 🎨 스타일링 방법 요약

|방법|특징|
|---|---|
|Inline Style|`style={{ color: 'red' }}`|
|CSS 파일|`import './App.css'`|
|CSS Module|`import styles from './App.module.css'`|
|styled-components|CSS-in-JS 스타일링 방법|
|Tailwind CSS|유틸리티 클래스 기반 스타일링|

---

### 📡 API 요청 (Fetch)

jsx

복사편집

`useEffect(() => {   fetch('https://api.example.com/data')     .then(res => res.json())     .then(data => setData(data)); }, []);`

---

### 💾 상태 관리 (간단 소개)

|방법|특징|
|---|---|
|useState|단순한 컴포넌트 내부 상태|
|useContext|전역 상태 공유|
|Redux|복잡한 앱 상태 관리에 적합|
|Zustand, Recoil|최신 상태 관리 라이브러리들 (가볍고 트렌디함)|

---

### ⚙️ Hooks 요약

|Hook|설명|
|---|---|
|`useState`|상태값 설정|
|`useEffect`|부수 효과 처리 (fetch, 타이머 등)|
|`useContext`|전역 데이터 전달|
|`useRef`|DOM 접근 또는 값을 저장할 때|
|`useMemo`|값 메모이제이션|
|`useCallback`|함수 메모이제이션|

---

### 📌 보너스: Custom Hook 예시

jsx

복사편집

`function useToggle(initial = false) {   const [value, setValue] = useState(initial);   const toggle = () => setValue(v => !v);   return [value, toggle]; }`

---

이걸 PDF나 Notion, Obsidian에 저장해두면 리액트 할 때 참 편해.  
필요하면 **리액트 + 타입스크립트**, **리액트 + Next.js**, **리액트 + Redux Toolkit** 버전도 만들어줄게!

원하는 스타일이나 확장 분야 있으면 말해줘 😎

### Redux

[[Redux]]

### React-Router

[[React-Router]]