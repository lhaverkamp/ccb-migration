package us.haverkamp.ccb.dao;

import java.util.List;

import us.haverkamp.ccb.domain.Campus;

public abstract class CampusDAO extends GenericDAO<Campus> {
	public List<Campus> findBy() throws DataAccessException {
		final String xml = get("campus_list");
		
		return getItems(xml);
	}
		
	public abstract int[] update(List<Campus> items) throws DataAccessException;

	@Override
	protected Campus getItem(String xml) throws DataAccessException {
		return Mapper.getCampus(xml);
	}

	@Override
	protected List<Campus> getItems(String xml) throws DataAccessException {
		return Mapper.getCampuses(xml);
	}
}