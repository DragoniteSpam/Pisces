package gamedata.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PiscesItemPants extends PiscesItemEquipment {
	public PiscesItemPants(String name) {
		super(name);
	}
	
	public PiscesItemPants(String name, int id) {
		super(name, id);
	}
	
	public PiscesItemPants(String name, int id, TextureRegion mainImage, TextureRegion grayedImage) {
		super(name, id, mainImage, grayedImage);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemPants(this));
	}
}
