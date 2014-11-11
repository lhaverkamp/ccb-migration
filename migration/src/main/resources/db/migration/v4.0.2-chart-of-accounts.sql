CREATE OR REPLACE VIEW vw_chart_of_accounts AS
SELECT 
	fund,
	subfund,
	IF(tax_deductible, 'Yes', 'No') AS tax_deductible,
	campus,
	IF(active, 'Active', 'Inactive') AS 'Active/Inactive'
FROM ss_chart_of_account
UNION
SELECT 
	CASE fund
		WHEN 'Lenten Offering' THEN 'Lent'
		WHEN 'Memorial' THEN 'Memorials'
		WHEN 'Mission' THEN 'Missions'
		ELSE fund
	END AS fund,
	subfund,
	IF(tax_deductible, 'Yes', 'No') AS tax_deductible,
	campus,
	IF(active, 'Active', 'Inactive') AS 'Active/Inactive'
FROM cw_chart_of_account;
