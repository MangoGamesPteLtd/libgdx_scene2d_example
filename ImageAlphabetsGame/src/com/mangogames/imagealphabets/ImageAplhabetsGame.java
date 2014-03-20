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
package com.mangogames.imagealphabets;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mangogames.imagealphabets.ui.scene.SplashScene;
import com.netthreads.libgdx.director.AppInjector;
import com.netthreads.libgdx.director.Director;

/**
 * @author Vennela
 * 
 */
public class ImageAplhabetsGame implements ApplicationListener
{
	private Director    director;
	private SplashScene splashScene;
	int                 width, height;
	
	@Override
	public void create() {
		width = Gdx.graphics.getWidth();
		height= Gdx.graphics.getHeight();
		
		director = AppInjector.getInjector().getInstance(Director.class);
		// Set initial width and height.
		director.setWidth(width);
		director.setHeight(height);
		
		// Add this as an event observer.
		Texture.setEnforcePotImages(false);
		//boardScene = getBoardScene();
		splashScene = getSplashScene();
		director.setScene(splashScene);
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void render() {

		director.update();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() 
	{
		director.dispose();
	}
	
	public SplashScene getSplashScene()
	{
		if (splashScene == null)
		{
			splashScene = new SplashScene();
		}
		
		return splashScene;
	}
}
