package us.haverkamp.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XmlUtils {
	public static DocumentBuilder newDocumentBuilder() 
			throws ParserConfigurationException {
		final DocumentBuilderFactory documentBuilderFactory = 
			DocumentBuilderFactory.newInstance();
		
		return documentBuilderFactory.newDocumentBuilder();
	}
	
	public static Document newDocument() throws ParserConfigurationException {
		final DocumentBuilder documentBuilder = newDocumentBuilder();
		
		return documentBuilder.newDocument();
	}
	
	public static Document getDocument(String xml) 
			throws ParserConfigurationException, SAXException, IOException {
		final DocumentBuilder documentBuilder = newDocumentBuilder();
		
		return documentBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
	}
	
	public static String toString(Node node) 
			throws TransformerException, IOException {
		final DOMSource domSource = new DOMSource(node);
		final StringWriter writer = new StringWriter();
		
		try {
			final StreamResult streamResult = new StreamResult(writer);
			
			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(domSource, streamResult);
			
			return writer.toString();
		} finally {
			writer.close();
		}
	}
}
