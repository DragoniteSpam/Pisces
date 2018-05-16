package gamedata.items;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

import exceptions.ResourceNotFoundException;
import gamedata.GameData;
import gamedata.resources.PiscesModel;

public class PiscesItem extends GameData {
	private static HashMap<String, PiscesItem> all=new HashMap<String, PiscesItem>();
	
	private Texture image;
	private PiscesModel model;
	protected boolean required;

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
	
	public static PiscesItem getByName(String name) throws ResourceNotFoundException {
		if (all.containsKey(name)) {
			return all.get(name);
		}
		throw new ResourceNotFoundException("No PiscesSkillTree found with the name "+name);
	}
}
