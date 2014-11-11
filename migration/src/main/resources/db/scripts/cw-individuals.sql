SELECT 
	IndRec.IndivNo AS 'Individual Id',
	IndRec.FamNo AS 'Family Id',
	CASE 
		WHEN IndRec.FamilyRel = 'H' THEN 'Primary Contact'
		WHEN IndRec.FamilyRel = 'S' THEN 'Spouse'
		WHEN IndRec.FamilyRel = 'C' THEN 'Child'
		ELSE 'Other'
	END AS 'Family Position',
	TRIM(IndRec.Title) AS 'Prefix',
	TRIM(CASE WHEN IndRec.PreferredName IS NOT NULL 
		THEN IndRec.PreferredName
		ELSE IndRec.FirstName 
	END) AS 'First Name',
	TRIM(IndRec.MiddleName) AS 'Middle Name',
	TRIM(IndRec.LastName) AS 'Last Name',
	TRIM(IndRec.Suffix) AS 'Suffix',
	TRIM(CASE WHEN IndRec.PreferredName IS NOT NULL
		THEN IndRec.FirstName
		ELSE NULL
	END) AS 'Legal Name', -- nickname vs firstname could be used here
	TRIM(IndRec.Definable3) AS 'MaidenName',
	NULL AS 'Limited Access User',
	TRIM(CASE IndRec.IncludeOnDirYN 
		WHEN 'Y' THEN 'Yes'
		WHEN 'N' THEN 'No'
		ELSE 'No'
	END) AS 'Listed',
	CASE IndRec.Status 
		WHEN 'I' THEN 'Y'
		ELSE NULL
	END AS 'Inactive/Remove',
	NULL AS 'Campus',
	NULL AS 'Family Email',
	TRIM(IndRec.Definable18) AS 'Individual Email',
	TRIM(FamRec.Address1) AS 'Mailing Street 1',
	TRIM(FamRec.Address2) AS 'Mailing Street 2',
	CASE 
		WHEN TRIM(FamRec.CityState) REGEXP '[A-Z]{2}$' THEN SUBSTR(TRIM(FamRec.CityState), 1, LENGTH(TRIM(FamRec.CityState)) - 2)
		ELSE TRIM(FamRec.CityState)
	END AS 'Mailing City',
	CASE 
		WHEN TRIM(FamRec.CityState) REGEXP '[A-Z]{2}$' THEN SUBSTR(TRIM(FamRec.CityState), -2)
		ELSE NULL
	END AS 'Mailing State',
	TRIM(FamRec.Zip) AS 'Mailing Postal Code',
	NULL AS 'Mailing Country',
	TRIM(FamRec.CarrierRoute) AS 'Mailing Carrier Route',
	TRIM(FamRec.Address1) AS 'Home Street 1',
	TRIM(FamRec.Address2) AS 'Home Street 2',
	CASE 
		WHEN TRIM(FamRec.CityState) REGEXP '[A-Z]{2}$' THEN SUBSTR(TRIM(FamRec.CityState), 1, LENGTH(TRIM(FamRec.CityState)) - 2)
		ELSE TRIM(FamRec.CityState)
	END AS 'Home City',
	CASE 
		WHEN TRIM(FamRec.CityState) REGEXP '[A-Z]{2}$' THEN SUBSTR(TRIM(FamRec.CityState), -2)
		ELSE NULL
	END AS 'Home State',
	TRIM(FamRec.Zip) AS 'Home Postal Code',
	NULL AS 'Area of Town',
	TRIM(FamRec.AltAddrAddress1) AS 'Other Street 1',
	TRIM(FamRec.AltAddrAddress2) AS 'Other Street 2',
	CASE 
		WHEN TRIM(FamRec.AltAddrCityState) REGEXP '[A-Z]{2}$' THEN SUBSTR(TRIM(FamRec.AltAddrCityState), 1, LENGTH(TRIM(FamRec.AltAddrCityState)) - 2)
		ELSE TRIM(FamRec.AltAddrCityState)
	END AS 'Other City',
	CASE 
		WHEN TRIM(FamRec.AltAddrCityState) REGEXP '[A-Z]{2}$' THEN SUBSTR(TRIM(FamRec.AltAddrCityState), -2)
		ELSE NULL
	END AS 'Other State',
	TRIM(FamRec.AltAddrZip) AS 'Other Postal Code',
	REPLACE(TRIM(FamRec.HomePhone), '-', '') AS 'Contact Phone',
	REPLACE(TRIM(FamRec.HomePhone), '-', '') AS 'Home Phone',
	REPLACE(TRIM(IndRec.WorkPhone), '-', '') AS 'Work Phone',
	REPLACE(TRIM(IndRec.Definable19), '-', '') AS 'Cell Phone',
	NULL AS 'Service Provider',
	REPLACE(TRIM(FamRec.AltAddrPhone), '-', '') AS 'Other Phone',
	NULL AS 'Fax',
	NULL AS 'Pager',
	NULL AS 'Emergency Phone',
	NULL AS 'Emergency Contact Name',
	CASE WHEN IndRec.Birthdate IS NOT NULL
		THEN DATE_FORMAT(IndRec.Birthdate, '%Y-%m-%d') 
		ELSE NULL
	END AS 'Birthday',
	CASE WHEN IndRec.Definable10 IS NOT NULL
		THEN DATE_FORMAT(IndRec.Definable10, '%Y-%m-%d')
		ELSE NULL
	END AS 'Anniversary',
	TRIM(IndRec.Definable1) AS 'Gender',
	IndRec.EnvNo AS 'Giving #',
	TRIM(IndRec.Definable2) AS 'Marital Status',
	CASE WHEN IndRec.Definable8 IS NOT NULL
		THEN DATE_FORMAT(IndRec.Definable8, '%Y-%m-%d') 
		ELSE NULL
	END AS 'Membership Date',
	CASE WHEN IndRec.TerminationDate IS NOT NULL
		THEN DATE_FORMAT(IndRec.TerminationDate, '%Y-%m-%d') 
		ELSE NULL
	END AS 'Membership Stop Date',
	TRIM(CASE IndRec.Status
		WHEN 'A' THEN 'Active'
		WHEN 'D' THEN 'Deceased'
		WHEN 'I' THEN 'Inactive'
		WHEN 'P' THEN 'Prospect'
		WHEN 'T' THEN 'Terminated'
		WHEN 'V' THEN 'Visitor'
		ELSE 'Other'
	END) AS 'Membership Type',
	IndRec.Definable22 AS 'Baptized',
	CASE WHEN IndRec.Definable7 IS NOT NULL
		THEN DATE_FORMAT(IndRec.Definable7, '%Y-%m-%d') 
		ELSE NULL
	END AS 'Baptism Date',
	NULL AS 'School',
	TRIM(IndRec.SchoolGrade) AS 'School Grade',
	NULL AS 'Known Allergies',
	NULL AS 'Household Notes',
	IndRec.Comments AS 'Individual Notes',
	NULL AS 'Approved to Work with Children',
	NULL AS 'Approved to Work with Children Stop Date',
	NULL AS 'Commitment Date', -- not sure the intended purpose of this field; so leaving it blank
	NULL AS 'How They Heard',
	TRIM(CASE 
		WHEN IndRec.Definable9 = 'Unknown' AND IndRec.Definable7 = IndRec.Definable8 THEN 'Child Baptism'
		ELSE IndRec.Definable9 
	END) AS 'How They Joined',
	TRIM(IndRec.ReasonForTermination) AS 'Reason Left Church',
	TRIM(IndRec.Definable5) AS 'Job Title',
	TRIM(IndRec.Definable17) AS 'Work Email',
	TRIM(IndRec.AltAddrAddress1) AS 'Work Street 1',
	TRIM(IndRec.AltAddrAddress2) AS 'Work Street 2',
	CASE 
		WHEN TRIM(IndRec.AltAddrCityState) REGEXP '[A-Z]{2}$' THEN SUBSTR(TRIM(IndRec.AltAddrCityState), 1, LENGTH(TRIM(IndRec.AltAddrCityState)) - 2)
		ELSE TRIM(IndRec.AltAddrCityState)
	END AS 'Work City',
	CASE 
		WHEN TRIM(IndRec.AltAddrCityState) REGEXP '[A-Z]{2}$' THEN SUBSTR(TRIM(IndRec.AltAddrCityState), -2)
		ELSE NULL
	END AS 'Work State',
	TRIM(IndRec.AltAddrZip) AS 'Work Postal Code',
	NULL AS 'Testimony Current',
	NULL AS 'Testimony Salvation',
	CASE IndRec.Status
		WHEN 'D' THEN DATE_FORMAT(IndRec.TerminationDate, '%Y-%m-%d') 
		ELSE NULL
	END AS 'Deceased',
	NULL AS 'Facebook Username',
	NULL AS 'Twitter Username',
	NULL AS 'Blog Username',
	NULL AS 'Website Personal',
	NULL AS 'Website Work',
	NULL AS 'Military',
	NULL AS 'Spiritual Maturity',
	NULL AS 'Spiritual Gifts',
	NULL AS 'Passions',
	NULL AS 'Abilities/Skills',
	NULL AS 'Church Services I Attend',
	NULL AS 'Personal Style',
	NULL AS 'Process Queue 1',
	NULL AS 'Process Queue 2',
	IndRec.Definable24 AS 'Confirmed',
	CASE WHEN IndRec.Definable12 IS NOT NULL
		THEN DATE_FORMAT(IndRec.Definable12, '%Y-%m-%d') 
		ELSE NULL
	END AS 'Confirmation Date',
	NULL AS 'Custom Text 2',
	NULL AS 'Custom Text 3',
	NULL AS 'Custom Date 2',
	NULL AS 'Custom Date 3',
	NULL AS 'First Communion Date',
	NULL AS 'Significant Event 2',
	NULL AS 'Significant Event 3',

	TRIM(IndRec.Definable21) AS 'Confirmation Verse',
	NULL AS 'Newsletter',
	NULL AS 'Elder',

	IndRec.UniqueID AS 'Other ID'
FROM IndRec
LEFT JOIN FamRec ON IndRec.FamNo = FamRec.FamNo;

--WHERE IndRec.FamNo = 651;
