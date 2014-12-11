-- Event
SELECT 
	event_id,
	event,
	occurance,
	COUNT(*) AS attendance
FROM ss_attendance
GROUP BY event_id, event, occurance;

-- Attendance
SELECT 
	event_id,
	event,
	occurance,
	attendee_id,
	ss_individual.first_name,
	ss_individual.last_name
FROM ss_attendance
INNER JOIN ss_individual ON ss_attendance.attendee_id = ss_individual.individual_id
INNER JOIN custom_report ON ss_individual.other_id = custom_report.other_id;
