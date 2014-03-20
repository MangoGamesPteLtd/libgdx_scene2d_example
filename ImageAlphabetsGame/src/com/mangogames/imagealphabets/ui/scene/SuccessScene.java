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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mangogames.imagealphabets.model.Game;
import com.netthreads.libgdx.director.AppInjector;
import com.netthreads.libgdx.director.Director;
import com.netthreads.libgdx.scene.Scene;

/**
 * @author Vennela
 * 
 */
public class SuccessScene extends Scene
{
	private Image    successImage;
	private Director director;
	private Music    sound;
	private Game     game;
	
	public SuccessScene(Game game)
	{
		this.game = game;
		director = AppInjector.getInjector().getInstance(Director.class);
		loadLayer();
	}

	private void loadLayer() 
	{
		successImage = new Image(new Texture(Gdx.files.internal("data/greatjob.png")));
		successImage.setPosition(100, 0);
		
		successImage.addAction(Actions.sequence(Actions.delay(3f),
	            Actions.run(onSuccessFinishedRunnable)));

        // and finally we add the actor to the stage
        addActor(successImage);
	}
	
	/**
	 * Go to the next set of alphabets board scene
	 */
	Runnable onSuccessFinishedRunnable = new Runnable() {

		//change to board scene
	    @Override
	    public void run() {
	    	game.setGameinstance(1);
	    	sound = loadMusic("letsplay.mp3");
	    	sound.play();
	    	//sound.dispose();
	       director.setScene(new BoardScene(game));
	    }
	};
	
	private static Music loadMusic(String filename)
	{
		return Gdx.audio.newMusic(Gdx.files.internal("data/sounds/"+filename));
	}
	
	
}
