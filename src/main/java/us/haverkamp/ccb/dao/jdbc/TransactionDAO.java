package us.haverkamp.ccb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import us.haverkamp.ccb.domain.Transaction;

public class TransactionDAO extends GenericDAO<Transaction> {
	private static final String SQL_INSERT = "";
	
	public List<Transaction> findBy() {
		// TODO auto-generated method stub
		return new ArrayList<Transaction>();
	}
	
	public int[] update(List<Transaction> items) throws DataAccessException {
		try {
			final Connection connection = getConnection();
			
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

	@Override
	protected Transaction getItem(String xml) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Transaction> getItems(String xml) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
