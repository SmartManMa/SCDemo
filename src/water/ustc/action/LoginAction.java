package water.ustc.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sc.ustc.scinterface.Action;
import zhiman.ustc.bean.UserBean;

public class LoginAction implements Action {
	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
		String name = request.getParameter("userID");
		String pwd = request.getParameter("password");
		if ("admin".equals(name) && "123".equals(pwd)) {
			return SUCCESS;
		} else {
			return "failuer";
		}
	}
	public String handleLogin(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException, SQLException {
		String name = request.getParameter("userID");
		String pwd = request.getParameter("password");
		System.out.println(name+"----------"+pwd);
		UserBean user = new UserBean();
		user.setUserName(name);
		user.setUserPass(pwd);
		boolean isSuccess = user.signIn();
		if ( isSuccess ) {
			return SUCCESS;
		} else 
			return FAILUER;
	}

}
