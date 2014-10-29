LOAD DATA INFILE 'C:/Users/lhaverkamp/git/ccb-migration/migration/src/main/resources/db/data/Giving.csv'
	INTO TABLE Giving
	FIELDS
		TERMINATED BY ','
		OPTIONALLY ENCLOSED BY '"'
	LINES
		TERMINATED BY '\n'
	IGNORE 1 LINES
	(
		@Date,
		EnvNo,
		IndNo,
		AccountNo,
		@Amount,
		Description,
		TransID,
		@CheckNo,
		BatchCode,
		@DateTimePosted,
		User
	)
	SET Date = STR_TO_DATE(@Date, '%m/%d/%y %h:%i %p'),
		Amount = REPLACE(REPLACE(@Amount, '$', ''), ',', ''),
		CheckNo = NULLIF(@CheckNo, ''),
		DateTimePosted = NULLIF(@DateTimePosted, '')
		