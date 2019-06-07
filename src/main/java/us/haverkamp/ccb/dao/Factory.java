package us.haverkamp.ccb.dao;

public abstract class Factory {
	public static final int CSV = 1;
	public static final int JDBC = 2;
	
	public static Factory getInstance(int factory) {
		switch(factory) {
		case CSV:
			return us.haverkamp.ccb.dao.csv.Factory.getInstance();
		case JDBC:
			return us.haverkamp.ccb.dao.jdbc.Factory.getInstance();
		default:
			throw new IllegalArgumentException("factory not defined");
		}
	}
	
	public abstract AttendanceDAO getAttendanceDAO();
	
	public abstract CampusDAO getCampusDAO();
	
	public abstract EventDAO getEventDAO();
	
	public abstract FamilyDAO getFamilyDAO();
	
	public abstract GroupDAO getGroupDAO();
	
	public abstract IndividualDAO getIndividualDAO();

	public abstract LookupDAO getLookupDAO();
	
	public abstract TransactionDAO getTransactionDAO();
}
