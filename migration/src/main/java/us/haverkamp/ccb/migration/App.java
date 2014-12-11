package us.haverkamp.ccb.migration;

import java.util.List;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.dao.EventDAO;
import us.haverkamp.ccb.dao.Factory;
import us.haverkamp.ccb.dao.IndividualDAO;
import us.haverkamp.ccb.domain.Event;
import us.haverkamp.ccb.domain.Group;
import us.haverkamp.ccb.domain.Individual;

public class App {
	public void syncIndividuals() throws DataAccessException {
        final IndividualDAO dao = Factory.getInstance().getIndividualDAO();
        final List<Individual> individuals = 
        	dao.findBy(IndividualDAO.SQL_UPDATE);
        
        int cnt = 0;
        for(Individual individual : individuals) {
        	int status;
        	
			if((status = dao.update(individual)) == 200) {
        		cnt++;
        	};
        	
        	System.out.println(status + ": " + individual.getFirstName() + " " + individual.getLastName());
        }
        
        System.out.println(cnt + " of " + individuals.size());
	}
	
	public void syncEvents() throws DataAccessException {
		final Group group = Factory.getInstance().getGroupDAO().findByName("Hope Lutheran Church");
		
		final EventDAO dao = Factory.getInstance().getEventDAO();
		final List<Event> events = dao.findBy(EventDAO.SQL_ALL);
		
		int cnt = 0;
		for(Event event : events) {
			int status;

			event.setGroup(group);
			
			if((status = dao.create(event)) == 200) {
				cnt++;
			}
        	
        	System.out.println(status + ": " + event.getName() + " " + event.getAttendees().size());
		}
        
        System.out.println(cnt + " of " + events.size());
	}
	
    public static void main( String[] args ) throws DataAccessException {
    	final App app = new App();
    	
    	//TODO app.syncIndividuals();
    	app.syncEvents();
    }
}
