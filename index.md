<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>KOSA DVD</title>
<link rel="stylesheet" href="./css/layout.css" type="text/css" media="screen" />
<style type="text/css">
table { float : left ; 
		padding-left: 20px;
}

table caption{
	font-size: 24px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//1. 미리보기 기능...
	
	
	
});
</script>
</head>
<body>
	<header>
		<h1>KOSA DVD</h1>
	</header>
	<nav>
		<ul>
			<li class="selected"><a href="#">최신 DVD</a></li>
			<li class="selected"><a href="#">DVD 목록</a></li>
			<li class="selected"><a href="jimList.html">찜 리스트</a></li>
		</ul>
	</nav>
	<section id="intro">
		<header>
			<h2>무공해 힐링 시네마</h2>
		</header>
		<p>무공해 힐링 시네마는 새로운 개념의 영화장르의 한가지를 말하는 것입니다. 지친 영혼을 위한 휴식을 주는 영화나,
			마음을 정화시키고 치유하는 힘을 가진 영화들 ... .</p>
	</section>
	<div id="content">
			<article class="blogPost">
				<section>
					<video width="400" height="300" controls="controls" poster="img/poster.jpg" id="video">
					</video>
				</section>
				<section>
				<table>
					<tr>
					<td><h2>쿵푸팬더</h2><p/>
					<img src="img/movie_image2.jpg" /><p/>
					<button name="vplay">미리 보기</button>
					<button name="cartinsert">찜하기</button>
					</td>
					<td>
					<h2>어벤져스</h2><p/>
					<img  src="img/avengers.jpg" /><p/>
					<button name="vplay">미리 보기</button>
					<button name="cartinsert">찜하기</button>
					</td>
					<td>
					<h2>빅벅</h2><p/>
					<img  src="img/BigBuck.jpg" /><p/>
					<button name="vplay">미리 보기</button>
					<button name="cartinsert">찜하기</button>
					</td>
					</tr>
				</table>
				</section>
			</article>
	</div>
	<footer>
		<div>
			<section id="about">
				<h3>About</h3>
				<p>
					서울 대학로 지점. <br /> <a href="#">hsh@kosa.com</a>
				</p>
			</section>
			<section id="security">
				<h3>개인정보 취급 방침</h3>
				<ul>
					<li><a href="#">개요</a></li>
					<li><a href="#">문의처</a></li>
				</ul>
			</section>
			<section id="notice">
				<h3>공지사항</h3>
				<ul>
					<li><a href="#">오시는 길</a></li>
					<li><a href="#">오류시 문의사항</a></li>
				</ul>
			</section>
		</div>
	</footer>
</body>
</html>