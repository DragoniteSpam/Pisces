package stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Vertex {
	private Vector3 position;
	private Vector3 normal;
	private Vector2 tex;
	private Color color;
	private float alpha;
	
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
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public float getZ() {
		return position.z;
	}
	
	public Vector3 getPosition() {
		return position;
	}
	
	public float getNX() {
		return normal.x;
	}
	
	public float getNY() {
		return normal.y;
	}
	
	public float getNZ() {
		return normal.z;
	}
	
	public Vector3 getNormals() {
		return normal;
	}
	
	public float getXTex() {
		return tex.x;
	}
	
	public float getYTex() {
		return tex.y;
	}
	
	public Vector2 getTex() {
		return tex;
	}
	
	public Color getColor() {
		return color;
	}
	
	public float getAlpha() {
		return alpha;
	}
}
