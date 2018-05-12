package com.pisces;

import java.io.File;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import com.badlogic.gdx.ApplicationAdapter;
<<<<<<< HEAD
import com.badlogic.gdx.ApplicationListener;
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
<<<<<<< HEAD
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.DebugDrawer;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.ContactResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
=======
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btOverlappingPairCache;
<<<<<<< HEAD
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import WorldObjects.WorldEntity;
import WorldObjects.WorldEntityPlayer;
import WorldObjects.WorldObject;
<<<<<<< HEAD
import WorldObjects.lights.WorldEntityLight;
import gamedata.PiscesModel;
import gamedata.PiscesSound;
import gamedata.PiscesItemPocket;
import screens.DebugScreen;
import screens.HUDScreen;
import screens.LoadingScreen;
import screens.PauseScreen;
import stuff.CameraProperties;
import stuff.DebugStates;
=======
import gamedata.PiscesModel;
import screens.LoadingScreen;
import stuff.CameraProperties;
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
import stuff.GameStates;
import stuff.PiscesContactListener;
import stuff.PlayStates;
import stuff.VRAContainer;

<<<<<<< HEAD
/**
 * The central hub that manages the game loop and pretty much connects everything to everything else.
 * 
 * @author mpeng
 * @version	1.0.0
 */
public final class Pisces extends ApplicationAdapter implements ApplicationListener {
	/**
	 * Whether or not debug mode is enabled.
	 */
	public static final boolean DEBUG = true;
	/**
	 * A generic Vector3 for generic purposes.
	 */
	public static final Vector3 position=new Vector3();
	
	private GameStates gameState;
	private PlayStates playState;
	private DebugStates debugState;
	private boolean isPaused;
=======
public final class Pisces extends ApplicationAdapter {
	public static final boolean DEBUG = true;
	public static final Vector3 position=new Vector3();
	private GameStates gameState;
	private PlayStates playState;
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	private static Pisces me = null;
	
	private AssetManager assets;

	private long frames;
	private long startupBeginTime;
	private Settings settings;
	private Stats stats;
	
	private PiscesController controller;
	private PiscesCamera camera;
	private Environment environment;

	private LoadingScreen loadingScreen;
<<<<<<< HEAD
	private PauseScreen pauseScreen;
	private DebugScreen debugScreen;
	private HUDScreen hudScreen;
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e

	private SpriteBatch spriteBatch;
	private BitmapFont font12, font20;

	private ModelBatch modelBatch;
	
	private btCollisionConfiguration collisionConfig;
	private btDispatcher dispatcher;
	private btBroadphaseInterface broadphase;
	private btCollisionWorld world;
	private PiscesContactListener contactListener;
<<<<<<< HEAD
	private DebugDrawer debugDrawer;
	


	/**
	 * Constructs a new Pisces. Sort of a singleton?
	 */
=======

>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public Pisces() {
		super();
		if (me == null) {
			me = this;
		}
	}

<<<<<<< HEAD
	/**
	 * @return The running Pisces object. Sort of a singleton.
	 */
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public static Pisces me() {
		return me;
	}

<<<<<<< HEAD
	/**
	 * Initializes (most) variables, begins the loading of (most) game data
	 */
=======
	@Override
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public void create() {
		/*
		 * Really important general stuff
		 */
		startupBeginTime=System.currentTimeMillis();
		frames = 0;
		gameState = GameStates.LOADING;
		//playState = PlayStates.TITLE;
		playState=PlayStates.PLAYING;
<<<<<<< HEAD
		debugState=DebugStates.OFF;
		isPaused=false;
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
		spriteBatch = new SpriteBatch();
		font12 = new BitmapFont();
		font20 = new BitmapFont();
		
		/*
		 * Various libraries, etc
		 */

		Bullet.init();
		
		/*
		 * Collision
		 */
		
		collisionConfig=new btDefaultCollisionConfiguration();
		dispatcher=new btCollisionDispatcher(collisionConfig);
		broadphase=new btDbvtBroadphase();
		world=new btCollisionWorld(dispatcher, broadphase, collisionConfig);
		contactListener=new PiscesContactListener();
		contactListener.enable();
		
		/*
		 * Assets
		 */
		
		loadAssets();
		
		/*
		 * Lighting
		 */

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
<<<<<<< HEAD
		//environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
=======
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
		
		/*
		 * Screens
		 */

		loadingScreen = new LoadingScreen();
<<<<<<< HEAD
		pauseScreen=new PauseScreen();
		debugScreen=new DebugScreen();
		hudScreen=new HUDScreen();
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
		
		/*
		 * The camera and things related to the camera
		 */

		camera=new PiscesCamera(60f, Gdx.graphics.getWidth()*1f, Gdx.graphics.getHeight()*1f);
		
		modelBatch=new ModelBatch();
		
		/*
		 * Controls and Settings
		 */
		
		controller=new PiscesController();
		Gdx.input.setInputProcessor(controller);
		Controllers.addListener(controller);
		
		settings=new Settings();
		stats=new Stats();
		
		/*
		 * Test stuff
		 */
<<<<<<< HEAD
		
		debugDrawer=new DebugDrawer();
		debugDrawer.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_MAX_DEBUG_DRAW_MODE);
		world.setDebugDrawer(debugDrawer);
	}

	/**
	 * Renders everything in the screen, as well as performs the game loop ("the Step event")
	 */
=======
	}

	@Override
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public void render() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		if (gameState == GameStates.LOADING) {
			if (assets.update()) {
				processAssets();
				gameState = GameStates.PLAYING;
				stats.setStartupTime(System.currentTimeMillis()-startupBeginTime);
				Tools.log("Startup took "+stats.getStartupTime()/1000.0+" seconds.");
			} else {
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				spriteBatch.begin();
				loadingScreen.render(spriteBatch, frames);
				spriteBatch.end();
			}
		} else {
			camera.update();
			WorldObject.processAll(controller,  Gdx.graphics.getDeltaTime(), world);
			world.performDiscreteCollisionDetection();
			WorldObject.processAllPost(controller, Gdx.graphics.getDeltaTime(), world);
			
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
			
			switch (playState) {
			case TITLE:
				break;
			case PLAYING:
				break;
			case DEBUG:
				break;
			}
			/*
			 * The models
			 */
<<<<<<< HEAD
			
			int visible=0; 
			if (controller.get(PiscesController.DEBUG_DRAW_WORLD)) {
				debugDrawer.begin(camera);
				world.debugDrawWorld();
				debugDrawer.end();
			} else {
				modelBatch.begin(camera);
				visible=WorldObject.renderAll(modelBatch, environment);
				modelBatch.end();
			}
			
			/*
			 * The HUD layer
			 */
			
			if (isPaused) {
				pauseScreen.render(spriteBatch, frames);
			} else {
				hudScreen.render(spriteBatch,  frames);
				debugScreen.render(spriteBatch, frames);
			}
=======
			modelBatch.begin(camera);
			int visible=WorldObject.renderAll(modelBatch, environment);
			modelBatch.end();
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
		}
		frames++;
	}

<<<<<<< HEAD
	/**
	 * Gets rid of Bullet objects, closes streams, that sort of thing
	 */
=======
	@Override
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public void dispose() {
		modelBatch.dispose();
		spriteBatch.dispose();

		WorldObject.disposeAll();
		
		// These must be disposed of in the reverse order that they were created.
		world.dispose();
		broadphase.dispose();
		dispatcher.dispose();
		collisionConfig.dispose();

		contactListener.disable();
		contactListener.dispose();
	}

<<<<<<< HEAD
	/**
	 * Code that runs when the game window is resized
	 */
	public void resize(int width, int height) {
	}

	/**
	 * Code that runs when the game is paused (not used on Desktop)
	 */
=======
	public void resize(int width, int height) {
	}

>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public void pause() {

	}

<<<<<<< HEAD
	/**
	 * Code that runs when the game is resumed (not used on Desktop)
	 */
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public void resume() {

	}

	private void loadAssets() {
		assets=new AssetManager();
		// @warning This may not work when the project is built, depending on the way the jar links everything together!
		loadAssetModels(Gdx.files.internal("../pisces-core/assets/models/"));
<<<<<<< HEAD
		loadAssetSounds(Gdx.files.internal("../pisces-core/assets/sounds/"));
		PiscesItemPocket.createItemPockets();
		Text.load("en");
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	}
	
	private void loadAssetModels(FileHandle root) {
		FileHandle[] handles=root.list();
		for (FileHandle f : handles) {
			if (f.isDirectory()){
				loadAssetModels(f);
			} else {
				assets.load(f.path(), Model.class);
			}
		}
	}
	
<<<<<<< HEAD
	private void loadAssetSounds(FileHandle root) {
		FileHandle[] handles=root.list();
		for (FileHandle f : handles) {
			if (f.isDirectory()){
				loadAssetSounds(f);
			} else {
				PiscesSound.addSound(Gdx.audio.newSound(f), f.name());
			}
		}
	}
	
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	private void processAssets() {
		Array<String> allModels=assets.getAssetNames();
		for (String name : allModels) {
			File f=new File(name);
			if (!f.getName().endsWith("_c.g3db")) {
				PiscesModel pm=new PiscesModel(f.getName());
				pm.setModelVisible(assets.get(name, Model.class));
				String collisionName=name.replace(".g3db", "_c.g3db");
				pm.setModelVisibleCollision(assets.get(collisionName, Model.class));
				pm.autoCollisionShape();
				if (name.endsWith(".nc.g3db")) {
					pm.setCulling(false);
				}
			}
		}
		
		new WorldEntity(new Vector3(96f, 0f, 64f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f), PiscesModel.get("barrel.g3db"), "Barrel");
<<<<<<< HEAD
		WorldEntityLight light=new WorldEntityLight(new Vector3(64f, 0f, 64f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f), PiscesModel.get("lamp.g3db"), "Lamp");
		light.setLightColor(Color.WHITE);
		light.setLightIntensity(1024f);
		light.setLightOffset(0f, 60f, 0f);
		light.setLight(environment);
=======
		new WorldEntity(new Vector3(64f, 0f, 64f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f), PiscesModel.get("lamp.g3db"), "Lamp");
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
		camera.setFollowing(new WorldEntityPlayer(new Vector3(0f, 0f, 0f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f), PiscesModel.get("npc.g3db"), "Player"));
		camera.setCameraThirdPerson();
		//camera.setCameraFirstPerson();
	}

<<<<<<< HEAD
	/**
	 * @return The size-twelve game font
	 */
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public BitmapFont getFont12() {
		return font12;
	}

<<<<<<< HEAD
	/**
	 * @return The size-twenty game font
	 */
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public BitmapFont getFont20() {
		return font20;
	}
	
<<<<<<< HEAD
	/**
	 * @return The input-handling object
	 */
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public PiscesController getController() {
		return controller;
	}
	
<<<<<<< HEAD
	/**
	 * @return The game settings device
	 */
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public Settings getSettings() {
		return settings;
	}
	
<<<<<<< HEAD
	/**
	 * @return The collision world
	 */
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public btCollisionWorld getCollisionWorld() {
		return world;
	}
	
<<<<<<< HEAD
	/**
	 * @return The 3D camera
	 */
=======
>>>>>>> a927a9627e0d19b60834746900d36667d53f337e
	public PiscesCamera getCamera() {
		return camera;
	}
}
