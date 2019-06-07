package us.haverkamp.ccb.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Transaction;
import us.haverkamp.utils.SQLUtils;

public class TransactionDAO extends us.haverkamp.ccb.dao.TransactionDAO {
	private static final String SQL_INSERT = "";
	
	public int[] update(List<Transaction> items) throws DataAccessException {
		try {
			final Connection connection = SQLUtils.getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				
				try {
					for(Transaction item : items) {
						int i = 1;
						
						// TODO ps.setLong(i++, item.getId());
						
						for(int y=0;y<2;y++) {
							// TODO auto-generated method stub
						}
						
						ps.addBatch();
					}
					
					return ps.executeBatch();
				} finally {
					ps.close();
				}
			} finally {
				connection.close();
			}
		} catch(SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public int[] update(List<Transaction> items, boolean append) throws DataAccessException {
		return update(items);
	}
}
