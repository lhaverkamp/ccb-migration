package us.haverkamp.ccb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.haverkamp.ccb.domain.Event;
import us.haverkamp.ccb.domain.Individual;

public class IndividualDAO extends GenericDAO<Individual> {
	private static final String COLUMNS = 
		"	t.family_position, " +
		"	t.limited_access_user, " +
		"	t.prefix, " +
		"	t.first_name, " +
		"	t.middle_name, " +
		"	t.last_name, " +
		"	t.suffix, " +
		"	IF(t.email_individual IS NOT NULL, t.email_individual, IF(t.email_work IS NOT NULL, t.email_work, IF(t.family_position IN ('Primary Contact', 'Spouse'), t.email_family, NULL))) AS email, " +
		"	t.area_of_town, " +
		"	t.address_mailing_street1, " +
		"	t.address_mailing_city, " +
		"	t.address_mailing_state, " +
		"	t.address_mailing_postal_code, " +
		"	t.address_mailing_carrier_route, " +
		"	t.phone_contact, " +
		"	t.phone_home, " +
		"	t.phone_work, " +
		"	t.phone_mobile, " +
		"	t.fax, " +
		"	t.pager, " +
		"	t.emergency_phone, " +
		"	t.emergency_contact_name, " +
		"	t.birthdate, " +
		"	t.anniversary, " +
		"   t.deceased, " + 
		"	t.gender, " +
		"	t.giving_number, " +
		"	t.marital_status, " +
		"	t.area_of_town, " +
		"	t.address_home_street1, " +
		"	t.address_home_city, " +
		"	t.address_home_state, " +
		"	t.address_home_postal_code, " +
		"	t.address_work_street1, " +
		"	t.address_work_city, " +
		"	t.address_work_state, " +
		"	t.address_work_postal_code, " +
		"	t.address_other_street1, " +
		"	t.address_other_city, " +
		"	t.address_other_state, " +
		"	t.address_other_postal_code, " +
		"	t.job_title, " +
		"	t.school, " +
		"	t.school_grade, " +
		"	t.known_allergies, " +
		"	t.military, " +
		"	t.confirmation_verse, " +
		"	t.email_work, " +
		"	t.maiden_name, " +
		"	t.first_communion_date, " +
		"	t.confirmation_date, " +
		"	t.baptism_date, " +
		"	t.elder, " +
		"	t.newsletter, " +
		"	t.confirmed, " +
		"	t.membership_date, " +
		"	t.membership_stop_date, " +
		"	t.membership_type, " +
		"	t.baptized, " +
		"	t.listed, " +
		"	t.inactive, " +
		"	t.how_they_heard, " +
		"	t.reason_left_church," +
		"	t.other_id ";
	
	public static final String SQL_FIND_BY_EVENT_ID =
		"SELECT " +
		"	event_id, " +
		"	event, " +
		"	occurance, " +
		"	attendee_id, " +
		"	custom_report.individual_id, " +
		"	custom_report.family_id, " +
		COLUMNS +
		"FROM ss_attendance " +
		"INNER JOIN ss_individual t ON ss_attendance.attendee_id = t.individual_id " +
		"INNER JOIN custom_report ON t.other_id = custom_report.other_id " +
		"WHERE ss_attendance.event_id = ?" +
		"  AND ss_attendance.occurance = ?";
	
	public static final String SQL_UPDATE = 
		"SELECT " +
		"	individual_export.individual_id, " +
		"	individual_export.family_id, " +
		COLUMNS +
		"FROM individual_export " +
		"INNER JOIN custom_report ON individual_export.individual_id = custom_report.individual_id " +
		"INNER JOIN ss_individual t ON custom_report.other_id = t.other_id " +
		"WHERE individual_export.modified_by IN ('System', 'Laura Haverkamp') " +
		"  AND NOT (/*individual_export.family_position = t.family_position " +
		"  AND */individual_export.limited_access_user = t.limited_access_user " +
		"  AND individual_export.prefix <=> t.prefix " +
		"  AND individual_export.first_name = t.first_name " +
		"  AND individual_export.middle_name <=> t.middle_name " +
		"  AND individual_export.last_name = t.last_name " +
		"  AND individual_export.suffix <=> t.suffix " +
		"  AND individual_export.email <=> IF(t.email_individual IS NOT NULL, t.email_individual, IF(t.email_work IS NOT NULL, t.email_work, IF(t.family_position IN ('Primary Contact', 'Spouse'), t.email_family, NULL))) " +
//		"  AND individual_export.area_of_town <=> t.area_of_town " +
//		"  AND individual_export.mailing_street <=> t.address_mailing_street1 " +
//		"  AND individual_export.mailing_city <=> t.address_mailing_city " +
//		"  AND individual_export.mailing_state <=> t.address_mailing_state " +
//		"  AND individual_export.mailing_zip <=> t.address_mailing_postal_code " +
//		"  AND individual_export.mailing_carrier_route <=> t.address_mailing_carrier_route " +
		"  AND REPLACE(individual_export.contact_phone, '+1', '') <=> t.phone_contact " +
		"  AND REPLACE(individual_export.home_phone, '+1', '') <=> t.phone_home " +
		"  AND REPLACE(individual_export.work_phone, '+1', '') <=> t.phone_work " +
		"  AND REPLACE(individual_export.mobile_phone, '+1', '') <=> t.phone_mobile " +
//		"  AND individual_export.fax <=> t.fax " +
//		"  AND individual_export.pager <=> t.pager " +
		"  AND individual_export.emergency_phone <=> t.emergency_phone " +
		"  AND individual_export.emergency_contact_name <=> t.emergency_contact_name " +
		"  AND individual_export.birthday <=> t.birthdate " +
		"  AND individual_export.anniversary <=> t.anniversary " +
		"  AND t.deceased IS NULL " +
		"  AND SUBSTR(individual_export.gender, 1, 1) <=> t.gender " +
		"  AND individual_export.giving_number <=> t.giving_number " +
//		"  AND individual_export.marital_status <=> t.marital_status " +
//		"  AND individual_export.home_area <=> t.area_of_town " +
//		"  AND individual_export.home_street <=> t.address_home_street1 " +
//		"  AND individual_export.home_city <=> t.address_home_city " +
//		"  AND individual_export.home_state <=> t.address_home_state " +
//		"  AND individual_export.home_zip <=> t.address_home_postal_code " +
		"  AND individual_export.work_street <=> t.address_work_street1 " +
		"  AND individual_export.work_city <=> t.address_work_city " +
		"  AND individual_export.work_state <=> t.address_work_state " +
		"  AND individual_export.work_zip <=> t.address_work_postal_code " +
//		"  AND individual_export.other_street <=> t.address_other_street1 " +
//		"  AND individual_export.other_city <=> t.address_other_city " +
//		"  AND individual_export.other_state <=> t.address_other_state " +
//		"  AND individual_export.other_zip <=> t.address_other_postal_code " +
		"  AND individual_export.work_title <=> t.job_title " +
//		"  AND individual_export.school_name <=> t.school " +
//		"  AND individual_export.school_grade <=> t.school_grade " +
		"  AND individual_export.allergies <=> t.known_allergies " +
//		"  AND individual_export.military <=> t.military " +
		"  AND individual_export.confirmation_verse <=> t.confirmation_verse " +
		"  AND individual_export.email_work <=> t.email_work " +
		"  AND individual_export.maiden_name <=> t.maiden_name " +
		"  AND individual_export.first_communion <=> t.first_communion_date " +
		"  AND individual_export.confirmation <=> t.confirmation_date " +
		"  AND individual_export.baptism <=> t.baptism_date " +
		"  AND individual_export.elder <=> t.elder " +
		"  AND individual_export.newsletter <=> IF(t.newsletter, 'Yes', 'No') " +
		"  AND individual_export.confirmed <=> IF(t.confirmed, 'Yes', 'No') " +
		"  AND individual_export.membership_start_date <=> t.membership_date " +
		"  AND individual_export.membership_end_date <=> t.membership_stop_date " +
		"  AND individual_export.membership_type <=> t.membership_type " +
		"  AND individual_export.baptized <=> t.baptized " +
		"  AND (individual_export.listed <=> t.listed OR individual_export.membership_type = 'Deceased') " +
		"  AND (individual_export.inactive <=> t.inactive OR individual_export.membership_type = 'Deceased') " +
		"  AND individual_export.how_they_heard <=> t.how_they_heard " +
		"  AND individual_export.reason_left_church <=> t.reason_left_church " +
		") AND individual_export.individual_id = 1321";
	
	private Map<String, Integer> membershipType;
	private Map<String, Integer> elders;
	private Map<String, Integer> newsletter;
	private Map<String, Integer> confirmed;
	
	public List<Individual> findBy(Event event) throws DataAccessException {
		return findBy(SQL_FIND_BY_EVENT_ID, new Object[] { 
			event.getId(),
			event.getDate()
		});
	}
	
	@Override
	protected Individual getItem(ResultSet rs) throws DataAccessException {
		try {
			return Mapper.getIndividual(rs);
		} catch(SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public String update(Individual individual) throws DataAccessException {
		final String srv = "update_individual&individual_id=" + individual.getId();

		// add params
		final List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("first_name", toString(individual.getFirstName())));
		params.add(new BasicNameValuePair("last_name", toString(individual.getLastName())));
		params.add(new BasicNameValuePair("middle_name", toString(individual.getMiddleName())));
		//params.add(new BasicNameValuePair("legal_first_name", toString(individual.getLegalFirstName())));
		//sync_id
		//params.add(new BasicNameValuePair("other_id", individual.getOtherId()));
		params.add(new BasicNameValuePair("salutation", toString(individual.getPrefix())));
		params.add(new BasicNameValuePair("suffix", toString(individual.getSuffix())));
		
		//campus_id
		//family_id
		//TODO params.add(new BasicNameValuePair("family_position", toString(individual.getFamilyPosition())));
		params.add(new BasicNameValuePair("gender", toString(individual.getGender())));
		params.add(new BasicNameValuePair("birthday", toString(individual.getBirthday())));
		params.add(new BasicNameValuePair("anniversary", toString(individual.getAnniversary())));
		params.add(new BasicNameValuePair("deceased", toString(individual.getDeceased())));
		params.add(new BasicNameValuePair("membership_date", toString(individual.getMembershipStartDate())));
		params.add(new BasicNameValuePair("membership_end", toString(individual.getMembershipStopDate())));
		params.add(new BasicNameValuePair("membership_type_id", getMembershipType().get(individual.getMembershipType()).toString()));

		params.add(new BasicNameValuePair("giving_number", toString(individual.getGivingNo())));
		params.add(new BasicNameValuePair("email", toString(individual.getEmail())));
		
		params.add(new BasicNameValuePair("mailing_street_address", toString(individual.getContact().getStreet())));
		params.add(new BasicNameValuePair("mailing_city", toString(individual.getContact().getCity())));
		params.add(new BasicNameValuePair("mailing_state", toString(individual.getContact().getState())));
		params.add(new BasicNameValuePair("mailing_zip", toString(individual.getContact().getZip())));
		//mailing_country
		
		params.add(new BasicNameValuePair("home_street_address", toString(individual.getHome().getStreet())));
		params.add(new BasicNameValuePair("home_city", toString(individual.getHome().getCity())));
		params.add(new BasicNameValuePair("home_state", toString(individual.getHome().getState())));
		params.add(new BasicNameValuePair("home_zip", toString(individual.getHome().getZip())));
		//home_country
		
		params.add(new BasicNameValuePair("work_street_address", toString(individual.getWork().getStreet())));
		params.add(new BasicNameValuePair("work_city", toString(individual.getWork().getCity())));
		params.add(new BasicNameValuePair("work_state", toString(individual.getWork().getState())));
		params.add(new BasicNameValuePair("work_zip", toString(individual.getWork().getZip())));
		//work_country
		params.add(new BasicNameValuePair("work_title", toString(individual.getWorkTitle())));
		
		params.add(new BasicNameValuePair("other_street_address", toString(individual.getOther().getStreet())));
		params.add(new BasicNameValuePair("other_city", toString(individual.getOther().getCity())));
		params.add(new BasicNameValuePair("other_state", toString(individual.getOther().getState())));
		params.add(new BasicNameValuePair("other_zip", toString(individual.getOther().getZip())));
		//other_country
		
		params.add(new BasicNameValuePair("contact_phone", toString(individual.getContactPhone())));
		params.add(new BasicNameValuePair("home_phone", toString(individual.getHomePhone())));
		params.add(new BasicNameValuePair("work_phone", toString(individual.getWorkPhone())));
		params.add(new BasicNameValuePair("mobile_phone", toString(individual.getMobilePhone())));
		params.add(new BasicNameValuePair("phone_emergency", toString(individual.getEmergencyPhone())));
		params.add(new BasicNameValuePair("emergency_contact_name", toString(individual.getEmergencyContact())));
		params.add(new BasicNameValuePair("allergies", toString(individual.getAllergies())));
		
		params.add(new BasicNameValuePair("udf_text_10", toString(individual.getConfirmationVerse()))); // Confirmation Verse
		params.add(new BasicNameValuePair("udf_text_11", toString(individual.getEmailWork()))); // Email Work
		params.add(new BasicNameValuePair("udf_text_12", toString(individual.getMaidenName()))); // Maiden Name
		
		params.add(new BasicNameValuePair("udf_date_4", toString(individual.getFirstCommunion()))); // First Communion 
		params.add(new BasicNameValuePair("udf_date_5", toString(individual.getConfirmation()))); // Confirmation
		params.add(new BasicNameValuePair("udf_date_6", toString(individual.getBaptism()))); // Baptism
		
		if(individual.getElder() != null) {
			params.add(new BasicNameValuePair("udf_pulldown_4", getElders().get(individual.getElder()).toString())); // Elder
		}
		params.add(new BasicNameValuePair("udf_pulldown_5", getNewsletter().get(individual.getNewsletter() ? "Yes" : "No").toString())); // Newsletter
		params.add(new BasicNameValuePair("udf_pulldown_6", getConfirmed().get(individual.getConfirmed() ? "Yes" : "No").toString())); // Confirmed
		
		//modifier_id

		return post(srv, params);
	}
	
	private Map<String, Integer> getMembershipType() throws DataAccessException {
		if(this.membershipType == null) {
			this.membershipType = getLookupTable("membership_type_list");
		}
		
		return this.membershipType;
	}

	private Map<String, Integer> getElders() throws DataAccessException {
		if(this.elders == null) {
			this.elders = getLookupTable("udf_ind_pulldown_4_list");
		}
		
		return this.elders;
	}
	
	private Map<String, Integer> getNewsletter() throws DataAccessException {
		if(this.newsletter == null) {
			this.newsletter = getLookupTable("udf_ind_pulldown_5_list");
		}
		
		return this.newsletter;
	}
	
	private Map<String, Integer> getConfirmed() throws DataAccessException {
		if(this.confirmed == null) {
			this.confirmed = getLookupTable("udf_ind_pulldown_6_list");
		}
		
		return this.confirmed;
	}
}
