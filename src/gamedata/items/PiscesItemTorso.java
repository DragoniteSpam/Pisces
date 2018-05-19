package gamedata.items;

public class PiscesItemTorso extends PiscesItemEquipment {
	public PiscesItemTorso(String name) {
		super(name);
	}
	
	public PiscesItemTorso(String name, int id) {
		super(name, id);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemTorso(this));
	}
}
