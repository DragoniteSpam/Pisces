package gamedata.abilities;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import exceptions.ResourceNotFoundException;

public class PiscesAbilityShape {
	private static HashMap<String, PiscesAbilityShape> all=new HashMap<String, PiscesAbilityShape>();
	private static Texture masterImage=null;
	private String name;
	private TextureRegion textureRegionFilled;
	private TextureRegion textureRegionAvailable;
	private TextureRegion textureRegionUnfilled;
	
	public PiscesAbilityShape(String name, float imageY) {
		this.name=name;
		this.textureRegionFilled=new TextureRegion(masterImage, 128, imageY, 64, 64);
		this.textureRegionAvailable=new TextureRegion(masterImage, 64, imageY, 64, 64);
		this.textureRegionUnfilled=new TextureRegion(masterImage, 0, imageY, 64, 64);
		
		all.put(this.name, this);
	}
	
	public static PiscesAbilityShape getByName(String name) throws ResourceNotFoundException {
		if (all.containsKey(name)) {
			return all.get(name);
		}
		throw new ResourceNotFoundException("No PiscesAbilityShape found with the name "+name);
	}
	
	public static void createAbilityShapes() {
		masterImage=new Texture("graphics/icons/abilities.png");
		new PiscesAbilityShape("Square", 0);
		new PiscesAbilityShape("Star", 64);
		new PiscesAbilityShape("Heart", 128);
		new PiscesAbilityShape("Diamond", 192);
		new PiscesAbilityShape("Circle", 256);
	}
}
