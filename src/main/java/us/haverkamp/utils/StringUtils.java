package us.haverkamp.utils;

import org.w3c.dom.Node;

public class StringUtils {
	public static Boolean parseBoolean(Node n) {
		return n != null ? parseBoolean(n.getTextContent()) : null;
	}
	
	public static Boolean parseBoolean(String s) {
		return s != null ? Boolean.parseBoolean(s) : null;
	}
	
	public static Integer parseInt(Node n) {
		return n != null ? parseInt(n.getTextContent()) : null;
	}
	
	public static Integer parseInt(String s) {
		return s != null && s.length() != 0 ? Integer.parseInt(s) : null;
	}

	public static Long parseLong(Node n) {
		return n != null ? parseLong(n.getTextContent()) : null;
	}
	
	public static Long parseLong(String s) {
		return s != null && s.length() != 0 ? Long.parseLong(s) : null;
	}
	
	public static String parseString(Node n) {
		return n != null ? parseString(n.getTextContent()) : null;
	}
	
	private static String parseString(String s) {
		return s != null && s.length() != 0 ? s : null;
	}
}
