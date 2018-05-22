package WorldObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ClosestNotMeRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.pisces.Pisces;
import com.pisces.PiscesCamera;
import com.pisces.PiscesController;

import gamedata.resources.PiscesModel;
import stuff.CameraProperties;

public class WorldObject {
	public static final int COLLISION_DEFAULT = 1 << 1;
	public static final int COLLISION_PRIMARY = 1 << 2;
	public static final int COLLISION_PRIMARY_DEFAULT = 1 << 2 | 1 << 1;
	public static final int COLLISION_EVENT = 1 << 3;
	public static final int COLLISION_EVENT_DEFAULT = 1 << 3 | 1 << 1;
	public static final int COLLISION_DEAD = 1 << 4;
	public static final int COLLISION_DEAD_DEFAULT = 1 << 4| 1 << 1;
	public static final int COLLISION_EVERYTHING = COLLISION_DEFAULT | COLLISION_PRIMARY | COLLISION_EVENT
			| COLLISION_DEAD;

	public static final float DEFAULT_EYE_HEIGHT = 25;
	
	private static ArrayList<WorldObject> all=new ArrayList<WorldObject>(); 
	private static HashMap<Integer, WorldObject> ids=new HashMap<Integer, WorldObject>();
	private static Random random=new Random();
	
	protected Vector3 position;
	protected Vector3 previous;
	protected Vector3 tprevious;
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
	protected AnimationController animationController;
	protected ModelInstance modelInstance;
	protected ModelInstance modelInstanceDebug;
	protected btCollisionObject collisionObject;
	protected final ClosestNotMeRayResultCallback callback;
	//protected PiscesCollisionObject collisionObject;
	protected Matrix4 worldTransform;
	
	public WorldObject(Vector3 position, Quaternion orientation, Vector3 scale, PiscesModel model, String name) {
		do {
			this.id=random.nextInt();
		} while (ids.containsKey(this.id));
		ids.put(this.id,  this);
		
		this.position=position;
		this.previous=this.position.cpy();
		this.tprevious=this.previous.cpy();
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
		this.modelInstanceDebug=new ModelInstance(this.model.getVisibleCollisionModel());
		this.worldTransform=new Matrix4(position, orientation, scale);
		autoSetWorldTransform();
		
		this.collisionObject=new btCollisionObject();
		this.collisionObject.setCollisionShape(this.model.getCollisionShape());
		this.collisionObject.setUserValue(this.id);
		this.collisionObject.setCollisionFlags(this.collisionObject.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_CUSTOM_MATERIAL_CALLBACK);
		this.collisionObject.setWorldTransform(this.worldTransform);
		
		Pisces.me().getCollisionWorld().addCollisionObject(this.collisionObject, COLLISION_PRIMARY, COLLISION_EVERYTHING);
		
		this.callback=new ClosestNotMeRayResultCallback(this.collisionObject);
		this.callback.setClosestHitFraction(1f);
		//this.callback.setCollisionFilterGroup(COLLISION_DEFAULT);
		//this.callback.setCollisionFilterMask(COLLISION_PRIMARY);
		
		/*this.collisionObject=new PiscesCollisionObject(this.model.getCollisionModel(), COLLISION_PRIMARY, this.id);
		this.collisionObject.position=position;
		this.collisionObject.orientation=orientation;
		this.collisionObject.scale=scale;
		Pisces.me().getCollisionWorld().addObject(this.collisionObject);*/
		
		all.add(this);
	}
	
	protected void autoSetWorldTransform() {
		//this.worldTransform.set(this.position.x, this.position.y, this.position.z, this.orientation.x+90, this.orientation.y, this.orientation.z, this.orientation.w, this.scale.x, this.scale.y, this.scale.z);
		this.worldTransform.set(this.position, this.orientation, this.scale);
	}
	
	public void update(PiscesController controller, double deltaTime, btCollisionWorld world) {
		
	}
	
	public void updatePost(PiscesController controller, double deltaTime, btCollisionWorld world) {
		this.previous.set(this.position);
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

	public boolean render(Camera camera, ModelBatch batch, Environment environment, boolean debug) {
		return false;
	}

	public void delete() {
		// Any cleanup that needs to happen here
		for (int i = 0; i < all.size(); i++) {
			if (all.get(i) == this) {
				all.remove(i);
				break;
			}
		}
		ids.remove(this.id);
		
		this.callback.dispose();
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
				if (object.render(camera, batch, environment, false)) {
					visible++;					
				}
			}
		}
		
		return visible;
	}
	
	public static int renderAllDebug(ModelBatch batch, Environment environment) {
		PiscesCamera camera=Pisces.me().getCamera();
		int visible=0;
		for (WorldObject object : all) {
			if (!(camera.getTarget()==object&&camera.getPOV()==CameraProperties.CAMERA_FIRST_PERSON)) {
				if (object.render(camera, batch, environment, true)) {
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

	public static boolean getIDExists(int id) {
		return ids.containsKey(id);
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
	
	public String toString() {
		return this.model.getName()+"@"+this.position;
	}
}
