LOAD DATA INFILE 'C:/Users/lhaverkamp/git/ccb-migration/migration/src/main/resources/db/data/addressphonexref.csv'
	INTO TABLE addressphonexref
	FIELDS
		TERMINATED BY ','
		OPTIONALLY ENCLOSED BY '"'
	LINES
		TERMINATED BY '\n'
	IGNORE 1 LINES
	(
		XREFID,
		IDTYPE,
		ID,
		CREATIONDATETIME,
		REVISIONDATETIME
	)
