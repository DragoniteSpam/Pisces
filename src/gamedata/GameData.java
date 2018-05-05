package gamedata;

public class GameData {
	protected String name;
	protected long id;
	
	public GameData(String name) {
		this.name=name;
	}
	
	public GameData(String name, long id) {
		this(name);
		this.id=id;
	}
	
	public final String getName() {
		return name;
	}
	
	public final long id() {
		return id;
	}
}
