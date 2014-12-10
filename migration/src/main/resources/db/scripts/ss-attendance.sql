SELECT 
	event.eventid AS [Event ID],
	event.eventname AS [Event],
	attendancedetail.personid AS [Attendee ID],
	attendancedetail.date AS [Occurance],
	attendancedetail.creationdatetime AS [Created Date],
	attendancedetail.revisiondatetime AS [Updated Date]
FROM attendancedetail
INNER JOIN event ON attendancedetail.eventid = event.eventid AND attendancedetail.type1 = event.type1 AND attendancedetail.type2 = event.type2 AND attendancedetail.type3 = event.type3 AND attendancedetail.type4 = event.type4 AND attendancedetail.type5 = event.type5;
