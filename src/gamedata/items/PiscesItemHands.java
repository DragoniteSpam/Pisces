package gamedata.items;

public class PiscesItemHands extends PiscesItemEquipment {
	public PiscesItemHands(String name) {
		super(name);
	}
	
	public PiscesItemHands(String name, int id) {
		super(name, id);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemHands(this));
	}
}
