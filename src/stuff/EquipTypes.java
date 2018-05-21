package stuff;

public enum EquipTypes {
	HEAD("Head", true),
	TORSO("Torso", true),
	ARMS("Arms", true),
	PANTS("Pants", true),
	SHOES("Shoes", true),
	WEAPON("Weapon", false);
	
	private boolean isArmor;
	private String name;
	
	EquipTypes(String name, boolean isArmor){
		this.name=name;
		this.isArmor=isArmor;
	}
	
	public boolean isArmor() {
		return this.isArmor;
	}
	
	public boolean isWeapon() {
		return !this.isArmor;
	}
	
	public String getName() {
		return this.name;
	}
}
