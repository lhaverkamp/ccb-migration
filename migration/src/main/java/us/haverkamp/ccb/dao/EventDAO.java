package us.haverkamp.ccb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import us.haverkamp.ccb.domain.Event;

public class EventDAO extends GenericDAO<Event> {
	public static final String SQL_ALL =
		"SELECT " +
		"	event_id, " +
		"	event, " +
		"	TRIM(event) AS event_grouping, " +
		"	TRIM(occurance) AS start_date, " +
		"	TRIM(occurance) AS end_date, " +
		"	COUNT(*) AS attendance " +
		"FROM ss_attendance " +
		"GROUP BY event_id, event, occurance";
	
	private Map<String, Integer> eventGrouping;

	public int create(Event event) throws DataAccessException {
		try {
			// figure out some XML
			System.out.println(event.toXML());
		} catch(ParserConfigurationException e) {
			throw new DataAccessException(e);
		}
		
		// TODO
		return 200;
	}

	protected Event getItem(ResultSet rs) throws DataAccessException {
		try {
			final Event event = Mapper.getEvent(rs);
			event.setAttendees(Factory.getInstance().getIndividualDAO().findBy(event));
			
			return event;
		} catch(SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	private Map<String, Integer> getEventGrouping() throws DataAccessException {
		if(this.eventGrouping == null) {
			this.eventGrouping = getLookupTable("event_grouping_list");
		}
		
		return this.eventGrouping;
	}
}
