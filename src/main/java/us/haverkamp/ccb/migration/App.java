package us.haverkamp.ccb.migration;

import java.text.ParseException;
import java.util.List;

import us.haverkamp.ccb.dao.AttendanceDAO;
import us.haverkamp.ccb.dao.CampusDAO;
import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.dao.EventDAO;
import us.haverkamp.ccb.dao.Factory;
import us.haverkamp.ccb.dao.FamilyDAO;
import us.haverkamp.ccb.dao.GroupDAO;
import us.haverkamp.ccb.dao.IndividualDAO;
import us.haverkamp.ccb.dao.LookupDAO;
import us.haverkamp.ccb.dao.Table;
import us.haverkamp.ccb.dao.TransactionDAO;
import us.haverkamp.ccb.domain.Campus;
import us.haverkamp.ccb.domain.Event;
import us.haverkamp.ccb.domain.Family;
import us.haverkamp.ccb.domain.Group;
import us.haverkamp.ccb.domain.Individual;
import us.haverkamp.ccb.domain.Occurrence;
import us.haverkamp.utils.DateUtils;

public class App {
	private int getType() {
		return Factory.CSV;
	}
	
	public void syncLookupTables() throws DataAccessException {
		final LookupDAO dao = Factory.getInstance(getType()).getLookupDAO();
		// TODO ability
		// TODO age_bracket
		// TODO area
		// TODO church_service
		dao.update(dao.getEventGroupings(), Table.EVENT_GROUPING); // event_grouping_list
		// TODO gift
		dao.update(dao.getDepartments(), Table.DEPARTMENT); // group_grouping_list
		dao.update(dao.getGroupTypes(), Table.GROUP_TYPE); // group_type_list
		dao.update(dao.getHowJoinedChurch(), Table.HOW_JOINED_CHURCH); // how_joined_church_list
		dao.update(dao.getHowTheyHeard(), Table.HOW_THEY_HEARD); // how_they_heard_list
		// TODO meet_day
		// TODO meet_time
		dao.update(dao.getMembershipTypes(), Table.MEMBERSHIP_TYPE); // membership_type_list
		// TODO passion
		dao.update(dao.getReasonLeftChurch(), Table.REASON_LEFT_CHURCH); // reason_left_church_list
		dao.update(dao.getSchools(), Table.SCHOOL); // school_list
		dao.update(dao.getSchoolGrades(), Table.SCHOOL_GRADE); // school_grade_list
		// TODO significant_event
		// TODO spiritual_maturity
		// TODO style
		dao.update(dao.getTransactionGrouping(),  Table.TRANSACTION_GROUPING); // transaction_grouping
		// TODO udf_grp_pulldown_1
		// TODO udf_grp_pulldown_2
		// TODO udf_grp_pulldown_3
		dao.update(dao.getUserDefinedPulldown1(), Table.UDF_IND_PULLDOWN_1); // udf_ind_pulldown_1_list
		dao.update(dao.getUserDefinedPulldown2(), Table.UDF_IND_PULLDOWN_2); // udf_ind_pulldown_2_list
		dao.update(dao.getUserDefinedPulldown3(), Table.UDF_IND_PULLDOWN_3); // udf_ind_pulldown_3_list
		dao.update(dao.getUserDefinedPulldown4(), Table.UDF_IND_PULLDOWN_4); // udf_ind_pulldown_4_list
		dao.update(dao.getUserDefinedPulldown5(), Table.UDF_IND_PULLDOWN_5); // udf_ind_pulldown_5_list
		dao.update(dao.getUserDefinedPulldown6(), Table.UDF_IND_PULLDOWN_6); // udf_ind_pulldown_6_list
		// TODO udf_resource_pulldown_1
	}
	
	public void syncFamilies() throws DataAccessException {
		final FamilyDAO dao = Factory.getInstance(getType()).getFamilyDAO();
		final List<Family> items = dao.findBy();
		
		dao.update(items);
	}
	public void syncIndividuals() throws DataAccessException {
        final IndividualDAO dao = Factory.getInstance(getType()).getIndividualDAO();
        final List<Individual> individuals = dao.findBy();
        
        dao.update(individuals);
	}
	
	public void syncCampuses() throws DataAccessException {
		final CampusDAO dao = Factory.getInstance(getType()).getCampusDAO();
		final List<Campus> items = dao.findBy();
		
		dao.update(items);
	}
	
	public void syncGroups() throws DataAccessException {
		final GroupDAO dao = Factory.getInstance(getType()).getGroupDAO();
		final List<Group> items = dao.findBy();
		
		dao.update(items);
		
		// TODO group_leader
		// TODO group_participant
	}
	
	public void syncEvents() throws DataAccessException {
		final EventDAO dao = Factory.getInstance(getType()).getEventDAO();
		final List<Event> items = dao.findBy();
		
		dao.update(items);
		
		final AttendanceDAO dao2 = Factory.getInstance(getType()).getAttendanceDAO();
		final List<Occurrence> items2 = dao2.findBy();
		
		dao2.update(items2);
	}
	
	public void syncTransactions() throws DataAccessException {
		final TransactionDAO dao = Factory.getInstance(getType()).getTransactionDAO();
		
		try {
			dao.update(dao.findBy(DateUtils.parseDate("2007-01-01"), DateUtils.parseDate("2007-12-31")));
			dao.update(dao.findBy(DateUtils.parseDate("2008-01-01"), DateUtils.parseDate("2008-12-31")), true);
			dao.update(dao.findBy(DateUtils.parseDate("2009-01-01"), DateUtils.parseDate("2009-12-31")), true);
			dao.update(dao.findBy(DateUtils.parseDate("2010-01-01"), DateUtils.parseDate("2010-12-31")), true);
			dao.update(dao.findBy(DateUtils.parseDate("2011-01-01"), DateUtils.parseDate("2011-12-31")), true);
			dao.update(dao.findBy(DateUtils.parseDate("2012-01-01"), DateUtils.parseDate("2012-12-31")), true);
			dao.update(dao.findBy(DateUtils.parseDate("2013-01-01"), DateUtils.parseDate("2013-12-31")), true);
			dao.update(dao.findBy(DateUtils.parseDate("2014-01-01"), DateUtils.parseDate("2014-12-31")), true);
			dao.update(dao.findBy(DateUtils.parseDate("2015-01-01"), DateUtils.parseDate("2015-12-31")), true);
			dao.update(dao.findBy(DateUtils.parseDate("2016-01-01"), DateUtils.parseDate("2016-12-31")), true);
			dao.update(dao.findBy(DateUtils.parseDate("2017-01-01"), DateUtils.parseDate("2017-12-31")), true);
			dao.update(dao.findBy(DateUtils.parseDate("2018-01-01"), DateUtils.parseDate("2018-12-31")), true);
			dao.update(dao.findBy(DateUtils.parseDate("2019-01-01"), DateUtils.parseDate("2019-12-31")), true);
		} catch(ParseException e) {
			throw new DataAccessException(e);
		}
	}
	
    public static void main( String[] args ) throws DataAccessException {
    	final App app = new App();
    	
    	app.syncCampuses();
    	app.syncLookupTables();
    	app.syncFamilies();
    	app.syncIndividuals();
    	app.syncGroups();
    	app.syncEvents();
    	app.syncTransactions();
    }
}
