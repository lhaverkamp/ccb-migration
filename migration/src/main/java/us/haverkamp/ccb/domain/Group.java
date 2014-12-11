package us.haverkamp.ccb.domain;

public class Group {
	private Long id;
	private String name;
	
	public Group(Long id) {
		setId(id);
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
}
