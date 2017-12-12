package water.ustc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sc.ustc.scinterface.Action;
import zhiman.ustc.bean.UserBean;

public class LoginAction implements Action {
	private UserBean user;

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
	public String handleLogin() {
		return SUCCESS;
	}

}
