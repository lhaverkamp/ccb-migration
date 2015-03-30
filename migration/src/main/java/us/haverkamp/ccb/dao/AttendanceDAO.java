package us.haverkamp.ccb.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import us.haverkamp.ccb.domain.Event;
import us.haverkamp.ccb.domain.Individual;
import us.haverkamp.utils.DateUtils;

public class AttendanceDAO extends GenericDAO<Event> {
	private static final String SQL_INSERT = 
		"INSERT INTO attendance(individual_id, occurance, updated_date) " +
		"VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE updated_date = ?";

	public int[] create(Event event) throws DataAccessException {
		try {
			final Connection connection = getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				
				try {
					ps.setDate(2, new Date(event.getDate().getTime()));
					final Date updated = new Date(System.currentTimeMillis());
					ps.setDate(3, updated);
					ps.setDate(4, updated);

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
	
	public Event findBy(long id, java.util.Date date) throws DataAccessException {
		final String xml = get("attendance_profile&id=" + id + "&occurrence=" + DateUtils.toString(date));
		
		try {
			return Mapper.getEvent(xml);
		} catch(IOException e) {
			throw new DataAccessException(e);
		} catch(SAXException e) {
			throw new DataAccessException(e);
		} catch(ParserConfigurationException e) {
			throw new DataAccessException(e);
		} catch(ParseException e) {
			throw new DataAccessException(e);
		}
	}
	
	@Override
	protected Event getItem(ResultSet rs) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
