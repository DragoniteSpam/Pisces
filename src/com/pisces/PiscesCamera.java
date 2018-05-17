package com.pisces;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import stuff.CameraProperties;
import WorldObjects.WorldObject;

public class PiscesCamera extends PerspectiveCamera {
	public static final double DEFAULT_DISTANCE = 64;
	public static final double MIN_DISTANCE=32;
	public static final double MAX_DISTANCE=256;
	public static final double DEFAULT_GLIDE_RATE = 0.1;

	private Vector3 to, from, up;
	private double distance;
	private double glideRate;
	private float lookDirection;
	private float lookPitch;
	private CameraProperties state;
	private CameraProperties mode;
	
	private final Vector3 tmp = new Vector3();

	private WorldObject target;
	private WorldObject hook;

	public PiscesCamera(float fieldOfView, float viewportWidth, float viewportHeight) {
		// Manually assign the constructor variables because calling super(fov, width,
		// height) implicitly calls
		// update() before setting the value of state
		this.fieldOfView = fieldOfView;
		this.viewportWidth = viewportWidth;
		this.viewportHeight = viewportHeight;
		this.near = 1f;
		this.far = 32000f;

		this.target = null;
		this.hook = null;
		this.to = new Vector3(0f, 0f, 0f);
		this.from = new Vector3(0f, 0f, 0f);
		this.up = new Vector3(0f, 1f, 0f);
		this.distance = DEFAULT_DISTANCE;
		this.lookDirection=0f;
		this.lookPitch=0f;
		this.glideRate = DEFAULT_GLIDE_RATE;
		this.state = CameraProperties.CAMERA_FREE;
		this.mode = CameraProperties.CAMERA_SNAP;
	}

	public void setFollowing(WorldObject object) {
		setFollowing(object, true);
	}
	
	public void setFollowing(WorldObject object, boolean assumeNewLookAngle) {
		this.target=object;
		if (assumeNewLookAngle) {
			this.lookDirection=target.getDirection();
			this.lookPitch=target.getPitch();
		}
	}

	public void update() {
		Vector3 targetPosition;
		switch (state) {
		case CAMERA_FIRST_PERSON:
			if (target == null) {
				throw new NullPointerException("Camera is set to first person but the target is null");
			}
			targetPosition = target.getPosition();
			from.set(targetPosition.x, targetPosition.y + target.getEyeHeight(), targetPosition.z);
			to.set((float) (from.x + Tools.dcos(lookDirection)),
					(float) (from.y - Tools.dsin(lookPitch)),
					(float) (from.z - Tools.dsin(lookDirection)));
			break;
		case CAMERA_THIRD_PERSON:
			if (target == null) {
				throw new NullPointerException("Camera is set to third person but the target is null");
			}
			targetPosition = target.getPosition();
			to.set(targetPosition.x, targetPosition.y + target.getEyeHeight(), targetPosition.z);
			// TODO There should be a raycast here to make sure the camera doesn't clip
			// through a wall
			from.set((float) (to.x - distance * Tools.dcos(lookDirection)),
					(float) (to.y - distance * Tools.dsin(lookPitch)),
					(float) (to.z + distance * Tools.dsin(lookDirection)));
			break;
		case CAMERA_FREE:
			// leave alone
			break;
		default:
			break;
		}

		if (hook != null) {
			switch (mode) {
			case CAMERA_SNAP:
				to.set(hook.getPosition());
				break;
			case CAMERA_GLIDE:
				targetPosition = hook.getPosition();
				to.set((float) Tools.lerp(to.x, targetPosition.x, glideRate),
						(float) Tools.lerp(to.y, targetPosition.y, glideRate),
						(float) Tools.lerp(to.z, targetPosition.z, glideRate));
				break;
			default:
				break;
			}
		}
		moveTo(from, to, up);
	}

	public void moveTo(Vector3 from, Vector3 to, Vector3 up) {
		this.from = from;
		this.to = to;
		this.up = up;
		position.set(from);
		lookAt(to);
		// I don't know why but super.update ruins everything.
		//super.update();
		
		// This is literally the code for super.update but not done in super.update.
		
		float aspect = viewportWidth / viewportHeight;
		projection.setToProjection(Math.abs(near), Math.abs(far), fieldOfView, aspect);
		view.setToLookAt(position, tmp.set(position).add(direction), up);
		combined.set(projection);
		Matrix4.mul(combined.val, view.val);

		invProjectionView.set(combined);
		Matrix4.inv(invProjectionView.val);
		frustum.update(invProjectionView);
	}
	
	public void zoomIn(double amount) {
		distance=Math.max(MIN_DISTANCE, distance-amount);
	}
	
	public void zoomOut(double amount) {
		distance=Math.min(MAX_DISTANCE, distance+amount);
	}

	public void setCameraFirstPerson() {
		this.state = CameraProperties.CAMERA_FIRST_PERSON;
	}
	
	public void setCameraThirdPerson() {
		this.state = CameraProperties.CAMERA_THIRD_PERSON;
	}
	
	public void setCameraFree() {
		this.state = CameraProperties.CAMERA_FREE;
	}

	public void setCameraSnap() {
		this.mode = CameraProperties.CAMERA_SNAP;
	}
	
	public void setCameraGlide() {
		this.mode = CameraProperties.CAMERA_GLIDE;
	}

	public CameraProperties getPOV() {
		return this.state;
	}

	public WorldObject getTarget() {
		return this.target;
	}
	
	public Vector3 getFrom() {
		return this.from;
	}
	
	public Vector3 getTo() {
		return this.to;
	}
	
	public float getDirection() {
		return this.lookDirection;
	}
	
	public float getPitch() {
		return this.lookPitch;
	}
	
	public void setDirection(float direction) {
		this.lookDirection=direction;
	}
	
	public void setPitch(float pitch) {
		this.lookPitch=pitch;
	}
}
