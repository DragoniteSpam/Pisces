package gamedata.items;

public class PiscesItemComponent extends PiscesItem {
	public PiscesItemComponent(String name) {
		super(name);
	}

	public PiscesItemComponent(String name, int id) {
		super(name, id);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemComponent(this));
	}
}
