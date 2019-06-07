package us.haverkamp.ccb.dao.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Group;
import us.haverkamp.utils.CSVUtils;
import us.haverkamp.utils.DateUtils;

public class GroupDAO extends us.haverkamp.ccb.dao.GroupDAO {

	@Override
	public int[] update(List<Group> items) throws DataAccessException {
		final Path path = Paths.get("groups.csv");
		
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
						"image",
						"campus_id",
						"main_leader_id",
						"director_id",
						"group_type_id",
						"department_id",
						"creator_id",
						"modifier_id",
						"created",
						"modified"
					);
					
					for(Group item : items) {
						printer.printRecord(
							item.getId(), // id
							item.getName(), // name
							item.getDescription(), // description
							null, // TODO image
							item.getCampus().getId(), // campus_id
							item.getMainLeader().getId(), // main_leader_id
							item.getDirector() != null ? item.getDirector().getId() : null, // director_id
							item.getGroupType().getId(), // group_type_id
							item.getDepartment() != null ? item.getDepartment().getId() : null, // department_id
							item.getCreator().getId(), // creator_id
							item.getModifier().getId(), // modifier_id
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
