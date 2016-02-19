package us.haverkamp.ccb.domain;

import java.util.Date;

public class Family extends Api {
	private Long id;
	private String name;
	private Date modified;
	
	public Family(Long id, String name) {
		setId(id);
		setName(name);
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getModified() {
		return this.modified;
	}
	
	public void setModified(Date modified) {
		this.modified = modified;
	}
}
