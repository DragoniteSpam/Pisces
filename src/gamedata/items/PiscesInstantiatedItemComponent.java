package gamedata.items;

public class PiscesInstantiatedItemComponent extends PiscesInstantiatedItem {
	private PiscesItemComponent baseItem;
	public PiscesInstantiatedItemComponent(PiscesItemComponent baseItem) {
		super();
		this.baseItem=baseItem;
	}
	
	public PiscesItemComponent getBaseItem() {
		return this.baseItem;
	}
}
