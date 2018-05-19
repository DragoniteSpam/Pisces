package gamedata.items;

public class PiscesInstantiatedItemShoes extends PiscesInstantiatedItemEquipment {
	private PiscesItemShoes baseItem;
	
	public PiscesInstantiatedItemShoes(PiscesItemShoes baseItem) {
		super();
		this.baseItem=baseItem;
		this.slots=new PiscesItemAugment[baseItem.getSlots()];
	}
	
	public PiscesItemShoes getBaseItem() {
		return this.baseItem;
	}
}
