package gamedata.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PiscesItemMisc extends PiscesItem {
	public PiscesItemMisc(String name) {
		super(name);
	}

	public PiscesItemMisc(String name, int id) {
		super(name, id);
	}
	
	public PiscesItemMisc(String name, int id, TextureRegion mainImage, TextureRegion grayedImage) {
		super(name, id, mainImage, grayedImage);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemMisc(this));
	}
}
