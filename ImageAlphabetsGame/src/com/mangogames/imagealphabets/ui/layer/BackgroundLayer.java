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
package com.mangogames.imagealphabets.ui.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.netthreads.libgdx.director.AppInjector;
import com.netthreads.libgdx.director.Director;
import com.netthreads.libgdx.scene.Layer;

/**
 * @author Vennela
 * 
 */
public class BackgroundLayer extends Layer
{
	private Director director;
	public Image     image;
	public BackgroundLayer(float width, float height)
	{
		setWidth(width);
		setHeight(height);
		buildElements();
	}

	/**
	 * Build view elements.
	 * Add background image to the scene
	 */
	public void buildElements()
	{
		// ---------------------------------------------------------------
		// Background.
		// ---------------------------------------------------------------		
		director= AppInjector.getInjector().getInstance(Director.class);
		String bgname = "data/"+director.getWidth()+"X"+director.getHeight()+"/kids_game_BG.jpg";
		image = new Image(new Texture(Gdx.files.internal(bgname)));

		addActor(image);
	}

}
