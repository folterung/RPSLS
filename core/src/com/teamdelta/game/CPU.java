package com.teamdelta.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class CPU extends AbstractEntity {
	
	public CPU(TextureAtlas atlas){
		skin = new Sprite(atlas.findRegion("red"));
		skin.setSize(100, 200);
		skin.setPosition(500, 600);
	}

	@Override
	void render(float timeSinceLastFrame, SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void update(float timeSinceLastFrame) {
		
	}

}
