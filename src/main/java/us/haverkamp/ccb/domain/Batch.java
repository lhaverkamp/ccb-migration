package us.haverkamp.ccb.domain;

import java.util.Date;

public class Batch extends Api {
	private Long id; // id
	private Campus campus; // campus
	private Date postDate; // post_date
	private Date beginDate; // begin_date
	private Date endDate; // end_date
	private Boolean inAccountingPackage; // is_accounting_package
	private String status; // status
	private String source; // source
	
	private Individual creator; // creator
	private Individual modifier; // modifier
	private Date created; // created
	private Date modified; // modified
	
	public Batch(Long id) {
		setId(id);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Campus getCampus() {
		return this.campus;
	}
	
	public void setCampus(Campus campus) {
		this.campus = campus;
	}
	
	public Date getPostDate() {
		return this.postDate;
	}
	
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	public Date getBeginDate() {
		return this.beginDate;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Boolean getInAccountingPackage() {
		return this.inAccountingPackage;
	}
	
	public void setInAccountingPackage(Boolean inAccountingPackage) {
		this.inAccountingPackage = inAccountingPackage;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	public Individual getCreator() {
		return creator;
	}

	public void setCreator(Individual creator) {
		this.creator = creator;
	}

	public Individual getModifier() {
		return this.modifier;
	}

	public void setModifier(Individual modifier) {
		this.modifier = modifier;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
}
