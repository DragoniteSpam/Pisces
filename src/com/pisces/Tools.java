package com.pisces;

import java.util.ArrayList;

/**
 * Various tools used in various places in the game.
 * 
 * @author mpeng
 * @version	1.0.0
 */
public class Tools {
	private static ArrayList<String> log = new ArrayList<String>();

	/**
	 * Takes an array of bytes (a Game Maker buffer, for lack of better words) and turns it into a double.
	 * 
	 * @param data The array/buffer to read data out of
	 * @param offset Where to start converting to a double
	 * @return The double-precision floating point value
	 */

	public static double bytesToDouble(byte[] data, int offset) {
		int i = (data[offset] & 0xFF) | ((data[offset + 1] & 0xFF) << 8) | ((data[offset + 2] & 0xFF) << 16)
				| ((data[offset + 3] & 0xFF) << 24);
		return Float.intBitsToFloat(i);
	}

	/**
	 * Linearly interpolates between two values.
	 * 
	 * @param point1 The lower value
	 * @param point2 The upper value
	 * @param amount The amount to interpolate
	 * @return The result of the linear interpolation, i.e. lerp(1, 2, 0.5) = 1.5
	 */
	public static double lerp(double point1, double point2, double amount) {
		return point1 + amount * (point2 - point1);
	}

	/**
	 * Writes a line to the game's log, and prints it out on the Java command line via standard output.
	 * @param line The line to log
	 */
	public static void log(String line) {
		log.add(line);
		System.out.println(line);
	}
	
	/**
	 * Writes a line to the game's log, and prints it out on the Java command line via standard error output.
	 * @param line The line to log
	 */
	public static void err(String line) {
		log.add(line);
		System.err.println(line);
	}
	
	/**
	 * @param x The number to take the sine of
	 * @return The sine of the degree value
	 */
	public static double dsin(double x) {
		return Math.sin(Math.toRadians(x));
	}
	
	/**
	 * @param x The number to take the cosine of
	 * @return The cosine of the degree value
	 */
	public static double dcos(double x) {
		return Math.cos(Math.toRadians(x));
	}
	
	/**
	 * @param x The number to take the tangent of
	 * @return The tangent of the degree value
	 */
	public static double dtan(double x) {
		return Math.tan(Math.toRadians(x));
	}
	
	/**
	 * @param x The number to take the sine of
	 * @return The sine of the degree value
	 */
	public static float dsin(float x) {
		return (float) Math.sin(Math.toRadians(x));
	}
	
	/**
	 * @param x The number to take the cosine of
	 * @return The cosine of the degree value
	 */
	public static float dcos(float x) {
		return (float) Math.cos(Math.toRadians(x));
	}
	
	/**
	 * @param x The number to take the tangent of
	 * @return The tangent of the degree value
	 */
	public static float dtan(float x) {
		return (float) Math.tan(Math.toRadians(x));
	}
	
	/**
	 * Clamps a value.
	 * @param x	The value to clamp
	 * @param lower	The lower bounds of the clamped value
	 * @param upper	The upper bounds of the clamped value
	 * @return	The clamped value
	 */
	public static double clamp(double x, double lower, double upper) {
		return Math.max(lower, Math.min(upper, x));
	}
	
	/**
	 * Clamps a value.
	 * @param x	The value to clamp
	 * @param lower	The lower bounds of the clamped value
	 * @param upper	The upper bounds of the clamped value
	 * @return	The clamped value
	 */
	public static float clamp(float x, float lower, float upper) {
		return Math.max(lower, Math.min(upper, x));
	}
	
	/**
	 * Gets a reference to the log.
	 * @return a reference to the log
	 */
	public static ArrayList<String> getLog(){
		return log;
	}
}
