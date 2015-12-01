package us.haverkamp.ccb.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import us.haverkamp.ccb.domain.Address;
import us.haverkamp.ccb.domain.Event;
import us.haverkamp.ccb.domain.Family;
import us.haverkamp.ccb.domain.Group;
import us.haverkamp.ccb.domain.Individual;
import us.haverkamp.utils.DateUtils;
import us.haverkamp.utils.XmlUtils;

public class Mapper {
	private static final String NODE_ATTENDEE = "attendee";
	private static final String NODE_ATTENDEE_ATTRIBUTE_ID = "id";
	private static final String NODE_FIRST_NAME = "first_name";
	private static final String NODE_LAST_NAME = "last_name";
	
	private static final String NODE_EVENT = "event";
	private static final String NODE_EVENT_ATTRIBUTE_ID = "id";
	private static final String NODE_OCCURRENCE = "occurrence";
	
	private static final String NODE_GROUP = "group";
	private static final String NODE_GROUP_ATTRIBUTE_ID = "id";
	private static final String NODE_NAME = "name";
	
	public static List<Event> getEvents(String xml) 
			throws NoDataFoundException, ParserConfigurationException, SAXException, IOException, ParseException {
		final Document document = XmlUtils.getDocument(xml);
		
		final NodeList items = document.getElementsByTagName(NODE_EVENT);
		if(items == null) {
			throw new NoDataFoundException(
				document.getElementsByTagName("message").item(0).getTextContent()
			);
		}
		
		final List<Event> events = new ArrayList<Event>();
		for(int i=0;i<items.getLength();i++) {
			final Element e = (Element) items.item(i);
			
			events.add(Mapper.getEvent(e));
			
		}
		
		return events;
	}

	public static Event getEvent(String xml) 
			throws NoDataFoundException, ParserConfigurationException, SAXException, IOException, ParseException {
		final Document document = XmlUtils.getDocument(xml);
		
		final Element e = (Element) document.getElementsByTagName(NODE_EVENT).item(0);
		if(e == null) {
			throw new NoDataFoundException(
				document.getElementsByTagName("message").item(0).getTextContent()
			);
		}
		
		return Mapper.getEvent(e);
	}
	
	public static Event getEvent(Element e) throws ParseException {
		final Event event = 
			new Event(Long.valueOf(e.getAttribute(NODE_EVENT_ATTRIBUTE_ID)));
		event.setDate(
			DateUtils.toDate(
				e.getElementsByTagName(NODE_OCCURRENCE).item(0).getTextContent(),
				DateUtils.DATE_TIME
			));

		final NodeList items = e.getElementsByTagName(NODE_ATTENDEE);
		
		final List<Individual> attendees = new ArrayList<Individual>();
		for(int i=0;i<items.getLength();i++) {
			attendees.add(Mapper.getIndividual((Element) items.item(i)));
		}
		event.setAttendees(attendees);

		return event;
	}
	
	public static Event getEvent(ResultSet rs) throws SQLException {
		final Event event = new Event(rs.getLong("event_id"));
		
		event.setName(rs.getString("event"));
		event.setEventGrouping(rs.getString("event_grouping"));
		event.setDate(rs.getDate("event_date"));
		
		return event;
	}
	
	public static Group getGroup(Element e) {
		final Group group = new Group(Long.valueOf(e.getAttribute(NODE_GROUP_ATTRIBUTE_ID)));
		group.setName(e.getElementsByTagName(NODE_NAME).item(0).getTextContent());
		
		return group;
	}
	
	public static Group getGroup(ResultSet rs) throws SQLException {
		final Group group = new Group(rs.getLong("group_id"));
		
		group.setName(rs.getString("group"));
		
		return group;
	}
	
	public static List<Group> getGroups(String xml) 
			throws ParserConfigurationException, SAXException, IOException {
		final Document document = XmlUtils.getDocument(xml);
		
		final List<Group> groups = new ArrayList<Group>();
		final NodeList items = document.getElementsByTagName(NODE_GROUP);
		
		for(int i=0;i<items.getLength();i++) {
			groups.add(Mapper.getGroup((Element) items.item(i)));
		}

		return groups;
	}
	
	public static Individual getIndividual(Element e) {
		final Individual individual = new Individual(Long.valueOf(e.getAttribute(NODE_ATTENDEE_ATTRIBUTE_ID)));
		individual.setFirstName(e.getElementsByTagName(NODE_FIRST_NAME).item(0).getTextContent());
		individual.setLastName(e.getElementsByTagName(NODE_LAST_NAME).item(0).getTextContent());
		
		return individual;
	}
	
	public static Individual getIndividual(ResultSet rs) throws SQLException {
		final Individual individual = new Individual(rs.getLong("individual_id"));
		
		individual.setFamily(Mapper.getFamily(rs));
		individual.setFamilyPosition(rs.getString("family_position"));

		individual.setLimitedAccessUser(rs.getBoolean("limited_access_user"));

		individual.setPrefix(rs.getString("prefix"));
		individual.setFirstName(rs.getString("first_name"));
		individual.setMiddleName(rs.getString("middle_name"));
		individual.setLastName(rs.getString("last_name"));
		individual.setSuffix(rs.getString("suffix"));
		
		individual.setEmail(rs.getString("email"));
		
		individual.setContact(Mapper.getContactAddress(rs));
		individual.setHome(Mapper.getHomeAddress(rs));
		individual.setWork(Mapper.getWorkAddress(rs));
		individual.setOther(Mapper.getOtherAddress(rs));
		
		individual.setContactPhone(rs.getLong("phone_contact"));
		if(rs.wasNull()) { 
			individual.setContactPhone(null);
		}
		individual.setHomePhone(rs.getLong("phone_home"));
		if(rs.wasNull()) { 
			individual.setHomePhone(null);
		}
		individual.setWorkPhone(rs.getLong("phone_work"));
		if(rs.wasNull()) {
			individual.setWorkPhone(null);
		}
		individual.setMobilePhone(rs.getLong("phone_mobile"));
		if(rs.wasNull()) {
			individual.setMobilePhone(null);
		}
		
		individual.setFax(rs.getLong("fax"));
		if(rs.wasNull()) {
			individual.setFax(null);
		}
		individual.setPager(rs.getLong("pager"));
		if(rs.wasNull()) {
			individual.setPager(null);
		}
		
		individual.setEmergencyPhone(rs.getLong("emergency_phone"));
		if(rs.wasNull()) {
			individual.setEmergencyPhone(null);
		}
		individual.setEmergencyContact(rs.getString("emergency_contact_name"));
		
		individual.setBirthday(rs.getDate("birthdate"));
		individual.setAnniversary(rs.getDate("anniversary"));
		individual.setDeceased(rs.getDate("deceased"));
		
		individual.setGender(rs.getString("gender"));
		
		individual.setGivingNo(rs.getInt("giving_number"));
		if(rs.wasNull()) {
			individual.setGivingNo(null);
		}
		
		individual.setMaritalStatus(rs.getString("marital_status"));
		individual.setWorkTitle(rs.getString("job_title"));
		individual.setSchool(rs.getString("school"));
		individual.setSchoolGrade(rs.getString("school_grade"));
		
		individual.setAllergies(rs.getString("known_allergies"));
		individual.setMilitary(rs.getString("military"));
		individual.setConfirmationVerse(rs.getString("confirmation_verse"));
		individual.setEmailWork(rs.getString("email_work"));
		individual.setMaidenName(rs.getString("maiden_name"));
		
		individual.setFirstCommunion(rs.getDate("first_communion_date"));
		individual.setConfirmation(rs.getDate("confirmation_date"));
		individual.setBaptism(rs.getDate("baptism_date"));
		
		individual.setElder(rs.getString("elder"));
		
		individual.setNewsletter(rs.getBoolean("newsletter"));
		individual.setConfirmed(rs.getBoolean("confirmed"));
		
		individual.setMembershipStartDate(rs.getDate("membership_date"));
		individual.setMembershipStopDate(rs.getDate("membership_stop_date"));
		individual.setMembershipType(rs.getString("membership_type"));

		individual.setBaptized(rs.getBoolean("baptized"));
		individual.setListed(rs.getBoolean("listed"));
		individual.setInactive(rs.getBoolean("inactive"));

		individual.setHowTheyHeard(rs.getString("how_they_heard"));
		individual.setReasonLeftChurch(rs.getString("reason_left_church"));
		
		individual.setOtherId(rs.getString("other_id"));
		
		return individual;
	}
	
	public static Family getFamily(ResultSet rs) throws SQLException {
		return new Family(rs.getLong("family_id"));
	}
	
	private static Address getContactAddress(ResultSet rs) throws SQLException {
		return Mapper.getAddress(rs, new String[] {
			"address_mailing_street1", 
			"address_mailing_city", 
			"address_mailing_state", 
			"address_mailing_postal_code"
		});
	}
	
	private static Address getHomeAddress(ResultSet rs) throws SQLException {
		return Mapper.getAddress(rs, new String[] {
			"address_home_street1", 
			"address_home_city", 
			"address_home_state", 
			"address_home_postal_code"
		});
	}
	
	private static Address getWorkAddress(ResultSet rs) throws SQLException {
		return Mapper.getAddress(rs, new String[] {
			"address_work_street1", 
			"address_work_city", 
			"address_work_state", 
			"address_work_postal_code"
		});
	}
	
	private static Address getOtherAddress(ResultSet rs) throws SQLException {
		return Mapper.getAddress(rs, new String[] {
			"address_other_street1", 
			"address_other_city", 
			"address_other_state", 
			"address_other_postal_code"
		});
	}
	
	private static Address getAddress(ResultSet rs, String[] fields) throws SQLException {
		final Address address = new Address();
		
		address.setStreet(rs.getString(fields[0]));
		address.setCity(rs.getString(fields[1]));
		address.setState(rs.getString(fields[2]));
		address.setZip(rs.getString(fields[3]));
		
		return address;
	}
}
