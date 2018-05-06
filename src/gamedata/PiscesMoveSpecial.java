package gamedata;

import com.badlogic.gdx.graphics.Texture;

public class PiscesMoveSpecial extends PiscesMove {
	protected Texture image;
	
	public PiscesMoveSpecial(String name) {
		super(name);
		
		init();
	}

	public PiscesMoveSpecial(String name, int id) {
		super(name, id);
		
		init();
	}
}

