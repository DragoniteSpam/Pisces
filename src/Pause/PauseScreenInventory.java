package Pause;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PauseScreenInventory implements PauseScreenDrawable {
	private Texture overlayTexture;
	private TextureRegion icon;
	private TextureRegion selectedIcon;
	public PauseScreenInventory(Texture overlayTexture, int iconX, int iconY, int selectedIconX, int selectedIconY) {
		this.overlayTexture=overlayTexture;
		this.icon=new TextureRegion(overlayTexture, iconX, iconY, 64, 64);
		this.selectedIcon=new TextureRegion(overlayTexture, selectedIconX, selectedIconY, 64, 64);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
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

}
