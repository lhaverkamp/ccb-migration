CREATE OR REPLACE VIEW vw_contributions AS 
SELECT
	ss_contribution.individual_id,
	DATE_FORMAT(ss_contribution.contribution_date, '%Y-%m-%d') AS contribution_date,
	ss_contribution.amount,
	ss_contribution.type_of_gift,
	ss_contribution.check_number,
	ss_contribution.fund,
	ss_contribution.subfund,
	ss_contribution.campus,
	ss_contribution.transaction_grouping,
	ss_contribution.batch_number,
	IF(ss_contribution.tax_deductible, 'Yes', 'No') AS tax_deductible,
	ss_contribution.memo
FROM ss_contribution
UNION ALL
SELECT
	IF(ss_individual.individual_id IS NOT NULL, ss_individual.individual_id, cw_contribution.individual_id + 1000000) AS individual_id,
	DATE_FORMAT(cw_contribution.contribution_date, '%Y-%m-%d') AS contribution_date,
	cw_contribution.amount,
	cw_contribution.type_of_gift,
	cw_contribution.check_number,
	CASE fund
		WHEN 'Envelope Offering' THEN 'General Fund'
		WHEN 'Lenten Offering' THEN 'Lent'
		WHEN 'Memorial' THEN 'Memorials'
		WHEN 'Mission' THEN 'Missions'
		ELSE fund
	END AS fund,
	cw_contribution.subfund,
	cw_contribution.campus,
	cw_contribution.transaction_grouping,
	cw_contribution.batch_number,
	IF(cw_contribution.tax_deductible, 'Yes', 'No') AS tax_deductible,
	cw_contribution.memo
FROM cw_contribution
INNER JOIN cw_individual ON cw_contribution.individual_id = cw_individual.individual_id
LEFT JOIN ss_individual
	ON cw_individual.first_name IN (ss_individual.first_name, ss_individual.legal_name)
	AND cw_individual.last_name IN (ss_individual.last_name, ss_individual.maiden_name);
