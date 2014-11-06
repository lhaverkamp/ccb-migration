-- 52, 965, 966, 967, 968, 4073, 4074 not resolved via distinct
SELECT DISTINCT
	person.personid AS [Individual Id],
	person.houseid AS [Family Id],
	CASE 
		WHEN person.sequence = 1 THEN 'Primary Contact'
		WHEN person.sequence = 2 THEN 'Spouse'
		WHEN person.sequence = 3 THEN 'Child'
		ELSE 'Other'
	END AS [Family Position],
	TRIM(person.title) AS [Prefix],
	TRIM(CASE WHEN person.nickname IS NOT NULL 
		THEN person.nickname
		ELSE person.firstname 
	END) AS [First Name],
	TRIM(person.middlename) AS [Middle Name],
	TRIM(person.lastname) AS [Last Name],
	TRIM(person.namesuffix) AS [Suffix],
	TRIM(CASE WHEN person.nickname IS NOT NULL
		THEN person.firstname
		ELSE NULL
	END) AS [Legal Name], -- nickname vs firstname could be used here
	TRIM(person.maidenname) AS [Maiden Name],
	NULL AS [Limited Access User],
	TRIM(CASE WHEN person.usertext1 IS NOT NULL
		THEN person.usertext1
		ELSE 'No'
	END) AS [Listed],
	CASE person.memberparticipation 
		WHEN 'Inactive' THEN 'Yes'
		ELSE 'No'
	END AS [Inactive/Remove],
	'Hope Lutheran Church' AS [Campus],
	TRIM(household.emailaddress) AS [Family Email],
	TRIM(person.emailpersonal) AS [Individual Email],
	TRIM(ha.address1) AS [Mailing Street 1],
	TRIM(ha.address2) AS [Mailing Street 2],
	TRIM(ha.city) AS [Mailing City],
	TRIM(ha.state) AS [Mailing State],
	TRIM(ha.zipcode) AS [Mailing Postal Code],
	NULL AS [Mailing Country],
	TRIM(ha.carrierroute) AS [Mailing Carrier Route],
	TRIM(ha.address1) AS [Home Street 1],
	TRIM(ha.address2) AS [Home Street 2],
	TRIM(ha.city) AS [Home City],
	TRIM(ha.state) AS [Home State],
	TRIM(ha.zipcode) AS [Home Postal Code],
	NULL AS [Area of Town],
	TRIM(oa.address1) AS [Other Street 1],
	TRIM(oa.address2) AS [Other Street 2],
	TRIM(oa.city) AS [Other City],
	TRIM(oa.state) AS [Other State],
	TRIM(oa.zipcode) AS [Other Postal Code],
	REPLACE(TRIM(hp.areacode) & TRIM(hp.number), '-', '') AS [Contact Phone],
	REPLACE(TRIM(hp.areacode) & TRIM(hp.number), '-', '') AS [Home Phone],
	REPLACE(TRIM(wp.areacode) & TRIM(wp.number), '-', '') AS [Work Phone],
	REPLACE(TRIM(mp.areacode) & TRIM(mp.number), '-', '') AS [Cell Phone],
	NULL AS [Service Provider],
	REPLACE(TRIM(op.areacode) & TRIM(op.number), '-', '') AS [Other Phone],
	NULL AS [Fax],
	NULL AS [Pager],
	NULL AS [Emergency Phone],
	NULL AS [Emergency Contact Name],
	CASE WHEN person.birthdate IS NOT NULL
		THEN FORMAT(person.birthdate, 'yyyy-mm-dd') 
		ELSE NULL
	END AS [Birthday],
	CASE WHEN person.weddingdate IS NOT NULL
		THEN FORMAT(person.weddingdate, 'yyyy-mm-dd')
		ELSE NULL
	END AS [Anniversary],
	TRIM(person.sex) AS [Gender],
	contributor.envelope AS [Giving #],
	TRIM(person.maritalstatus) AS [Marital Status],
	CASE WHEN person.receiveddate IS NOT NULL
		THEN FORMAT(person.receiveddate, 'yyyy-mm-dd') 
		ELSE NULL
	END AS [Membership Date],
	CASE WHEN person.removeddate IS NOT NULL
		THEN FORMAT(person.removeddate, 'yyyy-mm-dd') 
		ELSE NULL
	END AS [Membership Stop Date],
	TRIM(CASE person.memberparticipation
		WHEN 'Active' THEN person.memberparticipation
		WHEN 'Deceased' THEN person.memberparticipation
		WHEN 'Inactive' THEN person.memberparticipation
		WHEN 'Prospect' THEN person.memberparticipation
		WHEN 'Terminated' THEN person.memberparticipation
		WHEN 'Visitor' THEN person.memberparticipation
		ELSE 'Other'
	END) AS [Membership Type],
	CASE WHEN person.baptismdate IS NOT NULL OR person.isbaptized 
		THEN 'Yes'
		ELSE 'No'
	END AS [Baptized],
	CASE WHEN person.baptismdate IS NOT NULL
		THEN FORMAT(person.baptismdate, 'yyyy-mm-dd') 
		ELSE NULL
	END AS [Baptism Date],
	NULL AS [School],
	TRIM(person.sundayschoolgrade + CHR(person.sundayschoolgradenumber)) AS [School Grade],
	NULL AS [Known Allergies],
	TRIM(hn.note) AS [Household Notes],
	TRIM(pn.note) AS [Individual Notes],
	NULL AS [Approved to Work with Children],
	NULL AS [Approved to Work with Children Stop Date],
	NULL AS [Commitment Date], -- not sure the intended purpose of this field; so leaving it blank
	NULL AS [How They Heard],
	TRIM(CASE 
		WHEN person.receivedby = 'Unknown' AND person.baptismdate = person.receiveddate THEN 'Child Baptism'
		ELSE person.receivedby 
	END) AS [How They Joined],
	person.removedby AS [Reason Left Church],
	person.occupationtitle AS [Job Title],
	NULL AS [Work Email],
	TRIM(wa.address1) AS [Work Street 1],
	TRIM(wa.address2) AS [Work Street 2],
	TRIM(wa.city) AS [Work City],
	TRIM(wa.state) AS [Work State],
	TRIM(wa.zipcode) AS [Work Postal Code],
	NULL AS [Testimony Current],
	NULL AS [Testimony Salvation],
	CASE WHEN death.dateofdeath IS NOT NULL
		THEN FORMAT(death.dateofdeath, 'yyyy-mm-dd') 
		ELSE NULL
	END AS [Deceased],
	NULL AS [Facebook Username],
	NULL AS [Twitter Username],
	NULL AS [Blog Username],
	NULL AS [Website Personal],
	NULL AS [Website Work],
	NULL AS [Military],
	NULL AS [Spiritual Maturity],
	NULL AS [Spiritual Gifts],
	NULL AS [Passions],
	NULL AS [Abilities/Skills],
	NULL AS [Church Services I Attend],
	NULL AS [Personal Style],
	NULL AS [Process Queue 1],
	NULL AS [Process Queue 2],
	CASE WHEN person.confirmationdate IS NOT NULL OR person.isconfirmed 
		THEN 'Yes'
		ELSE 'No'
	END AS [Confirmed],
	CASE WHEN person.confirmationdate IS NOT NULL
		THEN FORMAT(person.confirmationdate, 'yyyy-mm-dd') 
		ELSE NULL
	END AS [Confirmation Date],
	NULL AS [Custom Text 2],
	NULL AS [Custom Text 3],
	NULL AS [Custom Date 2],
	NULL AS [Custom Date 3],
	person.firstcommuniondate AS [First Communion Date],
	NULL AS [Significant Event 2],
	NULL AS [Significant Event 3],

	TRIM(person.usertext2) AS [Confirmation Verse],
	CASE WHEN household.newsletter 
		THEN 'Yes'
		ELSE 'No'
	END AS [Newsletter],
	TRIM(household.personassigned) AS [Elder],

	person.personid AS [Other ID]
FROM person
LEFT JOIN household ON person.houseid = household.houseid
LEFT JOIN contributor ON person.personid = contributor.person1id OR person.personid = contributor.person2id

LEFT JOIN addressphonexref hpa ON person.houseid = hpa.id AND hpa.idtype = 'H'
LEFT JOIN phone hp ON hpa.xrefid = hp.xrefid AND hp.type = 'PRIMARY'
LEFT JOIN address ha ON hpa.xrefid = ha.xrefid

LEFT JOIN addressphonexref wpa ON person.personid = wpa.id AND wpa.idtype = 'P'
LEFT JOIN phone wp ON wpa.xrefid = wp.xrefid AND wp.type = 'WORK'
LEFT JOIN address wa ON wpa.xrefid = wa.xrefid

LEFT JOIN addressphonexref mpa ON person.houseid = mpa.id AND mpa.idtype = 'H'
LEFT JOIN phone mp ON mpa.xrefid = mp.xrefid AND mp.type = 'MOBILE'
LEFT JOIN address ma ON mpa.xrefid = ma.xrefid

LEFT JOIN addressphonexref opa ON person.houseid = opa.id AND opa.idtype = 'H'
LEFT JOIN phone op ON opa.xrefid = op.xrefid AND op.type = 'OTHER'
LEFT JOIN address oa ON opa.xrefid = oa.xrefid

LEFT JOIN death ON person.personid = death.id
LEFT JOIN note pn ON person.noteid = pn.noteid
LEFT JOIN note hn ON household.noteid = hn.noteid;

--WHERE person.houseid = 92;
