package us.haverkamp.ccb.migration;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.dao.EventDAO;
import us.haverkamp.ccb.dao.Factory;
import us.haverkamp.ccb.dao.IndividualDAO;
import us.haverkamp.ccb.domain.Event;
import us.haverkamp.ccb.domain.Group;
import us.haverkamp.ccb.domain.Individual;
import us.haverkamp.utils.XmlUtils;

public class App {
	public void syncIndividuals() throws DataAccessException {
        final IndividualDAO dao = Factory.getInstance().getIndividualDAO();
        final List<Individual> individuals = 
        	dao.findBy(IndividualDAO.SQL_UPDATE);
        
        for(Individual individual : individuals) {
        	final String xml = dao.update(individual);
        	
        	System.out.println(xml);
        }
	}
	
	public void syncEvents() throws DataAccessException {
		final Group group = Factory.getInstance().getGroupDAO().findByName("Hope Lutheran Church");
		
		final EventDAO dao = Factory.getInstance().getEventDAO();
		final List<Event> events = dao.findBy(EventDAO.SQL_ALL);
		
		for(Event event : events) {
			event.setGroup(group);
			
			String xml = dao.create(event);
			System.out.println(xml);
			
			try {
				final Document document = XmlUtils.getDocument(xml);
				final Element element = 
					(Element) document.getElementsByTagName("event").item(0);
				
				event.setId(Long.valueOf(element.getAttribute("id")));
			} catch(ParserConfigurationException e) {
				throw new DataAccessException(e);
			} catch(SAXException e) {
				throw new DataAccessException(e);
			} catch(IOException e) {
				throw new DataAccessException(e);
			}
			
			xml = dao.attendance(event);
			
        	System.out.println(xml);
		}
 	}
	
    public static void main( String[] args ) throws DataAccessException {
    	final App app = new App();
    	
    	//app.syncIndividuals();
    	app.syncEvents();
    }
}
