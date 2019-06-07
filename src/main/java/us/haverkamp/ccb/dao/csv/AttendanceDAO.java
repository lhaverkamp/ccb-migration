package us.haverkamp.ccb.dao.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Individual;
import us.haverkamp.ccb.domain.Occurrence;
import us.haverkamp.utils.CSVUtils;
import us.haverkamp.utils.DateUtils;

public class AttendanceDAO extends us.haverkamp.ccb.dao.AttendanceDAO {

	@Override
	public int[] update(List<Occurrence> items) throws DataAccessException {
		final Path path = Paths.get("attendance.csv");
		
		try {
			final FileWriter writer = new FileWriter(path.toFile());
			
			try {
				final CSVPrinter printer = CSVUtils.getPrinter(writer, CSVFormat.DEFAULT);
				
				try {
					// header
					printer.printRecord(
						"event_id",
						"event",
						"occurrence",
						"did_not_meet",
						"topic",
						"notes",
						"prayer_requestes",
						"info",
						"attendee_id",
						"head_count"
					);
					
					for(Occurrence item : items) {
						for(Individual attendee : item.getAttendees()) {
							printer.printRecord(
								item.getId(), // event_id
								item.getName(), // event
								DateUtils.toString(item.getOccurrence(), DateUtils.TIMESTAMP), // occurrence
								item.getDidNotMeet(), // did_not_meet
								item.getTopic(), // topic
								item.getNotes(), // notes
								item.getPrayerRequests(), // prayer_requests
								item.getInfo(), // info
								attendee.getId(), // attendee_id
								item.getHeadCount() // head_count
							);
						}
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
