package gamedata.items;

public class PiscesItemWeapon extends PiscesItemEquipment {

	public PiscesItemWeapon(String name) {
		super(name);
	}
	
	public PiscesItemWeapon(String name, int id) {
		super(name, id);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemWeapon(this));
	}

}
