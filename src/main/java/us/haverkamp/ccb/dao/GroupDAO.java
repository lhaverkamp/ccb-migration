package us.haverkamp.ccb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.haverkamp.ccb.domain.Group;

public class GroupDAO extends GenericDAO<Group> {
	private static final String SQL_INSERT = "INSERT INTO `group`(id, name, description) VALUES(?) ON DUPLICATE KEY UPDATE name = ?, description = ?";

	public List<Group> findBy() throws DataAccessException {
		final String xml = get("group_profiles&include_participants=false");
		
		return getItems(xml);
	}
	
	public Group findBy(Long id) throws NoDataFoundException, DataAccessException {
		// TODO could be done via group_profiles and using a map like the by name works
		final String xml = get("group_profile_from_id&id=" + id);
		
		return getItem(xml);
	}
	
	public Group findBy(String name) throws NoDataFoundException, DataAccessException {
		final Map<String, Group> groups = new HashMap<String, Group>();
		
		for(Group group : findBy()) {
			groups.put(group.getName(), group);
		}
		
		return groups.get(name);
	}
	
	protected Group getItem(String xml) throws DataAccessException {
		return Mapper.getGroup(xml);
	}
	
	protected List<Group> getItems(String xml) throws DataAccessException {
		return Mapper.getGroups(xml);
	}
	
	public int[] update(List<Group> items) throws DataAccessException {
		try {
			final Connection connection = getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				
				try {
					for(Group item : items) {
						int i = 0;
						ps.setLong(i++, item.getId());
						
						for(int y=0;y<2;y++) {
							ps.setString(i++, item.getName());
							ps.setString(i++,  item.getDescription());
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
