package us.haverkamp.ccb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import us.haverkamp.ccb.domain.Event;
import us.haverkamp.utils.DateUtils;

public class AttendanceDAO extends GenericDAO<Event> {
	private static final String SQL_INSERT = 
		"INSERT INTO attendance(individual_id, occurance, updated_date) "
		+ "VALUES(?, ?, ?) "
		+ "ON DUPLICATE KEY "
		+ "UPDATE occurance = ?, updated_date = ?";

	public List<Event> findBy() throws DataAccessException {
		final String xml = get("attendance_profiles");
		
		return getItems(xml);
	}
	
	public List<Event> findBy(java.util.Date startDate, java.util.Date endDate) throws DataAccessException {
		final String xml = get("attendance_profiles&start_date=" + DateUtils.toString(startDate) + "&end_date=" + DateUtils.toString(endDate));
		
		return getItems(xml);
	}
	
	public int[] update(List<Event> items) throws DataAccessException {
		try {
			final Connection connection = getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				
				try {
					for(Event item : items) {
						int i = 1;
						
						// TODO primary key
						
						for(int y=0;y<2;y++) {
							// TODO columns
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

	@Override
	protected Event getItem(String xml) throws DataAccessException {
		return Mapper.getEvent(xml);
	}

	@Override
	protected List<Event> getItems(String xml) throws DataAccessException {
		return Mapper.getEvents(xml);
	}
}
