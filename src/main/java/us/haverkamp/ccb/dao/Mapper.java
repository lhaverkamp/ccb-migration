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
import us.haverkamp.ccb.domain.Campus;
import us.haverkamp.ccb.domain.CommunicationPreferences;
import us.haverkamp.ccb.domain.Event;
import us.haverkamp.ccb.domain.Family;
import us.haverkamp.ccb.domain.Group;
import us.haverkamp.ccb.domain.Individual;
import us.haverkamp.ccb.domain.PrivacySettings;
import us.haverkamp.ccb.domain.Selection;
import us.haverkamp.ccb.domain.UserDefinedField;
import us.haverkamp.utils.DateUtils;
import us.haverkamp.utils.StringUtils;
import us.haverkamp.utils.XmlUtils;

public class Mapper {
	protected static Address getAddress(Node node) {
		final Element element = (Element) node;
		
		final Address address = new Address(
			StringUtils.parseString(element.getElementsByTagName(Constants.STREET_ADDRESS).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.CITY).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.STATE).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.ZIP).item(0))
		);
		
		address.setCountry(StringUtils.parseString(element.getElementsByTagName(Constants.COUNTRY).item(0)));		
		address.setLine1(StringUtils.parseString(element.getElementsByTagName(Constants.LINE_1).item(0)));		
		address.setLine2(StringUtils.parseString(element.getElementsByTagName(Constants.LINE_2).item(0)));
		
		return address;
	}
	
	protected static Campus getCampus(Node node) {
		final Element element = (Element) node;
		
		final Campus item = new Campus(
			Long.valueOf(element.getAttribute(Constants.ID)),
			StringUtils.parseString(element)
		);
		
		return item;
	}
	
	protected static CommunicationPreferences getCommunicationPreferences(Node node) {
		final Element element = (Element) node;
		
		final CommunicationPreferences communicationPreferences = new CommunicationPreferences(
			StringUtils.parseBoolean(element.getElementsByTagName(Constants.RECEIVE_EMAIL_FROM_CHURCH).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.DEFAULT_NEW_GROUP_MESSAGES).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.DEFAULT_NEW_GROUP_COMMENTS).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.DEFAULT_NEW_GROUP_DIGEST).item(0)),
			StringUtils.parseString(element.getElementsByTagName(Constants.DEFAULT_NEW_GROUP_SMS).item(0))
		);
		
		return communicationPreferences;
	}

	protected static Event getEvent(Node node) throws DataAccessException {
		final Element element = (Element) node;
		
		try {
			final Event event = new Event(Long.valueOf(element.getAttribute(Constants.ID)));
			
			event.setDate(DateUtils.parseTimestamp(element.getElementsByTagName("occurrence").item(0)));
			event.setAttendees(Mapper.getIndividuals(element, Constants.ATTENDEE));
		
			return event;
		} catch(ParseException e) {
			throw new DataAccessException(e);
		}
	}

	public static Event getEvent(String xml) throws NoDataFoundException, DataAccessException {
		try {
			final Document document = XmlUtils.getDocument(xml);
			
			final NodeList items = document.getElementsByTagName(Constants.EVENT);
			// TODO error handling
			if(items == null || items.getLength() == 0) {
				throw new NoDataFoundException(
					document.getElementsByTagName("message").item(0).getTextContent()
				);
			}
			
			return Mapper.getEvent(items.item(0));
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}

	public static List<Event> getEvents(String xml) throws DataAccessException {
		try {
			final Document document = XmlUtils.getDocument(xml);
			
			final NodeList items = document.getElementsByTagName(Constants.EVENT);
			if(items == null || items.getLength() == 0) {
				throw new NoDataFoundException(
					document.getElementsByTagName("message").item(0).getTextContent()
				);
			}
			
			final List<Event> events = new ArrayList<Event>();
			for(int i=0;i<items.getLength();i++) {
				final Node node = items.item(i);
				
				events.add(Mapper.getEvent(node));
			}
			
			return events;
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static Family getFamily(Node node) throws DataAccessException {
		return Mapper.getFamily(node, true);
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
		final List<Family> families = getFamilies(xml);
		
		if(families.size() != 1) {
			throw new NoDataFoundException(families.size() + " families found");
		}
		
		return families.get(0);
	}
	
	public static List<Family> getFamilies(String xml) throws NoDataFoundException, DataAccessException {
		try {
			final Document document = XmlUtils.getDocument(xml);
			
			final NodeList nodes = document.getElementsByTagName(Constants.FAMILY);
			if(nodes == null) {
				throw new NoDataFoundException(
					document.getElementsByTagName("message").item(0).getTextContent()
				);
			}
			
			final List<Family> items = new ArrayList<Family>();
			for(int i=0;i<nodes.getLength();i++) {
				items.add(Mapper.getFamily(nodes.item(i)));
			}
			
			return items;
		} catch(IOException | SAXException | ParserConfigurationException e) {
			throw new DataAccessException(e);
		}
	}

	protected static Individual getIndividual(Node node) throws DataAccessException {
		return Mapper.getIndividual(node, true);
	}

	protected static Individual getIndividual(Node node, boolean all) throws DataAccessException {
		final Element element = (Element) node;
		try {
			final Individual item = new Individual(Long.valueOf(element.getAttribute(Constants.ID)));
			
			if(all) {
				item.setSyncId(StringUtils.parseLong(element.getElementsByTagName(Constants.SYNC_ID).item(0)));
				item.setOtherId(StringUtils.parseString(element.getElementsByTagName(Constants.OTHER_ID).item(0)));
				item.setGivingNumber(StringUtils.parseInt(element.getElementsByTagName(Constants.GIVING_NUMBER).item(0))); // TODO may need to be a string
				item.setCampus(Mapper.getCampus(element.getElementsByTagName(Constants.CAMPUS).item(0)));
				item.setFamily(Mapper.getFamily(element.getElementsByTagName(Constants.FAMILY).item(0), false));
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
				item.setMembershipType(StringUtils.parseString(element.getElementsByTagName(Constants.MEMBERSHIP_TYPE).item(0)));;
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
			final Document document = XmlUtils.getDocument(xml);
			
			final Node node = document.getElementsByTagName(Constants.INDIVIDUAL).item(0);
			if(node == null) {
				throw new NoDataFoundException(
					document.getElementsByTagName("message").item(0).getTextContent()
				);
			}
			
			return Mapper.getIndividual(node);
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}
	
	private static List<Individual> getIndividuals(Element element, String node) throws DataAccessException {
		final List<Individual> items = new ArrayList<Individual>();
		
		final NodeList nodes = element.getElementsByTagName(node);
		for(int i=0;i<nodes.getLength();i++) {
			items.add(Mapper.getIndividual(nodes.item(i), false));
		}
		
		return items;
	}

	public static List<Individual> getIndividuals(String xml) throws DataAccessException {
		try {
			final Document document = XmlUtils.getDocument(xml);
			
			NodeList nodes = document.getElementsByTagName(Constants.INDIVIDUALS);
			if(nodes == null) {
				throw new NoDataFoundException(
					document.getElementsByTagName("message").item(0).getTextContent()
				);
			}
			
			final Element parent = (Element) nodes.item(0);
			nodes = parent.getElementsByTagName(Constants.INDIVIDUAL);
			
			final List<Individual> items = new ArrayList<Individual>();
			for(int i=0;i<nodes.getLength();i++) {
				final Element item = (Element) nodes.item(i);
				
				if(item.getParentNode().isSameNode(parent)) {
					items.add(Mapper.getIndividual(item));
				}
			}
			
			return items;
		} catch(IOException | SAXException | ParserConfigurationException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static Group getGroup(Node node) {
		final Element element = (Element) node;
		
		final Group group = new Group(Long.valueOf(element.getAttribute(Constants.ID)));
		group.setName(StringUtils.parseString(element.getElementsByTagName(Constants.NAME).item(0)));
		group.setDescription(StringUtils.parseString(element.getElementsByTagName(Constants.DESCRIPTION).item(0)));
		
		return group;
	}
	
	public static Group getGroup(String xml) throws NoDataFoundException, DataAccessException {
		try {
			final Document document = XmlUtils.getDocument(xml);
			
			final Node node = document.getElementsByTagName(Constants.GROUP).item(0);
			if(node == null) {
				throw new NoDataFoundException(
					document.getElementsByTagName("message").item(0).getTextContent()
				);
			}
			
			return Mapper.getGroup(node);
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}
	
	public static List<Group> getGroups(String xml) throws DataAccessException {
		try {
			final Document document = XmlUtils.getDocument(xml);
			
			final List<Group> items = new ArrayList<Group>();
	
			final NodeList nodes = document.getElementsByTagName(Constants.GROUP);
			for(int i=0;i<nodes.getLength();i++) {
				items.add(Mapper.getGroup(nodes.item(i)));
			}
	
			return items;
		} catch(IOException | ParserConfigurationException | SAXException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected static PrivacySettings getPrivacySettings(Node node) {
		final Element element = (Element) node;
		
		final PrivacySettings privacySettings = new PrivacySettings(
			StringUtils.parseBoolean(element.getElementsByTagName(Constants.PROFILE_LISTED).item(0)),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.MAILING_ADDRESS).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.HOME_ADDRESS).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.CONTACT_PHONE).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.HOME_PHONE).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.WORK_PHONE).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.MOBILE_PHONE).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.EMERGENCY_PHONE).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.BIRTHDAY).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.ANNIVERSARY).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.GENDER).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.MARITAL_STATUS).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.USER_DEFINED_FIELDS).item(0)).getAttribute(Constants.ID)).intValue(),
			StringUtils.parseInt(((Element) element.getElementsByTagName(Constants.ALLERGIES).item(0)).getAttribute(Constants.ID)).intValue()
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
			final Document document = XmlUtils.getDocument(xml);
			
			final List<Selection> items = new ArrayList<Selection>();
			
			final NodeList nodes = document.getElementsByTagName(Constants.ITEM);
			for(int i=0;i<nodes.getLength();i++) {
				items.add(Mapper.getSelection(nodes.item(i)));
			}
			
			return items;
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