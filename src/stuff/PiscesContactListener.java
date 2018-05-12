package stuff;

import com.badlogic.gdx.physics.bullet.collision.ContactCache;
import com.badlogic.gdx.physics.bullet.collision.ContactListener;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObjectWrapper;
import com.badlogic.gdx.physics.bullet.collision.btManifoldPoint;
import com.badlogic.gdx.physics.bullet.collision.btPersistentManifold;

import WorldObjects.WorldObject;

public class PiscesContactListener extends ContactListener {
	public boolean onContactAdded (int userValue0, int partId0, int index0, boolean match0, int userValue1, int partId1, int index1, boolean match1) {
		System.out.println("Collision detected between "+userValue0+" and "+userValue1);
		/*WorldObject.getByID(userValue0).moveToPrevious();
		WorldObject.getByID(userValue1).moveToPrevious();*/
		return true;
	}
	
	/*public void onContactStarted(btPersistentManifold manifold, boolean match0, boolean match1) {
		System.out.println("Collision detected between "+manifold.getBody0()+" and "+manifold.getBody1());
		/*WorldObject.getByID(userValue0).moveToPrevious();
		WorldObject.getByID(userValue1).moveToPrevious();* /
		//return true;
	}*/
}
