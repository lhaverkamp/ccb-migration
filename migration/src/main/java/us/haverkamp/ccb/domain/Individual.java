package us.haverkamp.ccb.domain;

import java.util.Date;

public class Individual {
	private Long id;
	private Family family;
	
	private String familyPosition;
	
	private Boolean limitedAccessUser;
	
	private String prefix;
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	
	private String email;
	
	private Address contact;
	private Address home;
	private Address work;
	private Address other;
	
	private Number contactPhone;
	private Number homePhone;
	private Number workPhone;
	private Number mobilePhone;
	
	private Number fax;
	private Number pager;
	
	private Number emergencyPhone;
	private String emergencyContact;
	
	private Date birthday;
	private Date anniversary;
	
	private String gender;
	
	private Integer givingNo;
	
	private String maritalStatus;
	private String workTitle;
	private String school;
	private String schoolGrade;
	
	private String allergies;
	private String military;
	private String confirmationVerse;
	private String emailWork;
	private String maidenName;
	
	private Date firstCommunion;
	private Date confirmation;
	private Date baptism;
	
	private String elder;
	
	private Boolean newsletter;
	private Boolean confirmed;
	
	private Date membershipStartDate;
	private Date membershipStopDate;
	
	private String membershipType;
	
	private Boolean baptized;
	private Boolean listed;
	private Boolean inactive;
	
	private String howTheyHeard;
	private String reasonLeftChurch;
	
	public Individual(Long id) {
		setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public String getFamilyPosition() {
		return familyPosition;
	}

	public void setFamilyPosition(String familyPosition) {
		this.familyPosition = familyPosition;
	}

	public Boolean getLimitedAccessUser() {
		return limitedAccessUser;
	}

	public void setLimitedAccessUser(Boolean limitedAccessUser) {
		this.limitedAccessUser = limitedAccessUser;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getContact() {
		return contact;
	}

	public void setContact(Address contact) {
		this.contact = contact;
	}

	public Address getHome() {
		return home;
	}

	public void setHome(Address home) {
		this.home = home;
	}

	public Address getWork() {
		return work;
	}

	public void setWork(Address work) {
		this.work = work;
	}

	public Address getOther() {
		return other;
	}

	public void setOther(Address other) {
		this.other = other;
	}

	public Number getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(Number contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Number getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(Number homePhone) {
		this.homePhone = homePhone;
	}

	public Number getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(Number workPhone) {
		this.workPhone = workPhone;
	}

	public Number getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(Number mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Number getFax() {
		return fax;
	}

	public void setFax(Number fax) {
		this.fax = fax;
	}

	public Number getPager() {
		return pager;
	}

	public void setPager(Number pager) {
		this.pager = pager;
	}

	public Number getEmergencyPhone() {
		return this.emergencyPhone;
	}

	public void setEmergencyPhone(Number emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getEmergencyContact() {
		return this.emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getAnniversary() {
		return this.anniversary;
	}

	public void setAnniversary(Date anniversary) {
		this.anniversary = anniversary;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getGivingNo() {
		return givingNo;
	}

	public void setGivingNo(Integer givingNo) {
		this.givingNo = givingNo;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getWorkTitle() {
		return workTitle;
	}

	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSchoolGrade() {
		return this.schoolGrade;
	}

	public void setSchoolGrade(String schoolGrade) {
		this.schoolGrade = schoolGrade;
	}

	public String getAllergies() {
		return this.allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getMilitary() {
		return this.military;
	}

	public void setMilitary(String military) {
		this.military = military;
	}

	public String getConfirmationVerse() {
		return this.confirmationVerse;
	}

	public void setConfirmationVerse(String confirmationVerse) {
		this.confirmationVerse = confirmationVerse;
	}

	public String getEmailWork() {
		return emailWork;
	}

	public void setEmailWork(String emailWork) {
		this.emailWork = emailWork;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public Date getFirstCommunion() {
		return firstCommunion;
	}

	public void setFirstCommunion(Date firstCommunion) {
		this.firstCommunion = firstCommunion;
	}

	public Date getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(Date confirmation) {
		this.confirmation = confirmation;
	}

	public Date getBaptism() {
		return baptism;
	}

	public void setBaptism(Date baptism) {
		this.baptism = baptism;
	}

	public String getElder() {
		return elder;
	}

	public void setElder(String elder) {
		this.elder = elder;
	}

	public Boolean getNewsletter() {
		return newsletter;
	}

	public void setNewsletter(Boolean newsletter) {
		this.newsletter = newsletter;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Date getMembershipStartDate() {
		return membershipStartDate;
	}

	public void setMembershipStartDate(Date membershipStartDate) {
		this.membershipStartDate = membershipStartDate;
	}

	public Date getMembershipStopDate() {
		return membershipStopDate;
	}

	public void setMembershipStopDate(Date membershipStopDate) {
		this.membershipStopDate = membershipStopDate;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public Boolean getBaptized() {
		return baptized;
	}

	public void setBaptized(Boolean baptized) {
		this.baptized = baptized;
	}

	public Boolean getListed() {
		return listed;
	}

	public void setListed(Boolean listed) {
		this.listed = listed;
	}

	public Boolean getInactive() {
		return inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	public String getHowTheyHeard() {
		return howTheyHeard;
	}

	public void setHowTheyHeard(String howTheyHeard) {
		this.howTheyHeard = howTheyHeard;
	}

	public String getReasonLeftChurch() {
		return reasonLeftChurch;
	}

	public void setReasonLeftChurch(String reasonLeftChurch) {
		this.reasonLeftChurch = reasonLeftChurch;
	}
		
}
