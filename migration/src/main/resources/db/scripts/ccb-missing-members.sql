SELECT *
FROM ss_individual
WHERE NOT EXISTS (
	SELECT 1
	FROM ccb_export
	WHERE ss_individual.other_id = ccb_export.other_id
) AND (reason_left_church IS NULL OR reason_left_church != 'DELETION')
AND individual_id NOT IN (1735, 3830, 3831);
