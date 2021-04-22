package board_proj;

public class TestMain {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String className ="board_proj.Sum";
		
		
		//1.jdbc 연결할때도 이방법 썼었음
		//객체생성의 두가지 방법
		//방법1
		Sum s2 = new Sum();
		s2.add(5, 2);
		//방법2
		Class<?> cls = Class.forName(className);		//JVM voard_proj.Sum 클래스를 로드하라는뜻
		Sum s = (Sum)cls.newInstance();   //board_proj.Sum 를 인스턴스화
		s.add(5,3);
		
	}

}
