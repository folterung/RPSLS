package com.teamdelta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * 
 * @author Ismael Poteau - Rendering
 * @author Bill Warnock - Input Processing
 * @author Jake Herritt -  Game Logic
 * @author Jeff Kunert -  Initialization
 * @version 1.0.0.0
 * @since 2/9/16
 * 
 * The GameScreen class controls the game play and statistics display.
 * 
 * 2/9/16  - Fixed tieScore spelling errors - Dennis
 * 2/12/16 - Added non-image to buttons - Ismael
 * 2/15/16 - Fixed boolean set for winner - Dennis
 */
public class GameScreen extends AbstractScreen {
	GameModeManager gameModeManager;
	Button aboutButton;
	Button rulesButton;
	Button closeButton;
	Button playAgainButton;
	BitmapFont font;
	Vector3 input;
	boolean gameOver;
	
	public GameScreen(Main gameS) {
		super(gameS);
		input = new Vector3();
		font = new BitmapFont(Gdx.files.internal("calibrismall.fnt"));

		aboutButton = new Button(atlas.findRegion("ABOUTBUTTON"),
				atlas.findRegion("ABOUTBUTTONSELECTED"), new Rectangle(250, 30,
						100, 50));
		rulesButton = new Button(atlas.findRegion("RULESBUTTON"),
				atlas.findRegion("RULESBUTTONSELECTED"), new Rectangle(350, 30,
						100, 50));
		closeButton = new Button(atlas.findRegion("CLOSEBUTTON"),
				atlas.findRegion("CLOSEBUTTONSELECTED"), new Rectangle(450, 30,
						100, 50));
		//Ismael added playAgainButton
		
		playAgainButton = new Button(atlas.findRegion("PLAYBUTTON"),
				atlas.findRegion("PLAYBUTTONSELECTED"), new Rectangle(350, 155,
						100, 50));
		
		gameModeManager = new GameModeManager(this);
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void hide() {
		Gdx.input.setCatchBackKey(false);
	}

	@Override
	void loadAssets() {

	}

	@Override
	public void render(float timePassed) {
		renderButtons();
		gameModeManager.render(timePassed);
		
	}

	void renderButtons() {
	
		aboutButton.draw(batch);
		rulesButton.draw(batch);
		closeButton.draw(batch);
		//Ismael added render playAgainButton when game is over
		if(gameOver){
			playAgainButton.draw(batch);
		}
	}

	@Override
	void update(float timePassed) {
		gameModeManager.update(timePassed);
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		input.x = screenX;
		input.y = screenY;

		aboutButton.selected = false;
		rulesButton.selected = false;
		closeButton.selected = false;
		playAgainButton.selected = false;

		gameInstance.camera.unproject(input);

		if (aboutButton.colisionRect.contains(input.x, input.y)) {
			this.hide();
			gameInstance.currentScreen = gameInstance.aboutScreen;
			gameInstance.previousScreen = this;
			gameInstance.currentScreen.show();
		}

		if (rulesButton.colisionRect.contains(input.x, input.y)) {
			this.hide();
			gameInstance.currentScreen = gameInstance.rulesScreen;
			gameInstance.previousScreen = this;
			gameInstance.currentScreen.show();
		}

		if (closeButton.colisionRect.contains(input.x, input.y)) {
			this.hide();
			gameInstance.currentScreen = gameInstance.startScreen;
			gameInstance.previousScreen = gameInstance.currentScreen;
			gameInstance.currentScreen.show();
		}
		//Ismael added functionality to playAgainButton to reset game state
		if(gameOver){
				if (playAgainButton.colisionRect.contains(input.x, input.y)) {
				resetGameState();
			}
		}

		return true;
	}

	private void resetGameState() {
		gameOver = false;
		gameModeManager.reset();
	}

	// Override InputAdapter TouchUp
	//added click sound to buttons - Warnock
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		input.x = screenX;
		input.y = screenY;

		gameInstance.camera.unproject(input);

		if (aboutButton.colisionRect.contains(input.x, input.y)) {
			gameInstance.clickSound.play();
			aboutButton.selected = true;
		} else {
			aboutButton.selected = false;
		}

		if (rulesButton.colisionRect.contains(input.x, input.y)) {
			gameInstance.clickSound.play();
			rulesButton.selected = true;
		} else {
			rulesButton.selected = false;
		}

		if (closeButton.colisionRect.contains(input.x, input.y)) {
			closeButton.selected = true;
			gameInstance.clickSound.play();
		} else {
			closeButton.selected = false;
		}
		
		if (playAgainButton.colisionRect.contains(input.x, input.y)) {
			playAgainButton.selected = true;
			gameInstance.clickSound.play();
		} else {
			playAgainButton.selected = false;
		}

		return true;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	void disposeAssets() {
		// TODO Auto-generated method stub

	}

}
