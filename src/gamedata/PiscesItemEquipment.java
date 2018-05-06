package gamedata;

import java.util.HashMap;

public class PiscesItemEquipment extends PiscesItem {
	protected int ratingAgility;
	protected int ratingHP;
	protected int ratingAttack;
	protected int ratingSpAttack;
	protected int ratingDefense;
	protected int ratingSpDefense;
	protected int ratingCritical;
	protected int advanceEXP;
	protected int advanceSP;
	protected int advanceAP;
	protected HashMap<Integer, Double> elementDamage;
	protected HashMap<Integer, Double> elementResist;
	protected byte weight;
	protected byte slots;
	protected byte rarity;
	protected int price;

	public PiscesItemEquipment(String name) {
		super(name);
		
		init();
	}

	public PiscesItemEquipment(String name, int id) {
		super(name, id);
		
		init();
	}
	
	private void init() {
		setRatings(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		this.elementDamage=new HashMap<Integer, Double>();
		this.elementResist=new HashMap<Integer, Double>();
		this.weight=0;
		this.slots=0;
		this.rarity=0;
		this.price=0;
	}
	
	public void setRatings(int agility, int hp, int attack, int spAttack, int defense, int spDefense, int critical, int exp, int sp, int ap) {
		this.ratingAgility=agility;
		this.ratingHP=hp;
		this.ratingAttack=attack;
		this.ratingSpAttack=spAttack;
		this.ratingDefense=defense;
		this.ratingSpDefense=spDefense;
		this.ratingCritical=critical;
		this.advanceEXP=exp;
		this.advanceSP=sp;
		this.advanceAP=ap;
	}
	
	public int getRatingAgility() {
		return this.ratingAgility;
	}
	
	public int getRatingHP() {
		return this.ratingHP;
	}
	
	public int getRatingAttack() {
		return this.ratingAttack;
	}
	
	public int getRatingSpAttack() {
		return this.ratingSpAttack;
	}
	
	public int getRatingDefense() {
		return this.ratingDefense;
	}
	
	public int getRatingSpDefense() {
		return this.ratingSpDefense;
	}
	
	public int getRatingCritical() {
		return this.ratingCritical;
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
}
