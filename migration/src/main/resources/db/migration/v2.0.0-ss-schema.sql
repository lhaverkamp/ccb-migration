CREATE TABLE IF NOT EXISTS ss_individual(
	individual_id INT(10) NOT NULL,
	family_id INT(10),
	family_position VARCHAR(50) NOT NULL,
	
	prefix VARCHAR(10),
	first_name VARCHAR(50) NOT NULL,
	middle_name VARCHAR(50),
	last_name VARCHAR(50) NOT NULL,
	suffix VARCHAR(10),
	legal_name VARCHAR(50),
	maiden_name VARCHAR(50),
	
	limited_access_user BOOLEAN NOT NULL DEFAULT FALSE,
	listed BOOLEAN NOT NULL DEFAULT TRUE,
	inactive BOOLEAN NOT NULL DEFAULT FALSE,
	campus VARCHAR(50),
	
	email_family VARCHAR(50),
	email_individual VARCHAR(50),
	
	address_mailing_street1 VARCHAR(100),
	address_mailing_street2 VARCHAR(100),
	address_mailing_city VARCHAR(100),
	address_mailing_state VARCHAR(10),
	address_mailing_postal_code VARCHAR(10),
	address_mailing_country VARCHAR(10),
	address_mailing_carrier_route VARCHAR(10),
	
	address_home_street1 VARCHAR(100),
	address_home_street2 VARCHAR(100),
	address_home_city VARCHAR(100),
	address_home_state VARCHAR(10),
	address_home_postal_code VARCHAR(10),
	area_of_town VARCHAR(50),
	
	address_other_street1 VARCHAR(100),
	address_other_street2 VARCHAR(100),
	address_other_city VARCHAR(100),
	address_other_state VARCHAR(50),
	address_other_postal_code VARCHAR(10),
	
	phone_contact VARCHAR(10),
	phone_home VARCHAR(10),
	phone_work VARCHAR(10),
	phone_mobile VARCHAR(10),
	service_provider VARCHAR(10),
	phone_other VARCHAR(10),
	fax VARCHAR(10),
	pager VARCHAR(10),
	emergency_phone VARCHAR(10),
	emergency_contact_name VARCHAR(50),
	
	birthdate DATE,
	anniversary DATE,
	gender VARCHAR(1) NOT NULL,
	giving_number INT(10),
	marital_status VARCHAR(10),
	
	membership_date DATE,
	membership_stop_date DATE,
	membership_type VARCHAR(50),
	
	baptized BOOLEAN NOT NULL DEFAULT FALSE,
	baptism_date DATE,
	
	school VARCHAR(50),
	school_grade INT(2),
	
	known_allergies VARCHAR(1000),
	notes_family VARCHAR(5000),
	notes_individual VARCHAR(5000),
	
	approved_to_work_with_children BOOLEAN,
	approved_to_work_with_children_stop_date DATE,
	commitment_date DATE,
	how_they_heard VARCHAR(50),
	how_they_joined VARCHAR(50),
	reason_left_church VARCHAR(50),
	
	job_title VARCHAR(50),
	email_work VARCHAR(50),
	address_work_street1 VARCHAR(100),
	address_work_street2 VARCHAR(100),
	address_work_city VARCHAR(100),
	address_work_state VARCHAR(50),
	address_work_postal_code VARCHAR(10),
	
	testimony_current VARCHAR(100),
	testimony_salvation VARCHAR(100),
	
	deceased DATE,
	
	facebook_username VARCHAR(50),
	twitter_username VARCHAR(50),
	blog_username VARCHAR(50),
	website_personal VARCHAR(100),
	website_work VARCHAR(100),
	military VARCHAR(50),
	spiritual_maturity VARCHAR(50),
	spiritual_gifts VARCHAR(50),
	passions VARCHAR(1000),
	abilities_skills VARCHAR(1000),
	church_services_i_attend VARCHAR(50),
	personal_style VARCHAR(50),
	
	process_queue1 VARCHAR(50),
	process_queue2 VARCHAR(50),
	confirmed BOOLEAN NOT NULL DEFAULT FALSE,
	confirmation_date DATE,
	
	custom_text2 VARCHAR(50),
	custom_text3 VARCHAR(50),
	custom_date2 DATE,
	custom_date3 DATE,
	
	first_communion_date DATE,
	
	significant_event2 DATE,
	significant_event3 DATE,
	
	confirmation_verse VARCHAR(50),
	newsletter BOOLEAN NOT NULL DEFAULT TRUE,
	elder VARCHAR(50),
	
	other_id VARCHAR(50) NOT NULL,
	
	CONSTRAINT pk_ss_individual PRIMARY KEY (individual_id)
);

CREATE TABLE IF NOT EXISTS ss_chart_of_account(
	fund VARCHAR(50) NOT NULL,
	subfund VARCHAR(50),
	tax_deductible BOOLEAN NOT NULL DEFAULT TRUE,
	campus VARCHAR(50),
	active BOOLEAN NOT NULL DEFAULT TRUE,
	
	CONSTRAINT pk_ss_chart_of_account PRIMARY KEY (fund)
);

CREATE TABLE IF NOT EXISTS ss_contribution(
	individual_id INT(10) NOT NULL,
	contribution_date TIMESTAMP NOT NULL,
	amount DOUBLE(15,4) NOT NULL,
	type_of_gift VARCHAR(10) NOT NULL DEFAULT 'Other',
	check_number VARCHAR(10),
	fund VARCHAR(50) NOT NULL,
	subfund VARCHAR(50),
	campus VARCHAR(50),
	transaction_grouping VARCHAR(50),
	batch_number INT(10) NOT NULL,
	tax_deductible BOOLEAN NOT NULL DEFAULT TRUE,
	memo VARCHAR(1000),
	
	CONSTRAINT fk_ss_contributions_individual_id FOREIGN KEY (individual_id) REFERENCES ss_individual(individual_id)
);

CREATE TABLE IF NOT EXISTS ss_attendance(
	event_id INT(10) NOT NULL,
	occurance TIMESTAMP NOT NULL,
	did_not_meet BOOLEAN NOT NULL DEFAULT FALSE,
	head_count INT(10) NOT NULL,
	attendee_id INT(10) NOT NULL,
	topic VARCHAR(100),
	notes VARCHAR(5000),
	prayer_requests VARCHAR(1000),
	info VARCHAR(1000),
	email_notifications BOOLEAN NOT NULL DEFAULT FALSE,
	
	CONSTRAINT pk_ss_attendance PRIMARY KEY (event_id, occurance, attendee_id),
	CONSTRAINT fk_ss_attendance_attendee_id FOREIGN KEY (attendee_id) REFERENCES ss_individual(individual_id)
);
