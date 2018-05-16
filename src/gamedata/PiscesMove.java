package gamedata;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

import exceptions.ResourceNotFoundException;
import stuff.Element;

public class PiscesMove extends GameData {
	protected static HashMap<String, PiscesMove> all=new HashMap<String, PiscesMove>();
	
	public static final byte MAX_LEVEL=10;
	
	protected Texture image;
	protected int[] power;
	protected int[] cooldown;
	protected int[] range;
	protected Element element;
	protected boolean isPhysical;
	protected PiscesEffect effect;
	
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
		
		this.power=new int[MAX_LEVEL];
		this.cooldown=new int[MAX_LEVEL];
		this.range=new int[MAX_LEVEL];
		this.element=Element.NORMAL;
		this.isPhysical=true;
		this.effect=null;
		
		all.put(this.name, this);
	}
	
	public static PiscesMove getByName(String name) throws ResourceNotFoundException {
		if (all.containsKey(name)) {
			return all.get(name);
		}
		throw new ResourceNotFoundException("No PiscesSkillTree found with the name "+name);
	}
}
