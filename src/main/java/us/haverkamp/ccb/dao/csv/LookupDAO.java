package us.haverkamp.ccb.dao.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.LookupTable;
import us.haverkamp.ccb.domain.Selection;
import us.haverkamp.utils.CSVUtils;

public class LookupDAO extends us.haverkamp.ccb.dao.LookupDAO {
	@Override
	public int[] update(List<Selection> fields, String table) throws DataAccessException {
		final Path path = Paths.get(table + ".csv");
		
		try {
			final FileWriter writer = new FileWriter(path.toFile());
			
			try {
				final CSVPrinter printer = CSVUtils.getPrinter(writer, CSVFormat.DEFAULT);
				
				try {
					// header
					printer.printRecord(
						"id",
						"name",
						"sort_order"
					);
					
					for(LookupTable field : fields) {
						printer.printRecord(
							field.getId(),
							field.getName(),
							field.getOrder()
						);
					}
					
					return new int[fields.size()];
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
