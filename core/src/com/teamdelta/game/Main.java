package com.teamdelta.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends Game {
	//--
	SpriteBatch batch;
	OrthographicCamera camera;
	Viewport viewport;
	AssetManager assetMgr;
	
	//--Screens
	StartScreen startScreen;
	GameScreen gameScreen;
	AboutScreen aboutScreen;
	RulesScreen rulesScreen;
	AbstractScreen currentScreen;
	AbstractScreen previousScreen;
	
	//--Game assets
	TextureAtlas gameAtlas;
	Sound clickSound;
	
	
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

}
