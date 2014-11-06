LOAD DATA INFILE '../csv/ss-individuals.csv'
INTO TABLE ss_individual
FIELDS
	TERMINATED BY ','
	OPTIONALLY ENCLOSED BY '"'
LINES
	TERMINATED BY '\n'
IGNORE 1 LINES
(
	individual_id,
	@family_id,
	family_position,
	
	@prefix,
	first_name,
	@middle_name,
	last_name,
	@suffix,
	@legal_name,
	@maiden_name,
	
	@limited_access_user,
	@listed,
	@inactive,
	@campus,
	
	@email_family,
	@email_individual,
	
	@address_mailing_street1,
	@address_mailing_street2,
	@address_mailing_city,
	@address_mailing_state,
	@address_mailing_postal_code,
	@address_mailing_country,
	@address_mailing_carrier_route,
	
	@address_home_street1,
	@address_home_street2,
	@address_home_city,
	@address_home_state,
	@address_home_postal_code,
	@area_of_town,
	
	@address_other_street1,
	@address_other_street2,
	@address_other_city,
	@address_other_state,
	@address_other_postal_code,
	
	@phone_contact,
	@phone_home,
	@phone_work,
	@phone_mobile,
	@service_provider,
	@phone_other,
	@fax,
	@pager,
	@emergency_phone,
	@emergency_contact_name,
	
	@birthdate,
	@anniversary,
	gender,
	@giving_number,
	@marital_status,
	
	@membership_date,
	@membership_stop_date,
	@membership_type,
	
	@baptized,
	@baptism_date,
	
	@school,
	@school_grade,
	
	@known_allergies,
	@notes_family,
	@notes_individual,
	
	@approved_to_work_with_children,
	@approved_to_work_with_children_stop_date,
	@commitment_date,
	@how_they_heard,
	@how_they_joined,
	@reason_left_church,
	
	@job_title,
	@email_work,
	@address_work_street1,
	@address_work_street2,
	@address_work_city,
	@address_work_state,
	@address_work_postal_code,
	
	@testimony_current,
	@testimony_salvation,
	
	@deceased,
	
	@facebook_username,
	@twitter_username,
	@blog_username,
	@website_personal,
	@website_work,
	@military,
	@spiritual_maturity,
	@spiritual_gifts,
	@passions,
	@abilities_skills,
	@church_services_i_attend,
	@personal_style,
	
	@process_queue1,
	@process_queue2,
	@confirmed,
	@confirmation_date,
	
	@custom_text2,
	@custom_text3,
	@custom_date2,
	@custom_date3,
	
	@first_communion_date,
	
	@significant_event2,
	@significant_event3,
	
	@confirmation_verse,
	@newsletter,
	@elder,
	
	@other_id
)
SET family_id = NULLIF(@family_id, 0),

	prefix = NULLIF(@prefix, ''),
	middle_name = NULLIF(@middle_name, ''),
	suffix = NULLIF(@suffix, ''),
	legal_name = NULLIF(@legal_name, ''),
	maiden_name = NULLIF(@maiden_name, ''),
	
	limited_access_user = IF(@limited_access_user = 'Yes', TRUE, FALSE),
	listed = IF(@listed = 'Yes', TRUE, FALSE),
	inactive = IF(@inactive = 'Yes', TRUE, FALSE),
	campus = NULLIF(@campus, ''),
	
	email_family = NULLIF(@email_family, ''),
	email_individual = NULLIF(@email_individual, ''),
	
	address_mailing_street1 = NULLIF(@address_mailing_street1, ''),
	address_mailing_street2 = NULLIF(@address_mailing_street2, ''),
	address_mailing_city = NULLIF(@address_mailing_city, ''),
	address_mailing_state = NULLIF(@address_mailing_state, ''),
	address_mailing_postal_code = 
		CASE @address_mailing_postal_code
			WHEN '*****-****-**' THEN NULL
			ELSE NULLIF(@address_mailing_postal_code, '')
		END,
	address_mailing_country = NULLIF(@address_mailing_country, ''),
	address_mailing_carrier_route = NULLIF(@address_mailing_carrier_route, ''),
	
	address_home_street1 = NULLIF(@address_home_street1, ''),
	address_home_street2 = NULLIF(@address_home_street2, ''),
	address_home_city = NULLIF(@address_home_city, ''),
	address_home_state = NULLIF(@address_home_state, ''),
	address_home_postal_code = 
		CASE @address_home_postal_code
			WHEN '*****-****-**' THEN NULL
			ELSE NULLIF(@address_home_postal_code, '')
		END,
	area_of_town = NULLIF(@area_of_town, ''),
	
	address_other_street1 = NULLIF(@address_other_street1, ''),
	address_other_street2 = NULLIF(@address_other_street2, ''),
	address_other_city = NULLIF(@address_other_city, ''),
	address_other_state = NULLIF(@address_other_state, ''),
	address_other_postal_code = 
		CASE @address_other_postal_code
			WHEN '*****-****-**' THEN NULL
			ELSE NULLIF(@address_other_postal_code, '')
		END,
	
	phone_contact = NULLIF(@phone_contact, ''),
	phone_home = NULLIF(@phone_home, ''),
	phone_work = NULLIF(@phone_work, ''),
	phone_mobile = NULLIF(@phone_mobile, ''),
	service_provider = NULLIF(@service_provider, ''),
	phone_other = NULLIF(@phone_other, ''),
	fax = NULLIF(@fax, ''),
	pager = NULLIF(@pager, ''),
	emergency_phone = NULLIF(@emergency_phone, ''),
	emergency_contact_name = NULLIF(@emergency_contact_name, ''),
	
	birthdate = NULLIF(@birthdate, ''),
	anniversary = NULLIF(@anniversary, ''),
	giving_number = NULLIF(@giving_number, ''),
	marital_status = NULLIF(@marital_status, ''),
	
	membership_date = NULLIF(@membership_date, ''),
	membership_stop_date = NULLIF(@membership_stop_date, ''),
	membership_type = NULLIF(@membership_type, ''),
	
	baptized = IF(@baptized = 'Yes', TRUE, FALSE),
	baptism_date = NULLIF(@baptism_date, ''),
	
	school = NULLIF(@school, ''),
	school_grade = 
		CASE @school_grade
			WHEN 'S01 First Grade' THEN 1
			WHEN 'S02 Second Grade' THEN 2
			WHEN 'S05 Fifth Grade' THEN 5
			ELSE NULLIF(@school_grade, '')
		END,
	
	known_allergies = NULLIF(@known_allergies, ''),
	notes_family = NULLIF(@notes_family, ''),
	notes_individual = NULLIF(@notes_individual, ''),
	
	approved_to_work_with_children = NULLIF(@approved_to_work_with_children, ''),
	approved_to_work_with_children_stop_date = NULLIF(@approved_to_work_with_children_stop_date, ''),
	commitment_date = NULLIF(@commitment_date, ''),
	how_they_heard = NULLIF(@how_they_heard, ''),
	how_they_joined = NULLIF(@how_they_joined, ''),
	reason_left_church = NULLIF(@reason_left_church, ''),
	
	job_title = NULLIF(@job_title, ''),
	email_work = NULLIF(@email_work, ''),
	address_work_street1 = NULLIF(@address_work_street1, ''),
	address_work_street2 = NULLIF(@address_work_street2, ''),
	address_work_city = NULLIF(@address_work_city, ''),
	address_work_state = NULLIF(@address_work_state, ''),
	address_work_postal_code = NULLIF(@address_work_postal_code, ''),
	
	testimony_current = NULLIF(@testimony_current, ''),
	testimony_salvation = NULLIF(@testimony_salvation, ''),
	
	deceased = NULLIF(@deceased, ''),
	
	facebook_username = NULLIF(@facebook_username, ''),
	twitter_username = NULLIF(@twitter_username, ''),
	blog_username = NULLIF(@blog_username, ''),
	website_personal = NULLIF(@website_personal, ''),
	website_work = NULLIF(@website_work, ''),
	military = NULLIF(@military, ''),
	spiritual_maturity = NULLIF(@spiritual_maturity, ''),
	spiritual_gifts = NULLIF(@spiritual_gifts, ''),
	passions = NULLIF(@passions, ''),
	abilities_skills = NULLIF(@abilities_skills, ''),
	church_services_i_attend = NULLIF(@church_services_i_attend, ''),
	personal_style = NULLIF(@personal_style, ''),
	
	process_queue1 = NULLIF(@process_queue1, ''),
	process_queue2 = NULLIF(@process_queue2, ''),
	confirmed = IF(@confirmed = 'Yes', TRUE, FALSE),
	confirmation_date = NULLIF(@confirmation_date, ''),
	
	custom_text2 = NULLIF(@custom_text2, ''),
	custom_text3 = NULLIF(@custom_text3, ''),
	custom_date2 = NULLIF(@custom_date2, ''),
	custom_date3 = NULLIF(@custom_date3, ''),
	
	first_communion_date = NULLIF(@first_communion_date, ''),
	
	significant_event2 = NULLIF(@significant_event2, ''),
	significant_event3 = NULLIF(@significant_event3, ''),
	
	confirmation_verse = NULLIF(@confirmation_verse, ''),
	newsletter = IF(@newsletter = 'Yes', TRUE, FALSE),
	elder = NULLIF(@elder, ''),
	
	other_id = NULLIF(@other_id, '')
;