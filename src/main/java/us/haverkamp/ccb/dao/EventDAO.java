package us.haverkamp.ccb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import us.haverkamp.ccb.domain.Event;

public class EventDAO extends GenericDAO<Event> {
	public static final String SQL_INSERT = "";
	
	public List<Event> findBy() throws DataAccessException {
		// TODO auto-generated method stub
		return new ArrayList<Event>();
	}
	
	public int[] update(List<Event> items) throws DataAccessException {
		try {
			final Connection connection = getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				
				try {
					for(Event item : items) {
						int i = 1;
						
						ps.setLong(i++, item.getId());
						
						for(int y=0;y<2;y++) {
							// TODO
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
