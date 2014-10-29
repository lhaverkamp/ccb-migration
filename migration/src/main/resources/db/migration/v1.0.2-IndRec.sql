LOAD DATA INFILE 'C:/Users/lhaverkamp/git/ccb-migration/migration/src/main/resources/db/data/IndRec.csv'
	INTO TABLE IndRec
	FIELDS
		TERMINATED BY ','
		OPTIONALLY ENCLOSED BY '"'
	LINES
		TERMINATED BY '\n'
	IGNORE 1 LINES
	(
		IndivNo,
		@TerminationDate,
		@ReasonForTermination,
		@Visitor,
		@UseIndName,
		LastName,
		Suffix,
		Title,
		@UsePrefName,
		@LastUpdate,
		Status,
		FamilyRel,
		@IncludeOnDir,
		@Birthdate,
		SchoolGrade,
		WorkPhone,
		@AltAddrFromDate,
		@AltAddrToDate,
		AltAddrAddress1,
		AltAddrAddress2,
		AltAddrCityState,
		AltAddrZip,
		AltAddrCarrierRoute,
		AltAddrPhone,
		OldGroups,
		OldSkills,
		Comments,
		@EnvNo,
		FirstName,
		IndNo,
		MiddleName,
		FamNo,
		PreferredName,
		Definable1,
		Definable31,
		Definable2,
		Definable32,
		Definable3,
		Definable33,
		Definable34,
		Definable4,
		Definable35,
		Definable5,
		Definable36,
		Definable6,
		Definable37,
		@Definable7,
		Definable38,
		@Definable8,
		Definable39,
		Definable9,
		@Definable10,
		Definable40,
		Definable11,
		Definable41,
		@Definable12,
		Definable42,
		Definable13,
		Definable43,
		Definable14,
		Definable44,
		Definable15,
		Definable45,
		Definable16,
		Definable46,
		Definable17,
		Definable47,
		Definable18,
		Definable48,
		Definable19,
		Definable49,
		Definable20,
		Definable50,
		Definable21,
		Definable51,
		Definable22,
		Definable52,
		Definable23,
		Definable53,
		Definable24,
		Definable54,
		Definable25,
		Definable55,
		Definable26,
		Definable56,
		Definable27,
		Definable57,
		Definable28,
		Definable58,
		Definable29,
		Definable59,
		Definable30,
		Definable60,
		@ContJointly,
		Definable61,
		Definable62,
		Definable63,
		Definable64,
		Definable65,
		Definable66,
		Definable67,
		Definable68,
		Definable69,
		Definable70,
		Definable71,
		Definable72,
		Definable73,
		Definable74,
		Definable75,
		Definable76,
		Definable77,
		Definable78,
		Definable79,
		Definable80,
		Definable81,
		Definable82,
		Definable83,
		Definable84,
		Definable85,
		Definable86,
		Definable87,
		Definable88,
		Definable89,
		IncludeOnDirYN,
		@Sched_Include,
		UniqueID
	)
	SET TerminationDate = CASE WHEN INSTR(@TerminationDate, '?') > 0 THEN NULL ELSE STR_TO_DATE(NULLIF(@TerminationDate, ''), '%m%d%Y') END,
		ReasonForTermination = NULLIF(@ReasonForTermination, ''),
		Visitor = CASE WHEN @Visitor = 'TRUE' THEN TRUE ELSE FALSE END,
		UseIndName = CASE WHEN @UseIndName = 'TRUE' THEN TRUE ELSE FALSE END,
		UsePrefName = CASE WHEN @UsePrefName = 'TRUE' THEN TRUE ELSE FALSE END,
		LastUpdate = STR_TO_DATE(@LastUpdate, '%m%d%Y'),
		IncludeOnDir = NULLIF(@IncludeOnDir, ''),
		Birthdate = CASE WHEN INSTR(@Birthdate, '?') > 0 THEN NULL ELSE STR_TO_DATE(NULLIF(@Birthdate, ''), '%m%d%Y') END,
		AltAddrFromDate = STR_TO_DATE(NULLIF(@AltAddrFromDate, ''), '%m%Y'),
		AltAddrToDate = STR_TO_DATE(NULLIF(@AltAddrToDate, ''), '%m%Y'),
		EnvNo = NULLIF(@EnvNo, ''),
		Definable7 = CASE WHEN INSTR(@Definable7, '?') > 0 OR @Definable7 = '09992094' THEN NULL ELSE STR_TO_DATE(NULLIF(@Definable7, ''), '%m%d%Y') END,
		Definable8 = CASE WHEN INSTR(@Definable8, '?') > 0 THEN NULL ELSE STR_TO_DATE(NULLIF(@Definable8, ''), '%m%d%Y') END,
		Definable10 = CASE WHEN INSTR(@Definable10, '?') > 0 THEN NULL ELSE STR_TO_DATE(NULLIF(@Definable10, ''), '%m%d%Y') END,
		Definable12 = CASE WHEN INSTR(@Definable12, '?') > 0 THEN NULL ELSE STR_TO_DATE(NULLIF(@Definable12, ''), '%m%d%Y') END,
		ContJointly = CASE WHEN @ContJointly = 'TRUE' THEN TRUE ELSE FALSE END,
		Sched_Include = CASE WHEN @Sched_Include = 'TRUE' THEN TRUE ELSE FALSE END