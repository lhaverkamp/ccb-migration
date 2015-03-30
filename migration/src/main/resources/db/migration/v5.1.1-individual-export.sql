LOAD DATA INFILE '../csv/export_all_individuals.csv'
INTO TABLE individual_export
FIELDS
	TERMINATED BY ','
	OPTIONALLY ENCLOSED BY '"'
LINES
	TERMINATED BY '\n'
IGNORE 1 LINES
(
	individual_id,
	family_id,
	@family_position,
	@limited_access_user,
	@prefix,
	@legal_name,
	@first_name,
	@middle_name,
	@last_name,
	@suffix,
	@campus,
	@email,
	email_privacy_level,
	@general_communication,
	@area_of_town,
	@mailing_street,
	@mailing_city,
	@mailing_state,
	@mailing_zip,
	@mailing_carrier_route,
	mailing_privacy_level,
	@mailing_latitude,
	@mailing_longitude,
	@contact_phone,
	contact_phone_privacy_level,
	@home_phone,
	home_phone_privacy_level,
	@work_phone,
	work_phone_privacy_level,
	@mobile_phone,
	@mobile_carrier,
	mobile_phone_privacy_level,
	@fax,
	fax_privacy_level,
	@pager,
	pager_privacy_level,
	@emergency_phone,
	emergency_phone_privacy_level,
	@emergency_contact_name,
	@birthday,
	birthday_privacy_level,
	@anniversary,
	anniversary_privacy_level,
	@gender,
	gender_privacy_level,
	@giving_number,
	@marital_status,
	marital_status_privacy_level,
	@home_area,
	@home_street,
	@home_city,
	@home_state,
	@home_zip,
	home_privacy_level,
	@work_area,
	@work_street,
	@work_city,
	@work_state,
	@work_zip,
	work_privacy_level,
	@other_area,
	@other_street,
	@other_city,
	@other_state,
	@other_zip,
	other_privacy_level,
	@work_title,
	@school_name,
	@school_grade,
	@allergies,
	@allergies_confirmed,
	allergies_privacy_level,
	@commitment_date,
	@commitment_story,
	@current_story,
	@my_website,
	@work_website,
	@military,
	@services_usually_attended,
	plugged_in_privacy_level,
	@user_defined_text_1,
	@user_defined_text_2,
	@user_defined_text_3,
	@user_defined_text_4,
	@user_defined_text_5,
	@user_defined_text_6,
	@user_defined_text_7,
	@user_defined_text_8,
	@user_defined_text_9,
	@confirmation_verse,
	@email_work,
	@maiden_name,
	@user_defined_date_1,
	@user_defined_date_2,
	@user_defined_date_3,
	@first_communion,
	@confirmation,
	@baptism,
	@user_defined_pulldown_1,
	@user_defined_pulldown_2,
	@user_defined_pulldown_3,
	@elder,
	@newsletter,
	@confirmed,
	custom_field_privacy_level,
	@personal_style,
	@spiritual_gifts,
	@passions,
	@abilities,
	my_fit_privacy_level,
	@last_logged_in,
	@created_by,
	@modified_by,
	@created_date,
	@modified_date,
	@giving_number2,
	@listed,
	@inactive,
	@membership_start_date,
	@membership_end_date,
	@membership_type,
	@spiritual_maturity,
	@baptized,
	@deceased_date,
	@how_they_heard,
	@reason_left_church,
	@child_work_date_start,
	@child_work_date_end,
	@other_id,
	sync_id,
	@age_bracket
)
SET 
	family_position = NULLIF(@family_position, ''),
	limited_access_user = IF(@limited_access_user = '', '0', @limited_access_user),
	prefix = NULLIF(@prefix, ''),
	legal_name = NULLIF(@legal_name, ''),
	first_name = NULLIF(@first_name, ''),
	middle_name = NULLIF(@middle_name, ''),
	last_name = NULLIF(@last_name, ''),
	suffix = NULLIF(@suffix, ''),
	campus = NULLIF(@campus, ''),
	email = NULLIF(@email, ''),
	general_communication = IF(@general_communication = 'Yes', TRUE, FALSE),
	area_of_town = NULLIF(@area_of_town, ''),
	mailing_street = NULLIF(@mailing_street, ''),
	mailing_city = NULLIF(@mailing_city, ''),
	mailing_state = NULLIF(@mailing_state, ''),
	mailing_zip = NULLIF(@mailing_zip, ''),
	mailing_carrier_route = NULLIF(@mailing_carrier_route, ''),
	mailing_latitude = NULLIF(@mailing_latitude, ''),
	mailing_longitude = NULLIF(@mailing_longitude, ''),
	contact_phone = NULLIF(@contact_phone, ''),
	home_phone = NULLIF(@home_phone, ''),
	work_phone = NULLIF(@work_phone, ''),
	mobile_phone = NULLIF(@mobile_phone, ''),
	mobile_carrier = NULLIF(@mobile_carrier, ''),
	fax = NULLIF(@fax, ''),
	pager = NULLIF(@pager, ''),
	emergency_phone = NULLIF(@emergency_phone, ''),
	emergency_contact_name = NULLIF(@emergency_contact_name, ''),
	birthday = STR_TO_DATE(NULLIF(@birthday, ''), '%b %d, %Y'),
	anniversary = STR_TO_DATE(NULLIF(@anniversary, ''), '%b %d, %Y'),
	gender = NULLIF(@gender, ''),
	giving_number = NULLIF(@giving_number, ''),
	marital_status = NULLIF(@marital_status, ''),
	home_area = NULLIF(@home_area, ''),
	home_street = IF(@home_street = 'Unknown', NULL ,NULLIF(@home_street, '')),
	home_city = NULLIF(@home_city, ''),
	home_state = NULLIF(@home_state, ''),
	home_zip = NULLIF(@home_zip, ''),
	work_area = NULLIF(@work_area, ''),
	work_street = NULLIF(@work_street, ''),
	work_city = NULLIF(@work_city, ''),
	work_state = NULLIF(@work_state, ''),
	work_zip = NULLIF(@work_zip, ''),
	other_area = NULLIF(@other_area, ''),
	other_street = NULLIF(@other_street, ''),
	other_city = NULLIF(@other_city, ''),
	other_state = NULLIF(@other_state, ''),
	other_zip = NULLIF(@other_zip, ''),
	work_title = NULLIF(@work_title, ''),
	school_name = NULLIF(@school_name, ''),
	school_grade = NULLIF(@school_grade, ''),
	allergies = NULLIF(@allergies, ''),
	allergies_confirmed = NULLIF(@allergies_confirmed, ''),
	commitment_date = STR_TO_DATE(NULLIF(@commitment_date, ''), '%b %d, %Y'),
	commitment_story = NULLIF(@commitment_story, ''),
	current_story = NULLIF(@current_story, ''),
	my_website = NULLIF(@my_website, ''),
	work_website = NULLIF(@work_website, ''),
	military = NULLIF(@military, ''),
	services_usually_attended = NULLIF(@services_usually_attended, ''),
	user_defined_text_1 = NULLIF(@user_defined_text_1, ''),
	user_defined_text_2 = NULLIF(@user_defined_text_2, ''),
	user_defined_text_3 = NULLIF(@user_defined_text_3, ''),
	user_defined_text_4 = NULLIF(@user_defined_text_4, ''),
	user_defined_text_5 = NULLIF(@user_defined_text_5, ''),
	user_defined_text_6 = NULLIF(@user_defined_text_6, ''),
	user_defined_text_7 = NULLIF(@user_defined_text_7, ''),
	user_defined_text_8 = NULLIF(@user_defined_text_8, ''),
	user_defined_text_9 = NULLIF(@user_defined_text_9, ''),
	confirmation_verse = NULLIF(@confirmation_verse, ''),
	email_work = NULLIF(@email_work, ''),
	maiden_name = NULLIF(@maiden_name, ''),
	user_defined_date_1 = STR_TO_DATE(NULLIF(@user_defined_date_1, ''), '%b %d, %Y'),
	user_defined_date_2 = STR_TO_DATE(NULLIF(@user_defined_date_2, ''), '%b %d, %Y'),
	user_defined_date_3 = STR_TO_DATE(NULLIF(@user_defined_date_3, ''), '%b %d, %Y'),
	first_communion = STR_TO_DATE(NULLIF(@first_communion, ''), '%b %d, %Y'),
	confirmation = STR_TO_DATE(NULLIF(@confirmation, ''), '%b %d, %Y'),
	baptism = STR_TO_DATE(NULLIF(@baptism, ''), '%b %d, %Y'),
	user_defined_pulldown_1 = NULLIF(@user_defined_pulldown_1, ''),
	user_defined_pulldown_2 = NULLIF(@user_defined_pulldown_2, ''),
	user_defined_pulldown_3 = NULLIF(@user_defined_pulldown_3, ''),
	elder = NULLIF(@elder, ''),
	newsletter = NULLIF(@newsletter, ''),
	confirmed = NULLIF(@confirmed, ''),
	personal_style = NULLIF(@personal_style, ''),
	spiritual_gifts = NULLIF(@spiritual_gifts, ''),
	passions = NULLIF(@passions, ''),
	abilities = NULLIF(@abilities, ''),
	last_logged_in = STR_TO_DATE(NULLIF(@last_logged_in, ''), '%b %d, %Y'),
	created_by = NULLIF(@created_by, ''),
	modified_by = NULLIF(@modified_by, ''),
	created_date = STR_TO_DATE(@created_date, '%b %d, %Y'),
	modified_date = STR_TO_DATE(@modified_date, '%b %d, %Y'),
	listed = IF(@listed = 'Unlisted', FALSE, TRUE),
	inactive = IF(@inactive = 'Inactive', TRUE, FALSE),
	membership_start_date = STR_TO_DATE(NULLIF(@membership_start_date, ''), '%b %d, %Y'),
	membership_end_date = STR_TO_DATE(NULLIF(@membership_end_date, ''), '%b %d, %Y'),
	membership_type = NULLIF(@membership_type, ''),
	spiritual_maturity = NULLIF(@spiritual_maturity, ''),
	baptized = IF(@baptized = 'Baptized', TRUE, FALSE),
	deceased_date = STR_TO_DATE(NULLIF(NULLIF(@deceased_date, '-0'), '0'), '%b %d, %Y'),
	how_they_heard = NULLIF(@how_they_heard, ''),
	reason_left_church = NULLIF(@reason_left_church, ''),
	child_work_date_start = STR_TO_DATE(NULLIF(@child_work_date_start, ''), '%b %d, %Y'),
	child_work_date_end = STR_TO_DATE(NULLIF(@child_work_date_end, ''), '%b %d, %Y'),
	other_id = NULLIF(@other_id, ''),
	age_bracket = NULLIF(@age_bracket, '')
;
