package gamedata.items;

public class PiscesInstantiatedItemCollectable extends PiscesInstantiatedItem {
	private PiscesItemCollectable baseItem;
	
	public PiscesInstantiatedItemCollectable(PiscesItemCollectable baseItem) {
		super();
		this.baseItem=baseItem;
	}
	
	public PiscesItemCollectable getBaseItem() {
		return this.baseItem;
	}
}
