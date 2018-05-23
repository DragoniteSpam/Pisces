package com.pisces;

import java.io.File;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import exceptions.ResourceNotFoundException;
import exceptions.TeamException;
import gamedata.abilities.PiscesAbility;
import gamedata.abilities.PiscesAbilityShape;
import gamedata.abilities.PiscesSkillTree;
import gamedata.items.PiscesItem;
import gamedata.items.PiscesItemAugment;
import gamedata.items.PiscesItemCollectable;
import gamedata.items.PiscesItemComponent;
import gamedata.items.PiscesItemHands;
import gamedata.items.PiscesItemHead;
import gamedata.items.PiscesItemKey;
import gamedata.items.PiscesItemManual;
import gamedata.items.PiscesItemMisc;
import gamedata.items.PiscesItemPants;
import gamedata.items.PiscesItemPocket;
import gamedata.items.PiscesItemShoes;
import gamedata.items.PiscesItemWeapon;
import gamedata.resources.PiscesModel;
import gamedata.resources.PiscesSound;
import gamedata.PiscesCharacter;
import gamedata.PiscesClass;
import gamedata.PiscesEffect;
import gamedata.PiscesElement;
import gamedata.PiscesMove;
import screens.DebugScreen;
import screens.HUDScreen;
import screens.LoadingScreen;
import screens.PauseScreen;
import stuff.DebugStates;
import stuff.Element;
import stuff.GameStates;
import stuff.ItemPockets;
import stuff.PiscesContactListener;
import stuff.PlayStates;
import stuff.pause.PauseStages;
import WorldObjects.WorldEntity;
import WorldObjects.WorldEntityPlayer;
import WorldObjects.WorldObject;
import WorldObjects.animation.WorldEntityAutoAnimate;

/**
 * The central hub that manages the game loop and pretty much connects
 * everything to everything else.
 * 
 * @author mpeng
 * @version 1.0.0
 */
public final class Pisces extends ApplicationAdapter implements ApplicationListener {
	/**
	 * Whether or not debug mode is enabled.
	 */
	public static final boolean DEBUG = true;
	/**
	 * A generic Vector3 for generic purposes.
	 */
	public static final Vector3 position = new Vector3();

	private GameStates gameState;
	private PlayStates playState;
	private DebugStates debugState;
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

	private Texture overlayTexture;
	private LoadingScreen loadingScreen;
	private PauseScreen pauseScreen;
	private DebugScreen debugScreen;
	private HUDScreen hudScreen;

	private SpriteBatch spriteBatch;
	private BitmapFont font12, font20, font32;
	private BitmapFont font32o, font64o;

	private ModelBatch modelBatch;

	// private PiscesCollisions world;

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
		startupBeginTime = System.currentTimeMillis();
		frames = 0;
		gameState = GameStates.LOADING;
		// playState = PlayStates.TITLE;
		playState = PlayStates.PLAYING;
		debugState = DebugStates.OFF;
		drawHUD = true;

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

		// collision=new PiscesCollisions();

		/*
		 * Collision
		 */

		collisionConfig = new btDefaultCollisionConfiguration();
		dispatcher = new btCollisionDispatcher(collisionConfig);
		broadphase = new btDbvtBroadphase();
		world = new btCollisionWorld(dispatcher, broadphase, collisionConfig);
		contactListener = new PiscesContactListener();
		contactListener.enable();

		// this.world=new PiscesCollisions();

		/*
		 * Assets
		 */

		overlayTexture = new Texture("../pisces-core/assets/graphics/overlay.png");
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

		stagePause = new Stage();
		stageDebug = new Stage();
		stageHUD = new Stage();

		pauseScreen = new PauseScreen(overlayTexture);
		debugScreen = new DebugScreen(overlayTexture);
		hudScreen = new HUDScreen(overlayTexture);

		stagePause.addActor(pauseScreen);
		stageDebug.addActor(debugScreen);
		stageHUD.addActor(hudScreen);

		/*
		 * The camera and things related to the camera
		 */

		camera = new PiscesCamera(60f, Gdx.graphics.getWidth() * 1f, Gdx.graphics.getHeight() * 1f);
		orthographic = new OrthographicCamera();

		modelBatch = new ModelBatch();

		/*
		 * Controls and Settings
		 */

		controller = new PiscesController();
		Gdx.input.setInputProcessor(controller);
		Controllers.addListener(controller);

		settings = new Settings();
		stats = new Statistics();

		/*
		 * Other important things
		 */

		player = null;

		/*
		 * Test stuff
		 */

		debugDrawer = new DebugDrawer();
		debugDrawer.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_MAX_DEBUG_DRAW_MODE);
		world.setDebugDrawer(debugDrawer);
	}

	/**
	 * Renders everything in the screen, as well as performs the game loop ("the
	 * Step event")
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
				controller.lockCursor();
				stats.setStartupTime(System.currentTimeMillis() - startupBeginTime);
				Tools.log("Startup took " + stats.getStartupTime() / 1000.0 + " seconds.");
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

			if (Gdx.input.isKeyJustPressed(Keys.GRAVE)) {
				if (debugState != DebugStates.LOG) {
					debugState = DebugStates.LOG;
					controller.unlockCursor();
				} else {
					debugState = DebugStates.OFF;
					controller.lockCursor();
				}
			}

			if (controller.startRelease()) {
				if (playState == PlayStates.PLAYING) {
					pauseGame();
				} else {
					unpauseGame();
				}
			}

			if (playState == PlayStates.PLAYING && debugState == DebugStates.OFF) {
				WorldObject.processAll(controller, Gdx.graphics.getDeltaTime(), world);
				//world.performDiscreteCollisionDetection();
				WorldObject.processAllPost(controller, Gdx.graphics.getDeltaTime(), world);
			}
			/*
			 * the Draw event
			 */
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
			// Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

			switch (playState) {
			case TITLE:
				break;
			case PLAYING:
				break;
			case DEBUG:
				break;
			case PAUSED:
				break;
			default:
				break;
			}
			/*
			 * The models
			 */

			int visible = 0;
			if (debugState.getDrawWorld()) {
				if (controller.get(PiscesController.DEBUG_DRAW_WORLD)) {
					debugDrawer.begin(camera);
					world.debugDrawWorld();
					debugDrawer.end();
				} else if (controller.get(PiscesController.DEBUG_BLOCK_WORLD)) {
					modelBatch.begin(camera);
					visible = WorldObject.renderAllDebug(modelBatch, environment);
					modelBatch.end();
				} else {
					modelBatch.begin(camera);
					visible = WorldObject.renderAll(modelBatch, environment);
					modelBatch.end();
				}
			}

			/*
			 * The HUD layer
			 */

			spriteBatch.setProjectionMatrix(orthographic.combined);
			spriteBatch.begin();
			if (debugState.getIsDebugState()) {
				stageDebug.draw();
			} else if (playState == PlayStates.PAUSED) {
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
		assets = new AssetManager();
		// @warning This may not work when the project is built, depending on the way
		// the jar links everything together!
		loadAssetModels(Gdx.files.internal("../pisces-core/assets/models/"));
		loadAssetSounds(Gdx.files.internal("../pisces-core/assets/sounds/"));
		try {
			// This is order dependant.
			PiscesItemPocket.createItemPockets(this.overlayTexture);
			PiscesElement.createAllElements(this.overlayTexture);
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
		FileHandle[] handles = root.list();
		for (FileHandle f : handles) {
			if (f.isDirectory()) {
				loadAssetModels(f);
			} else {
				assets.load(f.path(), Model.class);
			}
		}
	}

	private void loadAssetSounds(FileHandle root) {
		FileHandle[] handles = root.list();
		for (FileHandle f : handles) {
			if (f.isDirectory()) {
				loadAssetSounds(f);
			} else {
				PiscesSound.addSound(Gdx.audio.newSound(f), f.name());
			}
		}
	}

	private void processAssets() {
		Array<String> allModels = assets.getAssetNames();
		for (String name : allModels) {
			File f = new File(name);
			if (!f.getName().endsWith(".c.g3db")) {
				PiscesModel pm = new PiscesModel(f.getName());
				pm.setModelVisible(assets.get(name, Model.class));
				String collisionName = name.replace(".g3db", ".c.g3db");
				pm.setModelVisibleCollision(assets.get(collisionName, Model.class));
				pm.autoCollisionShape();
				if (name.endsWith(".nc.g3db")) {
					pm.setCulling(false);
				}
			}
		}

		new WorldEntity(new Vector3(96f, 0f, 64f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f),
				PiscesModel.get("barrel.g3db"), "Barrel");
		new WorldEntityAutoAnimate(new Vector3(256f, 0f, 128f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f),
				PiscesModel.get("windmill.g3db"), "Windmill", "Default Take");
		WorldEntityLight light = new WorldEntityLight(new Vector3(64f, 0f, 64f), new Quaternion(0, 0, 0, 0),
				new Vector3(1f, 1f, 1f), PiscesModel.get("lamp.g3db"), "Lamp");
		
		new WorldEntityAutoAnimate(new Vector3(128f, 0f, -128f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f),
				PiscesModel.get("chest.g3db"), "Treasure Chest", "chest.top|chest.topAction");
		light.setLightColor(Color.WHITE);
		light.setLightIntensity(1024f);
		light.setLightOffset(0f, 60f, 0f);
		light.setLight(environment);
		player = new WorldEntityPlayer(new Vector3(0f, 0f, 0f), new Quaternion(0, 0, 0, 0), new Vector3(1f, 1f, 1f),
				PiscesModel.get("npc.g3db"), "Player");
		try {
			player.addTeamMemeber(PiscesCharacter.getByName("Dragonite"));
		} catch (TeamException e) {
			e.printStackTrace();
			quit();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			quit();
		}
		TextureRegion sword = new TextureRegion(overlayTexture, 512, 768, 64, 64);
		TextureRegion naSword = new TextureRegion(overlayTexture, 576, 768, 64, 64);
		TextureRegion gun = new TextureRegion(overlayTexture, 512, 832, 64, 64);
		TextureRegion naGun = new TextureRegion(overlayTexture, 576, 832, 64, 64);
		TextureRegion staff = new TextureRegion(overlayTexture, 512, 896, 64, 64);
		TextureRegion naStaff = new TextureRegion(overlayTexture, 576, 896, 64, 64);
		TextureRegion wand = new TextureRegion(overlayTexture, 640, 768, 64, 64);
		TextureRegion naWand = new TextureRegion(overlayTexture, 704, 768, 64, 64);
		TextureRegion imageItem = new TextureRegion(overlayTexture, 768, 768, 64, 64);
		TextureRegion grayedImageItem = new TextureRegion(overlayTexture, 832, 768, 64, 64);

		TextureRegion imageMoveAvailable = new TextureRegion(overlayTexture);
		TextureRegion imageMoveUnavailable = new TextureRegion(overlayTexture);

		int[] stabbyPowers = { 10, 15, 20, 25, 30, 35, 40, 45, 50, 55 };
		double[] stabbyCooldowns = { 10, 9, 8, 7, 6, 5, 4.5, 4, 3.5, 3, 2.5 };
		int[] stabbyRanges = { 32, 36, 40, 44, 48 , 50, 52, 54, 56, 58 };
		PiscesEffect effectNone = new PiscesEffect("Default Effect", -1);

		PiscesMove moveStabby = new PiscesMove("Stabby", -1, imageMoveAvailable, imageMoveUnavailable, stabbyPowers,
				stabbyCooldowns, stabbyRanges, Element.NORMAL, true, effectNone);
		moveStabby.setSummary("You stab your opponent with the tip of your sword. Repeatedly.");

		new PiscesItemWeapon("Sword", -1, sword, naSword).setPocket(ItemPockets.WEAPON);
		new PiscesItemWeapon("Big Sword", -1, sword, naSword).setPocket(ItemPockets.WEAPON);
		PiscesItemWeapon weaponGun = new PiscesItemWeapon("Gun", -1, gun, naGun);
		weaponGun.setPocket(ItemPockets.WEAPON);
		weaponGun.setElementalDamage(Element.NORMAL, 0.5);
		weaponGun.setElementalDamage(Element.FIRE, 0.5);
		weaponGun.setElementalDamage(Element.WATER, 0.5);
		weaponGun.setElementalDamage(Element.DARK, 0.5);
		weaponGun.setElementalDamage(Element.LIGHT, 0.5);
		weaponGun.setRatings(0, 10, 5, 10, 5, 2, 2, 2, 5, 0, 0, 0);
		new PiscesItemWeapon("Big Gun", -1, gun, naGun).setPocket(ItemPockets.WEAPON);
		new PiscesItemCollectable("Pine Cone", -1, imageItem, grayedImageItem);
		new PiscesItemAugment("Attack+", -1, imageItem, grayedImageItem);
		new PiscesItemComponent("3/4-inch Screws", -1, imageItem, grayedImageItem);
		new PiscesItemHands("Oven Mitts", -1, imageItem, grayedImageItem);
		new PiscesItemKey("Dragonite's Front Door", -1, imageItem, grayedImageItem);
		new PiscesItemHead("Bicycle Helmet", -1, imageItem, grayedImageItem);
		new PiscesItemManual("Skill Book: Stabby", -1, imageItem, grayedImageItem, moveStabby).setMove(moveStabby);
		new PiscesItemMisc("Dog Bone", -1, imageItem, grayedImageItem);
		new PiscesItemPants("Boxers", -1, imageItem, grayedImageItem);
		new PiscesItemShoes("Fuzzy Socks", -1, imageItem, grayedImageItem);
		try {
			player.inventory.addItem(PiscesItem.getByName("Sword"));
			player.inventory.addItem(PiscesItem.getByName("Gun"));
			for (int i = 0; i < 28; i++) {
				player.inventory.addItem(PiscesItem.getByName("Big Gun"));
			}
			player.inventory.addItem(PiscesItem.getByName("Big Sword"));
			player.inventory.addItem(PiscesItem.getByName("Big Sword"));
			for (int i = 0; i < 3; i++) {
				player.inventory.addItem(PiscesItem.getByName("Pine Cone"));
			}
			player.inventory.addItem(PiscesItem.getByName("Attack+"));
			player.inventory.addItem(PiscesItem.getByName("3/4-inch Screws"));
			player.inventory.addItem(PiscesItem.getByName("Oven Mitts"));
			player.inventory.addItem(PiscesItem.getByName("Dragonite's Front Door"));
			player.inventory.addItem(PiscesItem.getByName("Bicycle Helmet"));
			player.inventory.addItem(PiscesItem.getByName("Skill Book: Stabby"));
			player.inventory.addItem(PiscesItem.getByName("Dog Bone"));
			player.inventory.addItem(PiscesItem.getByName("Boxers"));
			player.inventory.addItem(PiscesItem.getByName("Fuzzy Socks"));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			quit();
		}
		camera.setFollowing(player);
		camera.setCameraThirdPerson();
		// camera.setCameraFirstPerson();
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
	/*
	 * public PiscesCollisions getCollisionWorld() { return world; }
	 */

	/**
	 * @return The 3D camera
	 */
	public PiscesCamera getCamera() {
		return camera;
	}

	public DebugStates getDebugState() {
		return this.debugState;
	}

	public PauseScreen getPauseScreen() {
		return this.pauseScreen;
	}
	
	public void setPauseScreen(PauseStages stage) {
		pauseScreen.setPauseScreen(stage);
	}

	public void pauseGame() {
		playState = PlayStates.PAUSED;
		controller.unlockCursor();
		pauseScreen.reset(true);
	}

	public void unpauseGame() {
		playState = PlayStates.PLAYING;
		controller.lockCursor();
	}

	public void toTitle() {

	}

	public void quit() {
		// TODO probably autosave here or something
		Gdx.app.exit();
	}
}
