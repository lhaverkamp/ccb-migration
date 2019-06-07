package us.haverkamp.ccb.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Event;
import us.haverkamp.utils.DateUtils;
import us.haverkamp.utils.SQLUtils;

public class EventDAO extends us.haverkamp.ccb.dao.EventDAO {
	public static final String SQL_INSERT = 
		"INSERT INTO event("
		+ "id, "
		+ "name, "
		+ "description, "
		+ "leader_notes, "
		+ "start_datetime, "
		+ "end_datetime, "
		+ "timezone, "
		// TODO + "recurrence_description, "
		// TODO + "approval_status_id, "
		+ "group_id, "
		+ "organizer_id,"
		// TODO + "phone_contact, "
		// TODO + "location_name, "
		// TODO + "location_street, "
		// TODO + "location_city, "
		// TODO + "location_state, "
		// TODO + "location_zip, "
		// TODO + "location_line_1, "
		// TODO + "location_line_2, "
		// TODO + "setup_start, "
		// TODO + "setup_end, "
		// TODO + "setup_notes, "
		+ "event_grouping_id, "
		+ "creator_id, "
		+ "modifier_id, "
		+ "listed, "
		+ "public_calendar_listed, "
		+ "created, "
		+ "modified "
		+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
		+ "ON DUPLICATE KEY UPDATE "
		+ "name = ?, "
		+ "description = ?, "
		+ "leader_notes = ?, "
		+ "start_datetime = ?, "
		+ "end_datetime = ?, "
		+ "timezone = ?, "
		// TODO + "recurrence_description = ?, "
		// TODO + "approval_status_id = ?, "
		+ "group_id = ?, "
		+ "organizer_id = ?,"
		// TODO + "phone_contact = ?, "
		// TODO + "location_name = ?, "
		// TODO + "location_street = ?, "
		// TODO + "location_city = ?, "
		// TODO + "location_state = ?, "
		// TODO + "location_zip = ?, "
		// TODO + "location_line_1 = ?, "
		// TODO + "location_line_2 = ?, "
		// TODO + "setup_start = ?, "
		// TODO + "setup_end = ?, "
		// TODO + "setup_notes = ?, "
		+ "event_grouping_id = ?, "
		+ "creator_id = ?, "
		+ "modifier_id = ?, "
		+ "listed = ?, "
		+ "public_calendar_listed = ?, "
		+ "created = ?, "
		+ "modified = ?";
	
	public int[] update(List<Event> items) throws DataAccessException {
		try {
			final Connection connection = SQLUtils.getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				
				try {
					for(Event item : items) {
						int i = 1;
						
						ps.setLong(i++, item.getId()); // id
						
						for(int y=0;y<2;y++) {
							ps.setObject(i++, item.getName()); // name
							ps.setObject(i++, item.getDescription()); // description
							ps.setObject(i++, item.getLeaderNotes()); // leader_notes 
							ps.setString(i++, DateUtils.toString(item.getStart(), DateUtils.TIMESTAMP)); // start_datetime 
							ps.setString(i++, DateUtils.toString(item.getEnd(), DateUtils.TIMESTAMP)); // end_datetime
							ps.setString(i++, item.getTimezone()); // timezone
							 // recurrence_description 
							 // approval_status_id 
							ps.setObject(i++, item.getGroup().getId()); // group_id
							ps.setObject(i++, item.getOrganizer().getId()); // organizer_id
							 // phone_contact 
							 // location_name 
							 // location_street 
							 // location_city
							 // location_state 
							 // location_zip
							 // location_line_1 
							 // location_line_2 
							 // setup_start
							 // setup_end
							 // setup_notes 
							ps.setObject(i++, item.getEventGrouping().getId()); // event_grouping_id 
							ps.setObject(i++, item.getCreator().getId()); // creator_id
							ps.setObject(i++, item.getModifier().getId()); // modifier_id
							ps.setObject(i++, item.getListed()); // listed
							ps.setObject(i++, item.getPublicCalendarListed()); // public_calendar_listed 
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
