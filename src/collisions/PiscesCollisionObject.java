package collisions;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class PiscesCollisionObject {
	private PiscesCollisionShape shape;
	private BoundingBox boundingBox;
	public Vector3 position;
	public Quaternion orientation;
	public Vector3 scale;
	public int mask;
	public int userID;
	
	public PiscesCollisionObject(PiscesCollisionShape shape, int mask, int userID) {
		setShape(shape);
		this.mask=mask;
		this.userID=userID;
	}
	
	public void setShape(PiscesCollisionShape shape) {
		this.shape=shape;
		this.boundingBox=new BoundingBox(this.shape.getMinimum(), this.shape.getMaximum());
	}
	
	public PiscesCollisionShape getShape() {
		return this.shape;
	}
	
	public BoundingBox getBoundingBox() {
		return this.boundingBox;
	}
}
