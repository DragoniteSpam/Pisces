package pause;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import stuff.pause.PauseStages;

public class PauseScreenInventory implements PauseScreenDrawable {
	private Texture overlayTexture;
	private TextureRegion icon;
	private TextureRegion selectedIcon;
	private TextureRegion grayedIcon;
	private float x;
	private float y;
	private String name;
	private TextureRegion inventoryBackgroundBackground;
	
	private TextureRegion summaryBackground;
	private TextureRegion inventoryBackground;
	
	public PauseScreenInventory(Texture overlayTexture, int iconX, int iconY, int selectedIconX, int selectedIconY, int grayedIconX, int grayedIconY, String name) {
		this.overlayTexture=overlayTexture;
		this.icon=new TextureRegion(overlayTexture, iconX, iconY, 64, 64);
		this.selectedIcon=new TextureRegion(overlayTexture, selectedIconX, selectedIconY, 64, 64);
		this.grayedIcon=new TextureRegion(overlayTexture, grayedIconX, grayedIconY, 64, 64);
		this.name=name;
		
		this.summaryBackground=new TextureRegion(overlayTexture, 0, 192, 512, 576);
		this.inventoryBackground=new TextureRegion(overlayTexture, 512, 192, 768, 512);
		this.inventoryBackgroundBackground=new TextureRegion(overlayTexture, 1280, 192, 832, 576);
		
		setCoordinates(0, 0);
	}

	@Override
	public void draw(Batch batch, float parentAlpha, boolean canControl) {
		batch.draw(this.summaryBackground, 256, 256);
		batch.draw(this.inventoryBackgroundBackground, 864, 256);
		batch.draw(this.inventoryBackground, 896, 288);
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
