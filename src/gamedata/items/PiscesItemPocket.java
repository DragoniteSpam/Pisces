package gamedata.items;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import exceptions.ResourceNotFoundException;
import stuff.ItemPockets;

public class PiscesItemPocket {
	private static PiscesItemPocket[] all = new PiscesItemPocket[ItemPockets.KEY.ordinal() + 1];
	private String name;
	private TextureRegion image;
	private TextureRegion imageGrayed;
	private Sound sound;

	public PiscesItemPocket(String name, TextureRegion image, TextureRegion imageGrayed, Sound sound) {
		this.name = name;
		this.image = image;
		this.imageGrayed = imageGrayed;
		this.sound = sound;
	}

	public String getName() {
		return name;
	}

	public static void createItemPockets(Texture overlayTexture) {
		all[ItemPockets.WEAPON.ordinal()] = new PiscesItemPocket("Weapons",
				new TextureRegion(overlayTexture, 0, 768, 64, 64), new TextureRegion(overlayTexture, 64, 768, 64, 64),
				null);
		all[ItemPockets.HAT.ordinal()] = new PiscesItemPocket("Hats", new TextureRegion(overlayTexture, 0, 832, 64, 64),
				new TextureRegion(overlayTexture, 64, 832, 64, 64), null);
		all[ItemPockets.TORSO.ordinal()] = new PiscesItemPocket("Torso",
				new TextureRegion(overlayTexture, 0, 896, 64, 64), new TextureRegion(overlayTexture, 64, 896, 64, 64),
				null);
		all[ItemPockets.ARMS.ordinal()] = new PiscesItemPocket("Arms",
				new TextureRegion(overlayTexture, 128, 768, 64, 64),
				new TextureRegion(overlayTexture, 192, 768, 64, 64), null);
		all[ItemPockets.PANTS.ordinal()] = new PiscesItemPocket("Pants",
				new TextureRegion(overlayTexture, 128, 832, 64, 64),
				new TextureRegion(overlayTexture, 192, 832, 64, 64), null);
		all[ItemPockets.SHOES.ordinal()] = new PiscesItemPocket("Shoes",
				new TextureRegion(overlayTexture, 128, 896, 64, 64),
				new TextureRegion(overlayTexture, 192, 896, 64, 64), null);
		all[ItemPockets.AUGMENT.ordinal()] = new PiscesItemPocket("Augments",
				new TextureRegion(overlayTexture, 256, 768, 64, 64),
				new TextureRegion(overlayTexture, 320, 768, 64, 64), null);
		all[ItemPockets.COLLECTABLE.ordinal()] = new PiscesItemPocket("Collectables",
				new TextureRegion(overlayTexture, 256, 832, 64, 64),
				new TextureRegion(overlayTexture, 320, 832, 64, 64), null);
		all[ItemPockets.COMPONENT.ordinal()] = new PiscesItemPocket("Components",
				new TextureRegion(overlayTexture, 256, 896, 64, 64),
				new TextureRegion(overlayTexture, 320, 896, 64, 64), null);
		all[ItemPockets.MANUAL.ordinal()] = new PiscesItemPocket("Manuals",
				new TextureRegion(overlayTexture, 384, 768, 64, 64),
				new TextureRegion(overlayTexture, 448, 768, 64, 64), null);
		all[ItemPockets.KEY.ordinal()] = new PiscesItemPocket("Keys",
				new TextureRegion(overlayTexture, 384, 832, 64, 64),
				new TextureRegion(overlayTexture, 448, 832, 64, 64), null);
		all[ItemPockets.MISC.ordinal()] = new PiscesItemPocket("Misc.",
				new TextureRegion(overlayTexture, 384, 896, 64, 64),
				new TextureRegion(overlayTexture, 448, 896, 64, 64), null);
	}

	public static PiscesItemPocket[] getAll() {
		return all;
	}

	public TextureRegion getImage() {
		return this.image;
	}

	public TextureRegion getGrayedImage() {
		return this.imageGrayed;
	}

	public Sound getSound() {
		return this.sound;
	}
}
