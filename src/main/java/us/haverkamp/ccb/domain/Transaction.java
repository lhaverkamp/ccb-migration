package us.haverkamp.ccb.domain;

import java.util.Date;

public class Transaction extends Api {
	private Batch batch; // batch

	private Long id; // id
	private Individual individual; // individual
	private Campus campus; // campus
	private Date date; // date
	private Grouping grouping; // grouping
	private String paymentType; // payment_type
	private String checkNumber; // check_number
	
	private COA coa; // coa
	private Float amount; // amount
	private Boolean taxDeductible; // tax_deductible
	private String notes; // note
	
	private Individual creator; // creator
	private Individual modifier; // modifier
	private Date created; // created
	private Date modified; // modified
	
	public Transaction(Batch batch, Long id) {
		setBatch(batch);
		setId(id);
	}
	
	public Batch getBatch() {
		return this.batch;
	}
	
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Individual getIndividual() {
		return this.individual;
	}
	
	public void setIndividual(Individual individual) {
		this.individual = individual;
	}
	
	public Campus getCampus() {
		return this.campus;
	}
	
	public void setCampus(Campus campus) {
		this.campus = campus;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Grouping getGrouping() {
		return this.grouping;
	}
	
	public void setGrouping(Grouping grouping) {
		this.grouping = grouping;
	}
	
	public String getPaymentType() {
		return this.paymentType;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public String getCheckNumber() {
		return this.checkNumber;
	}
	
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	
	public COA getCOA() {
		return this.coa;
	}
	
	public void setCOA(COA coa) {
		this.coa = coa;
	}
	
	public Float getAmount() {
		return this.amount;
	}
	
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	public Boolean getTaxDeductible() {
		return this.taxDeductible;
	}
	
	public void setTaxDeductible(Boolean taxDeductible) {
		this.taxDeductible = taxDeductible;
	}
	
	public String getNotes() {
		return this.notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
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
