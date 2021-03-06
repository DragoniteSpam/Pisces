package gamedata;

import java.util.ArrayList;
import java.util.HashMap;

import com.pisces.Settings;

import exceptions.ResourceNotFoundException;
import gamedata.abilities.PiscesAbility;
import gamedata.items.PiscesInstantiatedItemEquipment;
import stuff.EquipTypes;
import stuff.Stats;

public class PiscesCharacter extends GameData {
	private static HashMap<String, PiscesCharacter> all=new HashMap<String, PiscesCharacter>();
	
	public static final byte NUMBER_ACTIVE_MOVES=8;
	
	private PiscesInstantiatedItemEquipment[] equip;
	
	private PiscesInstantiatedMove[] activeMoves;
	private ArrayList<PiscesInstantiatedMove> totalMoves;
	private PiscesClass baseClass;
	private PiscesInstantiatedSkillTree[] skillTrees;
	private byte activeSkillTree;
	private int exp;
	private int level;
	private int sp;
	private int ap;
	private int hp;
	private ArrayList<PiscesEffect> effects;
	
	public PiscesCharacter(String name) {
		super(name);
		
		init();
	}

	public PiscesCharacter(String name, int id) {
		super(name, id);
		
		init();
	}
	
	public PiscesCharacter(String name, int id, PiscesClass baseClass) {
		super(name, id);
		
		init();
		
		setClass(baseClass);
		
		this.hp=getStat(Stats.HP);
	}
	
	private void init() {
		this.activeSkillTree=0;
		this.hp=0;
		this.exp=getEXPFromLevel(1);
		this.level=1;
		this.sp=0;
		this.ap=0;

		this.equip=new PiscesInstantiatedItemEquipment[EquipTypes.WEAPON.ordinal()];
		
		this.activeMoves=new PiscesInstantiatedMove[NUMBER_ACTIVE_MOVES];
		this.totalMoves=new ArrayList<PiscesInstantiatedMove>();
		this.skillTrees=new PiscesInstantiatedSkillTree[PiscesClass.NUMBER_SKILL_TREES];
		this.effects=new ArrayList<PiscesEffect>();
		
		all.put(this.name, this);
	}
	
	public int getStat(Stats stat) {
		double m=1;
		for (PiscesEffect effect : this.effects) {
			m=m+effect.getStatModifier(stat);
		}
		for (PiscesInstantiatedItemEquipment equip : this.equip) {
			if (equip!=null) {
				m=m+equip.getStatRating(stat);
			}
		}
		PiscesInstantiatedSkillTree baseTree=skillTrees[activeSkillTree];
		if (baseTree.getBaseTree().getBuffStat()==stat) {
			m=m+skillTrees[activeSkillTree].getMaxAbility().getEffectValue();
		}
		m=1+m/100;
		if (stat.getIsStatic()) {
			return (int)Math.max(baseClass.getStat(stat)*m, 1);
		}
		return (int)Math.max((Math.floor(2*baseClass.getStat(stat))+2.5*level)*m, 1);
	}
	
	private int recalculateLevel() {
		int level=0;
		while (getEXPFromLevel(level)<=exp) {
			level++;
		}
		this.level=level;
		return level;
	}
	
	private int getEXPFromLevel(int level) {
		return (int) (Settings.LEVEL_BASE*level+Settings.LEVEL_CURVE/2*(Math.pow(level, 2)+level));
	}

	private void setClass(PiscesClass baseClass) {
		this.baseClass=baseClass;
		for (int i=0; i<skillTrees.length; i++) {
			skillTrees[i]=new PiscesInstantiatedSkillTree(baseClass.getSkillTree(i));
		}
	}
	
	public PiscesClass getBaseClass() {
		return this.baseClass;
	}
	
	public void addEXP(int amount) {
		this.exp=this.exp+amount;
		recalculateLevel();
	}
	
	public int getEXP() {
		return this.exp;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getSP() {
		return this.sp;
	}
	
	public int getAP() {
		return this.ap;
	}
	
	public void addHP(int amount) {
		hp=Math.min(hp+amount, baseClass.getStat(Stats.HP));
	}
	
	public void removeHP(int amount) {
		hp=Math.max(hp-amount,  0);
	}
	
	public int getHP() {
		return this.hp;
	}
	
	public PiscesInstantiatedMove knowsMove(PiscesMove move) {
		for (PiscesInstantiatedMove m : totalMoves) {
			if (m.getBaseMove()==move) {
				return m;
			}
		}
		return null;
	}
	
	public static PiscesCharacter getByName(String name) throws ResourceNotFoundException {
		if (all.containsKey(name)) {
			return all.get(name);
		}
		throw new ResourceNotFoundException("No PiscesCharacter found with the name "+name);
	}
	
	public static void createCharacters() throws ResourceNotFoundException {
		new PiscesCharacter("Dragonite", -1, PiscesClass.getByName("Wizard"));
	}
}
