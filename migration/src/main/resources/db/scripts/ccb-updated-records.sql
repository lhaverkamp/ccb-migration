SELECT 
	individual_export.individual_id,
	individual_export.family_id,
	individual_export.family_position,
	t.family_position,
	individual_export.limited_access_user,
	t.limited_access_user,
	individual_export.prefix,
	t.prefix,
	individual_export.first_name,
	t.first_name,
	individual_export.middle_name,
	t.middle_name,
	individual_export.last_name,
	t.last_name,
	individual_export.suffix,
	t.suffix,
	individual_export.email,
	IF(t.email_individual IS NULL, t.email_family, t.email_individual) AS email,
	individual_export.area_of_town,
	t.area_of_town,
	individual_export.mailing_street,
	t.address_mailing_street1,
	individual_export.mailing_city,
	t.address_mailing_city,
	individual_export.mailing_state,
	t.address_mailing_state,
	individual_export.mailing_zip,
	t.address_mailing_postal_code,
	individual_export.mailing_carrier_route,
	t.address_mailing_carrier_route,
	individual_export.contact_phone,
	t.phone_contact,
	individual_export.home_phone,
	t.phone_home,
	individual_export.work_phone,
	t.phone_work,
	individual_export.mobile_phone,
	t.phone_mobile,
	individual_export.fax,
	t.fax,
	individual_export.pager,
	t.pager,
	individual_export.emergency_phone,
	t.emergency_phone,
	individual_export.emergency_contact_name,
	t.emergency_contact_name,
	individual_export.birthday,
	t.birthdate,
	individual_export.anniversary,
	t.anniversary,
	individual_export.gender,
	t.gender,
	individual_export.giving_number,
	t.giving_number,
	individual_export.marital_status,
	t.marital_status,
	individual_export.home_area,
	t.area_of_town,
	individual_export.home_street,
	t.address_home_street1,
	individual_export.home_city,
	t.address_home_city,
	individual_export.home_state,
	t.address_home_state,
	individual_export.home_zip,
	t.address_home_postal_code,
	individual_export.work_street,
	t.address_work_street1,
	individual_export.work_city,
	t.address_work_city,
	individual_export.work_state,
	t.address_work_state,
	individual_export.work_zip,
	t.address_work_postal_code,
	individual_export.other_street,
	t.address_other_street1,
	individual_export.other_city,
	t.address_other_city,
	individual_export.other_state,
	t.address_other_state,
	individual_export.other_zip,
	t.address_other_postal_code,
	individual_export.work_title,
	t.job_title,
	individual_export.school_name,
	t.school,
	individual_export.school_grade,
	t.school_grade,
	individual_export.allergies,
	t.known_allergies,
	individual_export.military,
	t.military,
	individual_export.confirmation_verse,
	t.confirmation_verse,
	individual_export.email_work,
	t.email_work,
	individual_export.maiden_name,
	t.maiden_name,
	individual_export.first_communion,
	t.first_communion_date,
	individual_export.confirmation,
	t.confirmation_date,
	individual_export.baptism,
	t.baptism_date,
	individual_export.elder,
	t.elder,
	individual_export.newsletter,
	t.newsletter,
	individual_export.confirmed,
	t.confirmed,
	individual_export.membership_start_date,
	t.membership_date,
	individual_export.membership_end_date,
	t.membership_stop_date,
	individual_export.membership_type,
	t.membership_type,
	individual_export.baptized,
	t.baptized,
	individual_export.listed,
	t.listed,
	individual_export.inactive,
	t.inactive,
	individual_export.how_they_heard,
	t.how_they_heard,
	individual_export.reason_left_church,
	t.reason_left_church
FROM individual_export
INNER JOIN custom_report ON individual_export.individual_id = custom_report.individual_id
INNER JOIN ss_individual t ON custom_report.other_id = t.other_id AND custom_report.last_name = t.last_name
WHERE individual_export.modified_by IN ('System', 'Laura Haverkamp')
  AND NOT (/*individual_export.family_position = t.family_position
  AND*/ individual_export.limited_access_user = t.limited_access_user
  AND individual_export.prefix <=> t.prefix
  AND individual_export.first_name = t.first_name
  AND individual_export.middle_name <=> t.middle_name
  AND individual_export.last_name = t.last_name
  AND individual_export.suffix <=> t.suffix
--  AND individual_export.email <=> IF(t.email_individual IS NULL, t.email_family, t.email_individual)
--  AND individual_export.area_of_town <=> t.area_of_town
--  AND individual_export.mailing_street <=> t.address_mailing_street1
--  AND individual_export.mailing_city <=> t.address_mailing_city
--  AND individual_export.mailing_state <=> t.address_mailing_state
--  AND individual_export.mailing_zip <=> t.address_mailing_postal_code
--  AND individual_export.mailing_carrier_route <=> t.address_mailing_carrier_route
  AND (REPLACE(individual_export.contact_phone, '+1', '') <=> t.phone_contact OR t.phone_contact IS NULL)
  AND (REPLACE(individual_export.home_phone, '+1', '') <=> t.phone_home OR t.phone_home IS NULL)
  AND (REPLACE(individual_export.work_phone, '+1', '') <=> t.phone_work OR t.phone_work IS NULL)
  AND (REPLACE(individual_export.mobile_phone, '+1', '') <=> t.phone_mobile OR t.phone_mobile IS NULL)
  AND individual_export.fax <=> t.fax
  AND individual_export.pager <=> t.pager
  AND individual_export.emergency_phone <=> t.emergency_phone
  AND individual_export.emergency_contact_name <=> t.emergency_contact_name
  AND individual_export.birthday <=> t.birthdate
  --AND individual_export.anniversary <=> t.anniversary
  AND SUBSTR(individual_export.gender, 1, 1) <=> t.gender
  AND individual_export.giving_number <=> t.giving_number
  AND (individual_export.marital_status <=> t.marital_status OR individual_export.anniversary IS NOT NULL OR t.marital_status IS NULL)
--  AND individual_export.home_area <=> t.area_of_town
--  AND individual_export.home_street <=> t.address_home_street1
--  AND individual_export.home_city <=> t.address_home_city
--  AND individual_export.home_state <=> t.address_home_state
--  AND individual_export.home_zip <=> t.address_home_postal_code
--  AND individual_export.work_street <=> t.address_work_street1
--  AND individual_export.work_city <=> t.address_work_city
--  AND individual_export.work_state <=> t.address_work_state
--  AND individual_export.work_zip <=> t.address_work_postal_code
--  AND individual_export.other_street <=> t.address_other_street1
--  AND individual_export.other_city <=> t.address_other_city
--  AND individual_export.other_state <=> t.address_other_state
--  AND individual_export.other_zip <=> t.address_other_postal_code
  AND individual_export.work_title <=> t.job_title
  AND individual_export.school_name <=> t.school
  AND individual_export.school_grade <=> t.school_grade
  AND individual_export.allergies <=> t.known_allergies
  AND individual_export.military <=> t.military
  AND (individual_export.confirmation_verse <=> t.confirmation_verse OR t.confirmation_verse IS NULL)
  AND individual_export.email_work <=> t.email_work
  AND individual_export.maiden_name <=> t.maiden_name
  AND individual_export.first_communion <=> t.first_communion_date
  AND individual_export.confirmation <=> t.confirmation_date
  AND individual_export.baptism <=> t.baptism_date
  AND individual_export.elder <=> t.elder
  AND individual_export.newsletter <=> IF(t.newsletter, 'Yes', NULL)
  AND individual_export.confirmed <=> IF(t.confirmed, 'Yes', NULL)
  AND individual_export.membership_start_date <=> t.membership_date
  AND individual_export.membership_end_date <=> t.membership_stop_date
  AND individual_export.membership_type <=> t.membership_type
  AND individual_export.baptized <=> t.baptized
  AND individual_export.listed <=> t.listed
  AND (individual_export.inactive <=> t.inactive OR individual_export.membership_type = 'Deceased')
  AND individual_export.how_they_heard <=> t.how_they_heard
  AND individual_export.reason_left_church <=> t.reason_left_church
)
