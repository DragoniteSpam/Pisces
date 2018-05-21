package gamedata.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PiscesItemKey extends PiscesItem {
	public PiscesItemKey(String name) {
		super(name);
	}

	public PiscesItemKey(String name, int id) {
		super(name, id);
	}
	
	public PiscesItemKey(String name, int id, TextureRegion mainImage, TextureRegion grayedImage) {
		super(name, id, mainImage, grayedImage);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemKey(this));
	}
}
