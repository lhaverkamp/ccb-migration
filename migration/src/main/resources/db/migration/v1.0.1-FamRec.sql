LOAD DATA INFILE 'C:/Users/lhaverkamp/git/ccb-migration/migration/src/main/resources/db/data/FamRec.csv'
	INTO TABLE FamRec
	FIELDS
		TERMINATED BY ','
		OPTIONALLY ENCLOSED BY '"'
	LINES
		TERMINATED BY '\n'
	IGNORE 1 LINES
	(
		FamilyNo,
		@Visitor,
		Address1,
		Address2,
		CityState,
		Zip,
		CarrierRoute,
		HomePhone,
		@UnlistedHomePhone,
		MailingCode,
		GeographicArea,
		@LastUpdate,
		@AltAddrFromDate,
		@AltAddrToDate,
		AltAddrAddress1,
		AltAddrAddress2,
		AltAddrCityState,
		AltAddrZip,
		AltAddrCarrierRoute,
		Comments,
		AltAddrPhone,
		MailingLabel,
		DelvPoint,
		FamNo,
		County,
		UniqueID
	)
	SET Visitor = IF(@Visitor = 'TRUE', TRUE, FALSE),
		UnlistedHomePhone = IF(@UnlistedHomePhone = 'TRUE', TRUE, FALSE),
		LastUpdate = STR_TO_DATE(@LastUpdate, '%m%d%Y'),
		AltAddrFromDate = STR_TO_DATE(NULLIF(@AltAddrFromDate, ''), '%m%d%Y'),
		AltAddrToDate = STR_TO_DATE(NULLIF(@AltAddrToDate, ''), '%m%d%Y')