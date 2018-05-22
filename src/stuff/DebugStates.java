package stuff;

public enum DebugStates {
	OFF(false, false, true),
	LOG(false, true, false),
	OBJECTS(true, true, true),
	EVENTS(true, true, true),
	FILE(false, true, false),
	GAMEDATA(false, true, false);
	
	private boolean freeRayDistance;
	private boolean isDebugState;
	private boolean drawWorld;
	
	DebugStates(boolean freeRayDistance, boolean isDebugState, boolean drawWorld){
		this.freeRayDistance=freeRayDistance;
		this.isDebugState=isDebugState;
		this.drawWorld=drawWorld;
	}
	
	public boolean getFreeRayDistance() {
		return this.freeRayDistance;
	}
	
	public boolean getIsDebugState() {
		return this.isDebugState;
	}
	
	public boolean getDrawWorld() {
		return this.drawWorld;
	}
}
