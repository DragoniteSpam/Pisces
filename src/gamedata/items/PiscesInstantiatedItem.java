package gamedata.items;

import java.util.Date;

import stuff.Element;
import stuff.Stats;

public class PiscesInstantiatedItem {
	private PiscesItem baseItem;
	private long dateObtained;
	
	public PiscesInstantiatedItem(PiscesItem baseItem) {
		this.baseItem=baseItem;
		this.dateObtained=new Date().getTime();	// unix timestamp, sort of
	}
	
	// Have to do this because Java is being stupid
	public PiscesInstantiatedItem() {
		this.baseItem=null;
		this.dateObtained=new Date().getTime();	// unix timestamp, sort of
	}
	
	public PiscesItem getBaseItem() {
		return this.baseItem;
	}
	
	public long getDateObtained() {
		return this.dateObtained;
	}
}
