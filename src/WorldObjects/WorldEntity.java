package WorldObjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.RayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.pisces.Pisces;
import com.pisces.PiscesController;

import gamedata.PiscesModel;

public class WorldEntity extends WorldObject {
	protected float xspeed, yspeed, zspeed;

	public WorldEntity(Vector3 position, Quaternion orientation, Vector3 scale, PiscesModel model, String name) {
		super(position, orientation, scale, model, name);
		xspeed = 0f;
		yspeed = 0f;
		zspeed = 0f;
	}

	public boolean render(Camera camera, ModelBatch batch, Environment environment) {
		if (isVisible(camera)) {
			modelInstance.transform = new Matrix4(position, orientation, scale);
			batch.render(modelInstance, environment);
			return true;
		}
		return false;
	}

	public void update(PiscesController controller, double deltaTime, btCollisionWorld world) {
		this.position.set(position.x + xspeed, position.y + yspeed, position.z + zspeed);
		
		int flags=this.collisionObject.getCollisionFlags();
		//this.collisionObject.setCollisionFlags(0);
		
		callback.setCollisionObject(null);
		callback.setClosestHitFraction(1f);
		callback.setCollisionFilterMask(flags);
		callback.setCollisionFilterGroup(flags);
		callback.setRayFromWorld(previous);
		callback.setRayToWorld(position);
		world.rayTest(this.previous, this.position, callback);
		
		if (callback.hasHit()) {
			if (WorldObject.getIDExists(callback.getCollisionObject().getUserValue())) {
				System.out.println(name+" has hit "+WorldObject.getByID(callback.getCollisionObject().getUserValue()).getName());
			} else {
				System.out.println(name+" has hit one of the test objects");
			}
		}
		
		if (!(this.position.x == this.previous.x && this.position.y == this.previous.y
				&& this.position.z == this.previous.z)) {
			this.worldTransform.set(position, orientation);
			this.collisionObject.setWorldTransform(worldTransform);
		}
		if (!(this.orientation.x == this.previousOrientation.x && this.orientation.y == this.previousOrientation.y
				&& this.orientation.z == this.previousOrientation.z
				&& this.orientation.w == this.previousOrientation.w)) {
			this.worldTransform.set(orientation);
			this.collisionObject.setWorldTransform(worldTransform);
		}
		
		/*this.worldTransform.set(position, orientation);
		this.collisionObject.setWorldTransform(worldTransform);*/
		//this.collisionObject.setCollisionFlags(flags);
	}

	public void updatePost(PiscesController controller, double deltaTime, btCollisionWorld world) {

	}

}
