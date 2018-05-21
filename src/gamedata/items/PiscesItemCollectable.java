package gamedata.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PiscesItemCollectable extends PiscesItem {
	public PiscesItemCollectable(String name) {
		super(name);
	}

	public PiscesItemCollectable(String name, int id) {
		super(name, id);
	}
	
	public PiscesItemCollectable(String name, int id, TextureRegion mainImage, TextureRegion grayedImage) {
		super(name, id, mainImage, grayedImage);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemCollectable(this));
	}
}
