package us.haverkamp.ccb.domain;

public class Address {
	private String areaOfTown;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	private String mailingCarrierRoute;

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
}
