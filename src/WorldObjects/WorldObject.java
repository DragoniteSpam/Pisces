package WorldObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.pisces.Pisces;
import com.pisces.PiscesCamera;
import com.pisces.PiscesController;
import com.pisces.Tools;

import gamedata.PiscesModel;
import stuff.CameraProperties;

public class WorldObject {
	public static final int COLLISION_DEFAULT=1;
	public static final int COLLISION_PRIMARY=2;
	public static final int COLLISION_PRIMARY_DEFAULT=3;
	public static final int COLLISION_EVENT=4;
	public static final int COLLISION_EVENT_DEFAULT=5;
	public static final int COLLISION_DEAD=8;
	public static final int COLLISION_DEAD_DEFAULT=9;
	public static final int COLLISION_EVERYTHING=255;
	
	public static final float DEFAULT_EYE_HEIGHT=25;
	
	private static ArrayList<WorldObject> all=new ArrayList<WorldObject>(); 
	private static HashMap<Integer, WorldObject> ids=new HashMap<Integer, WorldObject>();
	private static Random random=new Random();
	
	protected Vector3 position;
	protected Vector3 previous;
	protected Quaternion orientation;
	protected Quaternion previousOrientation;
	protected float direction;
	protected float pitch;
	protected Vector3 scale;
	protected float eyeHeight;
	
	protected int id;
	protected String name;
	protected WorldObject parent;
	protected boolean alive;
	protected boolean locked;
	protected boolean gravity;
	
	protected PiscesModel model;
	protected ModelInstance modelInstance;
	protected btCollisionObject collisionObject;
	
	public WorldObject(Vector3 position, Quaternion orientation, Vector3 scale, PiscesModel model, String name) {
		do {
			this.id=random.nextInt();
		} while (ids.containsKey(this.id));
		ids.put(this.id,  this);
		
		this.position=position;
		this.previous=this.position.cpy();
		this.orientation=orientation;
		this.previousOrientation=this.orientation.cpy();
		this.scale=scale;
		this.model=model;
		this.name=name;
		this.alive=true;
		this.locked=false;
		this.gravity=false;
		
		this.direction=0f;
		this.pitch=0f;
		this.eyeHeight=DEFAULT_EYE_HEIGHT;
		
		this.modelInstance=new ModelInstance(this.model.getVisibleModel());
		
		this.collisionObject=new btCollisionObject();
		this.collisionObject.setCollisionShape(this.model.getCollisionModel());
		this.collisionObject.setUserValue(this.id);
		this.collisionObject.setCollisionFlags(this.collisionObject.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_CUSTOM_MATERIAL_CALLBACK/*COLLISION_PRIMARY_DEFAULT*/);
		Pisces.me().getCollisionWorld().addCollisionObject(this.collisionObject);
		this.collisionObject.setWorldTransform(new Matrix4(this.position, this.orientation, this.scale));
		
		all.add(this);
	}
	
	public void update(PiscesController controller, double deltaTime, btCollisionWorld world) {
		
	}
	
	public void updatePost(PiscesController controller, double deltaTime, btCollisionWorld world) {
		
	}
	
	public void setPrevious() {
		this.previous.set(this.position);
		this.previousOrientation.set(this.orientation);
	}
	
	public void moveToPrevious() {
		this.position.set(this.position);
	}
	
	public void setMask(int mask) {
	}
	
	public final String getName() {
		return this.name;
	}
	
	public Vector3 getPosition() {
		return position;
	}
	
	public Quaternion getOrientation() {
		return orientation;
	}
	
	public Vector3 getScale() {
		return scale;
	}
	
	public btCollisionObject getCollisionObject() {
		return null;
	}
	
	public float getDirection() {
		return direction;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public float getEyeHeight() {
		return eyeHeight;
	}
	
	public boolean isVisible(final Camera camera) {
		if (!model.getCulling()) {
			return true;
		}
		modelInstance.transform.getTranslation(Pisces.position);
		return camera.frustum.sphereInFrustum(Pisces.position, this.model.getRadius());
	}
	
	public boolean render(Camera camera, ModelBatch batch, Environment environment) {
		return false;
	}
	
	public void delete() {
		// Any cleanup that needs to happen here
		for (int i=0; i<all.size(); i++) {
			if (all.get(i)==this) {
				all.remove(i);
				break;
			}
		}
		ids.remove(this.id);
		
		this.collisionObject.dispose();
	}
	
	public void kill() {
		alive=false;
	}
	
	public void resurrect() {
		alive=true;
	}
	
	public static int renderAll(ModelBatch batch, Environment environment) {
		PiscesCamera camera=Pisces.me().getCamera();
		int visible=0;
		for (WorldObject object : all) {
			if (!(camera.getTarget()==object&&camera.getPOV()==CameraProperties.CAMERA_FIRST_PERSON)) {
				if (object.render(camera, batch, environment)) {
					visible++;					
				}
			}
		}
		
		return visible;
	}
	
	public static void processAll(PiscesController controller, double deltaTime, btCollisionWorld world) {
		for (WorldObject object : all) {
			if (object.alive) {
				object.setPrevious();
				object.update(controller, deltaTime, world);
			}
		}
	}
	
	public static void processAllPost(PiscesController controller, double deltaTime, btCollisionWorld world) {
		for (WorldObject object : all) {
			if (object.alive) {
				object.updatePost(controller, deltaTime, world);
			}
		}
	}
	
	public static WorldObject getByID(int id) {
		return ids.get(id);
	}
	
	public static void disposeAll() {
		// ConcurrentModificationException
		/*for (WorldObject object : all) {
			object.delete();
		}*/
		// Can't use a regular for loop because you're deleting
		// elements from the arraylist as you iterate over the thing
		while (all.size()>0) {
			all.get(0).delete();
		}
	}
}
