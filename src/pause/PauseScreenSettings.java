package pause;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import stuff.pause.PauseStages;

public class PauseScreenSettings implements PauseScreenDrawable {
	private Texture overlayTexture;
	private TextureRegion icon;
	private TextureRegion selectedIcon;
	private TextureRegion grayedIcon;
	private float x;
	private float y;
	private String name;
	
	public PauseScreenSettings(Texture overlayTexture, int iconX, int iconY, int selectedIconX, int selectedIconY, int grayedIconX, int grayedIconY, String name) {
		this.overlayTexture = overlayTexture;
		this.icon = new TextureRegion(overlayTexture, iconX, iconY, 64, 64);
		this.selectedIcon=new TextureRegion(overlayTexture, selectedIconX, selectedIconY, 64, 64);
		this.grayedIcon=new TextureRegion(overlayTexture, grayedIconX, grayedIconY, 64, 64);
		this.name=name;
		
		setCoordinates(0, 0);
	}

	@Override
	public void draw(Batch batch, float parentAlpha, boolean canControl) {
		// TODO Auto-generated method stub

	}

	public void reset() {

	}

	public TextureRegion getIcon() {
		return this.icon;
	}
	
	public TextureRegion getSelectedIcon() {
		return this.selectedIcon;
	}

	@Override
	public void setCoordinates(float x, float y) {
		this.x=x;
		this.y=y;
	}

	@Override
	public float getX() {
		return this.x;
	}

	@Override
	public float getY() {
		return this.y;
	}

	@Override
	public TextureRegion getGrayedIcon() {
		return this.grayedIcon;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
