package stuff;

public enum Stats {
	HP("HP", false),
	ATK("Attack", false),
	DEF("Defense", false),
	SPA("Sp. Attack", false),
	SPD("Sp. Defense", false),
	AGL("Agility", false),
	BLK("Block", true),
	CRT("Critical Rate", true),
	LCK("Luck", true);
	
	private String name;
	private boolean isStatic;
	
	Stats(String name, boolean isStatic) {
		this.name=name;
		this.isStatic=isStatic;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getIsStatic() {
		return this.isStatic;
	}
}
