package screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pisces.Pisces;
import com.pisces.PiscesController;
import com.pisces.Tools;

import Pause.PauseScreenDrawable;
import Pause.PauseScreenInventory;
import Pause.PauseScreenMap;
import Pause.PauseScreenSave;
import Pause.PauseScreenSettings;
import Pause.PauseScreenTeam;
import WorldObjects.WorldEntityPlayer;
import stuff.PauseStages;

public class PauseScreen extends Actor {
	private Texture overlayTexture;
	private TextureRegion background;
	private PauseStages screen;
	private ArrayList<PauseScreenDrawable> screens;
	
	private int position;
	
	public PauseScreen(Texture overlayTexture) {
		this.overlayTexture=overlayTexture;
		this.background=new TextureRegion(overlayTexture, 0, 960, 1920, 1080);
		
		screens=new ArrayList<PauseScreenDrawable>();
		screens.add(new PauseScreenInventory(overlayTexture, 256, 0, 256, 64));
		screens.add(new PauseScreenMap(overlayTexture, 320, 0, 320, 64));
		screens.add(new PauseScreenTeam(overlayTexture, 384, 0, 384, 64));
		screens.add(new PauseScreenSave(overlayTexture, 448, 0, 448, 64));
		screens.add(new PauseScreenSettings(overlayTexture, 512, 0, 512, 64));
		
		reset();
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
		Pisces pisces=Pisces.me();
		PiscesController controller=pisces.getController();
		BitmapFont font12=pisces.getFont12();
		BitmapFont font20=pisces.getFont20();
		BitmapFont font32=pisces.getFont32();
		WorldEntityPlayer player=pisces.getPlayer();
		
		switch (screen) {
		case MAIN:
			float middleX=Gdx.graphics.getWidth()/2.0f;
			float iconWidth=64;
			int n=screens.size()-1;
			float startX=(float) (middleX-(iconWidth+32)*n/2.0);
			for (int i=0; i<screens.size(); i++) {
				if (position==i) {
					batch.draw(screens.get(i).getSelectedIcon(), startX+(32+iconWidth)*i, 80);
				} else {
					batch.draw(screens.get(i).getIcon(), startX+(32+iconWidth)*i, 80);
				}
			}
			int size=screens.size();
			if (controller.padLeftRelease()) {
				position=(--position+size)%size;
			}
			if (controller.padRightRelease()) {
				position=(++position)%size;
			}
			if (controller.bRelease()) {
				pisces.unpauseGame();
			}
			break;
		case INVENTORY:
			break;
		case MAP:
			break;
		case SAVE:
			break;
		case SETTINGS:
			break;
		case TEAM:
			break;
		default:
			break;
		}
	}
	
	public void reset() {
		this.screen=PauseStages.MAIN;
		this.position=0;
		
		for (PauseScreenDrawable screen : screens) {
			screen.reset();
		}
	}
}
