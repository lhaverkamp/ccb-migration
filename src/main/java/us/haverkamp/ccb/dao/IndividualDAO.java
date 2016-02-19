package us.haverkamp.ccb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
			//+ "family_image, "
			+ "family_position, "
			+ "first_name, "
			+ "last_name, "
			+ "middle_name, "
			+ "legal_first_name, "
			+ "full_name, "
			+ "salutation, "
			+ "suffix, "
			/*
			+ "image, "
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
			// TODO home, work, other
			+ "phone_contact,"
			+ "phone_home,"
			+ "phone_work,"
			+ "phone_emergency,"
			+ "mobile_carrier_id,"
			+ "gender,"
			+ "marital_status"
			+ "birthday,"
			+ "emergency_contact_name,"
			+ "anniversary,"
			+ "baptized"
			+ "deceased"
			+ "membership_type_id,"
			+ "membership_date,"
			+ "membership_end,"
			+ "recieve_email_from_church,"
			+ "default_new_group_messages,"
			+ "default_new_group_comments,"
			+ "default_new_group_digest,"
			+ "profile_listed,"
			+ "mailing_address_privacy_level,"
			+ "home_address_privacy_level,"
			+ "contact_phone_privacy_level,"
			+ "home_phone_privacy_level,"
			+ "work_phone_privacy_level,"
			+ "mobile_phone_privacy_level,"
			+ "emergency_phone_privacy_level,"
			+ "birthday_privacy_level,"
			+ "anniversary_privacy_level,"
			+ "gender_privacy_level,"
			+ "marital_status_privacy_level,"
			+ "user_defined_fields_privacy_level,"
			+ "allergies_privacy_level,"
			+ "active,"
			*/
			+ "creator,"
			+ "modifier,"
			+ "created,"
			+ "modified,"
			/*
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
			+ "udf_date_5"
			+ "udf_date_6,"
			+ "udf_pulldown_1,"
			+ "udf_pulldown_2,"
			+ "udf_pulldown_3,"
			+ "udf_pulldown_4,"
			+ "udf_pulldown_5,"
			*/
			+ "udf_pulldown_6"
			+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE "
			+ "sync_id = ?, "
			+ "other_id = ?, "
			+ "giving_number = ?, "
			+ "campus_id = ?, "
			+ "family_id = ?, "
			//+ "family_image = ?, "
			+ "family_position = ?, "
			+ "first_name = ?, "
			+ "last_name = ?, "
			+ "middle_name = ?, "
			+ "legal_first_name = ?, "
			+ "full_name = ?, "
			+ "salutation = ?, "
			+ "suffix = ?, "
			/*
			+ "image = ?, "
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
			// TODO home, work, other
			+ "phone_contact = ?,"
			+ "phone_home = ?,"
			+ "phone_work = ?,"
			+ "phone_emergency = ?,"
			+ "mobile_carrier_id = ?,"
			+ "gender = ?,"
			+ "marital_status = ?"
			+ "birthday = ?,"
			+ "emergency_contact_name = ?,"
			+ "anniversary = ?,"
			+ "baptized = ?"
			+ "deceased = ?"
			+ "membership_type_id = ?,"
			+ "membership_date = ?,"
			+ "membership_end = ?,"
			+ "recieve_email_from_church = ?,"
			+ "default_new_group_messages = ?,"
			+ "default_new_group_comments = ?,"
			+ "default_new_group_digest = ?,"
			+ "profile_listed = ?,"
			+ "mailing_address_privacy_level = ?,"
			+ "home_address_privacy_level = ?,"
			+ "contact_phone_privacy_level = ?,"
			+ "home_phone_privacy_level = ?,"
			+ "work_phone_privacy_level = ?,"
			+ "mobile_phone_privacy_level = ?,"
			+ "emergency_phone_privacy_level = ?,"
			+ "birthday_privacy_level = ?,"
			+ "anniversary_privacy_level = ?,"
			+ "gender_privacy_level = ?,"
			+ "marital_status_privacy_level = ?,"
			+ "user_defined_fields_privacy_level = ?,"
			+ "allergies_privacy_level = ?,"
			+ "active = ?,"
			*/
			+ "creator = ?,"
			+ "modifier = ?,"
			+ "created = ?,"
			+ "modified = ?,"
			/*
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
			+ "udf_date_5 = ?"
			+ "udf_date_6 = ?,"
			+ "udf_pulldown_1 = ?,"
			+ "udf_pulldown_2 = ?,"
			+ "udf_pulldown_3 = ?,"
			+ "udf_pulldown_4 = ?,"
			+ "udf_pulldown_5 = ?,"
			*/
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
						System.out.println(item);
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
							// login, 
							// email, 
							// allergies, 
							// confirmed_no_allergies, 
							// address_mailing_street, 
							// address_mailing_city,
							// address_mailing_state,
							// address_mailing_zip,
							// address_mailing_line_1,
							// address_mailing_line_2,
							// phone_contact,
							// phone_home,
							// phone_work,
							// phone_emergency,
							// mobile_carrier_id,
							// gender,
							// marital_status
							// birthday,
							// emergency_contact_name,
							// anniversary,
							// baptized
							// deceased
							// membership_type_id,
							// membership_date,
							// membership_end
							// recieve_email_from_church,
							// default_new_group_messages,
							// default_new_group_comments,
							// default_new_group_digest,
							// profile_listed,
							// mailing_address_privacy_level,
							// home_address_privacy_level,
							// contact_phone_privacy_level,
							// home_phone_privacy_level,
							// work_phone_privacy_level,
							// mobile_phone_privacy_level,
							// emergency_phone_privacy_level,
							// birthday_privacy_level,
							// anniversary_privacy_level,
							// gender_privacy_level,
							// marital_status_privacy_level,
							// user_defined_fields_privacy_level,
							// allergies_privacy_level,
							// active,
							ps.setObject(i++,  item.getCreator().getId()); // creator
							ps.setObject(i++, item.getModifier().getId()); // modifier
							ps.setString(i++, DateUtils.toString(item.getCreated(), DateUtils.TIMESTAMP)); //created
							ps.setString(i++, DateUtils.toString(item.getModified(), DateUtils.TIMESTAMP)); // modified
							// udf_text_1,
							// udf_text_2,
							// udf_text_3,
							// udf_text_4,
							// udf_text_5,
							// udf_text_6,
							// udf_text_7,
							// udf_text_8,
							// udf_text_9,
							// udf_text_10,
							// udf_text_11,
							// udf_text_12,
							// udf_date_1,
							// udf_date_2,
							// udf_date_3,
							// udf_date_4,
							// udf_date_5
							// udf_date_6,
							// udf_pulldown_1,
							// udf_pulldown_2,
							// udf_pulldown_3,
							// udf_pulldown_4,
							// udf_pulldown_5,
							ps.setObject(i++, null); // udf_pulldown_6			
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
