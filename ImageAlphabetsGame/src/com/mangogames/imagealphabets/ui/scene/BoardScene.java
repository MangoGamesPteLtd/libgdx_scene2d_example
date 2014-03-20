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
package com.mangogames.imagealphabets.ui.scene;

import com.mangogames.imagealphabets.model.Game;
import com.mangogames.imagealphabets.ui.layer.AlphabetsLayer;
import com.mangogames.imagealphabets.ui.layer.BackgroundLayer;
import com.mangogames.imagealphabets.ui.layer.ImagesLayer;
import com.mangogames.imagealphabets.ui.sprite.AlphabetSprite;
import com.mangogames.imagealphabets.ui.sprite.ImageSprite;
import com.netthreads.libgdx.scene.Scene;


/**
 * @author Vennela
 * 
 */
public class BoardScene extends Scene 
{
	private ImagesLayer     imagesLayer;
	private AlphabetsLayer  alphabetsLayer;
	private BackgroundLayer backgroundLayer;
	private Game            game;
	
	public BoardScene(Game game)
	{
		this.game = game;
		loadLayers();
	}
	
	/**
	 * Create layers
	 */
	public void loadLayers() 
	{
		// ---------------------------------------------------------------
		// Background Layer
		// ---------------------------------------------------------------
		backgroundLayer = new BackgroundLayer(getWidth(), getHeight());
		addLayer(backgroundLayer);
		// ---------------------------------------------------------------
		// Image Layer
		// ---------------------------------------------------------------
		
		imagesLayer    = new ImagesLayer(getWidth(), getHeight()/2, game);
		getInputMultiplexer().addProcessor(imagesLayer);
		addLayer(imagesLayer);
		
		// ---------------------------------------------------------------
		// Alphabets Layer
		// ---------------------------------------------------------------
		alphabetsLayer  = new AlphabetsLayer(getWidth(), getHeight()/2, game, this);
		getInputMultiplexer().addProcessor(alphabetsLayer);
		addLayer(alphabetsLayer);	
		
	}

	public BackgroundLayer getBackgroundLayer()
	{
		return backgroundLayer;
	}
	
	public ImagesLayer getImagesLayer()
	{
		return  imagesLayer;
	}
	
	public AlphabetsLayer getAlphabetsLayer()
	{
		return alphabetsLayer;
	}
	
	public AlphabetSprite getAlphabetSprite(int row, int col)
	{
		return ((AlphabetsLayer)alphabetsLayer).getAlphabetSprite(row, col);
	}
	
	public ImageSprite getImageSprite(int row, int col)
	{
		return ((ImagesLayer)imagesLayer).getImageSprite(row, col);
	}
	
	public void setImageSprite(int row, int col)
	{
		((ImagesLayer)imagesLayer).setImageSprite(row, col);
	} 
}