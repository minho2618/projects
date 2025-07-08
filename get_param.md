<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>get</title>
    <style>
      button{
        height: 26px
      }
    </style>
  </head>
  <body>

	<h4>By Link</h4>
	<a href="params?job=insert&pageNo=10&searchWord=kosa">get link</a>
	
	<h4>By Button & JavaScript</h4>
	<button id="btnSendGet">Get 전송</button>
    <script>
    window.onload = ()=>{    	
       
        document.querySelector("#btnSendGet").onclick = ()=>{
          // Get 전송 코드를 작성하세요.(위와 동일한 곳으로 동일한 값을 전송하는 코드)
        };

      };
    </script>
  </body>
</html>
