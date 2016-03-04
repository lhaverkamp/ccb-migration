-- It appears there isn't an API call that will allow us to populate this 
-- information.  It is being hard-coded based upon how the values look in the 
-- application.
CREATE TABLE IF NOT EXISTS privacy_level(
	id				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	CONSTRAINT		pk_privacy_id			PRIMARY KEY(id)
);
INSERT INTO privacy_level(id, name) VALUES(1, 'Church Leadership Only') ON DUPLICATE KEY UPDATE name = 'Church Leadership Only';
INSERT INTO privacy_level(id, name) VALUES(2, 'Friends Only') ON DUPLICATE KEY UPDATE name = 'Friends Only';
INSERT INTO privacy_level(id, name) VALUES(3, 'Friends & My Group Members') ON DUPLICATE KEY UPDATE name = 'Friends & My Group Members';
INSERT INTO privacy_level(id, name) VALUES(4, 'Everybody') ON DUPLICATE KEY UPDATE name = 'Everybody';

-- campus_list
CREATE TABLE IF NOT EXISTS campus(
	id 				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	CONSTRAINT		pk_campripus_id			PRIMARY KEY(id),
	CONSTRAINT		uk_campus_name			UNIQUE(name)
);

-- event_grouping_list
CREATE TABLE IF NOT EXISTS event_grouping(
	id				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_event_grouping_id	PRIMARY KEY(id),
	CONSTRAINT		uk_event_grouping_name	UNIQUE(name)
);

-- group_grouping_list
CREATE TABLE IF NOT EXISTS department(
	id				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_department_id		PRIMARY KEY(id),
	CONSTRAINT		uk_department_name		UNIQUE(name)
);

-- group_type_list
CREATE TABLE IF NOT EXISTS group_type(
	id				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_group_type_id		PRIMARY KEY(id),
	CONSTRAINT		uk_group_type_name		UNIQUE(name)
);

-- how_joined_church_list
CREATE TABLE IF NOT EXISTS how_joined_church(
	id				INT(10)						NOT NULL,
	name			VARCHAR(50)					NOT NULL,
	sort_order		INT(10)						NOT NULL,
	CONSTRAINT		pk_how_joined_church_id		PRIMARY KEY(id),
	CONSTRAINT		uk_how_joined_church_name	UNIQUE(name)
);

-- how_they_heard_list
CREATE TABLE IF NOT EXISTS how_they_heard(
	id				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_how_they_heard_id	PRIMARY KEY(id),
	CONSTRAINT		uk_how_they_heard_name	UNIQUE(name)
);

-- family_list
CREATE TABLE IF NOT EXISTS family(
	id				INT(10)					NOT NULL,
	name			VARCHAR(100),
	modified		TIMESTAMP				NOT NULL,
	CONSTRAINT		pk_family_id			PRIMARY KEY(id)
);

-- membership_type_list
CREATE TABLE IF NOT EXISTS membership_type(
	id				INT(10)					NOT NULL,
	name			VARCHAR(100)			NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_membership_type_id	PRIMARY KEY(id),
	CONSTRAINT		uk_membership_type_name	UNIQUE(name)
);

-- reason_left_church_list
CREATE TABLE IF NOT EXISTS reason_left_church(
	id				INT(10)						NOT NULL,
	name			VARCHAR(50)					NOT NULL,
	sort_order		INT(10)						NOT NULL,
	CONSTRAINT		pk_reason_left_church_id	PRIMARY KEY(id),
	CONSTRAINT		uk_reason_left_church_name	UNIQUE(name)
);

-- school_list
CREATE TABLE IF NOT EXISTS school(
	id				INT(10)					NOT NULL,
	name			VARCHAR(100)			NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_school_id			PRIMARY KEY(id),
	CONSTRAINT		uk_school_name			UNIQUE(name)
);

-- school_grade_list
CREATE TABLE IF NOT EXISTS school_grade(
	id				INT(10)					NOT NULL,
	name			VARCHAR(100)			NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_school_grade_id		PRIMARY KEY(id),
	CONSTRAINT		uk_school_grade_name	UNIQUE(name)
);

-- TODO need to populate this table
CREATE TABLE udf_text_field(
	id				INT(10)					NOT NULL,
	name			VARCHAR(100)			NOT NULL,
	label			VARCHAR(100)			NOT NULL,
	admin_only		BOOLEAN					DEFAULT FALSE,
	CONSTRAINT		pk_udf_text_field_id	PRIMARY KEY(id),
	CONSTRAINT		uk_udf_text_field_name	UNIQUE(name)
);

-- TODO need to populate this table
CREATE TABLE udf_date_field(
	id				INT(10)					NOT NULL,
	name			VARCHAR(100)			NOT NULL,
	label			VARCHAR(100)			NOT NULL,
	admin_only		BOOLEAN					DEFAULT FALSE,
	CONSTRAINT		pk_udf_text_field_id	PRIMARY KEY(id),
	CONSTRAINT		uk_udf_text_field_name	UNIQUE(name)
);

-- TODO need to populate this table
CREATE TABLE udf_pulldown_field(
	id				INT(10)					NOT NULL,
	name			VARCHAR(100)			NOT NULL,
	label			VARCHAR(100)			NOT NULL,
	admin_only		BOOLEAN					DEFAULT FALSE,
	CONSTRAINT		pk_udf_text_field_id	PRIMARY KEY(id),
	CONSTRAINT		uk_udf_text_field_name	UNIQUE(name)
);

-- udf_ind_pulldown_1_list
CREATE TABLE udf_ind_pulldown_1(
	id				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_udf_ind_pulldown_1_id	PRIMARY KEY(id),
	CONSTRAINT		uk_udf_ind_pulldown_1_name	UNIQUE(name)
);

-- udf_ind_pulldown_2_list
CREATE TABLE udf_ind_pulldown_2(
	id				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_udf_ind_pulldown_2_id	PRIMARY KEY(id),
	CONSTRAINT		uk_udf_ind_pulldown_2_name	UNIQUE(name)
);

-- udf_ind_pulldown_3_list
CREATE TABLE udf_ind_pulldown_3(
	id				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_udf_ind_pulldown_3_id	PRIMARY KEY(id),
	CONSTRAINT		uk_udf_ind_pulldown_3_name	UNIQUE(name)
);

-- udf_ind_pulldown_4_list
CREATE TABLE udf_ind_pulldown_4(
	id				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_udf_ind_pulldown_4_id	PRIMARY KEY(id),
	CONSTRAINT		uk_udf_ind_pulldown_4_name	UNIQUE(name)
);

-- udf_ind_pulldown_5_list
CREATE TABLE udf_ind_pulldown_5(
	id				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_udf_ind_pulldown_5_id	PRIMARY KEY(id),
	CONSTRAINT		uk_udf_ind_pulldown_5_name	UNIQUE(name)
);

-- udf_ind_pulldown_6_list
CREATE TABLE udf_ind_pulldown_6(
	id				INT(10)					NOT NULL,
	name			VARCHAR(50)				NOT NULL,
	sort_order		INT(10)					NOT NULL,
	CONSTRAINT		pk_udf_ind_pulldown_6_id	PRIMARY KEY(id),
	CONSTRAINT		uk_udf_ind_pulldown_6_name	UNIQUE(name)
);

-- individual_profiles
CREATE TABLE IF NOT EXISTS individual(
	id				INT(10)					NOT NULL,
	sync_id			INT(10),
	other_id		VARCHAR(10),
	giving_number	INT(10),
	campus_id		INT(10)					NOT NULL,
	family_id		INT(10)					NOT NULL,
	family_image	BLOB,
	family_position	VARCHAR(100),
	first_name		VARCHAR(50),
	last_name		VARCHAR(50),
	middle_name		VARCHAR(50),
	legal_first_name	VARCHAR(50),
	full_name		VARCHAR(100),
	salutation		VARCHAR(10),
	suffix			VARCHAR(10),
	image			BLOB,
	login			VARCHAR(50),
	email			VARCHAR(100),
	allergies		TEXT,
	confirmed_no_allergies	BOOLEAN,
	address_mailing_street	VARCHAR(100),
	address_mailing_city	VARCHAR(50),
	address_mailing_state	VARCHAR(10),
	address_mailing_zip		VARCHAR(10),
	address_mailing_country	VARCHAR(20),
	address_mailing_line_1	VARCHAR(100),
	address_mailing_line_2	VARCHAR(100),
	address_home_street	VARCHAR(100),
	address_home_city	VARCHAR(50),
	address_home_state	VARCHAR(10),
	address_home_zip		VARCHAR(10),
	address_home_country	VARCHAR(20),
	address_home_line_1	VARCHAR(100),
	address_home_line_2	VARCHAR(100),
	address_work_street	VARCHAR(100),
	address_work_city	VARCHAR(50),
	address_work_state	VARCHAR(10),
	address_work_zip		VARCHAR(10),
	address_work_country	VARCHAR(20),
	address_work_line_1	VARCHAR(100),
	address_work_line_2	VARCHAR(100),
	address_other_street	VARCHAR(100),
	address_other_city	VARCHAR(50),
	address_other_state	VARCHAR(10),
	address_other_zip		VARCHAR(10),
	address_other_country	VARCHAR(20),
	address_other_line_1	VARCHAR(100),
	address_other_line_2	VARCHAR(100),
	phone_contact		VARCHAR(50),
	phone_home			VARCHAR(50),
	phone_work			VARCHAR(50),
	phone_mobile		VARCHAR(50),
	phone_emergency		VARCHAR(50),
	mobile_carrier_id	INT(10),
	gender			VARCHAR(1),
	marital_status	VARCHAR(10),
	birthday		DATE,
	emergency_contact_name	VARCHAR(100),
	anniversary		DATE,
	baptized		BOOLEAN,
	deceased		BOOLEAN,
	membership_type_id	INT(10),
	membership_date	DATE,
	membership_end	DATE,
	receive_email_from_church BOOLEAN,
	default_new_group_messages VARCHAR(50),
	default_new_group_comments VARCHAR(50),
	default_new_group_digest VARCHAR(50),
	profile_listed	BOOLEAN,
	mailing_address_privacy_level_id	INT(10),
	home_address_privacy_level_id	INT(10),
	contact_phone_privacy_level_id	INT(10),
	home_phone_privacy_level_id		INT(10),
	work_phone_privacy_level_id		INT(10),
	mobile_phone_privacy_level_id	INT(10),
	emergency_phone_privacy_level_id	INT(10),
	birthday_privacy_level_id	INT(10),
	anniversary_privacy_level_id	INT(10),
	gender_privacy_level_id	INT(10),
	marital_status_privacy_level_id INT(10),
	user_defined_fields_privacy_level_id INT(10),
	allergies_privacy_level_id INT(10),
	active BOOLEAN,
	creator_id INT(10) NOT NULL,
	modifier_id INT(10) NOT NULL,
	created TIMESTAMP NOT NULL,
	modified TIMESTAMP NOT NULL,
	udf_text_1 VARCHAR(50),
	udf_text_2 VARCHAR(50),
	udf_text_3 VARCHAR(50),
	udf_text_4 VARCHAR(50),
	udf_text_5 VARCHAR(50),
	udf_text_6 VARCHAR(50),
	udf_text_7 VARCHAR(50),
	udf_text_8 VARCHAR(50),
	udf_text_9 VARCHAR(50),
	udf_text_10 VARCHAR(50),
	udf_text_11 VARCHAR(50),
	udf_text_12 VARCHAR(50),
	udf_date_1 DATE,
	udf_date_2 DATE,
	udf_date_3 DATE,
	udf_date_4 DATE,
	udf_date_5 DATE,
	udf_date_6 DATE,
	udf_ind_pulldown_1_id INT(10),
	udf_ind_pulldown_2_id INT(10),
	udf_ind_pulldown_3_id INT(10),
	udf_ind_pulldown_4_id INT(10),
	udf_ind_pulldown_5_id INT(10),
	udf_ind_pulldown_6_id INT(10),
	CONSTRAINT		pk_individual_id		PRIMARY KEY(id),
	CONSTRAINT		fk_campus_id			FOREIGN KEY(campus_id)		REFERENCES campus(id),
	CONSTRAINT		fk_family_id			FOREIGN KEY(family_id)		REFERENCES family(id),
	CONSTRAINT		fk_membership_type_id	FOREIGN KEY(membership_type_id)	REFERENCES membership_type(id),
	CONSTRAINT		fk_mailing_address_privacy_level_id	FOREIGN KEY(mailing_address_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_home_address_privacy_level_id	FOREIGN KEY(home_address_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_contact_phone_privacy_level_id	FOREIGN KEY(contact_phone_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_home_phone_privacy_level_id		FOREIGN KEY(home_phone_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_work_phone_privacy_level_id		FOREIGN KEY(work_phone_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_mobile_phone_privacy_level_id	FOREIGN KEY(mobile_phone_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_emergency_phone_privacy_level_id	FOREIGN KEY(emergency_phone_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_birthday_privacy_level_id		FOREIGN KEY(birthday_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_anniversary_privacy_level_id		FOREIGN KEY(anniversary_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_gender_privacy_level_id			FOREIGN KEY(gender_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_marital_status_privacy_level_id	FOREIGN KEY(marital_status_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_user_defined_fields_privacy_level_id FOREIGN KEY(user_defined_fields_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_allergies_privacy_level_id		FOREIGN KEY(allergies_privacy_level_id) REFERENCES privacy_level(id),
	CONSTRAINT		fk_udf_ind_pulldown_1				FOREIGN KEY(udf_ind_pulldown_1_id) REFERENCES udf_ind_pulldown_1(id),
	CONSTRAINT		fk_udf_ind_pulldown_2				FOREIGN KEY(udf_ind_pulldown_2_id) REFERENCES udf_ind_pulldown_2(id),
	CONSTRAINT		fk_udf_ind_pulldown_3				FOREIGN KEY(udf_ind_pulldown_3_id) REFERENCES udf_ind_pulldown_3(id),
	CONSTRAINT		fk_udf_ind_pulldown_4				FOREIGN KEY(udf_ind_pulldown_4_id) REFERENCES udf_ind_pulldown_4(id),
	CONSTRAINT		fk_udf_ind_pulldown_5				FOREIGN KEY(udf_ind_pulldown_5_id) REFERENCES udf_ind_pulldown_5(id),
	CONSTRAINT		fk_udf_ind_pulldown_6				FOREIGN KEY(udf_ind_pulldown_6_id) REFERENCES udf_ind_pulldown_6(id)
);

-- group_profiles
CREATE TABLE IF NOT EXISTS `group`(
	id				INT(10)					NOT NULL,
	name			VARCHAR(100)			NOT NULL,
	description		TEXT,
	image			BLOB,
	campus_id		INT(10)					NOT NULL,
	main_leader_id	INT(10)					NOT NULL,
	director_id		INT(10),
	group_type_id	INT(10)					NOT NULL,
	department_id	INT(10),
	creator_id		INT(10)					NOT NULL,
	modifier_id		INT(10)					NOT NULL,
	created			TIMESTAMP				NOT NULL,
	modified		TIMESTAMP				NOT NULL,
	CONSTRAINT		pk_group_id				PRIMARY KEY(id),
	CONSTRAINT		uk_group_name			UNIQUE(name),
	CONSTRAINT		fk_group_campus_id		FOREIGN KEY(campus_id)		REFERENCES campus(id),
	CONSTRAINT		fk_group_main_leader_id	FOREIGN KEY(main_leader_id)	REFERENCES individual(id),
	CONSTRAINT		fk_group_department_id	FOREIGN KEY(department_id)	REFERENCES department(id)
);

CREATE TABLE IF NOT EXISTS group_leader(
	group_id		INT(10)					NOT NULL,
	individual_id	INT(10)					NOT NULL,
	CONSTRAINT pk_group_leader				PRIMARY KEY(group_id, individual_id)
);

CREATE TABLE IF NOT EXISTS group_participant(
	group_id		INT(10)					NOT NULL,
	individual_id	INT(10)					NOT NULL,
	CONSTRAINT pk_group_participant			PRIMARY KEY(group_id, individual_id)
);