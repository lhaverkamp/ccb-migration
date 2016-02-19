package us.haverkamp.ccb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import us.haverkamp.ccb.domain.Event;
import us.haverkamp.ccb.domain.Individual;
import us.haverkamp.utils.DateUtils;

public class AttendanceDAO extends GenericDAO<Event> {
	private static final String SQL_INSERT = 
		"INSERT INTO attendance(individual_id, occurance, updated_date) " +
		"VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE occurance = ?, updated_date = ?";

	public int[] create(Event event) throws DataAccessException {
		try {
			final Connection connection = getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				
				try {
					final String occurance = DateUtils.toString(event.getDate(), DateUtils.TIMESTAMP); 
					final Date updated = new Date(System.currentTimeMillis());
					ps.setString(2, occurance);
					ps.setDate(3, updated);

					ps.setString(4, occurance);
					ps.setDate(5, updated);

					for(Individual individual : event.getAttendees()) {
						ps.setLong(1, individual.getId());
						
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
	
	public Event findBy(long id, java.util.Date date) throws NoDataFoundException, DataAccessException {
		final String xml = get("attendance_profile&id=" + id + "&occurrence=" + DateUtils.toString(date));
		
		return getItem(xml);
	}
	
	public List<Event> findBy(java.util.Date startDate, java.util.Date endDate) throws DataAccessException {
		final String xml = get("attendance_profiles&start_date=" + DateUtils.toString(startDate) + "&end_date=" + DateUtils.toString(endDate));
		
		return getItems(xml);
	}

	@Override
	protected Event getItem(String xml) throws DataAccessException {
		return Mapper.getEvent(xml);
	}

	@Override
	protected List<Event> getItems(String xml) throws DataAccessException {
		return Mapper.getEvents(xml);
	}
}
