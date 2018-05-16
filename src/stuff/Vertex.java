package stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Vertex {
	public Vector3 position;
	public Vector3 normal;
	public Vector2 tex;
	public Color color;
	public float alpha;
	
	public Vertex(Vector3 position, Vector3 normal, Vector2 tex, Color color, float alpha) {
		this.position=position;
		this.normal=normal;
		this.tex=tex;
		this.color=color;
		this.alpha=alpha;
	}
	
	public Vertex(float x, float y, float z, float nx, float ny, float nz, float xtex, float ytex, Color color, float alpha) {
		this.position=new Vector3(x, y, z);
		this.normal=new Vector3(nx, ny, nz);
		this.tex=new Vector2(xtex,  ytex);
		this.color=color;
		this.alpha=alpha;
	}
	
	public Vertex(float x, float y, float z, float nx, float ny, float nz) {
		this.position=new Vector3(x, y, z);
		this.normal=new Vector3(nx, ny, nz);
		this.tex=new Vector2(0, 0);
		this.color=Color.WHITE;
		this.alpha=1f;
	}
	
	public String toString() {
		return this.position.toString();
	}
}
