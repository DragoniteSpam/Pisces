package com.pisces;

/**
 * Class which collects various stats of the game, for debug purposes or just to be silly.
 * 
 * @author mpeng
 * @version 1.0.0
 */
public class Statistics {
	private long startupTime;
	
	/**
	 * Constructor (doesn't do anything)
	 */
	
	public Statistics() {
		super();
	}
	
	/**
	 * @param time How long it took the game to load
	 */
	public void setStartupTime(long time) {
		startupTime=time;
	}
	
	/**
	 * @return How long it took the game to load
	 */
	public long getStartupTime() {
		return startupTime;
	}
}
