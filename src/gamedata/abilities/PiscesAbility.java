package gamedata.abilities;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

import exceptions.ResourceNotFoundException;
import gamedata.GameData;

public class PiscesAbility extends GameData {
	private static HashMap<String, PiscesAbility> all=new HashMap<String, PiscesAbility>();
	protected PiscesAbilityShape shape;
	protected boolean linkable;
	protected int cost;
	protected String description;
	protected int effectValue;
	
	public PiscesAbility(String name) {
		super(name);
		
		init();
	}

	public PiscesAbility(String name, int id) {
		super(name, id);
		
		init();
	}
	
	public PiscesAbility(String name, int id, PiscesAbilityShape shape, boolean linkable, int cost, int effectValue, String description) {
		super(name, id);
		
		init();
		
		this.shape=shape;
		this.linkable=linkable;
		this.cost=cost;
		this.effectValue=effectValue;
		this.description=description;
	}
	
	private void init() {
		this.shape=null;
		this.linkable=true;
		this.cost=1;
		this.effectValue=5;
		this.description=null;
		all.put(this.name, this);
	}
	
	public PiscesAbilityShape getShape() {
		return this.shape;
	}
	
	public boolean getLinkable() {
		return this.linkable;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public int getEffectValue() {
		return this.effectValue;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static PiscesAbility getByName(String name) throws ResourceNotFoundException {
		if (all.containsKey(name)) {
			return all.get(name);
		}
		throw new ResourceNotFoundException("No PiscesAbility found with the name "+name);
	}
	
	public static void createAbilities() throws ResourceNotFoundException {
		// Shulk Intuition
		new PiscesAbility("Battle Cry", -1, PiscesAbilityShape.getByName("Square"), true, 13, 5, "Increases TP gained from Battle Start Affinity by 20.");
		new PiscesAbility("Underdog", -1, PiscesAbilityShape.getByName("Circle"), true, 2, 5, "Agility Up buff (15%) at start of battle with higher level enemies.");
		new PiscesAbility("Equipment Master", -1, PiscesAbilityShape.getByName("Square"), true, 5, 5, "Reduced weight of equipment by 5.");
		new PiscesAbility("Fever Pitch", -1, PiscesAbilityShape.getByName("Star"), true, 18, 5, "Increases chance of Fever when gem crafting.");
		new PiscesAbility("Element of Surprise", -1, PiscesAbilityShape.getByName("Square"), true, 20, 5, "Improves Arts with Bonus Effects from behind");
	}
}
