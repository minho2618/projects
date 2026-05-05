- **생성 패턴**

![[Pasted image 20250704170017.png]]

## Source
- Controller 인터페이스
```java
public interface Controller {

	String requestHandle(HttpServletRequest request, HttpServletResponse response);

}
```

- 컴포턴트 예시
```java
public class RegisterController implements Controller {

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response) {
/*
* MVC...Servlet...
* FrontController...register() {}
* 1. 폼 값 받아서
* 2. DAO 반환받아서 비지니스 로직 호출
* 3. 데이터바인딩
* 4. 내비게이션
*/
		System.out.println("RegisterController...DAO...registerMember call...");
		return "register_result.jsp";
	}

  

}
```

- Factory 예시
```java
// Controller를 생성하는 공장
public class ControllerFactory {
	private static ControllerFactory factory = new ControllerFactory();
	
	private ControllerFactory() { }

	public static ControllerFactory getInstance() {
		return factory;
	}

	public Controller createController(String command) {
		Controller controller = null;
		if (command.equals("REGISTER")) {
			controller = new RegisterController();
			System.out.println("RegisterController");
		} else if (command.equals("LOGIN")) {
			controller = new LoginController();
			System.out.println("LoginController");
		} else if (command.equals("FIND")) {
			controller = new FindController();
			System.out.println("FindController");
		} else if (command.equals("UPDATE")) {
			controller = new UpdateController();
			System.out.println("UpdateController");
		}

		return controller;
	}
}
```

