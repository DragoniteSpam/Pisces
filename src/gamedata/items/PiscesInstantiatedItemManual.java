package gamedata.items;

public class PiscesInstantiatedItemManual extends PiscesInstantiatedItem {
	private PiscesItemManual baseItem;
	
	public PiscesInstantiatedItemManual(PiscesItemManual baseItem) {
		super();
		this.baseItem=baseItem;
	}
	
	public PiscesItemManual getBaseItem() {
		return this.baseItem;
	}
}
