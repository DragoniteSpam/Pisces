package gamedata.items;

import stuff.Element;
import stuff.Stats;

public interface PiscesItemWithStats {
	public int getRating(Stats stat);
	
	public int getAdvanceEXP();
	
	public int getAdvanceAP();
	
	public int getAdvanceSP();
	
	public void setRarity(byte rarity);
	
	public byte getRarity();
	
	public void setElementalDamage(Element element, double rating);
	
	public double getElementalDamage(Element element);
	
	public void setElementalResist(Element element, double rating);
	
	public double getElementalResist(Element element);

	public int getSlots();
}
