SELECT first_name, last_name, COUNT(*)
FROM ccb_export
GROUP BY first_name, last_name
HAVING COUNT(*) > 1;