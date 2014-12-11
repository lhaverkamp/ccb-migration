SELECT first_name, last_name, COUNT(*)
FROM custom_report
GROUP BY first_name, last_name
HAVING COUNT(*) > 1;