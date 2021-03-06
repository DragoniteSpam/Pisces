package com.pisces;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * Class which controls input handling such as button presses and joystick axes.
 * 
 * @author mpeng
 * @version 1.0.0
 */

public class PiscesController implements InputProcessor, ControllerListener {
	private boolean[] keys;
	private boolean[] previousKeys;
	private String[] keyNames;
	
	private int keyA, keyB, keyX, keyY, keyL, keyR, keyL2, keyR2, keyLS, keyRS, keyStart, keySelect, keyUp, keyDown,
			keyLeft, keyRight, keyPadUp, keyPadDown, keyPadLeft, keyPadRight, keyRightUp, keyRightDown, keyRightLeft,
			keyRightRight;
	private int gpA, gpB, gpX, gpY, gpL, gpR, gpL2, gpR2, gpLS, gpRS, gpStart, gpSelect, gpUp, gpDown, gpLeft, gpRight,
			gpPadUp, gpPadDown, gpPadLeft, gpPadRight, gpRightUp, gpRightDown, gpRightLeft, gpRightRight;
	
	private float leftStickHorizontal, rightStickHorizontal, leftStickVertical, rightStickVertical;

	public static final int MOUSE_LEFT_BUTTON = 256;
	public static final int MOUSE_MIDDLE_BUTTON = 257;
	public static final int MOUSE_RIGHT_BUTTON = 258;
	public static final int MOUSE_UP = 259;
	public static final int MOUSE_DOWN = 260;
	public static final int MOUSE_LEFT = 261;
	public static final int MOUSE_RIGHT = 262;

	public static final int CONTROLLER_A = 263;
	public static final int CONTROLLER_B = 264;
	public static final int CONTROLLER_X = 265;
	public static final int CONTROLLER_Y = 266;
	public static final int CONTROLLER_L = 267;
	public static final int CONTROLLER_R = 268;
	public static final int CONTROLLER_L2 = 269;
	public static final int CONTROLLER_R2 = 270;
	public static final int CONTROLLER_LS = 271;
	public static final int CONTROLLER_RS = 272;
	public static final int CONTROLLER_START = 273;
	public static final int CONTROLLER_SELECT = 274;
	public static final int CONTROLLER_UP = 275;
	public static final int CONTROLLER_DOWN = 276;
	public static final int CONTROLLER_LEFT = 277;
	public static final int CONTROLLER_RIGHT = 278;
	public static final int CONTROLLER_PAD_UP = 279;
	public static final int CONTROLLER_PAD_DOWN = 280;
	public static final int CONTROLLER_PAD_LEFT = 281;
	public static final int CONTROLLER_PAD_RIGHT = 282;
	public static final int CONTROLLER_RIGHT_UP = 283;
	public static final int CONTROLLER_RIGHT_DOWN = 284;
	public static final int CONTROLLER_RIGHT_LEFT = 285;
	public static final int CONTROLLER_RIGHT_RIGHT = 286;
	
	private static final double Z_THRESHOLD=0.95;
	
	public static final int DEBUG_DRAW_WORLD=Keys.F12;
	public static final int DEBUG_BLOCK_WORLD=Keys.F11;

	private HashMap<Integer, Integer> buttonMap;
	private HashMap<Integer, Integer> mouseMap;
	private HashMap<Integer, Integer> povMap;

	private boolean lockCursor;

	private int mouseX;
	private int mouseY;

	/**
	 * Constructor which initializes the key code array and maps various inputs onto values in the key code array.
	 * Also it centers the mouse cursor.
	 */
	public PiscesController() {
		keys = new boolean[300];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}
		lockCursor = false;
		mouseX = Gdx.graphics.getWidth() / 2;
		mouseY = Gdx.graphics.getHeight() / 2;
		Gdx.input.setCursorPosition(mouseX, Gdx.graphics.getHeight() - mouseY);
		
		/*
		 * Button maps
		 */

		buttonMap = new HashMap<Integer, Integer>();
		buttonMap.put(0, CONTROLLER_A);
		buttonMap.put(1, CONTROLLER_B);
		buttonMap.put(2, CONTROLLER_X);
		buttonMap.put(3, CONTROLLER_Y);
		buttonMap.put(4, CONTROLLER_L2);
		buttonMap.put(5, CONTROLLER_R2);
		buttonMap.put(6, CONTROLLER_SELECT);
		buttonMap.put(7, CONTROLLER_START);
		buttonMap.put(8, CONTROLLER_LS);
		buttonMap.put(9, CONTROLLER_RS);

		mouseMap = new HashMap<Integer, Integer>();
		mouseMap.put(0, MOUSE_LEFT_BUTTON);
		mouseMap.put(1, MOUSE_RIGHT_BUTTON);
		mouseMap.put(2, MOUSE_MIDDLE_BUTTON);

		povMap = new HashMap<Integer, Integer>();
		povMap.put(1, CONTROLLER_PAD_UP);
		povMap.put(2, CONTROLLER_PAD_DOWN);
		povMap.put(3, CONTROLLER_PAD_RIGHT);
		povMap.put(4, CONTROLLER_PAD_LEFT);
		
		/*
		 * Key assignments
		 */
		
		keyA=MOUSE_LEFT_BUTTON;
		keyB=MOUSE_RIGHT_BUTTON;
		keyX=Keys.TAB;
		keyY=Keys.SPACE;
		keyL=Keys.SHIFT_LEFT;
		keyR=Keys.SHIFT_RIGHT;
		keyL2=Keys.CONTROL_LEFT;
		keyR2=Keys.CONTROL_RIGHT;
		keyLS=Keys.ALT_LEFT;
		keyRS=Keys.ALT_RIGHT;
		keyStart=Keys.ESCAPE;
		keySelect=Keys.BACKSPACE;
		keyUp=Keys.W;
		keyDown=Keys.S;
		keyLeft=Keys.A;
		keyRight=Keys.D;
		keyPadUp=Keys.UP;
		keyPadDown=Keys.DOWN;
		keyPadLeft=Keys.LEFT;
		keyPadRight=Keys.RIGHT;
		keyRightUp=MOUSE_UP;
		keyRightDown=MOUSE_DOWN;
		keyRightLeft=MOUSE_LEFT;
		keyRightRight=MOUSE_RIGHT;
		
		gpA=CONTROLLER_A;
		gpB=CONTROLLER_B;
		gpX=CONTROLLER_X;
		gpY=CONTROLLER_Y;
		gpL=CONTROLLER_L;
		gpR=CONTROLLER_R;
		gpL2=CONTROLLER_L2;
		gpR2=CONTROLLER_R2;
		gpLS=CONTROLLER_LS;
		gpRS=CONTROLLER_RS;
		gpStart=CONTROLLER_START;
		gpSelect=CONTROLLER_SELECT;
		gpUp=CONTROLLER_UP;
		gpDown=CONTROLLER_DOWN;
		gpLeft=CONTROLLER_LEFT;
		gpRight=CONTROLLER_RIGHT;
		gpPadUp=CONTROLLER_PAD_UP;
		gpPadDown=CONTROLLER_PAD_DOWN;
		gpPadLeft=CONTROLLER_PAD_LEFT;
		gpPadRight=CONTROLLER_PAD_RIGHT;
		gpRightUp=CONTROLLER_RIGHT_UP;
		gpRightDown=CONTROLLER_RIGHT_DOWN;
		gpRightLeft=CONTROLLER_RIGHT_LEFT;
		gpRightRight=CONTROLLER_RIGHT_RIGHT;
		
		leftStickHorizontal=0;
		leftStickVertical=0;
		rightStickHorizontal=0;
		rightStickVertical=0;
		
		keyNames=new String[300];
		for (int i=0; i<256; i++) {
			keyNames[i]=Keys.toString(i);
		}
		
		keyNames[MOUSE_LEFT_BUTTON]=Text.I("Mouse LB");
		keyNames[MOUSE_MIDDLE_BUTTON]=Text.I("Mouse MB");
		keyNames[MOUSE_RIGHT_BUTTON]=Text.I("Mouse RB");
		keyNames[MOUSE_UP]=Text.I("Mouse Up");
		keyNames[MOUSE_DOWN]=Text.I("Mouse Down");
		keyNames[MOUSE_LEFT]=Text.I("Mouse Left");
		keyNames[MOUSE_RIGHT]=Text.I("Mouse Right");
		keyNames[CONTROLLER_A]="A";
		keyNames[CONTROLLER_B]="B";
		keyNames[CONTROLLER_X]="X";
		keyNames[CONTROLLER_Y]="Y";
		keyNames[CONTROLLER_L]=Text.I("LT");
		keyNames[CONTROLLER_R]=Text.I("RT");
		keyNames[CONTROLLER_L2]=Text.I("LB");
		keyNames[CONTROLLER_R2]=Text.I("RB");
		keyNames[CONTROLLER_LS]=Text.I("LS");
		keyNames[CONTROLLER_RS]=Text.I("RS");
		keyNames[CONTROLLER_START]=Text.I("Start");
		keyNames[CONTROLLER_SELECT]=Text.I("Back");
		keyNames[CONTROLLER_UP]=Text.I("Left Up");
		keyNames[CONTROLLER_DOWN]=Text.I("Left Down");
		keyNames[CONTROLLER_LEFT]=Text.I("Left Left");
		keyNames[CONTROLLER_RIGHT]=Text.I("Left Right");
		keyNames[CONTROLLER_PAD_UP]=Text.I("Pad Up");
		keyNames[CONTROLLER_PAD_DOWN]=Text.I("Pad Down");
		keyNames[CONTROLLER_PAD_LEFT]=Text.I("Pad Left");
		keyNames[CONTROLLER_PAD_RIGHT]=Text.I("Pad Right");
		keyNames[CONTROLLER_RIGHT_UP]=Text.I("Right Up");
		keyNames[CONTROLLER_RIGHT_DOWN]=Text.I("Right Down");
		keyNames[CONTROLLER_RIGHT_LEFT]=Text.I("Right Left");
		keyNames[CONTROLLER_RIGHT_RIGHT]=Text.I("Right Right");
		
	}

	/**
	 * Code that runs when a key is pressed
	 * 
	 * @param keycode The code for the key which has been pressed
	 * @return false?
	 */
	public boolean keyDown(int keycode) {
		keys[keycode] = true;
		return false;
	}

	/**
	 * Code that runs when a key is released
	 * 
	 * @param keycode The code for the key which has been released
	 * @return false?
	 */
	public boolean keyUp(int keycode) {
		keys[keycode] = false;
		return false;
	}

	/**
	 * I honestly don't know what this is for.
	 * 
	 * @param character The character which was typed, I suppose
	 * @return I give up
	 */
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		keys[mouseMap.get(button)] = true;
		return false;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		keys[mouseMap.get(button)] = false;
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		if (screenX>Gdx.graphics.getWidth()/2) {
			keys[MOUSE_RIGHT]=true;
			keys[MOUSE_LEFT]=false;
		} else if (screenX<Gdx.graphics.getWidth()/2) {
			keys[MOUSE_RIGHT]=false;
			keys[MOUSE_LEFT]=true;
		} else {
			keys[MOUSE_RIGHT]=false;
			keys[MOUSE_LEFT]=false;
		}
		// This is flipped because Java is weird
		if (screenY<Gdx.graphics.getHeight()/2) {
			keys[MOUSE_DOWN]=true;
			keys[MOUSE_UP]=false;
		} else if (screenY>Gdx.graphics.getHeight()/2) {
			keys[MOUSE_DOWN]=false;
			keys[MOUSE_UP]=true;
		} else {
			keys[MOUSE_DOWN]=false;
			keys[MOUSE_UP]=false;
		}
		Settings settings=Pisces.me().getSettings();
		rightStickHorizontal=Tools.clamp(settings.getControlSensitivity()*(screenX-Gdx.graphics.getWidth()/2)/16, -1, 1);
		rightStickVertical=Tools.clamp(settings.getControlSensitivity()*(screenY-Gdx.graphics.getHeight()/2)/16, -1, 1);
		this.mouseX = screenX;
		this.mouseY = Gdx.graphics.getHeight() - screenY;
		if (lockCursor) {
			Gdx.input.setCursorPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		}
		return false;
	}

	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public void connected(Controller controller) {
	}

	public void disconnected(Controller controller) {
	}

	public boolean buttonDown(Controller controller, int buttonCode) {
		keys[buttonMap.get(buttonCode)] = true;
		return false;
	}

	public boolean buttonUp(Controller controller, int buttonCode) {
		keys[buttonMap.get(buttonCode)] = false;
		return false;
	}

	public boolean axisMoved(Controller controller, int axisCode, float value) {
		Settings settings=Pisces.me().getSettings();
		if (Math.abs(value) > Pisces.me().getSettings().getControlDeadZone()) {
			switch (axisCode) {
			case 0:	// Left stick vertical
				if (value > 0) {
					keys[CONTROLLER_DOWN] = true;
				} else {
					keys[CONTROLLER_UP] = true;
				}
				leftStickVertical=value*settings.getControlSensitivity();
				break;
			case 1: // Left stick horizontal
				if (value > 0) {
					keys[CONTROLLER_RIGHT] = true;
				} else {
					keys[CONTROLLER_LEFT] = true;
				}
				leftStickHorizontal=value*settings.getControlSensitivity();
				break;
			case 2:	// Right stick vertical
				if (value > 0) {
					keys[CONTROLLER_RIGHT_DOWN] = true;
				} else {
					keys[CONTROLLER_RIGHT_UP] = true;
				}
				rightStickVertical=value*settings.getControlSensitivity();
				break;
			case 3:	// Right stick horizontal
				if (value > 0) {
					keys[CONTROLLER_RIGHT_RIGHT] = true;
				} else {
					keys[CONTROLLER_RIGHT_LEFT] = true;
				}
				rightStickHorizontal=value*settings.getControlSensitivity();
				break;
			case 4:
				if (value > Z_THRESHOLD) {
					keys[CONTROLLER_L] = true;
				} else if (value<-Z_THRESHOLD){
					keys[CONTROLLER_R] = true;
				}
				// Z axis - this is binary
				break;
			}
		} else {
			switch (axisCode) {
			case 0:
				keys[CONTROLLER_UP] = false;
				keys[CONTROLLER_DOWN] = false;
				leftStickVertical=0;
				break;
			case 1:
				keys[CONTROLLER_LEFT] = false;
				keys[CONTROLLER_RIGHT] = false;
				leftStickHorizontal=0;
				break;
			case 2:
				keys[CONTROLLER_RIGHT_UP] = false;
				keys[CONTROLLER_RIGHT_DOWN] = false;
				rightStickVertical=0;
				break;
			case 3:
				keys[CONTROLLER_RIGHT_LEFT] = false;
				keys[CONTROLLER_RIGHT_RIGHT] = false;
				rightStickHorizontal=0;
				break;
			case 4:
				keys[CONTROLLER_L] = false;
				keys[CONTROLLER_R] = false;
				break;
			}
		}
		return false;
	}

	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		int ord = value.ordinal();
		keys[CONTROLLER_PAD_UP] = false;
		keys[CONTROLLER_PAD_DOWN] = false;
		keys[CONTROLLER_PAD_LEFT] = false;
		keys[CONTROLLER_PAD_RIGHT] = false;
		if (ord > 0) {
			if (povMap.containsKey(ord)) {
				keys[povMap.get(ord)] = true;
			}
		}
		return false;
	}

	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * Accessing the Input
	 */
	
	public int getMouseX() {
		return this.mouseX;
	}
	
	public int getMouseY() {
		return this.mouseY;
	}
	
	public boolean get(int keyCode) {
		if (keyCode<0||keyCode>=keys.length) {
			throw new ArrayIndexOutOfBoundsException("Illegal key code: "+keyCode);
		}
		boolean value=keys[keyCode];
		return value;
	}
	
	public boolean genericRelease(int keyCode) {
		if (keyCode<0||keyCode>=keys.length) {
			throw new ArrayIndexOutOfBoundsException("Illegal key code: "+keyCode);
		}
		boolean value=keys[keyCode];
		keys[keyCode]=false;
		return value;
	}
	
	public boolean a() {
		boolean value=keys[gpA]||keys[keyA];
		return value;
	}
	
	public boolean b() {
		boolean value=keys[gpB]||keys[keyB];
		return value;
	}
	
	public boolean x() {
		boolean value=keys[gpX]||keys[keyX];
		return value;
	}
	
	public boolean y() {
		boolean value=keys[gpY]||keys[keyY];
		return value;
	}
	
	public boolean l() {
		boolean value=keys[gpL]||keys[keyL];
		return value;
	}
	
	public boolean r() {
		boolean value=keys[gpR]||keys[keyR];
		return value;
	}
	
	public boolean l2() {
		boolean value=keys[gpL2]||keys[keyL2];
		return value;
	}
	
	public boolean r2() {
		boolean value=keys[gpR2]||keys[keyR2];
		return value;
	}
	
	public boolean ls() {
		boolean value=keys[gpLS]||keys[keyLS];
		return value;
	}
	
	public boolean rs() {
		boolean value=keys[gpRS]||keys[keyRS];
		return value;
	}
	
	public boolean start() {
		boolean value=keys[gpStart]||keys[keyStart];
		return value;
	}
	
	public boolean select() {
		boolean value=keys[gpSelect]||keys[keySelect];
		return value;
	}
	
	public boolean up() {
		boolean value=keys[gpUp]||keys[keyUp];
		return value;
	}
	
	public boolean down() {
		boolean value=keys[gpDown]||keys[keyDown];
		return value;
	}
	
	public boolean left() {
		boolean value=keys[gpLeft]||keys[keyLeft];
		return value;
	}
	
	public boolean right() {
		boolean value=keys[gpRight]||keys[keyRight];
		return value;
	}
	
	public boolean padUp() {
		boolean value=keys[gpPadUp]||keys[keyPadUp];
		return value;
	}
	
	public boolean padDown() {
		boolean value=keys[gpPadDown]||keys[keyPadDown];
		return value;
	}
	
	public boolean padLeft() {
		boolean value=keys[gpPadLeft]||keys[keyPadLeft];
		return value;
	}
	
	public boolean padRight() {
		boolean value=keys[gpPadRight]||keys[keyPadRight];
		return value;
	}
	
	public boolean rightUp() {
		boolean value=keys[gpRightUp]||keys[keyRightUp];
		return value;
	}
	
	public boolean rightDown() {
		boolean value=keys[gpRightDown]||keys[keyRightDown];
		return value;
	}
	
	public boolean rightLeft() {
		boolean value=keys[gpRightLeft]||keys[keyRightLeft];
		return value;
	}
	
	public boolean rightRight() {
		boolean value=keys[gpRightRight]||keys[keyRightRight];
		return value;
	}
	
	public boolean aRelease() {
		boolean value=keys[gpA]||keys[keyA];
		keys[keyA]=false;
		keys[gpA]=false;
		return value;
	}
	
	public boolean bRelease() {
		boolean value=keys[gpB]||keys[keyB];
		keys[keyB]=false;
		keys[gpB]=false;
		return value;
	}
	
	public boolean xRelease() {
		boolean value=keys[gpX]||keys[keyX];
		keys[keyX]=false;
		keys[gpX]=false;
		return value;
	}
	
	public boolean yRelease() {
		boolean value=keys[gpY]||keys[keyY];
		keys[keyY]=false;
		keys[gpY]=false;
		return value;
	}
	
	public boolean lRelease() {
		boolean value=keys[gpL]||keys[keyL];
		keys[keyL]=false;
		keys[gpL]=false;
		return value;
	}
	
	public boolean rRelease() {
		boolean value=keys[gpR]||keys[keyR];
		keys[keyR]=false;
		keys[gpR]=false;
		return value;
	}
	
	public boolean l2Release() {
		boolean value=keys[gpL2]||keys[keyL2];
		keys[keyL2]=false;
		keys[gpL2]=false;
		return value;
	}
	
	public boolean r2Release() {
		boolean value=keys[gpR2]||keys[keyR2];
		keys[keyR2]=false;
		keys[gpR2]=false;
		return value;
	}
	
	public boolean lsRelease() {
		boolean value=keys[gpLS]||keys[keyLS];
		keys[keyLS]=false;
		keys[gpLS]=false;
		return value;
	}
	
	public boolean rsRelease() {
		boolean value=keys[gpRS]||keys[keyRS];
		keys[keyRS]=false;
		keys[gpRS]=false;
		return value;
	}
	
	public boolean startRelease() {
		boolean value=keys[gpStart]||keys[keyStart];
		keys[keyStart]=false;
		keys[gpStart]=false;
		return value;
	}
	
	public boolean selectRelease() {
		boolean value=keys[gpSelect]||keys[keySelect];
		keys[keySelect]=false;
		keys[gpSelect]=false;
		return value;
	}
	
	public boolean upRelease() {
		boolean value=keys[gpUp]||keys[keyUp];
		keys[keyUp]=false;
		keys[gpUp]=false;
		return value;
	}
	
	public boolean downRelease() {
		boolean value=keys[gpDown]||keys[keyDown];
		keys[keyDown]=false;
		keys[gpDown]=false;
		return value;
	}
	
	public boolean leftRelease() {
		boolean value=keys[gpLeft]||keys[keyLeft];
		keys[keyLeft]=false;
		keys[gpLeft]=false;
		return value;
	}
	
	public boolean rightRelease() {
		boolean value=keys[gpRight]||keys[keyRight];
		keys[keyRight]=false;
		keys[gpRight]=false;
		return value;
	}
	
	public boolean padUpRelease() {
		boolean value=keys[gpPadUp]||keys[keyPadUp];
		keys[keyPadUp]=false;
		keys[gpPadUp]=false;
		return value;
	}
	
	public boolean padDownRelease() {
		boolean value=keys[gpPadDown]||keys[keyPadDown];
		keys[keyPadDown]=false;
		keys[gpPadDown]=false;
		return value;
	}
	
	public boolean padLeftRelease() {
		boolean value=keys[gpPadLeft]||keys[keyPadLeft];
		keys[keyPadLeft]=false;
		keys[gpPadLeft]=false;
		return value;
	}
	
	public boolean padRightRelease() {
		boolean value=keys[gpPadRight]||keys[keyPadRight];
		keys[keyPadRight]=false;
		keys[gpPadRight]=false;
		return value;
	}
	
	public boolean rightUpRelease() {
		boolean value=keys[gpRightUp]||keys[keyRightUp];
		keys[keyRightUp]=false;
		keys[gpRightUp]=false;
		return value;
	}
	
	public boolean rightDownRelease() {
		boolean value=keys[gpRightDown]||keys[keyRightDown];
		keys[keyRightDown]=false;
		keys[gpRightDown]=false;
		return value;
	}
	
	public boolean rightLeftRelease() {
		boolean value=keys[gpRightLeft]||keys[keyRightLeft];
		keys[keyRightLeft]=false;
		keys[gpRightLeft]=false;
		return value;
	}
	
	public boolean rightRightRelease() {
		boolean value=keys[gpRightRight]||keys[keyRightRight];
		keys[keyRightRight]=false;
		keys[gpRightRight]=false;
		return value;
	}
	
	/*
	 * Stick magnitude and direction
	 */
	
	private double getStickMagnitude(double r, double u) {
		double deadZone=Pisces.me().getSettings().getControlDeadZone();
		double tr=r-(keys[keyLeft]?1:0)+(keys[keyRight]?1:0);
		double tu=u-(keys[keyUp]?1:0)+(keys[keyDown]?1:0);
		if (Math.abs(tr)<deadZone&&Math.abs(tu)<deadZone){
			return 0;
		}
		return Math.sqrt(tr*tr+tu*tu);
	}
	
	private double getStickDirection(double r, double u) {
		double deadZone=Pisces.me().getSettings().getControlDeadZone();
		double tr=r-(keys[keyLeft]?1:0)+(keys[keyRight]?1:0);
		double tu=u-(keys[keyUp]?1:0)+(keys[keyDown]?1:0);
		if (Math.abs(tr)<deadZone&&Math.abs(tu)<deadZone){
			return -1;
		}
		return (Math.toDegrees(Math.atan2(-tu, tr))+360)%360;
	}
	
	public double getLeftStickMagnitude() {
		return getStickMagnitude(leftStickHorizontal, leftStickVertical);
	}
	
	public double getRightStickMagnitude() {
		return getStickMagnitude(rightStickHorizontal, rightStickVertical);
	}
	
	public double getLeftStickDirection() {
		return getStickDirection(leftStickHorizontal, leftStickVertical);
	}
	
	public double getRightStickDirection() {
		return getStickDirection(rightStickHorizontal, rightStickVertical);
	}
	
	public double getRightStickHorizontal() {
		return rightStickHorizontal;
	}
	
	public double getRightStickVertical() {
		return rightStickVertical;
	}
	
	public double getLeftStickHorizontal() {
		return leftStickHorizontal;
	}
	
	public double getLeftStickVertical() {
		return leftStickVertical;
	}
	
	/*
	 * Other accessors and mutators
	 */
	
	public boolean getLockCursor() {
		return this.lockCursor;
	}
	
	public void lockCursor() {
		this.lockCursor=true;
		Gdx.input.setCursorPosition(mouseX, Gdx.graphics.getHeight() - mouseY);
	}
	
	public void unlockCursor() {
		this.lockCursor=false;
	}
	
	public void setMousePosition(float x, float y) {
		Gdx.input.setCursorPosition((int)x, Gdx.graphics.getHeight()-(int)y);
		this.mouseX=(int)x;
		this.mouseY=(int)y;
	}
	
	public void setMousePosition(int x, int y) {
		Gdx.input.setCursorPosition(x, Gdx.graphics.getHeight()-y);
		this.mouseX=x;
		this.mouseY=y;
	}
	
	public String getInputName(int input) {
		return this.keyNames[input];
	}
}
