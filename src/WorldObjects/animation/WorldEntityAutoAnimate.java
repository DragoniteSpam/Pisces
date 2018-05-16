package WorldObjects.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.pisces.PiscesController;

import WorldObjects.WorldEntity;
import gamedata.resources.PiscesModel;

public class WorldEntityAutoAnimate extends WorldEntity {

	private float speed;
	
	public WorldEntityAutoAnimate(Vector3 position, Quaternion orientation, Vector3 scale, PiscesModel model, String name) {
		super(position, orientation, scale, model, name);
		this.speed=1;
		this.animationController = new AnimationController(this.modelInstance);
		this.animationController.setAnimation("Default Take", -1, this.speed, null);
	}

	public boolean render(Camera camera, ModelBatch batch, Environment environment, boolean debug) {
		animationController.update(Gdx.graphics.getDeltaTime());
		return super.render(camera, batch, environment, debug);
	}

	public void updatePost(PiscesController controller, double deltaTime, btCollisionWorld world) {
		
	}
	
	public void setAnimationSpeed(float speed) {
		this.speed=speed;
		this.animationController.setAnimation("Default Take", -1, speed, null);
	}
	
	public float getAnimationSpeed() {
		return this.speed;
	}
}
