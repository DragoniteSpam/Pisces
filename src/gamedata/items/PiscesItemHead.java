package gamedata.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PiscesItemHead extends PiscesItemEquipment {
	public PiscesItemHead(String name) {
		super(name);
	}
	
	public PiscesItemHead(String name, int id) {
		super(name, id);
	}
	
	public PiscesItemHead(String name, int id, TextureRegion mainImage, TextureRegion grayedImage) {
		super(name, id, mainImage, grayedImage);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemHead(this));
	}
}
