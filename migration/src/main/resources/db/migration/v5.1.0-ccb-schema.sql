CREATE TABLE IF NOT EXISTS privacy(
	id INT(10),
	name VARCHAR(50),
	CONSTRAINT pk_privacy PRIMARY KEY(id)
);
INSERT INTO privacy(id, name) VALUES(1, 'Church Leadership Only') ON DUPLICATE KEY UPDATE name = 'Church Leadership Only';
INSERT INTO privacy(id, name) VALUES(2, 'Friends Only') ON DUPLICATE KEY UPDATE name = 'Friends Only';
INSERT INTO privacy(id, name) VALUES(3, 'Friends & My Group Members') ON DUPLICATE KEY UPDATE name = 'Friends & My Group Members';
INSERT INTO privacy(id, name) VALUES(4, 'Everybody') ON DUPLICATE KEY UPDATE name = 'Everybody';

CREATE TABLE IF NOT EXISTS individual_export(
	individual_id INT(10) NOT NULL,
	family_id INT(10) NOT NULL,
	family_position VARCHAR(50),
	limited_access_user BOOLEAN NOT NULL DEFAULT FALSE,
	prefix VARCHAR(50),
	first_name VARCHAR(50),
	middle_name VARCHAR(50),
	last_name VARCHAR(50),
	suffix VARCHAR(50),
	campus VARCHAR(50),
	email VARCHAR(50),
	email_privacy_level INT(10) NOT NULL,
	general_communication BOOLEAN NOT NULL DEFAULT TRUE,
	area_of_town VARCHAR(50),
	mailing_street VARCHAR(100),
	mailing_city VARCHAR(50),
	mailing_state VARCHAR(50),
	mailing_zip VARCHAR(10),
	mailing_carrier_route VARCHAR(50),
	mailing_privacy_level INT(10) NOT NULL,
	mailing_latitude DOUBLE(10,6),
	mailing_longitude DOUBLE(10,6),
	contact_phone VARCHAR(12),
	contact_phone_privacy_level INT(10) NOT NULL,
	home_phone VARCHAR(12),
	home_phone_privacy_level INT(10) NOT NULL,
	work_phone VARCHAR(12),
	work_phone_privacy_level INT(10) NOT NULL,
	mobile_phone VARCHAR(12),
	mobile_phone_privacy_level INT(10) NOT NULL,
	fax VARCHAR(12),
	fax_privacy_level INT(10) NOT NULL,
	pager VARCHAR(12),
	pager_privacy_level INT(10) NOT NULL,
	emergency_phone VARCHAR(12),
	emergency_phone_privacy_level INT(10) NOT NULL,
	emergency_contact_name VARCHAR(50),
	birthday DATE,
	birthday_privacy_level INT(10) NOT NULL,
	anniversary DATE,
	anniversary_privacy_level INT(10) NOT NULL,
	gender VARCHAR(10),
	gender_privacy_level INT(10) NOT NULL,
	giving_number INT(10),
	marital_status VARCHAR(50),
	marital_status_privacy_level INT(10) NOT NULL,
	home_area VARCHAR(50),
	home_street VARCHAR(100),
	home_city VARCHAR(50),
	home_state VARCHAR(50),
	home_zip VARCHAR(10),
	home_privacy_level INT(10) NOT NULL,
	work_area VARCHAR(50),
	work_street VARCHAR(100),
	work_city VARCHAR(50),
	work_state VARCHAR(50),
	work_zip VARCHAR(10),
	work_privacy_level INT(10) NOT NULL,
	other_area VARCHAR(50),
	other_street VARCHAR(100),
	other_city VARCHAR(50),
	other_state VARCHAR(50),
	other_zip VARCHAR(10),
	other_privacy_level INT(10) NOT NULL,
	work_title VARCHAR(50),
	school_name VARCHAR(50),
	school_grade VARCHAR(50),
	badge_queue VARCHAR(50),
	allergies VARCHAR(50),
	allergies_privacy_level INT(10) NOT NULL,
	salvation_date DATE,
	chat_aol VARCHAR(50),
	chat_msn VARCHAR(50),
	chat_icq VARCHAR(50),
	my_website VARCHAR(50),
	work_website VARCHAR(50),
	favorite_radio_station VARCHAR(50),
	military VARCHAR(50),
	user_defined_text_1 VARCHAR(50),
	user_defined_text_2 VARCHAR(50),
	user_defined_text_3 VARCHAR(50),
	user_defined_text_4 VARCHAR(50),
	user_defined_text_5 VARCHAR(50),
	user_defined_text_6 VARCHAR(50),
	user_defined_text_7 VARCHAR(50),
	user_defined_text_8 VARCHAR(50),
	user_defined_text_9 VARCHAR(50),
	confirmation_verse VARCHAR(50),
	email_work VARCHAR(50),
	maiden_name VARCHAR(50),
	user_defined_date_1 DATE,
	user_defined_date_2 DATE,
	user_defined_date_3 DATE,
	first_communion DATE,
	confirmation DATE,
	baptism DATE,
	user_defined_pulldown_1 VARCHAR(50),
	user_defined_pulldown_2 VARCHAR(50),
	user_defined_pulldown_3 VARCHAR(50),
	elder VARCHAR(50),
	newsletter VARCHAR(50),
	confirmed VARCHAR(50),
	user_defined_privacy_level INT(10) NOT NULL,
	my_fit_privacy_level INT(10) NOT NULL,
	community_privacy_level INT(10) NOT NULL,
	created_by VARCHAR(50) NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	created_date TIMESTAMP NOT NULL,
	modified_date TIMESTAMP NOT NULL,
	age_bracket VARCHAR(50),
	membership_start_date DATE,
	membership_end_date DATE,
	membership_type VARCHAR(50),
	spiritual_maturity VARCHAR(50),
	baptized BOOLEAN,
	listed BOOLEAN DEFAULT TRUE NOT NULL,
	inactive BOOLEAN DEFAULT FALSE NOT NULL,
	how_they_heard VARCHAR(50),
	reason_left_church VARCHAR(50),
	child_work_date_start DATE,
	child_work_date_end DATE,
	CONSTRAINT pk_individual_export PRIMARY KEY (individual_id)
);
/*
 * ,
	CONSTRAINT fk_individual_export_email_privacy_level FOREIGN KEY (email_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_mailing_privacy_level FOREIGN KEY (mailing_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_contact_phone_privacy_level FOREIGN KEY (contact_phone_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_home_phone_privacy_level FOREIGN KEY (home_phone_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_work_phone_privacy_level FOREIGN KEY (work_phone_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_mobile_phone_privacy_level FOREIGN KEY (mobile_phone_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_fax_privacy_level FOREIGN KEY (fax_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_pager_privacy_level FOREIGN KEY (pager_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_emergency_phone_privacy_level FOREIGN KEY (emergency_phone_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_birthday_privacy_level FOREIGN KEY (birthday_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_anniversary_privacy_level FOREIGN KEY (anniversary_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_gender_privacy_level FOREIGN KEY (gender_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_marital_status_privacy_level FOREIGN KEY (marital_status_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_home_privacy_level FOREIGN KEY (home_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_work_privacy_level FOREIGN KEY (work_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_other_privacy_level FOREIGN KEY (other_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_allergies_privacy_level FOREIGN KEY (allergies_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_user_defined_privacy_level FOREIGN KEY (user_defined_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_my_fit_privacy_level FOREIGN KEY (my_fit_privacy_level) REFERENCES privacy(id),
	CONSTRAINT fk_individual_export_community_privacy_level FOREIGN KEY (community_privacy_level) REFERENCES privacy(id)
 */

CREATE TABLE IF NOT EXISTS custom_report(
	individual_id INT(10) NOT NULL,
	family_id INT(10) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	other_id INT(10),
	CONSTRAINT pk_custom_export PRIMARY KEY (individual_id)
);
