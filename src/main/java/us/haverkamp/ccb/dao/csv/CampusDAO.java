package us.haverkamp.ccb.dao.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Campus;
import us.haverkamp.utils.CSVUtils;

public class CampusDAO extends us.haverkamp.ccb.dao.CampusDAO {
	@Override
	public int[] update(List<Campus> items) throws DataAccessException {
		final Path path = Paths.get("campus.csv");
		
		try {
			final FileWriter writer = new FileWriter(path.toFile());
			
			try {
				final CSVPrinter printer = CSVUtils.getPrinter(writer, CSVFormat.DEFAULT);
				
				try {
					// header
					printer.printRecord(
						"campus_id",
						"campus"
					);
					
					for(Campus item : items) {
						printer.printRecord(
							item.getId(), // campus_id
							item.getName() // campus
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
