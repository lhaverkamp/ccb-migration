-- The following fields aren't part of the CCB API and need to be synced 
-- manually:
-- family_position (is but requires one child families to be Primary Contact)
-- limited_access_user
-- fax
-- pager
-- marital_status
-- school
-- school_grade
-- military
-- baptized
-- listed
-- inactive
-- how_they_heard
-- reason_left_church

-- Gender
SELECT custom_report.*, t.first_name, t.last_name, t.gender, individual_export.gender
FROM ss_individual t
INNER JOIN custom_report ON t.other_id = custom_report.other_id
INNER JOIN individual_export ON custom_report.individual_id = individual_export.individual_id
WHERE t.gender != SUBSTR(individual_export.gender, 1, 1);

SELECT custom_report.*, t.first_name, t.last_name, t.gender, individual_export.gender
FROM cw_individual t
INNER JOIN custom_report ON t.other_id = custom_report.other_id
INNER JOIN individual_export ON custom_report.individual_id = individual_export.individual_id
WHERE t.gender != SUBSTR(individual_export.gender, 1, 1);

-- Family Position
SELECT custom_report.*, t.first_name, t.last_name, t.family_position, individual_export.family_position
FROM ss_individual t
INNER JOIN custom_report ON t.other_id = custom_report.other_id
INNER JOIN individual_export ON custom_report.individual_id = individual_export.individual_id
WHERE t.family_position != individual_export.family_position
AND t.family_position != 'Other';

SELECT custom_report.*, t.first_name, t.last_name, t.family_position, individual_export.family_position
FROM cw_individual t
INNER JOIN custom_report ON t.other_id = custom_report.other_id
INNER JOIN individual_export ON custom_report.individual_id = individual_export.individual_id
WHERE t.family_position != individual_export.family_position
AND t.family_position != 'Other';

-- Deceased
SELECT custom_report.*, t.first_name, t.last_name, t.deceased, individual_export.deceased
FROM ss_individual t
INNER JOIN custom_report ON t.other_id = custom_report.other_id
INNER JOIN individual_export ON custom_report.individual_id = individual_export.individual_id
WHERE t.deceased != individual_export.deceased_date;

SELECT custom_report.*, t.first_name, t.last_name, t.deceased, individual_export.deceased
FROM cw_individual t
INNER JOIN custom_report ON t.other_id = custom_report.other_id
INNER JOIN individual_export ON custom_report.individual_id = individual_export.individual_id
WHERE t.deceased != individual_export.deceased_date;

-- Maiden Name
SELECT custom_report.*, t.first_name, t.last_name, t.maiden_name, individual_export.maiden_name
FROM ss_individual t
INNER JOIN custom_report ON t.other_id = custom_report.other_id
INNER JOIN individual_export ON custom_report.individual_id = individual_export.individual_id
WHERE t.maiden_name != individual_export.maiden_name;

SELECT custom_report.*, t.first_name, t.last_name, t.maiden_name, individual_export.maiden_name
FROM cw_individual t
INNER JOIN custom_report ON t.other_id = custom_report.other_id
INNER JOIN individual_export ON custom_report.individual_id = individual_export.individual_id
WHERE t.maiden_name != individual_export.maiden_name;

-- Inactive
SELECT custom_report.*, t.first_name, t.last_name, t.inactive, individual_export.inactive
FROM ss_individual t
INNER JOIN custom_report ON t.other_id = custom_report.other_id
INNER JOIN individual_export ON custom_report.individual_id = individual_export.individual_id
WHERE t.inactive != individual_export.inactive;

SELECT custom_report.*, t.first_name, t.last_name, t.inactive, individual_export.inactive
FROM cw_individual t
INNER JOIN custom_report ON t.other_id = custom_report.other_id
INNER JOIN individual_export ON custom_report.individual_id = individual_export.individual_id
WHERE t.inactive != individual_export.inactive;

-- Membership Type
SELECT * FROM custom_report WHERE membership_type = 'Member';

-- Reason Left Church
SELECT * FROM custom_report WHERE reason_left_church = 'DELETION';

