package us.haverkamp.ccb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import us.haverkamp.ccb.domain.Selection;

public class LookupDAO extends GenericDAO {
	private static final String SQL_INSERT =
		"INSERT INTO udf_pulldown_field_value(type, id, name, sort_order) "
		+ "VALUES(?, ?, ?, ?) "
		+ "ON DUPLICATE KEY "
		+ "UPDATE name = ?, sort_order = ?";
	
	private List<Selection> getUserDefinedPulldown(String srv) throws DataAccessException {
		final String xml = get(srv);
		
		return Mapper.getSelections(xml);
	}

	public List<Selection> getUserDefinedPulldown1() throws DataAccessException {
		return getUserDefinedPulldown("udf_ind_pulldown_1_list");
	}

	public List<Selection> getUserDefinedPulldown2() throws DataAccessException {
		return getUserDefinedPulldown("udf_ind_pulldown_2_list");
	}

	public List<Selection> getUserDefinedPulldown3() throws DataAccessException {
		return getUserDefinedPulldown("udf_ind_pulldown_3_list");
	}

	public List<Selection> getUserDefinedPulldown4() throws DataAccessException {
		return getUserDefinedPulldown("udf_ind_pulldown_4_list");
	}

	public List<Selection> getUserDefinedPulldown5() throws DataAccessException {
		return getUserDefinedPulldown("udf_ind_pulldown_5_list");
	}

	public List<Selection> getUserDefinedPulldown6() throws DataAccessException {
		return getUserDefinedPulldown("udf_ind_pulldown_6_list");
	}
	
	public int[] update(List<Selection> fields) throws DataAccessException {
		try {
			final Connection connection = getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				
				try {
					for(Selection field : fields) {
						ps.setString(1, field.getType());
						ps.setLong(2, field.getId());
						ps.setString(3, field.getName());
						ps.setInt(4, field.getOrder());
						
						ps.setString(5, field.getName());
						ps.setInt(6, field.getOrder());
						
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
	protected Object getItem(String xml) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List getItems(String xml) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
