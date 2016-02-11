package com.teamdelta.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Button {
	TextureRegion textureRegion;
	TextureRegion textureRegionSelected;
	boolean selected;
	Rectangle colisionRect;
	
	public Button(){
		textureRegion = new TextureRegion();
		textureRegionSelected = new TextureRegion();
		colisionRect = new Rectangle();
	}
	public Button(TextureRegion tR, TextureRegion tRs, Rectangle colRect) {
		textureRegion = tR;
		textureRegionSelected = tRs;
		colisionRect = colRect;
	}
	
	void draw(SpriteBatch batch){
		if(!selected){
			batch.draw(textureRegion, colisionRect.x, colisionRect.y, colisionRect.width, colisionRect.height);
		}else{
			batch.draw(textureRegionSelected, colisionRect.x, colisionRect.y, colisionRect.width, colisionRect.height);
		}
	}
}
