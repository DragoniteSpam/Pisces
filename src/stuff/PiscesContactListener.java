package stuff;

import com.badlogic.gdx.physics.bullet.collision.ContactListener;
import com.pisces.Tools;

import WorldObjects.WorldObject;

public class PiscesContactListener extends ContactListener {
	@Override
	public boolean onContactAdded(int userValue0, int partId0, int index0, int userValue1, int partId1, int index1) {

		Tools.log(
				"Collision between " + WorldObject.getByID(userValue0) + " and " + WorldObject.getByID(userValue1));

		WorldObject.getByID(userValue0).moveToPrevious();
		WorldObject.getByID(userValue1).moveToPrevious();

		return true;
	}
}
