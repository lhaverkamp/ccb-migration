package us.haverkamp.ccb.dao;

import java.util.List;

import us.haverkamp.ccb.domain.Family;

public abstract class FamilyDAO extends GenericDAO<Family> {
	public List<Family> findBy() throws DataAccessException {
		final String xml = get("family_list");
		
		return getItems(xml);
	}
	
	public abstract int[] update(List<Family> items) throws DataAccessException;
	
	@Override
	protected Family getItem(String xml) throws DataAccessException {
		return Mapper.getFamily(xml);
	}

	@Override
	protected List<Family> getItems(String xml) throws DataAccessException {
		return Mapper.getFamilies(xml);
	}
}
