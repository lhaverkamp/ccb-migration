SELECT
	NULL AS [Account Number],
	NULL AS [Routing Number],
	FORMAT(contribution.date, 'yyyy-mm-dd') AS [Transaction Date],
	contribution.batchid AS [Batch Name],
	NULL AS [Transaction Grouping],
	contribution.description AS [Note],
	contribution.amount AS [Amount],
	'Other' AS [Payment Type], -- we aren't storing this data in Shepherd's Staff
	contribution.checknumber AS [Check Number],
	NULL AS [Check Date],
	'Yes' AS [Tax Deductible], -- need to check if all of our 'funds' are tax deductible
	NULL AS [Individual ID],
	contributor.envelope AS [Giving Number],
	contributionfund.name AS [COA Category],
	contribution.person1id AS [Other ID],
	contributor.envelopename AS [Envelope Name],
	contribution.creationdatetime,
	contribution.revisiondatetime
FROM contribution
INNER JOIN contributionfund ON contribution.fundid = contributionfund.fundid
INNER JOIN contributor ON contribution.person1id = contributor.person1id AND NVL(contribution.person2id, 0) = NVL(contribution.person2id, 0)
--WHERE creationdatetime > '2014-11-10 00:00:00.0'
WHERE creationdatetime > '2014-12-02 00:00:00.0'
