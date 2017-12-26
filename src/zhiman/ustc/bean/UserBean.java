package zhiman.ustc.bean;

import java.sql.SQLException;

import javax.sql.RowSet;


import zhiman.ustc.dao.UserDAO;

public class UserBean {
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
	private static String DB_NAME = "zhiman";
	private static String DB_PASS = "1234";
	
	private String userID;
	private String userName;
	private String userPass;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public boolean signIn() throws SQLException{

		String querySql = "select * from userlist where name = \""+userName+"\" " ;
		UserDAO dao = new UserDAO(DRIVER, DB_URL, DB_NAME, DB_PASS);
		RowSet rs = (RowSet) dao.query(querySql);
		//没有匹配的用户名，返回false
		if ( rs == null ) {
			return false;
		}
		while ( rs.next() ) {
			String name = rs.getString("name");
			String pass = rs.getString("password");
			//用户名密码匹配返回true
			if ( userName.equals(name) && userPass.equals(pass) ) {
				return true;
			}
		}
		//没有找到匹配的用户名密码，返回false
		return false;
	}
	
//	
//	public void TestSQL(){
//		try {
//			System.out.println(signIn());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
