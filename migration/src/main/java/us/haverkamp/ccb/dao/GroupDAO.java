package us.haverkamp.ccb.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import us.haverkamp.ccb.domain.Group;

public class GroupDAO extends GenericDAO<Group> {
	public Group findByName(String name) throws DataAccessException {
		final String xml =
			get("group_profiles&include_participants=false");
		
		final Map<String, Group> groups = new HashMap<String, Group>();
		
		try {
			for(Group group : Mapper.getGroups(xml)) {
				groups.put(group.getName(), group);
			}
		} catch(ParserConfigurationException e) {
			throw new DataAccessException(e);
		} catch(SAXException e) {
			throw new DataAccessException(e);
		} catch(IOException e) {
			throw new DataAccessException(e);
		}
		
		return groups.get(name);
	}
	
	protected Group getItem(ResultSet rs) throws DataAccessException {
		try {
			return Mapper.getGroup(rs);
		} catch(SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	protected Map<String, Integer> parseXML(final Document document) {
		final Map<String, Integer> items = new HashMap<String, Integer>();
		final NodeList groups = document.getElementsByTagName("group");
		
		for(int i=0;i<groups.getLength();i++) {
			final Element element = (Element) groups.item(i);
			
			final Integer value = Integer.valueOf(element.getAttribute("id"));
			final String key = element.getElementsByTagName("name").item(0).getTextContent();
			
			items.put(key, value);
		}

		return items;
	}
}
