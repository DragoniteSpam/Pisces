package gamedata.items;

public class PiscesItemHead extends PiscesItemEquipment {
	public PiscesItemHead(String name) {
		super(name);
	}
	
	public PiscesItemHead(String name, int id) {
		super(name, id);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemHead(this));
	}
}
