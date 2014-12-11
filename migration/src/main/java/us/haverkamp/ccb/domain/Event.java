package us.haverkamp.ccb.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Event {
	private Long id;
	private Group group;
	private String name;
	
	private Date start;
	private Date end;
	
	private String description;
	private String leaderNotes;
	private int setup;
	private int cleanup;
	private String setupNotes;
	
	private Individual organizer;
	// Contact Phone
	// Event Type ID
	// Registration Form ID
	private String eventGrouping;
	// Registration Limit
	// Recurrence Type
	// Recurrence Frequency
	// Recurrence Week Number
	// Recurrence Day of Week
	// Recurrence Day of Month
	// Recurrence End Date
	// Number of Occurrences
	
	// Location Name
	// Location Street Address
	// Location City
	// Location State
	// Location Zip
	
	// Notification
	// Attendance Reminder
	// Listed
	// Creator ID
	
	private List<Individual> attendees;
	
	private static final String NODE_EVENT = "event";
	private static final String NODE_EVENT_ATTRIBUTE_ID = "id";
	private static final String NODE_EVENT_ATTRIBUTE_OCCURRENCE = "occurrence";
	private static final String NODE_DID_NOT_MEET = "did_not_meet";
	private static final String NODE_HEAD_COUNT = "head_count";
	private static final String NODE_ATTENDEES = "attendees";
	private static final String NODE_ATTENDEE = "attendee";
	private static final String NODE_ATTENDEE_ATTRIBUTE_ID = "id";
	private static final String NODE_TOPIC = "topic";
	private static final String NODE_NOTES = "notes";
	private static final String NODE_PRAYER_REQUESTS = "prayer_requests";
	private static final String NODE_INFO = "info";
	private static final String NODE_EMAIL_NOTIFICATION = "email_notification";
	
	public Event(Long id) {
		setId(id);
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	/**
	 * This method figures out the Event Time based upon the classification of
	 * the event.  Some events do occur at multiple times but for historic data
	 * we are simply importing them at one specific time.
	 * 
	 * @return String in the format of yyyy-mm-dd HH:mi:ss
	 */
	private String getOccurrence() {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(getStart());
		
		switch (getEventGrouping()) {
		case "Sunday Worship":
		case "Communion":
			calendar.set(Calendar.HOUR_OF_DAY, 8);
			break;
		case "Advent":
		case "New Year's Eve":
		case "Ash Wednesday":
		case "Lent":
		case "Maundy Thursday":
		case "Good Friday":
		case "Thanksgiving Eve":
		case "Board / Committee":
			calendar.set(Calendar.HOUR_OF_DAY, 19);
			break;
		case "Christmas Eve":
			calendar.set(Calendar.HOUR_OF_DAY, 18);
			calendar.set(Calendar.MINUTE, 30);
			break;
		case "Christmas Day":
		case "Holy Saturday":
		case "Sunday School":
			calendar.set(Calendar.HOUR_OF_DAY, 9);
			calendar.set(Calendar.MINUTE, 30);
			break;
		default:
			throw new NullPointerException("event grouping (" + getEventGrouping() + ") does not have a default time specified");
		}
		
		final DateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mi:ss");
		
		return format.format(calendar.getTime());
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLeaderNotes() {
		return leaderNotes;
	}

	public void setLeaderNotes(String leaderNotes) {
		this.leaderNotes = leaderNotes;
	}

	public int getSetup() {
		return setup;
	}

	public void setSetup(int setup) {
		this.setup = setup;
	}

	public int getCleanup() {
		return cleanup;
	}

	public void setCleanup(int cleanup) {
		this.cleanup = cleanup;
	}

	public String getSetupNotes() {
		return setupNotes;
	}

	public void setSetupNotes(String setupNotes) {
		this.setupNotes = setupNotes;
	}

	public Individual getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Individual organizer) {
		this.organizer = organizer;
	}

	public String getEventGrouping() {
		return eventGrouping;
	}

	public void setEventGrouping(String eventGrouping) {
		this.eventGrouping = eventGrouping;
	}

	public List<Individual> getAttendees() {
		return this.attendees;
	}

	public void setAttendees(List<Individual> attendees) {
		this.attendees = attendees;
	}
	
	/**
	 * This method returns the XML that is used for reporting Event Attendance
	 * for the create_event_attendance API call.  The major difference is that
	 * it uses the occurrence field to represent a particular instance of an
	 * event that has occurred.
	 * 
	 *	<?xml version="1.0" encoding="UTF-8" ?>
		<events>
			<event id="#" occurrence="yyyy-mm-dd hh:mi:ss">
				<did_not_meet>false</did_not_meet>
				<head_count>#</head_count>
				<attendees>
					<attendee id="#"></attendee>
					<attendee id="#"></attendee>
					...
				</attendees>
				<topic>...</topic>
				<notes>...</notes>
				<prayer_requests>...</prayer_requests>
				<info>...</info>
				<email_notification>none</email_notification>
			</event>
			...
		</events>
		
	 * @return Element representing the XML for the event node.
	 * @throws ParserConfigurationException 
	 */
	public Element toXML() throws ParserConfigurationException {
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final Document document = builder.newDocument();
		final Element event = document.createElement(NODE_EVENT);
		
		event.setAttribute(NODE_EVENT_ATTRIBUTE_ID, getId().toString());
		event.setAttribute(NODE_EVENT_ATTRIBUTE_OCCURRENCE, getOccurrence());
		
		final Node didNotMeet = 
			event.appendChild(document.createElement(NODE_DID_NOT_MEET));
		didNotMeet.setTextContent("false");
		
		final Node headCount = 
			event.appendChild(document.createElement(NODE_HEAD_COUNT));
		headCount.setTextContent(Integer.toString(getAttendees().size()));
		
		final Node attendees = 
			event.appendChild(document.createElement(NODE_ATTENDEES));
		
		for(Individual individual : getAttendees()) {
			final Element attendee = 
				(Element) attendees.appendChild(document.createElement(NODE_ATTENDEE));
			attendee.setAttribute(NODE_ATTENDEE_ATTRIBUTE_ID, individual.getId().toString());
		}
		
		final Node topic = event.appendChild(document.createElement(NODE_TOPIC));
		final Node notes = event.appendChild(document.createElement(NODE_NOTES));
		final Node prayerRequests = 
			event.appendChild(document.createElement(NODE_PRAYER_REQUESTS));
		final Node info = event.appendChild(document.createElement(NODE_INFO));
		// TODO set info to indicate this record was created as part of the CCB
		// migration process
		
		final Node emailNotification = 
			event.appendChild(document.createElement(NODE_EMAIL_NOTIFICATION));
		emailNotification.setTextContent("none");
		
		return event;
	}
}
