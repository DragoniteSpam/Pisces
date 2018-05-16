package gamedata.resources;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btBvhTriangleMeshShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.pisces.Tools;

import collisions.PiscesCollisionShape;
import gamedata.GameData;

public class PiscesModel extends GameData {
	private static HashMap<String, PiscesModel> all=new HashMap<String, PiscesModel>();
	
	private Model modelVisible;
	private Model modelVisibleCollision;
	//private PiscesCollisionShape modelCollision;
	private btCollisionShape modelCollision;
	private boolean cull;
	
	public final Vector3 center=new Vector3();
	public final Vector3 dimensions=new Vector3();
	public float radius;
	private static final BoundingBox bounds=new BoundingBox();
	
	public PiscesModel(String name) {
		super(name);
		
		init();
	}
	
	public PiscesModel(String name, int id) {
		super(name, id);
		
		init();
	}
	
	private void init() {
		this.modelVisible=null;
		this.modelCollision=null;
		this.modelVisibleCollision=null;
		
		this.cull=true;
		this.radius=0f;
		
		all.put(this.name, this);
	}
	
	public void delete() {
		// Any cleanup that needs to happen here
		all.remove(this.name);
	}
	
	public void autoCollisionShape() {
		if (this.modelVisibleCollision==null) {
			throw new NullPointerException("Trying to auto-set a collision model before assigning a visible collision model");
		}
		//this.modelCollision=new PiscesCollisionShape();
		//this.modelCollision.setModel(this.modelVisibleCollision);
		this.modelCollision=Bullet.obtainStaticNodeShape(this.modelVisibleCollision.nodes);
		//this.modelCollision=new btBvhTriangleMeshShape(this.modelVisibleCollision.meshParts);
		this.modelCollision=new btBoxShape(new Vector3(12f, 12f, 12f));
//		this.modelCollision=new btBvhTriangleMeshShape(this.modelVisibleCollision.nodes);
	}
	
	public static PiscesModel get(String name) {
		if (all.containsKey(name)) {
			return all.get(name);
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

	/*public void setModelCollision(btCollisionShape modelCollision) {
		this.modelCollision=modelCollision;
	}*/
	
	public Model getVisibleModel() {
		return this.modelVisible;
	}
	
	public Model getVisibleCollisionModel() {
		return this.modelVisibleCollision;
	}
	
	public boolean getCulling() {
		return this.cull;
	}
	
	/*public PiscesCollisionShape getCollisionModel() {
		return this.modelCollision;
	}*/
	
	public float getRadius() {
		return radius;
	}

	public btCollisionShape getCollisionShape() {
		return this.modelCollision;
	}
}
