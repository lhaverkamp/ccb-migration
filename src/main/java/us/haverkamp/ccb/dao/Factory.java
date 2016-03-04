package us.haverkamp.ccb.dao;

public class Factory {
	private AttendanceDAO attendanceDAO;
	private CampusDAO campusDAO;
	private EventDAO eventDAO;
	private FamilyDAO familyDAO;
	private GroupDAO groupDAO;
	private IndividualDAO individualDAO;
	private LookupDAO lookupDAO;
	private TransactionDAO transactionDAO;
	
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
	
	public CampusDAO getCampusDAO() {
		if(this.campusDAO == null) {
			this.campusDAO = new CampusDAO();
		}
		
		return this.campusDAO;
	}
	
	public EventDAO getEventDAO() {
		if(this.eventDAO == null) {
			this.eventDAO = new EventDAO();
		}
		
		return this.eventDAO;
	}
	
	public FamilyDAO getFamilyDAO() {
		if(this.familyDAO == null) {
			this.familyDAO = new FamilyDAO();
		}
		
		return this.familyDAO;
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

	public LookupDAO getLookupDAO() {
		if(this.lookupDAO == null) {
			this.lookupDAO = new LookupDAO();
		}
		
		return this.lookupDAO;
	}
	
	public TransactionDAO getTransactionDAO() {
		if(this.transactionDAO == null) {
			this.transactionDAO = new TransactionDAO();
		}
		
		return this.transactionDAO;
	}
}
