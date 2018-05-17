package Pause;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface PauseScreenDrawable {
	public void draw(Batch batch, float parentAlpha);
	public void reset();
	public TextureRegion getIcon();
	public TextureRegion getSelectedIcon();
}
