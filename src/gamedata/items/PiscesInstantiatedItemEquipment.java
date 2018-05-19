package gamedata.items;

import stuff.Element;
import stuff.Stats;

public class PiscesInstantiatedItemEquipment extends PiscesInstantiatedItem {
	private PiscesItemWithStats baseItem;
	protected PiscesItemAugment[] slots;
	
	public PiscesInstantiatedItemEquipment() {
		super();
		this.slots=new PiscesItemAugment[0];
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
