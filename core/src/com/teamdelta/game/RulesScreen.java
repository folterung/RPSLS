package com.teamdelta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class RulesScreen extends AbstractScreen {
	Button closeButton;
	Button rockButton;
	Button paperButton;
	Button scissorsButton;
	Button lizardButton;
	Button spockButton;
	BitmapFont smallFont;
	BitmapFont bigFont;
	TextureRegion greenHighlight;
	TextureRegion redHighlight;
	Vector3 input;

	public RulesScreen(Main game) {
		super(game);
		input = new Vector3();

		greenHighlight = atlas.findRegion("green");
		redHighlight = atlas.findRegion("red");
		bigFont = new BitmapFont(Gdx.files.internal("calibri.fnt"));
		smallFont = new BitmapFont(Gdx.files.internal("calibrismall.fnt"));

		closeButton = new Button(atlas.findRegion("CLOSEBUTTON"),
				atlas.findRegion("CLOSEBUTTONSELECTED"), new Rectangle(
						340, 50, 120, 40));
		rockButton = new Button(atlas.findRegion("rock"),
				atlas.findRegion("rock"), new Rectangle(
						50, 350, 100, 150));
		paperButton = new Button(atlas.findRegion("paper"),
				atlas.findRegion("paper"), new Rectangle(
						650, 350, 100, 150));
		scissorsButton = new Button(atlas.findRegion("scissors"),
				atlas.findRegion("scissors"), new Rectangle(
						50, 150, 100, 150));
		lizardButton = new Button(atlas.findRegion("lizard"),
				atlas.findRegion("lizard"), new Rectangle(
						650, 150, 100, 150));
		spockButton = new Button(atlas.findRegion("spock"),
				atlas.findRegion("spock"), new Rectangle(
						350, 275, 100, 150));
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void render(float delta) {
		renderMessage();
		if (rockButton.selected) {
			batch.draw(redHighlight, scissorsButton.colisionRect.x,
					scissorsButton.colisionRect.y,
					scissorsButton.colisionRect.width,
					scissorsButton.colisionRect.height);
			batch.draw(redHighlight, lizardButton.colisionRect.x,
					lizardButton.colisionRect.y,
					lizardButton.colisionRect.width,
					lizardButton.colisionRect.height);
		}
		if (paperButton.selected) {
			batch.draw(redHighlight, rockButton.colisionRect.x,
					rockButton.colisionRect.y, rockButton.colisionRect.width,
					rockButton.colisionRect.height);
			batch.draw(redHighlight, spockButton.colisionRect.x,
					spockButton.colisionRect.y, spockButton.colisionRect.width,
					spockButton.colisionRect.height);
		}
		if (scissorsButton.selected) {
			batch.draw(redHighlight, paperButton.colisionRect.x,
					paperButton.colisionRect.y, paperButton.colisionRect.width,
					paperButton.colisionRect.height);
			batch.draw(redHighlight, lizardButton.colisionRect.x,
					lizardButton.colisionRect.y,
					lizardButton.colisionRect.width,
					lizardButton.colisionRect.height);
		}
		if (lizardButton.selected) {
			batch.draw(redHighlight, spockButton.colisionRect.x,
					spockButton.colisionRect.y, spockButton.colisionRect.width,
					spockButton.colisionRect.height);
			batch.draw(redHighlight, paperButton.colisionRect.x,
					paperButton.colisionRect.y, paperButton.colisionRect.width,
					paperButton.colisionRect.height);
		}
		if (spockButton.selected) {
			batch.draw(redHighlight, scissorsButton.colisionRect.x,
					scissorsButton.colisionRect.y,
					scissorsButton.colisionRect.width,
					scissorsButton.colisionRect.height);
			batch.draw(redHighlight, rockButton.colisionRect.x,
					rockButton.colisionRect.y, rockButton.colisionRect.width,
					rockButton.colisionRect.height);
		}

		// --Rock
		if (rockButton.selected) {
			batch.draw(greenHighlight, rockButton.colisionRect.x,
					rockButton.colisionRect.y, rockButton.colisionRect.width,
					rockButton.colisionRect.height);
			rockButton.draw(batch);

		} else {
			rockButton.draw(batch);
		}
		// --Paper
		if (paperButton.selected) {
			batch.draw(greenHighlight, paperButton.colisionRect.x,
					paperButton.colisionRect.y, paperButton.colisionRect.width,
					paperButton.colisionRect.height);
			paperButton.draw(batch);

		} else {
			paperButton.draw(batch);
		}
		// --Scissors
		if (scissorsButton.selected) {
			batch.draw(greenHighlight, scissorsButton.colisionRect.x,
					scissorsButton.colisionRect.y,
					scissorsButton.colisionRect.width,
					scissorsButton.colisionRect.height);
			scissorsButton.draw(batch);

		} else {
			scissorsButton.draw(batch);
		}
		// --Lizard
		if (lizardButton.selected) {
			batch.draw(greenHighlight, lizardButton.colisionRect.x,
					lizardButton.colisionRect.y,
					lizardButton.colisionRect.width,
					lizardButton.colisionRect.height);
			lizardButton.draw(batch);

		} else {
			lizardButton.draw(batch);
		}
		// --Spock
		if (spockButton.selected) {
			batch.draw(greenHighlight, spockButton.colisionRect.x,
					spockButton.colisionRect.y, spockButton.colisionRect.width,
					spockButton.colisionRect.height);
			spockButton.draw(batch);

		} else {
			spockButton.draw(batch);
		}
		
		closeButton.draw(batch);
	}
	
	void renderMessage(){
		bigFont.draw(batch, "----RULES----", 300, 620);
		bigFont.draw(batch, "Click a character to check how it wins", 200, 580);
		if(rockButton.selected){
			bigFont.draw(batch, "Rock:", 350, 210);
			smallFont.draw(batch, "crushes lizard", 350, 180);
			smallFont.draw(batch, "crushes scissors", 350, 160);
		}
		if(paperButton.selected){
			bigFont.draw(batch, "Paper:", 350, 210);
			smallFont.draw(batch, "dispproves Spock", 350, 180);
			smallFont.draw(batch, "covers rock", 350, 160);
		}
		if(scissorsButton.selected){
			bigFont.draw(batch, "Scissors:", 350, 210);
			smallFont.draw(batch, "cuts paper", 350, 180);
			smallFont.draw(batch, "decapitates lizard", 350, 160);
		}
		if(lizardButton.selected){
			bigFont.draw(batch, "Lizard", 350, 210);
			smallFont.draw(batch, "poisons Spock", 350, 180);
			smallFont.draw(batch, "eats paper", 350, 160);
		}
		
		if(spockButton.selected){
			bigFont.draw(batch, "Spock:", 350, 210);
			smallFont.draw(batch, "smashes scissors", 350, 180);
			smallFont.draw(batch, "vaporizes rock", 350, 160);
		}
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
	public void hide() {
		Gdx.input.setCatchBackKey(false);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	void loadAssets() {
		// TODO Auto-generated method stub

	}

	@Override
	void disposeAssets() {
		// TODO Auto-generated method stub

	}

	@Override
	void update(float timeSinceLastFrame) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		input.x = screenX;
		input.y = screenY;

		gameInstance.camera.unproject(input);

		if (closeButton.colisionRect.contains(input.x, input.y)) {
			closeButton.selected = true;
		} else {
			closeButton.selected = false;
		}
		if (rockButton.colisionRect.contains(input.x, input.y)) {
			rockButton.selected = true;
		} else {
			rockButton.selected = false;
		}
		if (paperButton.colisionRect.contains(input.x, input.y)) {
			paperButton.selected = true;
		} else {
			paperButton.selected = false;
		}
		if (scissorsButton.colisionRect.contains(input.x, input.y)) {
			scissorsButton.selected = true;
		} else {
			scissorsButton.selected = false;
		}
		if (lizardButton.colisionRect.contains(input.x, input.y)) {
			lizardButton.selected = true;
		} else {
			lizardButton.selected = false;
		}
		if (spockButton.colisionRect.contains(input.x, input.y)) {
			spockButton.selected = true;
		} else {
			spockButton.selected = false;
		}

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		input.x = screenX;
		input.y = screenY;

		gameInstance.camera.unproject(input);
		closeButton.selected = false;
		rockButton.selected = false;
		paperButton.selected = false;
		scissorsButton.selected = false;
		lizardButton.selected = false;
		spockButton.selected = false;
		if (closeButton.colisionRect.contains(input.x, input.y)) {
			this.hide();
			gameInstance.currentScreen = gameInstance.previousScreen;
			gameInstance.previousScreen = gameInstance.currentScreen;
			gameInstance.currentScreen.show();
		}
		return true;
	}

	// -----end RulesScreen
}
