/*******************************************************************************

Copyright (c) 2014 Mango Games Interactive Pte Ltd

This source file is generated from Mango Games prototype code
 
Permission is hereby granted, free of charge, to any person obtaining a copy

of this software and associated documentation files (the "Software"), to deal

in the Software without restriction, including without limitation the rights

to use, copy, modify, merge, publish, distribute, sublicense, and/or sell

copies of the Software, and to permit persons to whom the Software is

furnished to do so, subject to the following conditions:

*******************************************************************************/
package com.mangogames.imagealphabets.ui.sprite;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mangogames.imagealphabets.model.Alphabets;
import com.netthreads.libgdx.action.ActionCallBack;
import com.netthreads.libgdx.sprite.AnimatedSprite;

public class ImageSprite extends AnimatedSprite implements TweenCallback, ActionCallBack
{
	private String    imageName;
	private Alphabets alphabetName;
	private int       colpos;
	
	/**
	 * Create Image sprite.
	 * 
	 * @param textureregion
	 *            
	 * @param rows
	 *            
	 * @param cols
	 *            
	 * @param frameDuration
	 *            Frame duration seconds.
	 */

	public ImageSprite(TextureRegion textureRegion, int rows, int cols, float frameDuration)
	{
		super(textureRegion, rows, cols, frameDuration);
	}

	

	@Override
	public void onCallBack() {
		
	}

	@Override
	public void onEvent(int arg0, BaseTween<?> arg1) {
		
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Alphabets getAlphabetName() {
		return alphabetName;
	}

	public void setAlphabetName(Alphabets alphabetName) {
		this.alphabetName = alphabetName;
	}

	public int getColpos() {
		return colpos;
	}

	public void setColpos(int colpos) {
		this.colpos = colpos;
	}

}
