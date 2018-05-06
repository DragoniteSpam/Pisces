package gamedata;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class PiscesElement extends GameData {
	protected Color color;
	protected Texture image;
	
	public PiscesElement(String name) {
		super(name);
		
		init();
	}

	public PiscesElement(String name, int id) {
		super(name, id);
		
		init();
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
