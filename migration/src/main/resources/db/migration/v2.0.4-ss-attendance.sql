LOAD DATA INFILE '../csv/ss-attendance.csv'
INTO TABLE ss_attendance
FIELDS
	TERMINATED BY ','
	OPTIONALLY ENCLOSED BY '"'
LINES
	TERMINATED BY '\r\n'
IGNORE 1 LINES
(
	event_id,
	@event,
	attendee_id,
	occurance,
	created_date,
	updated_date
) SET
	event = CASE @event
		WHEN 'Advent Services' THEN 'Advent'
		WHEN 'Christmas Eve Servic' THEN 'Christmas Eve'
		WHEN 'New Year’s Eve Ser' THEN 'New Year''s Eve'
		WHEN 'Lenten Services' THEN 'Lent'
		WHEN 'Maundy Thursday Ser' THEN 'Maundy Thursday'
		WHEN 'Good Friday Ser' THEN 'Good Friday'
		WHEN 'Thanksgiving Eve Ser' THEN 'Thanksgiving Eve'
		ELSE @event
	END
;
	