CREATE OR REPLACE VIEW vw_contributions AS 
SELECT
	ss_contribution.individual_id,
	ss_contribution.contribution_date,
	ss_contribution.amount,
	ss_contribution.type_of_gift,
	ss_contribution.check_number,
	ss_contribution.fund,
	ss_contribution.subfund,
	ss_contribution.campus,
	ss_contribution.transaction_grouping,
	ss_contribution.batch_number,
	ss_contribution.tax_deductible,
	ss_contribution.memo
FROM ss_contribution
UNION ALL
SELECT
	vw_individual.individual_id,
	cw_contribution.contribution_date,
	cw_contribution.amount,
	cw_contribution.type_of_gift,
	cw_contribution.check_number,
	cw_contribution.fund,
	cw_contribution.subfund,
	cw_contribution.campus,
	cw_contribution.transaction_grouping,
	cw_contribution.batch_number,
	cw_contribution.tax_deductible,
	cw_contribution.memo
FROM cw_contribution
INNER JOIN vw_individual ON cw_contribution.individual_id = vw_individual.cw_individual_id;
