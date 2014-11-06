LOAD DATA INFILE '../csv/Giving.csv'
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
		@Description,
		TransID,
		@CheckNo,
		@BatchCode,
		@DateTimePosted,
		@User
	)
	SET Date = STR_TO_DATE(@Date, '%m/%d/%y %h:%i %p'),
		Amount = REPLACE(REPLACE(@Amount, '$', ''), ',', ''),
		Description = NULLIF(@Description, ''),
		CheckNo = NULLIF(@CheckNo, ''),
		BatchCode = NULLIF(@BatchCode, ''),
		DateTimePosted = NULLIF(@DateTimePosted, ''),
		User = NULLIF(@User, '')
		