package us.haverkamp.ccb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import us.haverkamp.ccb.domain.Family;
import us.haverkamp.utils.DateUtils;

public class FamilyDAO extends GenericDAO<Family> {
	private static final String SQL_INSERT = "INSERT INTO family(id, name, modified) VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE name = ?, modified = ?"; 
	
	public List<Family> findBy() throws DataAccessException {
		final String xml = get("family_list");
		
		return getItems(xml);
	}
	
	public int[] update(List<Family> items) throws DataAccessException {
		try {
			final Connection connection = getConnection();
			
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

	@Override
	protected Family getItem(String xml) throws DataAccessException {
		return Mapper.getFamily(xml);
	}

	@Override
	protected List<Family> getItems(String xml) throws DataAccessException {
		return Mapper.getFamilies(xml);
	}
}
