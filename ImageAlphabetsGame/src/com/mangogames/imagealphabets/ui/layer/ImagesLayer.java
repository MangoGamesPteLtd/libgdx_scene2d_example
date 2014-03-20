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

import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.esotericsoftware.tablelayout.Cell;
import com.mangogames.imagealphabets.model.Alphabets;
import com.mangogames.imagealphabets.model.Game;
import com.mangogames.imagealphabets.ui.sprite.ImageSprite;
import com.netthreads.libgdx.director.AppInjector;
import com.netthreads.libgdx.director.Director;
import com.netthreads.libgdx.scene.Layer;

/**
 * @author Vennela
 * 
 */
public class ImagesLayer extends Layer 
{
	public Table         imageTable;
	private TextureAtlas textureAtlas, textureAtlas2;
	private Director     director;
	private Game         game;
	
	public ImagesLayer(float width, float height, Game game)
	{
		setPosition(0, height);
		
		setWidth(width);
		setHeight(height);
		
		this.game = game;
		loadTextures();
		buildElements();
	}
	
	/**
	 * loads the image files from atlas file
	 */
	public void loadTextures()
	{
		director = AppInjector.getInjector().getInstance(Director.class);
		String figblankname ="data/"+director.getWidth()+"X"+director.getHeight()+"/figuresblankpack.atlas";
		String figname ="data/"+director.getWidth()+"X"+director.getHeight()+"/figurespack.atlas";
		textureAtlas = new TextureAtlas(figblankname);
		textureAtlas2 = new TextureAtlas(figname);
	}
	
	/**
	 * create image sprites and render it on board scene 
	 */
	private void buildElements()
	{
		Alphabets alp;
		if(game.getAlphabetList().size()<3)
		{
			for(int i=game.getAlphabetList().size();i>0;i--)
			{
				game.getUsedAlphabetsList().add(game.getAlphabetList().get(i-1));
				game.getAlphabetList().remove(i-1);
			}
			
			game.getAlphabetList().addAll(game.getUsedAlphabetsList());
			Gdx.app.log("used list", "::"+game.getUsedAlphabetsList().size());
			for(int j=game.getUsedAlphabetsList().size()-1;j>=0;j--)
			{
				game.getUsedAlphabetsList().remove(j);
			}
		}
		List three = game.getNextThreeAlphabetList();
		imageTable = new Table();
		imageTable.setPosition(0, -50);
		
		imageTable.setSize(getWidth(), getHeight());
		for(int k=0; k< three.size();k++)
		{
			alp = (Alphabets) three.get(k);
			Gdx.app.log("alphabet name", "::"+alp.getImageName());
			ImageSprite iSprite = createImageSprite(alp.getImageName(), false, k);
			imageTable.add(iSprite).pad(19);
		}
		
		imageTable.row();
		imageTable.setFillParent(true);
		imageTable.pack();
		
		addActor(imageTable);
	}
	
	/**
	 * @param imageName
	 * @param filled
	 * @param column
	 * @return
	 */
	protected ImageSprite createImageSprite(String imageName, boolean filled, int column)
	{
		AtlasRegion region;
		if(filled)
			region = textureAtlas2.findRegion(imageName);	
		else
		 region = textureAtlas.findRegion(imageName);
		
		ImageSprite imageSprite = new ImageSprite(region, 1, 1, 0.2f );
		imageSprite.setImageName(imageName);
		imageSprite.setColpos(column);
		imageSprite.setAlphabetName(new Alphabets(imageName.charAt(0)));
		return imageSprite;
	}
	
	/**
	 * Get imagesprite from the scene, in that particular row and column
	 * @param row
	 * @param col
	 * @return
	 */
	public ImageSprite getImageSprite(int row, int col)
	{
		ImageSprite imageSprite = null;
	
		List<Cell> allCells = imageTable.getCells();
		if( allCells != null )
		{
			Cell cell = allCells.get(col);
			
			if( cell.getWidget() instanceof ImageSprite )
			    imageSprite = (ImageSprite)cell.getWidget();
		}
		return imageSprite;
	}
	
	/**
	 * set image sprite to that particular row and column
	 * @param row
	 * @param col
	 */
	public void setImageSprite(int row, int col)
	{
		ImageSprite imageSprite = getImageSprite(row, col);
		List<Cell> allCells = imageTable.getCells();
		
		if( allCells != null )
		{
			Cell cell = allCells.get(col);
			if( cell.getWidget() instanceof ImageSprite )
			{
				Alphabets alpha = imageSprite.getAlphabetName();
			    cell.setWidget(createImageSprite(alpha.getFilledImageName(), true, col));
			}
		}		
	}

}
