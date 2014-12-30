-- Event
SELECT 
	event_id, 
	event, 
	TRIM(event) AS event_grouping, 
	TRIM(occurance) AS event_date, 
	COUNT(*) AS attendance 
FROM ss_attendance 
--WHERE event_id = 1 AND occurance = '2013-08-04' 
GROUP BY event_id, event, occurance
ORDER BY occurance, event, event_id;

-- Attendance
SELECT 
	event_id, 
	event, 
	occurance, 
	attendee_id, 
	custom_report.individual_id, 
	custom_report.family_id, 
	t.first_name,
	t.last_name
FROM ss_attendance 
INNER JOIN ss_individual t ON ss_attendance.attendee_id = t.individual_id 
INNER JOIN custom_report ON t.other_id = custom_report.other_id; 
