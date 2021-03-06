LOAD DATA INFILE '../csv/GivAccts.csv'
	INTO TABLE GivAccts
	FIELDS
		TERMINATED BY ','
		OPTIONALLY ENCLOSED BY '"'
	LINES
		TERMINATED BY '\n'
	IGNORE 1 LINES
	(
		ContYr,
		@F_IncExpAcct,
		@F_AssetAcct,
		AcctID,
		AcctName,
		`Order`,
		@Restricted,
		@Month1,
		@Month2,
		@Month3,
		@Month4,
		@Month5,
		@Month6,
		@Month7,
		@Month8,
		@Month9,
		@Month10,
		@Month11,
		@Month12,
		@PrtOnStmt,
		DebitedAccountGuid,
		CreditedAccountGuid
	)
	SET F_IncExpAcct = NULLIF(@F_IncExpAcct, ''),
		F_AssetAcct = NULLIF(@F_AssetAcct, ''),
		Restricted = IF(@Restricted = 'TRUE', TRUE, FALSE),
		Month1 = REPLACE(REPLACE(@Month1, '$', ''), ',', ''),
		Month2 = REPLACE(REPLACE(@Month2, '$', ''), ',', ''),
		Month3 = REPLACE(REPLACE(@Month3, '$', ''), ',', ''),
		Month4 = REPLACE(REPLACE(@Month4, '$', ''), ',', ''),
		Month5 = REPLACE(REPLACE(@Month5, '$', ''), ',', ''),
		Month6 = REPLACE(REPLACE(@Month6, '$', ''), ',', ''),
		Month7 = REPLACE(REPLACE(@Month7, '$', ''), ',', ''),
		Month8 = REPLACE(REPLACE(@Month8, '$', ''), ',', ''),
		Month9 = REPLACE(REPLACE(@Month9, '$', ''), ',', ''),
		Month10 = REPLACE(REPLACE(@Month10, '$', ''), ',', ''),
		Month11 = REPLACE(REPLACE(@Month11, '$', ''), ',', ''),
		Month12 = REPLACE(REPLACE(@Month12, '$', ''), ',', ''),
		PrtOnStmt = IF(@PrtOnStmt = 'TRUE', TRUE, FALSE)
	
	