LOAD DATA INFILE '../csv/ccb-contributions.csv'
INTO TABLE ccb_contribution
FIELDS
	TERMINATED BY ','
	OPTIONALLY ENCLOSED BY '"'
LINES
	TERMINATED BY '\n'
IGNORE 1 LINES
(
	@individual_id,
	contribution_date,
	amount,
	type_of_gift,
	@check_number,
	fund,
	@subfund,
	@campus,
	@transaction_grouping,
	@batch_number,
	@tax_deductible,
	@memo
)
SET 
	individual_id = REPLACE(@individual_id, ' ', ''),
	check_number = NULLIF(@check_number, 11),
	subfund = NULLIF(@subfund, ''),
	campus = NULLIF(@campus, ''),
	transaction_grouping = NULLIF(@transaction_grouping, ''),
	tax_deductible = IF(@tax_deductible = 'Yes', TRUE, FALSE),
	batch_number = 
		CASE @batch_number
			WHEN 'EARLY' THEN NULL
			ELSE NULLIF(@batch_number, '')
		END,
	memo = NULLIF(@memo, '')
;
	