package us.haverkamp.ccb.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Event extends Api {
	private Long id;
	private Group group;
	private String name;

	private Date date;
	
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

	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getStartTime() {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate());
		
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
		
		return calendar.getTime();
	}
	
	public Date getEndTime() {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(getStartTime());
		calendar.add(Calendar.HOUR, 1);
		
		return calendar.getTime();
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
}
