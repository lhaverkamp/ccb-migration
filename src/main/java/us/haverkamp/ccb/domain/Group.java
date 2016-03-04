package us.haverkamp.ccb.domain;

import java.util.Date;

public class Group extends Api {
	private Long id;
	private String name;
	private String description;
	private String image;
	
	private Campus campus;
	private Individual mainLeader;
	private Individual director;
	private GroupType groupType;
	private Department department;
	
	private Individual creator;
	private Individual modifier;
	private Date created;
	private Date modified;
	
	public Group(Long id) {
		setId(id);
	}
	
	public Group(Long id, String name) {
		this(id);
		setName(name);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public Campus getCampus() {
		return this.campus;
	}
	
	public void setCampus(Campus campus) {
		this.campus = campus;
	}
	
	public Individual getMainLeader() {
		return this.mainLeader;
	}
	
	public void setMainLeader(Individual mainLeader) {
		this.mainLeader = mainLeader;
	}
	
	public Individual getDirector() {
		return this.director;
	}
	
	public void setDirector(Individual director) {
		this.director = director;
	}

	public GroupType getGroupType() {
		return groupType;
	}

	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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
