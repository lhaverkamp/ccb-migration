package us.haverkamp.ccb.dao;

import java.util.List;

import us.haverkamp.ccb.domain.Selection;

public abstract class LookupDAO extends GenericDAO<Selection> {
	private List<Selection> getSelections(String srv) throws DataAccessException {
		final String xml = get(srv);
		
		return Mapper.getSelections(xml);
	}
	
	public List<Selection> getEventGroupings() throws DataAccessException {
		return getSelections("event_grouping_list");
	}
	
	public List<Selection> getDepartments() throws DataAccessException {
		return getSelections("group_grouping_list");
	}
	
	public List<Selection> getGroupTypes() throws DataAccessException {
		return getSelections("group_type_list");
	}
	
	public List<Selection> getHowJoinedChurch() throws DataAccessException {
		return getSelections("how_joined_church_list");
	}
	
	public List<Selection> getHowTheyHeard() throws DataAccessException {
		return getSelections("how_they_heard_list");
	}
	
	public List<Selection> getMembershipTypes() throws DataAccessException {
		return getSelections("membership_type_list");
	}
	
	public List<Selection> getReasonLeftChurch() throws DataAccessException {
		return getSelections("reason_left_church_list");
	}
	
	public List<Selection> getSchools() throws DataAccessException {
		return getSelections("school_list");
	}
	
	public List<Selection> getTransactionGrouping() throws DataAccessException {
		return getSelections("transaction_grouping");
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
	
	public abstract int[] update(List<Selection> fields, String table) throws DataAccessException;
	
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
