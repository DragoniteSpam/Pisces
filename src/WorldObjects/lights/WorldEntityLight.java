package WorldObjects.lights;

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
import gamedata.PiscesModel;

public class WorldEntityLight extends WorldEntity {
	protected PointLight light;
	protected Vector3 lightPosition;
	protected Vector3 lightOffset;
	protected float lightIntensity;
	protected Color lightColor;

	public WorldEntityLight(Vector3 position, Quaternion orientation, Vector3 scale, PiscesModel model, String name) {
		super(position, orientation, scale, model, name);
		this.animationController = new AnimationController(this.modelInstance);
		this.animationController.setAnimation("FlickerAction", -1);
		this.light = null;
		this.lightPosition=position.cpy();
		this.lightOffset=new Vector3(0f, 0f, 0f);
		this.lightColor=new Color(1f, 1f, 1f, 1f);
		this.lightIntensity=128f;
	}

	public boolean render(Camera camera, ModelBatch batch, Environment environment) {
		animationController.update(Gdx.graphics.getDeltaTime());
		return super.render(camera, batch, environment);
	}

	public void updatePost(PiscesController controller, double deltaTime, btCollisionWorld world) {
		
	}

	public void setLight(Environment environment) {
		if (this.light != null) {
			environment.remove(light);
		}
		this.light = new PointLight().set(this.lightColor, this.lightPosition, this.lightIntensity);
		environment.add(light);
	}
	
	public void setLightOffset(float x, float y, float z) {
		this.lightOffset.set(x, y, z);
		this.lightPosition.set(this.position.x+x, this.position.y+y, this.position.z+z);
		if (this.light!=null) {
			this.light.set(this.lightColor, this.lightPosition, this.lightIntensity);
		}
	}
	
	public void setLightColor(float r, float b, float g) {
		this.lightColor.set(r, g, b, 1f);
		if (this.light!=null) {
			this.light.set(this.lightColor, this.lightPosition, this.lightIntensity);
		}
	}
	
	public void setLightColor(Color color) {
		setLightColor(color.r, color.g, color.b);
	}
	
	public void setLightIntensity(float intensity) {
		this.lightIntensity=intensity;
		if (this.light!=null) {
			this.light.set(this.lightColor, this.lightPosition, this.lightIntensity);
		}
	}

}
