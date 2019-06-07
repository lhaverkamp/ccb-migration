package us.haverkamp.ccb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.haverkamp.ccb.domain.Group;

public abstract class GroupDAO extends GenericDAO<Group> {
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
	
	public abstract int[] update(List<Group> items) throws DataAccessException;
}
