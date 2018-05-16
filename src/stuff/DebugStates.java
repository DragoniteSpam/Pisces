package stuff;

public enum DebugStates {
	OFF,
	LOG,
	OBJECTS,
	EVENTS,
	FILE,
	GAMEDATA;
	
	public static boolean freeRayDistance(DebugStates state) {
		switch (state) {
		case OFF:
		case LOG:
		case FILE:
		case GAMEDATA:
			return false;
		}
		return true;
	}
	
	public static boolean isDebugState(DebugStates state) {
		switch (state) {
		case OFF:
			return false;
		}
		return true;
	}
	
	public static boolean drawWorld(DebugStates state) {
		switch (state) {
		case OFF:
		case LOG:
		case OBJECTS:
		case EVENTS:
			return true;
		}
		return false;
	}
}
