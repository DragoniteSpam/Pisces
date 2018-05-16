package stuff;

public enum EquipTypes {
	HEAD,
	TORSO,
	ARMS,
	PANTS,
	SHOES,
	WEAPON;
	
	public static boolean isArmor(EquipTypes type) {
		switch (type) {
		case WEAPON:
			return false;
		}
		return true;
	}
	
	public static boolean isWeapon(EquipTypes type) {
		switch (type) {
		case WEAPON:
			return true;
		}
		return false;
	}
}
