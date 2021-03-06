package com.pisces;

/**
 * A class containing the game's settings. Should be able to be canned and retrieved.
 * 
 * @author mpeng
 * @version 1.0.0
 */

public class Settings {
	public static final int LEVEL_CURVE=120;
	public static final int LEVEL_BASE=100;
	public static final int BASE_AP_PER_ABILITY=100;
	
	private float controlDeadZone;
	private float controlSensitivity;
	private boolean controlInvertX;
	private boolean controlInvertY;
	
	/**
	 * Initializes the Settings object, and sets the default values.
	 */
	public Settings() {
		reset();
	}
	
	/**
	 * Resets all of the settings values to their defaults.
	 */
	public void reset() {
		resetControls();
	}
	
	/**
	 * Resets the control settings to their default values.
	 */
	public void resetControls() {
		resetControlDeadZone();
		resetControlSensitivity();
		resetControlInvertX();
		resetControlInvertY();
	}

	/**
	 * @return The controller's dead zone, i.e. how far the control sticks have to tilt before they begin to recognize input.
	 */
	public float getControlDeadZone() {
		return controlDeadZone;
	}
	
	public void resetControlDeadZone() {
		this.controlDeadZone=0.25f;
	}
	
	public float getControlSensitivity() {
		return this.controlSensitivity;
	}
	
	public void resetControlSensitivity() {
		this.controlSensitivity=1.25f;
	}
	
	/**	 
	 * @return Whether the X axis of the controller is inverted
	 */
	public boolean getControlInvertX() {
		return controlInvertX;
	}
	
	public void resetControlInvertX() {
		this.controlInvertX=false;
	}
	
	/**
	 * @return Whether the Y axis of the contoller is inverted
	 */
	public boolean getControlInvertY() {
		return controlInvertY;
	}
	
	public void resetControlInvertY() {
		this.controlInvertY=true;
	}
}
