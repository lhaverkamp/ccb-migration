LOAD DATA INFILE 'C:/Users/lhaverkamp/git/ccb-migration/migration/src/main/resources/db/data/AttRec.csv'
	INTO TABLE AttRec
	FIELDS
		TERMINATED BY ','
		OPTIONALLY ENCLOSED BY '"'
	LINES
		TERMINATED BY '\n'
	IGNORE 1 LINES
	(
		@AttEvent,
		@AttCommunion,
		@Date,
		IndivNo,
		AttCode,
		AttNotes,
		AttLesson,
		AttFactors,
		@AttExcused,
		@AttAddlInfo1,
		@AttAddlInfo2,
		@AttAddlInfo3,
		@AttAddlInfo4,
		@AttAddlInfo5,
		@AttAddlInfo6
	)
	SET AttEvent = CASE WHEN @AttEvent = 'TRUE' THEN TRUE ELSE FALSE END,
		AttCommunion = CASE WHEN @AttCommunion = 'TRUE' THEN TRUE ELSE FALSE END,
		Date = STR_TO_DATE(@Date, '%m/%d/%y %h:%i %p'),
		AttExcused = CASE WHEN @AttExcused = 'TRUE' THEN TRUE ELSE FALSE END,
		AttAddlInfo1 = CASE WHEN @AttAddlInfo1 = 'TRUE' THEN TRUE ELSE FALSE END,
		AttAddlInfo2 = CASE WHEN @AttAddlInfo2 = 'TRUE' THEN TRUE ELSE FALSE END,
		AttAddlInfo3 = CASE WHEN @AttAddlInfo3 = 'TRUE' THEN TRUE ELSE FALSE END,
		AttAddlInfo4 = CASE WHEN @AttAddlInfo4 = 'TRUE' THEN TRUE ELSE FALSE END,
		AttAddlInfo5 = CASE WHEN @AttAddlInfo5 = 'TRUE' THEN TRUE ELSE FALSE END,
		AttAddlInfo6 = CASE WHEN @AttAddlInfo6 = 'TRUE' THEN TRUE ELSE FALSE END