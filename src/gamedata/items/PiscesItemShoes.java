package gamedata.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PiscesItemShoes extends PiscesItemEquipment {
	public PiscesItemShoes(String name) {
		super(name);
	}
	
	public PiscesItemShoes(String name, int id) {
		super(name, id);
	}
	
	public PiscesItemShoes(String name, int id, TextureRegion mainImage, TextureRegion grayedImage) {
		super(name, id, mainImage, grayedImage);
	}
	
	public void addMe(Inventory inventory) {
		inventory.addItem(new PiscesInstantiatedItemShoes(this));
	}
}
