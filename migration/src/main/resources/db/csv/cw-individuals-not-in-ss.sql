SELECT
	cw_individual.individual_id,
	cw_individual.family_id,
	cw_individual.family_position,
	cw_individual.prefix,
	cw_individual.first_name,
	cw_individual.middle_name,
	cw_individual.last_name,
	cw_individual.suffix,
	cw_individual.legal_name,
	cw_individual.maiden_name,
	cw_individual.limited_access_user,
	cw_individual.listed,
	cw_individual.inactive,
	cw_individual.campus,
	cw_individual.email_family,
	cw_individual.email_individual,
	cw_individual.address_mailing_street1,
	cw_individual.address_mailing_street2,
	cw_individual.address_mailing_city,
	cw_individual.address_mailing_state,
	cw_individual.address_mailing_postal_code,
	cw_individual.address_mailing_country,
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
	cw_individual.giving_number,
	cw_individual.marital_status,
	cw_individual.membership_date,
	cw_individual.membership_stop_date,
	cw_individual.membership_type,
	cw_individual.baptized,
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
	cw_individual.confirmed,
	cw_individual.confirmation_date,
	cw_individual.custom_text2,
	cw_individual.custom_text3,
	cw_individual.custom_date2,
	cw_individual.custom_date3,
	cw_individual.first_communion_date,
	cw_individual.significant_event2,
	cw_individual.significant_event3,
	cw_individual.confirmation_verse,
	cw_individual.newsletter,
	cw_individual.elder,
	cw_individual.other_id
FROM cw_individual
WHERE NOT EXISTS (
	SELECT 1
	FROM ss_individual
	WHERE cw_individual.first_name IN (ss_individual.first_name, ss_individual.legal_name)
	  AND cw_individual.last_name IN (ss_individual.last_name, ss_individual.maiden_name)
);

