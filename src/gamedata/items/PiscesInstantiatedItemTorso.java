package gamedata.items;

public class PiscesInstantiatedItemTorso extends PiscesInstantiatedItemEquipment {
	private PiscesItemTorso baseItem;
	
	public PiscesInstantiatedItemTorso(PiscesItemTorso baseItem) {
		super();
		this.baseItem=baseItem;
		this.slots=new PiscesItemAugment[baseItem.getSlots()];
	}
	
	public PiscesItemTorso getBaseItem() {
		return this.baseItem;
	}
}
