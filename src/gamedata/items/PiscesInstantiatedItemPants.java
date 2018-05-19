package gamedata.items;

public class PiscesInstantiatedItemPants extends PiscesInstantiatedItemEquipment {
	private PiscesItemPants baseItem;
	
	public PiscesInstantiatedItemPants(PiscesItemPants baseItem) {
		super();
		this.baseItem=baseItem;
		this.slots=new PiscesItemAugment[baseItem.getSlots()];
	}
	
	public PiscesItemPants getBaseItem() {
		return this.baseItem;
	}
}
