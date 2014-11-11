LOAD DATA INFILE '../csv/IndRec.csv'
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
		@Suffix,
		@Title,
		@UsePrefName,
		@LastUpdate,
		Status,
		@FamilyRel,
		@IncludeOnDir,
		@Birthdate,
		@SchoolGrade,
		@WorkPhone,
		@AltAddrFromDate,
		@AltAddrToDate,
		@AltAddrAddress1,
		@AltAddrAddress2,
		@AltAddrCityState,
		@AltAddrZip,
		@AltAddrCarrierRoute,
		@AltAddrPhone,
		@OldGroups,
		@OldSkills,
		@Comments,
		@EnvNo,
		FirstName,
		IndNo,
		@MiddleName,
		FamNo,
		@PreferredName,
		@Definable1,
		@Definable31,
		@Definable2,
		@Definable32,
		@Definable3,
		@Definable33,
		@Definable34,
		@Definable4,
		@Definable35,
		@Definable5,
		@Definable36,
		@Definable6,
		@Definable37,
		@Definable7,
		@Definable38,
		@Definable8,
		@Definable39,
		@Definable9,
		@Definable10,
		@Definable40,
		@Definable11,
		@Definable41,
		@Definable12,
		@Definable42,
		@Definable13,
		@Definable43,
		@Definable14,
		@Definable44,
		@Definable15,
		@Definable45,
		@Definable16,
		@Definable46,
		@Definable17,
		@Definable47,
		@Definable18,
		@Definable48,
		@Definable19,
		@Definable49,
		@Definable20,
		@Definable50,
		@Definable21,
		@Definable51,
		@Definable22,
		@Definable52,
		@Definable23,
		@Definable53,
		@Definable24,
		@Definable54,
		@Definable25,
		@Definable55,
		@Definable26,
		@Definable56,
		@Definable27,
		@Definable57,
		@Definable28,
		@Definable58,
		@Definable29,
		@Definable59,
		@Definable30,
		@Definable60,
		@ContJointly,
		@Definable61,
		@Definable62,
		@Definable63,
		@Definable64,
		@Definable65,
		@Definable66,
		@Definable67,
		@Definable68,
		@Definable69,
		@Definable70,
		@Definable71,
		@Definable72,
		@Definable73,
		@Definable74,
		@Definable75,
		@Definable76,
		@Definable77,
		@Definable78,
		@Definable79,
		@Definable80,
		@Definable81,
		@Definable82,
		@Definable83,
		@Definable84,
		@Definable85,
		@Definable86,
		@Definable87,
		@Definable88,
		@Definable89,
		IncludeOnDirYN,
		@Sched_Include,
		UniqueID
	)
	SET TerminationDate = CASE WHEN INSTR(@TerminationDate, '?') > 0 THEN NULL ELSE STR_TO_DATE(NULLIF(@TerminationDate, ''), '%m%d%Y') END,
		ReasonForTermination = NULLIF(@ReasonForTermination, ''),
		Visitor = IF(@Visitor = 'TRUE', TRUE, FALSE),
		UseIndName = IF(@UseIndName = 'TRUE', TRUE, FALSE),
		Suffix = NULLIF(@Suffix, ''),
		Title = NULLIF(@Title, ''),
		UsePrefName = IF(@UsePrefName = 'TRUE', TRUE, FALSE),
		LastUpdate = STR_TO_DATE(@LastUpdate, '%m%d%Y'),
		FamilyRel = NULLIF(@FamilyRel, ''),
		IncludeOnDir = NULLIF(@IncludeOnDir, ''),
		Birthdate = CASE WHEN INSTR(@Birthdate, '?') > 0 THEN NULL ELSE STR_TO_DATE(NULLIF(@Birthdate, ''), '%m%d%Y') END,
		SchoolGrade = NULLIF(@SchoolGrade, ''),
		WorkPhone = NULLIF(@WorkPhone, ''),
		AltAddrFromDate = STR_TO_DATE(NULLIF(@AltAddrFromDate, ''), '%m%Y'),
		AltAddrToDate = STR_TO_DATE(NULLIF(@AltAddrToDate, ''), '%m%Y'),
		AltAddrAddress1 = NULLIF(@AltAddrAddress1, ''),
		AltAddrAddress2 = NULLIF(@AltAddrAddress2, ''),
		AltAddrCityState = NULLIF(@AltAddrCityState, ''),
		AltAddrZip = NULLIF(@AltAddrZip, ''),
		AltAddrCarrierRoute = NULLIF(@AltAddrCarrierRoute, ''),
		AltAddrPhone = NULLIF(@AltAddrPhone, ''),
		OldGroups = NULLIF(@OldGroups, ''),
		OldSkills = NULLIF(@OldSkills, ''),
		Comments = NULLIF(@Comments, ''),
		EnvNo = NULLIF(@EnvNo, ''),
		MiddleName = NULLIF(@MiddleName, ''),
		PreferredName = NULLIF(@PreferredName, ''),
		Definable1 = NULLIF(@Definable1, ''),
		Definable31 = NULLIF(@Definable31, ''),
		Definable2 = NULLIF(@Definable2, ''),
		Definable32 = NULLIF(@Definable32, ''),
		Definable3 = NULLIF(@Definable3, ''),
		Definable33 = NULLIF(@Definable33, ''),
		Definable34 = NULLIF(@Definable34, ''),
		Definable4 = NULLIF(@Definable4, ''),
		Definable35 = NULLIF(@Definable35, ''),
		Definable5 = NULLIF(@Definable5, ''),
		Definable35 = NULLIF(@Definable36, ''),
		Definable6 = NULLIF(@Definable6, ''),
		Definable37 = NULLIF(@Definable37, ''),
		Definable7 = CASE WHEN INSTR(@Definable7, '?') > 0 OR @Definable7 = '09992094' THEN NULL ELSE STR_TO_DATE(NULLIF(@Definable7, ''), '%m%d%Y') END,
		Definable38 = NULLIF(@Definable38, ''),
		Definable8 = CASE WHEN INSTR(@Definable8, '?') > 0 THEN NULL ELSE STR_TO_DATE(NULLIF(@Definable8, ''), '%m%d%Y') END,
		Definable39 = NULLIF(@Definable39, ''),
		Definable9 = NULLIF(@Definable9, ''),
		Definable10 = CASE WHEN INSTR(@Definable10, '?') > 0 THEN NULL ELSE STR_TO_DATE(NULLIF(@Definable10, ''), '%m%d%Y') END,
		Definable40 = NULLIF(@Definable40, ''),
		Definable11 = NULLIF(@Definable11, ''),
		Definable41 = NULLIF(@Definable41, ''),
		Definable12 = CASE WHEN INSTR(@Definable12, '?') > 0 THEN NULL ELSE STR_TO_DATE(NULLIF(@Definable12, ''), '%m%d%Y') END,
		Definable42 = NULLIF(@Definable42, ''),
		Definable13 = NULLIF(@Definable13, ''),
		Definable43 = NULLIF(@Definable43, ''),
		Definable14 = NULLIF(@Definable14, ''),
		Definable44 = NULLIF(@Definable44, ''),
		Definable15 = NULLIF(@Definable15, ''),
		Definable45 = NULLIF(@Definable45, ''),
		Definable16 = NULLIF(@Definable16, ''),
		Definable46 = NULLIF(@Definable46, ''),
		Definable17 = NULLIF(@Definable17, ''),
		Definable47 = NULLIF(@Definable47, ''),
		Definable18 = NULLIF(@Definable18, ''),
		Definable48 = NULLIF(@Definable48, ''),
		Definable19 = NULLIF(@Definable19, ''),
		Definable49 = NULLIF(@Definable49, ''),
		Definable20 = NULLIF(@Definable20, ''),
		Definable50 = NULLIF(@Definable50, ''),
		Definable21 = NULLIF(@Definable21, ''),
		Definable51 = NULLIF(@Definable51, ''),
		Definable22 = NULLIF(@Definable22, ''),
		Definable52 = NULLIF(@Definable52, ''),
		Definable23 = NULLIF(@Definable23, ''),
		Definable53 = NULLIF(@Definable53, ''),
		Definable24 = NULLIF(@Definable24, ''),
		Definable54 = NULLIF(@Definable54, ''),
		Definable25 = NULLIF(@Definable25, ''),
		Definable55 = NULLIF(@Definable55, ''),
		Definable26 = NULLIF(@Definable26, ''),
		Definable56 = NULLIF(@Definable56, ''),
		Definable27 = NULLIF(@Definable27, ''),
		Definable57 = NULLIF(@Definable57, ''),
		Definable28 = NULLIF(@Definable28, ''),
		Definable58 = NULLIF(@Definable58, ''),
		Definable29 = NULLIF(@Definable29, ''),
		Definable59 = NULLIF(@Definable59, ''),
		Definable30 = NULLIF(@Definable30, ''),
		Definable60 = NULLIF(@Definable60, ''),
		ContJointly = IF(@ContJointly = 'TRUE', TRUE, FALSE),
		Definable61 = NULLIF(@Definable61, ''),
		Definable62 = NULLIF(@Definable62, ''),
		Definable63 = NULLIF(@Definable63, ''),
		Definable64 = NULLIF(@Definable64, ''),
		Definable65 = NULLIF(@Definable65, ''),
		Definable66 = NULLIF(@Definable66, ''),
		Definable67 = NULLIF(@Definable67, ''),
		Definable68 = NULLIF(@Definable68, ''),
		Definable69 = NULLIF(@Definable69, ''),
		Definable70 = NULLIF(@Definable70, ''),
		Definable71 = NULLIF(@Definable71, ''),
		Definable72 = NULLIF(@Definable72, ''),
		Definable73 = NULLIF(@Definable73, ''),
		Definable74 = NULLIF(@Definable74, ''),
		Definable75 = NULLIF(@Definable75, ''),
		Definable76 = NULLIF(@Definable76, ''),
		Definable77 = NULLIF(@Definable77, ''),
		Definable78 = NULLIF(@Definable78, ''),
		Definable79 = NULLIF(@Definable79, ''),
		Definable80 = NULLIF(@Definable80, ''),
		Definable81 = NULLIF(@Definable81, ''),
		Definable82 = NULLIF(@Definable82, ''),
		Definable83 = NULLIF(@Definable83, ''),
		Definable84 = NULLIF(@Definable84, ''),
		Definable85 = NULLIF(@Definable85, ''),
		Definable86 = NULLIF(@Definable86, ''),
		Definable87 = NULLIF(@Definable87, ''),
		Definable88 = NULLIF(@Definable88, ''),
		Definable89 = NULLIF(@Definable89, ''),
		Sched_Include = IF(@Sched_Include = 'TRUE', TRUE, FALSE)
;