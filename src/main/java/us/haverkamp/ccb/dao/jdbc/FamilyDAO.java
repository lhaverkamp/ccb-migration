package us.haverkamp.ccb.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Family;
import us.haverkamp.utils.DateUtils;
import us.haverkamp.utils.SQLUtils;

public class FamilyDAO extends us.haverkamp.ccb.dao.FamilyDAO {
	private static final String SQL_INSERT = "INSERT INTO family(id, name, modified) VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE name = ?, modified = ?"; 
	
	public int[] update(List<Family> items) throws DataAccessException {
		try {
			final Connection connection = SQLUtils.getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				
				try {
					for(Family item : items) {
						int i = 1;
						
						ps.setLong(i++, item.getId());
						
						for(int y=0;y<2;y++) { // set once for insert and once for update
							ps.setString(i++, item.getName());
							ps.setString(i++, DateUtils.toString(item.getModified(), DateUtils.TIMESTAMP));
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
}
