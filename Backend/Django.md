#Django #Python
# Django Cheat Sheet

## 프로젝트 설정

### 프로젝트 생성 및 관리

```bash
# Django 설치
pip install django

# 프로젝트 생성
django-admin startproject myproject

# 앱 생성
python manage.py startapp myapp

# 서버 실행
python manage.py runserver
python manage.py runserver 8080  # 포트 지정

# 마이그레이션
python manage.py makemigrations
python manage.py migrate
python manage.py migrate --fake  # 가짜 마이그레이션

# 슈퍼유저 생성
python manage.py createsuperuser

# Shell 접속
python manage.py shell
python manage.py shell_plus  # django-extensions 필요

# 정적 파일 수집
python manage.py collectstatic
```

## Models

### 기본 모델 정의

```python
from django.db import models
from django.contrib.auth.models import User

class Post(models.Model):
    # 필드 타입들
    title = models.CharField(max_length=200)
    slug = models.SlugField(unique=True)
    content = models.TextField()
    is_published = models.BooleanField(default=False)
    view_count = models.IntegerField(default=0)
    price = models.DecimalField(max_digits=10, decimal_places=2)
    rating = models.FloatField(null=True, blank=True)
    
    # 날짜/시간
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    published_date = models.DateField(null=True, blank=True)
    
    # 관계 필드
    author = models.ForeignKey(User, on_delete=models.CASCADE)
    category = models.ForeignKey('Category', on_delete=models.SET_NULL, null=True)
    tags = models.ManyToManyField('Tag', related_name='posts')
    
    # 파일/이미지
    image = models.ImageField(upload_to='posts/%Y/%m/%d/')
    file = models.FileField(upload_to='documents/')
    
    class Meta:
        ordering = ['-created_at']
        verbose_name = '포스트'
        verbose_name_plural = '포스트들'
        db_table = 'blog_posts'
        unique_together = ['title', 'author']
        
    def __str__(self):
        return self.title
```

### 필드 옵션

```python
# 공통 필드 옵션
field = models.CharField(
    max_length=100,
    null=True,           # DB에서 NULL 허용
    blank=True,          # 폼에서 빈 값 허용
    default='default',   # 기본값
    unique=True,         # 유니크 제약
    db_index=True,       # 인덱스 생성
    editable=False,      # 폼에서 편집 불가
    help_text='도움말',
    verbose_name='필드명',
    choices=[('M', 'Male'), ('F', 'Female')],
    validators=[validate_function],
)

# on_delete 옵션
models.CASCADE     # 연결된 객체 함께 삭제
models.PROTECT     # 삭제 방지
models.SET_NULL    # NULL로 설정
models.SET_DEFAULT # 기본값으로 설정
models.SET()       # 특정 값으로 설정
models.DO_NOTHING  # 아무것도 안함
```

### QuerySet API

```python
# 조회
Post.objects.all()
Post.objects.get(id=1)
Post.objects.filter(title__contains='Django')
Post.objects.exclude(is_published=False)
Post.objects.first()
Post.objects.last()
Post.objects.exists()
Post.objects.count()

# 필터링 옵션
.filter(title__exact='Django')         # 정확히 일치
.filter(title__iexact='django')       # 대소문자 무시
.filter(title__contains='Django')      # 포함
.filter(title__icontains='django')     # 대소문자 무시 포함
.filter(title__startswith='Django')    # 시작
.filter(title__endswith='Django')      # 끝
.filter(id__in=[1, 2, 3])             # 리스트 내 포함
.filter(id__gt=5)                      # 보다 큰
.filter(id__gte=5)                     # 크거나 같은
.filter(id__lt=5)                      # 보다 작은
.filter(id__lte=5)                     # 작거나 같은
.filter(date__range=['2024-01-01', '2024-12-31'])
.filter(date__year=2024)
.filter(date__month=1)
.filter(date__day=15)
.filter(title__isnull=True)            # NULL 체크

# 정렬
.order_by('created_at')    # 오름차순
.order_by('-created_at')   # 내림차순
.order_by('?')             # 랜덤

# 중복 제거
.distinct()

# 값 선택
.values('title', 'content')
.values_list('title', flat=True)

# 집계
from django.db.models import Count, Sum, Avg, Max, Min
Post.objects.aggregate(total=Count('id'))
Post.objects.aggregate(avg_rating=Avg('rating'))

# 그룹화
.annotate(comment_count=Count('comments'))

# 관련 객체 미리 로드
.select_related('author')           # ForeignKey, OneToOne
.prefetch_related('tags')           # ManyToMany

# 생성/수정/삭제
Post.objects.create(title='New Post')
Post.objects.get_or_create(title='Post')
Post.objects.update_or_create(id=1, defaults={'title': 'Updated'})
Post.objects.filter(id=1).update(title='Updated')
Post.objects.filter(id=1).delete()
Post.objects.bulk_create([Post(...), Post(...)])
Post.objects.bulk_update(posts, ['title'])

# Q 객체 (복잡한 쿼리)
from django.db.models import Q
Post.objects.filter(Q(title__contains='Django') | Q(content__contains='Django'))
Post.objects.filter(Q(title__contains='Django') & ~Q(is_published=False))

# F 객체 (필드 참조)
from django.db.models import F
Post.objects.update(view_count=F('view_count') + 1)
Post.objects.filter(view_count__gt=F('like_count'))
```

## Views

### Function-Based Views

```python
from django.shortcuts import render, redirect, get_object_or_404
from django.http import HttpResponse, JsonResponse, Http404
from django.contrib.auth.decorators import login_required
from django.views.decorators.http import require_http_methods
from django.views.decorators.cache import cache_page
from django.core.paginator import Paginator

@login_required
@require_http_methods(["GET", "POST"])
@cache_page(60 * 15)  # 15분 캐시
def post_list(request):
    posts = Post.objects.all()
    
    # 페이지네이션
    paginator = Paginator(posts, 10)
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)
    
    context = {
        'posts': page_obj,
        'title': 'Post List'
    }
    return render(request, 'posts/list.html', context)

def post_detail(request, pk):
    post = get_object_or_404(Post, pk=pk)
    return render(request, 'posts/detail.html', {'post': post})

def post_create(request):
    if request.method == 'POST':
        # 폼 처리
        return redirect('post_list')
    return render(request, 'posts/create.html')
```

### Class-Based Views

```python
from django.views.generic import (
    ListView, DetailView, CreateView, 
    UpdateView, DeleteView, TemplateView
)
from django.contrib.auth.mixins import LoginRequiredMixin
from django.urls import reverse_lazy

class PostListView(ListView):
    model = Post
    template_name = 'posts/list.html'
    context_object_name = 'posts'
    paginate_by = 10
    ordering = ['-created_at']
    
    def get_queryset(self):
        queryset = super().get_queryset()
        return queryset.filter(is_published=True)
    
    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['title'] = 'Post List'
        return context

class PostDetailView(DetailView):
    model = Post
    template_name = 'posts/detail.html'
    context_object_name = 'post'

class PostCreateView(LoginRequiredMixin, CreateView):
    model = Post
    fields = ['title', 'content']
    template_name = 'posts/form.html'
    success_url = reverse_lazy('post_list')
    
    def form_valid(self, form):
        form.instance.author = self.request.user
        return super().form_valid(form)

class PostUpdateView(LoginRequiredMixin, UpdateView):
    model = Post
    fields = ['title', 'content']
    template_name = 'posts/form.html'
    
class PostDeleteView(LoginRequiredMixin, DeleteView):
    model = Post
    success_url = reverse_lazy('post_list')
    template_name = 'posts/confirm_delete.html'
```

## URLs

### URL 패턴

```python
# project/urls.py
from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('admin/', admin.site.urls),
    path('accounts/', include('django.contrib.auth.urls')),
    path('blog/', include('blog.urls', namespace='blog')),
    path('api/', include('api.urls')),
]

if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
    urlpatterns += static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)

# app/urls.py
from django.urls import path
from . import views

app_name = 'blog'

urlpatterns = [
    path('', views.PostListView.as_view(), name='post_list'),
    path('<int:pk>/', views.PostDetailView.as_view(), name='post_detail'),
    path('<slug:slug>/', views.post_by_slug, name='post_slug'),
    path('create/', views.PostCreateView.as_view(), name='post_create'),
    path('<int:pk>/update/', views.PostUpdateView.as_view(), name='post_update'),
    path('<int:pk>/delete/', views.PostDeleteView.as_view(), name='post_delete'),
]
```

## Templates

### 템플릿 문법

```django
{# 주석 #}

{# 변수 출력 #}
{{ variable }}
{{ variable|default:"기본값" }}

{# 필터 #}
{{ text|lower }}
{{ text|upper }}
{{ text|title }}
{{ text|truncatewords:30 }}
{{ text|truncatechars:100 }}
{{ text|linebreaks }}
{{ text|safe }}
{{ value|add:10 }}
{{ date|date:"Y-m-d" }}
{{ number|floatformat:2 }}
{{ list|length }}
{{ list|first }}
{{ list|last }}
{{ list|join:", " }}

{# 템플릿 태그 #}
{% if condition %}
    ...
{% elif other_condition %}
    ...
{% else %}
    ...
{% endif %}

{% for item in items %}
    {{ forloop.counter }}
    {{ forloop.counter0 }}
    {{ forloop.first }}
    {{ forloop.last }}
{% empty %}
    No items
{% endfor %}

{% url 'app:view_name' pk=object.pk %}
{% csrf_token %}
{% load static %}
{% static 'css/style.css' %}

{# 템플릿 상속 #}
{# base.html #}
<!DOCTYPE html>
<html>
<head>
    <title>{% block title %}Default Title{% endblock %}</title>
    {% block extra_css %}{% endblock %}
</head>
<body>
    {% block content %}
    {% endblock %}
    {% block extra_js %}{% endblock %}
</body>
</html>

{# child.html #}
{% extends 'base.html' %}
{% load static %}

{% block title %}Post List{% endblock %}

{% block content %}
    <h1>Posts</h1>
    {% include 'partials/post_list.html' %}
{% endblock %}
```

## Forms

### Django Forms

```python
from django import forms
from django.core.validators import MinLengthValidator
from .models import Post

class PostForm(forms.Form):
    title = forms.CharField(
        max_length=200,
        required=True,
        widget=forms.TextInput(attrs={
            'class': 'form-control',
            'placeholder': '제목을 입력하세요'
        }),
        validators=[MinLengthValidator(5)]
    )
    content = forms.CharField(
        widget=forms.Textarea(attrs={
            'class': 'form-control',
            'rows': 10
        })
    )
    email = forms.EmailField()
    date = forms.DateField(widget=forms.DateInput(attrs={'type': 'date'}))
    category = forms.ChoiceField(choices=[('1', 'Tech'), ('2', 'Life')])
    tags = forms.MultipleChoiceField(
        choices=[('django', 'Django'), ('python', 'Python')],
        widget=forms.CheckboxSelectMultiple
    )
    
    def clean_title(self):
        title = self.cleaned_data.get('title')
        if 'bad' in title.lower():
            raise forms.ValidationError('부적절한 단어가 포함되어 있습니다.')
        return title
    
    def clean(self):
        cleaned_data = super().clean()
        # 전체 폼 검증
        return cleaned_data

class PostModelForm(forms.ModelForm):
    class Meta:
        model = Post
        fields = ['title', 'content', 'category', 'tags']
        # fields = '__all__'
        # exclude = ['author']
        widgets = {
            'title': forms.TextInput(attrs={'class': 'form-control'}),
            'content': forms.Textarea(attrs={'class': 'form-control'})
        }
        labels = {
            'title': '제목',
            'content': '내용'
        }
        help_texts = {
            'title': '제목을 입력하세요'
        }
```

### 뷰에서 폼 처리

```python
def post_create(request):
    if request.method == 'POST':
        form = PostForm(request.POST, request.FILES)
        if form.is_valid():
            # 처리
            title = form.cleaned_data['title']
            # 또는 ModelForm인 경우
            post = form.save(commit=False)
            post.author = request.user
            post.save()
            form.save_m2m()  # ManyToMany 저장
            return redirect('post_list')
    else:
        form = PostForm()
    
    return render(request, 'posts/form.html', {'form': form})
```

## Admin

### Admin 커스터마이징

```python
from django.contrib import admin
from .models import Post, Category, Tag

@admin.register(Post)
class PostAdmin(admin.ModelAdmin):
    list_display = ['title', 'author', 'is_published', 'created_at']
    list_filter = ['is_published', 'created_at', 'category']
    search_fields = ['title', 'content', 'author__username']
    prepopulated_fields = {'slug': ('title',)}
    raw_id_fields = ['author']
    date_hierarchy = 'created_at'
    ordering = ['-created_at']
    
    fieldsets = (
        ('기본 정보', {
            'fields': ('title', 'slug', 'author')
        }),
        ('내용', {
            'fields': ('content', 'image')
        }),
        ('메타데이터', {
            'fields': ('category', 'tags', 'is_published'),
            'classes': ('collapse',)
        }),
    )
    
    actions = ['make_published', 'make_draft']
    
    def make_published(self, request, queryset):
        queryset.update(is_published=True)
    make_published.short_description = '선택한 포스트를 발행'
    
    def get_queryset(self, request):
        qs = super().get_queryset(request)
        if request.user.is_superuser:
            return qs
        return qs.filter(author=request.user)

# Inline Admin
class CommentInline(admin.TabularInline):  # 또는 StackedInline
    model = Comment
    extra = 1
    
class PostAdmin(admin.ModelAdmin):
    inlines = [CommentInline]
```

## Settings

### 주요 설정

```python
# settings.py

# 기본 설정
DEBUG = True
SECRET_KEY = 'your-secret-key'
ALLOWED_HOSTS = ['localhost', '127.0.0.1', '.mydomain.com']

# 앱 설정
INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    # Third-party apps
    'rest_framework',
    'corsheaders',
    # Local apps
    'blog.apps.BlogConfig',
]

# 미들웨어
MIDDLEWARE = [
    'django.middleware.security.SecurityMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    'corsheaders.middleware.CorsMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
]

# 데이터베이스
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.postgresql',
        'NAME': 'mydb',
        'USER': 'myuser',
        'PASSWORD': 'mypassword',
        'HOST': 'localhost',
        'PORT': '5432',
    }
}

# 정적 파일
STATIC_URL = '/static/'
STATIC_ROOT = BASE_DIR / 'staticfiles'
STATICFILES_DIRS = [BASE_DIR / 'static']

# 미디어 파일
MEDIA_URL = '/media/'
MEDIA_ROOT = BASE_DIR / 'media'

# 인증
AUTH_USER_MODEL = 'accounts.CustomUser'
LOGIN_URL = '/accounts/login/'
LOGIN_REDIRECT_URL = '/'
LOGOUT_REDIRECT_URL = '/'

# 국제화
LANGUAGE_CODE = 'ko-kr'
TIME_ZONE = 'Asia/Seoul'
USE_I18N = True
USE_TZ = True

# 이메일
EMAIL_BACKEND = 'django.core.mail.backends.smtp.EmailBackend'
EMAIL_HOST = 'smtp.gmail.com'
EMAIL_PORT = 587
EMAIL_USE_TLS = True
EMAIL_HOST_USER = 'your-email@gmail.com'
EMAIL_HOST_PASSWORD = 'your-password'

# 캐시
CACHES = {
    'default': {
        'BACKEND': 'django.core.cache.backends.redis.RedisCache',
        'LOCATION': 'redis://127.0.0.1:6379/1',
    }
}

# 세션
SESSION_ENGINE = 'django.contrib.sessions.backends.db'
SESSION_COOKIE_AGE = 1209600  # 2주
SESSION_COOKIE_SECURE = True  # HTTPS only
SESSION_COOKIE_HTTPONLY = True

# CORS
CORS_ALLOWED_ORIGINS = [
    "http://localhost:3000",
    "https://example.com",
]
```

## 인증 및 권한

### 사용자 인증

```python
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required, permission_required
from django.contrib.auth.models import User, Group, Permission

# 로그인
def login_view(request):
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']
        user = authenticate(request, username=username, password=password)
        if user is not None:
            login(request, user)
            return redirect('home')
    return render(request, 'login.html')

# 로그아웃
def logout_view(request):
    logout(request)
    return redirect('login')

# 회원가입
def signup_view(request):
    if request.method == 'POST':
        user = User.objects.create_user(
            username=request.POST['username'],
            email=request.POST['email'],
            password=request.POST['password']
        )
        login(request, user)
        return redirect('home')
    return render(request, 'signup.html')

# 데코레이터
@login_required
def profile_view(request):
    return render(request, 'profile.html')

@permission_required('blog.add_post')
def create_post(request):
    pass

# CBV에서 믹신 사용
from django.contrib.auth.mixins import LoginRequiredMixin, PermissionRequiredMixin

class PostCreateView(LoginRequiredMixin, PermissionRequiredMixin, CreateView):
    permission_required = 'blog.add_post'
    login_url = '/login/'
```

## 시그널 (Signals)

```python
from django.db.models.signals import pre_save, post_save, pre_delete, post_delete
from django.dispatch import receiver
from django.core.signals import request_started, request_finished
from .models import Post

@receiver(post_save, sender=Post)
def post_saved(sender, instance, created, **kwargs):
    if created:
        # 새 포스트 생성 시
        print(f"New post created: {instance.title}")
    else:
        # 기존 포스트 수정 시
        print(f"Post updated: {instance.title}")

# 또는 connect 방법
def post_saved_handler(sender, instance, created, **kwargs):
    pass

post_save.connect(post_saved_handler, sender=Post)

# 커스텀 시그널
from django.dispatch import Signal

post_published = Signal()

# 시그널 발생
post_published.send(sender=Post, instance=post)
```

## 미들웨어

```python
# middleware.py
class CustomMiddleware:
    def __init__(self, get_response):
        self.get_response = get_response
        
    def __call__(self, request):
        # 요청 전 처리
        request.custom_data = 'some_value'
        
        response = self.get_response(request)
        
        # 응답 후 처리
        response['X-Custom-Header'] = 'value'
        
        return response
    
    def process_view(self, request, view_func, view_args, view_kwargs):
        # 뷰 실행 전
        pass
    
    def process_exception(self, request, exception):
        # 예외 처리
        pass
```

## 테스트

```python
from django.test import TestCase, Client
from django.urls import reverse
from django.contrib.auth.models import User
from .models import Post

class PostTestCase(TestCase):
    def setUp(self):
        self.client = Client()
        self.user = User.objects.create_user(
            username='testuser',
            password='testpass'
        )
        self.post = Post.objects.create(
            title='Test Post',
            content='Test Content',
            author=self.user
        )
    
    def test_post_list_view(self):
        response = self.client.get(reverse('blog:post_list'))
        self.assertEqual(response.status_code, 200)
        self.assertContains(response, 'Test Post')
    
    def test_post_create(self):
        self.client.login(username='testuser', password='testpass')
        response = self.client.post(reverse('blog:post_create'), {
            'title': 'New Post',
            'content': 'New Content'
        })
        self.assertEqual(response.status_code, 302)
        self.assertTrue(Post.objects.filter(title='New Post').exists())
    
    def test_model_str(self):
        self.assertEqual(str(self.post), 'Test Post')
    
    def tearDown(self):
        # 정리 작업
        pass

# 테스트 실행
# python manage.py test
# python manage.py test app.tests.PostTestCase
# python manage.py test --parallel
```

## REST Framework 기본

```python
# serializers.py
from rest_framework import serializers
from .models import Post

class PostSerializer(serializers.ModelSerializer):
    author_name = serializers.CharField(source='author.username', read_only=True)
    
    class Meta:
        model = Post
        fields = ['id', 'title', 'content', 'author', 'author_name', 'created_at']
        read_only_fields = ['created_at']

# views.py
from rest_framework import viewsets, permissions
from rest_framework.decorators import action
from rest_framework.response import Response

class PostViewSet(viewsets.ModelViewSet):
    queryset = Post.objects.all()
    serializer_class = PostSerializer
    permission_classes = [permissions.IsAuthenticatedOrReadOnly]
    
    @action(detail=True, methods=['post'])
    def set_published(self, request, pk=None):
        post = self.get_object()
        post.is_published = True
        post.save()
        return Response({'status': 'published'})

# urls.py
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register(r'posts', PostViewSet)

urlpatterns = [
    path('api/', include(router.urls)),
]
```

## 유용한 팁

### 커스텀 매니저

```python
class PublishedManager(models.Manager):
    def get_queryset(self):
        return super().get_queryset().filter(is_published=True)

class Post(models.Model):
    objects = models.Manager()  # 기본 매니저
    published = PublishedManager()  # 커스텀 매니저
    
# 사용
Post.published.all()  # 발행된 포스트만
```

### 커스텀 명령어

```python
# management/commands/my_command.py
from django.core.management.base import BaseCommand

class Command(BaseCommand):
    help = 'My custom command'
    
    def add_arguments(self, parser):
        parser.add_argument('--option', type=str)
    
    def handle(self, *args, **options):
        self.stdout.write(self.style.SUCCESS('Command executed'))

# 실행: python manage.py my_command --option=value
```

### 환경변수 관리

```python
# .env 파일 사용 (python-decouple)
from decouple import config

SECRET_KEY = config('SECRET_KEY')
DEBUG = config('DEBUG', default=False, cast=bool)
DATABASE_URL = config('DATABASE_URL')
```

이 Cheat Sheet는 Django 개발에서 자주 사용되는 패턴과 코드들을 모아놓은 것입니다. 프로젝트 특성에 맞게 선택적으로 활용하시면 됩니다.