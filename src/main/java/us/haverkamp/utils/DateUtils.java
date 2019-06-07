package us.haverkamp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.Node;

public class DateUtils {
	public static final String DATE = "yyyy-MM-dd";
	public static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	
	private static Date parseDate(String date, String pattern) throws ParseException {
		return date != null && date.length() != 0 ? new SimpleDateFormat(pattern).parse(date) : null;
	}
	
	public static Date parseDate(String date) throws ParseException {
		return parseDate(date, DATE);
	}
	
	public static Date parseDate(Node node) throws ParseException {
		return parseDate(node.getTextContent());
	}
	
	public static Date parseTimestamp(String date) throws ParseException {
		return parseDate(date, TIMESTAMP);
	}
	
	public static Date parseTimestamp(Node node) throws ParseException {
		return parseDate(node.getTextContent(), TIMESTAMP);
	}
	
	public static String toString(Date date) {
		return toString(date, DATE);
	}
	
	public static String toString(Date date, String pattern) {
		return date != null ? new SimpleDateFormat(pattern).format(date) : null;
	}
}
