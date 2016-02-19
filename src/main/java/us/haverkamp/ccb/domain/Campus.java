package us.haverkamp.ccb.domain;

public class Campus extends Api {
	private Long id;
	private String name;

	public Campus(Long id, String name) {
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
}
