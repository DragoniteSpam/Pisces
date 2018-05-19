package gamedata.items;

public class PiscesInstantiatedItemAugment extends PiscesInstantiatedItem {
	private PiscesItemAugment baseItem;
	
	public PiscesInstantiatedItemAugment(PiscesItemAugment baseItem) {
		super();
		this.baseItem=baseItem;
	}
	
	public PiscesItemAugment getBaseItem() {
		return this.baseItem;
	}
}
