package us.haverkamp.ccb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.haverkamp.ccb.domain.Group;
import us.haverkamp.utils.DateUtils;

public class GroupDAO extends GenericDAO<Group> {
	private static final String SQL_INSERT = 
		"INSERT INTO `group`("
		+ "id, "
		+ "name, "
		+ "description, "
		// TODO + "image,"
		+ "campus_id,"
		+ "main_leader_id,"
		+ "director_id,"
		+ "group_type_id,"
		+ "department_id,"
		+ "creator_id,"
		+ "modifier_id,"
		+ "created,"
		+ "modified"
		+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
		+ "ON DUPLICATE KEY UPDATE "
		+ "name = ?, "
		+ "description = ?, "
		// TODO + "image = ?,"
		+ "campus_id = ?,"
		+ "main_leader_id = ?,"
		+ "director_id = ?,"
		+ "group_type_id = ?,"
		+ "department_id = ?,"
		+ "creator_id = ?,"
		+ "modifier_id = ?,"
		+ "created = ?,"
		+ "modified = ?";

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
						int i = 1;
						ps.setLong(i++, item.getId()); // id
						
						for(int y=0;y<2;y++) {
							ps.setObject(i++, item.getName()); // name
							ps.setObject(i++,  item.getDescription()); // description
							// TODO image
							ps.setObject(i++, item.getCampus().getId()); // campus_id
							ps.setObject(i++,  item.getMainLeader().getId()); // main_leader_id
							ps.setObject(i++, item.getDirector() != null ? item.getDirector().getId() : null); // director_id
							ps.setObject(i++, item.getGroupType().getId()); // group_type_id
							//TODO ps.setObject(i++, item.getDepartment() != null ? item.getDepartment().getId() : null); // department_id
							ps.setObject(i++, null);
							ps.setObject(i++, item.getCreator().getId()); // creator_id
							ps.setObject(i++, item.getModifier().getId()); // modifier_id
							ps.setString(i++, DateUtils.toString(item.getCreated(), DateUtils.TIMESTAMP)); //created
							ps.setString(i++, DateUtils.toString(item.getModified(), DateUtils.TIMESTAMP)); // modified
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
