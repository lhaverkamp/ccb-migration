package us.haverkamp.ccb.dao;

import java.util.List;

import us.haverkamp.ccb.domain.Event;

public class EventDAO extends GenericDAO<Event> {
	public static final String SQL_INSERT = "";

	@Override
	protected Event getItem(String xml) throws DataAccessException {
		return Mapper.getEvent(xml);
	}

	@Override
	protected List<Event> getItems(String xml) throws DataAccessException {
		return Mapper.getEvents(xml);
	}
}
