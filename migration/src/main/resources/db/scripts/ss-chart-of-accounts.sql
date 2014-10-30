SELECT 
	TRIM(contributionfund.name) AS [Name],
	NULL AS [Sub Fund],
	'Yes' AS [Tax Deductible],
	'Hope Lutheran Church' AS [Campus],
	IIF(contributionfund.isinactive, 'Inactive', 'Active') AS [Active/Inactive]
FROM contributionfund;
