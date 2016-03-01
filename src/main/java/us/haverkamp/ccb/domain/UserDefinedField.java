package us.haverkamp.ccb.domain;

import java.util.Date;

public class UserDefinedField extends Api {
	private String name;
	private String label;
	
	private String text;
	private Date date;
	private Integer selection;
	
	private Boolean adminOnly;
	
	public UserDefinedField(String name, String label, String text) {
		setName(name);
		setLabel(label);
		setText(text);
	}
	
	public UserDefinedField(String name, String label, Date date) {
		setName(name);
		setLabel(label);
		setDate(date);
	}
	
	public UserDefinedField(String name, String label, Integer selection) {
		setName(name);
		setLabel(label);
		setSelection(selection);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getSelection() {
		return selection;
	}

	public void setSelection(Integer selection) {
		this.selection = selection;
	}

	public Boolean isAdminOnly() {
		return adminOnly;
	}

	public void setAdminOnly(Boolean adminOnly) {
		this.adminOnly = adminOnly;
	}
}
