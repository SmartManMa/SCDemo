package zhiman.ustc.bean;

import java.sql.SQLException;
import javax.sql.RowSet;
import zhiman.ustc.dao.UserDAO;

public class UserBean {
	
	
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
		RowSet rs = (RowSet) new UserDAO().query();
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
}
