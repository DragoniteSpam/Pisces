package gamedata.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PiscesItemComponent extends PiscesItem {
	public PiscesItemComponent(String name) {
		super(name);
	}

	public PiscesItemComponent(String name, int id) {
		super(name, id);
	}
	
	public PiscesItemComponent(String name, int id, TextureRegion mainImage, TextureRegion grayedImage) {
		super(name, id, mainImage, grayedImage);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemComponent(this));
	}
}
