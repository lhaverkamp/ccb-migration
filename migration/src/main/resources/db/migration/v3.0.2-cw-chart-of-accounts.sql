LOAD DATA INFILE '../csv/cw-chart-of-accounts.csv'
INTO TABLE cw_chart_of_account
FIELDS
	TERMINATED BY ','
	OPTIONALLY ENCLOSED BY '"'
LINES
	TERMINATED BY '\n'
IGNORE 1 LINES
(
	fund,
	@subfund,
	@tax_deductible,
	@campus,
	@active
)
SET
	subfund = NULLIF(@subfund, ''),
	tax_deductible = IF(@tax_deductible = 'Yes', TRUE, FALSE),
	campus = NULLIF(@campus, ''),
	active = IF(@active = 'Active', TRUE, FALSE)
;
	