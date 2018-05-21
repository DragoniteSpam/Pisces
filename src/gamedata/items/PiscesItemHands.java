package gamedata.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PiscesItemHands extends PiscesItemEquipment {
	public PiscesItemHands(String name) {
		super(name);
	}
	
	public PiscesItemHands(String name, int id) {
		super(name, id);
	}
	
	public PiscesItemHands(String name, int id, TextureRegion mainImage, TextureRegion grayedImage) {
		super(name, id, mainImage, grayedImage);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemHands(this));
	}
}
