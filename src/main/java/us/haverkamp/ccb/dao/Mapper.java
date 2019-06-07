package us.haverkamp.ccb.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import us.haverkamp.ccb.Constants;
import us.haverkamp.ccb.domain.Address;
import us.haverkamp.ccb.domain.ApprovalStatus;
import us.haverkamp.ccb.domain.Batch;
import us.haverkamp.ccb.domain.COA;
import us.haverkamp.ccb.domain.Campus;
import us.haverkamp.ccb.domain.CommunicationPreferences;
import us.haverkamp.ccb.domain.Department;
import us.haverkamp.ccb.domain.Event;
import us.haverkamp.ccb.domain.EventGrouping;
import us.haverkamp.ccb.domain.Family;
import us.haverkamp.ccb.domain.Group;
import us.haverkamp.ccb.domain.GroupType;
import us.haverkamp.ccb.domain.Grouping;
import us.haverkamp.ccb.domain.Individual;
import us.haverkamp.ccb.domain.MembershipType;
import us.haverkamp.ccb.domain.Occurrence;
import us.haverkamp.ccb.domain.PrivacySettings;
import us.haverkamp.ccb.domain.Selection;
import us.haverkamp.ccb.domain.Transaction;
import us.haverkamp.ccb.domain.UserDefinedField;
import us.haverkamp.utils.DateUtils;
import us.haverkamp.utils.StringUtils;
import us.haverkamp.utils.XMLUtils;

public class Mapper {
	protected static Address getAddress(Node node) {
		final Element element = (Element) node;
		
		final Address item = new Address(
			StringUtils.parseString(element.getElementsByTagName(Constants.STREET_ADDRESS).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.CITY).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.STATE).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.ZIP).item(0))
		);
		
		item.setCountry(StringUtils.parseString(element.getElementsByTagName(Constants.COUNTRY).item(0)));		
		item.setLine1(StringUtils.parseString(element.getElementsByTagName(Constants.LINE_1).item(0)));		
		item.setLine2(StringUtils.parseString(element.getElementsByTagName(Constants.LINE_2).item(0)));
		
		item.setName(StringUtils.parseString(element.getElementsByTagName(Constants.NAME).item(0)));
		
		return item;
	}
	
	protected static ApprovalStatus getApprovalStatus(Node node) {
		final Element element = (Element) node;

		final ApprovalStatus item = new ApprovalStatus(
			Long.valueOf(element.getAttribute(Constants.ID)),
			StringUtils.parseString(element)
		);
		
		return item;
	}
	
	protected static Campus getCampus(Node node) {
		return getCampus(node, true);
	}
	
	protected static Campus getCampus(Node node, boolean all) {
		final Element element = (Element) node;
		
		final Campus item = new Campus(Long.valueOf(element.getAttribute(Constants.ID)));
		
		if(all) {
			item.setName(StringUtils.parseString(element.getElementsByTagName(Constants.NAME).item(0))); // name
		}
		
		return item;
	}
	
	public static Campus getCampus(String xml) throws NoDataFoundException, DataAccessException {
		final List<Campus> items = getCampuses(xml);
		
		if(items.size() != 1) {
			throw new NoDataFoundException(items.size() + " campuses found");
		}
		
		return items.get(0);
	}
	
	public static List<Campus> getCampuses(String xml) throws NoDataFoundException, DataAccessException {
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			final NodeList nodes = document.getElementsByTagName(Constants.CAMPUS);
			if(nodes == null) {
				throw new NoDataFoundException(
					StringUtils.parseString(document.getElementsByTagName("message").item(0))
				);
			}
			
			final List<Campus> items = new ArrayList<Campus>();
			for(int i=0;i<nodes.getLength();i++) {
				items.add(getCampus(nodes.item(i)));
			}
			
			return items;
		} catch(IOException | SAXException | ParserConfigurationException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static COA getCOA(Node node) {
		final Element element = (Element) node;
		
		final COA item = new COA(
			StringUtils.parseLong(element.getAttribute(Constants.ID)), // id
			StringUtils.parseString(element) // name
		);
		
		return item;
	}

	protected static CommunicationPreferences getCommunicationPreferences(Node node) {
		final Element element = (Element) node;
		
		final CommunicationPreferences item = new CommunicationPreferences(
			StringUtils.parseBoolean(element.getElementsByTagName(Constants.RECEIVE_EMAIL_FROM_CHURCH).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.DEFAULT_NEW_GROUP_MESSAGES).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.DEFAULT_NEW_GROUP_COMMENTS).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.DEFAULT_NEW_GROUP_DIGEST).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.DEFAULT_NEW_GROUP_SMS).item(0))
		);
		
		return item;
	}
	
	protected static Department getDepartment(Node node) throws DataAccessException {
		final Element element = (Element) node;
		
		final Department item = new Department(
			StringUtils.parseLong(element.getAttribute(Constants.ID)),
			StringUtils.parseString(element)
		);
		
		return item;
	}

	protected static Event getEvent(Node node) throws DataAccessException {
		final Element element = (Element) node;
		
		try {
			final Event item = new Event(Long.valueOf(element.getAttribute(Constants.ID))); // id
			
			item.setName(StringUtils.parseString(element.getElementsByTagName(Constants.NAME).item(0))); // name
			item.setDescription(StringUtils.parseString(element.getElementsByTagName(Constants.DESCRIPTION).item(0))); // description
			item.setLeaderNotes(StringUtils.parseString(element.getElementsByTagName(Constants.LEADER_NOTES).item(0)));// leader_notes
			item.setStart(DateUtils.parseTimestamp(element.getElementsByTagName(Constants.START_DATETIME).item(0))); // start_datetime
			// start_date
			// start_time
			item.setEnd(DateUtils.parseTimestamp(element.getElementsByTagName(Constants.END_DATETIME).item(0))); // end_datetime
			// end_date
			// end_time
			item.setTimezone(StringUtils.parseString(element.getElementsByTagName(Constants.TIMEZONE).item(0))); // timezone
			item.setRecurrenceDescription(StringUtils.parseString(element.getElementsByTagName(Constants.RECURRENCE_DESCRIPTION).item(0)));// recurrence_description
			item.setApprovalStatus(getApprovalStatus(element.getElementsByTagName(Constants.APPROVAL_STATUS).item(0))); // approval_status
			// exceptions
				// exception
			item.setGroup(getGroup(element.getElementsByTagName(Constants.GROUP).item(0), false)); // group
			item.setOrganizer(getIndividual(element.getElementsByTagName(Constants.ORGANIZER).item(0), false)); // organizer
			item.setPhone(StringUtils.parseString(element.getElementsByTagName(Constants.PHONE).item(0))); // phone
			item.setLocation(getAddress(element.getElementsByTagName(Constants.LOCATION).item(0))); // location
			// registration
				// limit
				// event_type id
				// forms
			// guest_list
			// resources
				// resource id
					// name
					// description
					// status id
					// resource_type
			// setup
				// start
				// end
				// notes
			item.setEventGrouping(getEventGrouping(element.getElementsByTagName(Constants.EVENT_GROUPING).item(0))); // event_grouping
			item.setCreator(getIndividual(element.getElementsByTagName(Constants.CREATOR).item(0), false)); // creator
			item.setModifier(getIndividual(element.getElementsByTagName(Constants.MODIFIER).item(0), false)); // modifier
			item.setListed(StringUtils.parseBoolean(element.getElementsByTagName(Constants.LISTED).item(0))); // listed
			item.setPublicCalendarListed(StringUtils.parseBoolean(element.getElementsByTagName(Constants.PUBLIC_CALENDAR_LISTED).item(0)));// public_calendar_listed
			item.setCreated(DateUtils.parseTimestamp(element.getElementsByTagName(Constants.CREATED).item(0)));
			item.setModified(DateUtils.parseTimestamp(element.getElementsByTagName(Constants.MODIFIED).item(0)));
			
			return item;
		} catch(ParseException e) {
			throw new DataAccessException(e);
		}
	}

	public static Event getEvent(String xml) throws NoDataFoundException, DataAccessException {
		// TODO refactor
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			final NodeList items = document.getElementsByTagName(Constants.EVENT);
			// TODO error handling
			if(items == null || items.getLength() == 0) {
				throw new NoDataFoundException(
					StringUtils.parseString(document.getElementsByTagName("message").item(0))
				);
			}
			
			return getEvent(items.item(0));
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}

	public static List<Event> getEvents(String xml) throws DataAccessException {
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			final NodeList items = document.getElementsByTagName(Constants.EVENT);
			if(items == null || items.getLength() == 0) {
				throw new NoDataFoundException(
					StringUtils.parseString(document.getElementsByTagName("message").item(0))
				);
			}
			
			final List<Event> events = new ArrayList<Event>();
			for(int i=0;i<items.getLength();i++) {
				final Node node = items.item(i);
				
				events.add(getEvent(node));
			}
			
			return events;
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static EventGrouping getEventGrouping(Node node) throws DataAccessException {
		final Element element = (Element) node;
		
		final EventGrouping item = new EventGrouping(
			StringUtils.parseLong(element.getAttribute(Constants.ID)),
			StringUtils.parseString(element)
		);
		
		return item;
	}

	protected static Family getFamily(Node node) throws DataAccessException {
		return getFamily(node, true);
	}
	
	protected static Family getFamily(Node node, boolean all) throws DataAccessException {
		final Element element = (Element) node;
		try {
			final Family item = new Family(
				Long.valueOf(element.getAttribute(Constants.ID)),
				null//StringUtils.parseString(element)
			);
			
			if(all) {
				item.setModified(DateUtils.parseTimestamp(element.getElementsByTagName(Constants.MODIFIED).item(0)));
			}
			
			return item;
		} catch(ParseException ex) {
			throw new DataAccessException(ex);
		}
	}
	
	public static Family getFamily(String xml) throws NoDataFoundException, DataAccessException {
		final List<Family> items = getFamilies(xml);
		
		if(items.size() != 1) {
			throw new NoDataFoundException(items.size() + " families found");
		}
		
		return items.get(0);
	}
	
	public static List<Family> getFamilies(String xml) throws NoDataFoundException, DataAccessException {
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			final NodeList nodes = document.getElementsByTagName(Constants.FAMILY);
			if(nodes == null) {
				throw new NoDataFoundException(
					StringUtils.parseString(document.getElementsByTagName("message").item(0))
				);
			}
			
			final List<Family> items = new ArrayList<Family>();
			for(int i=0;i<nodes.getLength();i++) {
				items.add(getFamily(nodes.item(i)));
			}
			
			return items;
		} catch(IOException | SAXException | ParserConfigurationException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static Group getGroup(Node node) throws DataAccessException {
		return getGroup(node, true);
	}

	protected static Group getGroup(Node node, boolean all) throws DataAccessException {
		final Element element = (Element) node;
		
		try {
			final Group item = new Group(Long.valueOf(element.getAttribute(Constants.ID)));
			
			if(all) {
				item.setName(StringUtils.parseString(element.getElementsByTagName(Constants.NAME).item(0)));
				item.setDescription(StringUtils.parseString(element.getElementsByTagName(Constants.DESCRIPTION).item(0)));
				// TODO image
				item.setCampus(getCampus(element.getElementsByTagName(Constants.CAMPUS).item(0), false)); // campus
				item.setMainLeader(getIndividual(element.getElementsByTagName(Constants.MAIN_LEADER).item(0), false)); // main_leader
				NodeList nodes = element.getElementsByTagName(Constants.DIRECTOR);
				if(nodes.getLength() == 1) {
					item.setDirector(getIndividual(nodes.item(0), false)); // director
				}
				item.setGroupType(getGroupType(element.getElementsByTagName(Constants.GROUP_TYPE).item(0))); // group_type
				nodes = element.getElementsByTagName(Constants.DEPARTMENT);
				if(nodes.getLength() == 1) {
					item.setDepartment(getDepartment(nodes.item(0))); // department
				}
				item.setCreator(getIndividual(element.getElementsByTagName(Constants.CREATOR).item(0), false)); // creator
				item.setModifier(getIndividual(element.getElementsByTagName(Constants.MODIFIER).item(0), false)); // modifier
				item.setCreated(DateUtils.parseTimestamp(element.getElementsByTagName(Constants.CREATED).item(0))); // created
				item.setModified(DateUtils.parseTimestamp(element.getElementsByTagName(Constants.MODIFIED).item(0))); // modified
			}
			
			return item;
		} catch(ParseException e) {
			throw new DataAccessException(e);
		}
	}

	public static Group getGroup(String xml) throws NoDataFoundException, DataAccessException {
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			final Node node = document.getElementsByTagName(Constants.GROUP).item(0);
			if(node == null) {
				throw new NoDataFoundException(
					StringUtils.parseString(document.getElementsByTagName("message").item(0))
				);
			}
			
			return getGroup(node);
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}

	public static List<Group> getGroups(String xml) throws DataAccessException {
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			final List<Group> items = new ArrayList<Group>();
	
			final NodeList nodes = document.getElementsByTagName(Constants.GROUP);
			for(int i=0;i<nodes.getLength();i++) {
				items.add(getGroup(nodes.item(i)));
			}
	
			return items;
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}

	protected static Grouping getGrouping(Node node) throws DataAccessException {
		final Element element = (Element) node;
		
		final Grouping item = new Grouping(
			StringUtils.parseLong(element.getAttribute(Constants.ID)),
			StringUtils.parseString(element)
		);
		
		return item;
	}

	protected static GroupType getGroupType(Node node) throws DataAccessException {
		final Element element = (Element) node;
		
		final GroupType item = new GroupType(
			StringUtils.parseLong(element.getAttribute(Constants.ID)),
			StringUtils.parseString(element)
		);
		
		return item;
	}

	protected static Individual getIndividual(Node node) throws DataAccessException {
		return getIndividual(node, true);
	}

	protected static Individual getIndividual(Node node, boolean all) throws DataAccessException {
		final Element element = (Element) node;
		
		try {
			final Individual item = new Individual(Long.valueOf(element.getAttribute(Constants.ID)));
			
			if(all) {
				item.setSyncId(StringUtils.parseLong(element.getElementsByTagName(Constants.SYNC_ID).item(0)));
				item.setOtherId(StringUtils.parseString(element.getElementsByTagName(Constants.OTHER_ID).item(0)));
				item.setGivingNumber(StringUtils.parseInt(element.getElementsByTagName(Constants.GIVING_NUMBER).item(0))); // TODO may need to be a string
				item.setCampus(getCampus(element.getElementsByTagName(Constants.CAMPUS).item(0), false));
				item.setFamily(getFamily(element.getElementsByTagName(Constants.FAMILY).item(0), false));
				// TODO family_image
				item.setFamilyPosition(StringUtils.parseString(element.getElementsByTagName(Constants.FAMILY_POSITION).item(0)));
				// TODO family_members
				item.setFirstName(StringUtils.parseString(element.getElementsByTagName(Constants.FIRST_NAME).item(0)));
				item.setLastName(StringUtils.parseString(element.getElementsByTagName(Constants.LAST_NAME).item(0)));
				item.setMiddleName(StringUtils.parseString(element.getElementsByTagName(Constants.MIDDLE_NAME).item(0)));
				item.setLegalFirstName(StringUtils.parseString(element.getElementsByTagName(Constants.LEGAL_FIRST_NAME).item(0)));
				item.setFullName(StringUtils.parseString(element.getElementsByTagName(Constants.FULL_NAME).item(0)));
				item.setPrefix(StringUtils.parseString(element.getElementsByTagName(Constants.SALUTATION).item(0)));
				item.setSuffix(StringUtils.parseString(element.getElementsByTagName(Constants.SUFFIX).item(0)));
				// TODO image
				item.setLogin(StringUtils.parseString(element.getElementsByTagName("login").item(0)));
				item.setEmail(StringUtils.parseString(element.getElementsByTagName(Constants.EMAIL).item(0)));
				item.setAllergies(StringUtils.parseString(element.getElementsByTagName(Constants.ALLERGIES).item(0)));
				item.setConfirmedNoAllergies(StringUtils.parseBoolean(element.getElementsByTagName(Constants.CONFIRMED_NO_ALLERGIES).item(0)));
				NodeList nodes = element.getElementsByTagName(Constants.ADDRESS);
				for(int i=0;i<nodes.getLength();i++) {
					final String type = ((Element) nodes.item(i)).getAttribute(Constants.TYPE);
					if(Constants.MAILING.equals(type)) {
						item.setContact(getAddress(nodes.item(i)));
					} else if(Constants.HOME.equals(type)) {
						item.setHome(getAddress(nodes.item(i)));
					} else if(Constants.WORK.equals(type)) {
						item.setWork(getAddress(nodes.item(i)));
					} else if(Constants.OTHER.equals(type)) {
						item.setOther(getAddress(nodes.item(i)));
					}
				}
				nodes = element.getElementsByTagName(Constants.PHONE);
				for(int i=0;i<nodes.getLength();i++) {
					final String type = ((Element) nodes.item(i)).getAttribute(Constants.TYPE);
					if(Constants.MAILING.equals(type)) {
						item.setContactPhone(StringUtils.parseString(nodes.item(i)));
					} else if(Constants.HOME.equals(type)) {
						item.setHomePhone(StringUtils.parseString(nodes.item(i)));
					} else if(Constants.MOBILE.equals(type)) {
						item.setMobilePhone(StringUtils.parseString(nodes.item(i)));
					} else if(Constants.WORK.equals(type)) {
						item.setWorkPhone(StringUtils.parseString(nodes.item(i)));
					} else if(Constants.EMERGENCY.equals(type)) {
						item.setEmergencyPhone(StringUtils.parseString(nodes.item(i)));
					}
				}
				// TODO mobile_carrier
				item.setGender(StringUtils.parseString(element.getElementsByTagName(Constants.GENDER).item(0)));// email
				item.setMaritalStatus(StringUtils.parseString(element.getElementsByTagName(Constants.MARITAL_STATUS).item(0)));// email
				item.setBirthday(DateUtils.parseDate(element.getElementsByTagName(Constants.BIRTHDAY).item(0)));
				item.setEmergencyContact(StringUtils.parseString(element.getElementsByTagName(Constants.EMERGENCY_CONTACT_NAME).item(0)));
				item.setAnniversary(DateUtils.parseDate(element.getElementsByTagName(Constants.ANNIVERSARY).item(0)));
				item.setBaptized(StringUtils.parseBoolean(element.getElementsByTagName(Constants.BAPTIZED).item(0)));
				item.setDeceased(DateUtils.parseDate(element.getElementsByTagName(Constants.DECEASED).item(0)));
				item.setMembershipType(getMembershipType(element.getElementsByTagName(Constants.MEMBERSHIP_TYPE).item(0)));;
				item.setMembershipDate(DateUtils.parseDate(element.getElementsByTagName(Constants.MEMBERSHIP_DATE).item(0)));
				item.setMembershipEnd(DateUtils.parseDate(element.getElementsByTagName(Constants.MEMBERSHIP_END).item(0)));
				item.setCommunicationPreferences(getCommunicationPreferences(element));
				item.setPrivacySettings(getPrivacySettings(element.getElementsByTagName(Constants.PRIVACY_SETTINGS).item(0)));
				item.setActive(StringUtils.parseBoolean(element.getElementsByTagName(Constants.ACTIVE).item(0)));
				item.setCreator(getIndividual(element.getElementsByTagName(Constants.CREATOR).item(0), false));
				item.setModifier(getIndividual(element.getElementsByTagName(Constants.MODIFIER).item(0), false));
				item.setCreated(DateUtils.parseTimestamp(element.getElementsByTagName(Constants.CREATED).item(0)));
				item.setModified(DateUtils.parseTimestamp(element.getElementsByTagName(Constants.MODIFIED).item(0)));
				item.setUserDefinedTextFields(getUserDefinedTextFields(element.getElementsByTagName(Constants.USER_DEFINED_TEXT_FIELDS).item(0)));
				item.setUserDefinedDateFields(getUserDefinedDateFields(element.getElementsByTagName(Constants.USER_DEFINED_DATE_FIELDS).item(0)));
				item.setUserDefinedPulldownFields(getUserDefinedPulldownFields(element.getElementsByTagName(Constants.USER_DEFINED_PULLDOWN_FIELDS).item(0)));
			}
		
			return item;
		} catch(ParseException ex) {
			throw new DataAccessException(ex);
		}
	}
	
	public static Individual getIndividual(String xml) throws NoDataFoundException, DataAccessException {
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			final Node node = document.getElementsByTagName(Constants.INDIVIDUAL).item(0);
			if(node == null) {
				throw new NoDataFoundException(
					StringUtils.parseString(document.getElementsByTagName("message").item(0))
				);
			}
			
			return getIndividual(node);
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static List<Individual> getIndividuals(NodeList nodes, boolean all) throws DataAccessException {
		final List<Individual> items = new ArrayList<Individual>();
		
		for(int i=0;i<nodes.getLength();i++) {
			items.add(getIndividual(nodes.item(i), all));
		}
			
		return items;
	}
	
	public static List<Individual> getIndividuals(String xml) throws DataAccessException {
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			NodeList nodes = document.getElementsByTagName(Constants.INDIVIDUALS);
			if(nodes == null) {
				throw new NoDataFoundException(
					StringUtils.parseString(document.getElementsByTagName("message").item(0))
				);
			}
			
			final Element parent = (Element) nodes.item(0);
			nodes = parent.getElementsByTagName(Constants.INDIVIDUAL);
			
			final List<Individual> items = new ArrayList<Individual>();
			for(int i=0;i<nodes.getLength();i++) {
				final Element item = (Element) nodes.item(i);
				
				if(item.getParentNode().isSameNode(parent)) {
					items.add(getIndividual(item));
				}
			}
			
			return items;
		} catch(IOException | SAXException | ParserConfigurationException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static MembershipType getMembershipType(Node node) {
		final Element element = (Element) node;
		
		final MembershipType item = new MembershipType(
			StringUtils.parseLong(element.getAttribute(Constants.ID)),
			StringUtils.parseString(element)
		);
		
		return item;
	}
	
	protected static Occurrence getOccurrence(Node node) throws DataAccessException {
		final Element element = (Element) node;
		
		try {
			final Occurrence item = new Occurrence(
				Long.valueOf(element.getAttribute(Constants.ID)), // id
				StringUtils.parseString(element.getElementsByTagName(Constants.NAME).item(0)), // name
				DateUtils.parseTimestamp(element.getElementsByTagName(Constants.OCCURRENCE).item(0)) // occurrence
			); // id
			
			item.setDidNotMeet(StringUtils.parseBoolean(element.getElementsByTagName(Constants.DID_NOT_MEET).item(0))); // did_not_meet
			
			item.setAttendees(getIndividuals(element.getElementsByTagName(Constants.ATTENDEE), false)); // attendees
			
			return item;
		} catch(ParseException e) {
			throw new DataAccessException(e);
		}
		
	}
	
	public static Occurrence getOccurrence(String xml) throws NoDataFoundException, DataAccessException {
		// TODO refactor
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			final NodeList items = document.getElementsByTagName(Constants.EVENT);
			// TODO error handling
			if(items == null || items.getLength() == 0) {
				throw new NoDataFoundException(
					StringUtils.parseString(document.getElementsByTagName("message").item(0))
				);
			}
			
			return getOccurrence(items.item(0));
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}

	public static List<Occurrence> getOccurrences(String xml) throws DataAccessException {
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			final NodeList items = document.getElementsByTagName(Constants.EVENT);
			if(items == null || items.getLength() == 0) {
				throw new NoDataFoundException(
					StringUtils.parseString(document.getElementsByTagName("message").item(0))
				);
			}
			
			final List<Occurrence> occurrences = new ArrayList<Occurrence>();
			for(int i=0;i<items.getLength();i++) {
				final Node node = items.item(i);
				
				occurrences.add(getOccurrence(node));
			}
			
			return occurrences;
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static PrivacySettings getPrivacySettings(Node node) {
		final Element element = (Element) node;
		
		final PrivacySettings privacySettings = new PrivacySettings(
			StringUtils.parseBoolean(element.getElementsByTagName(Constants.PROFILE_LISTED).item(0)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.MAILING_ADDRESS).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.HOME_ADDRESS).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.CONTACT_PHONE).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.HOME_PHONE).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.WORK_PHONE).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.MOBILE_PHONE).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.EMERGENCY_PHONE).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.BIRTHDAY).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.ANNIVERSARY).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.GENDER).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.MARITAL_STATUS).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.USER_DEFINED_FIELDS).item(0)).getAttribute(Constants.ID)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.ALLERGIES).item(0)).getAttribute(Constants.ID))
		);
		
		return privacySettings;
	}
	
	protected static Selection getSelection(Node node) {
		final Element element = (Element) node;
		
		final Selection item = new Selection(
			((Element) element.getElementsByTagName(Constants.ITEM_ID).item(0)).getAttribute(Constants.TYPE),
			StringUtils.parseLong(element.getElementsByTagName(Constants.ID).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.NAME).item(0))
		);
		item.setOrder(StringUtils.parseInt(element.getElementsByTagName(Constants.ORDER).item(0)));
		
		return item;
	}
	
	public static List<Selection> getSelections(String xml) throws DataAccessException {
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			final List<Selection> items = new ArrayList<Selection>();
			
			final NodeList nodes = document.getElementsByTagName(Constants.ITEM);
			for(int i=0;i<nodes.getLength();i++) {
				items.add(getSelection(nodes.item(i)));
			}
			
			return items;
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static Transaction getTransaction(Batch batch, Node node) throws DataAccessException {
		final Element element = (Element) node;
		
		try {
			final Transaction item = new Transaction(batch, Long.valueOf(element.getAttribute(Constants.ID))); // id
			item.setCampus(getCampus(element.getElementsByTagName(Constants.CAMPUS).item(0), false)); // campus
			item.setIndividual(getIndividual(element.getElementsByTagName(Constants.INDIVIDUAL).item(0), false)); // individual
			item.setDate(DateUtils.parseDate(element.getElementsByTagName(Constants.DATE).item(0))); // date
			item.setGrouping(getGrouping(element.getElementsByTagName(Constants.GROUPING).item(0))); // grouping
			item.setPaymentType(StringUtils.parseString(element.getElementsByTagName(Constants.PAYMENT_TYPE).item(0))); // payment_type
			item.setPaymentType(StringUtils.parseString(element.getElementsByTagName(Constants.CHECK_NUMBER).item(0))); // check_number
			
			item.setCOA(getCOA(element.getElementsByTagName(Constants.COA).item(0))); // coa
			item.setAmount(StringUtils.parseFloat(element.getElementsByTagName(Constants.AMOUNT).item(0))); // amount
			item.setTaxDeductible(StringUtils.parseBoolean(element.getElementsByTagName(Constants.TAX_DEDUCTIBLE).item(0))); // tax_deductible
			item.setNotes(StringUtils.parseString(element.getElementsByTagName(Constants.NOTE).item(0))); // note
	
			item.setCreator(getIndividual(element.getElementsByTagName(Constants.CREATOR).item(0), false)); // creator
			item.setModifier(getIndividual(element.getElementsByTagName(Constants.MODIFIER).item(0), false)); // modifier
			item.setCreated(DateUtils.parseTimestamp(element.getElementsByTagName(Constants.CREATED).item(0))); // created
			item.setModified(DateUtils.parseTimestamp(element.getElementsByTagName(Constants.MODIFIED).item(0))); // modified
			
			return item;
		} catch(ParseException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static List<Transaction> getBatch(Node node) throws DataAccessException {
		final Element element = (Element) node;
		
		try {
			final Batch item = new Batch(Long.valueOf(element.getAttribute(Constants.ID))); // id
			item.setCampus(getCampus(element.getElementsByTagName(Constants.CAMPUS).item(0), false)); // campus
			item.setPostDate(DateUtils.parseDate(element.getElementsByTagName(Constants.POST_DATE).item(0))); // post_date
			item.setBeginDate(DateUtils.parseDate(element.getElementsByTagName(Constants.POST_DATE).item(0))); // post_date
			item.setEndDate(DateUtils.parseDate(element.getElementsByTagName(Constants.POST_DATE).item(0))); // post_date
			item.setInAccountingPackage(StringUtils.parseBoolean(element.getElementsByTagName(Constants.IN_ACCOUNTING_PACKAGE).item(0))); // in_accounting_package
			item.setStatus(StringUtils.parseString(element.getElementsByTagName(Constants.STATUS).item(0))); // status
			item.setSource(StringUtils.parseString(element.getElementsByTagName(Constants.SOURCE).item(0))); // source
			
			// TODO verify that the correct node is being pulled
			item.setCreator(getIndividual(element.getElementsByTagName(Constants.CREATOR).item(0), false)); // creator
			item.setModifier(getIndividual(element.getElementsByTagName(Constants.MODIFIER).item(0), false)); // modifier
			item.setCreated(DateUtils.parseDate(element.getElementsByTagName(Constants.CREATED).item(0))); // created
			item.setModified(DateUtils.parseDate(element.getElementsByTagName(Constants.MODIFIED).item(0))); // modified
			
			final List<Transaction> transactions = new ArrayList<Transaction>();
			
			final NodeList nodes = element.getElementsByTagName(Constants.TRANSACTION);
			for(int i=0;i<nodes.getLength();i++) {
				transactions.add(getTransaction(item, nodes.item(i)));
			}
			
			return transactions;
		} catch(ParseException e) {
			throw new DataAccessException(e);
		}
	}
	
	public static Transaction getTransaction(String xml) throws DataAccessException {
		final List<Transaction> items = getTransactions(xml);
		
		if(items.size() != 1) {
			throw new NoDataFoundException(items.size() + " transactions found");
		}
		
		return items.get(0);
		
	}
	
	public static List<Transaction> getTransactions(String xml) throws DataAccessException {
		try {
			final Document document = XMLUtils.getDocument(xml);
			
			final NodeList items = document.getElementsByTagName(Constants.BATCH);
			if(items == null || items.getLength() == 0) {
				throw new NoDataFoundException(
					StringUtils.parseString(document.getElementsByTagName("message").item(0))
				);
			}
			
			final List<Transaction> transactions = new ArrayList<Transaction>();
			
			for(int i=0;i<items.getLength();i++) {
				final Node node = items.item(i);
				
				transactions.addAll(getBatch(node));
			}
			
			return transactions;
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static List<UserDefinedField> getUserDefinedTextFields(Node node) {
		final List<UserDefinedField> fields = new ArrayList<UserDefinedField>();
		
		final NodeList nodes = ((Element) node).getElementsByTagName(Constants.USER_DEFINED_TEXT_FIELD);
		for(int i=0;i<nodes.getLength();i++) {
			final Element element = (Element) nodes.item(i);
			
			final UserDefinedField field = new UserDefinedField(
				StringUtils.parseString(element.getElementsByTagName(Constants.NAME).item(0)),
				StringUtils.parseString(element.getElementsByTagName(Constants.LABEL).item(0)),
				StringUtils.parseString(element.getElementsByTagName(Constants.TEXT).item(0))
			);
			field.setAdminOnly(StringUtils.parseBoolean(element.getElementsByTagName(Constants.ADMIN_ONLY).item(0)));
			
			fields.add(field);
		}
		
		return fields;
	}
	
	protected static List<UserDefinedField> getUserDefinedDateFields(Node node) 
			throws DataAccessException {
		final List<UserDefinedField> fields = new ArrayList<UserDefinedField>();
		
		final NodeList nodes = ((Element) node).getElementsByTagName(Constants.USER_DEFINED_DATE_FIELD);
		
		try {
			for(int i=0;i<nodes.getLength();i++) {
				final Element element = (Element) nodes.item(i);
				
				final UserDefinedField field = new UserDefinedField(
					StringUtils.parseString(element.getElementsByTagName(Constants.NAME).item(0)),
					StringUtils.parseString(element.getElementsByTagName(Constants.LABEL).item(0)),
					DateUtils.parseDate(element.getElementsByTagName(Constants.DATE).item(0))
				);
				field.setAdminOnly(StringUtils.parseBoolean(element.getElementsByTagName(Constants.ADMIN_ONLY).item(0)));
				
				fields.add(field);
			}
		} catch(ParseException e) {
			throw new DataAccessException(e);
		}
		
		return fields;
	}
	
	protected static List<UserDefinedField> getUserDefinedPulldownFields(Node node) {
		final List<UserDefinedField> fields = new ArrayList<UserDefinedField>();
		
		final NodeList nodes = ((Element) node).getElementsByTagName(Constants.USER_DEFINED_PULLDOWN_FIELD);
		for(int i=0;i<nodes.getLength();i++) {
			final Element element = (Element) nodes.item(i);
			
			final UserDefinedField field = new UserDefinedField(
				StringUtils.parseString(element.getElementsByTagName(Constants.NAME).item(0)),
				StringUtils.parseString(element.getElementsByTagName(Constants.LABEL).item(0)),
				StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.SELECTION).item(0)).getAttribute(Constants.ID))
			);
			field.setAdminOnly(StringUtils.parseBoolean(element.getElementsByTagName(Constants.ADMIN_ONLY).item(0)));
			
			fields.add(field);
		}
		
		return fields;
	}
}
