package com.teamdelta.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * 
 * @author Ismael Poteau 
 * @version 1.0.0.0
 * @since 2/9/16
 * 
 * The AbstractScreen class defines the screens for the game.
 * 
 */

public abstract class AbstractScreen extends InputAdapter implements Screen {
	Main gameInstance;
	SpriteBatch batch;
	OrthographicCamera camera;
	TextureAtlas atlas;
	boolean paused;
	
	public AbstractScreen(Main game) {
		gameInstance = game;
		batch = game.batch;
		camera = game.camera;
		atlas = gameInstance.assetMgr.get("game.pack", TextureAtlas.class);
	}

	abstract void loadAssets();
	abstract void disposeAssets();

	abstract void update(float timeSinceLastFrame);

}
