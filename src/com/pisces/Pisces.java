package com.pisces;

import java.io.File;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.DebugDrawer;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.badlogic.gdx.utils.Array;

import WorldObjects.WorldEntity;
import WorldObjects.WorldEntityPlayer;
import WorldObjects.WorldObject;
import WorldObjects.lights.WorldEntityLight;
import gamedata.PiscesModel;
import gamedata.PiscesSound;
import gamedata.PiscesItemPocket;
import screens.DebugScreen;
import screens.HUDScreen;
import screens.LoadingScreen;
import screens.PauseScreen;
import stuff.DebugStates;
import stuff.GameStates;
import stuff.PiscesContactListener;
import stuff.PlayStates;

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
	private PauseScreen pauseScreen;
	private DebugScreen debugScreen;
	private HUDScreen hudScreen;

	private SpriteBatch spriteBatch;
	private BitmapFont font12, font20;

	private ModelBatch modelBatch;
	
	private btCollisionConfiguration collisionConfig;
	private btDispatcher dispatcher;
	private btBroadphaseInterface broadphase;
	private btCollisionWorld world;
	private PiscesContactListener contactListener;
	private DebugDrawer debugDrawer;
	


	/**
	 * Constructs a new Pisces. Sort of a singleton?
	 */
	public Pisces() {
		super();
		if (me == null) {
			me = this;
		}
	}

	/**
	 * @return The running Pisces object. Sort of a singleton.
	 */
	public static Pisces me() {
		return me;
	}

	/**
	 * Initializes (most) variables, begins the loading of (most) game data
	 */
	@Override
	public void create() {
		/*
		 * Really important general stuff
		 */
		startupBeginTime=System.currentTimeMillis();
		frames = 0;
		gameState = GameStates.LOADING;
		//playState = PlayStates.TITLE;
		playState=PlayStates.PLAYING;
		debugState=DebugStates.OFF;
		isPaused=false;
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
		//environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		/*
		 * Screens
		 */

		loadingScreen = new LoadingScreen();
		pauseScreen=new PauseScreen();
		debugScreen=new DebugScreen();
		hudScreen=new HUDScreen();
		
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
		debugDrawer=new DebugDrawer();
		debugDrawer.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_MAX_DEBUG_DRAW_MODE);
		world.setDebugDrawer(debugDrawer);
	}

	/**
	 * Renders everything in the screen, as well as performs the game loop ("the Step event")
	 */

	@Override
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
		}
		frames++;
	}

	/**
	 * Gets rid of Bullet objects, closes streams, that sort of thing
	 */

	@Override

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

	/**
	 * Code that runs when the game is paused (not used on Desktop)
	 */
	public void resize(int width, int height) {
	}

	public void pause() {

	}

	/**
	 * Code that runs when the game is resumed (not used on Desktop)
	 */

	public void resume() {

	}

	private void loadAssets() {
		assets=new AssetManager();
		// @warning This may not work when the project is built, depending on the way the jar links everything together!
		loadAssetModels(Gdx.files.internal("../pisces-core/assets/models/"));
		loadAssetSounds(Gdx.files.internal("../pisces-core/assets/sounds/"));
		PiscesItemPocket.createItemPockets();
		Text.load("en");
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
		WorldEntityLight light=new WorldEntityLight(new Vector3(64f, 0f, 64f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f), PiscesModel.get("lamp.g3db"), "Lamp");
		light.setLightColor(Color.WHITE);
		light.setLightIntensity(1024f);
		light.setLightOffset(0f, 60f, 0f);
		light.setLight(environment);
		new WorldEntity(new Vector3(64f, 0f, 64f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f), PiscesModel.get("lamp.g3db"), "Lamp");
		camera.setFollowing(new WorldEntityPlayer(new Vector3(0f, 0f, 0f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f), PiscesModel.get("npc.g3db"), "Player"));
		camera.setCameraThirdPerson();
		//camera.setCameraFirstPerson();
	}

	/**
	 * @return The size-twelve game font
	 */
	public BitmapFont getFont12() {
		return font12;
	}

	/**
	 * @return The size-twenty game font
	 */
	public BitmapFont getFont20() {
		return font20;
	}
	
	/**
	 * @return The input-handling object
	 */
	public PiscesController getController() {
		return controller;
	}
	
	/**
	 * @return The game settings device
	 */
	public Settings getSettings() {
		return settings;
	}
	
	/**
	 * @return The collision world
	 */
	public btCollisionWorld getCollisionWorld() {
		return world;
	}
	
	/**
	 * @return The 3D camera
	 */
	public PiscesCamera getCamera() {
		return camera;
	}
}
