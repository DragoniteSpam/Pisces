package gamedata.items;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;

import pause.PauseScreenInventory;
import stuff.ItemPockets;

public class Inventory {
	private ArrayList<PiscesInstantiatedItemWeapon> weapons;
	private ArrayList<PiscesInstantiatedItemHead> head;
	private ArrayList<PiscesInstantiatedItemTorso> torso;
	private ArrayList<PiscesInstantiatedItemHands> hands;
	private ArrayList<PiscesInstantiatedItemPants> pants;
	private ArrayList<PiscesInstantiatedItemShoes> shoes;
	private ArrayList<PiscesInstantiatedItemAugment> augments;
	private ArrayList<PiscesInstantiatedItemCollectable> collectables;
	private ArrayList<PiscesInstantiatedItemComponent> components;
	private ArrayList<PiscesInstantiatedItemManual> manuals;
	private ArrayList<PiscesInstantiatedItemMisc> misc;
	private ArrayList<PiscesInstantiatedItemKey> keys;

	public Inventory() {
		weapons=new ArrayList<PiscesInstantiatedItemWeapon>();
		head=new ArrayList<PiscesInstantiatedItemHead>();
		torso=new ArrayList<PiscesInstantiatedItemTorso>();
		hands=new ArrayList<PiscesInstantiatedItemHands>();
		pants=new ArrayList<PiscesInstantiatedItemPants>();
		shoes=new ArrayList<PiscesInstantiatedItemShoes>();
		augments=new ArrayList<PiscesInstantiatedItemAugment>();
		
		collectables=new ArrayList<PiscesInstantiatedItemCollectable>();
		components=new ArrayList<PiscesInstantiatedItemComponent>();
		manuals=new ArrayList<PiscesInstantiatedItemManual>();
		misc=new ArrayList<PiscesInstantiatedItemMisc>();
		keys=new ArrayList<PiscesInstantiatedItemKey>();
	}

	public void addItem(PiscesInstantiatedItemWeapon item) {
		weapons.add(item);
	}
	
	public void addItem(PiscesInstantiatedItemHead item) {
		head.add(item);
	}
	
	public void addItem(PiscesInstantiatedItemHands item) {
		hands.add(item);
	}
	
	public void addItem(PiscesInstantiatedItemTorso item) {
		torso.add(item);
	}
	
	public void addItem(PiscesInstantiatedItemPants item) {
		pants.add(item);
	}
	
	public void addItem(PiscesInstantiatedItemShoes item) {
		shoes.add(item);
	}
	
	public void addItem(PiscesInstantiatedItemComponent item) {
		components.add(item);
	}
	
	public void addItem(PiscesInstantiatedItemCollectable item) {
		collectables.add(item);
	}
	
	public void addItem(PiscesInstantiatedItemAugment item) {
		augments.add(item);
	}
	
	public void addItem(PiscesInstantiatedItemManual item) {
		manuals.add(item);
	}
	
	public void addItem(PiscesInstantiatedItemMisc item) {
		misc.add(item);
	}
	
	public void addItem(PiscesInstantiatedItemKey item) {
		keys.add(item);
	}
	
	public void addItem(PiscesItem item) {
		// the lengths i go to (to) avoid "instanceof"
		item.addMe(this);
	}
	
	public int size(ItemPockets pocket) {
		switch (pocket) {
		case WEAPON:
			return weapons.size();
		case HAT:
			return head.size();
		case TORSO:
			return torso.size();
		case ARMS:
			return hands.size();
		case PANTS:
			return pants.size();
		case SHOES:
			return shoes.size();
		case AUGMENT:
			return augments.size();
		case COLLECTABLE:
			return collectables.size();
		case MANUAL:
			return manuals.size();
		case MISC:
			return misc.size();
		case KEY:
			return keys.size();
		case COMPONENT:
			return components.size();
		default:
			break;
		}
		// The Java compiler isn't quite smart enough for switch statements.
		return 0;
	}
	
	public void clear(ItemPockets pocket) {
		switch (pocket) {
		case WEAPON:
			weapons.clear();
			break;
		case HAT:
			head.clear();
			break;
		case TORSO:
			torso.clear();
			break;
		case ARMS:
			hands.clear();
			break;
		case PANTS:
			pants.clear();
			break;
		case SHOES:
			shoes.clear();
			break;
		case AUGMENT:
			augments.clear();
			break;
		case COLLECTABLE:
			collectables.clear();
			break;
		case MANUAL:
			manuals.clear();
			break;
		case MISC:
			misc.clear();
			break;
		case KEY:
			keys.clear();
			break;
		case COMPONENT:
			components.clear();
			break;
		default:
			break;
		}
	}
	
	public ArrayList<PiscesInstantiatedItemWeapon> getWeapons(){
		return this.weapons;
	}
	
	public ArrayList<PiscesInstantiatedItemHead> getHead(){
		return this.head;
	}
	
	public ArrayList<PiscesInstantiatedItemHands> getHands(){
		return this.hands;
	}
	
	public ArrayList<PiscesInstantiatedItemTorso> getTorso(){
		return this.torso;
	}
	
	public ArrayList<PiscesInstantiatedItemPants> getPants(){
		return this.pants;
	}
	
	public ArrayList<PiscesInstantiatedItemShoes> getShoes(){
		return this.shoes;
	}
	
	public ArrayList<PiscesInstantiatedItemAugment> getAugments(){
		return this.augments;
	}
	
	public ArrayList<PiscesInstantiatedItemComponent> getComponents(){
		return this.components;
	}
	
	public ArrayList<PiscesInstantiatedItemCollectable> getCollectables(){
		return this.collectables;
	}
	
	public ArrayList<PiscesInstantiatedItemManual> getManuals(){
		return this.manuals;
	}
	
	public ArrayList<PiscesInstantiatedItemKey> getKeys(){
		return this.keys;
	}
	
	public ArrayList<PiscesInstantiatedItemMisc> getMisc(){
		return this.misc;
	}
	
	public ArrayList get(ItemPockets pocket){
		switch (pocket) {
		case WEAPON:
			return weapons;
		case HAT:
			return head;
		case TORSO:
			return torso;
		case ARMS:
			return hands;
		case PANTS:
			return pants;
		case SHOES:
			return shoes;
		case AUGMENT:
			return augments;
		case COLLECTABLE:
			return collectables;
		case MANUAL:
			return manuals;
		case MISC:
			return misc;
		case KEY:
			return keys;
		case COMPONENT:
			return components;
		default:
			break;
		}
		return null;
	}
}
