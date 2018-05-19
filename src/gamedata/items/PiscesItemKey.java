package gamedata.items;

public class PiscesItemKey extends PiscesItem {
	public PiscesItemKey(String name) {
		super(name);
	}

	public PiscesItemKey(String name, int id) {
		super(name, id);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemKey(this));
	}
}
