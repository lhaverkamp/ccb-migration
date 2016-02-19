package us.haverkamp.ccb.domain;

public class PrivacyLevel extends Api {
	public static final int CHURCH_LEADERSHIP_ONLY = 1;
	public static final int FRIENDS_ONLY = 2;
	public static final int FRIENDS_AND_GROUP_MEMBERS = 3;
	public static final int EVERYBODY = 4;
	
	private int level;
	
	private PrivacyLevel(int level) {
		setLevel(level);
	}
	
	public String getName() {
		String name = "Unknown";
		
		switch(this.level) {
		case CHURCH_LEADERSHIP_ONLY:
			name = "Church Leadership Only";
			break;
		case FRIENDS_ONLY:
			name = "Friends Only";
			break;
		case FRIENDS_AND_GROUP_MEMBERS:
			name = "Friends & My Group Members";
			break;
		case EVERYBODY:
			name = "Everybody";
			break;
		}
		
		return name;
	}
	
	public PrivacyLevel getLevel() {
		return new PrivacyLevel(this.level);
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
