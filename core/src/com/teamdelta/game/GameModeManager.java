package com.teamdelta.game;

public class GameModeManager {
	AbstractGameMode currentGameMode;
	ClassicGameMode classicGameMode;
	TimeAttackGameMode timeAttackGameMode;
	
	public GameModeManager(GameScreen gameScreen) {
		classicGameMode = new ClassicGameMode(gameScreen.atlas, gameScreen);
		timeAttackGameMode = new TimeAttackGameMode(gameScreen.atlas, gameScreen);
		currentGameMode = classicGameMode;
	}
	
	public void render(float timePassed){
		currentGameMode.render(timePassed);
	}
	
	public void update(float timePassed){
		currentGameMode.update(timePassed);
	}
	
	public void reset(){
		currentGameMode.reset();
	}

}
