
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>KOSA 영화 예매</title>
<style type="text/css">
	#first{
		width: 600px;
		height: 900px;
		margin: 0 auto;
		padding: 30px;
		border: 8px solid #007017;
	}
	h1,h2{
		text-align: center;
	}
	img{
		margin-left: 150px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#ilban').css('display','inline');
		$('#jojo').css('display','none');
		$('#option').css('display','none');
		$('#people').css('text-align','right');
		$('#price').css('text-align','right');

		//이벤트를 연결합니다..
		$('select').change(function(){
			var theater=$(this).val();
			// alert(theater);
		});
		var today = $('input[type=date').val()
		$('.time').click(function(){
			var time=$(this).val();
			if(time=='08:30'){
				$('#jojo').css('display','inline');
				$('#ilban').css('display','none');
				$('#cost').val(5000)
			}else{
				$('#jojo').css('display','none');
				$('#ilban').css('display','inline');
				$('#cost').val(9000)
			}
			eChange();
		});

		$('#op').change(function(){
			if(this.checked){
				$('#option').css('display','block')
			}else{
				$('#option').css('display','none')
			}
		});

		$('#people').change(function(){
			eChange();
		});

	});

	function eChange(){
		var count=$('#people').val();
		var cost = $('#cost').val()
		$('#price').val(count * cost)

		
	}
</script>
</head>
<body>
<div id="first">
<h1>KOSTA 영화 예매</h1>
<h2>인사이드 아웃2</h2>
<img width="300"  height="400" src="https://i.namu.wiki/i/_2rXSKS4AcZ0YQUuJwPfnJC40Pl8c96Mb2esJnv5USdbtcbu9kCPqo1DxhCo8hA55U4WiuZNaxm0XTe0H5RnSg.webp">
<p></p>

<form>
<label>상영관</label>
<select>
	<option>CGV</option>
	<option>롯데시네마</option>
	<option>메가박스</option>
	<option>프리머스</option>
	<option>다른극장</option>
</select>

<label>날짜 선택</label><input type="date"/>
<fieldset>
<legend size=30>시간선택</legend>
<input type="radio" name="time" value="08:30" class="time" /><label>08:30</label>
<input type="radio" name="time" value="11:00" class="time"  /><label>11:00</label>
<input type="radio" name="time" value="14:20" class="time"/><label>14:20</label>
<input type="radio" name="time" value="17:25" class="time"/><label>17:25</label>
<input type="radio" name="time" value="20:10" class="time"/><label>20:10</label>
<input type="radio" name="time" value="23:05" class="time" checked="checked"/><label>23:05</label>
</fieldset>
<p/>
<label>요금</label>&nbsp;&nbsp;<span id="jojo"> <del>W9,000</del>&nbsp;&nbsp; <ins>W5,000</ins></span><span id="ilban" >W9,000</span><p/>
<input type="hidden" id="cost" value="9000" />
<label>인원</label> <input type="number" required="required" id="people" value="0"/><p/>
<label>금액</label> <input type="number" readonly="readonly" id="price" value="0"/>
<p>
<input type="checkbox" id="op" checked="checked"/><label>옵션</label>
<div id="option">
<fieldset>
<input type="checkbox" name="popcorn"/><label>팝콘</label>
<input type="checkbox" name="cola"/><label>콜라</label>
</fieldset>
</div>
</form>
</body>
</div>
</html>