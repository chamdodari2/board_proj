package board_proj.ds;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JndiDS {
	private static DataSource ds;

	private JndiDS() {
	} // 이렇게하는건 외부에서 생성하지못하게 !!

	static { // 처음본당. static블럭인데 이걸 젤먼저 수행하게된다. 그다음에 위에 private jndDs수행쓰
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/board_proj");
			System.out.println("ds : " + ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
