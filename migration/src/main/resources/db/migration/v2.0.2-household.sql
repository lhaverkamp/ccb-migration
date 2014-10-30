LOAD DATA INFILE 'C:/Users/lhaverkamp/git/ccb-migration/migration/src/main/resources/db/data/household.csv'
	INTO TABLE household
	FIELDS
		TERMINATED BY ','
		OPTIONALLY ENCLOSED BY '"'
	LINES
		TERMINATED BY '\n'
	IGNORE 1 LINES
	(
		HOUSEID,
		IDTYPE,
		NAME,
		ADDRESSEE1,
		ADDRESSEE2,
		SALUTATION,
		PERSONASSIGNED,
		HOUSETYPE,
		MINISTRYGROUP,
		USEROPTION1,
		USEROPTION2,
		USEROPTION3,
		USERTEXT1,
		USERTEXT2,
		USERTEXT3,
		@USERDATE1,
		@USERDATE2,
		@USERDATE3,
		@NOTEID,
		@CREATIONDATETIME,
		@REVISIONDATETIME,
		EMAILADDRESS,
		PHOTOFILENAME,
		PHOTODESC,
		@PHOTODATE,
		@HASATTACHMENT,
		@EMAILADDRESSISUNLISTED,
		@NEWSLETTER,
		DEFAULTIMAGESIZE
	)
	SET USERDATE1 = NULLIF(@USERDATE1, ''),
		USERDATE2 = NULLIF(@USERDATE2, ''),
		USERDATE3 = NULLIF(@USERDATE3, ''),
		NOTEID = NULLIF(@NOTEID, 0),
		CREATIONDATETIME = STR_TO_DATE(@CREATIONDATETIME, '%Y-%m-%d %H:%i:%S.%x'),
		REVISIONDATETIME = STR_TO_DATE(@REVISIONDATETIME, '%Y-%m-%d %H:%i:%S.%x'),
		PHOTODATE = NULLIF(@PHOTODATE, ''),
		HASATTACHMENT = IF(@HASATTACHMENT = 'true', TRUE, FALSE),
		EMAILADDRESSISUNLISTED = IF(@EMAILADDRESSISUNLISTED = 'true', TRUE, FALSE),
		NEWSLETTER = IF(@NEWSLETTER = 'true', TRUE, FALSE)