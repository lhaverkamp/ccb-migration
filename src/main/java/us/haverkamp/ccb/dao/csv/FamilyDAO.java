package us.haverkamp.ccb.dao.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Family;
import us.haverkamp.utils.CSVUtils;
import us.haverkamp.utils.DateUtils;

public class FamilyDAO extends us.haverkamp.ccb.dao.FamilyDAO {

	@Override
	public int[] update(List<Family> items) throws DataAccessException {
		final Path path = Paths.get("families.csv");
		
		try {
			final FileWriter writer = new FileWriter(path.toFile());
			
			try {
				final CSVPrinter printer = CSVUtils.getPrinter(writer, CSVFormat.DEFAULT);
				
				try {
					// header
					printer.printRecord(
						"id",
						"name",
						"modified"
					);
					
					for(Family item : items) {
						printer.printRecord(
							item.getId(), // id
							item.getName(), // name
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
