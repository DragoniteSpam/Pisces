package gamedata.items;

public class PiscesItemMisc extends PiscesItem {
	public PiscesItemMisc(String name) {
		super(name);
	}

	public PiscesItemMisc(String name, int id) {
		super(name, id);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemMisc(this));
	}
}
