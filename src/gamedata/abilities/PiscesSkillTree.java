package gamedata.abilities;

import java.util.HashMap;

import exceptions.ResourceNotFoundException;
import gamedata.GameData;
import stuff.Stats;

public class PiscesSkillTree extends GameData {
	private static HashMap<String, PiscesSkillTree> all=new HashMap<String, PiscesSkillTree>();
	public static final byte MAX_ABILITIES=5;
	
	private Stats buffStat;
	private String displayName;
	
	private PiscesAbility[] abilities;
	
	public PiscesSkillTree(String name) {
		super(name);
		init();
	}
	
	public PiscesSkillTree(String name, int id) {
		super(name, id);
		init();
	}
	
	public PiscesSkillTree(String name, String displayName, int id, Stats buffStat) {
		super(name, id);
		init();
		this.displayName=displayName;
		this.buffStat=buffStat;
	}
	
	private void init() {
		abilities=new PiscesAbility[MAX_ABILITIES];
		
		all.put(this.name, this);
	}
	
	public PiscesAbility[] getAbilities() {
		return this.abilities;
	}
	
	public void setAbility(int n, PiscesAbility ability) {
		this.abilities[n]=ability;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public Stats getBuffStat() {
		return this.buffStat;
	}
	
	public static PiscesSkillTree getByName(String name) throws ResourceNotFoundException {
		if (all.containsKey(name)) {
			return all.get(name);
		}
		throw new ResourceNotFoundException("No PiscesSkillTree found with the name "+name);
	}
	
	public static void createSkillTrees() {
		new PiscesSkillTree("Intuition", "Intuition", -1, Stats.AGL);
	}
}
