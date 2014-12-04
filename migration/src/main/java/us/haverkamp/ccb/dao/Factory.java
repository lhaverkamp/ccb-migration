package us.haverkamp.ccb.dao;

public class Factory {
	private IndividualDAO individualDAO;
	
	private Factory() {
		// private for factory method
	}
	
	public static Factory getInstance() {
		return new Factory();
	}
	
	public IndividualDAO getIndividualDAO() {
		if(this.individualDAO == null) {
			this.individualDAO = new IndividualDAO();
		}
		
		return this.individualDAO;
	}
}
