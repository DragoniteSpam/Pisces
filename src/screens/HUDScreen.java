package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pisces.Pisces;

import WorldObjects.WorldEntityPlayer;
import gamedata.PiscesCharacter;
import stuff.Stats;

public class HUDScreen extends Actor {
	private Texture hudTexture;
	private TextureRegion character;
	
	public HUDScreen(Texture overlayTexture) {
		hudTexture=overlayTexture;
		character=new TextureRegion(hudTexture, 0, 0, 256, 192);
	}

	@Override
	public void draw (Batch batch, float parentAlpha) {
		Pisces pisces=Pisces.me();
		BitmapFont font12=pisces.getFont12();
		BitmapFont font20=pisces.getFont20();
		BitmapFont font32=pisces.getFont32();
		WorldEntityPlayer player=pisces.getPlayer();
		
		float characterOverlayWidth=character.getRegionWidth();
		float characterOverlayHeight=character.getRegionHeight();
		float characterX=32;
		for (int i=0; i<Math.min(3, player.getTeamSize()); i++) {
			PiscesCharacter teammate=player.getTeamMember(i);
			float characterY=Gdx.graphics.getHeight()-32-characterOverlayHeight-(characterOverlayHeight+32)*i;
			
			batch.draw(character, characterX, characterY);
			font20.setColor(Color.BLACK);
			font32.setColor(Color.BLACK);
			font32.draw(batch, teammate.getName(), characterX+32, characterY+characterOverlayHeight-32);
			font20.draw(batch, "Level "+teammate.getLevel(), characterX+32, characterY+characterOverlayHeight-64);
			font20.draw(batch, "HP: "+teammate.getHP()+"/"+teammate.getStat(Stats.HP), characterX+32, characterY+characterOverlayHeight-80);
		}
	}

}
