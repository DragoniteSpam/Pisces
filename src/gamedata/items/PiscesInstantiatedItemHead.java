package gamedata.items;

public class PiscesInstantiatedItemHead extends PiscesInstantiatedItemEquipment {
	private PiscesItemHead baseItem;
	
	public PiscesInstantiatedItemHead(PiscesItemHead baseItem) {
		super();
		this.baseItem=baseItem;
		this.slots=new PiscesItemAugment[baseItem.getSlots()];
	}
	
	public PiscesItemHead getBaseItem() {
		return this.baseItem;
	}
}
