package gamedata.items;

public class PiscesItemCollectable extends PiscesItem {
	public PiscesItemCollectable(String name) {
		super(name);
	}

	public PiscesItemCollectable(String name, int id) {
		super(name, id);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemCollectable(this));
	}
}
