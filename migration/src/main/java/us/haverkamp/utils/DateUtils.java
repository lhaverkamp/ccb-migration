package us.haverkamp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static final String DATE = "yyyy-MM-dd";
	public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	public static Date toDate(String date) throws ParseException {
		return toDate(date, DATE);
	}
	
	public static Date toDate(String date, String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(date);
	}
	
	public static String toString(Date date) {
		return toString(date, DATE);
	}
	
	public static String toString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
}
