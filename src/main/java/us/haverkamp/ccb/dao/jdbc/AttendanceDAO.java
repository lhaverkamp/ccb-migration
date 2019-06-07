package us.haverkamp.ccb.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.List;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Individual;
import us.haverkamp.ccb.domain.Occurrence;
import us.haverkamp.utils.DateUtils;
import us.haverkamp.utils.SQLUtils;

public class AttendanceDAO extends us.haverkamp.ccb.dao.AttendanceDAO {
	private static final String SQL_INSERT = 
		"INSERT INTO event_occurrence("
			+ "event_id, "
			+ "occurrence, "
			+ "did_not_meet, "
			+ "topic, "
			+ "notes, "
			+ "prayer_requests, "
			+ "info, "
			+ "head_count"
		+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?) "
		+ "ON DUPLICATE KEY "
		+ "UPDATE "
			+ "did_not_meet = ?,"
			+ "topic = ?,"
			+ "notes = ?,"
			+ "prayer_requests = ?,"
			+ "info = ?,"
			+ "head_count = ?";
	
	private static final String SQL_ATTENDANCE = 
		"INSERT INTO attendance("
			+ "event_id, "
			+ "occurrence, "
			+ "attendee_id"
		+ ") VALUES(?, ?, ?) "
		+ "ON DUPLICATE KEY "
		+ "UPDATE attendee_id = ?";
	
	/* (non-Javadoc)
	 * @see us.haverkamp.ccb.dao.jdbc.AttendanceDA#findBy()
	 */
	@Override
	public List<Occurrence> findBy() throws DataAccessException {
		// TODO need to figure out an appropriate default for start_date
		final String xml = get("attendance_profiles&start_date=2000-01-01&end_date=" + DateUtils.toString(Calendar.getInstance().getTime()));
		
		return getItems(xml);
	}
	
	/* (non-Javadoc)
	 * @see us.haverkamp.ccb.dao.jdbc.AttendanceDA#findBy(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Occurrence> findBy(java.util.Date startDate, java.util.Date endDate) throws DataAccessException {
		final String xml = get("attendance_profiles&start_date=" + DateUtils.toString(startDate) + "&end_date=" + DateUtils.toString(endDate));
		
		return getItems(xml);
	}
	
	/* (non-Javadoc)
	 * @see us.haverkamp.ccb.dao.jdbc.AttendanceDA#update(java.util.List)
	 */
	@Override
	public int[] update(List<Occurrence> items) throws DataAccessException {
		try {
			final Connection connection = SQLUtils.getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				final PreparedStatement ps2 = connection.prepareStatement(SQL_ATTENDANCE);
				
				try {
					for(Occurrence item : items) {
						int i = 1;
						
						ps.setLong(i++, item.getId()); // event_id
						ps.setString(i++, DateUtils.toString(item.getOccurrence(), DateUtils.TIMESTAMP)); // occurrence
						
						for(int y=0;y<2;y++) {
							ps.setBoolean(i++, item.getDidNotMeet()); // did_not_meet
							ps.setString(i++, item.getTopic()); // topic
							ps.setString(i++, item.getNotes()); // notes
							ps.setString(i++, item.getPrayerRequests()); // prayer_requests
							ps.setString(i++, item.getInfo()); // info
							ps.setNull(i++, Types.INTEGER); // TODO
							//ps.setInt(i++, item.getHeadCount()); // head_count
						}
						
						ps.addBatch();
						
						ps2.setLong(1, item.getId()); // event_id
						ps2.setString(2, DateUtils.toString(item.getOccurrence(), DateUtils.TIMESTAMP)); // occurrence
						
						for(Individual attendee : item.getAttendees()) {
							ps2.setLong(3, attendee.getId());
							ps2.setLong(4, attendee.getId());
							
							ps2.addBatch();
						}
					}
					
					// TODO need to figure out how to combine or simply return 1st
					//return ps.executeBatch();
					ps.executeBatch();
					
					return ps2.executeBatch();
				} finally {
					ps.close();
					ps2.close();
				}
			} finally {
				connection.close();
			}
		} catch(SQLException e) {
			throw new DataAccessException(e);
		}
	}
}
