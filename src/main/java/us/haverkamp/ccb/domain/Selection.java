package us.haverkamp.ccb.domain;

public class Selection extends Api {
	private String type;
	private Long id;
	private String name;
	private Integer order;
	
	public Selection(String type, Long id, String name) {
		setType(type);
		setId(id);
		setName(name);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
