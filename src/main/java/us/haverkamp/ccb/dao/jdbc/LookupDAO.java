package us.haverkamp.ccb.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.LookupTable;
import us.haverkamp.ccb.domain.Selection;
import us.haverkamp.utils.SQLUtils;

public class LookupDAO extends us.haverkamp.ccb.dao.LookupDAO {
	private static final String SQL_INSERT =
		"INSERT INTO {0}(id, name, sort_order) "
		+ "VALUES(?, ?, ?) "
		+ "ON DUPLICATE KEY "
		+ "UPDATE name = ?, sort_order = ?";

	public int[] update(List<Selection> fields, String table) throws DataAccessException {
		try {
			final Connection connection = SQLUtils.getConnection();
			
			try {
				final String sql = MessageFormat.format(SQL_INSERT, table);
				final PreparedStatement ps = connection.prepareStatement(sql);
				
				try {
					for(LookupTable field : fields) {
						int i = 1;
						
						ps.setLong(i++, field.getId());
						ps.setString(i++, field.getName());
						ps.setInt(i++, field.getOrder());
						
						ps.setString(i++, field.getName());
						ps.setInt(i++, field.getOrder());
						
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
