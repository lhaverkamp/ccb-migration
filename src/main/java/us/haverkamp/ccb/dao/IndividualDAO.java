package us.haverkamp.ccb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import us.haverkamp.ccb.Constants;
import us.haverkamp.ccb.domain.Individual;
import us.haverkamp.utils.DateUtils;

public class IndividualDAO extends GenericDAO<Individual> {
	private static final String SQL_INSERT = 
		"INSERT INTO individual("
			+ "id, "
			+ "sync_id, "
			+ "other_id, "
			+ "giving_number, "
			+ "campus_id, "
			+ "family_id, "
			// TODO + "family_image, "
			+ "family_position, "
			+ "first_name, "
			+ "last_name, "
			+ "middle_name, "
			+ "legal_first_name, "
			+ "full_name, "
			+ "salutation, "
			+ "suffix, "
			// TODO + "image, "
			+ "login, "
			+ "email, "
			+ "allergies, "
			+ "confirmed_no_allergies, "
			+ "address_mailing_street, "
			+ "address_mailing_city,"
			+ "address_mailing_state,"
			+ "address_mailing_zip,"
			+ "address_mailing_line_1,"
			+ "address_mailing_line_2,"
			+ "address_home_street, "
			+ "address_home_city,"
			+ "address_home_state,"
			+ "address_home_zip,"
			+ "address_home_line_1,"
			+ "address_home_line_2,"
			+ "address_work_street, "
			+ "address_work_city,"
			+ "address_work_state,"
			+ "address_work_zip,"
			+ "address_work_line_1,"
			+ "address_work_line_2,"
			+ "address_other_street, "
			+ "address_other_city,"
			+ "address_other_state,"
			+ "address_other_zip,"
			+ "address_other_line_1,"
			+ "address_other_line_2,"
			+ "phone_contact,"
			+ "phone_home,"
			+ "phone_work,"
			+ "phone_emergency,"
			// TODO + "mobile_carrier_id,"
			+ "gender,"
			+ "marital_status,"
			+ "birthday,"
			+ "emergency_contact_name,"
			+ "anniversary,"
			+ "baptized,"
			+ "deceased,"
			// TODO + "membership_type_id,"
			+ "membership_date,"
			+ "membership_end,"
			+ "receive_email_from_church,"
			+ "default_new_group_messages,"
			+ "default_new_group_comments,"
			+ "default_new_group_digest,"
			+ "profile_listed,"
			+ "mailing_address_privacy_level_id,"
			+ "home_address_privacy_level_id,"
			+ "contact_phone_privacy_level_id,"
			+ "home_phone_privacy_level_id,"
			+ "work_phone_privacy_level_id,"
			+ "mobile_phone_privacy_level_id,"
			+ "emergency_phone_privacy_level_id,"
			+ "birthday_privacy_level_id,"
			+ "anniversary_privacy_level_id,"
			+ "gender_privacy_level_id,"
			+ "marital_status_privacy_level_id,"
			+ "user_defined_fields_privacy_level_id,"
			+ "allergies_privacy_level_id,"
			+ "active,"
			+ "creator,"
			+ "modifier,"
			+ "created,"
			+ "modified,"
			+ "udf_text_1,"
			+ "udf_text_2,"
			+ "udf_text_3,"
			+ "udf_text_4,"
			+ "udf_text_5,"
			+ "udf_text_6,"
			+ "udf_text_7,"
			+ "udf_text_8,"
			+ "udf_text_9,"
			+ "udf_text_10,"
			+ "udf_text_11,"
			+ "udf_text_12,"
			+ "udf_date_1,"
			+ "udf_date_2,"
			+ "udf_date_3,"
			+ "udf_date_4,"
			+ "udf_date_5,"
			+ "udf_date_6,"
			+ "udf_pulldown_1,"
			+ "udf_pulldown_2,"
			+ "udf_pulldown_3,"
			+ "udf_pulldown_4,"
			+ "udf_pulldown_5,"
			+ "udf_pulldown_6"
			+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
			+ "ON DUPLICATE KEY UPDATE "
			+ "sync_id = ?, "
			+ "other_id = ?, "
			+ "giving_number = ?, "
			+ "campus_id = ?, "
			+ "family_id = ?, "
			// TODO + "family_image = ?, "
			+ "family_position = ?, "
			+ "first_name = ?, "
			+ "last_name = ?, "
			+ "middle_name = ?, "
			+ "legal_first_name = ?, "
			+ "full_name = ?, "
			+ "salutation = ?, "
			+ "suffix = ?, "
			// TODO + "image = ?, "
			+ "login = ?, "
			+ "email = ?, "
			+ "allergies = ?, "
			+ "confirmed_no_allergies = ?, "
			+ "address_mailing_street = ?, "
			+ "address_mailing_city = ?,"
			+ "address_mailing_state = ?,"
			+ "address_mailing_zip = ?,"
			+ "address_mailing_line_1 = ?,"
			+ "address_mailing_line_2 = ?,"
			+ "address_home_street = ?, "
			+ "address_home_city = ?,"
			+ "address_home_state = ?,"
			+ "address_home_zip = ?,"
			+ "address_home_line_1 = ?,"
			+ "address_home_line_2 = ?,"
			+ "address_work_street = ?, "
			+ "address_work_city = ?,"
			+ "address_work_state = ?,"
			+ "address_work_zip = ?,"
			+ "address_work_line_1 = ?,"
			+ "address_work_line_2 = ?,"
			+ "address_other_street = ?, "
			+ "address_other_city = ?,"
			+ "address_other_state = ?,"
			+ "address_other_zip = ?,"
			+ "address_other_line_1 = ?,"
			+ "address_other_line_2 = ?,"
			+ "phone_contact = ?,"
			+ "phone_home = ?,"
			+ "phone_work = ?,"
			+ "phone_emergency = ?,"
			// TODO + "mobile_carrier_id = ?,"
			+ "gender = ?,"
			+ "marital_status = ?,"
			+ "birthday = ?,"
			+ "emergency_contact_name = ?,"
			+ "anniversary = ?,"
			+ "baptized = ?,"
			+ "deceased = ?,"
			// TODO + "membership_type_id = ?,"
			+ "membership_date = ?,"
			+ "membership_end = ?,"
			+ "receive_email_from_church = ?,"
			+ "default_new_group_messages = ?,"
			+ "default_new_group_comments = ?,"
			+ "default_new_group_digest = ?,"
			+ "profile_listed = ?,"
			+ "mailing_address_privacy_level_id = ?,"
			+ "home_address_privacy_level_id = ?,"
			+ "contact_phone_privacy_level_id = ?,"
			+ "home_phone_privacy_level_id = ?,"
			+ "work_phone_privacy_level_id = ?,"
			+ "mobile_phone_privacy_level_id = ?,"
			+ "emergency_phone_privacy_level_id = ?,"
			+ "birthday_privacy_level_id = ?,"
			+ "anniversary_privacy_level_id = ?,"
			+ "gender_privacy_level_id = ?,"
			+ "marital_status_privacy_level_id = ?,"
			+ "user_defined_fields_privacy_level_id = ?,"
			+ "allergies_privacy_level_id = ?,"
			+ "active = ?,"
			+ "creator = ?,"
			+ "modifier = ?,"
			+ "created = ?,"
			+ "modified = ?,"
			+ "udf_text_1 = ?,"
			+ "udf_text_2 = ?,"
			+ "udf_text_3 = ?,"
			+ "udf_text_4 = ?,"
			+ "udf_text_5 = ?,"
			+ "udf_text_6 = ?,"
			+ "udf_text_7 = ?,"
			+ "udf_text_8 = ?,"
			+ "udf_text_9 = ?,"
			+ "udf_text_10 = ?,"
			+ "udf_text_11 = ?,"
			+ "udf_text_12 = ?,"
			+ "udf_date_1 = ?,"
			+ "udf_date_2 = ?,"
			+ "udf_date_3 = ?,"
			+ "udf_date_4 = ?,"
			+ "udf_date_5 = ?,"
			+ "udf_date_6 = ?,"
			+ "udf_pulldown_1 = ?,"
			+ "udf_pulldown_2 = ?,"
			+ "udf_pulldown_3 = ?,"
			+ "udf_pulldown_4 = ?,"
			+ "udf_pulldown_5 = ?,"
			+ "udf_pulldown_6 = ?"
			;
	
	public List<Individual> findBy() throws DataAccessException {
		final String xml = get("individual_profiles");
		
		return getItems(xml);
	}
	
	public int[] update(List<Individual> items) throws DataAccessException {
		try {
			final Connection connection = getConnection();
			
			try {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				
				try {
					for(Individual item : items) {
						int i = 1;
						
						ps.setLong(i++, item.getId());
						
						for(int y=0;y<2;y++) { // set once for insert and once for update
							ps.setObject(i++, item.getSyncId()); // sync_id
							ps.setString(i++, item.getOtherId()); // other_id
							ps.setObject(i++, item.getGivingNumber()); // giving_number
							ps.setObject(i++, item.getCampus().getId()); // campus_id
							ps.setObject(i++, item.getFamily().getId()); // family_id
							// family_image, 
							ps.setObject(i++, item.getFamilyPosition()); // family_position
							ps.setString(i++, item.getFirstName()); // first_name
							ps.setString(i++, item.getLastName()); // last_name
							ps.setString(i++, item.getMiddleName()); // middle_name
							ps.setString(i++, item.getLegalFirstName()); // legal_first_name,
							ps.setString(i++, item.getFirstName());	// full_name,
							ps.setString(i++, item.getPrefix()); // salutation,
							ps.setString(i++,  item.getSuffix()); // suffix, 
							// image, 
							ps.setObject(i++, item.getLogin()); // login, 
							ps.setObject(i++, item.getEmail()); // email, 
							ps.setObject(i++, item.getAllergies()); // allergies, 
							ps.setObject(i++, item.getConfirmedNoAllergies()); // confirmed_no_allergies, 
							ps.setObject(i++, item.getContact().getStreet()); // address_mailing_street, 
							ps.setObject(i++, item.getContact().getCity()); // address_mailing_city,
							ps.setObject(i++, item.getContact().getState()); // address_mailing_state,
							ps.setObject(i++, item.getContact().getZip()); // address_mailing_zip,
							ps.setObject(i++, item.getContact().getLine1()); // address_mailing_line_1,
							ps.setObject(i++, item.getContact().getLine2()); // address_mailing_line_2,
							ps.setObject(i++, item.getHome().getStreet()); // address_home_street, 
							ps.setObject(i++, item.getHome().getCity()); // address_home_city,
							ps.setObject(i++, item.getHome().getState()); // address_home_state,
							ps.setObject(i++, item.getHome().getZip()); // address_home_zip,
							ps.setObject(i++, item.getHome().getLine1()); // address_home_line_1,
							ps.setObject(i++, item.getHome().getLine2()); // address_home_line_2,
							ps.setObject(i++, item.getWork().getStreet()); // address_work_street, 
							ps.setObject(i++, item.getWork().getCity()); // address_work_city,
							ps.setObject(i++, item.getWork().getState()); // address_work_state,
							ps.setObject(i++, item.getWork().getZip()); // address_work_zip,
							ps.setObject(i++, item.getWork().getLine1()); // address_work_line_1,
							ps.setObject(i++, item.getWork().getLine2()); // address_work_line_2,
							ps.setObject(i++, item.getOther().getStreet()); // address_other_street, 
							ps.setObject(i++, item.getOther().getCity()); // address_other_city,
							ps.setObject(i++, item.getOther().getState()); // address_other_state,
							ps.setObject(i++, item.getOther().getZip()); // address_other_zip,
							ps.setObject(i++, item.getOther().getLine1()); // address_other_line_1,
							ps.setObject(i++, item.getOther().getLine2()); // address_other_line_2,
							ps.setObject(i++, item.getContactPhone()); // phone_contact,
							ps.setObject(i++, item.getHomePhone()); // phone_home,
							ps.setObject(i++, item.getWorkPhone()); // phone_work,
							ps.setObject(i++, item.getEmergencyPhone()); // phone_emergency,
							// mobile_carrier_id,
							ps.setObject(i++, item.getGender()); // gender,
							ps.setObject(i++, item.getMaritalStatus()); // marital_status
							ps.setObject(i++, DateUtils.toString(item.getBirthday())); // birthday,
							ps.setObject(i++, item.getEmergencyContact()); // emergency_contact_name,
							ps.setObject(i++, DateUtils.toString(item.getAnniversary())); // anniversary,
							ps.setObject(i++, item.getBaptized()); // baptized
							ps.setObject(i++, DateUtils.toString(item.getDeceased())); // deceased
							// membership_type_id,
							ps.setObject(i++, DateUtils.toString(item.getMembershipDate())); // membership_date,
							ps.setObject(i++, DateUtils.toString(item.getMembershipEnd())); // membership_end
							ps.setObject(i++, item.getCommunicationPreferences().getReceiveEmailFromChurch()); // receive_email_from_church,
							ps.setObject(i++, item.getCommunicationPreferences().getDefaultNewGroupMessages()); // default_new_group_messages,
							ps.setObject(i++, item.getCommunicationPreferences().getDefaultNewGroupComments()); // default_new_group_comments,
							ps.setObject(i++, item.getCommunicationPreferences().getDefaultNewGroupDigest()); // default_new_group_digest,
							ps.setObject(i++, item.getPrivacySettings().getListed()); // profile_listed,
							ps.setObject(i++, item.getPrivacySettings().getMailingAddress()); // mailing_address_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getHomeAddress()); // home_address_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getContactPhone()); // contact_phone_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getHomePhone()); // home_phone_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getWorkPhone()); // work_phone_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getMobilePhone()); // mobile_phone_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getEmergencyPhone()); // emergency_phone_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getBirthday()); // birthday_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getAnniversary()); // anniversary_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getGender()); // gender_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getMartialStatus()); // marital_status_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getUserDefinedFields()); // user_defined_fields_privacy_level_id,
							ps.setObject(i++, item.getPrivacySettings().getAllergies()); // allergies_privacy_level_id,
							ps.setObject(i++, item.getActive()); // active,
							ps.setObject(i++, item.getCreator().getId()); // creator
							ps.setObject(i++, item.getModifier().getId()); // modifier
							ps.setString(i++, DateUtils.toString(item.getCreated(), DateUtils.TIMESTAMP)); //created
							ps.setString(i++, DateUtils.toString(item.getModified(), DateUtils.TIMESTAMP)); // modified
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_1)); // udf_text_1,
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_2)); // udf_text_2,
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_3)); // udf_text_3,
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_4)); // udf_text_4,
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_5)); // udf_text_5,
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_6)); // udf_text_6,
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_7)); // udf_text_7,
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_8)); // udf_text_8,
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_9)); // udf_text_9,
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_10)); // udf_text_10,
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_11)); // udf_text_11,
							ps.setObject(i++, item.getUserDefinedTextField(Constants.UDF_TEXT_12)); // udf_text_12,
							ps.setObject(i++, item.getUserDefinedDateField(Constants.UDF_DATE_1)); // udf_date_1,
							ps.setObject(i++, item.getUserDefinedDateField(Constants.UDF_DATE_2)); // udf_date_2,
							ps.setObject(i++, item.getUserDefinedDateField(Constants.UDF_DATE_3)); // udf_date_3,
							ps.setObject(i++, item.getUserDefinedDateField(Constants.UDF_DATE_4)); // udf_date_4,
							ps.setObject(i++, item.getUserDefinedDateField(Constants.UDF_DATE_5)); // udf_date_5
							ps.setObject(i++, item.getUserDefinedDateField(Constants.UDF_DATE_6)); // udf_date_6,
							ps.setObject(i++, item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_1)); // udf_pulldown_1,
							ps.setObject(i++, item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_2)); // udf_pulldown_2,
							ps.setObject(i++, item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_3)); // udf_pulldown_3,
							ps.setObject(i++, item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_4)); // udf_pulldown_4,
							ps.setObject(i++, item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_5)); // udf_pulldown_5,
							ps.setObject(i++, item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_6)); // udf_pulldown_6			
						}
						
						ps.addBatch();
					}
					
					return ps.executeBatch();
				} finally {
					ps.close();
				}
			} finally {
				connection.close();
			}
		} catch(SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	protected Individual getItem(String xml) throws DataAccessException {
		return Mapper.getIndividual(xml);
	}

	@Override
	protected List<Individual> getItems(String xml) throws DataAccessException {
		return Mapper.getIndividuals(xml);
	}
}
