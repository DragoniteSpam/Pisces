package WorldObjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ContactResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.pisces.PiscesController;

import gamedata.items.Inventory;
import gamedata.resources.PiscesModel;
import stuff.PiscesContactResultCallback;

public class WorldEntity extends WorldObject {
	protected float xspeed, yspeed, zspeed;
	protected Vector3 out;
	ContactResultCallback crCallback;
	public final Inventory inventory;

	public WorldEntity(Vector3 position, Quaternion orientation, Vector3 scale, PiscesModel model, String name) {
		super(position, orientation, scale, model, name);
		xspeed = 0f;
		yspeed = 0f;
		zspeed = 0f;
		
		crCallback=new PiscesContactResultCallback();
		inventory=new Inventory();
	}

	public boolean render(Camera camera, ModelBatch batch, Environment environment, boolean debug) {
		if (isVisible(camera)) {
			if (debug) {
				modelInstanceDebug.transform.set(position, orientation, scale);
				batch.render(modelInstanceDebug, environment);
			} else {
				modelInstance.transform.set(position, orientation, scale);
				batch.render(modelInstance, environment);
			}
			return true;
		}
		return false;
	}

	public void update(PiscesController controller, float deltaTime, btCollisionWorld world) {

		if (Math.abs(xspeed) > 0 || Math.abs(yspeed) > 0 || Math.abs(zspeed) > 0) {
			// X position checking

			this.tprevious.set(this.position.x, this.position.y, this.position.z);
			this.position.x = this.position.x + xspeed*deltaTime;

			callback.setCollisionObject(null);
			this.fromRay.set(this.tprevious);
			this.toRay.set(this.position);
			
			world.rayTest(this.tprevious, this.position, this.callback);

			if (callback.hasHit()) {
				System.out.println("Hit something on the X axis");
				this.position.x = this.tprevious.x + xspeed * callback.getClosestHitFraction();
				this.tprevious.x = this.position.x;
			}

			// Y position checking

			// Z position checking

			this.tprevious.set(this.position.x, this.position.y, this.position.z);
			this.position.z = this.position.z + zspeed*deltaTime;

			callback.setCollisionObject(null);
			this.fromRay.set(this.tprevious);
			this.toRay.set(this.position);

			world.rayTest(this.tprevious, this.position, this.callback);

			if (callback.hasHit()) {
				System.out.println("Hit something on the Z axis");
				this.position.z = this.tprevious.z + zspeed * callback.getClosestHitFraction();
				this.tprevious.z = this.position.z;
			}

			/*
			 * int mask = this.collisionObject.mask; this.collisionObject.mask = 0; if
			 * (world.overlap(this.collisionObject, mask)) { System.out.println("Overlap!");
			 * } this.collisionObject.mask = mask;
			 */

			// Updating the position

			if ((!(this.position.x == this.previous.x && this.position.y == this.previous.y
					&& this.position.z == this.previous.z))
					|| (!(this.orientation.x == this.previousOrientation.x
							&& this.orientation.y == this.previousOrientation.y
							&& this.orientation.z == this.previousOrientation.z
							&& this.orientation.w == this.previousOrientation.w))) {
				autoSetWorldTransform();
				this.collisionObject.setWorldTransform(worldTransform);
				// this.collisionObject.position.set(this.position);
				// this.collisionObject.orientation.set(this.orientation);
			}
		}

		super.update(controller, deltaTime, world);
	}

	public void updatePost(PiscesController controller, float deltaTime, btCollisionWorld world) {
		super.updatePost(controller, deltaTime, world);
	}
	
	public void delete() {
		super.delete();
		crCallback.dispose();
	}
}
