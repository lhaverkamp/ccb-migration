-- Gender
SELECT ccb_export.*, t.first_name, t.last_name, t.gender
FROM ss_individual t
INNER JOIN ccb_export ON t.other_id = ccb_export.other_id
WHERE t.last_name = ccb_export.last_name
AND t.gender != ccb_export.gender;

SELECT ccb_export.*, t.first_name, t.last_name, t.gender
FROM cw_individual t
INNER JOIN ccb_export ON t.other_id = ccb_export.other_id
WHERE t.last_name = ccb_export.last_name
AND t.gender != ccb_export.gender;

-- Family Position
SELECT ccb_export.*, t.first_name, t.last_name, t.family_position
FROM ss_individual t
INNER JOIN ccb_export ON t.other_id = ccb_export.other_id
WHERE t.last_name = ccb_export.last_name
AND t.family_position != ccb_export.family_position
AND t.family_position != 'Other';

SELECT ccb_export.*, t.first_name, t.last_name, t.family_position
FROM cw_individual t
INNER JOIN ccb_export ON t.other_id = ccb_export.other_id
WHERE t.last_name = ccb_export.last_name
AND t.family_position != ccb_export.family_position
AND t.family_position != 'Other';

-- Deceased
SELECT ccb_export.*, t.first_name, t.last_name, t.deceased
FROM ss_individual t
INNER JOIN ccb_export ON t.other_id = ccb_export.other_id
WHERE t.last_name = ccb_export.last_name
AND t.deceased != ccb_export.deceased_date;

SELECT ccb_export.*, t.first_name, t.last_name, t.deceased
FROM cw_individual t
INNER JOIN ccb_export ON t.other_id = ccb_export.other_id
WHERE t.last_name = ccb_export.last_name
AND t.deceased != ccb_export.deceased_date;

-- Maiden Name
SELECT ccb_export.*, t.first_name, t.last_name, t.maiden_name
FROM ss_individual t
INNER JOIN ccb_export ON t.other_id = ccb_export.other_id
WHERE t.last_name = ccb_export.last_name
AND t.maiden_name != ccb_export.maiden_name;

SELECT ccb_export.*, t.first_name, t.last_name, t.maiden_name
FROM cw_individual t
INNER JOIN ccb_export ON t.other_id = ccb_export.other_id
WHERE t.last_name = ccb_export.last_name
AND t.maiden_name != ccb_export.maiden_name;

-- Inactive
SELECT ccb_export.*, t.first_name, t.last_name, t.inactive
FROM ss_individual t
INNER JOIN ccb_export ON t.other_id = ccb_export.other_id
WHERE t.last_name = ccb_export.last_name
AND t.inactive != ccb_export.inactive;

SELECT ccb_export.*, t.first_name, t.last_name, t.inactive
FROM cw_individual t
INNER JOIN ccb_export ON t.other_id = ccb_export.other_id
WHERE t.last_name = ccb_export.last_name
AND t.inactive != ccb_export.inactive;

-- Membership Type
SELECT * FROM ccb_export WHERE membership_type = 'Member';

-- Reason Left Church
SELECT * FROM ccb_export WHERE reason_left_church = 'DELETION';

