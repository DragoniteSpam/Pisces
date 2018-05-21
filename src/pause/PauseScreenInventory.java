package pause;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pisces.Pisces;
import com.pisces.PiscesController;
import com.pisces.Text;

import gamedata.items.PiscesInstantiatedItem;
import gamedata.items.PiscesItem;
import gamedata.items.PiscesItemPocket;
import stuff.ItemPockets;
import stuff.pause.PauseStages;

public class PauseScreenInventory implements PauseScreenDrawable {
	public static final int PER_PAGE=30; 
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
	private TextureRegion highlightedItem;
	private ItemPockets currentPocket;
	private int currentPage;
	private int currentPageIndex;
	
	private GlyphLayout glPrevious;
	private GlyphLayout glNext;
	
	public PauseScreenInventory(Texture overlayTexture, int iconX, int iconY, int selectedIconX, int selectedIconY, int grayedIconX, int grayedIconY, String name) {
		this.overlayTexture=overlayTexture;
		this.icon=new TextureRegion(overlayTexture, iconX, iconY, 64, 64);
		this.selectedIcon=new TextureRegion(overlayTexture, selectedIconX, selectedIconY, 64, 64);
		this.grayedIcon=new TextureRegion(overlayTexture, grayedIconX, grayedIconY, 64, 64);
		this.name=name;
		
		this.summaryBackground=new TextureRegion(overlayTexture, 0, 192, 512, 576);
		this.inventoryBackground=new TextureRegion(overlayTexture, 512, 192, 768, 512);
		this.inventoryBackgroundBackground=new TextureRegion(overlayTexture, 1280, 192, 832, 704);
		this.highlightedItem=new TextureRegion(overlayTexture, 960, 704, 64, 64);
		
		this.currentPocket=ItemPockets.WEAPON;
		this.currentPage=0;
		
		BitmapFont font32=Pisces.me().getFont32();
		font32.setColor(Color.BLACK);
		this.glPrevious=new GlyphLayout(font32, Text.I("Previous: "));
		this.glNext=new GlyphLayout(font32, Text.I("Next: "));
		
		setCoordinates(0, 0);
	}

	@Override
	public void draw(Batch batch, float parentAlpha, boolean canControl) {
		int summaryBackgroundX=256;
		int summaryBackgroundY=256;
		int inventoryBackgroundBackgroundX=864;
		int inventoryBackgroundBackgroundY=224;
		int inventoryBackgroundX=896;
		int inventoryBackgroundY=256;
		batch.draw(this.summaryBackground, summaryBackgroundX, summaryBackgroundY);
		batch.draw(this.inventoryBackgroundBackground, inventoryBackgroundBackgroundX, inventoryBackgroundBackgroundY);
		batch.draw(this.inventoryBackground, inventoryBackgroundX, inventoryBackgroundY);
		
		Pisces pisces=Pisces.me();
		PiscesController controller=pisces.getController();
		BitmapFont font32=pisces.getFont32();
		
		int selectedColumn=this.currentPageIndex%10;
		int selectedRow=this.currentPageIndex/10;
		int totalPages=pisces.getPlayer().inventory.size(this.currentPocket)/30;
		
		font32.setColor(Color.BLACK);
		font32.draw(batch, Text.I("Page")+(this.currentPage+1)+"/"+(totalPages+1),
				inventoryBackgroundX+32, inventoryBackgroundY+48);
		//font32.draw(batch, Text.I("Previous Pocket"), inventoryBackgroundBackgroundX+64, inventoryBackgroundY+this.inventoryBackground.getRegionHeight()+64);
		
		//font32.draw(batch, Text.I("Next Pocket"), inventoryBackgroundBackgroundX+64, inventoryBackgroundY+this.inventoryBackground.getRegionHeight()+64);
		font32.draw(batch, this.glPrevious, inventoryBackgroundBackgroundX+64, inventoryBackgroundY+this.inventoryBackground.getRegionHeight()+128);
		font32.draw(batch, controller.getInputName(PiscesController.CONTROLLER_L2), inventoryBackgroundBackgroundX+this.glPrevious.width+64, inventoryBackgroundY+this.inventoryBackground.getRegionHeight()+128);
		font32.draw(batch, this.glNext, inventoryBackgroundBackgroundX+this.inventoryBackground.getRegionWidth()-this.glNext.width-64, inventoryBackgroundY+this.inventoryBackground.getRegionHeight()+128);
		font32.draw(batch, controller.getInputName(PiscesController.CONTROLLER_R2), inventoryBackgroundBackgroundX+this.inventoryBackground.getRegionWidth()-64, inventoryBackgroundY+this.inventoryBackground.getRegionHeight()+128);
		
		int start=this.currentPage*PER_PAGE;
		int end=(this.currentPage+1)*PER_PAGE;
		ArrayList<PiscesInstantiatedItem> list=pisces.getPlayer().inventory.get(this.currentPocket);
		
		for (int i=start; i<Math.min(end, list.size()); i++) {
			int col=(i-start)%PER_PAGE;
			int row=(i-start)/PER_PAGE;
			PiscesInstantiatedItem item=list.get(i);
			PiscesItem baseItem=item.getBaseItem();
			batch.draw(baseItem.getImage(), inventoryBackgroundX+28+72*(col), inventoryBackgroundY+64+80*(2-row));
		}
		
		PiscesItemPocket[] array=PiscesItemPocket.getAll();
		for (int i=0; i<array.length; i++) {
			if (this.currentPocket.ordinal()==i) {
				batch.draw(array[i].getImage(), inventoryBackgroundX+64*i, inventoryBackgroundY+this.inventoryBackground.getRegionHeight());
			} else {
				batch.draw(array[i].getGrayedImage(), inventoryBackgroundX+64*i, inventoryBackgroundY+this.inventoryBackground.getRegionHeight());
			}
		}
		
		batch.draw(this.highlightedItem, inventoryBackgroundX+28+72*(selectedColumn), inventoryBackgroundY+64+80*(2-selectedRow));
		
		/*
		 * Controls 
		 */
		
		ItemPockets[] values=ItemPockets.values();
		if (controller.padLeftRelease()) {
			if (this.currentPageIndex==0) {
				if (this.currentPage>0) {
					--this.currentPage;
					this.currentPageIndex=PER_PAGE-1;
				}
			} else {
				--this.currentPageIndex;
			}
		} else if (controller.padRightRelease()) {
			if (this.currentPageIndex==(PER_PAGE-1)) {
				if (this.currentPage<totalPages) {
					++this.currentPage;
					this.currentPageIndex=0;
				}
			} else {
				++this.currentPageIndex;
			}
		} else if (controller.padUpRelease()) {
			if (this.currentPageIndex==0) {
				if (this.currentPage>0) {
					--this.currentPage;
				}
			} else {
				this.currentPageIndex=Math.max(this.currentPageIndex-10, 0);
			}
		} else if (controller.padDownRelease()) {
			if (this.currentPageIndex==(PER_PAGE-1)) {
				if (this.currentPage<totalPages) {
					++this.currentPage;
				}
			} else {
				this.currentPageIndex=Math.min(this.currentPageIndex+10, 29);
			}
		} else if (controller.l2Release()) {
			this.currentPocket=values[(this.currentPocket.ordinal()+values.length-1)%values.length];
		} else if (controller.r2Release()) {
			this.currentPocket=values[(this.currentPocket.ordinal()+1)%values.length];
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
