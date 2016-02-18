package com.teamdelta.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.teamdelta.game.entities.EntityNames;
import com.teamdelta.game.entities.RPSLSEntity;

public abstract class AbstractGameMode {
	GameScreen gameScreen;
	GameLogic gameLogic;
	SpriteBatch batch;
	User player;
	CPU cpu;
	Vector3 input;
	TextureAtlas atlas;	
	BitmapFont font;
	Button rockButton;
	Button paperButton;
	Button scissorsButton;
	Button lizardButton;
	Button spockButton;
	
	
	//--Entity Information - warnock
	RPSLSEntity rockEntity;
	RPSLSEntity paperEntity;
	RPSLSEntity scissorsEntity;
	RPSLSEntity lizardEntity;
	RPSLSEntity spockEntity;

	
	boolean playerTookTurn, cpuTookTurn, gameover, isGameTied, isPlayerWin, isCPUWin;
	
	AbstractGameMode(TextureAtlas atlas, GameScreen gameScreen){
		this.gameScreen 	= gameScreen; 
		this.atlas 			= atlas;
		this.batch 			= gameScreen.batch;
		
		input 				= new Vector3();
		player 				= new User(atlas);
		cpu 				= new CPU(atlas);
		createEntities(atlas);
	}
	//create entities - Warnock
	public void createEntities(TextureAtlas atlas) {
		String rock = EntityNames.ROCK.toString();
		String paper = EntityNames.PAPER.toString();
		String scissors = EntityNames.SCISSORS.toString();
		String lizard = EntityNames.LIZARD.toString();
		String spock = EntityNames.SPOCK.toString();

		//Create RPSLSEntities
		rockEntity 		= new RPSLSEntity(rock, atlas.findRegion(rock));
		paperEntity 	= new RPSLSEntity(paper, atlas.findRegion(paper));
		scissorsEntity 	= new RPSLSEntity(scissors, atlas.findRegion(scissors));
		lizardEntity 	= new RPSLSEntity(lizard, atlas.findRegion(lizard));
		spockEntity 	= new RPSLSEntity(spock, atlas.findRegion(spock));
	}
	
	abstract void reset();
	abstract void render(float timePassed);
	abstract void update(float timePassed);
}
