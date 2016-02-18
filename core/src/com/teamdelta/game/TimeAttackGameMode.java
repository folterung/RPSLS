package com.teamdelta.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.teamdelta.game.entities.EntityNames;

public class TimeAttackGameMode extends AbstractGameMode {

	TimeAttackGameMode(TextureAtlas atlas, GameScreen gameScreen) {
		super(atlas, gameScreen);
		font 			= gameScreen.font;
	
		//created new instance of GameLogic - Warnock
		gameLogic 		= new GameLogic(this);
		rockButton 		= rockEntity.setButton(new Rectangle(50, 350, 100, 150));
		paperButton 	= paperEntity.setButton(new Rectangle(650, 350, 100, 150));
		scissorsButton 	= scissorsEntity.setButton(new Rectangle(50, 150, 100, 150));
		lizardButton 	= lizardEntity.setButton(new Rectangle(650, 150, 100, 150));
		spockButton 	= spockEntity.setButton(new Rectangle(350, 275, 100, 150));
	}

	@Override
	void reset() {
		player.turnToPlay = true;
		cpu.turnToPlay = false;
		playerTookTurn = false;
		cpuTookTurn = false;
		isCPUWin = false;
		isGameTied = false;
		isPlayerWin = false;
	}

	@Override
	void render(float timePassed) {
		
	}
	
	void renderButtons(){
		rockButton.draw(gameScreen.batch);
		paperButton.draw(gameScreen.batch);
		scissorsButton.draw(gameScreen.batch);
		lizardButton.draw(gameScreen.batch);
		spockButton.draw(gameScreen.batch);
	}

	void renderScores() {
		font.draw(batch, "Wins:  " + player.winScore, 50, 560);
		font.draw(batch, "Losses:  " + player.loseScore, 50, 540);
		font.draw(batch, "Ties:  " + player.tieScore, 50, 520); //Jeff Added Tie Score for cpu

		font.draw(batch, "Wins:  " + cpu.winScore, 600, 560);
		font.draw(batch, "Losses:  " + cpu.loseScore, 600, 540);
		font.draw(batch, "Ties:  " + cpu.tieScore, 600, 520); //Jeff Added Tie Score for cpu

	}

	void renderFPS() {
		//font.draw(batch, "FPS " + Gdx.graphics.getFramesPerSecond(), 700, 620);

	}

	void renderMessages() {
		if(player.turnToPlay){
			font.draw(batch, "Your Turn", 50, 600);
		}
		if(cpu.turnToPlay){
			font.draw(batch, "CPU's Turn", 50, 600);
		}
		if(playerTookTurn){
			font.draw(batch, "You chose: " + player.choice.getName(), 50, 600);
		}
		if(cpuTookTurn){
			font.draw(batch, "CPU chose: " + cpu.choice.getName(), 600, 600);
		}
		if(gameover){
			font.draw(batch, "Play Again?", 355, 230);//Ismael added play again message when game is over
			if(isGameTied){
				font.draw(batch, "Game is tied", 320, 620);
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
	void update(float timePassed) {
		// TODO Auto-generated method stub
		
	}
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		input.x = screenX;
		input.y = screenY;

		gameScreen.gameInstance.camera.unproject(input);

		// refactored to set player choice to corresponding RPSLSEntity - warnock
		if(player.turnToPlay) {
			if (rockButton.colisionRect.contains(input.x, input.y)) {
				player.choice = gameLogic.getEntity(EntityNames.ROCK);
			} else if(paperButton.colisionRect.contains(input.x, input.y)) {
				player.choice = gameLogic.getEntity(EntityNames.PAPER);
			} else if(scissorsButton.colisionRect.contains(input.x, input.y)) {
				player.choice = gameLogic.getEntity(EntityNames.SCISSORS);
			} else if(lizardButton.colisionRect.contains(input.x, input.y)) {
				player.choice = gameLogic.getEntity(EntityNames.LIZARD);
			} else if(spockButton.colisionRect.contains(input.x, input.y)) {
				player.choice = gameLogic.getEntity(EntityNames.SPOCK);
			} else {
				return true;
			}

			playerTookTurn = true;
			player.turnToPlay = false;
			cpu.turnToPlay = true;
		}

		return true;
	}
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		input.x = screenX;
		input.y = screenY;
		

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
}
