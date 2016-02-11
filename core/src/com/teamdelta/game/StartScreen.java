package com.teamdelta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class StartScreen extends  AbstractScreen {

	Vector3 input;
	
	Button playGameButton;
	Button aboutButton;
	Button rulesButton;
	Button exitButton;
	
	
	public StartScreen(Main game) {
		super(game);

		loadAssets();

		input = new Vector3();

	}

	void loadAssets() {
		playGameButton = new Button(atlas.findRegion("PLAYBUTTON"),
				atlas.findRegion("PLAYBUTTONSELECTED"), 
				new Rectangle(gameInstance.WIDTH/2 - 60, 300, 140, 50));
		aboutButton = new Button(atlas.findRegion("ABOUTBUTTON"),
				atlas.findRegion("ABOUTBUTTONSELECTED"),
				new Rectangle(gameInstance.WIDTH/2 - 60, 225, 140, 50));
		rulesButton = new Button(atlas.findRegion("RULESBUTTON"),
				atlas.findRegion("RULESBUTTONSELECTED"),
				new Rectangle(gameInstance.WIDTH/2 - 60, 150, 140, 50));
		exitButton = new Button(atlas.findRegion("EXITBUTTON"),
				atlas.findRegion("EXITBUTTONSELECTED"),
				new Rectangle(gameInstance.WIDTH/2 - 60, 75, 140, 50));

	}
	@Override
	public void show() {

		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
		System.out.println("Menu show");
	}

	@Override
	public void render(float delta) {
		playGameButton.draw(batch);
		aboutButton.draw(batch);
		rulesButton.draw(batch);
		exitButton.draw(batch);
	}
	
	@Override
	public void update(float timeSinceLastFrame) {

	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

		System.out.println("Menu resumed");
	}

	@Override
	public void hide() {
		System.out.println("Menu hidden!");
		Gdx.input.setCatchBackKey(false);
	}

	@Override
	public void dispose() {
		System.out.println("Menu disposed!");
		gameInstance.assetMgr.dispose();
	}

	// Override InputAdapter TouchUp
	public boolean touchUp(int screenX, int screenY, int pointer, int button){
			
			input.x = screenX;
			input.y = screenY;
			
			playGameButton.selected = false;
			aboutButton.selected = false;
			rulesButton.selected = false;
			exitButton.selected = false;
			
			gameInstance.camera.unproject(input);
			
			if(playGameButton.colisionRect.contains(input.x, input.y)){
				this.hide();
				gameInstance.currentScreen = gameInstance.gameScreen;
				gameInstance.currentScreen.show();
			}
			
			if(aboutButton.colisionRect.contains(input.x, input.y)){
				this.hide();
				gameInstance.currentScreen = gameInstance.aboutScreen;
				gameInstance.currentScreen.show();
			}
			
			if(rulesButton.colisionRect.contains(input.x, input.y)){
				this.hide();
				gameInstance.currentScreen = gameInstance.rulesScreen;
				gameInstance.currentScreen.show();
			}
			
			if(exitButton.colisionRect.contains(input.x, input.y)){
				dispose();
				Gdx.app.exit();
			}
			
		return true;
	}

	// Override InputAdapter
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		input.x = screenX;
		input.y = screenY;

		gameInstance.camera.unproject(input);
		if(playGameButton.colisionRect.contains(input.x, input.y)){
			playGameButton.selected = true;
		}else{
			playGameButton.selected = false;
		}
		
		if(aboutButton.colisionRect.contains(input.x, input.y)){
			aboutButton.selected = true;
		}else{
			aboutButton.selected = false;
		}
		
		if(rulesButton.colisionRect.contains(input.x, input.y)){
			rulesButton.selected = true;
		}else{
			rulesButton.selected = false;
		}
		
		if(exitButton.colisionRect.contains(input.x, input.y)){
			exitButton.selected = true;
		}else{
			exitButton.selected = false;
		}
		
		return true;
	}

	// Override InputAdapter
	public boolean keyUp(int keycode) {

		if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
			Gdx.input.setCatchBackKey(false);
			Gdx.app.exit();
		}
		return true;
	}

	@Override
	void disposeAssets() {
		// TODO Auto-generated method stub
		
	}

}
