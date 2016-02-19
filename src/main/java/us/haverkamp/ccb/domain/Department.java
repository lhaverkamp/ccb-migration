package us.haverkamp.ccb.domain;

import java.util.HashMap;
import java.util.Map;

public class Department {
	private Long id;
	private String name;

	public Department(Long id, String name) {
		setId(id);
		setName(name);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		final Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", getId());
		map.put("name", getName());
		
		return map.toString();
	}
}
