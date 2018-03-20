package zhiman.ustc.dao;
import sc.ustc.dao.Conversion;
import zhiman.ustc.bean.UserBean;

public class UserDAO{
	public Object query() {
		UserBean ub = new UserBean();
		ub.setUserID("17225250");
		return Conversion.getObject(ub);
	}
}
