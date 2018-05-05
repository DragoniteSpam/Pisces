package stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNode;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.pisces.Tools;

import exceptions.BufferOutOfBoundsException;

public class Buffer {
	private byte[] data;
	private int offset;

	public Buffer(byte[] data) {
		this.data = data;
		offset = 0;
	}

	public long getOffset() {
		return offset;
	}

	public byte getByte() throws BufferOutOfBoundsException {
		if (offset >= data.length) {
			throw new BufferOutOfBoundsException();
		}
		return data[offset++];
	}

	public double getDouble() throws BufferOutOfBoundsException {
		if (offset >= data.length) {
			throw new BufferOutOfBoundsException();
		}
		double value = Tools.bytesToDouble(data, offset);
		offset = offset + 4;

		return value;
	}

	public void skipDouble() throws BufferOutOfBoundsException {
		if (offset >= data.length) {
			throw new BufferOutOfBoundsException();
		}
		offset = offset + 4;
	}

	public char getChar() throws BufferOutOfBoundsException {
		return (char) getDouble();
	}

	public String getString() throws BufferOutOfBoundsException {
		StringBuilder sb = new StringBuilder();
		char character;
		do {
			character = (char) (getDouble());
			if (character!=0) {
				sb.append(character);
			}
		} while (character != 0);
		return sb.toString();
	}

	public Model getModel() throws BufferOutOfBoundsException {
		ModelBuilder builder = new ModelBuilder();
		builder.begin();
		MeshPartBuilder part = builder.part(
				"0", 0, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
						| VertexAttributes.Usage.ColorUnpacked | VertexAttributes.Usage.TextureCoordinates,
				new Material(ColorAttribute.createDiffuse(Color.WHITE)));

		ModelData data=new ModelData();
		ModelNode node=new ModelNode();
		int n = (int) getDouble();

		VertexInfo[] vertices = new VertexInfo[n];
		for (int i = 0; i < n; i++) {
			float x = (float) getDouble();
			float y = (float) getDouble();
			float z = (float) getDouble();
			float nx = (float) getDouble();
			float ny = (float) getDouble();
			float nz = (float) getDouble();
			float xtex = (float) getDouble();
			float ytex = (float) getDouble();
			float color = (float) getDouble();
			float alpha = (float) getDouble();

			float r = 1.0f * ((int) color) % 256;
			float g = 1.0f * (((int) color) % 65536) / 256;
			float b = 1.0f * ((int) color) / 65536;
			vertices[i] = new VertexInfo().setPos(x, y, z).setNor(nx, ny, nz).setCol(new Color(r, g, b, alpha))
					.setUV(xtex, ytex);
			part.triangle(vertices[0], vertices[1], vertices[2]);
		}

		Model model = builder.end();

		return model;
	}

	public btCollisionShape getModelCollision() throws BufferOutOfBoundsException {
		ModelBuilder builder = new ModelBuilder();
		builder.begin();
		MeshPartBuilder part = builder.part("body", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal,
				new Material());

		int n = (int) getDouble();
		
		for (int i = 0; i < n / 3; i++) {
			VertexInfo[] vertices = new VertexInfo[3];
			for (int j = 0; j < 3; j++) {
				float x = (float) getDouble();
				float y = (float) getDouble();
				float z = (float) getDouble();
				float nx = (float) getDouble();
				float ny = (float) getDouble();
				float nz = (float) getDouble();
				skipDouble(); // xtex
				skipDouble(); // ytex
				skipDouble(); // color
				skipDouble(); // alpha
				vertices[j] = new VertexInfo().setPos(x, y, z).setNor(nx, ny, nz);
			}
			part.triangle(vertices[0], vertices[1], vertices[2]);
		}

		Model model = builder.end();
		btCollisionShape shape = Bullet.obtainStaticNodeShape(model.nodes);
		model.dispose();
		return shape;
	}

	public void reset() {
		offset = 0;
	}
}