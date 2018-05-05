package WorldObjects;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.pisces.Pisces;
import com.pisces.PiscesCamera;
import com.pisces.PiscesController;
import com.pisces.Tools;

import gamedata.PiscesModel;

public final class WorldEntityPlayer extends WorldEntityNPC {

	public WorldEntityPlayer(Vector3 position, Quaternion orientation, Vector3 scale, PiscesModel model, String name) {
		super(position, orientation, scale, model, name);
		this.gravity=true;
		this.pitch=10;
	}
	
	public void update(PiscesController controller, double deltaTime, btCollisionWorld world) {
		xspeed=0;
		zspeed=0;
		float moveSpeed=(float)(120*deltaTime);
		if (!locked) {
			if (controller.l()) {
				moveSpeed=(float)(210*deltaTime);
			}
			if (controller.yRelease()) {
				yspeed=JUMP_HEIGHT;
			}
			float deadZone=Pisces.me().getSettings().getControlDeadZone();
			if (controller.getLeftStickMagnitude()>deadZone) {
				float dir=direction+(float)(controller.getLeftStickDirection())-90f;
				xspeed=(float)(Tools.dcos(dir)*moveSpeed);
				zspeed=-(float)(Tools.dsin(dir)*moveSpeed);
			}
			if (controller.getRightStickMagnitude()>deadZone) {
				direction=(float)(360+direction-controller.getRightStickHorizontal())%360f;
				pitch=(float)(Tools.clamp(pitch+controller.getRightStickVertical(), -75f, 75f));
			}
			if (controller.rs()) {
				double vertical=controller.getRightStickVertical();
				double rate=64;
				PiscesCamera camera=Pisces.me().getCamera();
				if (vertical<0) {
					camera.zoomIn(rate*deltaTime);
				} else {
					camera.zoomOut(rate*deltaTime);
				}
			}
		}
		
		super.update(controller, deltaTime, world);
	}
	
}
