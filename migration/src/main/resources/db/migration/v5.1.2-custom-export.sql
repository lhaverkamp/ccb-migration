LOAD DATA INFILE '../csv/custom_report.csv'
INTO TABLE custom_report
FIELDS
	TERMINATED BY ','
	OPTIONALLY ENCLOSED BY '"'
LINES
	TERMINATED BY '\n'
IGNORE 1 LINES
(
	last_name,
	first_name,
	individual_id,
	family_id,
	@other_id
)
SET 
	other_id = NULLIF(@other_id, '-')
;
	