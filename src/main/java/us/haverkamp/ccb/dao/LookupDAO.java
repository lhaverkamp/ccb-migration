package us.haverkamp.ccb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import us.haverkamp.ccb.domain.MembershipType;
import us.haverkamp.ccb.domain.Selection;

public class LookupDAO extends GenericDAO<Selection> {
	private static final String SQL_INSERT =
			"INSERT INTO lookup_field_value(type, id, name, sort_order) "
			+ "VALUES(?, ?, ?, ?) "
			+ "ON DUPLICATE KEY "
			+ "UPDATE name = ?, sort_order = ?";

	private List<Selection> getSelections(String srv) throws DataAccessException {
		final String xml = get(srv);
		
		return Mapper.getSelections(xml);
	}
	
	public List<Selection> getAttendanceGroupings() throws DataAccessException {
		return getSelections("attendance_groupings_list");
	}
	
	public List<Selection> getDepartments() throws DataAccessException {
		return getSelections("department_list");
	}
	
	public List<Selection> getGroupTypes() throws DataAccessException {
		return getSelections("group_type_list");
	}
	
	public List<Selection> getMembershipTypes() throws DataAccessException {
		return getSelections("membership_type_list");
	}
	
	public List<Selection> getSchools() throws DataAccessException {
		return getSelections("school_list");
	}
	
	public List<Selection> getSchoolGrades() throws DataAccessException {
		return getSelections("school_grade_list");
	}
	
	public List<Selection> getUserDefinedPulldown1() throws DataAccessException {
		return getSelections("udf_ind_pulldown_1_list");
	}

	public List<Selection> getUserDefinedPulldown2() throws DataAccessException {
		return getSelections("udf_ind_pulldown_2_list");
	}

	public List<Selection> getUserDefinedPulldown3() throws DataAccessException {
		return getSelections("udf_ind_pulldown_3_list");
	}

	public List<Selection> getUserDefinedPulldown4() throws DataAccessException {
		return getSelections("udf_ind_pulldown_4_list");
	}

	public List<Selection> getUserDefinedPulldown5() throws DataAccessException {
		return getSelections("udf_ind_pulldown_5_list");
	}

	public List<Selection> getUserDefinedPulldown6() throws DataAccessException {
		return getSelections("udf_ind_pulldown_6_list");
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
	protected Selection getItem(String xml) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Selection> getItems(String xml) throws DataAccessException {
		return Mapper.getSelections(xml);
	}
}
