package gamedata.items;

import gamedata.PiscesMove;
import stuff.Element;
import stuff.Stats;

public class PiscesItemManual extends PiscesItem {
	protected PiscesMove advanceMove;

	public PiscesItemManual(String name) {
		super(name);
		
		init();
	}

	public PiscesItemManual(String name, int id) {
		super(name, id);
		
		init();
	}
	
	private void init() {
		this.advanceMove=null;
	}
	
	public void setMove(PiscesMove move) {
		this.advanceMove=move;
	}
	
	public PiscesMove getMove() {
		return this.advanceMove;
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemManual(this));
	}
}
