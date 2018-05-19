package gamedata.items;

public class PiscesInstantiatedItemHands extends PiscesInstantiatedItemEquipment {
	private PiscesItemHands baseItem;
	
	public PiscesInstantiatedItemHands(PiscesItemHands baseItem) {
		super();
		this.baseItem=baseItem;
		this.slots=new PiscesItemAugment[baseItem.getSlots()];
	}
	
	public PiscesItemHands getBaseItem() {
		return this.baseItem;
	}
}
