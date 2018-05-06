package gamedata;

import com.badlogic.gdx.graphics.Texture;

public class PiscesInventorySlot extends GameData {
	protected Texture image;
	
	public PiscesInventorySlot(String name) {
		super(name);
		
		init();
	}

	public PiscesInventorySlot(String name, int id) {
		super(name, id);
		
		init();
	}
	
	private void init() {
		this.image=null;
	}
}
