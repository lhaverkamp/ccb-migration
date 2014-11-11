-- subset of tables used from Church Windows MS Access DB
CREATE TABLE IF NOT EXISTS FamRec(
	FamilyNo VARCHAR(10) NOT NULL,
	Visitor BOOLEAN NOT NULL DEFAULT FALSE,
	Address1 VARCHAR(50),
	Address2 VARCHAR(50),
	CityState VARCHAR(50),
	Zip VARCHAR(50),
	CarrierRoute VARCHAR(50),
	HomePhone VARCHAR(10),
	UnlistedHomePhone BOOLEAN,
	MailingCode VARCHAR(50),
	GeographicArea VARCHAR(50),
	LastUpdate TIMESTAMP NOT NULL,
	AltAddrFromDate DATE,
	AltAddrToDate DATE,
	AltAddrAddress1 VARCHAR(50),
	AltAddrAddress2 VARCHAR(50),
	AltAddrCityState VARCHAR(50),
	AltAddrZip VARCHAR(50),
	AltAddrCarrierRoute VARCHAR(50),
	AltAddrPhone VARCHAR(10),
	Comments VARCHAR(1000),
	MailingLabel VARCHAR(50),
	DelvPoint VARCHAR(50),
	FamNo INT NOT NULL,
	County VARCHAR(50),
	UniqueID VARCHAR(50) NOT NULL,
	CONSTRAINT pk_FamRec PRIMARY KEY (FamilyNo),
	CONSTRAINT unk_FamRec_FamNo UNIQUE (FamNo),
	CONSTRAINT unk_FamRec_UniqueID UNIQUE (UniqueID)
);

CREATE TABLE IF NOT EXISTS IndRec(
	IndivNo VARCHAR(10) NOT NULL,
	TerminationDate DATE,
	ReasonForTermination VARCHAR(1),
	Visitor BOOLEAN NOT NULL DEFAULT FALSE,
	UseIndName BOOLEAN NOT NULL DEFAULT FALSE,
	LastName VARCHAR(50)NOT NULL,
	Suffix VARCHAR(50),
	Title VARCHAR(50),
	UsePrefName BOOLEAN NOT NULL DEFAULT FALSE,
	LastUpdate TIMESTAMP NOT NULL,
	Status VARCHAR(1) NOT NULL,
	FamilyRel VARCHAR(2),
	IncludeOnDir SMALLINT(1),
	Birthdate DATE,
	SchoolGrade VARCHAR(10),
	WorkPhone VARCHAR(10),
	AltAddrFromDate DATE,
	AltAddrToDate DATE,
	AltAddrAddress1 VARCHAR(50),
	AltAddrAddress2 VARCHAR(50),
	AltAddrCityState VARCHAR(50),
	AltAddrZip VARCHAR(50),
	AltAddrCarrierRoute VARCHAR(50),
	AltAddrPhone VARCHAR(50),
	OldGroups VARCHAR(1000),
	OldSkills VARCHAR(50),
	Comments VARCHAR(10000),
	EnvNo INT(10),
	FirstName VARCHAR(50) NOT NULL,
	IndNo INT(10) NOT NULL,
	MiddleName VARCHAR(50),
	FamNo INT(10) NOT NULL,
	PreferredName VARCHAR(50),
	Definable1 VARCHAR(50) COMMENT 'Gender', -- Gender
	Definable2 VARCHAR(50) COMMENT 'Marital Status', -- Marital Status
	Definable3 VARCHAR(50) COMMENT 'Maiden Name', -- Maiden Name
	Definable4 VARCHAR(50),
	Definable5 VARCHAR(50) COMMENT 'Occupation', -- Occuptation
	Definable6 VARCHAR(50) COMMENT 'Employer', -- Employer
	Definable7 DATE COMMENT 'Baptism Date', -- Baptism Date
	Definable8 DATE COMMENT 'Membership Date', -- Membership Date
	Definable9 VARCHAR(50) COMMENT 'Received By', -- Received By
	Definable10 DATE COMMENT 'Anniversary', -- Anniversary
	Definable11 VARCHAR(50) COMMENT 'Spouse Termination Date', -- Spouse Termination Date
	Definable12 DATE COMMENT 'Confirmation Date', -- Confirmation Date
	Definable13 VARCHAR(50) COMMENT 'Black Book #', -- Black Book #
	Definable14 VARCHAR(50) COMMENT 'LCMS Report Status', -- LCMS Report Status
	Definable15 VARCHAR(50) COMMENT 'Baptism Type', -- Baptism Type
	Definable16 VARCHAR(50) COMMENT 'Bulk Mail', -- Bulk Mail
	Definable17 VARCHAR(50) COMMENT 'Work Email', -- Work Email
	Definable18 VARCHAR(50) COMMENT 'Home Email', -- Home Email
	Definable19 VARCHAR(50) COMMENT 'Mobile Phone Number', -- Cell Phone Number
	Definable20 VARCHAR(50),
	Definable21 VARCHAR(50) COMMENT 'Confirmation Verse', -- Confirmation Verse
	Definable22 VARCHAR(50) COMMENT 'Baptized', -- Baptized
	Definable23 VARCHAR(50) COMMENT 'Special Mail', -- Special Mail
	Definable24 VARCHAR(50) COMMENT 'Confirmed', -- Confirmed
	Definable25 VARCHAR(50),
	Definable26 VARCHAR(50),
	Definable27 VARCHAR(50),
	Definable28 VARCHAR(50),
	Definable29 VARCHAR(50),
	Definable30 VARCHAR(50),
	Definable31 VARCHAR(50),
	Definable32 VARCHAR(50),
	Definable33 VARCHAR(50),
	Definable34 VARCHAR(50),
	Definable35 VARCHAR(50),
	Definable36 VARCHAR(50),
	Definable37 VARCHAR(50),
	Definable38 VARCHAR(50),
	Definable39 VARCHAR(50),
	Definable40 VARCHAR(50),
	Definable41 VARCHAR(50),
	Definable42 VARCHAR(50),
	Definable43 VARCHAR(50),
	Definable44 VARCHAR(50),
	Definable45 VARCHAR(50),
	Definable46 VARCHAR(50),
	Definable47 VARCHAR(50),
	Definable48 VARCHAR(50),
	Definable49 VARCHAR(50),
	Definable50 VARCHAR(50),
	Definable51 VARCHAR(50),
	Definable52 VARCHAR(50),
	Definable53 VARCHAR(50),
	Definable54 VARCHAR(50),
	Definable55 VARCHAR(50),
	Definable56 VARCHAR(50),
	Definable57 VARCHAR(50),
	Definable58 VARCHAR(50),
	Definable59 VARCHAR(50),
	Definable60 VARCHAR(50),
	Definable61 VARCHAR(50),
	Definable62 VARCHAR(50),
	Definable63 VARCHAR(50),
	Definable64 VARCHAR(50),
	Definable65 VARCHAR(50),
	Definable66 VARCHAR(50),
	Definable67 VARCHAR(50),
	Definable68 VARCHAR(50),
	Definable69 VARCHAR(50),
	Definable70 VARCHAR(50),
	Definable71 VARCHAR(50),
	Definable72 VARCHAR(50),
	Definable73 VARCHAR(50),
	Definable74 VARCHAR(50),
	Definable75 VARCHAR(50),
	Definable76 VARCHAR(50),
	Definable77 VARCHAR(50),
	Definable78 VARCHAR(50),
	Definable79 VARCHAR(50),
	Definable80 VARCHAR(50),
	Definable81 VARCHAR(50),
	Definable82 VARCHAR(50),
	Definable83 VARCHAR(50),
	Definable84 VARCHAR(50),
	Definable85 VARCHAR(50),
	Definable86 VARCHAR(50),
	Definable87 VARCHAR(50),
	Definable88 VARCHAR(50),
	Definable89 VARCHAR(50),
	ContJointly BOOLEAN NOT NULL DEFAULT FALSE,
	IncludeOnDirYN VARCHAR(1) NOT NULL DEFAULT 'Y',
	Sched_Include BOOLEAN NOT NULL DEFAULT FALSE,
	UniqueID VARCHAR(50) NOT NULL,
	CONSTRAINT pk_IndRec PRIMARY KEY (IndivNo),
	CONSTRAINT unk_IndRec_IndNo UNIQUE (IndNo),
	CONSTRAINT unk_IndRec_UniqueID UNIQUE (UniqueID)/*,
	CONSTRAINT fk_IndRec_FamNo FOREIGN KEY (FamNo) REFERENCES FamRec(FamNo)*/
);
	

CREATE TABLE IF NOT EXISTS AttRec(
	AttEvent BOOLEAN NOT NULL DEFAULT FALSE,
	AttCommunion BOOLEAN NOT NULL DEFAULT FALSE,
	Date DATE NOT NULL,
	IndivNo VARCHAR(10) NOT NULL,
	AttCode VARCHAR(10) NOT NULL,
	AttNotes VARCHAR(255),
	AttLesson VARCHAR(255),
	AttFactors VARCHAR(255),
	AttExcused BOOLEAN NOT NULL DEFAULT FALSE,
	AttAddlInfo1 BOOLEAN NOT NULL DEFAULT FALSE,
	AttAddlInfo2 BOOLEAN NOT NULL DEFAULT FALSE,
	AttAddlInfo3 BOOLEAN NOT NULL DEFAULT FALSE,
	AttAddlInfo4 BOOLEAN NOT NULL DEFAULT FALSE,
	AttAddlInfo5 BOOLEAN NOT NULL DEFAULT FALSE,
	AttAddlInfo6 BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT pk_AttRec PRIMARY KEY (Date, IndivNo, AttCode)/*,
	CONSTRAINT fk_AttRec_IndivNo FOREIGN KEY (IndivNo) REFERENCES IndRec(IndivNo)*/
);

CREATE TABLE IF NOT EXISTS EnvLookup(
	EnvNo INT NOT NULL,
	ContYr VARCHAR(6) NOT NULL,
	IndNo VARCHAR(10) NOT NULL,
	CONSTRAINT pk_EnvLookup PRIMARY KEY (EnvNo, ContYr, IndNo)/*,
	CONSTRAINT fk_EnvLookup_IndNo FOREIGN KEY (IndNo) REFERENCES IndRec(IndivNo)*/
);

CREATE TABLE IF NOT EXISTS GivAccts(
	ContYr VARCHAR(6) NOT NULL,
	F_IncExpAcct VARCHAR(50),
	F_AssetAcct VARCHAR(50),
	AcctID VARCHAR(10) NOT NULL,
	AcctName VARCHAR(50) NOT NULL,
	`Order` INT(5) NOT NULL,
	Restricted BOOLEAN NOT NULL DEFAULT FALSE,
	Month1 DOUBLE(15,4) NOT NULL DEFAULT 0,
	Month2 DOUBLE(15,4) NOT NULL DEFAULT 0,
	Month3 DOUBLE(15,4) NOT NULL DEFAULT 0,
	Month4 DOUBLE(15,4) NOT NULL DEFAULT 0,
	Month5 DOUBLE(15,4) NOT NULL DEFAULT 0,
	Month6 DOUBLE(15,4) NOT NULL DEFAULT 0,
	Month7 DOUBLE(15,4) NOT NULL DEFAULT 0,
	Month8 DOUBLE(15,4) NOT NULL DEFAULT 0,
	Month9 DOUBLE(15,4) NOT NULL DEFAULT 0,
	Month10 DOUBLE(15,4) NOT NULL DEFAULT 0,
	Month11 DOUBLE(15,4) NOT NULL DEFAULT 0,
	Month12 DOUBLE(15,4) NOT NULL DEFAULT 0,
	PrtOnStmt BOOLEAN NOT NULL DEFAULT TRUE,
	DebitedAccountGuid VARCHAR(50),
	CreditedAccountGuid VARCHAR(50),
	CONSTRAINT pk_GivAccts PRIMARY KEY (ContYr, AcctID)
);

CREATE TABLE IF NOT EXISTS Giving(
	Date DATE NOT NULL,
	EnvNo VARCHAR(10) NOT NULL,
	IndNo VARCHAR(10) NOT NULL,
	AccountNo VARCHAR(10) NOT NULL,
	Amount DOUBLE(15,4) NOT NULL,
	Description VARCHAR(100),
	TransID INT(10) NOT NULL,
	CheckNo INT(10),
	BatchCode VARCHAR(10),
	DateTimePosted TIMESTAMP,
	User VARCHAR(10),
	CONSTRAINT pk_Giving PRIMARY KEY (Date, IndNo, AccountNo, TransID)/*,
	CONSTRAINT fk_Giving_IndNo FOREIGN KEY (IndNo) REFERENCES IndRec(IndivNo)*/
);

CREATE TABLE IF NOT EXISTS Groups(
	IndNo INT(10) NOT NULL,
	Code VARCHAR(10) NOT NULL,
	Comments VARCHAR(1000),
	RecNo INT(10) NOT NULL,
	DateFrom TIMESTAMP,
	DateTo TIMESTAMP,
	TransferDate TIMESTAMP,
	TransferReason VARCHAR(50),
	Role VARCHAR(50),
	AdvFlag BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT pk_Groups PRIMARY KEY (IndNo, Code)/*,
	CONSTRAINT fk_Groups_IndNo FOREIGN KEY (IndNo) REFERENCES IndRec(IndivNo)*/
);
