package us.haverkamp.utils;

import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVUtils {
	public static final CSVPrinter getPrinter(Appendable appendable, CSVFormat format) throws IOException {
		return new CSVPrinter(appendable, format);
	}
}
