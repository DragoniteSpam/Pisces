package gamedata;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

import exceptions.ResourceNotFoundException;
import stuff.Element;
import stuff.Stats;

public class PiscesEffect extends GameData {
	private static HashMap<String, PiscesEffect> all=new HashMap<String, PiscesEffect>();
	
	protected Texture image;
	protected int duration;
	protected boolean canMove;
	protected boolean canAttack;
	protected boolean canTakeAttack;
	protected int hps=0;
	protected int[] statModifiers;
	protected int[] elementalDamage;
	protected int[] elementalResist;
	
	public PiscesEffect(String name) {
		super(name);
		
		init();
	}
	
	public PiscesEffect(String name, int id) {
		super(name, id);
		
		init();
	}
	
	public PiscesEffect(String name, int id, Texture image, int duration, boolean canMove, boolean canAttack, boolean canTakeAttack, int hps) {
		super(name, id);
		
		init();
		
		this.image=image;
		this.duration=duration;
		this.canMove=canMove;
		this.canAttack=canAttack;
		this.canTakeAttack=canTakeAttack;
		this.hps=hps;
	}
	
	private void init() {
		this.image=null;
		this.duration=15;
		this.canMove=true;
		this.canAttack=true;
		this.canTakeAttack=true;
		this.hps=0;
		this.statModifiers=new int[Stats.LCK.ordinal()+1];
		this.elementalDamage=new int[Element.EL15.ordinal()+1];
		this.elementalResist=new int[Element.EL15.ordinal()+1];
		
		all.put(this.name, this);
	}
	
	public Texture getImage() {
		return this.image;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public boolean getCanMove() {
		return this.canMove;
	}
	
	public boolean getCanAttack() {
		return this.canAttack;
	}
	
	public boolean getCanTakeAttack() {
		return this.canTakeAttack;
	}
	
	public int getHPS() {
		return this.hps;
	}
	
	public int getStatModifier(Stats stat) {
		return this.statModifiers[stat.ordinal()];
	}
	
	public int getElementalDamage(Element element) {
		return this.elementalDamage[element.ordinal()];
	}
	
	public int getElementalResist(Element element) {
		return this.elementalResist[element.ordinal()];
	}
	
	public static PiscesEffect getByName(String name) throws ResourceNotFoundException {
		if (all.containsKey(name)) {
			return all.get(name);
		}
		throw new ResourceNotFoundException("No PiscesSkillTree found with the name "+name);
	}
}
