package stuff;

public enum ItemPockets {
	WEAPON("Weapons"),
	HAT("Hats"),
	ARMS("Arms"),
	TORSO("Torso"),
	PANTS("Pants"),
	SHOES("Shoes"),
	MANUAL("Manuals"),
	COLLECTABLE("Collectables"),
	AUGMENT("Augments"),
	COMPONENT("Components"),
	MISC("Misc."),
	KEY("Keys");
	
	public String name;
	
	ItemPockets(String name){
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}
}
