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
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.DebugDrawer;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import WorldObjects.lights.WorldEntityLight;
import collisions.PiscesCollisions;
import exceptions.ResourceNotFoundException;
import exceptions.TeamException;
import gamedata.abilities.PiscesAbility;
import gamedata.abilities.PiscesAbilityShape;
import gamedata.abilities.PiscesSkillTree;
import gamedata.items.PiscesItemPocket;
import gamedata.resources.PiscesModel;
import gamedata.resources.PiscesSound;
import gamedata.PiscesCharacter;
import gamedata.PiscesClass;
import screens.DebugScreen;
import screens.HUDScreen;
import screens.LoadingScreen;
import screens.PauseScreen;
import stuff.DebugStates;
import stuff.GameStates;
import stuff.PiscesContactListener;
import stuff.PlayStates;
import WorldObjects.WorldEntity;
import WorldObjects.WorldEntityPlayer;
import WorldObjects.WorldObject;
import WorldObjects.animation.WorldEntityAutoAnimate;

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
	public boolean drawHUD;

	private static Pisces me = null;
	
	private AssetManager assets;

	private long frames;
	private long startupBeginTime;
	private Settings settings;
	private Statistics stats;
	
	private PiscesController controller;
	private PiscesCamera camera;
	private OrthographicCamera orthographic;
	private Environment environment;

	private Stage stagePause;
	private Stage stageDebug;
	private Stage stageHUD;
	
	private LoadingScreen loadingScreen;
	private PauseScreen pauseScreen;
	private DebugScreen debugScreen;
	private HUDScreen hudScreen;

	private SpriteBatch spriteBatch;
	private BitmapFont font12, font20, font32;
	private BitmapFont font32o, font64o;

	private ModelBatch modelBatch;
	
	//private PiscesCollisions world;
	
	private btCollisionConfiguration collisionConfig;
	private btDispatcher dispatcher;
	private btBroadphaseInterface broadphase;
	private btCollisionWorld world;
	private PiscesContactListener contactListener;
	
	private WorldEntityPlayer player;
	
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
		drawHUD=true;
		
		spriteBatch = new SpriteBatch();
		font12 = new BitmapFont(Gdx.files.internal("../pisces-core/assets/graphics/delfino12.fnt"));
		font20 = new BitmapFont(Gdx.files.internal("../pisces-core/assets/graphics/delfino20.fnt"));
		font32 = new BitmapFont(Gdx.files.internal("../pisces-core/assets/graphics/delfino32.fnt"));
		font32o = new BitmapFont(Gdx.files.internal("../pisces-core/assets/graphics/delfino32o.fnt"));
		font64o = new BitmapFont(Gdx.files.internal("../pisces-core/assets/graphics/delfino64o.fnt"));
		
		/*
		 * Various libraries, etc
		 */

		Bullet.init();
		
		//collision=new PiscesCollisions();
		
		/*
		 * Collision
		 */
		
		collisionConfig=new btDefaultCollisionConfiguration();
		dispatcher=new btCollisionDispatcher(collisionConfig);
		broadphase=new btDbvtBroadphase();
		world=new btCollisionWorld(dispatcher, broadphase, collisionConfig);
		contactListener=new PiscesContactListener();
		contactListener.enable();
		
		//this.world=new PiscesCollisions();
		
		/*
		 * Assets
		 */
		
		loadAssets();
		
		/*
		 * Lighting
		 */

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		/*
		 * Screens
		 */

		loadingScreen = new LoadingScreen();
		
		stagePause=new Stage();
		stageDebug=new Stage();
		stageHUD=new Stage();
		
		pauseScreen=new PauseScreen();
		debugScreen=new DebugScreen();
		hudScreen=new HUDScreen();
		
		stagePause.addActor(pauseScreen);
		stageDebug.addActor(debugScreen);
		stageHUD.addActor(hudScreen);
		
		/*
		 * The camera and things related to the camera
		 */

		camera=new PiscesCamera(60f, Gdx.graphics.getWidth()*1f, Gdx.graphics.getHeight()*1f);
		orthographic=new OrthographicCamera();
		
		modelBatch=new ModelBatch();
		
		/*
		 * Controls and Settings
		 */
		
		controller=new PiscesController();
		Gdx.input.setInputProcessor(controller);
		Controllers.addListener(controller);
		
		settings=new Settings();
		stats=new Statistics();
		
		/*
		 * Other important things
		 */
		
		player=null;
		
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
		/*
		 * Loading screen
		 */
		if (gameState == GameStates.LOADING) {
			if (assets.update()) {
				processAssets();
				gameState = GameStates.PLAYING;
				stats.setStartupTime(System.currentTimeMillis()-startupBeginTime);
				Tools.log("Startup took "+stats.getStartupTime()/1000.0+" seconds.");
			} else {
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				spriteBatch.begin();
				loadingScreen.render(spriteBatch, frames, orthographic);
				spriteBatch.end();
			}
		/*
		 * Game world screen
		 */
		} else {
			camera.update();
			
			/*
			 * the Step event
			 */
			WorldObject.processAll(controller,  Gdx.graphics.getDeltaTime(), world);
			//world.performDiscreteCollisionDetection();
			WorldObject.processAllPost(controller, Gdx.graphics.getDeltaTime(), world);
			
			/*
			 * the Draw event
			 */
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
			if (DebugStates.drawWorld(debugState)) {
				if (controller.get(PiscesController.DEBUG_DRAW_WORLD)) {
					debugDrawer.begin(camera);
					world.debugDrawWorld();
					debugDrawer.end();
				} else if (controller.get(PiscesController.DEBUG_BLOCK_WORLD)){
					modelBatch.begin(camera);
					visible=WorldObject.renderAllDebug(modelBatch, environment);
					modelBatch.end();
				} else {
					modelBatch.begin(camera);
					visible=WorldObject.renderAll(modelBatch, environment);
					modelBatch.end();
				}
			}
			
			/*
			 * The HUD layer
			 */
			
			spriteBatch.setProjectionMatrix(orthographic.combined);
			spriteBatch.begin();
			if (DebugStates.isDebugState(debugState)) {
				stageDebug.draw();
			} else if (isPaused) {
				stagePause.draw();
			} else if (drawHUD) {
				stageHUD.draw();
			}
			if (DEBUG) {
				
			}
			spriteBatch.end();
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
		
		stagePause.dispose();
		stageDebug.dispose();
		stageHUD.dispose();
	}

	/**
	 * Code that runs when the game is paused (not used on Desktop)
	 */
	public void resize(int width, int height) {
		stagePause.getViewport().update(width, height, true);
		stageDebug.getViewport().update(width, height, true);
		stageHUD.getViewport().update(width, height, true);
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
		try {
			// This is order dependant.
			PiscesItemPocket.createItemPockets();
			PiscesAbilityShape.createAbilityShapes();
			PiscesAbility.createAbilities();
			PiscesSkillTree.createSkillTrees();
			PiscesClass.createClasses();
			PiscesCharacter.createCharacters();
			Text.load("en");
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
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
		new WorldEntityAutoAnimate(new Vector3(256f, 0f, 128f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f), PiscesModel.get("windmill.g3db"), "Windmill");
		WorldEntityLight light=new WorldEntityLight(new Vector3(64f, 0f, 64f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f), PiscesModel.get("lamp.g3db"), "Lamp");
		light.setLightColor(Color.WHITE);
		light.setLightIntensity(1024f);
		light.setLightOffset(0f, 60f, 0f);
		light.setLight(environment);
		player=new WorldEntityPlayer(new Vector3(0f, 0f, 0f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f), PiscesModel.get("npc.g3db"), "Player");
		try {
			player.addTeamMemeber(PiscesCharacter.getByName("Dragonite"));
		} catch (TeamException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		camera.setFollowing(player);
		camera.setCameraThirdPerson();
		//camera.setCameraFirstPerson();
	}
	
	/**
	 * @return The game player
	 */
	
	public WorldEntityPlayer getPlayer() {
		return this.player;
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
	 * @return The size-thirty two game font
	 */
	public BitmapFont getFont32() {
		return font32;
	}
	
	/**
	 * @return The size-thirty two/outlined game font
	 */
	public BitmapFont getFont32o() {
		return font32o;
	}
	
	/**
	 * @return The size-sixty four/outlined game font
	 */
	public BitmapFont getFont64o() {
		return font64o;
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
	/*public PiscesCollisions getCollisionWorld() {
		return world;
	}*/
	
	/**
	 * @return The 3D camera
	 */
	public PiscesCamera getCamera() {
		return camera;
	}
	
	public DebugStates getDebugState() {
		return this.debugState;
	}
}
