-- The GROUP BY is due to the fact we have duplicate attendance entries in
-- the system for some reason or another.
SELECT 
	event.eventid AS [Event ID],
	event.eventname AS [Event],
	attendancedetail.personid AS [Attendee ID],
	attendancedetail.date AS [Occurance],
	MIN(attendancedetail.creationdatetime) AS [Created Date],
	MAX(attendancedetail.revisiondatetime) AS [Updated Date]
FROM attendancedetail
INNER JOIN event ON attendancedetail.eventid = event.eventid AND attendancedetail.type1 = event.type1 AND attendancedetail.type2 = event.type2 AND attendancedetail.type3 = event.type3 AND attendancedetail.type4 = event.type4 AND attendancedetail.type5 = event.type5
GROUP BY 
	event.eventid,
	event.eventname,
	attendancedetail.personid,
	attendancedetail.date;
