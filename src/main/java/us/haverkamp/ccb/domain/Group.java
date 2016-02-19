package us.haverkamp.ccb.domain;

public class Group extends Api {
	private Long id;
	private String name;
	private String description;
	
	public Group(Long id) {
		setId(id);
	}
	
	public Group(Long id, String name) {
		this(id);
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
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
