package gamedata.items;

public class PiscesInstantiatedItemKey extends PiscesInstantiatedItem {
	private PiscesItemKey baseItem;
	
	public PiscesInstantiatedItemKey(PiscesItemKey baseItem) {
		super();
		this.baseItem=baseItem;
	}
	
	public PiscesItemKey getBaseItem() {
		return this.baseItem;
	}
}
