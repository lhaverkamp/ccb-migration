LOAD DATA INFILE '../csv/AttRec.csv'
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
		@AttNotes,
		@AttLesson,
		@AttFactors,
		@AttExcused,
		@AttAddlInfo1,
		@AttAddlInfo2,
		@AttAddlInfo3,
		@AttAddlInfo4,
		@AttAddlInfo5,
		@AttAddlInfo6
	)
	SET AttEvent = IF(@AttEvent = 'TRUE', TRUE, FALSE),
		AttCommunion = IF(@AttCommunion = 'TRUE', TRUE, FALSE),
		Date = STR_TO_DATE(@Date, '%m/%d/%y %h:%i %p'),
		AttNotes = NULLIF(@AttNotes, ''),
		AttLesson = NULLIF(@AttLesson, ''),
		AttFactors = NULLIF(@AttFactors, ''),
		AttExcused = IF(@AttExcused = 'TRUE', TRUE, FALSE),
		AttAddlInfo1 = IF(@AttAddlInfo1 = 'TRUE', TRUE, FALSE),
		AttAddlInfo2 = IF(@AttAddlInfo2 = 'TRUE', TRUE, FALSE),
		AttAddlInfo3 = IF(@AttAddlInfo3 = 'TRUE', TRUE, FALSE),
		AttAddlInfo4 = IF(@AttAddlInfo4 = 'TRUE', TRUE, FALSE),
		AttAddlInfo5 = IF(@AttAddlInfo5 = 'TRUE', TRUE, FALSE),
		AttAddlInfo6 = IF(@AttAddlInfo6 = 'TRUE', TRUE, FALSE)