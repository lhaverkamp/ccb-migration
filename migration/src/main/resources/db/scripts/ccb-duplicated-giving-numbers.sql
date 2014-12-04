SELECT individual_id, family_id, first_name, last_name, individual_export.giving_number
FROM individual_export 
INNER JOIN (
	SELECT giving_number, COUNT(DISTINCT family_id) AS cnt
	FROM individual_export
	GROUP BY giving_number
	HAVING COUNT(DISTINCT family_id) > 1
) t ON individual_export.giving_number = t.giving_number
ORDER BY individual_export.giving_number;
