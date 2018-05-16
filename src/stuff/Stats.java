package stuff;

public enum Stats {
	HP, ATK, DEF, SPA, SPD, AGL, BLK, CRT, LCK;
	
	public static boolean isStatic(Stats stat) {
		switch (stat) {
		case BLK:
		case CRT:
		case LCK:
			return true;
		}
		return false;
	}
}
