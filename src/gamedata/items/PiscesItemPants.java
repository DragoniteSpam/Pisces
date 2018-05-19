package gamedata.items;

public class PiscesItemPants extends PiscesItemEquipment {
	public PiscesItemPants(String name) {
		super(name);
	}
	
	public PiscesItemPants(String name, int id) {
		super(name, id);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemPants(this));
	}
}
