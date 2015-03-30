package us.haverkamp.ccb.dao;

public class Factory {
	private AttendanceDAO attendanceDAO;
	private EventDAO eventDAO;
	private GroupDAO groupDAO;
	private IndividualDAO individualDAO;
	
	private Factory() {
		// private for factory method
	}
	
	public static Factory getInstance() {
		return new Factory();
	}
	
	public AttendanceDAO getAttendanceDAO() {
		if(this.attendanceDAO == null) {
			this.attendanceDAO = new AttendanceDAO();
		}
		
		return this.attendanceDAO;
	}
	
	public EventDAO getEventDAO() {
		if(this.eventDAO == null) {
			this.eventDAO = new EventDAO();
		}
		
		return this.eventDAO;
	}
	
	public GroupDAO getGroupDAO() {
		if(this.groupDAO == null) {
			this.groupDAO = new GroupDAO();
		}
		
		return this.groupDAO;
	}
	
	public IndividualDAO getIndividualDAO() {
		if(this.individualDAO == null) {
			this.individualDAO = new IndividualDAO();
		}
		
		return this.individualDAO;
	}
}
