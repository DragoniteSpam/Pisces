package collisions;

import stuff.Vertex;

public class PiscesCollisionTriangle {
	public Vertex[] vertices=new Vertex[3];
	
	public PiscesCollisionTriangle(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float nx, float ny, float nz) {
		vertices[0]=new Vertex(x1, y1, z1, nx, ny, nz);
		vertices[1]=new Vertex(x2, y2, z2, nx, ny, nz);
		vertices[2]=new Vertex(x3, y3, z3, nx, ny, nz);
	}
	
	public float getMinX() {
		return Math.min(Math.min(vertices[0].position.x, vertices[1].position.x), vertices[2].position.x);
	}
	
	public float getMinY() {
		return Math.min(Math.min(vertices[0].position.y, vertices[1].position.y), vertices[2].position.y);
	}
	
	public float getMinZ() {
		return Math.min(Math.min(vertices[0].position.z, vertices[1].position.z), vertices[2].position.z);
	}
	
	public float getMaxX() {
		return Math.max(Math.max(vertices[0].position.x, vertices[1].position.x), vertices[2].position.x);
	}
	
	public float getMaxY() {
		return Math.max(Math.max(vertices[0].position.y, vertices[1].position.y), vertices[2].position.y);
	}
	
	public float getMaxZ() {
		return Math.max(Math.max(vertices[0].position.z, vertices[1].position.z), vertices[2].position.z);
	}
	
	public String toString() {
		return vertices[0]+"|"+vertices[1]+"|"+vertices[2];
	}
}
