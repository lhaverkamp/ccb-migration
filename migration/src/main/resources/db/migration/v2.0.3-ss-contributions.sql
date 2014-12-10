LOAD DATA INFILE '../csv/ss-contributions.csv'
INTO TABLE ss_contribution
FIELDS
	TERMINATED BY ','
	OPTIONALLY ENCLOSED BY '"'
LINES
	TERMINATED BY '\r\n'
IGNORE 1 LINES
(
	individual_id,
	contribution_date,
	amount,
	type_of_gift,
	@check_number,
	fund,
	@subfund,
	@campus,
	@transaction_grouping,
	batch_number,
	@tax_deductible,
	@memo
)
SET
	check_number = NULLIF(@check_number, 11),
	subfund = NULLIF(@subfund, ''),
	campus = NULLIF(@campus, ''),
	transaction_grouping = NULLIF(@transaction_grouping, ''),
	tax_deductible = IF(@tax_deductible = 'Yes', TRUE, FALSE),
	memo = NULLIF(@memo, '')
;
	