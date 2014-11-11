SELECT
	contribution.person1id AS [Individual ID],
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
	NULL AS [Memo]
FROM contribution
INNER JOIN contributionfund ON contribution.fundid = contributionfund.fundid;
--WHERE contribution.person1id = 283;
 