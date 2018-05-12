package com.pisces;

<<<<<<< HEAD
/**
 * A class containing the game's settings. Should be able to be canned and retrieved.
 * 
 * @author mpeng
 * @version 1.0.0
 */

=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
public class Settings {
	private float controlDeadZone;
	private boolean controlInvertX;
	private boolean controlInvertY;
	
<<<<<<< HEAD
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
=======
	public Settings() {
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
		controlDeadZone=0.25f;
		controlInvertX=false;
		controlInvertY=true;
	}
	
<<<<<<< HEAD
	/**
	 * @return The controller's dead zone, i.e. how far the control sticks have to tilt before they begin to recognize input.
	 */
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public float getControlDeadZone() {
		return controlDeadZone;
	}
	
<<<<<<< HEAD
	/**	 
	 * @return Whether the X axis of the controller is inverted
	 */
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public boolean getControlInvertX() {
		return controlInvertX;
	}
	
<<<<<<< HEAD
	/**
	 * @return Whether the Y axis of the contoller is inverted
	 */
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public boolean getControlInvertY() {
		return controlInvertY;
	}
}
