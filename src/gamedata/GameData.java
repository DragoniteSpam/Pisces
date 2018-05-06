package gamedata;

import java.util.HashMap;
import java.util.Random;

public class GameData {
	private static HashMap<Integer, GameData> ids=new HashMap<Integer, GameData>();
	protected String name;
	protected int id;
	
	private static final Random r=new Random();
	
	public GameData(String name) {
		this.name=name;
		this.id=r.nextInt();
		ids.put(this.id,  this);
	}
	
	public GameData(String name, int id) {
		this(name);
		ids.remove(this.id);
		this.id=id;
		ids.put(this.id,  this);
	}
	
	public final String getName() {
		return name;
	}
	
	public final long id() {
		return id;
	}
	
	public static GameData getByID(int id) {
		return ids.get(id);
	}
	
	public static boolean getIDExists(int id) {
		return ids.containsKey(id);
	}
}
