package gamedata.items;

public class PiscesInstantiatedItemWeapon extends PiscesInstantiatedItemEquipment {
	private PiscesItemWeapon baseItem;
	
	public PiscesInstantiatedItemWeapon(PiscesItemWeapon baseItem) {
		super();
		this.baseItem=baseItem;
		this.slots=new PiscesItemAugment[baseItem.getSlots()];
	}
	
	public PiscesItemWeapon getBaseItem() {
		return this.baseItem;
	}
}
