package us.haverkamp.ccb.domain;

public class OrderedValue<V> extends Api {
	private V value;
	private Integer order;

	public OrderedValue(V value, Integer order) {
		setValue(value);
		setOrder(order);
	}
	
	public V getValue() {
		return this.value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public Integer getOrder() {
		return this.order;
	}
	
	public void setOrder(Integer order) {
		this.order = order;
	}
}
