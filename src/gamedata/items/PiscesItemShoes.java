package gamedata.items;

public class PiscesItemShoes extends PiscesItemEquipment {
	public PiscesItemShoes(String name) {
		super(name);
	}
	
	public PiscesItemShoes(String name, int id) {
		super(name, id);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemShoes(this));
	}
}
