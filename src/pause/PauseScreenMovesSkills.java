package pause;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pisces.Pisces;
import com.pisces.PiscesController;
import com.pisces.Tools;

import stuff.pause.PauseInventoryStages;
import stuff.pause.PauseMovesSkillsStages;
import stuff.pause.PauseStages;

public class PauseScreenMovesSkills implements PauseScreenDrawable {
	private static int OPTION_MOVES=0;
	private static int OPTION_SKILLS=1;
	
	private Texture overlayTexture;
	private TextureRegion icon;
	private TextureRegion selectedIcon;
	private TextureRegion grayedIcon;
	private ArrayList<PauseScreenDrawable> screens;
	private float x;
	private float y;
	private String name;
	private int position;
	private PauseMovesSkillsStages screen;
	
	public PauseScreenMovesSkills(Texture overlayTexture, int iconX, int iconY, int selectedIconX, int selectedIconY, int grayedIconX, int grayedIconY, String name) {
		this.overlayTexture = overlayTexture;
		this.icon = new TextureRegion(overlayTexture, iconX, iconY, 64, 64);
		this.selectedIcon=new TextureRegion(overlayTexture, selectedIconX, selectedIconY, 64, 64);
		this.grayedIcon=new TextureRegion(overlayTexture, grayedIconX, grayedIconY, 64, 64);
		this.name=name;
		
		this.position=0;
		this.screen=PauseMovesSkillsStages.MAIN;
		
		this.screens=new ArrayList<PauseScreenDrawable>();
		this.screens.add(new PauseScreenMovesSkillsMoves(overlayTexture, 896, 0, 896, 64, 896, 128, "Moves"));
		this.screens.add(new PauseScreenMovesSkillsSkills(overlayTexture, 768, 0, 768, 64, 768, 128, "Skills"));
		setCoordinates(0, 0);
	}

	@Override
	public void draw(Batch batch, float parentAlpha, boolean canControl) {
		Pisces pisces=Pisces.me();
		PiscesController controller=pisces.getController();
		
		switch (screen) {
		case MAIN:
			float middleX = x;
			float iconWidth = screens.get(0).getIcon().getRegionWidth();
			float iconHeight = screens.get(0).getIcon().getRegionHeight();
			int n = screens.size() - 1;
			float startX = (float) (middleX - (iconWidth + 32) * n / 2.0);
			for (int i = 0; i < screens.size(); i++) {
				PauseScreenDrawable s=screens.get(i);
				if (position == i) {
					batch.draw(s.getSelectedIcon(), s.getX(), s.getY());
				} else {
					batch.draw(s.getIcon(), s.getX(), s.getY());
				}
			}
			if (canControl) {
				
				/*
				 * Scrolling
				 */
				
				int size = screens.size();
				if (controller.padLeftRelease()) {
					position = (--position + size) % size;
					controller.setMousePosition(screens.get(position).getX() + iconWidth / 2, screens.get(position).getY()+iconHeight / 2);
				}
				if (controller.padRightRelease()) {
					position = (++position) % size;
					controller.setMousePosition(screens.get(position).getX() + iconWidth / 2, screens.get(position).getY()+iconHeight / 2);
				}
				
				/*
				 * Forwards/backwards
				 */
				
				if (controller.bRelease()) {
					pisces.getPauseScreen().reset(false);
				}
				if (controller.aRelease()) {
					switch (position) {
					case 0:
						screen=PauseMovesSkillsStages.MOVES;
						break;
					case 1:
						screen=PauseMovesSkillsStages.SKILLS;
						break;
					}
				}
				position = Tools.clamp((int) Math.floor((controller.getMouseX() - startX) / (32 + iconWidth)), 0, n);
			}
			break;
		case MOVES:
			/*
			 * Navigation
			 */
			if (controller.bRelease()) {
				screen=PauseMovesSkillsStages.MAIN;
			}
			break;
		case SKILLS:
			/*
			 * Navigation
			 */
			if (controller.bRelease()) {
				screen=PauseMovesSkillsStages.MAIN;
			}
			break;
		}
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
		
		int n = screens.size() - 1;
		float middleX = x;
		float iconWidth = screens.get(0).getIcon().getRegionWidth();
		float startX = (float) (middleX - (iconWidth + 32) * n / 2.0);
		
		for (int i=0; i<screens.size(); i++) {
			screens.get(i).setCoordinates(startX + (32 + iconWidth) * i, y+80);
		}
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
