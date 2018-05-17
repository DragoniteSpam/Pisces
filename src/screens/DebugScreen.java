package screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pisces.Pisces;
import com.pisces.Tools;

import WorldObjects.WorldEntityPlayer;

public class DebugScreen extends Actor {
	private ShapeRenderer shapes;
	private Texture overlayTexture;
	private TextureRegion background;

	public DebugScreen(Texture overlayTexture) {
		this.overlayTexture=overlayTexture;
		this.background=new TextureRegion(overlayTexture, 0, 960, 1920, 1080);
		this.shapes = new ShapeRenderer();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Pisces pisces = Pisces.me();
		BitmapFont font12 = pisces.getFont12();
		BitmapFont font20 = pisces.getFont20();
		BitmapFont font32 = pisces.getFont32();

		switch (pisces.getDebugState()) {
		case OFF:
			break;
		case LOG:
			batch.setColor(1f,  1f,  1f, 0.5f);
			batch.draw(background, 0, 0);
			
			font32.setColor(Color.BLACK);
			font12.setColor(Color.BLACK);
			font32.draw(batch, "Game Log", 32, Gdx.graphics.getHeight()-32);
			ArrayList<String> log = Tools.getLog();
			if (log.size() > 0) {
				int i = log.size();
				int y = 32;
				do {
					font12.draw(batch, log.get(i-1), 32, y);
					y = y + 16;
					i--;
				} while (i > 0 && y < Gdx.graphics.getHeight() - 64);
				break;
			}
		default:
			break;
		}
	}

}
