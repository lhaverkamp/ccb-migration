package us.haverkamp.ccb.domain;

public class CommunicationPreferences extends Api {
	private Boolean receiveEmailFromChurch;
	private String defaultNewGroupMessages;
	private String defaultNewGroupComments;
	private String defaultNewGroupDigest;
	private String defaultNewGroupSms;
	
	public CommunicationPreferences(Boolean receiveEmailFromChurch,
			String defaultNewGroupMessages, String defaultNewGroupComments,
			String defaultNewGroupDigest, String defaultNewGroupSms) {
		setReceiveEmailFromChurch(receiveEmailFromChurch);
		setDefaultNewGroupMessages(defaultNewGroupMessages);
		setDefaultNewGroupComments(defaultNewGroupComments);
		setDefaultNewGroupDigest(defaultNewGroupDigest);
		setDefaultNewGroupSms(defaultNewGroupSms);
	}

	public Boolean getReceiveEmailFromChurch() {
		return receiveEmailFromChurch;
	}

	public void setReceiveEmailFromChurch(Boolean receiveEmailFromChurch) {
		this.receiveEmailFromChurch = receiveEmailFromChurch;
	}

	public String getDefaultNewGroupMessages() {
		return defaultNewGroupMessages;
	}

	public void setDefaultNewGroupMessages(String defaultNewGroupMessages) {
		this.defaultNewGroupMessages = defaultNewGroupMessages;
	}

	public String getDefaultNewGroupComments() {
		return defaultNewGroupComments;
	}

	public void setDefaultNewGroupComments(String defaultNewGroupComments) {
		this.defaultNewGroupComments = defaultNewGroupComments;
	}

	public String getDefaultNewGroupDigest() {
		return defaultNewGroupDigest;
	}

	public void setDefaultNewGroupDigest(String defaultNewGroupDigest) {
		this.defaultNewGroupDigest = defaultNewGroupDigest;
	}

	public String getDefaultNewGroupSms() {
		return defaultNewGroupSms;
	}

	public void setDefaultNewGroupSms(String defaultNewGroupSms) {
		this.defaultNewGroupSms = defaultNewGroupSms;
	}
}
