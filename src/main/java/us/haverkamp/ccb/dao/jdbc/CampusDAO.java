package us.haverkamp.ccb.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Campus;
import us.haverkamp.utils.SQLUtils;

public class CampusDAO extends us.haverkamp.ccb.dao.CampusDAO {
	private static final String SQL_INSERT = 
		"INSERT INTO campus(id, name) "
		+ "VALUES(?, ?) "
		+ "ON DUPLICATE KEY UPDATE "
		+ "name = ?"; 
	
	public List<Campus> findBy() throws DataAccessException {
		final String xml = get("campus_list");
		
		return getItems(xml);
	}
	
	public int[] update(List<Campus> items) throws DataAccessException {
		try {
			final Connection connection = SQLUtils.getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				
				try {
					for(Campus item : items) {
						int i = 1;
						
						ps.setLong(i++, item.getId());
						
						for(int y=0;y<2;y++) { // set once for insert and once for update
							ps.setString(i++, item.getName());
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
