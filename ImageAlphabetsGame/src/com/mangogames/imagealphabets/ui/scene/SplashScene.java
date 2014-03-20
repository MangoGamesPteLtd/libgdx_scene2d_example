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
public class SplashScene extends Scene
{
	private Image    splashImage;
	private Director director;
	private Game     game;
	
	public SplashScene()
	{
		game = new Game();
		director = AppInjector.getInjector().getInstance(Director.class);
		loadLayer();
	}

	private void loadLayer() 
	{
		splashImage = new Image(new Texture(Gdx.files.internal("data/splashscene.png")));
		splashImage.setPosition(100, 0);
		splashImage.addAction(Actions.sequence(Actions.fadeOut( 0.000001f ), Actions.fadeIn( 2f ),
	            Actions.run(onSplashFinishedRunnable)));

        // and finally we add the actor to the stage
        addActor(splashImage);
	}
	
	Runnable onSplashFinishedRunnable = new Runnable() {

		//change to board scene
	    @Override
	    public void run() {
	       director.setScene(new BoardScene(game));
	    }
	};
	
}
