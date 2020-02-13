package WorldObjects;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.pisces.PiscesController;

import gamedata.resources.PiscesModel;

public class WorldEntityNPC extends WorldEntity {
	public static final float JUMP_HEIGHT=120f;
	public static final float SPEED_WALK=100f;
	public static final float SPEED_RUN=160f;
	
	public WorldEntityNPC(Vector3 position, Quaternion orientation, Vector3 scale, PiscesModel model, String name) {
		super(position, orientation, scale, model, name);
	}
	
	public void update(PiscesController controller, float deltaTime, btCollisionWorld world) {
		super.update(controller,  deltaTime,  world);
	}

}
