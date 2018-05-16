package collisions;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Disposable;

public class PiscesCollisions implements Disposable {
	private ArrayList<PiscesCollisionObject> objects;
	private Vector3 min0, max0, min1, max1;
	
	private static Vector3 t1=new Vector3();
	private static Vector3 t2=new Vector3();
	private static Vector3 t3=new Vector3();
	private static Vector3 t4=new Vector3();
	private static Vector3 t5=new Vector3();

	public PiscesCollisions() {
		objects = new ArrayList<PiscesCollisionObject>();
		min0 = new Vector3(0f, 0f, 0f);
		max0 = new Vector3(0f, 0f, 0f);
		min1 = new Vector3(0f, 0f, 0f);
		max1 = new Vector3(0f, 0f, 0f);
	}

	public void dispose() {

	}

	public void addObject(PiscesCollisionObject object) {
		this.objects.add(object);
	}

	public void removeObject(PiscesCollisionObject object) {
		for (int i = 0; i < this.objects.size(); i++) {
			if (this.objects.get(i) == object) {
				this.objects.remove(i);
				break;
			}
		}
	}

	public int getCount() {
		return this.objects.size();
	}

	public int getTriangles() {
		int t = 0;
		for (PiscesCollisionObject object : this.objects) {
			t = t + object.getShape().getSize();
		}
		return t;
	}

	public boolean overlap(PiscesCollisionObject object, int mask) {
		PiscesCollisionShape shape1 = object.getShape();
		min1.set(shape1.getMinimum());
		max1.set(shape1.getMaximum());
		float distance1 = Vector3.dst(min1.x, min1.y, min1.z, max1.x, max1.y, max1.z) * object.scale.x;
		for (PiscesCollisionObject obj : objects) {
			if ((mask & obj.mask) > 0) {
				PiscesCollisionShape shape0 = obj.getShape();
				min0.set(shape0.getMinimum());
				max0.set(shape0.getMaximum());
				float distance0 = Vector3.dst(min0.x, min0.y, min0.z, max0.x, max0.y, max0.z) * obj.scale.x;
				if (Vector3.dst(object.position.x, object.position.y, object.position.z, obj.position.x, obj.position.y,
						obj.position.z) < (distance1 + distance0)) {
					for (PiscesCollisionTriangle triangleBase : shape0.getVertices()) {
						for (PiscesCollisionTriangle triangleTest : shape1.getVertices()) {
							/*
							 * float[] v0 = { triangleBase.vertices[0].position.x,
							 * triangleBase.vertices[0].position.y, triangleBase.vertices[0].position.z };
							 * float[] v1 = { triangleBase.vertices[1].position.x,
							 * triangleBase.vertices[1].position.y, triangleBase.vertices[1].position.z };
							 * float[] v2 = { triangleBase.vertices[2].position.x,
							 * triangleBase.vertices[2].position.y, triangleBase.vertices[2].position.z };
							 * float[] u0 = { triangleTest.vertices[0].position.x,
							 * triangleTest.vertices[0].position.y, triangleTest.vertices[0].position.z };
							 * float[] u1 = { triangleTest.vertices[1].position.x,
							 * triangleTest.vertices[1].position.y, triangleTest.vertices[1].position.z };
							 * float[] u2 = { triangleTest.vertices[2].position.x,
							 * triangleTest.vertices[2].position.y, triangleTest.vertices[2].position.z };
							 * if (triangleIntersect(v0, v1, v2, u0, u1, u2)) {
							 * System.out.println("Intersected!"); }
							 */
							if (triangleIntersect(triangleBase, triangleTest)) {
								System.out.println("Intersected!");
							}
						}
					}
				}
			}
		}
		return false;
	}

	public boolean raycast(Vector3 from, Vector3 to, int mask) {
		return false;
	}

	private boolean triangleIntersect(PiscesCollisionTriangle triangle1, PiscesCollisionTriangle triangle2) {
		/*Vector3 n1 = getNormal(triangle1);
	    Vector3 n2 = getNormal(triangle2);
	    cross(n1, n2, t3);

	    float pxy = n1.z * triangle1.vertices[0].position.z * n1.x * n1.y;
	    float px = n1.z * triangle1.vertices[0].position.z * n1.x * n1.y * triangle1.vertices[0].position.y;
	    float py = n1.z * triangle1.vertices[0].position.z * n1.x * triangle1.vertices[0].position.x * n1.y;
	    float p0 = n1.z * triangle1.vertices[0].position.z * n1.x * triangle1.vertices[0].position.x * n1.y * triangle1.vertices[0].position.y;

	    t4.x = (p0/(px/pxy))/pxy;
	    t4.y = (p0/(py/pxy))/pxy;
	    t4.z=0;

	    add(t4, t3, t5);

	    Vector3 xq1 = null;
	    xq1 = Vector3.sub(t5, triangle1.vertices[0].position, xq1);
	    float i1 = Vector3.dot(xq1, n1);

	    Vector3 xq2 = null;
	    xq2 = Vector3.sub(x, triangle2.vertices[0].position, xq2);
	    float i2 = Vector3.dot(xq2, n2);

	    if (i1 == 0 && i2 == 0) {
	        return true;
	    }*/
	    return false;
	}

	private Vector3 getNormal(PiscesCollisionTriangle triangle) {
		t1.set(triangle.vertices[1].position).sub(triangle.vertices[0].position);
		t2.set(triangle.vertices[2].position).sub(triangle.vertices[0].position);
		return t1.crs(t2);
	}
	
	private void cross(Vector3 v0, Vector3 v1, Vector3 out) {
		out.x = v1.y * v0.z - v0.y * v1.z;
        out.y = v1.x * v0.z - v0.x * v1.z;
        out.z = v1.x * v0.y - v0.x * v1.y;
	}
	
	private void add(Vector3 v0, Vector3 v1, Vector3 out) {
		out.x=v0.x+v1.x;
		out.y=v0.y+v1.y;
		out.z=v0.z+v1.z;
	}

	/*
	 * private boolean triangleIntersect(float[] v0, float[] v1, float[] v2, float[]
	 * u0, float[] u1, float[] u2) { float[] e1 = new float[3]; float[] e2 = new
	 * float[3]; float[] n1 = new float[3]; float d1, du0, du1, du2, du0du1, du0du2;
	 * 
	 * sub(e1, v1, v0); sub(e2, v2, v0);
	 * 
	 * cross(n1, e1, e2);
	 * 
	 * d1 = -dot(n1, v0); du0 = dot(n1, v0) + d1; du1 = dot(n1, u1) + d1; du2 =
	 * dot(n1, u2) + d1; du0du1 = du0 * du1; du0du2 = du0 * du2;
	 * 
	 * if (du0du1 > 0.0f && du0du2 > 0.0f) { return false; }
	 * 
	 * float[] n2 = new float[3];
	 * 
	 * sub(e1, u1, u0); sub(e2, u2, u0); cross(n2, e1, e2);
	 * 
	 * float d2 = -dot(n2, u0);
	 * 
	 * float dv0 = dot(n2, v0) + d2; float dv1 = dot(n2, v1) + d2; float dv2 =
	 * dot(n2, v2) + d2; float dv0dv1 = dv0 * dv1; float dv0dv2 = dv0 * dv2;
	 * 
	 * if (dv0dv1 > 0.0f && dv0dv2 > 0.0f) { return false; }
	 * 
	 * float[] d = new float[3]; cross(d, n1, n2); float max = Math.abs(d[0]), bb =
	 * Math.abs(d[1]), cc = Math.abs(d[2]); short index = 0; if (bb > max) { max =
	 * bb; index = 1; } else { if (cc > max) { max = cc; index = 2; } }
	 * 
	 * float vp0 = v0[index]; float vp1 = v1[index]; float vp2 = v2[index]; float
	 * up0 = u0[index]; float up1 = u1[index]; float up2 = u2[index]; float a=0;
	 * float b=0; float c=0; float x0=0; float x1=0; float lowercaseD=0; float e=0;
	 * float f=0; float y0=0; float y1=0;;
	 * 
	 * float[] nc1 = newcomputeIntervals(vp0, vp1, vp2, dv0, dv1, dv2, dv0dv1,
	 * dv0dv2, a, b, c, x0, x1);
	 * 
	 * if (nc1 == null) { return false; }
	 * 
	 * vp0 = nc1[0]; vp1 = nc1[1]; vp2 = nc1[2]; dv0 = nc1[3]; dv1 = nc1[4]; dv2 =
	 * nc1[5]; dv0dv1 = nc1[6]; dv0dv2 = nc1[7]; a = nc1[8]; b = nc1[9]; c =
	 * nc1[10]; x0 = nc1[11]; x1 = nc1[12];
	 * 
	 * float[] nc2 = newcomputeIntervals(up0, up1, up2, du0, du1, du2, du0du1,
	 * du0du2, lowercaseD, e, f, y0, y1);
	 * 
	 * if (nc2 == null) { return false; }
	 * 
	 * up0 = nc2[0]; up1 = nc2[1]; up2 = nc2[2]; du0 = nc2[3]; du1 = nc2[4]; du2 =
	 * nc2[5]; du0du1 = nc2[6]; du0du2 = nc2[7]; lowercaseD = nc2[8]; e = nc2[9]; f
	 * = nc2[10]; y0 = nc2[11]; y1 = nc2[12];
	 * 
	 * float xx = x0 * x1; float[] isect1 = new float[2]; float[] isect2 = new
	 * float[2]; float yy = y0 * y1; float xxyy = xx * yy; float tmp = a * xxyy;
	 * isect1[0] = tmp + b * x1 * yy; isect1[1] = tmp + c * x0 * yy; tmp =
	 * lowercaseD * xxyy; isect2[0] = tmp + e * xx * y1; isect2[1] = tmp + f * xx *
	 * y0; if (!sort(isect1[0], isect1[1])) { float tmp2 = isect1[0]; isect1[0] =
	 * isect1[1]; isect1[1] = tmp2; } if (!sort(isect2[0], isect2[1])) { float tmp2
	 * = isect2[0]; isect2[0] = isect2[1]; isect2[1] = tmp2; }
	 * 
	 * if (isect1[1] < isect2[0] || isect2[1] < isect1[0]) return false;
	 * 
	 * return true; }
	 * 
	 * private void cross(float[] destination, float[] v1, float[] v2) {
	 * destination[0] = v1[1] * v2[2] - v1[2] * v2[1]; destination[1] = v1[2] *
	 * v2[0] - v1[0] * v2[2]; destination[2] = v1[0] * v2[1] - v1[1] * v2[0]; }
	 * 
	 * private float dot(float[] v1, float[] v2) { return (v1[0] * v2[0] + v1[1] *
	 * v2[1] + v1[2] * v2[2]); }
	 * 
	 * private void sub(float[] destination, float[] v1, float[] v2) {
	 * destination[0] = v1[0] - v2[0]; destination[1] = v1[1] - v2[1];
	 * destination[2] = v1[2] - v2[2]; }
	 * 
	 * private float[] newcomputeIntervals(float vv0, float vv1, float vv2, float
	 * d0, float d1, float d2, float d0d1, float d0d2, float a, float b, float c,
	 * float x0, float x1) { if (d0d1 > 0.0f) { a = vv2; b = (vv0 - vv2) * d2; c =
	 * (vv1 - vv2) * d2; x0 = d2 - d0; x1 = d2 - d1; } else if (d0d2 > 0.0f) { a =
	 * vv1; b = (vv0 - vv1) * d1; c = (vv2 - vv1) * d1; x0 = d1 - d0; x1 = d1 - d2;
	 * } else if (d1 * d2 > 0.0f || d0 != 0.0f) { a = vv0; b = (vv1 - vv0) * d0; c =
	 * (vv2 - vv0) * d0; x0 = d0 - d1; x1 = d0 - d2; } else if (d1 != 0.0f) { a =
	 * vv1; b = (vv0 - vv1) * d1; c = (vv2 - vv1) * d1; x0 = d1 - d0; x1 = d1 - d2;
	 * } else if (d2 != 0.0f) { a = vv2; b = (vv0 - vv2) * d2; c = (vv1 - vv2) * d2;
	 * x0 = d2 - d0; x1 = d2 - d1; } else { return null; } float[] result = { vv0,
	 * vv1, vv2, d0, d1, d2, d0d1, d0d2, a, b, c, x0, x1 }; return result; }
	 * 
	 * private boolean sort(float a, float b) { return a <= b; }
	 */
}
