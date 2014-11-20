CREATE OR REPLACE VIEW vw_individuals AS
SELECT
	ss_individual.individual_id,
	ss_individual.family_id,
	ss_individual.family_position,
	ss_individual.prefix,
	ss_individual.first_name,
	ss_individual.middle_name,
	ss_individual.last_name,
	ss_individual.suffix,
	ss_individual.legal_name,
	ss_individual.maiden_name,
	IF(ss_individual.limited_access_user, 'Yes', 'No') AS limited_access_user,
	IF(ss_individual.listed, 'Yes', 'No') AS listed,
	IF(ss_individual.inactive, 'Yes', 'No') AS inactive_remove,
	ss_individual.campus,
	ss_individual.email_family,
	ss_individual.email_individual,
	ss_individual.address_mailing_street1,
	ss_individual.address_mailing_street2,
	ss_individual.address_mailing_city,
	ss_individual.address_mailing_state,
	ss_individual.address_mailing_postal_code,
	ss_individual.address_mailing_country,
	ss_individual.address_mailing_carrier_route,
	ss_individual.address_home_street1,
	ss_individual.address_home_street2,
	ss_individual.address_home_city,
	ss_individual.address_home_state,
	ss_individual.address_home_postal_code,
	ss_individual.area_of_town,
	ss_individual.address_other_street1,
	ss_individual.address_other_street2,
	ss_individual.address_other_city,
	ss_individual.address_other_state,
	ss_individual.address_other_postal_code,
	ss_individual.phone_contact,
	ss_individual.phone_home,
	ss_individual.phone_work,
	ss_individual.phone_mobile,
	ss_individual.service_provider,
	ss_individual.phone_other,
	ss_individual.fax,
	ss_individual.pager,
	ss_individual.emergency_phone,
	ss_individual.emergency_contact_name,
	ss_individual.birthdate,
	ss_individual.anniversary,
	ss_individual.gender,
	ss_individual.giving_number,
	ss_individual.marital_status,
	ss_individual.membership_date,
	ss_individual.membership_stop_date,
	ss_individual.membership_type,
	IF(ss_individual.baptized, 'Yes', 'No') AS baptized,
	ss_individual.baptism_date,
	ss_individual.school,
	ss_individual.school_grade,
	ss_individual.known_allergies,
	NULLIF(CONCAT_WS('; ', cw_individual.notes_family, ss_individual.notes_family), '') AS notes_family,
	NULLIF(CONCAT_WS('; ', cw_individual.notes_individual, ss_individual.notes_individual), '') AS notes_individual,
	ss_individual.approved_to_work_with_children,
	ss_individual.approved_to_work_with_children_stop_date,
	ss_individual.commitment_date,
	ss_individual.how_they_heard,
	ss_individual.how_they_joined,
	ss_individual.reason_left_church,
	ss_individual.job_title,
	ss_individual.email_work,
	ss_individual.address_work_street1,
	ss_individual.address_work_street2,
	ss_individual.address_work_city,
	ss_individual.address_work_state,
	ss_individual.address_work_postal_code,
	ss_individual.testimony_current,
	ss_individual.testimony_salvation,
	ss_individual.deceased,
	ss_individual.facebook_username,
	ss_individual.twitter_username,
	ss_individual.blog_username,
	ss_individual.website_personal,
	ss_individual.website_work,
	ss_individual.military,
	ss_individual.spiritual_maturity,
	ss_individual.spiritual_gifts,
	ss_individual.passions,
	ss_individual.abilities_skills,
	ss_individual.church_services_i_attend,
	ss_individual.personal_style,
	ss_individual.process_queue1,
	ss_individual.process_queue2,
	
	IF(ss_individual.confirmed, 'Yes', 'No') AS confirmed,
	ss_individual.confirmation_date,
	ss_individual.first_communion_date,
	ss_individual.confirmation_verse,
	IF(ss_individual.newsletter, 'Yes', 'No') AS newsletter,
	ss_individual.elder,
	ss_individual.other_id
FROM ss_individual
LEFT JOIN cw_individual 
	ON cw_individual.first_name IN (ss_individual.first_name, ss_individual.legal_name)
	AND cw_individual.last_name IN (ss_individual.last_name, ss_individual.maiden_name)
UNION ALL
SELECT
	cw_individual.individual_id + 1000000 AS individual_id,
	vw_family.family_id,
	IF(cw_individual.deceased IS NOT NULL, 'Other', cw_individual.family_position) AS family_position,
	cw_individual.prefix,
	cw_individual.first_name,
	cw_individual.middle_name,
	cw_individual.last_name,
	cw_individual.suffix,
	cw_individual.legal_name,
	cw_individual.maiden_name,
	IF(cw_individual.limited_access_user, 'Yes', 'No'),
	IF(cw_individual.listed, 'Yes', 'No') AS listed,
	'Yes' AS inactive_remove,
	cw_individual.campus,
	cw_individual.email_family,
	cw_individual.email_individual,
	cw_individual.address_mailing_street1,
	cw_individual.address_mailing_street2,
	cw_individual.address_mailing_city,
	cw_individual.address_mailing_state,
	cw_individual.address_mailing_postal_code,
	cw_individual.address_mailing_country,
	cw_individual.address_mailing_carrier_route,
	cw_individual.address_home_street1,
	cw_individual.address_home_street2,
	cw_individual.address_home_city,
	cw_individual.address_home_state,
	cw_individual.address_home_postal_code,
	cw_individual.area_of_town,
	cw_individual.address_other_street1,
	cw_individual.address_other_street2,
	cw_individual.address_other_city,
	cw_individual.address_other_state,
	cw_individual.address_other_postal_code,
	cw_individual.phone_contact,
	cw_individual.phone_home,
	cw_individual.phone_work,
	cw_individual.phone_mobile,
	cw_individual.service_provider,
	cw_individual.phone_other,
	cw_individual.fax,
	cw_individual.pager,
	cw_individual.emergency_phone,
	cw_individual.emergency_contact_name,
	cw_individual.birthdate,
	cw_individual.anniversary,
	cw_individual.gender,
	NULL AS giving_number,
	cw_individual.marital_status,
	cw_individual.membership_date,
	cw_individual.membership_stop_date,
	cw_individual.membership_type,
	IF(cw_individual.baptized, 'Yes', 'No') AS baptized,
	cw_individual.baptism_date,
	cw_individual.school,
	cw_individual.school_grade,
	cw_individual.known_allergies,
	cw_individual.notes_family,
	cw_individual.notes_individual,
	cw_individual.approved_to_work_with_children,
	cw_individual.approved_to_work_with_children_stop_date,
	cw_individual.commitment_date,
	cw_individual.how_they_heard,
	cw_individual.how_they_joined,
	cw_individual.reason_left_church,
	cw_individual.job_title,
	cw_individual.email_work,
	cw_individual.address_work_street1,
	cw_individual.address_work_street2,
	cw_individual.address_work_city,
	cw_individual.address_work_state,
	cw_individual.address_work_postal_code,
	cw_individual.testimony_current,
	cw_individual.testimony_salvation,
	cw_individual.deceased,
	cw_individual.facebook_username,
	cw_individual.twitter_username,
	cw_individual.blog_username,
	cw_individual.website_personal,
	cw_individual.website_work,
	cw_individual.military,
	cw_individual.spiritual_maturity,
	cw_individual.spiritual_gifts,
	cw_individual.passions,
	cw_individual.abilities_skills,
	cw_individual.church_services_i_attend,
	cw_individual.personal_style,
	cw_individual.process_queue1,
	cw_individual.process_queue2,
	
	IF(cw_individual.confirmed, 'Yes', 'No') AS confirmed,
	cw_individual.confirmation_date,
	cw_individual.first_communion_date,
	cw_individual.confirmation_verse,
	IF(cw_individual.newsletter, 'Yes', 'No') AS newsletter,
	cw_individual.elder,
	cw_individual.individual_id AS other_id
FROM cw_individual
LEFT JOIN vw_family ON cw_individual.family_id = vw_family.cw_family_id 
WHERE NOT EXISTS (
	SELECT 1
	FROM ss_individual
	WHERE cw_individual.first_name IN (ss_individual.first_name, ss_individual.legal_name)
	  AND cw_individual.last_name IN (ss_individual.last_name, ss_individual.maiden_name)
);