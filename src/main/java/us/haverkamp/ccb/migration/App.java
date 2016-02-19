package us.haverkamp.ccb.migration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import us.haverkamp.ccb.dao.AttendanceDAO;
import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.dao.Factory;
import us.haverkamp.ccb.dao.FamilyDAO;
import us.haverkamp.ccb.dao.IndividualDAO;
import us.haverkamp.ccb.dao.LookupDAO;
import us.haverkamp.ccb.dao.NoDataFoundException;
import us.haverkamp.ccb.domain.Event;
import us.haverkamp.ccb.domain.Family;
import us.haverkamp.ccb.domain.Individual;

public class App {
	public void syncLookupTables() throws DataAccessException {
		final LookupDAO dao = Factory.getInstance().getLookupDAO();
		
		// TODO ability
		// TODO activity
		// TODO age_bracket
		// TODO church_service
		// TODO department
		// TODO event_grouping
		// TODO gift
		// TODO group_grouping
		// TODO group_type
		// TODO how_joined_church
		// TODO how_they_heard
		// TODO meet_day
		// TODO meet_time
		// TODO membership_type
		// TODO passion
		// TODO reason_left_church
		// TODO school
		// TODO school_grade
		// TODO significant_event
		// TODO spiritual_maturity
		// TODO style
		// TODO transaction_grouping
		// TODO udf_grp_pulldown_1
		// TODO udf_grp_pulldown_2
		// TODO udf_grp_pulldown_3
		dao.update(dao.getUserDefinedPulldown1()); // udf_ind_pulldown_1_list
		dao.update(dao.getUserDefinedPulldown2()); // udf_ind_pulldown_2_list
		dao.update(dao.getUserDefinedPulldown3()); // udf_ind_pulldown_3_list
		dao.update(dao.getUserDefinedPulldown4()); // udf_ind_pulldown_4_list
		dao.update(dao.getUserDefinedPulldown5()); // udf_ind_pulldown_5_list
		dao.update(dao.getUserDefinedPulldown6()); // udf_ind_pulldown_6_list
		// TODO udf_resource_pulldown_1
	}
	
	public void syncFamilies() throws DataAccessException {
		final FamilyDAO dao = Factory.getInstance().getFamilyDAO();
		final List<Family> families = dao.findBy();
		
		dao.update(families);
	}
	public void syncIndividuals() throws DataAccessException {
        final IndividualDAO dao = Factory.getInstance().getIndividualDAO();
        final List<Individual> individuals = dao.findBy();
        
        dao.update(individuals);
	}
	
	public void syncAttendance() throws DataAccessException {
		final AttendanceDAO dao = Factory.getInstance().getAttendanceDAO();
		
		final Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(
			calendar.getWeekYear(),
			calendar.get(Calendar.WEEK_OF_YEAR),
			Calendar.SUNDAY
		);
		final Date endDate = calendar.getTime();
		calendar.set(2015, 0, 1);
		final Date startDate = calendar.getTime();

		try {
			final List<Event> events = dao.findBy(startDate, endDate);
			System.out.println(events);
			for(Event event : events) {
				System.out.println(event);
				dao.create(event);
			}
		} catch(NoDataFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
    public static void main( String[] args ) throws DataAccessException {
    	final App app = new App();
    	
    	app.syncLookupTables();
    	app.syncFamilies();
    	app.syncIndividuals();
    	//app.syncGroups();
    	//app.syncAttendance(); // does Events
    	//app.syncTransactions();
    }
}
