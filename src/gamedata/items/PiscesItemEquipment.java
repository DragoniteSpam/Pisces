package gamedata.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import stuff.Element;
import stuff.Stats;

public class PiscesItemEquipment extends PiscesItem implements PiscesItemWithStats {
	protected int[] rating;
	protected int advanceEXP;
	protected int advanceSP;
	protected int advanceAP;
	protected double[] elementalDamage;
	protected double[] elementalResist;
	protected byte weight;
	protected byte slots;
	protected byte rarity;

	public PiscesItemEquipment(String name) {
		super(name);
		
		init();
	}

	public PiscesItemEquipment(String name, int id) {
		super(name, id);
		
		init();
	}
	
	public PiscesItemEquipment(String name, int id, TextureRegion image, TextureRegion grayedImage) {
		super(name, id, image, grayedImage);
		
		init();
	}
	
	private void init() {
		this.rating=new int[Stats.LCK.ordinal()+1];
		setRatings(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		this.elementalDamage=new double[Element.EL15.ordinal()+1];
		this.elementalResist=new double[Element.EL15.ordinal()+1];
		this.weight=0;
		this.slots=0;
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
	
	public int getSlots() {
		return slots;
	}
	
	public void setRarity(byte rarity) {
		this.rarity=rarity;
	}
	
	public byte getRarity() {
		return rarity;
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
