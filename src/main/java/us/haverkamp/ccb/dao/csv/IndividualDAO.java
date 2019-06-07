package us.haverkamp.ccb.dao.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import us.haverkamp.ccb.Constants;
import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Individual;
import us.haverkamp.utils.CSVUtils;
import us.haverkamp.utils.DateUtils;

public class IndividualDAO extends us.haverkamp.ccb.dao.IndividualDAO {

	@Override
	public int[] update(List<Individual> items) throws DataAccessException {
		final Path path = Paths.get("individuals.csv");
		
		try {
			final FileWriter writer = new FileWriter(path.toFile());
			
			try {
				final CSVPrinter printer = CSVUtils.getPrinter(writer, CSVFormat.DEFAULT);
				
				try {
					// header
					printer.printRecord(
						"sync_id",
						"other_id",
						"giving_number",
						"campus_id",
						"family_id",
						"family_image",
						"family_position",
						"first_name",
						"last_name",
						"middle_name",
						"legal_first_name",
						"full_name",
						"salutation",
						"suffix",
						"image",
						"login",
						"email",
						"allergies",
						"confirmed_no_allergies",
						"address_mailing_street",
						"address_mailing_city",
						"address_mailing_state",
						"address_mailing_zip",
						"address_mailing_line_1",
						"address_mailing_line_2",
						"address_home_street",
						"address_home_city",
						"address_home_state",
						"address_home_zip",
						"address_home_line_1",
						"address_home_line_2",
						"address_work_street",
						"address_work_city",
						"address_work_state",
						"address_work_zip",
						"address_work_line_1",
						"address_work_line_2",
						"address_other_street",
						"address_other_city",
						"address_other_state",
						"address_other_zip",
						"address_other_line_1",
						"address_other_line_2",
						"phone_contact",
						"phone_home",
						"phone_mobile",
						"phone_work",
						"phone_emergency",
						"mobile_carrier_id",
						"gender",
						"marital_status",
						"birthday",
						"emergency_contact_name",
						"anniversary",
						"baptized",
						"deceased",
						"membership_type_id",
						"membership_date",
						"membership_end",
						"receive_email_from_church",
						"default_new_group_messages",
						"default_new_group_comments",
						"default_new_group_digest",
						"profile_listed",
						"mailing_address_privacy_level_id",
						"home_address_privacy_level_id",
						"contact_phone-privacy_level_id",
						"home_phone_privacy_level_id",
						"work_phone_privacy_level_id",
						"mobile_phone_privacy_level_id",
						"emergency_phone_privace_level_id",
						"birthday_privacy_level_id",
						"anniversary_privacy_level_id",
						"gender_privacy_level_id",
						"marital_status_privacy_level_id",
						"user_defined_fields_privacy_level_id",
						"allergies_privacy_level_id",
						"active",
						"creator_id",
						"modifier_id",
						"created",
						"modified",
						"udf_text_1",
						"udf_text_2",
						"udf_text_3",
						"udf_text_4",
						"udf_text_5",
						"udf_text_6",
						"udf_text_7",
						"udf_text_8",
						"udf_text_9",
						"udf_text_10",
						"udf_text_11",
						"udf_text_12",
						"udf_date_1",
						"udf_date_2",
						"udf_date_3",
						"udf_date_4",
						"udf_date_5",
						"udf_date_6",
						"udf_pulldown_1",
						"udf_pulldown_2",
						"udf_pulldown_3",
						"udf_pulldown_4",
						"udf_pulldown_5",
						"udf_pulldown_6"			
					);
					
					for(Individual item : items) {
						printer.printRecord(
							item.getSyncId(), // sync_id
							item.getOtherId(), // other_id
							item.getGivingNumber(), // giving_number
							item.getCampus().getId(), // campus_id
							item.getFamily().getId(), // family_id
							null, // family_image, 
							item.getFamilyPosition(), // family_position
							item.getFirstName(), // first_name
							item.getLastName(), // last_name
							item.getMiddleName(), // middle_name
							item.getLegalFirstName(), // legal_first_name,
							item.getFirstName(), // full_name,
							item.getPrefix(), // salutation,
							item.getSuffix(), // suffix, 
							null, // image, 
							item.getLogin(), // login, 
							item.getEmail(), // email, 
							item.getAllergies(), // allergies, 
							item.getConfirmedNoAllergies(), // confirmed_no_allergies, 
							item.getContact().getStreet(), // address_mailing_street, 
							item.getContact().getCity(), // address_mailing_city,
							item.getContact().getState(), // address_mailing_state,
							item.getContact().getZip(), // address_mailing_zip,
							item.getContact().getLine1(), // address_mailing_line_1,
							item.getContact().getLine2(), // address_mailing_line_2,
							item.getHome().getStreet(), // address_home_street, 
							item.getHome().getCity(), // address_home_city,
							item.getHome().getState(), // address_home_state,
							item.getHome().getZip(), // address_home_zip,
							item.getHome().getLine1(), // address_home_line_1,
							item.getHome().getLine2(), // address_home_line_2,
							item.getWork().getStreet(), // address_work_street, 
							item.getWork().getCity(), // address_work_city,
							item.getWork().getState(), // address_work_state,
							item.getWork().getZip(), // address_work_zip,
							item.getWork().getLine1(), // address_work_line_1,
							item.getWork().getLine2(), // address_work_line_2,
							item.getOther().getStreet(), // address_other_street, 
							item.getOther().getCity(), // address_other_city,
							item.getOther().getState(), // address_other_state,
							item.getOther().getZip(), // address_other_zip,
							item.getOther().getLine1(), // address_other_line_1,
							item.getOther().getLine2(), // address_other_line_2,
							item.getContactPhone(), // phone_contact,
							item.getHomePhone(), // phone_home,
							item.getMobilePhone(), // phone_mobile
							item.getWorkPhone(), // phone_work,
							item.getEmergencyPhone(), // phone_emergency,
							null, // mobile_carrier_id,
							item.getGender(), // gender,
							item.getMaritalStatus(), // marital_status
							DateUtils.toString(item.getBirthday()), // birthday,
							item.getEmergencyContact(), // emergency_contact_name,
							DateUtils.toString(item.getAnniversary()), // anniversary,
							item.getBaptized(), // baptized
							DateUtils.toString(item.getDeceased()), // deceased
							item.getMembershipType().getId(), // membership_type_id,
							DateUtils.toString(item.getMembershipDate()), // membership_date,
							DateUtils.toString(item.getMembershipEnd()), // membership_end
							item.getCommunicationPreferences().getReceiveEmailFromChurch(), // receive_email_from_church,
							item.getCommunicationPreferences().getDefaultNewGroupMessages(), // default_new_group_messages,
							item.getCommunicationPreferences().getDefaultNewGroupComments(), // default_new_group_comments,
							item.getCommunicationPreferences().getDefaultNewGroupDigest(), // default_new_group_digest,
							item.getPrivacySettings().getListed(), // profile_listed,
							item.getPrivacySettings().getMailingAddress(), // mailing_address_privacy_level_id,
							item.getPrivacySettings().getHomeAddress(), // home_address_privacy_level_id,
							item.getPrivacySettings().getContactPhone(), // contact_phone_privacy_level_id,
							item.getPrivacySettings().getHomePhone(), // home_phone_privacy_level_id,
							item.getPrivacySettings().getWorkPhone(), // work_phone_privacy_level_id,
							item.getPrivacySettings().getMobilePhone(), // mobile_phone_privacy_level_id,
							item.getPrivacySettings().getEmergencyPhone(), // emergency_phone_privacy_level_id,
							item.getPrivacySettings().getBirthday(), // birthday_privacy_level_id,
							item.getPrivacySettings().getAnniversary(), // anniversary_privacy_level_id,
							item.getPrivacySettings().getGender(), // gender_privacy_level_id,
							item.getPrivacySettings().getMartialStatus(), // marital_status_privacy_level_id,
							item.getPrivacySettings().getUserDefinedFields(), // user_defined_fields_privacy_level_id,
							item.getPrivacySettings().getAllergies(), // allergies_privacy_level_id,
							item.getActive(), // active,
							item.getCreator().getId(), // creator_id
							item.getModifier().getId(), // modifier_id
							DateUtils.toString(item.getCreated(), DateUtils.TIMESTAMP), //created
							DateUtils.toString(item.getModified(), DateUtils.TIMESTAMP), // modified
							item.getUserDefinedTextField(Constants.UDF_TEXT_1), // udf_text_1,
							item.getUserDefinedTextField(Constants.UDF_TEXT_2), // udf_text_2,
							item.getUserDefinedTextField(Constants.UDF_TEXT_3), // udf_text_3,
							item.getUserDefinedTextField(Constants.UDF_TEXT_4), // udf_text_4,
							item.getUserDefinedTextField(Constants.UDF_TEXT_5), // udf_text_5,
							item.getUserDefinedTextField(Constants.UDF_TEXT_6), // udf_text_6,
							item.getUserDefinedTextField(Constants.UDF_TEXT_7), // udf_text_7,
							item.getUserDefinedTextField(Constants.UDF_TEXT_8), // udf_text_8,
							item.getUserDefinedTextField(Constants.UDF_TEXT_9), // udf_text_9,
							item.getUserDefinedTextField(Constants.UDF_TEXT_10), // udf_text_10,
							item.getUserDefinedTextField(Constants.UDF_TEXT_11), // udf_text_11,
							item.getUserDefinedTextField(Constants.UDF_TEXT_12), // udf_text_12,
							item.getUserDefinedDateField(Constants.UDF_DATE_1), // udf_date_1,
							item.getUserDefinedDateField(Constants.UDF_DATE_2), // udf_date_2,
							item.getUserDefinedDateField(Constants.UDF_DATE_3), // udf_date_3,
							item.getUserDefinedDateField(Constants.UDF_DATE_4), // udf_date_4,
							item.getUserDefinedDateField(Constants.UDF_DATE_5), // udf_date_5
							item.getUserDefinedDateField(Constants.UDF_DATE_6), // udf_date_6,
							item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_1), // udf_pulldown_1,
							item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_2), // udf_pulldown_2,
							item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_3), // udf_pulldown_3,
							item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_4), // udf_pulldown_4,
							item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_5), // udf_pulldown_5,
							item.getUserDefinedPulldownField(Constants.UDF_PULLDOWN_6) // udf_pulldown_6
						);
					}
					
					return new int[items.size()];
				} finally {
					printer.close();
				}
			} finally {
				writer.close();
			}
		} catch(IOException e) {
			throw new DataAccessException(e);
		}
	}
}
