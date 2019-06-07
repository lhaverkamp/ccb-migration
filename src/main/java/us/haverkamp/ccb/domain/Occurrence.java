package us.haverkamp.ccb.domain;

import java.util.Date;
import java.util.List;

public class Occurrence extends Event {
	private Date occurrence;
	private Boolean didNotMeet;
	private String topic;
	private String notes;
	private String prayerRequests;
	private String info;
	private List<Individual> attendees;
	private Integer headCount;
	
	public Occurrence(Long id, String name, Date occurrence) {
		super(id);
		setName(name);
		setOccurrence(occurrence);
	}

	public Date getOccurrence() {
		return this.occurrence;
	}
	
	public void setOccurrence(Date occurrence) {
		this.occurrence = occurrence;
	}
	
	public Boolean getDidNotMeet() {
		return didNotMeet;
	}

	public void setDidNotMeet(Boolean didNotMeet) {
		this.didNotMeet = didNotMeet;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPrayerRequests() {
		return prayerRequests;
	}

	public void setPrayerRequests(String prayerRequests) {
		this.prayerRequests = prayerRequests;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<Individual> getAttendees() {
		return this.attendees;
	}
	
	public void setAttendees(List<Individual> attendees) {
		this.attendees = attendees;
	}

	public Integer getHeadCount() {
		return headCount;
	}

	public void setHeadCount(Integer headCount) {
		this.headCount = headCount;
	}
}
