package gamedata.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PiscesItemWeapon extends PiscesItemEquipment {

	public PiscesItemWeapon(String name) {
		super(name);
	}
	
	public PiscesItemWeapon(String name, int id) {
		super(name, id);
	}
	
	public PiscesItemWeapon(String name, int id, TextureRegion mainImage, TextureRegion grayedImage) {
		super(name, id, mainImage, grayedImage);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemWeapon(this));
	}

}
