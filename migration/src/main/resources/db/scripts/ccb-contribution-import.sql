SELECT
	NULL AS [Account #],
	NULL AS [Routing #],
	NULL AS [Transaction Date],
	NULL AS [Batch Name],
	NULL AS [Transaction Grouping],
	NULL AS [Note],
	NULL AS [Amount],
	NULL AS [Payment Type],
	NULL AS [Check #],
	NULL AS [Check Date],
	NULL AS [Tax Deductible],
	NULL AS [Individual ID],
	NULL AS [Giving No],
	NULL AS [COA Category]
	contribution.person1id AS [Individual ID],
	contributor.envelope AS [Envelope],
	FORMAT(contribution.date, 'yyyy-mm-dd') AS [Date of Contribution],
	contribution.amount AS [Amount],
	'Other' AS [Type of Gift], -- we aren't storing this data in Shepherd's Staff
	contribution.checknumber AS [Check Number],
	contributionfund.name AS [Fund Given To],
	NULL AS [Sub Fund],
	NULL AS [Campus],
	NULL AS [Transaction Grouping],
	contribution.batchid AS [Batch Number],
	'Yes' AS [Tax Deductible], -- need to check if all of our 'funds' are tax deductible
	NULL AS [Memo],
	contribution.creationdatetime,
	contribution.revisiondatetime
FROM contribution
INNER JOIN contributor ON contribution.person1id = contributor.person1id 
INNER JOIN contributionfund ON contribution.fundid = contributionfund.fundid
WHERE contribution.creationdatetime > '2014-11-10 00:00:00.000'
OR contribution.revisiondatetime > '2014-11-10 00:00:00.000'

 SELECT * FROM contribution