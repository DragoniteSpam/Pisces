package gamedata;

import java.util.HashMap;
import java.util.Random;

public class GameData {
	private static HashMap<Integer, GameData> ids=new HashMap<Integer, GameData>();
	protected String name;
	protected String summary;
	protected int id;
	
	private static final Random r=new Random();
	
	public GameData(String name) {
		this.name=name;
		this.summary="";
		this.id=r.nextInt();
		ids.put(this.id,  this);
	}
	
	public GameData(String name, int id) {
		this(name);
		if (id>0) {
			ids.remove(this.id);
			this.id=id;
			ids.put(this.id,  this);
		}
	}
	
	public final String getName() {
		return name;
	}
	
	public final long id() {
		return id;
	}
	
	public final String getSummary() {
		return this.summary;
	}
	
	public final void setSummary(String summary) {
		this.summary=summary;
	}
	
	public static GameData getByID(int id) {
		return ids.get(id);
	}
	
	public static boolean getIDExists(int id) {
		return ids.containsKey(id);
	}
}
