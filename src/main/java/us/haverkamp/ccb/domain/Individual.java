package us.haverkamp.ccb.domain;

import java.util.Date;
import java.util.List;

public class Individual extends Api {
	private Long id;
	private Campus campus;
	private Family family;
	
	private String familyPosition;
	
	private Boolean limitedAccessUser;
	
	private String prefix;
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	
	private String legalFirstName;
	private String fullName;
	
	private String login;
	private String email;
	
	private Address contact;
	private Address home;
	private Address work;
	private Address other;
	
	private String contactPhone;
	private String homePhone;
	private String workPhone;
	private String mobilePhone;
	
	private String fax;
	private String pager;
	
	private String emergencyPhone;
	private String emergencyContact;
	
	private Date birthday;
	private Date anniversary;
	private Date deceased;
	
	private String gender;
	
	private Integer givingNumber;
	
	private String maritalStatus;
	private String workTitle;
	private String school;
	private String schoolGrade;
	
	private String allergies;
	private Boolean confirmedNoAllergies;
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
	
	private MembershipType membershipType;
	private Date membershipDate;
	private Date membershipEnd;
	
	private Boolean baptized;
	private Boolean listed;
	private Boolean active;
	
	private String howTheyHeard;
	private String reasonLeftChurch;
	
	private CommunicationPreferences communicationPreferences;
	private PrivacySettings privacySettings;
	
	private List<UserDefinedField> userDefinedTextFields;
	private List<UserDefinedField> userDefinedDateFields;
	private List<UserDefinedField> userDefinedPulldownFields;
	
	private Long syncId;
	private String otherId;
	
	private Individual creator;
	private Individual modifier;
	private Date created;
	private Date modified;
	
	public Individual(Long id) {
		setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Campus getCampus() {
		return campus;
	}
	
	public void setCampus(Campus campus) {
		this.campus = campus;
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
	
	public String getLegalFirstName() {
		return this.legalFirstName;
	}
	
	public void setLegalFirstName(String legalFirstName) {
		this.legalFirstName = legalFirstName;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return this.email;
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

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPager() {
		return pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public String getEmergencyPhone() {
		return this.emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
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

	public Date getDeceased() {
		return this.deceased;
	}
	
	public void setDeceased(Date deceased) {
		this.deceased = deceased;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getGivingNumber() {
		return givingNumber;
	}

	public void setGivingNumber(Integer givingNumber) {
		this.givingNumber = givingNumber;
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
	
	public Boolean getConfirmedNoAllergies() {
		return this.confirmedNoAllergies;
	}
	
	public void setConfirmedNoAllergies(Boolean confirmedNoAllergies) {
		this.confirmedNoAllergies = confirmedNoAllergies;
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

	public MembershipType getMembershipType() {
		return this.membershipType;
	}

	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}

	public Date getMembershipDate() {
		return membershipDate;
	}

	public void setMembershipDate(Date membershipDate) {
		this.membershipDate = membershipDate;
	}

	public Date getMembershipEnd() {
		return membershipEnd;
	}

	public void setMembershipEnd(Date membershipEnd) {
		this.membershipEnd = membershipEnd;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getHowTheyHeard() {
		return this.howTheyHeard;
	}

	public void setHowTheyHeard(String howTheyHeard) {
		this.howTheyHeard = howTheyHeard;
	}

	public String getReasonLeftChurch() {
		return this.reasonLeftChurch;
	}

	public void setReasonLeftChurch(String reasonLeftChurch) {
		this.reasonLeftChurch = reasonLeftChurch;
	}

	public CommunicationPreferences getCommunicationPreferences() {
		return communicationPreferences;
	}

	public void setCommunicationPreferences(
			CommunicationPreferences communicationPreferences) {
		this.communicationPreferences = communicationPreferences;
	}

	public PrivacySettings getPrivacySettings() {
		return privacySettings;
	}

	public void setPrivacySettings(PrivacySettings privacySettings) {
		this.privacySettings = privacySettings;
	}

	public String getUserDefinedTextField(String name) {
		for(UserDefinedField field : getUserDefinedTextFields()) {
			if(name.equals(field.getName())) {
				return field.getText();
			}
		}
		
		return null;
	}

	public List<UserDefinedField> getUserDefinedTextFields() {
		return userDefinedTextFields;
	}
	
	public void setUserDefinedTextFields(
			List<UserDefinedField> userDefinedTextFields) {
		this.userDefinedTextFields = userDefinedTextFields;
	}

	public Date getUserDefinedDateField(String name) {
		for(UserDefinedField field : getUserDefinedDateFields()) {
			if(name.equals(field.getName())) {
				return field.getDate();
			}
		}
		
		return null;
	}

	public List<UserDefinedField> getUserDefinedDateFields() {
		return userDefinedDateFields;
	}

	public void setUserDefinedDateFields(
			List<UserDefinedField> userDefinedDateFields) {
		this.userDefinedDateFields = userDefinedDateFields;
	}

	public Integer getUserDefinedPulldownField(String name) {
		for(UserDefinedField field : getUserDefinedPulldownFields()) {
			if(name.equals(field.getName())) {
				return field.getSelection();
			}
		}
		
		return null;
	}

	public List<UserDefinedField> getUserDefinedPulldownFields() {
		return userDefinedPulldownFields;
	}

	public void setUserDefinedPulldownFields(
			List<UserDefinedField> userDefinedPulldownFields) {
		this.userDefinedPulldownFields = userDefinedPulldownFields;
	}

	public Long getSyncId() {
		return this.syncId;
	}

	public void setSyncId(Long syncId) {
		this.syncId = syncId;
	}

	public String getOtherId() {
		return this.otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}

	public Individual getCreator() {
		return creator;
	}

	public void setCreator(Individual creator) {
		this.creator = creator;
	}

	public Individual getModifier() {
		return modifier;
	}

	public void setModifier(Individual modifier) {
		this.modifier = modifier;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
}
