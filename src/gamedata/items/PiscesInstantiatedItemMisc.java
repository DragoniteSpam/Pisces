package gamedata.items;

public class PiscesInstantiatedItemMisc extends PiscesInstantiatedItem {
	private PiscesItemMisc baseItem;
	
	public PiscesInstantiatedItemMisc(PiscesItemMisc baseItem) {
		super();
		this.baseItem=baseItem;
	}
	
	public PiscesItemMisc getBaseItem() {
		return this.baseItem;
	}
}
