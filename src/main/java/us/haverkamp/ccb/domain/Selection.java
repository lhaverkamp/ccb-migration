package us.haverkamp.ccb.domain;

public class Selection extends LookupTable {
	private String type;
	
	public Selection(String type, Long id, String name) {
		super(id, name);
		setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
