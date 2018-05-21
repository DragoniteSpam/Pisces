package stuff;

public enum Element {
	NORMAL("Normal"),
	FIRE("Fire"),
	WATER("Water"),
	ELECTRIC("Electric"),
	LIGHT("Light"),
	DARK("Dark"),
	EL06("EL06"),
	EL07("EL07"),
	EL08("EL08"),
	EL09("EL09"),
	EL10("EL10"),
	EL11("EL11"),
	EL12("EL12"),
	EL13("EL13"),
	EL14("EL14"),
	EL15("EL15");
	
	private String name;
	
	Element(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}
}
