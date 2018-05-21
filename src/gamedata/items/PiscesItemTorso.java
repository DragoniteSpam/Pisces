package gamedata.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PiscesItemTorso extends PiscesItemEquipment {
	public PiscesItemTorso(String name) {
		super(name);
	}
	
	public PiscesItemTorso(String name, int id) {
		super(name, id);
	}
	
	public PiscesItemTorso(String name, int id, TextureRegion mainImage, TextureRegion grayedImage) {
		super(name, id, mainImage, grayedImage);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemTorso(this));
	}
}
