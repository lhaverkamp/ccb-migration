LOAD DATA INFILE '../csv/FamRec.csv'
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
		@Address1,
		@Address2,
		@CityState,
		@Zip,
		@CarrierRoute,
		@HomePhone,
		@UnlistedHomePhone,
		@MailingCode,
		@GeographicArea,
		@LastUpdate,
		@AltAddrFromDate,
		@AltAddrToDate,
		@AltAddrAddress1,
		@AltAddrAddress2,
		@AltAddrCityState,
		@AltAddrZip,
		@AltAddrCarrierRoute,
		@Comments,
		@AltAddrPhone,
		@MailingLabel,
		@DelvPoint,
		FamNo,
		@County,
		UniqueID
	)
	SET Visitor = IF(@Visitor = 'TRUE', TRUE, FALSE),
		Address1 = NULLIF(@Address1, ''),
		Address2 = NULLIF(@Address2, ''),
		CityState = NULLIF(@CityState, ''),
		Zip = NULLIF(@Zip, ''),
		CarrierRoute = NULLIF(@CarrierRoute, ''),
		HomePhone = NULLIF(@HomePhone, ''),
		UnlistedHomePhone = IF(@UnlistedHomePhone = 'TRUE', TRUE, FALSE),
		MailingCode = NULLIF(@MailingCode, ''),
		GeographicArea = NULLIF(@GeographicArea, ''),
		LastUpdate = STR_TO_DATE(@LastUpdate, '%m%d%Y'),
		AltAddrFromDate = STR_TO_DATE(NULLIF(@AltAddrFromDate, ''), '%m%d%Y'),
		AltAddrToDate = STR_TO_DATE(NULLIF(@AltAddrToDate, ''), '%m%d%Y'),
		AltAddrAddress1 = NULLIF(@AltAddrAddress1, ''),
		AltAddrAddress2 = NULLIF(@AltAddrAddress2, ''),
		AltAddrCityState = NULLIF(@AltAddrCityState, ''),
		AltAddrZip = NULLIF(@AltAddrZip, ''),
		AltAddrCarrierRoute = NULLIF(@AltAddrCarrierRoute, ''),
		Comments = NULLIF(@Comments, ''),
		AltAddrPhone = NULLIF(@AltAddrPhone, ''),
		MailingLabel = NULLIF(@MailingLabel, ''),
		DelvPoint = NULLIF(@DelvPoint, ''),
		County = NULLIF(@County, '')