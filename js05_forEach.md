<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>forEach 사용법</title>
	<script type="text/javascript">
		function choiceMenu() {
			let str = "";
			document.querySelectorAll(".menu").forEach((item) => {
				if (item.checked) str += item.value + " ";
				item.checked = false;
			});
			
			if (str === "") 
				alert("하나 이상의 메뉴는 선택해주세요");
			else
				alert(str);
		}
		
		// Lunch Choice 버튼을 클릭하면 main-menu 영역에 있는 id 속성값을 출력
		const onload = () => {
			document.querySelectorAll(".menu").forEach((item) => {
				item.onclick = () => {
					let id = this.closest('.main-menu').attribute['id'].value;
					alert(id);
				};
			});
		};
		// Dinner Choice 버튼을 클릭하면 main-menu 영역에 있는 id 속성값을 출력
	</script>
</head>
<body>
	<h2>CheckBox Form</h2>
	<form action="#" name="frm">
		<div class="main-menu" id="lunch">		
			<h3>내일 점심 메뉴를 모두 선택하세요.</h3>
			<input type="checkbox" name="menu" value="김치찌개" class="menu" />김치찌개<br />
			<input type="checkbox" name="menu" value="돈가쓰" class="menu" />왕돈가쓰<br />
			<input type="checkbox" name="menu" value="카레라이스" class="menu" />인도카레<br />
			<input type="checkbox" name="menu" value="비빔밥" class="menu" />비빔밥<br />
			<input type="button" value="메뉴선택" onclick="choiceMenu()" />
			
			<button class="menus">Lunch Choice</button>
		</div>
		<div class="main-menu" id="dinner">		
			<h3>내일 저녁 메뉴를 모두 선택하세요.</h3>
			<input type="checkbox" name="menu" value="김치찌개" class="menu" />김치찌개<br />
			<input type="checkbox" name="menu" value="돈가쓰" class="menu" />왕돈가쓰<br />
			<input type="checkbox" name="menu" value="카레라이스" class="menu" />인도카레<br />
			<input type="checkbox" name="menu" value="비빔밥" class="menu" />비빔밥<br />
			<input type="button" value="메뉴선택" onclick="choiceMenu()" />
			
			<button class="menus">Dinner Choice</button>
		</div>
	</form>
</body>
</html>