package WorldObjects;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
<<<<<<< HEAD
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.pisces.PiscesController;
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e

import gamedata.PiscesModel;

public class WorldEntityNPC extends WorldEntity {
	public static final float JUMP_HEIGHT=2.0f;
	
	public WorldEntityNPC(Vector3 position, Quaternion orientation, Vector3 scale, PiscesModel model, String name) {
		super(position, orientation, scale, model, name);
	}
<<<<<<< HEAD
	
	public void update(PiscesController controller, double deltaTime, btCollisionWorld world) {
		super.update(controller,  deltaTime,  world);
	}
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e

}
