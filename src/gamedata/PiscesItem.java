package gamedata;

<<<<<<< HEAD
import com.badlogic.gdx.graphics.Texture;

public class PiscesItem extends GameData {
	private Texture image;
	private PiscesModel model;

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
=======
public class PiscesItem {

>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
}
