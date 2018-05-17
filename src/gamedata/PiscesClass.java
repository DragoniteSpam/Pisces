package gamedata;

import java.util.HashMap;

import exceptions.ResourceNotFoundException;
import gamedata.abilities.PiscesSkillTree;
import stuff.Stats;

public class PiscesClass extends GameData {
	private static HashMap<String, PiscesClass> all=new HashMap<String, PiscesClass>();
	
	public static final int NUMBER_SKILL_TREES=3;
	
	private double moveSpeed;
	private double jumpHeight;
	
	private int[] stats;
	private PiscesSkillTree[] skillTrees;
	
	public PiscesClass(String name) {
		super(name);
		init();
	}
	
	public PiscesClass(String name, int id) {
		super(name, id);
		init();
	}
	
	public PiscesClass(String name, int id, int hp, int atk, int def, int spa, int spd, int agl, int blk, int crt, int lck, double moveSpeed, double jumpHeight) {
		super(name, id);
		init();
		
		this.moveSpeed=moveSpeed;
		this.jumpHeight=jumpHeight;
		setStats(hp, atk, def, spa, spd, agl, blk, crt, lck);
	}
	
	private void init() {
		moveSpeed=1;
		jumpHeight=1;
		
		stats=new int[Stats.LCK.ordinal()+1];
		stats[Stats.HP.ordinal()]=50;
		stats[Stats.ATK.ordinal()]=50;
		stats[Stats.DEF.ordinal()]=50;
		stats[Stats.SPA.ordinal()]=50;
		stats[Stats.SPD.ordinal()]=50;
		stats[Stats.AGL.ordinal()]=50;
		stats[Stats.BLK.ordinal()]=50;
		stats[Stats.CRT.ordinal()]=50;
		stats[Stats.LCK.ordinal()]=50;
		
		skillTrees=new PiscesSkillTree[NUMBER_SKILL_TREES];
		
		all.put(this.name, this);
	}
	
	public int getStat(Stats stat) {
		return this.stats[stat.ordinal()];
	}
	
	private void setStats(int hp, int atk, int def, int spa, int spd, int agl, int blk, int crt, int lck) {
		this.stats[Stats.HP.ordinal()]=hp;
		this.stats[Stats.ATK.ordinal()]=atk;
		this.stats[Stats.DEF.ordinal()]=def;
		this.stats[Stats.SPA.ordinal()]=spa;
		this.stats[Stats.SPD.ordinal()]=spd;
		this.stats[Stats.AGL.ordinal()]=agl;
		this.stats[Stats.BLK.ordinal()]=blk;
		this.stats[Stats.CRT.ordinal()]=crt;
		this.stats[Stats.LCK.ordinal()]=lck;
	}
	
	public double getJumpHeight() {
		return this.jumpHeight;
	}
	
	public double getMoveSpeed() {
		return this.moveSpeed;
	}
	
	public void setSkillTree(int n, PiscesSkillTree tree) {
		this.skillTrees[n]=tree;
	}
	
	public PiscesSkillTree getSkillTree(int n) {
		return this.skillTrees[n];
	}
	
	public static void createClasses() throws ResourceNotFoundException {
		int stat1=100;
		int stat2=90;
		int stat3=80;
		int stat4=70;
		int stat5=60;
		int stat6=50;
		int stat7=40;
		int cb1=10;
		int cb2=5;
		PiscesClass active=new PiscesClass("Warrior", -1, stat2, stat1, stat2, stat7, stat5, stat4, cb1, cb2, stat6, 0.75, 0.75);
		active.setSkillTree(0,  PiscesSkillTree.getByName("Intuition"));
		active=new PiscesClass("Wizard", -1, stat3, stat7, stat6, stat1, stat2, stat5, cb2, cb1, stat4, 1, 1);
		active.setSkillTree(0,  PiscesSkillTree.getByName("Intuition"));
		active=new PiscesClass("Healer", -1, stat2, stat6, stat5, stat4, stat1, stat3, cb2, cb1, stat7, 0.9, 1);
		active.setSkillTree(0,  PiscesSkillTree.getByName("Intuition"));
	}

	public static PiscesClass getByName(String name) throws ResourceNotFoundException {
		if (all.containsKey(name)) {
			return all.get(name);
		}
		throw new ResourceNotFoundException("No PiscesClass found with the name "+name);
	}
}
