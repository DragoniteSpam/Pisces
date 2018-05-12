package gamedata;

import java.util.HashMap;

import com.badlogic.gdx.audio.Sound;

public class PiscesSound {
	private static HashMap<String, Sound> all=new HashMap<String, Sound>();
	
	public static void addSound(Sound sound, String name) {
		all.put(name,  sound);
	}
	
	public static Sound getSound(String name) {
		return all.get(name);
	}
}
