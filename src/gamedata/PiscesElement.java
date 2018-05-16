package gamedata;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import stuff.Element;

public class PiscesElement extends GameData {
	protected Color color;
	protected Texture image;
	protected Element id;
	
	public PiscesElement(String name) {
		super(name);
		
		init();
	}

	public PiscesElement(String name, int id) {
		super(name, id);
		
		init();
	}
	
	public PiscesElement(String name, int id, Element element) {
		super(name, id);
		init();
		this.id=element;
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
	
	public Texture getImage() {
		return image;
	}
	
	public void setImage(Texture image) {
		this.image=image;
	}

}
