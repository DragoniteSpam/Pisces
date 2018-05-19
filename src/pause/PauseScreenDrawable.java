package pause;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Interface with required methods for pause screen elements.
 * 
 * Do not ask why I decided to make this an interface. Making this a base class
 * would have made my life a LOT easier.
 * 
 * @author mpeng
 * @version 1.0.0
 */

public interface PauseScreenDrawable {
	public void draw(Batch batch, float parentAlpha, boolean canControl);

	public void reset();

	public TextureRegion getIcon();

	public TextureRegion getSelectedIcon();

	public TextureRegion getGrayedIcon();

	public void setCoordinates(float x, float y);

	public float getX();

	public float getY();
	
	public String getName();
}
