package us.haverkamp.ccb.domain;

import java.util.Date;

public class Event extends Api {
	private Long id; // id
	private String name; // name
	private String description; // description
	private String leaderNotes; // leader_notes
	
	private Group group; // group
	private Individual organizer; // organizer
	private String phone; // phone

	private Date start; // start_datetime
	// start_date
	// start_time
	private Date end; // end_datetime
	// end_date
	// end_time
	private String timezone; // timezone
	private String recurrenceDescription; // recurrence_description
	private ApprovalStatus approvalStatus; // approval_status
	// exceptions
	
	private Address location; // location
	
	// registration
	// guest_list
	// resources
	
	private int setup; // setup start
	private int cleanup; // setup end
	private String setupNotes; // setup notes
	
	private EventGrouping eventGrouping; // event_grouping
	private Boolean listed; // listed
	private Boolean publicCalendarListed; // public calendar listed
	
	private Individual creator; // creator
	private Individual modifier; // modifier
	private Date created; // created
	private Date modified; // modified
	
	public Event(Long id) {
		setId(id);
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Individual getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Individual organizer) {
		this.organizer = organizer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getRecurrenceDescription() {
		return recurrenceDescription;
	}

	public void setRecurrenceDescription(String recurrenceDescription) {
		this.recurrenceDescription = recurrenceDescription;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
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

	public EventGrouping getEventGrouping() {
		return eventGrouping;
	}

	public void setEventGrouping(EventGrouping eventGrouping) {
		this.eventGrouping = eventGrouping;
	}

	public Boolean getListed() {
		return listed;
	}

	public void setListed(Boolean listed) {
		this.listed = listed;
	}

	public Boolean getPublicCalendarListed() {
		return publicCalendarListed;
	}

	public void setPublicCalendarListed(Boolean publicCalendarListed) {
		this.publicCalendarListed = publicCalendarListed;
	}

	public Individual getCreator() {
		return creator;
	}

	public void setCreator(Individual creator) {
		this.creator = creator;
	}

	public Individual getModifier() {
		return modifier;
	}

	public void setModifier(Individual modifier) {
		this.modifier = modifier;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
}
