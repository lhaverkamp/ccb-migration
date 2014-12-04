SELECT *
FROM ss_individual
WHERE NOT EXISTS (
	SELECT 1
	FROM custom_report
	WHERE ss_individual.other_id = custom_report.other_id
) AND (reason_left_church IS NULL OR reason_left_church != 'DELETION')
AND individual_id >= 4527
