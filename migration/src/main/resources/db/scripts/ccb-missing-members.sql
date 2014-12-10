-- Shepherd's Staff
SELECT *
FROM ss_individual
WHERE NOT EXISTS (
	SELECT 1
	FROM custom_report
	WHERE ss_individual.other_id = custom_report.other_id
) AND (reason_left_church IS NULL OR reason_left_church != 'DELETION')
AND individual_id >= 4527

-- Church Windows
SELECT *
FROM cw_individual
WHERE NOT EXISTS (
	SELECT 1
	FROM custom_report
	WHERE cw_individual.other_id = custom_report.other_id
) AND NOT EXISTS (
	SELECT 1
	FROM ss_individual
	WHERE cw_individual.first_name IN (ss_individual.first_name, ss_individual.legal_name)
	  AND cw_individual.last_name IN (ss_individual.last_name, ss_individual.maiden_name)
)/* AND EXISTS (
	SELECT 1
	FROM AttRec
	WHERE cw_individual.individual_id = AttRec.IndivNo
)*/
