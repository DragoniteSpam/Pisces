package gamedata;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PiscesItemPocket {
	private static PiscesItemPocket[] all=new PiscesItemPocket[ItemPockets.KEY.ordinal()+1];
	private String name;
	private Sprite sprite;
	private Sound sound;
	
	public PiscesItemPocket(String name, Sprite sprite, Sound sound) {
		this.name=name;
		this.sprite=sprite;
		this.sound=sound;
	}
	
	public String getName() {
		return name;
	}
	
	public static void createItemPockets() {
		all[ItemPockets.WEAPON.ordinal()]=new PiscesItemPocket("Weapons", null, null);
		all[ItemPockets.HAT.ordinal()]=new PiscesItemPocket("Hats", null, null);
		all[ItemPockets.ARMS.ordinal()]=new PiscesItemPocket("Arms", null, null);
		all[ItemPockets.TORSO.ordinal()]=new PiscesItemPocket("Torso", null, null);
		all[ItemPockets.PANTS.ordinal()]=new PiscesItemPocket("Pants", null, null);
		all[ItemPockets.SHOES.ordinal()]=new PiscesItemPocket("Shoes", null, null);
		all[ItemPockets.AUGMENT.ordinal()]=new PiscesItemPocket("Augments", null, null);
		all[ItemPockets.COMPONENT.ordinal()]=new PiscesItemPocket("Components", null, null);
		all[ItemPockets.KEY.ordinal()]=new PiscesItemPocket("Keys", null, null);
	}
}
