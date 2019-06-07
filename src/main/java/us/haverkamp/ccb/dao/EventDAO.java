package us.haverkamp.ccb.dao;

import java.util.List;

import us.haverkamp.ccb.domain.Event;

public abstract class EventDAO extends GenericDAO<Event> {
	public List<Event> findBy() throws DataAccessException {
		final String xml = get("event_profiles");
				
		return getItems(xml);
	}
	
	public abstract int[] update(List<Event> items) throws DataAccessException;
	
	@Override
	protected Event getItem(String xml) throws DataAccessException {
		return Mapper.getEvent(xml);
	}

	@Override
	protected List<Event> getItems(String xml) throws DataAccessException {
		return Mapper.getEvents(xml);
	}
}
