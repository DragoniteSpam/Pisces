package com.pisces;

import java.util.ArrayList;

public class Tools {
	private static ArrayList<String> log = new ArrayList<String>();

	public static double bytesToDouble(byte[] data, int offset) {
		int i = (data[offset] & 0xFF) | ((data[offset + 1] & 0xFF) << 8) | ((data[offset + 2] & 0xFF) << 16)
				| ((data[offset + 3] & 0xFF) << 24);
		return Float.intBitsToFloat(i);
	}

	public static double lerp(double point1, double point2, double amount) {
		return point1 + amount * (point2 - point1);
	}

	public static void log(String line) {
		log.add(line);
		System.out.println(line);
	}
	
	public static double dsin(double x) {
		return Math.sin(Math.toRadians(x));
	}
	
	public static double dcos(double x) {
		return Math.cos(Math.toRadians(x));
	}
	
	public static double dtan(double x) {
		return Math.tan(Math.toRadians(x));
	}
	
	public static double clamp(double x, double lower, double upper) {
		return Math.max(lower, Math.min(upper, x));
	}
}
