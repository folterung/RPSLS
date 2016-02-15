package com.teamdelta.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.teamdelta.game.entities.RPSLSEntity;

/**
 * 
 * @author Jake Herritt
 * @version 1.0.0.0
 * @since 2/9/16
 * 
 * The AbstractEntity class defines controls for the player game play.
 * 
 */

public abstract class AbstractEntity {
	String name;
	Sprite skin;
	boolean turnToPlay;
	int winScore;
	int loseScore;
	int tieScore; //Edited by Jeff Kunert
	RPSLSEntity choice;//removed enum and made choice RPSLSEntity
	TextureAtlas atlas;
	
	abstract void render(float timeSinceLastFrame, SpriteBatch bath);
	abstract void update(float timeSinceLastFrame);
}
