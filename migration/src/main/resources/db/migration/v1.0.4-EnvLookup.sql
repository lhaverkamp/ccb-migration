LOAD DATA INFILE '../csv/EnvLookup.csv'
	INTO TABLE EnvLookup
	FIELDS
		TERMINATED BY ','
		OPTIONALLY ENCLOSED BY '"'
	LINES
		TERMINATED BY '\n'
	IGNORE 1 LINES
	(
		EnvNo,
		ContYr,
		IndNo
	)
	