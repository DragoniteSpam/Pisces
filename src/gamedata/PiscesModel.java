package gamedata;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.pisces.Tools;

public class PiscesModel extends GameData {
	
	private static ArrayList<PiscesModel> all=new ArrayList<PiscesModel>();
	
	private Model modelVisible;
	private Model modelVisibleCollision;
	private btCollisionShape modelCollision;
	private boolean cull;
	
	public final Vector3 center=new Vector3();
	public final Vector3 dimensions=new Vector3();
	public float radius;
	private static final BoundingBox bounds=new BoundingBox();
	
	public PiscesModel(String name) {
		super(name);
		all.add(this);
		
		this.modelVisible=null;
		this.modelCollision=null;
		this.modelVisibleCollision=null;
		
		this.cull=true;
		this.radius=0f;
	}
	
	public PiscesModel(String name, long id) {
		super(name, id);
	}
	
	public void delete() {
		// Any cleanup that needs to happen here
		for (int i=0; i<all.size(); i++) {
			if (all.get(i)==this) {
				all.remove(i);
				break;
			}
		}
	}
	
	public void autoCollisionShape() {
		if (this.modelVisibleCollision==null) {
			throw new NullPointerException("Trying to auto-set a collision model before assigning a visible collision model");
		}
		this.modelCollision=Bullet.obtainStaticNodeShape(modelVisibleCollision.nodes);
	}
	
	public static PiscesModel get(String name) {
		for (PiscesModel model : all) {
			if (model.name.equals(name)) {
				return model;
			}
		}
		Tools.log("Unable to find model: "+name);
		return null;
	}

	public void setModelVisible(Model model) {
		this.modelVisible=model;
		this.modelVisible.calculateBoundingBox(bounds);
		bounds.getCenter(center);
		bounds.getDimensions(dimensions);
		this.radius=dimensions.len()/2f;
	}
	
	public void setCulling(boolean cull) {
		this.cull=cull;
	}
	
	public void setModelVisibleCollision(Model model) {
		this.modelVisibleCollision=model;
	}

	public void setModelCollision(btCollisionShape modelCollision) {
		this.modelCollision=modelCollision;
	}
	
	public Model getVisibleModel() {
		return this.modelVisible;
	}
	
	public boolean getCulling() {
		return this.cull;
	}
	
	public btCollisionShape getCollisionModel() {
		return this.modelCollision;
	}
	
	public float getRadius() {
		return radius;
	}
}
