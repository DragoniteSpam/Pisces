package gamedata.items;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import exceptions.ResourceNotFoundException;
import gamedata.GameData;
import gamedata.resources.PiscesModel;
import stuff.ItemPockets;

public class PiscesItem extends GameData {
	protected static HashMap<String, PiscesItem> all=new HashMap<String, PiscesItem>();

	private TextureRegion image;
	private TextureRegion grayedImage;
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
	
	public PiscesItem(String name, int id, TextureRegion image, TextureRegion grayedImage) {
		this(name, id);
		
		this.image=image;
		this.grayedImage=grayedImage;
	}
	
	private void init() {
		this.image=null;
		this.model=null;
		this.required=false;
		this.pocket=ItemPockets.KEY;
		
		all.put(this.name,  this);
	}
	
	public TextureRegion getImage() {
		return image;
	}
	
	public PiscesModel getModel() {
		return model;
	}
	
	public void setImage(TextureRegion image) {
		this.image=image;
	}
	
	public void setGrayedImage(TextureRegion grayedImage) {
		this.grayedImage=grayedImage;
	}
	
	public TextureRegion getGrayedImage() {
		return this.grayedImage;
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
