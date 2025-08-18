package mybatis.services.user.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.services.domain.User;


/**
 * FileName : MyBATISTestApp.java
  * ㅇ JBDCTestApp.java 와 비교 이해.
  * ㅇ mybatis Framework 에서 제공하는 API을 사용 users table 의 정보 SELECT   
 */
public class MyBATISTestApp {
	///Main method
	public static void main(String[] args) throws Exception{
		
		Reader reader=Resources.getResourceAsReader("config/SqlMapConfig.xml");		
		
		//==> 2. Reader 객체를 이용 xml metadata 에 설정된 각정 정보를 접근, 사용가능한 
		//==>    읽어들인 reader를 바탕으로 SqlSessionFactory를 리턴받는다.
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(reader);
		
		//==> 3.factory의 openSession()을 통해서 SqlSession을 리턴 받는다.
		SqlSession session=factory.openSession();
		
		List<User> list=session.selectList("UserMapper.getUserList");
		System.out.println("#####################################");
		System.out.println(":: 회원정보 출력");
		
		for (User user : list) {
			System.out.println( user.toString() ) ;
		}
		System.out.println("#####################################");
	}// end of main
}//end of class

/*
   1. SqlMapConfig.xml을 읽어들인다.
   2. SqlSessionFactory 생성
   3. SqlSession 생성
      -- 쿼리문을 실행하는 함수 동작...
          <select>
          --> selectList()-->
          1)Connection 받아와서
          2)PreparedStatement 생성
          3)바인딩
          4)쿼리문 실행...ResultSet rs = executeQuery()  
          5)vo--> List
*/













