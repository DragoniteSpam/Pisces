package pause;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.pisces.Pisces;
import com.pisces.PiscesController;
import com.pisces.Text;

import WorldObjects.WorldEntityPlayer;
import gamedata.PiscesElement;
import gamedata.PiscesInstantiatedMove;
import gamedata.items.PiscesInstantiatedItem;
import gamedata.items.PiscesItem;
import gamedata.items.PiscesItemManual;
import gamedata.items.PiscesItemPocket;
import gamedata.items.PiscesItemWithStats;
import stuff.Element;
import stuff.ItemPockets;
import stuff.Stats;
import stuff.pause.PauseStages;

public class PauseScreenInventory implements PauseScreenDrawable {
	public static final int PER_PAGE = 30;
	private Texture overlayTexture;
	private TextureRegion icon;
	private TextureRegion selectedIcon;
	private TextureRegion grayedIcon;
	private TextureRegion popupBackground;
	private float x;
	private float y;
	private String name;
	private TextureRegion inventoryBackgroundBackground;
	private TextureRegion summaryBackground;
	private TextureRegion inventoryBackground;
	private TextureRegion highlightedItem;
	private ItemPockets currentPocket;
	private int currentPage;
	private int currentPageIndex;
	private GlyphLayout showingMessageText;

	private GlyphLayout glPrevious;
	private GlyphLayout glNext;
	private GlyphLayout glMoveDontKnow;
	private GlyphLayout glMoveAlreadyUnlocked;
	private GlyphLayout glMoveUnlockedNow;

	public PauseScreenInventory(Texture overlayTexture, int iconX, int iconY, int selectedIconX, int selectedIconY,
			int grayedIconX, int grayedIconY, String name) {
		this.overlayTexture = overlayTexture;
		this.icon = new TextureRegion(overlayTexture, iconX, iconY, 64, 64);
		this.selectedIcon = new TextureRegion(overlayTexture, selectedIconX, selectedIconY, 64, 64);
		this.grayedIcon = new TextureRegion(overlayTexture, grayedIconX, grayedIconY, 64, 64);
		this.name = name;

		this.summaryBackground = new TextureRegion(overlayTexture, 0, 192, 512, 576);
		this.inventoryBackground = new TextureRegion(overlayTexture, 512, 192, 768, 512);
		this.inventoryBackgroundBackground = new TextureRegion(overlayTexture, 1280, 192, 832, 704);
		this.popupBackground=new TextureRegion(overlayTexture, overlayTexture.getWidth()-320, 0, 320, 192);
		this.highlightedItem = new TextureRegion(overlayTexture, 960, 704, 64, 64);

		this.currentPocket = ItemPockets.WEAPON;
		this.currentPage = 0;

		BitmapFont font32 = Pisces.me().getFont32();
		font32.setColor(Color.BLACK);
		this.glPrevious = new GlyphLayout(font32, Text.I("Previous: "));
		this.glNext = new GlyphLayout(font32, Text.I("Next: "));
		this.glMoveDontKnow=new GlyphLayout(font32, Text.I("You don't know this move yet!"), Color.BLACK, 288, Align.center, true);
		this.glMoveAlreadyUnlocked=new GlyphLayout(font32, Text.I("This move has already been unlocked to its maximum potential!"), Color.BLACK, 288, Align.center, true);
		this.glMoveUnlockedNow=new GlyphLayout(font32, Text.I("Unlocked to its maximum potential!"), Color.BLACK, 288, Align.center, true);

		this.showingMessageText = null;

		setCoordinates(0, 0);
	}

	@Override
	public void draw(Batch batch, float parentAlpha, boolean canControl) {
		int summaryBackgroundX = 256;
		int summaryBackgroundY = 256;
		int inventoryBackgroundBackgroundX = 864;
		int inventoryBackgroundBackgroundY = 224;
		int inventoryBackgroundX = 896;
		int inventoryBackgroundY = 256;

		batch.draw(this.summaryBackground, summaryBackgroundX, summaryBackgroundY);
		batch.draw(this.inventoryBackgroundBackground, inventoryBackgroundBackgroundX, inventoryBackgroundBackgroundY);
		batch.draw(this.inventoryBackground, inventoryBackgroundX, inventoryBackgroundY);

		Pisces pisces = Pisces.me();
		WorldEntityPlayer player = pisces.getPlayer();
		PiscesController controller = pisces.getController();
		BitmapFont font20=pisces.getFont20();
		BitmapFont font32 = pisces.getFont32();

		int selectedColumn = this.currentPageIndex % 10;
		int selectedRow = this.currentPageIndex / 10;
		int totalPages = pisces.getPlayer().inventory.size(this.currentPocket) / 30;

		font32.setColor(Color.BLACK);
		font32.draw(batch, Text.I("Page ") + (this.currentPage + 1) + "/" + (totalPages + 1), inventoryBackgroundX + 32,
				inventoryBackgroundY + 48);
		font32.draw(batch, this.glPrevious, inventoryBackgroundBackgroundX + 64,
				inventoryBackgroundY + this.inventoryBackground.getRegionHeight() + 128);
		font32.draw(batch, controller.getInputName(PiscesController.CONTROLLER_L2),
				inventoryBackgroundBackgroundX + this.glPrevious.width + 64,
				inventoryBackgroundY + this.inventoryBackground.getRegionHeight() + 128);
		font32.draw(batch, this.glNext,
				inventoryBackgroundBackgroundX + this.inventoryBackground.getRegionWidth() - this.glNext.width - 64,
				inventoryBackgroundY + this.inventoryBackground.getRegionHeight() + 128);
		font32.draw(batch, controller.getInputName(PiscesController.CONTROLLER_R2),
				inventoryBackgroundBackgroundX + this.inventoryBackground.getRegionWidth() - 64,
				inventoryBackgroundY + this.inventoryBackground.getRegionHeight() + 128);

		int start = this.currentPage * PER_PAGE;
		int end = (this.currentPage + 1) * PER_PAGE;
		@SuppressWarnings("unchecked")
		ArrayList<PiscesInstantiatedItem> list = pisces.getPlayer().inventory.get(this.currentPocket);

		for (int i = start; i < Math.min(end, list.size()); i++) {
			int col = (i - start) % (PER_PAGE / 3);
			int row = (i - start) / (PER_PAGE / 3);
			PiscesInstantiatedItem item = list.get(i);
			PiscesItem baseItem = item.getBaseItem();
			batch.draw(baseItem.getImage(), inventoryBackgroundX + 28 + 72 * (col),
					inventoryBackgroundY + 64 + 80 * (2 - row));
		}

		PiscesItemPocket[] array = PiscesItemPocket.getAll();
		for (int i = 0; i < array.length; i++) {
			if (this.currentPocket.ordinal() == i) {
				batch.draw(array[i].getImage(), inventoryBackgroundX + 64 * i,
						inventoryBackgroundY + this.inventoryBackground.getRegionHeight());
			} else {
				batch.draw(array[i].getGrayedImage(), inventoryBackgroundX + 64 * i,
						inventoryBackgroundY + this.inventoryBackground.getRegionHeight());
			}
		}

		batch.draw(this.highlightedItem, inventoryBackgroundX + 28 + 72 * (selectedColumn),
				inventoryBackgroundY + 64 + 80 * (2 - selectedRow));

		final int summaryYBuffer = 8;

		PiscesItem active;
		if (this.currentPage * PER_PAGE + this.currentPageIndex < list.size()) {
			active = list.get(this.currentPage * PER_PAGE + this.currentPageIndex).getBaseItem();
			font32.draw(batch, active.getName(), summaryBackgroundX + 64,
					summaryBackgroundY + 15 * 32 - summaryYBuffer);

			if (this.currentPage * PER_PAGE + this.currentPageIndex < list.size()) {
				switch (this.currentPocket) {
				case ARMS:
				case AUGMENT:
				case HAT:
				case PANTS:
				case SHOES:
				case TORSO:
				case WEAPON:
					PiscesItemWithStats activeWithStats = (PiscesItemWithStats) active;

					Stats[] statArray = Stats.values();
					int r = 0;
					for (int i = 0; i <= Stats.LCK.ordinal(); i++) {
						if (activeWithStats.getRating(Stats.values()[i]) != 0) {
							font32.draw(batch, Text.I(statArray[i].getName()), summaryBackgroundX + 64,
									summaryBackgroundY + (14 - r) * 32 - summaryYBuffer);
							font32.draw(batch, activeWithStats.getRating(Stats.values()[i]) + "",
									summaryBackgroundX + this.summaryBackground.getRegionWidth() - 128,
									summaryBackgroundY + (14 - r) * 32 - summaryYBuffer);
							r++;
						}
					}

					int spot = 0;
					Element[] elements = Element.values();
					for (int i = 0; i <= Element.EL15.ordinal(); i++) {
						double elementalDamage = activeWithStats.getElementalDamage(elements[i]);
						double elementalResist = activeWithStats.getElementalResist(elements[i]);
						if (elementalDamage > 0.0) {
							int col = spot % 2;
							int row = spot / 2;
							batch.draw(PiscesElement.getByIndex(elements[i].ordinal()).getImage(),
									summaryBackgroundX + col * 192 + 64f, summaryBackgroundY + row * 32f, 0f, 0f, 64f,
									64f, 0.5f, 0.5f, 0f);
							font32.draw(batch, Math.floor(elementalDamage * 100) + "%",
									summaryBackgroundX + col * 192 + 128,
									summaryBackgroundY + (row + 1) * 32 - summaryYBuffer);
							spot++;
						}
						if (elementalResist > 0.0) {
							int col = spot % 2;
							int row = spot / 2;
							batch.draw(PiscesElement.getByIndex(elements[i].ordinal()).getImage(),
									summaryBackgroundX + col * 192 + 64f, summaryBackgroundY + row * 32f, 0f, 0f, 64f,
									64f, 0.5f, 0.5f, 0f);
							font32.draw(batch, Math.floor(elementalResist * 100) + "%",
									summaryBackgroundX + col * 192 + 128,
									summaryBackgroundY + (row + 1) * 32 - summaryYBuffer);
							spot++;
						}
					}
					break;
				case COLLECTABLE:
					break;
				case COMPONENT:
					break;
				case KEY:
					break;
				case MANUAL:
					PiscesItemManual activeManual = (PiscesItemManual) active;

					if (this.showingMessageText == null && controller.aRelease()) {
						PiscesInstantiatedMove knowsMove = player.knowsMove(activeManual.getMove());
						if (knowsMove == null) {
							this.showingMessageText =this.glMoveDontKnow;
						} else {
							if (knowsMove.getUnlocked()) {
								this.showingMessageText = this.glMoveAlreadyUnlocked;
							} else {
								this.showingMessageText = this.glMoveUnlockedNow;
								knowsMove.unlock();
							}
						}
					}
					break;
				case MISC:
					break;
				default:
					break;
				}

			} else {
				active = null;
			}
		}

		/*
		 * Controls
		 */

		ItemPockets[] values = ItemPockets.values();

		if (this.showingMessageText == null) {
			if (controller.padLeftRelease()) {
				if (this.currentPageIndex == 0) {
					if (this.currentPage > 0) {
						--this.currentPage;
						this.currentPageIndex = PER_PAGE - 1;
					}
				} else {
					--this.currentPageIndex;
				}
			} else if (controller.padRightRelease()) {
				if (this.currentPageIndex == (PER_PAGE - 1)) {
					if (this.currentPage < totalPages) {
						++this.currentPage;
						this.currentPageIndex = 0;
					}
				} else {
					++this.currentPageIndex;
				}
			} else if (controller.padUpRelease()) {
				if (this.currentPageIndex == 0) {
					if (this.currentPage > 0) {
						--this.currentPage;
					}
				} else {
					this.currentPageIndex = Math.max(this.currentPageIndex - 10, 0);
				}
			} else if (controller.padDownRelease()) {
				if (this.currentPageIndex == (PER_PAGE - 1)) {
					if (this.currentPage < totalPages) {
						++this.currentPage;
					}
				} else {
					this.currentPageIndex = Math.min(this.currentPageIndex + 10, 29);
				}
			} else if (controller.l2Release()) {
				this.currentPocket = values[(this.currentPocket.ordinal() + values.length - 1) % values.length];
			} else if (controller.r2Release()) {
				this.currentPocket = values[(this.currentPocket.ordinal() + 1) % values.length];
			} else if (controller.bRelease()) {
				reset();
				pisces.setPauseScreen(PauseStages.MAIN);
			}
		} else {
			int centerX=Gdx.graphics.getWidth()/2;
			int centerY=Gdx.graphics.getHeight()/2;
			batch.draw(this.popupBackground, centerX-this.popupBackground.getRegionWidth()/2, centerY-this.popupBackground.getRegionHeight()/2);
			font32.draw(batch, this.showingMessageText, centerX-this.showingMessageText.width/2-16, centerY+this.popupBackground.getRegionHeight()/2-32);
			font32.draw(batch, Text.I("Okay"), centerX, centerY-this.popupBackground.getRegionHeight()/2+32);
			font32.draw(batch, controller.getInputName(PiscesController.CONTROLLER_A), centerX-48, centerY-this.popupBackground.getRegionHeight()/2+32);
			if (controller.aRelease()||controller.bRelease()) {
				this.showingMessageText=null;
			}
		}
	}

	public void reset() {
		this.showingMessageText=null;
	}

	public TextureRegion getIcon() {
		return this.icon;
	}

	public TextureRegion getSelectedIcon() {
		return this.selectedIcon;
	}

	@Override
	public void setCoordinates(float x, float y) {
		this.x = x;
		this.y = y;
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
