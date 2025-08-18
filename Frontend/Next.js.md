#Nextjs #React #Frontend
# React
[[React]]

# Next.js Cheat Sheet

## 설치 및 프로젝트 생성

```bash
# 새 Next.js 프로젝트 생성
npx create-next-app@latest my-app
cd my-app
npm run dev

# TypeScript와 함께 생성
npx create-next-app@latest my-app --typescript
```

## 기본 파일 구조

```
my-app/
├── pages/          # 페이지 파일들 (App Router 이전)
├── app/           # App Router (Next.js 13+)
├── public/        # 정적 파일들
├── styles/        # CSS 파일들
├── components/    # 재사용 컴포넌트
└── next.config.js # Next.js 설정
```

## App Router (Next.js 13+)

### 기본 라우팅

```
app/
├── page.js        # / 경로
├── about/
│   └── page.js    # /about 경로
└── blog/
    ├── page.js    # /blog 경로
    └── [slug]/
        └── page.js # /blog/[slug] 동적 경로
```

### 특수 파일들

```javascript
// app/layout.js - 루트 레이아웃
export default function RootLayout({ children }) {
  return (
    <html lang="ko">
      <body>{children}</body>
    </html>
  )
}

// app/loading.js - 로딩 UI
export default function Loading() {
  return <div>로딩 중...</div>
}

// app/error.js - 에러 UI
'use client'
export default function Error({ error, reset }) {
  return (
    <div>
      <h2>오류가 발생했습니다!</h2>
      <button onClick={() => reset()}>다시 시도</button>
    </div>
  )
}

// app/not-found.js - 404 페이지
export default function NotFound() {
  return <div>페이지를 찾을 수 없습니다</div>
}
```

## 데이터 페칭

### Server Components에서 fetch

```javascript
// app/posts/page.js
async function getPosts() {
  const res = await fetch('https://api.example.com/posts')
  return res.json()
}

export default async function PostsPage() {
  const posts = await getPosts()
  return (
    <div>
      {posts.map(post => (
        <div key={post.id}>{post.title}</div>
      ))}
    </div>
  )
}
```

### Client Components에서 데이터 페칭

```javascript
'use client'
import { useState, useEffect } from 'react'

export default function ClientComponent() {
  const [data, setData] = useState(null)

  useEffect(() => {
    fetch('/api/data')
      .then(res => res.json())
      .then(setData)
  }, [])

  return <div>{data ? data.title : '로딩중...'}</div>
}
```

## API Routes

```javascript
// app/api/users/route.js
export async function GET() {
  const users = await fetchUsers()
  return Response.json(users)
}

export async function POST(request) {
  const body = await request.json()
  const user = await createUser(body)
  return Response.json(user)
}

// 동적 라우트: app/api/users/[id]/route.js
export async function GET(request, { params }) {
  const user = await getUserById(params.id)
  return Response.json(user)
}
```

## 메타데이터

```javascript
// app/page.js
export const metadata = {
  title: '홈페이지',
  description: '웹사이트 설명',
}

// 동적 메타데이터
export async function generateMetadata({ params }) {
  const product = await getProduct(params.id)
  return {
    title: product.name,
    description: product.description,
  }
}
```

## 스타일링

### CSS Modules

```javascript
// styles/Home.module.css
.container {
  padding: 20px;
}

// components/Home.js
import styles from '../styles/Home.module.css'

export default function Home() {
  return <div className={styles.container}>홈</div>
}
```

### Tailwind CSS

```bash
npm install -D tailwindcss postcss autoprefixer
npx tailwindcss init -p
```

## 이미지 최적화

```javascript
import Image from 'next/image'

export default function MyComponent() {
  return (
    <Image
      src="/profile.jpg"
      alt="프로필 사진"
      width={500}
      height={300}
      priority // LCP 이미지인 경우
    />
  )
}
```

## 링크와 네비게이션

```javascript
import Link from 'next/link'
import { useRouter } from 'next/navigation'

export default function Navigation() {
  const router = useRouter()

  return (
    <nav>
      <Link href="/about">소개</Link>
      <Link href={`/user/${userId}`}>사용자</Link>
      <button onClick={() => router.push('/dashboard')}>
        대시보드로 이동
      </button>
    </nav>
  )
}
```

## 환경 변수

```bash
# .env.local
DATABASE_URL=your_database_url
NEXT_PUBLIC_API_KEY=your_public_api_key
```

```javascript
// 서버사이드에서만 접근 가능
const dbUrl = process.env.DATABASE_URL

// 클라이언트사이드에서도 접근 가능 (NEXT_PUBLIC_ 접두사)
const apiKey = process.env.NEXT_PUBLIC_API_KEY
```

## 미들웨어

```javascript
// middleware.js
import { NextResponse } from 'next/server'

export function middleware(request) {
  if (request.nextUrl.pathname.startsWith('/admin')) {
    return NextResponse.redirect(new URL('/login', request.url))
  }
}

export const config = {
  matcher: '/admin/:path*'
}
```

## 배포 설정

```javascript
// next.config.js
/** @type {import('next').NextConfig} */
const nextConfig = {
  experimental: {
    appDir: true,
  },
  images: {
    domains: ['example.com'],
  },
  env: {
    CUSTOM_KEY: process.env.CUSTOM_KEY,
  },
}

module.exports = nextConfig
```

## 유용한 Hook들

```javascript
// 클라이언트 컴포넌트에서만 사용
'use client'
import { usePathname, useSearchParams } from 'next/navigation'

export default function MyComponent() {
  const pathname = usePathname()
  const searchParams = useSearchParams()
  
  return <div>현재 경로: {pathname}</div>
}
```

## 빌드 및 배포

```bash
# 프로덕션 빌드
npm run build

# 프로덕션 서버 시작
npm start

# 정적 export
npm run build && npm run export
```

이 치트시트는 Next.js의 핵심 기능들을 빠르게 참조할 수 있도록 정리한 것입니다. 각 기능에 대한 더 자세한 내용은 공식 문서를 참고하시기 바랍니다.