package collisions;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class PiscesCollisionShape {
	private ArrayList<PiscesCollisionTriangle> triangles;
	private Vector3 minimum;
	private Vector3 maximum;

	public PiscesCollisionShape() {
		triangles = new ArrayList<PiscesCollisionTriangle>();
		this.minimum=new Vector3(0, 0, 0);
		this.maximum=new Vector3(0, 0, 0);
	}

	public void addTriangle(PiscesCollisionTriangle triangle) {
		triangles.add(triangle);
		/*this.minimum.x=Math.min(this.minimum.x, triangle.getMinX());
		this.minimum.y=Math.min(this.minimum.y, triangle.getMinY());
		this.minimum.z=Math.min(this.minimum.z, triangle.getMinZ());
		
		this.maximum.x=Math.max(this.maximum.x, triangle.getMinX());
		this.maximum.y=Math.max(this.maximum.y, triangle.getMinY());
		this.maximum.z=Math.max(this.maximum.z, triangle.getMinZ());*/
	}

	public int getSize() {
		return triangles.size();
	}

	public void setModel(Model model) {
		this.triangles.clear();
		for (Node node : model.nodes) {
			for (NodePart np : node.parts) {
				int vertexSize = np.meshPart.mesh.getVertexSize() / 4;
				float[] vertices = new float[np.meshPart.mesh.getNumVertices() * vertexSize];
				np.meshPart.mesh.getVertices(vertices);
				for (int i = 0; i < vertices.length-vertexSize*3; i += vertexSize * 3) {
					this.triangles.add(new PiscesCollisionTriangle(vertices[i], vertices[i + 1], vertices[i + 2],
							vertices[i + vertexSize], vertices[i + vertexSize + 1], vertices[i + vertexSize + 2],
							vertices[i + vertexSize * 2], vertices[i + vertexSize * 2 + 1],
							vertices[i + vertexSize * 2 + 2], vertices[i + 3], vertices[i + 4], vertices[i + 5]));
					//System.out.println(this.triangles.get(this.triangles.size()-1));
				}
			}
		}
		BoundingBox box=new BoundingBox();
		model.calculateBoundingBox(box);
		this.minimum.set(box.min);
		this.maximum.set(box.max);
	}
	
	public Vector3 getMinimum() {
		return this.minimum;
	}
	
	public Vector3 getMaximum() {
		return this.maximum;
	}
	
	public ArrayList<PiscesCollisionTriangle> getVertices(){
		return this.triangles;
	}
}
