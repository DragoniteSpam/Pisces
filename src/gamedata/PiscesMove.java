package gamedata;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import exceptions.ResourceNotFoundException;
import stuff.Element;

public class PiscesMove extends GameData {
	protected static HashMap<String, PiscesMove> all = new HashMap<String, PiscesMove>();

	public static final byte MAX_LEVEL = 10;

	protected TextureRegion imageAvailable;
	protected TextureRegion imageUnavailable;
	protected int[] power;
	protected double[] cooldown;
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

	public PiscesMove(String name, int id, TextureRegion imageAvailable, TextureRegion imageUnavailable, int[] power,
			double[] cooldown, int[] range, Element element, boolean isPhysical, PiscesEffect effect) {
		super(name, id);

		init();
		
		this.imageAvailable=imageAvailable;
		this.imageUnavailable=imageUnavailable;
		this.power=power;
		this.cooldown=cooldown;
		this.range=range;
		this.element=element;
		this.isPhysical=isPhysical;
		this.effect=effect;

	}

	protected void init() {
		this.imageAvailable = null;
		this.imageUnavailable = null;

		this.power = new int[MAX_LEVEL];
		this.cooldown = new double[MAX_LEVEL];
		this.range = new int[MAX_LEVEL];
		this.element = Element.NORMAL;
		this.isPhysical = true;
		this.effect = null;

		all.put(this.name, this);
	}

	public static PiscesMove getByName(String name) throws ResourceNotFoundException {
		if (all.containsKey(name)) {
			return all.get(name);
		}
		throw new ResourceNotFoundException("No PiscesSkillTree found with the name " + name);
	}
	
	public int getPower(int level) {
		return this.power[level];
	}
	
	public double getCooldown(int level) {
		return this.cooldown[level];
	}
	
	public int getRange(int level) {
		return this.range[level];
	}
}
