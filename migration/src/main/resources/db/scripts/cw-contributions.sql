SELECT
	Giving.IndNo AS 'Individual ID',
	Giving.Date AS 'Date of Contribution',
	Giving.Amount AS 'Amount',
	'Other' AS 'Type of Gift',
	Giving.CheckNo AS 'Check Number',
	GivAccts.AcctName AS 'Fund Given To',
	NULL AS 'Sub Fund',
	NULL AS 'Campus',
	NULL AS 'Transaction Grouping',
	Giving.BatchCode AS 'Batch Number',
	'Yes' AS 'Tax Deductible', -- need to check if all of our 'funds' are tax deductible
	Giving.Description AS 'Memo'
FROM Giving
INNER JOIN GivAccts ON Giving.AccountNo = GivAccts.AcctID AND GivAccts.ContYr = DATE_FORMAT(Giving.Date, '01%Y')
WHERE EXISTS (
	SELECT 1
	FROM IndRec
	WHERE Giving.IndNo = IndRec.IndivNo
);
