package zhiman.ustc.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import sc.ustc.dao.BaseDAO;

public class UserDAOs extends BaseDAO{

	public UserDAOs (
			String deriver,
			String url,
			String userName,
			String password ) {
		
		setDeriver(deriver);
		setUrl(url);
		setUserName(userName);
		setPassword(password);
	}

	@Override
	public boolean delete(String sql) {
		return false;
	}

	@Override
	public boolean insert(String sql) {
		return false;
	}

	@Override
	public Object query(String sql) {
		CachedRowSet rowSet = null;
		try (	
				Connection c = openDBConnection(); 
				PreparedStatement ps = c.prepareStatement(sql);
			) {
			
			ResultSet rs = ps.executeQuery();
			
			//ResultSet随着数据库连接断开而无法使用，rowSet可以离线使用
			RowSetFactory factory = RowSetProvider.newFactory();  
			rowSet = factory.createCachedRowSet(); 
			rowSet.populate(rs);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return rowSet;
	}

	@Override
	public boolean update(String sql) {
		return false;
	}

}
