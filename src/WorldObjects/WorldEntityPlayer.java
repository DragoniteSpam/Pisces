package WorldObjects;

import java.util.ArrayList;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.pisces.Pisces;
import com.pisces.PiscesCamera;
import com.pisces.PiscesController;
import com.pisces.Tools;

import collisions.PiscesCollisions;
import exceptions.TeamException;
import gamedata.PiscesCharacter;
import gamedata.PiscesClass;
import gamedata.resources.PiscesModel;
import stuff.DebugStates;

public final class WorldEntityPlayer extends WorldEntityNPC {
	public static final int MAX_TEAM_SIZE=6;
	
	protected WorldObject inFrontOfMe;
	protected ArrayList<PiscesCharacter> team;

	public WorldEntityPlayer(Vector3 position, Quaternion orientation, Vector3 scale, PiscesModel model, String name) {
		super(position, orientation, scale, model, name);
		this.gravity=true;
		this.pitch=10;
		
		this.inFrontOfMe=null;
		
		this.team=new ArrayList<PiscesCharacter>();
	}
	
	public void update(PiscesController controller, double deltaTime, btCollisionWorld world) {
		xspeed=0;
		zspeed=0;
		float moveSpeed=(float)(SPEED_WALK*deltaTime);
		if (!locked) {
			PiscesClass baseClass=team.get(0).getBaseClass();
			if (controller.l()) {
				moveSpeed=(float)(SPEED_RUN*deltaTime*baseClass.getMoveSpeed());
			}
			if (controller.yRelease()) {
				yspeed=(float)(JUMP_HEIGHT*deltaTime*baseClass.getJumpHeight());
			}
			PiscesCamera camera=Pisces.me().getCamera();
			float deadZone=Pisces.me().getSettings().getControlDeadZone();
			float sensitivity=Pisces.me().getSettings().getControlSensitivity();
			if (controller.getLeftStickMagnitude()>deadZone) {
				float dir=(float) (camera.getDirection()+(controller.getLeftStickDirection())-90f);
				xspeed=Tools.dcos(dir)*moveSpeed;
				zspeed=-Tools.dsin(dir)*moveSpeed;
			}
			if (controller.getRightStickMagnitude()>deadZone) {
				// TODO raycast to make sure the camera doesn't go through anything
				camera.setDirection((float)(360+camera.getDirection()-controller.getRightStickHorizontal())%360f);
				camera.setPitch((float)(Tools.clamp(camera.getPitch()+controller.getRightStickVertical(), -75f, 75f)));
			}
			if (controller.rs()) {
				double vertical=controller.getRightStickVertical();
				double rate=64;
				if (vertical<0) {
					camera.zoomIn(rate*deltaTime);
				} else {
					camera.zoomOut(rate*deltaTime);
				}
			}
		}
		
		super.update(controller, deltaTime, world);
		
		Pisces pisces=Pisces.me();
		
		float distance;
		if (DebugStates.freeRayDistance(pisces.getDebugState())) {
			distance=1024f;
		} else {
			distance=80f;
		}
		
		/*callback.setCollisionObject(null);
		Ray pickRay=pisces.getCamera().getPickRay(pisces.getController().getMouseX(), pisces.getController().getMouseY());
		pickRay.direction.scl(distance).add(pisces.getCamera().getFrom());
		callback.setRayFromWorld(pisces.getCamera().getFrom());
		callback.setRayToWorld(pisces.getCamera().getTo().sub(this.position).scl(distance).add(this.position));
		
		world.rayTest(this.position, pickRay.direction, callback);
		
		if (callback.hasHit()) {
			System.out.println("Raycasted something: "+WorldObject.getByID(callback.getCollisionObject().getUserValue()));
		}*/
	}
	
	public void addTeamMemeber(PiscesCharacter character) throws TeamException {
		for (PiscesCharacter member : team) {
			if (member==character) {
				throw new TeamException("Character ["+character.toString()+"] already exists in team.");
			}
		}
		if (team.size()>=MAX_TEAM_SIZE) {
			throw new TeamException("Team is already full!");
		}
		this.team.add(character);
	}
	
	public PiscesCharacter getTeamLeader() {
		return team.get(0);
	}
	
	public PiscesCharacter getTeamMember(int n) {
		return team.get(n);
	}
	
	public int getTeamSize() {
		return team.size();
	}
	
}
