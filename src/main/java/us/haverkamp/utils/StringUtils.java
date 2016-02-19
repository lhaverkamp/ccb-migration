package us.haverkamp.utils;

import org.w3c.dom.Node;

public class StringUtils {
	public static Boolean parseBoolean(Node n) {
		return parseBoolean(n.getTextContent());
	}
	
	public static Boolean parseBoolean(String s) {
		return s != null ? Boolean.parseBoolean(s) : null;
	}
	
	public static Integer parseInt(Node n) {
		return parseInt(n.getTextContent());
	}
	
	public static Integer parseInt(String s) {
		return s != null && s.length() != 0 ? Integer.parseInt(s) : null;
	}

	public static Long parseLong(Node n) {
		return parseLong(n.getTextContent());
	}
	
	public static Long parseLong(String s) {
		return s != null && s.length() != 0 ? Long.parseLong(s) : null;
	}
	
	public static String parseString(Node n) {
		final String s = n.getTextContent();
		
		return s != null && s.length() != 0 ? s : null;
	}
}
