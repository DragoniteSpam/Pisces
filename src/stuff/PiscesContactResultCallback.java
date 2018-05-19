package stuff;

import com.badlogic.gdx.physics.bullet.collision.ContactResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObjectWrapper;
import com.badlogic.gdx.physics.bullet.collision.btManifoldPoint;
import com.pisces.Tools;

import WorldObjects.WorldObject;

public class PiscesContactResultCallback extends ContactResultCallback {
	@Override
	public float addSingleResult (btManifoldPoint cp, btCollisionObjectWrapper colObj0Wrap, int partId0, int index0,
		btCollisionObjectWrapper colObj1Wrap, int partId1, int index1) {
		Tools.log(WorldObject.getByID(colObj0Wrap.getCollisionObject().getUserValue()).getName()+" vs. "+WorldObject.getByID(colObj1Wrap.getCollisionObject().getUserValue()).getName());

		return 0f;
	}
}
