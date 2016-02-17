package com.teamdelta.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.teamdelta.game.entities.EntityNames;
import com.teamdelta.game.entities.RPSLSEntity;

/**
 * 
 * @author Ismael Poteau
 * @author Bill Warnock
 * @author Jake Herritt
 * @author Jeff Kunert
 * @author Dennis Bryant
 * @version 1.0.0.0
 * @since 2/9/16
 * 
 * The Game class controls the game.
 * 
 */

public class Main extends Game {
	//--
	SpriteBatch batch;
	OrthographicCamera camera;
	Viewport viewport;
	AssetManager assetMgr;

	//--GameLogic
	GameLogic gameLogic;

	//--Screens
	StartScreen startScreen;
	GameScreen gameScreen;
	AboutScreen aboutScreen;
	RulesScreen rulesScreen;
	AbstractScreen currentScreen;
	AbstractScreen previousScreen;
	
	//--Game assets
	TextureAtlas gameAtlas;
	Music clickSound;//changed Sound to Music - Warnock

	//--Entity Information - warnock
	RPSLSEntity rockEntity;
	RPSLSEntity paperEntity;
	RPSLSEntity scissorsEntity;
	RPSLSEntity lizardEntity;
	RPSLSEntity spockEntity;

	float delta;
	//
	final int WIDTH = 800;
	final int HEIGHT = 640;
	
	@Override
	public void create() {
		assetMgr = new AssetManager();
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
		camera.update();
		viewport = new StretchViewport(WIDTH, HEIGHT, camera);
		
		assetMgr.load("game.pack", TextureAtlas.class);
		assetMgr.finishLoading();

		//loaded assets and button click sound - Warnock
		gameAtlas = assetMgr.get("game.pack", TextureAtlas.class);
		clickSound = Gdx.audio.newMusic(Gdx.files.internal("buttonClick.mp3"));
		clickSound.setVolume(0.15f);

		createEntities();

		//created new instance of GameLogic - Warnock
		gameLogic = new GameLogic(this);
		startScreen = new StartScreen(this);
		gameScreen = new GameScreen(this);
		aboutScreen = new AboutScreen(this);
		rulesScreen = new RulesScreen(this);
		previousScreen = startScreen;
		currentScreen = startScreen;
		currentScreen.show();
		
	}

	@Override
	public void dispose(){
		currentScreen.dispose();
		assetMgr.dispose();
		batch.dispose();
	}
	
	@Override
	public void pause() {
		currentScreen.pause();
	}

	@Override
	public void resume() {
		currentScreen.resume();
		System.out.println("Main Resume");
	}

	@Override
	public void render(){
		delta = Gdx.graphics.getDeltaTime();
		camera.update();
		viewport.apply();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		currentScreen.render(delta);
		batch.end();
		if(!currentScreen.paused){
			currentScreen.update(delta);
		}
		
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
		viewport.apply();
	}

	//create entities - Warnock
	public void createEntities() {
		String rock = EntityNames.ROCK.toString();
		String paper = EntityNames.PAPER.toString();
		String scissors = EntityNames.SCISSORS.toString();
		String lizard = EntityNames.LIZARD.toString();
		String spock = EntityNames.SPOCK.toString();

		//Create RPSLSEntities
		rockEntity 		= new RPSLSEntity(rock, gameAtlas.findRegion(rock));
		paperEntity 	= new RPSLSEntity(paper, gameAtlas.findRegion(paper));
		scissorsEntity 	= new RPSLSEntity(scissors, gameAtlas.findRegion(scissors));
		lizardEntity 	= new RPSLSEntity(lizard, gameAtlas.findRegion(lizard));
		spockEntity 	= new RPSLSEntity(spock, gameAtlas.findRegion(spock));
	}
}
