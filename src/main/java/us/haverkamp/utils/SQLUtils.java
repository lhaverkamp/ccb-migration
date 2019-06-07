package us.haverkamp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import us.haverkamp.ccb.dao.DataAccessException;

public class SQLUtils {
	public static final Connection getConnection() throws DataAccessException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		
			return DriverManager.getConnection("jdbc:mysql://localhost/ccb", "root", "");
		} catch(ClassNotFoundException e) {
			throw new DataAccessException(e);
		} catch(IllegalAccessException e) {
			throw new DataAccessException(e);
		} catch (InstantiationException e) {
			throw new DataAccessException(e);
		} catch(SQLException e) {
			throw new DataAccessException(e);
		}
	}
}
