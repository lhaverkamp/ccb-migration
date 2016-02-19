package us.haverkamp.ccb.domain;

public class PrivacySettings extends Api {
	private Boolean listed;
	private int mailingAddress;
	private int homeAddress;
	private int contactPhone;
	private int homePhone;
	private int workPhone;
	private int mobilePhone;
	private int emergencyPhone;
	private int birthday;
	private int anniversary;
	private int gender;
	private int martialStatus;
	private int userDefinedFields;
	private int allergies;
	
	public PrivacySettings(Boolean listed, int mailingAddress, int homeAddress,
			int contactPhone, int homePhone, int workPhone, int mobilePhone,
			int emergencyPhone, int birthday, int anniversary, int gender, int martialStatus,
			int userDefinedFields, int allergies) {
		setListed(listed);
		setMailingAddress(mailingAddress);
		setHomeAddress(homeAddress);
		setContactPhone(contactPhone);
		setHomePhone(homePhone);
		setWorkPhone(workPhone);
		setMobilePhone(mobilePhone);
		setEmergencyPhone(emergencyPhone);
		setBirthday(birthday);
		setGender(gender);
		setMartialStatus(martialStatus);
		setUserDefinedFields(userDefinedFields);
		setAllergies(allergies);
	}
	public Boolean getListed() {
		return listed;
	}
	public void setListed(Boolean listed) {
		this.listed = listed;
	}
	public int getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(int mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	public int getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(int homeAddress) {
		this.homeAddress = homeAddress;
	}
	public int getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(int contactPhone) {
		this.contactPhone = contactPhone;
	}
	public int getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(int homePhone) {
		this.homePhone = homePhone;
	}
	public int getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(int workPhone) {
		this.workPhone = workPhone;
	}
	public int getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(int mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public int getEmergencyPhone() {
		return emergencyPhone;
	}
	public void setEmergencyPhone(int emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}
	public int getBirthday() {
		return birthday;
	}
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}
	public int getAnniversary() {
		return this.anniversary;
	}
	
	public void setAnniversary(int anniversary) {
		this.anniversary = anniversary;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getMartialStatus() {
		return martialStatus;
	}
	public void setMartialStatus(int martialStatus) {
		this.martialStatus = martialStatus;
	}
	public int getUserDefinedFields() {
		return userDefinedFields;
	}
	public void setUserDefinedFields(int userDefinedFields) {
		this.userDefinedFields = userDefinedFields;
	}
	public int getAllergies() {
		return allergies;
	}
	public void setAllergies(int allergies) {
		this.allergies = allergies;
	}
}
