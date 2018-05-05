package com.pisces;

public class Settings {
	private float controlDeadZone;
	private boolean controlInvertX;
	private boolean controlInvertY;
	
	public Settings() {
		controlDeadZone=0.25f;
		controlInvertX=false;
		controlInvertY=true;
	}
	
	public float getControlDeadZone() {
		return controlDeadZone;
	}
	
	public boolean getControlInvertX() {
		return controlInvertX;
	}
	
	public boolean getControlInvertY() {
		return controlInvertY;
	}
}
