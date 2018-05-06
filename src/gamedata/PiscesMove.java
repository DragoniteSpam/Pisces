package gamedata;

import com.badlogic.gdx.graphics.Texture;

public class PiscesMove extends GameData {
	protected Texture image;
	
	public PiscesMove(String name) {
		super(name);
		
		init();
	}

	public PiscesMove(String name, int id) {
		super(name, id);
		
		init();
	}
	
	protected void init() {
		this.image=null;
	}
}
