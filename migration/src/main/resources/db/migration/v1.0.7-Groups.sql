LOAD DATA INFILE '../csv/Groups.csv'
	INTO TABLE Groups
	FIELDS
		TERMINATED BY ','
		OPTIONALLY ENCLOSED BY '"'
	LINES
		TERMINATED BY '\r\n'
	IGNORE 1 LINES
	(
		IndNo,
		Code,
		@Comments,
		RecNo,
		@DateFrom,
		@DateTo,
		@TransferDate,
		@TransferReason,
		@Role,
		@AdvFlag
	)
	SET Comments = NULLIF(@Comments, ''),
		DateFrom = STR_TO_DATE(@DateFrom, '%m/%d/%y %h:%i %p'),
		DateTo = STR_TO_DATE(@DateTo, '%m/%d/%y %h:%i %p'),
		TransferDate = STR_TO_DATE(@TransferDate, '%m/%d/%y %h:%i %p'),
		TransferReason = NULLIF(@TransferReason, ''),
		Role = NULLIF(@Role, ''),
		AdvFlag = IF(@AdvFlag = 'TRUE', TRUE, FALSE)