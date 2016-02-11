package com.teamdelta.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * 
 * @author Dennis Bryant
 * @version 1.0.0.0
 * @since 2/8/16
 * 
 * The AboutScreen class create the credit screen for the application.
 * 
 */
public class AboutScreen extends AbstractScreen{
	Texture credits;
	BitmapFont bigFont;
	BitmapFont smallFont;
	Button closeButton;
	Vector3 input;
	
	/**
	 * The contructor to create the about screen.
	 * @param game
	 */
	public AboutScreen(Main game) {
		super(game);
		input = new Vector3();
		closeButton = new Button(atlas.findRegion("CLOSEBUTTON"),
				atlas.findRegion("CLOSEBUTTONSELECTED"),
				new Rectangle(gameInstance.WIDTH/2 - 60, 55, 120, 40));
		
		bigFont = new BitmapFont(Gdx.files.internal("calibri.fnt"));
		smallFont = new BitmapFont(Gdx.files.internal("calibrismall.fnt"));
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}

	/**
	 * The render method paints the screen. Set all needed fields.
	 */
	@Override
	public void render(float delta) {
		bigFont.draw(batch, "Rock, Paper, Scissors, Lizard, Spock", 220, 600);
		bigFont.draw(batch, "Developed by Team Delta", 245, 570);
		
		smallFont.draw(batch, "Team Lead: ", 250, 500-20);
		smallFont.draw(batch, "Jeffrey Kunert", 250, 480-20);
		smallFont.draw(batch, "Project Manager: ", 250, 440-20);
		smallFont.draw(batch, "Dennis Bryant", 250, 420-20);
		smallFont.draw(batch, "Programming Lead: ", 250, 380-20);
		smallFont.draw(batch, "Ismael Poteau", 250, 360-20);
		smallFont.draw(batch, "UI design and QA", 250, 320-20);
		smallFont.draw(batch, "Jacob Herritt", 250, 300-20);
		smallFont.draw(batch, "William Warnock", 250, 280-20);
		smallFont.draw(batch, "Version 1.0.0.0",  250, 240-20);
		
		closeButton.draw(batch);
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		Gdx.input.setCatchBackKey(false);
	}

	@Override
	public void dispose() {
		Gdx.input.setCatchBackKey(false);
	}

	void loadAssets() {
	}

	void update(float timeSinceLastFrame) {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * The touchDown method will hi-light the close button if mouse pointer is over top.
	 */
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		input.x = screenX;
		input.y = screenY;

		gameInstance.camera.unproject(input);
		if(closeButton.colisionRect.contains(input.x, input.y)){
			closeButton.selected = true;
		}else{
			closeButton.selected = false;
		}
			
		return true;
	}

	@Override
	/**
	 * ThetouchUp method will process the mouse click if mouse pointis is over top.
	 */
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		input.x = screenX;
		input.y = screenY;   

		gameInstance.camera.unproject(input);
		closeButton.selected = false;
		
		if(closeButton.colisionRect.contains(input.x, input.y)){
			this.hide();
			gameInstance.currentScreen = gameInstance.previousScreen;
			gameInstance.previousScreen = gameInstance.currentScreen;
			gameInstance.currentScreen.show();
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void disposeAssets() {
		// TODO Auto-generated method stub
		
	}
	public boolean keyUp(int keycode){	
		if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE ){
			System.out.println("Helooooooooooooo back is touched from menu");
			
		}
		return true;
	}

}
