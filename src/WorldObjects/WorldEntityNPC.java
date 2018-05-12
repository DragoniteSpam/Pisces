package WorldObjects;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.pisces.PiscesController;

import gamedata.PiscesModel;

public class WorldEntityNPC extends WorldEntity {
	public static final float JUMP_HEIGHT=2.0f;
	
	public WorldEntityNPC(Vector3 position, Quaternion orientation, Vector3 scale, PiscesModel model, String name) {
		super(position, orientation, scale, model, name);
	}
	
	public void update(PiscesController controller, double deltaTime, btCollisionWorld world) {
		super.update(controller,  deltaTime,  world);
	}

}
