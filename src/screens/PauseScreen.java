package screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

import WorldObjects.WorldEntityPlayer;
import pause.PauseScreenDrawable;
import pause.PauseScreenInventory;
import pause.PauseScreenMap;
import pause.PauseScreenMovesSkills;
import pause.PauseScreenQuit;
import pause.PauseScreenSave;
import pause.PauseScreenSettings;
import pause.PauseScreenStuff;
import pause.PauseScreenTeam;
import stuff.pause.PauseStages;

public class PauseScreen extends Actor {
	private static int OPTION_INVENTORY=0;
	private static int OPTION_MAP=1;
	private static int OPTION_MOVES_SKILLS=2;
	private static int OPTION_TEAM=3;
	private static int OPTION_STUFF=4;
	private static int OPTION_SAVE=5;
	private static int OPTION_SETTINGS=6;
	private static int OPTION_QUIT=7;
	
	private Texture overlayTexture;
	private TextureRegion background;
	private TextureRegion label;
	private PauseStages screen;
	private ArrayList<PauseScreenDrawable> screens;

	private int position;

	public PauseScreen(Texture overlayTexture) {
		this.overlayTexture = overlayTexture;
		this.background = new TextureRegion(overlayTexture, 0, 960, 1920, 1080);
		this.label=new TextureRegion(overlayTexture, 512, 704, 448, 64);

		screens = new ArrayList<PauseScreenDrawable>();
		screens.add(new PauseScreenInventory(overlayTexture, 256, 0, 256, 64, 256, 128, "Inventory"));
		screens.add(new PauseScreenMap(overlayTexture, 320, 0, 320, 64, 320, 128, "Map"));
		screens.add(new PauseScreenMovesSkills(overlayTexture, 832, 0, 832, 64, 832, 128, "Moves and Skills"));
		screens.add(new PauseScreenTeam(overlayTexture, 384, 0, 384, 64, 384, 128, "Team"));
		screens.add(new PauseScreenStuff(overlayTexture, 960, 0, 960, 64, 960, 128, "Stuff"));
		screens.add(new PauseScreenSave(overlayTexture, 448, 0, 448, 64, 448, 128, "Save"));
		screens.add(new PauseScreenSettings(overlayTexture, 512, 0, 512, 64, 512, 128, "Settings"));
		screens.add(new PauseScreenQuit(overlayTexture, 576, 0, 576, 64, 576, 128, "Quit"));
		
		int n = screens.size() - 1;
		float middleX = Gdx.graphics.getWidth() / 2.0f;
		float iconWidth = screens.get(0).getIcon().getRegionWidth();
		float startX = (float) (middleX - (iconWidth + 32) * n / 2.0);
		
		for (int i=0; i<screens.size(); i++) {
			screens.get(i).setCoordinates(startX + (32 + iconWidth) * i, 80);
		}

		reset(true);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		PauseScreenDrawable currentScreen;
		switch (screen) {
		case MAIN:
			drawMainMenu(batch, true);
			break;
		case INVENTORY:
			drawMainMenu(batch, false);
			currentScreen=screens.get(OPTION_INVENTORY);
			currentScreen.draw(batch, parentAlpha, true);
			drawTitleCard(batch, currentScreen.getName());
			break;
		case MAP:
			drawMainMenu(batch, false);
			currentScreen=screens.get(OPTION_MAP);
			currentScreen.draw(batch, parentAlpha, true);
			drawTitleCard(batch, currentScreen.getName());
			break;
		case MOVESSKILLS:
			drawMainMenu(batch, false);
			currentScreen=screens.get(OPTION_MOVES_SKILLS);
			currentScreen.draw(batch, parentAlpha, true);
			drawTitleCard(batch, currentScreen.getName());
			break;
		case TEAM:
			drawMainMenu(batch, false);
			currentScreen=screens.get(OPTION_TEAM);
			currentScreen.draw(batch, parentAlpha, true);
			drawTitleCard(batch, currentScreen.getName());
			break;
		case STUFF:
			drawMainMenu(batch, false);
			currentScreen=screens.get(OPTION_STUFF);
			currentScreen.draw(batch, parentAlpha, true);
			drawTitleCard(batch, currentScreen.getName());
			break;
		case SAVE:
			drawMainMenu(batch, false);
			currentScreen=screens.get(OPTION_SAVE);
			currentScreen.draw(batch, parentAlpha, true);
			drawTitleCard(batch, currentScreen.getName());
			break;
		case SETTINGS:
			drawMainMenu(batch, false);
			currentScreen=screens.get(OPTION_SETTINGS);
			currentScreen.draw(batch, parentAlpha, true);
			drawTitleCard(batch, currentScreen.getName());
			break;
		case QUIT:
			drawMainMenu(batch, false);
			currentScreen=screens.get(OPTION_QUIT);
			currentScreen.draw(batch, parentAlpha, true);
			drawTitleCard(batch, currentScreen.getName());
			break;
		default:
			break;
		}
	}

	public void reset(boolean resetPosition) {
		this.screen = PauseStages.MAIN;
		
		if (resetPosition) {
			this.position=0;
		}

		for (PauseScreenDrawable screen : screens) {
			screen.reset();
		}
	}

	private void drawMainMenu(Batch batch, boolean canControl) {
		Pisces pisces = Pisces.me();
		PiscesController controller = pisces.getController();
		
		int n = screens.size() - 1;
		float middleX = Gdx.graphics.getWidth() / 2.0f;
		float iconWidth = screens.get(0).getIcon().getRegionWidth();
		float iconHeight = screens.get(0).getIcon().getRegionHeight();
		float startX = (float) (middleX - (iconWidth + 32) * n / 2.0);
		
		for (int i = 0; i < screens.size(); i++) {
			PauseScreenDrawable s=screens.get(i);
			if (position == i) {
				batch.draw(screens.get(i).getSelectedIcon(), s.getX(), s.getY());
			} else if (!canControl){
				batch.draw(screens.get(i).getGrayedIcon(), s.getX(), s.getY());
			} else {
				batch.draw(screens.get(i).getIcon(), s.getX(), s.getY());
			}
		}

		if (canControl) {
			
			/*
			 * Scrolling
			 */
			
			int size = screens.size();
			if (controller.padLeftRelease()) {
				position = (--position + size) % size;
				controller.setMousePosition(screens.get(position).getX() + iconWidth / 2, 80 + iconHeight / 2);
			}
			if (controller.padRightRelease()) {
				position = (++position) % size;
				controller.setMousePosition(screens.get(position).getX() + iconWidth / 2, 80 + iconHeight / 2);
			}
			position = Tools.clamp((int) Math.floor((controller.getMouseX() - startX) / (32 + iconWidth)), 0, n);

			/*
			 * Forwards/backwards
			 */

			if (controller.bRelease()) {
				pisces.unpauseGame();
			}

			if (controller.aRelease()) {
				// Can't use the enum here because the values aren't 1:1 with the menu
				switch (position) {
				case 0:
					screen=PauseStages.INVENTORY;
					break;
				case 1:
					screen=PauseStages.MAP;
					break;
				case 2:
					screen=PauseStages.MOVESSKILLS;
					break;
				case 3:
					screen=PauseStages.TEAM;
					break;
				case 4:
					screen=PauseStages.STUFF;
					break;
				case 5:
					screen=PauseStages.SAVE;
					break;
				case 6:
					screen=PauseStages.SETTINGS;
					break;
				case 7:
					screen=PauseStages.QUIT;
					break;
				}
			}
		}
	}
	
	private void drawTitleCard(Batch batch, String name) {
		BitmapFont font32=Pisces.me().getFont32();
		
		batch.draw(this.label, 0, 15*64);
		font32.setColor(Color.BLACK);
		font32.draw(batch, name, 64, 15*64+48);
		
	}
}
