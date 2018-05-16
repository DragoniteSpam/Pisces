package gamedata.items;

import stuff.Element;
import stuff.Stats;

public class PiscesItemAugment extends PiscesItem {
	protected int[] rating;
	protected int advanceEXP;
	protected int advanceSP;
	protected int advanceAP;
	protected double[] elementalDamage;
	protected double[] elementalResist;
	protected byte weight;
	protected byte slots;
	protected byte rarity;
	protected int price;

	public PiscesItemAugment(String name) {
		super(name);
		
		init();
	}

	public PiscesItemAugment(String name, int id) {
		super(name, id);
		
		init();
	}
	
	private void init() {
		this.rating=new int[Stats.LCK.ordinal()];
		setRatings(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		this.elementalDamage=new double[Element.EL15.ordinal()];
		this.elementalResist=new double[Element.EL15.ordinal()];
		this.weight=0;
		this.slots=0;
		this.rarity=0;
		this.price=0;
	}
	
	public void setRatings(int hp, int atk, int def, int spa, int spd, int agl, int blk, int crt, int lck, int exp, int sp, int ap) {
		this.rating[Stats.HP.ordinal()]=hp;
		this.rating[Stats.ATK.ordinal()]=atk;
		this.rating[Stats.DEF.ordinal()]=def;
		this.rating[Stats.SPA.ordinal()]=spa;
		this.rating[Stats.SPD.ordinal()]=spd;
		this.rating[Stats.AGL.ordinal()]=agl;
		this.rating[Stats.BLK.ordinal()]=blk;
		this.rating[Stats.CRT.ordinal()]=crt;
		this.rating[Stats.LCK.ordinal()]=lck;
		
		this.advanceEXP=exp;
		this.advanceSP=sp;
		this.advanceAP=ap;
	}
	
	public int getRating(Stats stat) {
		return this.rating[stat.ordinal()];
	}
	
	public int getAdvanceEXP() {
		return this.advanceEXP;
	}
	
	public int getAdvanceAP() {
		return this.advanceAP;
	}
	
	public int getAdvanceSP() {
		return this.advanceSP;
	}
	
	public void setWeight(byte weight) {
		this.weight=weight;
	}
	
	public byte getWeight() {
		return weight;
	}
	
	public void setSlots(byte slots) {
		this.slots=slots;
	}
	
	public byte getSlots() {
		return slots;
	}
	
	public void setRarity(byte rarity) {
		this.rarity=rarity;
	}
	
	public byte getRarity() {
		return rarity;
	}
	
	public void setPrice(int price) {
		this.price=price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setElementalDamage(Element element, double rating) {
		this.elementalDamage[element.ordinal()]=rating;
	}
	
	public double getElementalDamage(Element element) {
		return this.elementalDamage[element.ordinal()];
	}
	
	public void setElementalResist(Element element, double rating) {
		this.elementalResist[element.ordinal()]=rating;
	}
	
	public double getElementalResist(Element element) {
		return this.elementalResist[element.ordinal()];
	}
}
