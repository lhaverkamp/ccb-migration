package us.haverkamp.ccb.dao.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.domain.Batch;
import us.haverkamp.ccb.domain.Transaction;
import us.haverkamp.utils.CSVUtils;
import us.haverkamp.utils.DateUtils;

public class TransactionDAO extends us.haverkamp.ccb.dao.TransactionDAO {
	@Override
	public int[] update(List<Transaction> items) throws DataAccessException {
		return update(items, false);
	}
	
	public int[] update(List<Transaction> items, boolean append) throws DataAccessException {
		final Path path = Paths.get("transactions.csv");
		
		try {
			final FileWriter writer = new FileWriter(path.toFile(), append);
			
			try {
				final CSVPrinter printer = CSVUtils.getPrinter(writer, CSVFormat.DEFAULT);
				
				try {
					if(!append) {
						// header
						printer.printRecord(
							"batch_id",
							"campus_id",
							"post_date",
							"begin_date",
							"end_date",
							"in_accounting_package",
							"status",
							"source",
							"creator_id",
							"modifier_id",
							"created",
							"modified",
							
							"transaction_id",
							"campus_id",
							"individual_id",
							"date",
							"grouping_id",
							"payment_type",
							"check_number",
							"coa_id",
							"amount",
							"tax_deductible",
							"notes",
							"creator_id",
							"modifier_id",
							"created",
							"modified"
						);
					}
					
					for(Transaction item : items) {
						final Batch batch = item.getBatch();
						
						printer.printRecord(
							batch.getId(), // batch_id
							batch.getCampus().getId(), // campus_id
							DateUtils.toString(batch.getPostDate()), // post_date
							DateUtils.toString(batch.getBeginDate()), // begin_date
							DateUtils.toString(batch.getEndDate()), // end_date
							batch.getInAccountingPackage(), // in_accounting_package,
							batch.getStatus(), // stauts
							batch.getSource(), // source
							batch.getCreator().getId(), // creator_id,
							batch.getModifier().getId(), // modifier_id,
							DateUtils.toString(batch.getCreated(), DateUtils.TIMESTAMP), // created
							DateUtils.toString(batch.getModified(), DateUtils.TIMESTAMP), // modified
							
							item.getId(), // transaction_id,
							item.getCampus().getId(), // campus_id
							item.getIndividual().getId(), // individual_id,
							item.getDate(), // date,
							item.getGrouping().getId(), // grouping_id
							item.getPaymentType(), // payment_type
							item.getCheckNumber(), // check_number
							item.getCOA().getId(), // coa_id
							item.getAmount(), // amount
							item.getTaxDeductible(), // tax_deductible
							item.getNotes(), // notes
							item.getCreator().getId(), // creator_id,
							item.getModifier().getId(), // modifier_id,
							DateUtils.toString(item.getCreated(), DateUtils.TIMESTAMP), // created
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
