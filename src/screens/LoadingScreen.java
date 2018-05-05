package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.pisces.Pisces;

public class LoadingScreen {
	private Texture texturePisces;
	private TextureRegion textureRegionPisces;
	GlyphLayout loadingButton;
	
	public LoadingScreen() {
		texturePisces = new Texture("graphics/pisces.png");
		textureRegionPisces = new TextureRegion(texturePisces);
		loadingButton=new GlyphLayout(Pisces.me().getFont20(), "Loading . . .");
	}
	
	public void render(SpriteBatch spriteBatch, long frames) {
		float fishX = textureRegionPisces.getRegionWidth() / 2;
		float fishY = textureRegionPisces.getRegionHeight() / 2;
		spriteBatch.draw(textureRegionPisces, Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f,
				fishX, fishY, fishX * 2, fishY * 2, 2.0f, 2.0f, 2f*frames);
		Pisces.me().getFont20().draw(spriteBatch, loadingButton, Gdx.graphics.getWidth()/2.0f, Gdx.graphics.getHeight()/2-fishY*8f);
		/*loadingButton.setPosition(Gdx.graphics.getWidth()/2.0f, Gdx.graphics.getHeight()/2.0f+fishY*8.0f);
		loadingButton.draw(spriteBatch,  1.0f);*/
		/*font20.draw(spriteBatch, "Loading . . .", Gdx.graphics.getWidth() / 2 * 1.0f,
				Gdx.graphics.getHeight() / 2.0f - fishY * 8.0f);*/
	}
}
