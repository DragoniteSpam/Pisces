package gamedata.items;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

import exceptions.ResourceNotFoundException;
import gamedata.GameData;
import gamedata.resources.PiscesModel;
import stuff.ItemPockets;

public class PiscesItem extends GameData {
	protected static HashMap<String, PiscesItem> all=new HashMap<String, PiscesItem>();
	
	private Texture image;
	private PiscesModel model;
	protected boolean required;
	protected int price;
	protected ItemPockets pocket;

	public PiscesItem(String name) {
		super(name);
		
		init();
	}
	
	public PiscesItem(String name, int id) {
		super(name, id);
		
		init();
	}
	
	private void init() {
		this.image=null;
		this.model=null;
		this.required=false;
		this.pocket=ItemPockets.KEY;
		
		all.put(this.name,  this);
	}
	
	public Texture getImage() {
		return image;
	}
	
	public PiscesModel getModel() {
		return model;
	}
	
	public void setImage(Texture image) {
		this.image=image;
	}
	
	public void setModel(PiscesModel model) {
		this.model=model;
	}
	
	public void setRequired(boolean required) {
		this.required=required;
	}
	
	public boolean getRequired() {
		return this.required;
	}
	
	public void setPrice(int price) {
		this.price=price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPocket(ItemPockets pocket) {
		this.pocket=pocket;
	}
	
	public ItemPockets getPocket() {
		return this.pocket;
	}
	
	public static PiscesItem getByName(String name) throws ResourceNotFoundException {
		if (all.containsKey(name)) {
			return all.get(name);
		}
		throw new ResourceNotFoundException("No PiscesSkillTree found with the name "+name);
	}
	
	public void use() {
		
	}
	
	public void addMe(Inventory inventory) {
		
	}
}
