package stuff.pause;

public enum PauseStages {
	MAIN,
	INVENTORY,
	MAP,
	MOVESSKILLS,
	TEAM,
	STUFF,
	SAVE,
	SETTINGS,
	QUIT;
	
	public static PauseStages next(PauseStages stage) {
		switch (stage) {
		case INVENTORY:
			return PauseStages.MAP;
		case MAP:
			return PauseStages.MOVESSKILLS;
		case MOVESSKILLS:
			return PauseStages.TEAM;
		case TEAM:
			return PauseStages.STUFF;
		case STUFF:
			return PauseStages.SAVE;
		case SAVE:
			return PauseStages.SETTINGS;
		case SETTINGS:
			return PauseStages.QUIT;
		case QUIT:
			return PauseStages.INVENTORY;
		default:
			return PauseStages.MAIN;
		}
	}
	
	public static PauseStages previous(PauseStages stage) {
		switch (stage) {
		case INVENTORY:
			return PauseStages.QUIT;
		case MAP:
			return PauseStages.INVENTORY;
		case MOVESSKILLS:
			return PauseStages.MAP;
		case TEAM:
			return PauseStages.MOVESSKILLS;
		case STUFF:
			return PauseStages.TEAM;
		case SAVE:
			return PauseStages.STUFF;
		case SETTINGS:
			return PauseStages.SAVE;
		case QUIT:
			return PauseStages.SETTINGS;
		default:
			return PauseStages.MAIN;
		}
	}
}
