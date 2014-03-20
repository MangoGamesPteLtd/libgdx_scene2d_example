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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.esotericsoftware.tablelayout.Cell;
import com.mangogames.imagealphabets.model.Alphabets;
import com.mangogames.imagealphabets.model.Game;
import com.mangogames.imagealphabets.ui.scene.BoardScene;
import com.mangogames.imagealphabets.ui.scene.SuccessScene;
import com.mangogames.imagealphabets.ui.sprite.AlphabetSprite;
import com.mangogames.imagealphabets.ui.sprite.ImageSprite;
import com.netthreads.libgdx.director.AppInjector;
import com.netthreads.libgdx.director.Director;
import com.netthreads.libgdx.scene.Layer;


/**
 * @author Vennela
 * 
 */

public class AlphabetsLayer extends Layer
{
	private Table 		   alphabetTable;
	private TextureAtlas   textureAtlas, textureAtlas2;
	public  boolean 	   isTouched;
	private AlphabetSprite alphabetSprite;
	private Game 		   game;
	private Music 		   sound;
	private Director 	   director;
	private BoardScene 	   boardScene;
	private int 		   count = 0;
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param game
	 * @param scene
	 */
	public AlphabetsLayer(float width, float height, Game game, BoardScene scene)
	{
		boardScene = scene;
		setWidth(width);
		setHeight(height);
		loadTextures();
		this.game=game;
		buildElements();
		if(game.getGameinstance()==0)
		{
			sound = loadMusic("match.mp3");
			sound.play();
			//sound.dispose();
		}
	}
	
	/**
	 * loads the alphabet images from atlas file
	 */
	public void loadTextures()
	{
		director = AppInjector.getInjector().getInstance(Director.class);
		String alphaname = "data/"+director.getWidth()+"X"+director.getHeight()+"/alphabetspack.atlas";
		String alphablankname = "data/"+director.getWidth()+"X"+director.getHeight()+"/blankalphabetspack.atlas";
		textureAtlas = new TextureAtlas(alphaname);
		textureAtlas2 = new TextureAtlas(alphablankname);
	}
	
	/**
	 * Create alphabet sprites and render it on boardscene
	 */
	private void buildElements()
	{
	
		Alphabets alp;
	  
		alphabetTable = new Table();
		alphabetTable.setPosition(0,0);
		alphabetTable.setSize(getWidth(), getHeight());
		List<Alphabets> alphabets = new ArrayList<Alphabets>();
		Collections.reverse(game.getUsedAlphabetsList());
		for(int i=0; i<3;i++)
		{
				alphabets.add(game.getUsedAlphabetsList().get(i));
		}
		alphabets.add(game.getAlphabetList().get(0));
		alphabets.add(game.getAlphabetList().get(1));
		
		alphabets = game.shuffle(alphabets);
		for(int j =0; j<alphabets.size();j++)
		{
			alp = alphabets.get(j);
			alphabetSprite = createAlphaSprite(alp.getFilledAlphabetName(), true, j);
			alphabetTable.add(alphabetSprite).padLeft(40);
		}
		
		alphabetTable.row();
		alphabetTable.setFillParent(true);
		alphabetTable.pack();
		
		addActor(alphabetTable);
	}
	
	/**
	 * 
	 * @param alphabetName
	 * @param filled
	 * @param column
	 * @return
	 */
	protected AlphabetSprite createAlphaSprite(String alphabetName,boolean filled, int column)
	{
		AtlasRegion region;
		if(filled)
			region = textureAtlas.findRegion(alphabetName);
		else
			region = textureAtlas2.findRegion(alphabetName);
		alphabetSprite = new AlphabetSprite(region, 1, 1, 0.2f );
		alphabetSprite.setColpos(column);
		alphabetSprite.setAlphabetName(alphabetName);
		alphabetSprite.setAlphaName(new Alphabets(alphabetName.charAt(0)));
		if(count<3)
			handleTouchEvents(alphabetSprite);
		
		return alphabetSprite;
	}
	
	/**
	 * Perform DragAndDrop functionality  
	 * @param actor
	 */
	public void handleTouchEvents(final Actor actor)
	{
		final AlphabetSprite alphabetSprite= (AlphabetSprite) actor;	
		DragAndDrop dragAndDrop = new DragAndDrop();
		
		char ch= alphabetSprite.getAlphabetName().charAt(0);
		String s=ch+"2";
		
		if(alphabetSprite.getAlphabetName().equals(s))
		{
			dragAndDrop.addSource(new Source(alphabetSprite) {
				
				@Override
				public Payload dragStart(InputEvent event, float x, float y,
						int pointer) {	
					alphabetSprite.setDropSuccessful(false);
					setAlphabetSprite(0, alphabetSprite.getColpos(), true);
					Payload payload = new Payload();
					payload.setDragActor(alphabetSprite);
					return payload;
				}
				
				@Override
				public void dragStop(InputEvent event, float x, float y,
						int pointer, Target target) {
					super.dragStop(event, x, y, pointer, target);
					if(alphabetSprite.isDropSuccessful()==false)
					{
						setAlphabetSprite(0, alphabetSprite.getColpos(), false);
					}
					
				}
			});	
			
			dragAndDrop.addTarget(new Target(boardScene.getBackgroundLayer().image) {
				public boolean drag(Source source, Payload payload, float x,
						float y, int pointer) {
					return true;
				}
				@Override
				public void drop(Source source, Payload payload, float x, float y,
						int pointer) {
						setAlphabetSprite(0, ((AlphabetSprite) payload.getDragActor()).getColpos(), false);
						if(alphabetSprite.isDropSuccessful()==false)
							alphabetSprite.setDropSuccessful(true);
				}
			});
			
			int length = boardScene.getImagesLayer().imageTable.getCells().size();
			for(int i=0;i<length;i++)
			{
				final int col=i;
				final ImageSprite imageSprite = boardScene.getImageSprite(0, col);
				dragAndDrop.addTarget(new Target(imageSprite) {
					public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
					getActor().setColor(Color.GREEN);
					return true;
				}
				
				@Override
				public void drop(Source source, Payload payload, float x, float y,
						int pointer) {
					if(alphabetSprite.isDropSuccessful()==false)
						alphabetSprite.setDropSuccessful(true);
					if(imageSprite.getImageName().charAt(0)==alphabetSprite.getAlphabetName().charAt(0))
					{
						boardScene.setImageSprite(0, col);	
						String alphasound = imageSprite.getImageName().charAt(0)+".mp3";
						sound = loadMusic(alphasound);
						sound.play();
				       // sound.dispose();
						count++;
						if(count == 3)
						{
							addAction(Actions.sequence(Actions.delay(2f),Actions.run(onCountThreeFinishedRunnable)));
						}
					}
					else
					{
						setAlphabetSprite(0, ((AlphabetSprite) payload.getDragActor()).getColpos(), false);
						}
					}
				});
			}
		}
	}
	
	/**
	 * get the music file from assets
	 * @param filename
	 * @return
	 */
	private static Music loadMusic(String filename)
	{
		return Gdx.audio.newMusic(Gdx.files.internal("data/sounds/"+filename));
	}
	
	/**
	 * Go to the next scene (successscene) 
	 */
	Runnable onCountThreeFinishedRunnable = new Runnable() {

	    @Override
	    public void run() {
	    	
	    	director.setScene(new SuccessScene(game));
	    }
	};
	
	/**
	 * get the sprite from the scene in that particular row and column
	 * @param row
	 * @param col
	 * @return
	 */
	public AlphabetSprite getAlphabetSprite(int row, int col)
	{
		AlphabetSprite alphabetSprite = null;
		
		List<Cell> allCells = alphabetTable.getCells();
		if( allCells != null )
		{
			Cell cell = allCells.get(col);
			if( cell.getWidget() instanceof AlphabetSprite )
				alphabetSprite = (AlphabetSprite)cell.getWidget();
		}

		return alphabetSprite;
	}
	
	/**
	 * Set alphabet sprite to that particular row and col
	 * @param row
	 * @param col
	 * @param filled
	 */
	public void setAlphabetSprite(int row, int col, boolean filled)
	{
		AlphabetSprite alphabetSprite = getAlphabetSprite(row, col);
		
		List<Cell> allCells = alphabetTable.getCells();
		if( allCells != null )
		{
			Cell cell = allCells.get(col);
			
			if( cell.getWidget() instanceof AlphabetSprite )
			{
				Alphabets alpha = alphabetSprite.getAlphaName();
				if(filled)
					cell.setWidget(createAlphaSprite(alpha.getAlphabetName(), false, col));
				else
					cell.setWidget(createAlphaSprite(alpha.getFilledAlphabetName(), true, col));
			}
		}	
	}

}
