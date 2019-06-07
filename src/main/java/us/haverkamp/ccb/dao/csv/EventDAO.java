package us.haverkamp.ccb.dao.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Event;
import us.haverkamp.utils.CSVUtils;
import us.haverkamp.utils.DateUtils;

public class EventDAO extends us.haverkamp.ccb.dao.EventDAO {
	@Override
	public int[] update(List<Event> items) throws DataAccessException {
		final Path path = Paths.get("events.csv");
		
		try {
			final FileWriter writer = new FileWriter(path.toFile());
			
			try {
				final CSVPrinter printer = CSVUtils.getPrinter(writer, CSVFormat.DEFAULT);
				
				try {
					// header
					printer.printRecord(
						"id",
						"name",
						"description",
						"leader_notes",
						"start_datetime",
						"end_datetime",
						"timezone",
						"recurrence_description",
						"approval_status_id",
						"group_id",
						"organizer_id",
						"phone_contact",
						"location_name",
						"location_street",
						"location_city",
						"location_state",
						"location_zip",
						"location_line_1",
						"location_line_2",
						"setup_start",
						"setup_end",
						"setup_notes",
						"event_grouping_id",
						"creator_id",
						"modifier_id",
						"listed",
						"public_calendar_listed",
						"created",
						"modified"
					);
					
					for(Event item : items) {
						printer.printRecord(
							item.getId(), // id
							item.getName(), // name
							item.getDescription(), // description
							item.getLeaderNotes(), // leader_notes 
							DateUtils.toString(item.getStart(), DateUtils.TIMESTAMP), // start_datetime 
							DateUtils.toString(item.getEnd(), DateUtils.TIMESTAMP), // end_datetime
							item.getTimezone(), // timezone
							item.getRecurrenceDescription(), // recurrence_description 
							item.getApprovalStatus().getId(), // approval_status_id 
							item.getGroup().getId(), // group_id
							item.getOrganizer().getId(), // organizer_id
							item.getPhone(), // phone_contact 
							item.getLocation().getName(), // location_name 
							item.getLocation().getStreet(), // location_street 
							item.getLocation().getCity(), // location_city
							item.getLocation().getState(), // location_state 
							item.getLocation().getZip(), // location_zip
							item.getLocation().getLine1(), // location_line_1 
							item.getLocation().getLine2(), // location_line_2 
							item.getSetup(), // setup_start
							item.getCleanup(), // setup_end
							item.getSetupNotes(), // setup_notes 
							item.getEventGrouping().getId(), // event_grouping_id 
							item.getCreator().getId(), // creator_id
							item.getModifier().getId(), // modifier_id
							item.getListed(), // listed
							item.getPublicCalendarListed(), // public_calendar_listed 
							DateUtils.toString(item.getCreated(), DateUtils.TIMESTAMP), //created
							DateUtils.toString(item.getModified(), DateUtils.TIMESTAMP) // modified
						);
					}
					
					return new int[items.size()];
				} finally {
					printer.close();
				}
			} finally {
				writer.close();
			}
		} catch(IOException e) {
			throw new DataAccessException(e);
		}
	}
}
