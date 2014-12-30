package us.haverkamp.ccb.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import us.haverkamp.ccb.domain.Event;
import us.haverkamp.utils.XmlUtils;

public class EventDAO extends GenericDAO<Event> {
	public static final String SQL_ALL =
		"SELECT " +
		"	event_id, " +
		"	event, " +
		"	TRIM(event) AS event_grouping, " +
		"	TRIM(occurance) AS event_date, " +
		"	COUNT(*) AS attendance " +
		"FROM ss_attendance " +
		"WHERE occurance > '2014-12-14' " + 
		"GROUP BY event_id, event, occurance " +
		"ORDER BY occurance, event, event_id";
	
	private Map<String, Integer> eventGrouping;

	//TODO should return the Event object that was just created
	public String create(Event event) throws DataAccessException {
		final List<NameValuePair> params = new ArrayList<NameValuePair>();
		// required parameters
		params.add(new BasicNameValuePair("group_id", event.getGroup().getId().toString()));
		params.add(new BasicNameValuePair("start_date", toString(event.getStartTime())));
		params.add(new BasicNameValuePair("end_date", toString(event.getEndTime())));
		params.add(new BasicNameValuePair("name", event.getName()));
		
		// optional parameters
		// TODO CCB doesn't accept ' even when it's URL encoded properly
		params.add(new BasicNameValuePair("description", "Imported from Shepherds Staff [Event ID = " + event.getId().toString() + "]"));
		//leader_notes
		//setup_minutes
		//cleanup_minutes
		//setup_notes
		//organizer_id
		//contact_phone
		//event_type_id
		//registration_form_id
		params.add(new BasicNameValuePair("event_grouping_id", getEventGrouping().get(event.getEventGrouping()).toString()));
		//registration_limit
		//recurrence_type
		//recurrency_frequency
		//recurrence_week_number
		//recurrence_day_of_week
		//recurrence_day_of_month
		//recurrence_end_date
		//number_of_occurrences
		//location_name
		//location_street_address
		//location_city
		//location_state
		//location_zip
		//notification
		//attendance_reminder
		//listed
		//creator_id
			
		final String xml = post("create_event", params);
		
		//TODOreturn Mapper.getEvent(xml);
		
		return xml;
	}
	
	/**
	 * This method posts attendance for the specific event to the CCB API.
	 * 
	 * @param event
	 * @return
	 * @throws DataAccessException
	 */
	public String attendance(Event event) throws DataAccessException {
		try {
			return post("create_event_attendance", toString(event.toXML()));
		} catch(ParserConfigurationException e) {
			throw new DataAccessException(e);
		} catch(TransformerException e) {
			throw new DataAccessException(e);
		} catch(IOException e) {
			throw new DataAccessException(e);
		}
	}
	
	private String toString(Element element) throws DataAccessException { 
		try {
			final Document document = XmlUtils.newDocument();
			// due to the fact this Element was most likely created by a 
			// different document; we need to adopt it to our current document
			document.adoptNode(element);
			
			final Element events = document.createElement("events");
			events.appendChild(element);
	
			document.appendChild(events);
			
			return XmlUtils.toString(document);
		} catch(ParserConfigurationException e) {
			throw new DataAccessException(e);
		} catch(TransformerException e) {
			throw new DataAccessException(e);
		} catch(IOException e) {
			throw new DataAccessException(e);
		}
	}

	protected Event getItem(ResultSet rs) throws DataAccessException {
		try {
			final Event event = Mapper.getEvent(rs);
			event.setAttendees(Factory.getInstance().getIndividualDAO().findBy(event));
			
			return event;
		} catch(SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	private Map<String, Integer> getEventGrouping() throws DataAccessException {
		if(this.eventGrouping == null) {
			this.eventGrouping = getLookupTable("event_grouping_list");
		}
		
		return this.eventGrouping;
	}

	protected String toString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
}
