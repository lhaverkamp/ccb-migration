package us.haverkamp.ccb.domain;

public class Address extends Api {
	private String name;
	
	private String areaOfTown;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	private String mailingCarrierRoute;
	
	private String country;
	private String line1;
	private String line2;
	
	public Address(String street, String city, String state, String zip) {
		setStreet(street);
		setCity(city);
		setState(state);
		setZip(zip);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getAreaOfTown() {
		return areaOfTown;
	}

	public void setAreaOfTown(String areaOfTown) {
		this.areaOfTown = areaOfTown;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getMailingCarrierRoute() {
		return mailingCarrierRoute;
	}

	public void setMailingCarrierRoute(String mailingCarrierRoute) {
		this.mailingCarrierRoute = mailingCarrierRoute;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}
}
