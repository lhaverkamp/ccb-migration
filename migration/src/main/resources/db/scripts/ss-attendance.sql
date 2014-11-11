SELECT 
	eventid AS [Event ID],
	FORMAT(date, 'yyyy-mm-dd hh:nn:ss') AS [Occurance],
	FALSE AS [Did Not Meet?],
	NULL AS [Head Count],
	personid AS [Attendee ID],
	NULL AS [Topic],
	NULL AS [Notes],
	NULL AS [Prayer Requests],
	NULL AS [Info],
	FALSE AS [Email Notifications]
FROM attendancedetail;
 