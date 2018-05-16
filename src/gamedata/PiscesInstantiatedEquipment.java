package gamedata;

import gamedata.items.PiscesItemAugment;
import gamedata.items.PiscesItemEquipment;
import stuff.Element;
import stuff.Stats;

public class PiscesInstantiatedEquipment {
	private PiscesItemEquipment baseItem;
	private PiscesItemAugment[] slots;
	
	public PiscesInstantiatedEquipment(PiscesItemEquipment baseItem) {
		this.baseItem=baseItem;
		this.slots=new PiscesItemAugment[baseItem.getSlots()];
	}
	
	public PiscesItemEquipment getBaseItem() {
		return this.baseItem;
	}
	
	public double getStatRating(Stats stat) {
		double m=0;
		for (PiscesItemAugment augment : slots) {
			if (augment==null) {
				break;
			}
			m=m+augment.getRating(stat);
		}
		return baseItem.getRating(stat)*(1+m/100);
	}
	
	public double getElementalResist(Element element) {
		double m=baseItem.getElementalResist(element);
		for (PiscesItemAugment augment : slots) {
			if (augment==null) {
				break;
			}
			m=m+augment.getElementalResist(element);
		}
		return (1+m/100);
	}
	
	public double getElementalDamage(Element element) {
		double m=baseItem.getElementalDamage(element);
		for (PiscesItemAugment augment : slots) {
			if (augment==null) {
				break;
			}
			m=m+augment.getElementalDamage(element);
		}
		return (1+m/100);
	}
	
	public PiscesItemAugment getAugment(int n) {
		return this.slots[n];
	}
}
