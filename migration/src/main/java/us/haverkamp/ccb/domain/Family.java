package us.haverkamp.ccb.domain;

public class Family {
	private Long id;
	
	public Family(Long id) {
		setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
