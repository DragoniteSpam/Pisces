package gamedata;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import stuff.Element;

public class PiscesElement extends GameData {
	protected static PiscesElement[] all=new PiscesElement[Element.EL15.ordinal()+1];
	protected Color color;
	protected Element id;
	protected TextureRegion image;
	
	public PiscesElement(String name) {
		super(name);
		
		init();
	}

	public PiscesElement(String name, int id) {
		super(name, id);
		
		init();
	}
	
	public PiscesElement(String name, int id, Element element, Color color, TextureRegion image) {
		super(name, id);
		init();
		this.id=element;
		this.color=color;
		this.image=image;
	}
	
	private void init() {
		this.color=Color.RED;
		this.image=null;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color=color;
	}
	
	public TextureRegion getImage() {
		return image;
	}
	
	public static void createAllElements(Texture baseTexture) {
		all[Element.NORMAL.ordinal()]=new PiscesElement("Normal", -1, Element.NORMAL, Color.LIGHT_GRAY, new TextureRegion(baseTexture, 1280, 896, 64, 64));
		all[Element.FIRE.ordinal()]=new PiscesElement("Fire", -1, Element.FIRE, Color.ORANGE, new TextureRegion(baseTexture, 1344, 896, 64, 64));
		all[Element.WATER.ordinal()]=new PiscesElement("Water", -1, Element.WATER, Color.BLUE, new TextureRegion(baseTexture, 1408, 896, 64, 64));
		all[Element.ELECTRIC.ordinal()]=new PiscesElement("Electric", -1, Element.ELECTRIC, Color.YELLOW, new TextureRegion(baseTexture, 1472, 896, 64, 64));
		all[Element.LIGHT.ordinal()]=new PiscesElement("Light", -1, Element.LIGHT, Color.WHITE, new TextureRegion(baseTexture, 1536, 896, 64, 64));
		all[Element.DARK.ordinal()]=new PiscesElement("Dark", -1, Element.DARK, Color.DARK_GRAY, new TextureRegion(baseTexture, 1600, 896, 64, 64));
		all[Element.EL06.ordinal()]=new PiscesElement("EL06", -1, Element.EL06, Color.LIGHT_GRAY, new TextureRegion(baseTexture, 1664, 896, 64, 64));
		all[Element.EL07.ordinal()]=new PiscesElement("El07", -1, Element.EL07, Color.LIGHT_GRAY, new TextureRegion(baseTexture, 1728, 896, 64, 64));
		all[Element.EL08.ordinal()]=new PiscesElement("EL08", -1, Element.EL08, Color.LIGHT_GRAY, new TextureRegion(baseTexture, 1792, 896, 64, 64));
		all[Element.EL09.ordinal()]=new PiscesElement("EL09", -1, Element.EL09, Color.LIGHT_GRAY, new TextureRegion(baseTexture, 1856, 896, 64, 64));
		all[Element.EL10.ordinal()]=new PiscesElement("EL10", -1, Element.EL10, Color.LIGHT_GRAY, new TextureRegion(baseTexture, 1920, 896, 64, 64));
		all[Element.EL11.ordinal()]=new PiscesElement("EL11", -1, Element.EL11, Color.LIGHT_GRAY, new TextureRegion(baseTexture, 1984, 896, 64, 64));
		all[Element.EL12.ordinal()]=new PiscesElement("EL12", -1, Element.EL12, Color.LIGHT_GRAY, new TextureRegion(baseTexture, 2048, 896, 64, 64));
		all[Element.EL13.ordinal()]=new PiscesElement("EL13", -1, Element.EL13, Color.LIGHT_GRAY, new TextureRegion(baseTexture, 2112, 896, 64, 64));
		all[Element.EL14.ordinal()]=new PiscesElement("EL14", -1, Element.EL14, Color.LIGHT_GRAY, new TextureRegion(baseTexture, 2176, 896, 64, 64));
		all[Element.EL15.ordinal()]=new PiscesElement("EL15", -1, Element.EL15, Color.LIGHT_GRAY, new TextureRegion(baseTexture, 2240, 896, 64, 64));
	}

	public static PiscesElement getByIndex(int index) {
		return all[index];
	}
}
