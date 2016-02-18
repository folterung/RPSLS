package com.teamdelta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.teamdelta.game.entities.EntityNames;

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
<<<<<<< HEAD
 * 2/14/16 - calculate winner using new GameLogic class - warnock
 * 2/14/16 - refactored to set player choice to corresponding RPSLSEntity - warnock
 * 2/14/16 - added click sound to buttons - Warnock
 * 2/16/16 - repositioned CPU Player Indicator to align with Player Indicator position - Warnock
 * 2/16/16 - Added Images to placeholders - Warnock
 * 2/17/16 - Changed Font color to Black - Warnock
 * 2/17/16 - Added Background - Warnock
 * 2/17/16 - Repositioned score for CPU - Warnock
 * 2/18/16 - added delay for CPU turn - Ismael
=======
>>>>>>> 1d932b532b0df74d8baf727851e302726dcd93c5
 */
public class GameScreen extends AbstractScreen {
	Button rockButton;
	Button paperButton;
	Button scissorsButton;
	Button lizardButton;
	Button spockButton;
	Button aboutButton;
	Button rulesButton;
	Button closeButton;
	Button playAgainButton;
	User player;
	CPU cpu;
	BitmapFont font;
	Vector3 input;
	boolean playerTookTurn, cpuTookTurn, gameover, isGameTied, isPlayerWin, isCPUWin;
	float cpuTurnTimer;//--Ismael added delay timer for CPU
	
	public GameScreen(Main gameS) {
		super(gameS);

		input = new Vector3();
		font = new BitmapFont(Gdx.files.internal("calibrismall.fnt"));
		font.setColor(new Color(Color.BLACK));

		player = new User(atlas);
		cpu = new CPU(atlas);

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
		
		//Bill added placeholders
		rockButton 		= gameS.rockEntity.setButton(new Rectangle(50, 350, 100, 150));
		paperButton 	= gameS.paperEntity.setButton(new Rectangle(650, 350, 100, 150));
		scissorsButton 	= gameS.scissorsEntity.setButton(new Rectangle(50, 150, 100, 150));
		lizardButton 	= gameS.lizardEntity.setButton(new Rectangle(650, 150, 100, 150));
		spockButton 	= gameS.spockEntity.setButton(new Rectangle(350, 275, 100, 150));
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
	public void render(float delta) {
		renderBackground();
		renderButtons();
		renderScores();
		renderFPS();
		renderMessages();
		
		player.skin.draw(batch);
		cpu.skin.draw(batch);
	}

	void renderBackground() {
		batch.draw(atlas.findRegion("ROCK_BACKGROUND"), 0, 0);
	}

	void renderButtons() {
		rockButton.draw(batch);
		paperButton.draw(batch);
		scissorsButton.draw(batch);
		lizardButton.draw(batch);
		spockButton.draw(batch);
		aboutButton.draw(batch);
		rulesButton.draw(batch);
		closeButton.draw(batch);
		//Ismael added render playAgainButton when game is over
		if(gameover){
			playAgainButton.draw(batch);
		}
	}

	void renderScores() {
		font.draw(batch, "Wins:  " + player.winScore, 50, 560);
		font.draw(batch, "Losses:  " + player.loseScore, 50, 540);
		font.draw(batch, "Ties:  " + player.tieScore, 50, 520); //Jeff Added Tie Score for cpu

		font.draw(batch, "Wins:  " + cpu.winScore, 650, 560);
		font.draw(batch, "Losses:  " + cpu.loseScore, 650, 540);
		font.draw(batch, "Ties:  " + cpu.tieScore, 650, 520); //Jeff Added Tie Score for cpu

	}

	void renderFPS() {
		//font.draw(batch, "FPS " + Gdx.graphics.getFramesPerSecond(), 700, 620);

	}

	void renderMessages() {
		if(player.turnToPlay){
			font.draw(batch, "Your Turn", 350, 600);
		}
		if(cpu.turnToPlay){
			font.draw(batch, "CPU's Turn", 350, 600);
		}
		if(playerTookTurn){
			font.draw(batch, "You chose: " + player.choice.getName(), 180, 500); //-- Adjusted Positioning - Ismael
		}
		if(cpuTookTurn){
			font.draw(batch, "CPU chose: " + cpu.choice.getName(), 470, 500); //-- Adjusted Positioning - Ismael
		}
		if(gameover){
			font.draw(batch, "Play Again?", 355, 230);//Ismael added play again message when game is over
			if(isGameTied){
				font.draw(batch, "Game is tied", 350, 620);
			}
			if(isPlayerWin){
				font.draw(batch, "You won this round", 320, 620);
			}
			if(isCPUWin){
				font.draw(batch, "CPU won this round", 320, 620);
			}
		}
	}

	@Override
	void update(float timeSinceLastFrame) {
// calculate winner using new GameLogic class - warnock
// Added in boolean set for the win, lose and tie - Bryant
		if (playerTookTurn && cpuTookTurn && !gameover) {
			int winnerValue = gameInstance.gameLogic.determineWinner(player.choice, cpu.choice);

			isGameTied = false;
			isCPUWin = false;
			isPlayerWin = false;
			
			gameover = true;

			if(winnerValue == -1) {
				isGameTied = true;
				cpu.tieScore ++;
				player.tieScore ++;
			} else if(winnerValue == 0) {
				isCPUWin= true;
				cpu.winScore++;
				player.loseScore++;
				isCPUWin = true;
			} else if(winnerValue == 1) {
				isPlayerWin = true;
				cpu.loseScore++;
				player.winScore++;
				isPlayerWin = true;
			}
		}

		if (playerTookTurn && cpu.turnToPlay && !cpuTookTurn) {
			cpuTurnTimer += timeSinceLastFrame; // --Delay timer
			if(cpuTurnTimer >= 2){
				cpu.choice = gameInstance.gameLogic.getEntity();
				cpuTookTurn = true;
				cpu.turnToPlay = false;
				cpuTurnTimer = 0; //--Reset timer
			}
		}

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
		if(gameover){
				if (playAgainButton.colisionRect.contains(input.x, input.y)) {
				resetGameState();
			}
		}
// refactored to set player choice to corresponding RPSLSEntity - warnock
		if(player.turnToPlay) {
			if (rockButton.colisionRect.contains(input.x, input.y)) {
				player.choice = gameInstance.gameLogic.getEntity(EntityNames.ROCK);
			} else if(paperButton.colisionRect.contains(input.x, input.y)) {
				player.choice = gameInstance.gameLogic.getEntity(EntityNames.PAPER);
			} else if(scissorsButton.colisionRect.contains(input.x, input.y)) {
				player.choice = gameInstance.gameLogic.getEntity(EntityNames.SCISSORS);
			} else if(lizardButton.colisionRect.contains(input.x, input.y)) {
				player.choice = gameInstance.gameLogic.getEntity(EntityNames.LIZARD);
			} else if(spockButton.colisionRect.contains(input.x, input.y)) {
				player.choice = gameInstance.gameLogic.getEntity(EntityNames.SPOCK);
			} else {
				return true;
			}

			playerTookTurn = true;
			player.turnToPlay = false;
			cpu.turnToPlay = true;
		}

		return true;
	}

	private void resetGameState() {
		gameover = false;
		player.turnToPlay = true;
		cpu.turnToPlay = false;
		playerTookTurn = false;
		cpuTookTurn = false;
		isCPUWin = false;
		isGameTied = false;
		isPlayerWin = false;
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

		if (player.turnToPlay) {
			if (rockButton.colisionRect.contains(input.x, input.y)) {

			}
			if (rockButton.colisionRect.contains(input.x, input.y)) {

			}
			if (rockButton.colisionRect.contains(input.x, input.y)) {

			}
			if (rockButton.colisionRect.contains(input.x, input.y)) {

			}
			if (rockButton.colisionRect.contains(input.x, input.y)) {

			}
			if (rockButton.colisionRect.contains(input.x, input.y)) {

			}
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
