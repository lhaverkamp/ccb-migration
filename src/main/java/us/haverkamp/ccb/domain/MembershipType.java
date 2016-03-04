package us.haverkamp.ccb.domain;

public class MembershipType extends Api {
	private Long id;
	private String name;
	private Integer order;
	
	public MembershipType(Long id, String name) {
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
