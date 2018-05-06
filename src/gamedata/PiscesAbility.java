package gamedata;

import com.badlogic.gdx.graphics.Texture;

public class PiscesAbility extends GameData {
	protected Texture image;
	
	public PiscesAbility(String name) {
		super(name);
		
		init();
	}

	public PiscesAbility(String name, int id) {
		super(name, id);
		
		init();
	}
	
	private void init() {
		this.image=null;
	}
}
